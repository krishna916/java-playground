package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public interface RecieptService {
    Receipt generateReceipt(Ticket ticket, LocalDateTime exitTime);
}
