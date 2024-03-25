package com.techtitans.smartbudget.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "stock_data", schema = "public")
public class StockData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_data")
    private long id_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company", referencedColumnName = "id_company")
    @NotNull(message = "Company cannot be null")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StockOptions company;

    @Column(name = "open")
    @DecimalMin(value = "0.0", inclusive = false, message = "Open must be greater than 0")
    private double open;

    @Column(name = "high")
    @DecimalMin(value = "0.0", inclusive = false, message = "High must be greater than 0")
    private double high;

    @Column(name = "low")
    @DecimalMin(value = "0.0", inclusive = false, message = "Low must be greater than 0")
    private double low;

    @Column(name = "close")
    @DecimalMin(value = "0.0", inclusive = false, message = "Close must be greater than 0")
    private double close;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDateTime date;

}