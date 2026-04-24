# Entities

## Intersection
- intersectionId
- Map<TrafficLight> trafficLights
- isEmergencyMode
- SignalCycle

## Traffic Light
- trafficLightId
- trafficLightState: State
- Direction
- SignalTiming
- intersectionId

### State
OFF -> RED -> GREEN -> YELLOW -> RED

## Direction Enum
```
 NORTH -> EAST -> SOUTH -> WEST -> NORTH
```

## Signal Timing
- signalTimingId
- intersectionId
- Green time
- yellow time
- trafficLightId


## Signal Cycle
- running
- currentPhase: Direction
- isPaused
- pausedDirection
- pausedTime


## Emergency Request
- Direction
- duration
- isActive
- intersectionId

### TrafficLightState

1. TrafficLightOffState
2. TrafficLightGreenState
3. TrafficLightYellowState
4. TrafficLightRedState
