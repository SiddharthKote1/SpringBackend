<!-- 🌾 Project Title -->
<h1 align="center">🌾 AgroNear – Backend Service</h1>

<p align="center">
  <img src="https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExcGR3YmNkenhubnllcXh2eGF1dTNpNXJvd2ZqeTNtY3o0NjA5YmN2cSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/rIjzk2AJNbQ3rYFp45/giphy.gif" />
</p>

<!-- 🎥 Hero Backend Animation -->
<img width="100%" src="https://media.tenor.com/2uyENRmiUt0AAAAC/backend-development.gif" />

## 🚨 Problem Statement

A reliable and secure backend system is required to manage **users, products, and business logic** while ensuring:

- 🔐 Secure authentication
- 👥 Role-based access control
- 🗄️ Data integrity for a farmer–buyer marketplace

AgroNear Backend solves this using **Spring Security + JWT** and a clean backend architecture.

---

## ✨ Key Features

- 🔐 User registration and login
- 🔑 **JWT-based authentication & authorization**
- 👨‍🌾👩‍💼 Role-based access (**Farmer / Buyer**)
- 📦 Product upload & retrieval APIs
- ❤️ Wishlist management APIs
- 🧩 Clean layered architecture


---

## ⚙️ How It Works

1️⃣ User registers or logs in  
2️⃣ Backend generates a **JWT token**  
3️⃣ Token is sent with every protected request  
4️⃣ Spring Security validates the token  
5️⃣ Role-based rules are enforced  
6️⃣ Data is stored securely in **MySQL**

---

## 🛠️ Tech Stack 🧩

**Backend**
- Kotlin
- Spring Boot

**Security**
- Spring Security
- JWT Authentication

**Database**
- MySQL
- JPA / Hibernate

**Tools**
- Postman
- Maven / Gradle

---

<img src="https://user-images.githubusercontent.com/74038190/216656981-1e7b8f47-1b71-4f68-8c1b-4b7b1a4e5d2f.gif" width="100%" />

## 🔐 Authentication Flow

- 🟢 Public APIs:
  - User Registration
  - User Login

- 🔴 Protected APIs:
  - Product Management
  - Wishlist Management

Authorization is enforced using:
- JWT token validation
- Role-based access control

---

## 📂 Project Structure


