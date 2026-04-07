package com.krishnamurti.playground.lld.parkinglot.controller;

/**
 * A utility class containing string constants used for endpoint paths and system-wide identifiers.
 * This class ensures consistency and centralizes endpoint management for controllers.
 */
public class Constants {

    // Base path for all parking lot-related endpoints.
    public final static String PARKING_LOT = "parking-lot";
    
    // Endpoint for vehicle entry requests.
    public final static String ENTRY = "entry";
    
    // Endpoint for vehicle exit requests.
    public final static String EXIT = "exit";
    
    // Base path for administrative endpoints.
    public final static String PARKING_LOT_ADMIN = PARKING_LOT + "/admin";
    
    // Administrative endpoint for adding a new parking floor.
    public final static String ADD_PARKING_FLOOR = "/floor";
    
    // Administrative endpoint for setting or updating pricing rules.
    public final static String PRICING_RULE = "/pricing";
}
