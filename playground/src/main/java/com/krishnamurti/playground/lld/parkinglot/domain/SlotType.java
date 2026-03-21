package com.krishnamurti.playground.lld.parkinglot.domain;

public enum SlotType {

    BIKE ( "BIKE", 2),
    CAR ("CAR", 4),
    TRUCK ("TRUCK", 10)
    ;

    private String type;
    private int size;

    SlotType(String type, int size) {
            this.type = type;
            this.size = size;
    }


    @Override
    public String toString() {
        return this.type;
    }


    public boolean willAccomadate(SlotType type) {
        return this.size >= type.size;
    }

    public int getSize() {
        return this.size;
    }

}
