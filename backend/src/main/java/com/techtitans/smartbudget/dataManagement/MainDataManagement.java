package com.techtitans.smartbudget.dataManagement;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MainDataManagement {

    @Autowired
    private StockOptionsController stockOptionsController;

    @Autowired
    private StockDataController stockDataController;

    @Async
    public void runInBackground() {

        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors * 10); // Adjust the thread pool size to the number of available processors

        while (true) {
            var companies = Objects.requireNonNull(stockOptionsController.getAllCompanies().getBody());

            CompletableFuture<?>[] futures = companies.stream()
                    .map(company -> CompletableFuture.runAsync(() -> {
                        try {
                            addNewDataInDatabase(company);
                        } catch (Exception e) {
                            // Handle the exception here
                            System.out.println("Error while adding new data in database for company: " + company.getName());
                        }
                    }, executor))
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(futures).join(); // Wait for all futures to complete

        }

    }

    @Async
    public void addNewDataInDatabase(com.techtitans.smartbudget.model.StockOptions company) throws IOException {
        String ticker = company.getTicker();
        var date = company.getLast_date_fetched();

        if (date.getHour() >= 21) {
            date = date.plusDays(1);
            date = date.withHour(14);
        }

        if (date.getHour() <= 14) {
            date = date.withHour(14);
        }

        if (date.isAfter(LocalDateTime.of(2023,1, 20, 0, 0))){
            return;
        }

        OkHttpClient client = new OkHttpClient();
        var url = getUrl(date, ticker);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("APCA-API-KEY-ID", "PKFCH7AQYMLDBQ455PYQ")
                .addHeader("APCA-API-SECRET-KEY", "FaJG6uyKxRaKgYYAuxFGg7h0DV7zTkY2biwYT9rv")
                .build();

        Response response = client.newCall(request).execute();

        assert response.body() != null;
        var responseString = response.body().string();

        var jsonObject = new JSONObject(responseString);

        try {
            jsonObject = jsonObject.getJSONObject("bars");
        } catch (Exception e) {
            return;
        }
        var jsonArray = jsonObject.getJSONArray(ticker);

        var timestamp = date;

        for (int i = 0; i < jsonArray.length(); i++) {
            var data = jsonArray.getJSONObject(i);
            var close = data.getDouble("c");
            var open = data.getDouble("o");
            var low = data.getDouble("l");
            var high = data.getDouble("h");
            var time = data.getString("t");
            var volume = data.getInt("v");
            timestamp = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);

            var companyData = new StockData(0, company, open, high, low, close, timestamp);

            if (close > 0 && open > 0 && low > 0 && high > 0 && timestamp.isAfter(company.getLast_date_fetched())) {
                stockDataController.createCompanyData(companyData);
            }
        }
        timestamp = timestamp.plusHours(1);
        if (timestamp.getHour() >= 21) {
            timestamp = timestamp.plusDays(1);
            timestamp = timestamp.withHour(14);
        }

        company.setLast_date_fetched(timestamp);
        stockOptionsController.updateCompany(company.getId_company(), company);

    }

    @NotNull
    private static String getUrl(LocalDateTime date, String ticker) {
        var url = "https://data.alpaca.markets/v2/stocks/bars?symbols=" + ticker + "&timeframe=1H&start=" + date.getYear();

        if (date.getMonthValue() < 10) {
            url += "-0" + date.getMonthValue();
        } else {
            url += "-" + date.getMonthValue();
        }

        if (date.getDayOfMonth() < 10) {
            url += "-0" + date.getDayOfMonth();
        } else {
            url += "-" + date.getDayOfMonth();
        }

        if (date.getHour() < 10) {
            url += "T0" + date.getHour();
        } else {
            url += "T" + date.getHour();
        }

        if (date.getMinute() < 10) {
            url += "%3A0" + date.getMinute();
        } else {
            url += "%3A" + date.getMinute();
        }

        if (date.getSecond() < 10) {
            url += "%3A0" + date.getSecond() + "Z";
        } else {
            url += "%3A" + date.getSecond() + "Z";
        }

        url += "&limit=1&adjustment=raw&feed=iex&currency=EUR&sort=asc";
        return url;
    }

}