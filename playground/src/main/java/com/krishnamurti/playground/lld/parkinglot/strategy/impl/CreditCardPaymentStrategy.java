package com.krishnamurti.playground.lld.parkinglot.strategy.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Payment;
import com.krishnamurti.playground.lld.parkinglot.strategy.PaymentStrategy;

import java.util.Random;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public Payment doPayment(double fee) {
        Random random = new Random();
        int status = random.nextInt(10);

        return status <= 1 ? new Payment(fee, Payment.Status.FAILED)  : new Payment(fee, Payment.Status.SUCCESS);
    }
}
