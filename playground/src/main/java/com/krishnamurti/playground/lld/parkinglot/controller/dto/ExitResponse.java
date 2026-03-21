package com.krishnamurti.playground.lld.parkinglot.controller.dto;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;

import java.time.LocalDateTime;

public class ExitResponse {

    private LocalDateTime exitTime;
    private Receipt receipt;

    public ExitResponse(LocalDateTime exitTime, Receipt receipt) {
        this.exitTime = exitTime;
        this.receipt = receipt;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Receipt getReceipt() {
        return receipt;
    }
}
