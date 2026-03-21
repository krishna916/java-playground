package com.krishnamurti.playground.lld.parkinglot.strategy;

import com.krishnamurti.playground.lld.parkinglot.domain.Payment;

public interface PaymentStrategy {
    Payment doPayment(double fee);
}
