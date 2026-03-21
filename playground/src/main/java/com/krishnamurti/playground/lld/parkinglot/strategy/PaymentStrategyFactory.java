package com.krishnamurti.playground.lld.parkinglot.strategy;

import com.krishnamurti.playground.lld.parkinglot.strategy.impl.UPIPaymentStrategy;

public class PaymentStrategyFactory {

    // dummy method with no logic
    public PaymentStrategy getStrategy() {
        return new UPIPaymentStrategy();
    }
}
