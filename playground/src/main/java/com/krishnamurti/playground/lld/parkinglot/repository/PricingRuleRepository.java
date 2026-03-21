package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;

import java.util.Optional;
import java.util.UUID;

public interface PricingRuleRepository {

    PricingRule fetchDefault();

    Optional<PricingRule> fetchById(UUID ruleId);

    PricingRule save(Double rate, PricingRule.PricingType type);

}
