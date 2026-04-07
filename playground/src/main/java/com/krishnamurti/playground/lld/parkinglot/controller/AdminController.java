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

/**
 * Controller for administrative operations within the parking lot system.
 * It provides endpoints to manage infrastructure (floors) and business logic (pricing rules).
 */
@RestController(Constants.PARKING_LOT_ADMIN)
public class AdminController {

    // Services required for administrative tasks.
    private final FloorService floorService;
    private final PricingRuleService pricingRuleService;

    /**
     * Constructs the AdminController with required services.
     *
     * @param floorService service for managing parking floors
     * @param pricingRuleService service for managing pricing rules
     */
    public AdminController(FloorService floorService, PricingRuleService pricingRuleService) {
        this.floorService = floorService;
        this.pricingRuleService = pricingRuleService;
    }

    /**
     * Adds a new floor to the parking lot.
     *
     * @param floor the floor details provided in the request body
     * @return a ResponseEntity indicating the floor was successfully created
     */
    @PostMapping(Constants.ADD_PARKING_FLOOR)
    public ResponseEntity<String> addFloor(@RequestBody Floor floor) {
        floorService.addFloor(floor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Adds or updates a pricing rule in the system.
     *
     * @param pricingRule the pricing rule details provided in the request body
     * @return a ResponseEntity indicating the pricing rule was successfully created
     */
    @PostMapping(Constants.PRICING_RULE)
    public ResponseEntity<String> addPricingRule(@RequestBody PricingRule pricingRule) {
        pricingRuleService.save(pricingRule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
