package com.techtitans.smartbudget.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "companies", schema = "public")
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private int id_company;

    @Column(name = "name")
    @NotBlank(message = "Name must not be blank")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Column(name = "grade")
    @NotBlank(message = "Grade must not be blank")
    @Pattern(regexp = "A|B|C|D|E|F|Ungraded", message = "Grade must be: A, B, C, D, E, F, Ungraded")
    private String grade;

    @Column(name = "ticker")
    @NotBlank(message = "Ticker must not be blank")
    @Size(min=2,max=6, message = "Ticker must be between 2 and 6 characters")
    private String ticker;

    @Column(name = "last_date_fetched")
    @PastOrPresent(message = "Last date fetched must be in the past or present")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime last_date_fetched;
}
