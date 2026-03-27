# LLD Code Review: Parking Lot System (FAANG/Fintech Interview Style)

### 1. Summary
The `parkinglot` package implements a robust Low-Level Design for a multi-vehicle parking system. It successfully handles the core lifecycle of a vehicle from entry (slot allocation and ticket generation) to exit (pricing calculation, payment, and slot release). The overall quality is **excellent** for a 60-minute interview, demonstrating a strong grasp of clean architecture and design patterns.

### 2. Strengths
*   **Layered Architecture:** Clear separation between Controllers, Services, Repositories, and Domain models.
*   **Effective use of Design Patterns:** Strategy pattern for pricing and payments, and Factory pattern for strategy retrieval.
*   **SOLID Compliance:** High adherence to Single Responsibility and Open/Closed principles.
*   **Type Safety:** Proper use of Enums (`SlotType`, `PricingType`) and `Optional` for null safety.

### 3. Key Issues (CRITICAL)
*   **Problem:** Typo in interface and implementation names: `RecieptService` instead of `ReceiptService`.
    *   **Why it matters in interview:** Attention to detail is crucial in FAANG. Frequent typos can suggest carelessness or lack of focus on professional standards.
    *   **Concrete fix:** Refactor `RecieptService` to `ReceiptService` and `ReceiptServiceImpl` accordingly.
*   **Problem:** Inconsistent Dependency Injection and encapsulation in `ExitController`. Fields are package-private and not `final`.
    *   **Why it matters in interview:** Lack of `final` and `private` modifiers on dependencies suggests a weak understanding of immutability and encapsulation, which are core tenets of robust Java development.
    *   **Concrete fix:** Make fields `private final` and ensure the constructor matches the pattern used in `EntryController`.
*   **Problem:** Logic leakage in `ReceiptServiceImpl` (e.g., `+ 100` dummy addition for duration).
    *   **Why it matters in interview:** While often done for simulation, hardcoding "magic numbers" in business logic is a red flag. It obscures the actual duration calculation logic that an interviewer wants to see.
    *   **Concrete fix:** Calculate the actual `Duration` and handle the rounding logic within the `PricingStrategy`.

### 4. Design Improvements
*   **Strategy Registry:** The `PricingStrategyFactory` could be improved by using a more dynamic registration process or leveraging Spring's `@Service` and `Map<String, Strategy>` injection to avoid manual `put` calls in the constructor.
*   **Concurrency:** In a real-world scenario, `occupyParkingSlot` and `releaseParkingSlot` need to be thread-safe. Adding `synchronized` or using `ReentrantLock` in the repository implementations would be a strong "bonus" addition.

### 5. Code Smells
*   **Static Map in Instance Factory:** `PricingStrategyFactory` uses a `static` map but populates it in an instance constructor. This can lead to unexpected behavior if multiple factories are created.
*   **Violation of DRY:** Some manual duration calculation logic exists in the service that might be better encapsulated in a `DurationCalculator` or the `Ticket` domain itself.

### 6. Interview Feedback
*   **Would this pass FAANG round?** **Yes**. The candidate demonstrated the ability to produce a highly modular, extensible, and clean codebase within a tight timeframe. The use of Strategy and Factory patterns is a significant "plus."
*   **What interviewer will challenge:**
    *   "How would you handle concurrent entry/exit requests at multiple gates?"
    *   "What if the pricing rule changes while the vehicle is parked?"
    *   "How would you implement a 'Nearest to Entry' slot allocation strategy?"

### 7. Scoring (out of 10)

| Category | Score | What interviewer thinks |
|----------|------|------------------------|
| Problem Understanding | 10/10 | Requirements fully met. |
| API Design | 9/10 | Very clean Controller/DTO structure. |
| Class Design | 9/10 | Strong domain modeling. |
| Abstraction Quality | 9/10 | Strategy patterns used effectively. |
| Extensibility | 10/10 | Easy to add vehicle types/pricing rules. |
| Code Quality | 8/10 | Good, but minor typos and consistency issues. |
| Edge Case Handling | 7/10 | Basic checks present; needs concurrency focus. |
| Tradeoff Discussion | 9/10 | Clear separation allows for easy DB swap. |

### 8. Code Improvements (IMPORTANT)

**BEFORE (Inconsistent/Weak Encapsulation):**
```java
@RestController(Constants.PARKING_LOT)
public class ExitController {
    TicketService ticketService;
    ParkingSlotService parkingSlotService;
    RecieptService recieptService;

    public ExitController(TicketService ticketService, ParkingSlotService parkingSlotService, RecieptService recieptService) {
        this.ticketService = ticketService;
        this.parkingSlotService = parkingSlotService;
        this.recieptService = recieptService;
    }
    // ...
}
```

**AFTER (Robust/Standard Encapsulation):**
```java
@RestController(Constants.PARKING_LOT)
public class ExitController {
    private final TicketService ticketService;
    private final ParkingSlotService parkingSlotService;
    private final ReceiptService receiptService; // Fixed typo

    public ExitController(final TicketService ticketService, 
                          final ParkingSlotService parkingSlotService, 
                          final ReceiptService receiptService) {
        this.ticketService = ticketService;
        this.parkingSlotService = parkingSlotService;
        this.receiptService = receiptService;
    }
    // ...
}
```

**BEFORE (Manual map population in constructor):**
```java
public class PricingStrategyFactory {
    private static final Map<PricingRule.PricingType, PricingStrategy> MAP = new HashMap<>();

    public PricingStrategyFactory() {
        MAP.put(PricingRule.PricingType.FLAT_RATE, FlatRatePricingStrategy.getInstance());
        // ...
    }
}
```

**AFTER (Clean Static Initialization):**
```java
public class PricingStrategyFactory {
    private static final Map<PricingRule.PricingType, PricingStrategy> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(PricingRule.PricingType.FLAT_RATE, FlatRatePricingStrategy.getInstance());
        STRATEGIES.put(PricingRule.PricingType.HOURLY, HourlyPricingStrategy.getInstance());
        STRATEGIES.put(PricingRule.PricingType.DAYS, DayPricingStrategy.getInstance());
    }

    public Optional<PricingStrategy> getStrategy(PricingRule.PricingType type) {
        return Optional.ofNullable(STRATEGIES.get(type));
    }
}
```

### 9. Improvement Plan (MOST IMPORTANT)
1.  **Fix Naming:** Perform a global refactor to fix the `Reciept` typo.
2.  **Ensure Immutability:** Update all Controller and Service dependencies to be `private final`.
3.  **Address Concurrency:** Add thread-safety mechanisms (like `ConcurrentHashMap` or `synchronized` blocks) to the in-memory repositories.
4.  **Refine Logic:** Move magic numbers (like the dummy `+100`) into configurable properties or constants with clear names.

### 10. What should the candidate do next?
*   **Refactoring:** Apply the naming and encapsulation fixes identified above.
*   **Practice:** Try implementing a more complex allocation strategy (e.g., "Best Fit" based on distance).
*   **Interview Tip:** Be prepared to explain *why* you chose the Strategy pattern over a simple `switch` statement (hint: it's for Open/Closed principle compliance).
