## 🚦 Traffic Light System — Machine Coding Problem

### 📌 Problem Statement

Design and implement a **Traffic Light Control System** for a road intersection.

The system should simulate traffic signal operations for multiple roads and ensure safe and orderly movement of vehicles.

---

### 📌 Functional Requirements

1. The intersection consists of multiple **roads** (assume 4 by default: North, South, East, West).
2. Each road has a **traffic signal** with three possible states:
    - RED
    - YELLOW
    - GREEN
3. The traffic signals must follow this transition order:

    ```
    GREEN → YELLOW → RED
    ```

4. At any given time:
    - Only **one direction** can have GREEN.
    - All other directions must be RED.
5. Each signal has configurable durations:
    - Green duration
    - Yellow duration
6. The system should:
    - Automatically cycle through all directions
    - Continuously run as a simulation

---

### 📌 Additional Features

1. Provide a way to:
    - Add or remove roads dynamically [ NOT REQUIRED ]
    - Configure signal timings per road
2. Support an **emergency override**:
    - A given road can be turned GREEN immediately (e.g., ambulance)
    - Other roads must turn RED
3. The system should be able to:
    - Start and stop the simulation
    - Print the current state of all signals at each step

---

### 📌 Constraints

- The system will run on a **single machine**
- Focus on clean design and extensibility
- Concurrency handling is optional but preferred if used

---

### 📌 Expected Deliverables

1. Class design (UML or code structure)
2. Core implementation
3. Simulation/driver code
4. Handling of edge cases

---

### 📌 Sample Output (for understanding)

```
Time 0:
North: GREEN
South: RED
East: RED
West: RED

Time 30:
North: RED
South: GREEN
East: RED
West: RED

Time 35:
North: RED
South: YELLOW
East: RED
West: RED
```