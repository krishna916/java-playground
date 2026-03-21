package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.repository.PricingRuleRepository;
import com.krishnamurti.playground.lld.parkinglot.service.PricingRuleService;

import java.util.Optional;
import java.util.UUID;

public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository pricingRuleRepository;

    public PricingRuleServiceImpl(final PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    @Override
    public Optional<PricingRule> getById(UUID ruleId) {
        return pricingRuleRepository.fetchById(ruleId);
    }

    @Override
    public PricingRule save(PricingRule pricingRule) {
        return pricingRuleRepository.save(pricingRule.getRate(), pricingRule.getType());
    }
}
