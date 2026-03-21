package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Payment;
import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.repository.ReceiptRepository;
import com.krishnamurti.playground.lld.parkinglot.service.PricingRuleService;
import com.krishnamurti.playground.lld.parkinglot.service.RecieptService;
import com.krishnamurti.playground.lld.parkinglot.service.TicketService;
import com.krishnamurti.playground.lld.parkinglot.strategy.PaymentStrategy;
import com.krishnamurti.playground.lld.parkinglot.strategy.PaymentStrategyFactory;
import com.krishnamurti.playground.lld.parkinglot.strategy.PricingStrategy;
import com.krishnamurti.playground.lld.parkinglot.strategy.PricingStrategyFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class ReceiptServiceImpl implements RecieptService {

    private final ReceiptRepository receiptRepository;
    private final PricingStrategyFactory pricingStrategyFactory;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, PricingStrategyFactory pricingStrategyFactory, PaymentStrategyFactory paymentStrategyFactory) {
        this.receiptRepository = receiptRepository;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    @Override
    public Receipt generateReceipt(Ticket ticket, LocalDateTime exitTime) {
        PricingRule pricingRule = ticket.getPricingRule();
        Optional<PricingStrategy> pricingStrategyOptional = pricingStrategyFactory.getStrategy(pricingRule.getType());

        if (pricingStrategyOptional.isEmpty()) {
            throw new IllegalStateException("No Pricing Mechanism found for the ticket");
        }

        PricingStrategy pricingStrategy = pricingStrategyOptional.get();

        long minutes = Duration.between(ticket.getTime(), exitTime).toMinutes() + 100; // dummy add for showing spent time
        long hours = (long)Math.ceil(minutes / 60.0);

        double finalRate = pricingStrategy.calculate(hours, ticket.getSlot().getType(), pricingRule);
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getStrategy();
        Payment payment = paymentStrategy.doPayment(finalRate);
        Receipt receipt =  new Receipt(ticket, exitTime, finalRate, payment);

        return receiptRepository.generateReceipt(receipt);
    }
}
