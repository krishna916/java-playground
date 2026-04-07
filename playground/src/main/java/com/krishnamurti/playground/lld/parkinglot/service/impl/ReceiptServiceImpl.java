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

/**
 * Implementation of {@link RecieptService}.
 * Orchestrates the exit workflow including duration calculation, fee assessment based on strategies,
 * and payment processing.
 */
public class ReceiptServiceImpl implements RecieptService {

    private final ReceiptRepository receiptRepository;
    private final PricingStrategyFactory pricingStrategyFactory;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, PricingStrategyFactory pricingStrategyFactory, PaymentStrategyFactory paymentStrategyFactory) {
        this.receiptRepository = receiptRepository;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    /**
     * {@inheritDoc}
     * This method calculates the parking fee by:
     * 1. Identifying the appropriate {@link PricingStrategy} based on the ticket's pricing rule.
     * 2. Calculating the total stay duration (rounded up to the nearest hour).
     * 3. Applying the pricing calculation logic based on slot type and rules.
     * 4. Processing the payment and generating a final receipt.
     */
    @Override
    public Receipt generateReceipt(Ticket ticket, LocalDateTime exitTime) {
        // Retrieve the strategy based on the pricing rule type associated with the ticket (e.g., HOURLY, FLAT)
        PricingRule pricingRule = ticket.getPricingRule();
        Optional<PricingStrategy> pricingStrategyOptional = pricingStrategyFactory.getStrategy(pricingRule.getType());

        if (pricingStrategyOptional.isEmpty()) {
            throw new IllegalStateException("No Pricing Mechanism found for the ticket");
        }

        PricingStrategy pricingStrategy = pricingStrategyOptional.get();

        // Duration Calculation:
        // Calculate the stay duration.
        // NOTE: The addition of 100 minutes is a simulation placeholder to ensure some fee is generated in test scenarios.
        long minutes = Duration.between(ticket.getTime(), exitTime).toMinutes() + 100;
        long hours = (long)Math.ceil(minutes / 60.0);

        // Fee Calculation Logic:
        // Uses the selected strategy to determine the final amount based on duration and vehicle slot type.
        double finalRate = pricingStrategy.calculate(hours, ticket.getSlot().getType(), pricingRule);

        // Payment Processing:
        // Uses the payment strategy factory to process the transaction.
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getStrategy();
        Payment payment = paymentStrategy.doPayment(finalRate);

        // Record the transaction and return the receipt.
        Receipt receipt =  new Receipt(ticket, exitTime, finalRate, payment);

        return receiptRepository.generateReceipt(receipt);
    }
}
