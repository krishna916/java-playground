# Parking lot entities

- format is entity followed by their attributes


* Vehicle
  * vehicleId - can be long / UUID (we will use UUID for simplicity)
  * licensePlate - String

* SlotType
  * Enum
    * BIKE
    * CAR
    * TRUCK

* ParkingSlot
  * slotId - UUID
  * type - SlotType
  * isOccupied - boolean
  
* ParkingFloor
  * floorId - UUID
  * slots - List of ParkingSlots

* Ticket
  * ticketId - UUID
  * generatedTime - time / long
  * vehicleId - UUID
  * slotId - UUID

Status - ENUM (PENDING, SUCCESS, FAILED)

* Receipt
  * receiptId - UUID
  * ticketId - UUID
  * exitTime - time / long
  * fee - double
  * Payment - payment
  
* PricingRule
  * ruleId - UUID
  * SlotType   
  * ratePerHour - double

* Payment
  * ticketId - UUID
  * amount - double
  * status - Status
