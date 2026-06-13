# SmartSpend 💰

A full-stack personal finance management application that helps users track expenses, analyze spending habits and manage their finances efficiently.

## 🚀 Live Features

* User Registration & Login
* JWT-based Authentication & Authorization
* Protected Routes
* Add Expenses
* Update Expenses
* Delete Expenses
* Dashboard Analytics
* Category-wise Expense Breakdown
* Monthly Expense Summary
* Expense Charts & Visualizations
* CSV Export for Expense Reports
* Responsive User Interface

---

## 📸 Screenshots 

Screenshots of

i) Login Page

ii) Register Page

iii) Dashboard

iv) Add Expense

v) Expense Chart

vi) Monthly Summary

vii) CSV Export

viii) AWS EC2 Deployment

is in /screenshots.

---

## 🏗️ Architecture

```text
React (Frontend)
        │
        ▼
Spring Boot REST API
        │
        ▼
PostgreSQL Database
```

### Deployment Architecture

```text
Browser
   │
   ▼
Nginx (AWS EC2)
   │
   ▼
Spring Boot Application
   │
   ▼
PostgreSQL
```

---

## 🛠️ Tech Stack

### Frontend

* React
* React Router
* Axios
* Recharts
* CSS

### Backend

* Java 21
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Maven

### Database

* PostgreSQL

### DevOps & Deployment

* AWS EC2
* Amazon Linux
* Nginx
* Systemd
* SSH

---

## 🔐 Authentication Flow

1. User logs in using email and password.
2. Spring Security validates credentials.
3. JWT token is generated and returned.
4. Frontend stores JWT in Local Storage.
5. Protected API requests include the JWT token in the Authorization header.
6. Spring Security validates the token before granting access.

---

## 📊 Dashboard Metrics

The dashboard provides:

* Total Expenses
* Expense Count
* Top Spending Category
* Category-wise Expense Distribution
* Monthly Expense Summary

---

## 📂 REST API Endpoints

### Authentication

| Method | Endpoint       | Description            |
| ------ | -------------- | ---------------------- |
| POST   | /auth/register | Register User          |
| POST   | /auth/login    | Login User             |
| GET    | /auth/me       | Current Logged-in User |

### Expenses

| Method | Endpoint                | Description       |
| ------ | ----------------------- | ----------------- |
| POST   | /expenses               | Add Expense       |
| GET    | /expenses/user/{userId} | Get User Expenses |
| PUT    | /expenses/{expenseId}   | Update Expense    |
| DELETE | /expenses/{expenseId}   | Delete Expense    |

### Analytics

| Method | Endpoint                                | Description        |
| ------ | --------------------------------------- | ------------------ |
| GET    | /{userId}/dashboard                     | Dashboard Summary  |
| GET    | /{userId}/expenses/total                | Total Expenses     |
| GET    | /{userId}/expenses/category/{category}  | Category Total     |
| GET    | /{userId}/expenses/month/{year}/{month} | Monthly Summary    |
| GET    | /{userId}/expenses/category-breakdown   | Category Breakdown |

### Export

| Method | Endpoint             | Description            |
| ------ | -------------------- | ---------------------- |
| GET    | /expenses/export/csv | Export Expenses as CSV |

---

## ⚙️ Local Setup

### Clone Repository

```bash
git clone <repository-url>
cd smartspend-backend
```

### Configure Database

Create a PostgreSQL database:

```sql
CREATE DATABASE smartspend;
```

Update:

```properties
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smartspend
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run Backend

```bash
./mvnw spring-boot:run
```

### Run Frontend

```bash
cd frontend

npm install

npm run dev
```

Frontend:

```text
http://localhost:5173
```

Backend:

```text
http://localhost:8080
```

---

## ☁️ AWS Deployment Experience

This project was successfully deployed on AWS EC2 using:

* Amazon Linux
* PostgreSQL
* Nginx
* Systemd Service
* Spring Boot Executable JAR

Deployment activities included:

* Linux server provisioning
* PostgreSQL installation and configuration
* Nginx setup and configuration
* Spring Boot service management using systemd
* Security Group configuration
* CORS troubleshooting
* Public deployment verification

---

## 🎯 Key Learnings

Through this project I gained hands-on experience with:

* Full Stack Development
* REST API Design
* JWT Authentication
* Spring Security
* PostgreSQL
* React State Management
* AWS EC2 Deployment
* Linux Server Administration
* Nginx Configuration
* Production Debugging

---

## Live Demo

Frontend: https://smart-spend-frontend-eight.vercel.app/

Backend API: https://smart-spend-wwvn.onrender.com

---

## 🔮 Future Enhancements

* Expense Budgeting
* Savings Goals Tracking
* Expense Search & Filtering
* Dark Mode
* Docker Support
* Email Notifications
* Multi-Currency Support
* Mobile Responsive Enhancements

---

## 👨‍💻 Author

**Yazhini S**

Built as a full-stack portfolio project to strengthen expertise in React, Spring Boot, PostgreSQL, AWS and modern web application development.
