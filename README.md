# reward-calculator

# Reward Calculation API
 
This Spring Boot application calculates reward points for customers based on their transaction history. Reward points are computed according to a tiered structure, and results are grouped by customer and by month (limited to the past 3 months).

# Project Structure

reward-points-calculator
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.rewards
│   │   │       ├── controller
│   │   │       │   └── RewardController.java
│   │   │       ├── exception
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── NoTransactionFoundException.java
│   │   │       ├── model
│   │   │       │   ├── Customer_Transaction.java
│   │   │       │   └── RewardResponse.java
│   │   │       ├── repository
│   │   │       │   └── TransactionRepository.java
│   │   │       ├── service
│   │   │       │   └── RewardService.java
│   │   │       └── RewardsPointsApiApplication.java
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       ├── application.properties
│   │       ├── data.sql
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com.rewards
│               ├── controller
│               │   └── RewardControllerTest.java
│               ├── service
│               │   └── RewardServiceTest.java
│               └── RewardsPointsApiApplicationTests.java

 
## Features
 
- Accepts a list of customer transactions.
- Filters transactions from the past 3 months.
- Calculates reward points using a tiered formula:
  - 2 points for every dollar spent over $100
  - 1 point for every dollar spent over $50 up to $100
- Aggregates monthly and total reward points per customer.
- Input validation and global exception handling.
- Includes unit and integration tests.
- Dummy data setup for quick testing.
 
## Technologies Used
 
- Java 21
- Spring Boot
- Maven
- JUnit 5 & Mockito
 
---

## API Endpoint
 
### `POST /api/rewards/calculate`

POST API to calculate reward points for provided transaction data.

---
 
## How to Run
 
### Prerequisites
 
- Java 21
- Maven 3.6+
 
### Steps
 
1. Clone the repository:
 
```bash
git clone https://github.com/your-repo/reward-points-api.git
cd reward-points-api
```
 
2. Build and run:
 
```bash
mvn clean install
mvn spring-boot:run
```
 
3. Test the API with Postman:
 
- **POST** `http://localhost:8080/api/rewards/calculate`

- **GET** `http://localhost:8080/api/rewards`
---
 
## Testing
 
### Unit Tests
 
```bash
mvn test
```
