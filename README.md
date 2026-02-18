# Banking Microservices System

## Architecture

- Eureka Server (Service Discovery)
- API Gateway
- User Service (JWT Authentication)
- Account Service
- Transaction Service
- Frontend (Angular/React)

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- Eureka
- API Gateway
- H2
- Docker (optional)

## How to Run

1. Start Eureka Server
2. Start API Gateway
3. Start User Service
4. Start Account Service
5. Start Transaction Service
6. Start Frontend

## Authentication Flow

User logs in → JWT generated → Token validated in Transaction Service → Authorization based on userId

**##POST MAN urls:**
**1.For User registration : POST http://localhost:8080/api/users/register** 
Body : Select: raw, JSON
BODY::: {
  "userName": "vijay",
  "email": "vijay@gmail.com",
  "password": "test123",
  "phone": "7999999998"
},
Expected Response:: {"id":1,"userName":"vijay"}

**2. POST http://localhost:8080/api/users/login**
Body : Select: raw, JSON
BODY:: {
    "email": "arya@gmail.com",
    "password": "best1234"
}
Expected Response: {
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W"
}
We need to use this token for all subsequent request in the header values as 
Key : Authorization, Value: Bearer: eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W

**3. POST http://localhost:8080/api/accounts?userId=1**
   Headers
   Key : Authorization, Value: Bearer: eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W
   Body: not needed
   Expected Response: Account number will be generated as below:: {
    "id": 8,
    "userId": 1,
    "accountNumber": "793840683",
    "balance": 0
}

**4. POST http://localhost:8080/api/accounts/4/credit?amount=1000**
 Headers
   Key : Authorization, Value: Bearer: eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W
   Body: not needed
   Expected Response: Empty, Status: 200OK

 **5. POST http://localhost:8080/api/accounts/1/debit?amount=200**
  Headers
   Key : Authorization, Value: Bearer: eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W
   Body: not needed
   Expected Response: Empty, Status: 200OK

**6. POST http://localhost:8080/api/transactions/transfer?fromId=4&toId=3&amount=100**
   Headers
   Key : Authorization, Value: Bearer: eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W
   Body: not needed
   Expected Response: Transfer Successful, Status: 200OK

   **7. GET http://localhost:8080/api/transactions/history/3**
   Headers
   Key : Authorization, Value: Bearer: eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzcxNDIxOTI2LCJleHAiOjE3NzE0MjU1MjZ9.z0t3J2nTMSUy_K7PBMMCeFcBmHeCI8J-86JNwH9TouSo3YcKfgo3Q94lCcsnul0W
   Body: not needed
   Expected Response: 
   [
    {
        "id": 1,
        "fromAccountId": 4,
        "toAccountId": 3,
        "amount": 100.00,
        "transactionType": "TRANSFER",
        "timestamp": "2026-02-18T19:15:06.122487",
        "createdAt": "2026-02-18T19:15:06.122487"
    },
    {
        "id": 2,
        "fromAccountId": 4,
        "toAccountId": 3,
        "amount": 100.00,
        "transactionType": "TRANSFER",
        "timestamp": "2026-02-18T19:18:00.744176",
        "createdAt": "2026-02-18T19:18:00.744176"
    },
    {
        "id": 3,
        "fromAccountId": 4,
        "toAccountId": 3,
        "amount": 100.00,
        "transactionType": "TRANSFER",
        "timestamp": "2026-02-18T19:18:11.832596",
        "createdAt": "2026-02-18T19:18:11.832596"
    }
]
