# 🔀 Dynamic Multi-Tenant Database Switching

This project demonstrates **how to dynamically switch between multiple databases** at runtime using Spring Boot, JPA, and a custom `AbstractRoutingDataSource`.

---

## 📌 Use Case

A `MainMember` belongs to a `Team`, and each team has its own separate database. When a request comes in with a member code:

1. The app looks up the member from the **main database**.
2. It extracts DB credentials from the member's `Team`.
3. It dynamically **registers and switches** to the appropriate **branch database**.
4. Then it queries data (like `GameScore`) from that branch DB.
5. Finally, it **clears the context** after the request is processed.

---

## 🧩 Project Structure

<pre>
com.example.dynamicdb
├── main                       # Main DB domain & repository
│   ├── domain                 # MainMember, Team entities
│   ├── repository             # MainMemberRepository, TeamRepository
│   └── service                # MainMemberService
├── branch                     # Branch DB domain & repository
│   ├── domain                 # BranchMember, GameScore entities
│   ├── repository             # BranchMemberRepository, GameScoreRepository
│   ├── service                # BranchMemberService, GameScoreService
│   └── controller             # GameScoreController
├── config
│   └── db                     # Routing datasource, context holder, config
│       ├── DatabaseContextHolder.java
│       ├── DynamicRoutingDataSource.java
│       ├── MainDbConfig.java
│       ├── BranchDbConfig.java  
│       └── BranchDatabaseService.java
└── DynamicDbApplication.java  # Main application class
</pre>

---

## ⚙️ Technologies Used

- **Spring Boot 3+**
- **Spring Data JPA (Hibernate)**
- **MySQL 8**
- **Docker & Docker Compose**

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/dynamic-db-routing.git
cd dynamic-db
```

### 2. Build the JAR

Make sure you're using JDK 17.

```bash
./gradlew clean build
```

> This creates the JAR at `build/libs/dynamic-db-0.0.1-SNAPSHOT.jar`.

---

## 🐳 Run with Docker Compose

### 3. Start MySQL containers and Spring Boot app

```bash
docker-compose up --build
```

This spins up:

- ✅ `main-db` (port: 13305)
- ✅ `real-madrid-db` (port: 13306)
- ✅ `manchester-city-db` (port: 13307)
- ✅ `dynamic-db-app` (Spring Boot API on port: 8080)

Each database is initialized using the SQL files in the `/init` directory.

