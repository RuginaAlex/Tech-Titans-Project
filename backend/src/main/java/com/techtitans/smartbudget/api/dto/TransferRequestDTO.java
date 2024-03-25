package com.techtitans.smartbudget.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private String senderIban;
    private String recipientIban;
    private double amount;


}
