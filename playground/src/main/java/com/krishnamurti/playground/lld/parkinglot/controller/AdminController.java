package com.krishnamurti.playground.lld.parkinglot.controller;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;
import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.service.FloorService;
import com.krishnamurti.playground.lld.parkinglot.service.PricingRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(Constants.PARKING_LOT_ADMIN)
public class AdminController {

    private final FloorService floorService;
    private final PricingRuleService pricingRuleService;

    public AdminController(FloorService floorService, PricingRuleService pricingRuleService) {
        this.floorService = floorService;
        this.pricingRuleService = pricingRuleService;
    }

    @PostMapping(Constants.ADD_PARKING_FLOOR)
    public ResponseEntity<String> addFloor(@RequestBody Floor floor) {
        floorService.addFloor(floor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(Constants.PRICING_RULE)
    public ResponseEntity<String> addPricingRule(@RequestBody PricingRule pricingRule) {
        pricingRuleService.save(pricingRule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
