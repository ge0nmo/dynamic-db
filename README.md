# 🔀 Dynamic Multi-Tenant Database Switching in Spring Boot

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
│       └── BranchDatabaseService.java
└── DynamicDbApplication.java  # Main application class
</pre>
