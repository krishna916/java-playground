package com.krishnamurti.playground.lld.parkinglot.domain;

/**
 * Defines the types of parking slots available in the parking lot.
 * Each type has a size associated with it, which is used to determine
 * if a vehicle can be accommodated in a particular slot.
 */
public enum SlotType {

    // Smallest slot type, suitable for bicycles or motorbikes
    BIKE ( "BIKE", 2),
    
    // Standard slot type for regular passenger cars
    CAR ("CAR", 4),
    
    // Largest slot type for heavy vehicles like trucks or buses
    TRUCK ("TRUCK", 10)
    ;

    // String representation of the slot type
    private String type;
    
    // Numeric size representing the space required or available
    private int size;

    /**
     * Enum constructor.
     * @param type String label for the type.
     * @param size Numerical capacity/requirement.
     */
    SlotType(String type, int size) {
            this.type = type;
            this.size = size;
    }


    @Override
    public String toString() {
        return this.type;
    }


    /**
     * Checks if this slot type can accommodate a vehicle of the given type.
     * A slot can accommodate a vehicle if its size is greater than or equal to the vehicle's required size.
     *
     * @param type The type of vehicle/slot to check against.
     * @return true if the size is sufficient, false otherwise.
     */
    public boolean willAccomadate(SlotType type) {
        return this.size >= type.size;
    }

    /**
     * @return The numerical size of the slot type.
     */
    public int getSize() {
        return this.size;
    }

}
