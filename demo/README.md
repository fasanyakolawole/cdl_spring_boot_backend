# Checkout System Backend

**Technical Test by Michael Fasanya**

This is a Spring Boot REST API backend that implements a checkout system with special pricing rules for a retail store.

## Overview

The backend calculates the total price of items with the following pricing structure:

- **Item A**: Unit price 50 pence. Special offer: 3 for 130 pence
- **Item B**: Unit price 30 pence. Special offer: 2 for 45 pence
- **Item C**: Unit price 20 pence (no special offers)
- **Item D**: Unit price 15 pence (no special offers)

The system applies special offers automatically when the quantities match, ensuring the best price for customers.

## Technology Stack

- **Java 21**
- **Spring Boot 3.5.7**
- **Maven** (build tool)
- **Spring Web** (REST API)

## How to Run

### Prerequisites

- Java 21 or higher installed
- Maven installed (or use the included Maven Wrapper)

### Running the Application

1. **Using Maven Wrapper (Recommended):**
   ```bash
   # On Windows
   ./mvnw.cmd spring-boot:run
   
   # On Linux/Mac
   ./mvnw spring-boot:run
   ```

2. **Using Maven (if installed):**
   ```bash
   mvn spring-boot:run
   ```

3. **Running from IDE:**
   - Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
   - Run the `DemoApplication.java` main class

The application will start on **http://localhost:8080** by default.

## API Documentation

### Endpoint

**POST** `/api/checkout/total`

Calculate the total price for a list of items.

#### Request Body

```json
{
  "items": ["A", "B", "C", "D", "A", "A"]
}
```

- `items`: Array of item SKUs (strings)

#### Response

```json
{
  "total": 165
}
```

- `total`: Total price in pence (integer)

#### Example Request

```bash
curl -X POST http://localhost:8080/api/checkout/total \
  -H "Content-Type: application/json" \
  -d '{"items": ["A", "A", "A", "B", "B"]}'
```

#### Example Response

```json
{
  "total": 175
}
```

Explanation:
- 3 × A = 130 pence (special offer)
- 2 × B = 45 pence (special offer)
- Total = 175 pence

## Pricing Rules Examples

| Items | Calculation | Total |
|-------|-------------|-------|
| A, A, A | 3 for 130 | 130 pence |
| A, A, A, A | 3 for 130 + 1 × 50 | 180 pence |
| B, B | 2 for 45 | 45 pence |
| B, B, B | 2 for 45 + 1 × 30 | 75 pence |
| A, B, C, D | 1 × 50 + 1 × 30 + 1 × 20 + 1 × 15 | 115 pence |
| A, A, A, B, B | 3 for 130 + 2 for 45 | 175 pence |

## CORS Configuration

The backend is configured to allow cross-origin requests from `http://localhost:3000` for frontend development.

## Project Structure

```
src/main/java/checkout/demo/
├── controller/
│   └── CheckoutController.java    # REST API endpoint
├── services/
│   └── CheckoutService.java        # Business logic for price calculation
├── dto/
│   ├── CheckoutRequest.java        # Request DTO
│   └── CheckoutResponse.java       # Response DTO
└── DemoApplication.java            # Spring Boot main class
```

## Testing

To run the test suite:

```bash
./mvnw.cmd test
```

---

**Author:** Michael Fasanya  
**Date:** Technical Test Submission
