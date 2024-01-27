package com.techtitans.smartbudget.dataManagement;

import com.techtitans.smartbudget.api.controller.CompanyController;
import com.techtitans.smartbudget.api.controller.CompanyDataController;
import com.techtitans.smartbudget.api.model.Companies;
import com.techtitans.smartbudget.api.model.CompanyData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class MainDataManagement {

    @Autowired
    private CompanyController companyController;

    @Autowired
    private CompanyDataController companyDataController;

    @Async
    public void runInBackground() {
        try {

            while (true) {

                Objects.requireNonNull(companyController.getAllCompanies().getBody()).forEach(company -> {
                    try {
                        addNewDataInDatabase(company);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                Thread.sleep(1000); // Sleep for 1 second
            }
        } catch (Exception e) {
            System.out.println("Background task was interrupted");
            Thread.currentThread().interrupt();
        }

    }

    @Async
    public void addNewDataInDatabase(Companies company) throws IOException {
        String ticker = company.getTicker();
        var date = company.getLast_date_fetched();
        if(date.getHour() < 13){
            date = date.withHour(13);
        }
        else if(date.getHour() >= 21){
            date = date.plusDays(1);
            date = date.withHour(13);
        }
        else{
            date = date.plusHours(1);
        }
        if (date.isAfter(LocalDateTime.now())){
            return;
        }
        company.setLast_date_fetched(date);
        companyController.updateCompany(company.getId_company(),company);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://data.alpaca.markets/v2/stocks/bars?symbols=AAPL&timeframe=1H&start=2021-01-05T00%3A00%3A00Z&end=2021-01-06T00%3A00%3A00Z&adjustment=raw&feed=iex&currency=EUR&sort=asc")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("APCA-API-KEY-ID", "PKFCH7AQYMLDBQ455PYQ")
                .addHeader("APCA-API-SECRET-KEY", "FaJG6uyKxRaKgYYAuxFGg7h0DV7zTkY2biwYT9rv")
                .build();

        Response response = client.newCall(request).execute();

        assert response.body() != null;
        var responseString = response.body().string();

        var jsonObject = new JSONObject(responseString);
        jsonObject = jsonObject.getJSONObject("bars");
        var jsonArray = jsonObject.getJSONArray(ticker);

        for (int i = 0; i < jsonArray.length(); i++) {
            var data = jsonArray.getJSONObject(i);
            var close = data.getDouble("c");
            var open = data.getDouble("o");
            var low = data.getDouble("l");
            var high = data.getDouble("h");
            var time = data.getString("t");
            var timestamp = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);

            var companyData = new CompanyData(0, company, open, high, low, close,timestamp);

            companyDataController.createCompanyData(companyData);

        }

    }

}
