# Requirements

## Functional Requirements

* Supported parking slot types 
  * two wheelers
  * cars
  * Trucks

### Entry flow
1. Vehicle arrives at the gate
2. Assign slot as per vehicle type
3. Generate ticket
4. Mark as occupied

### Exit flow
1. Check ticket validity
2. Check and calculate the pricing as per the pricing policy
3. Process Payment
4. Release the occupied spot
5. Success

### Admin flow
1. Add/update/delete Parking floor
2. Add/update/delete Parking slot and types
3. Override parking lot status


## Non functional requirements
1. No slot type would be assigned multiple times
2. Payment pricing and calculation will be extensible