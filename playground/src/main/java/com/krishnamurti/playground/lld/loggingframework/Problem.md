## 🧪 Problem Statement: Logging Framework (Bank-style Machine Coding)

You are building a **logging framework** for an internal banking system.

The framework will be used across multiple services like payments, fraud detection, and account management. It must be **extensible, configurable, and safe for production use**.

---

### 🔹 Functional Requirements

1. The system should support multiple **log levels**:
    - DEBUG, INFO, WARN, ERROR, FATAL
2. Users should be able to:
    - Log a message with a specific level
    - Example:

        ```
        logger.info("Payment processed successfully");
        logger.error("Transaction failed");
        ```

3. The framework should support multiple **output destinations (appenders)**:
    - Console
    - File
    - (Extensible for DB / remote logging later)
4. Each appender should have:
    - Its own **log level threshold**
    - Example:
        - Console → INFO and above
        - File → DEBUG and above
5. Log format should be configurable:
    - Example format:

        ```
        [TIMESTAMP] [LEVEL] [CLASS_NAME] - MESSAGE
        ```

6. The system should support **multiple loggers**:
    - Each logger is associated with a class/module name

---

### 🔹 Non-Functional Requirements (Bank-specific emphasis)

1. **Thread-safe** logging (multi-threaded services)
2. **No log loss** for ERROR/FATAL levels
3. **Extensible design** (new appenders, formats)
4. **Performance-sensitive**:
    - Logging should not block main business flow excessively
5. **Basic configurability**:
    - Ability to set log level globally or per logger

---

### 🔹 Constraints

- Do NOT use existing frameworks (Log4j, SLF4J, etc.)
- In-memory + file-based implementation is enough
- No need for distributed systems (keep it single JVM)

---

### 🔹 Example Usage

```
Loggerlogger=LoggerFactory.getLogger("PaymentService");

logger.info("Payment initiated");
logger.error("Payment failed due to insufficient balance");
```