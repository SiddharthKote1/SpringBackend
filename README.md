# 🌾 AgroNear – Backend Service

<p align="center">
  <img src="https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExcGR3YmNkenhubnllcXh2eGF1dTNpNXJvd2ZqeTNtY3o0NjA5YmN2cSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/rIjzk2AJNbQ3rYFp45/giphy.gif" width="60%" />
</p>

<img width="100%" src="https://media.tenor.com/2uyENRmiUt0AAAAC/backend-development.gif" />

---

## 🚨 Problem Statement

Modern farmer–buyer platforms require a secure, scalable, and role-based backend system to manage users, products, and business logic while ensuring data integrity and controlled access.

AgroNear Backend solves this using Spring Security with JWT authentication to provide a reliable and maintainable backend architecture.

---

## ✨ Key Features

- Secure user registration and login
- JWT-based authentication and authorization
- Role-based access control (Farmer / Buyer)
- Product upload and retrieval APIs
- Wishlist management APIs
- Clean layered backend architecture

---

## ⚙️ How It Works

1. User registers or logs in  
2. Backend generates a JWT token  
3. Token is sent with every protected request  
4. Spring Security validates the token  
5. Role-based rules are enforced  
6. Data is stored securely in MySQL  

---

## 🔐 Authentication & Authorization

### Public APIs
- User Registration  
- User Login  

### Protected APIs
- Product Management  
- Wishlist Management  

Authorization is enforced using JWT token validation and role-based access control.

---

## 🛠️ Tech Stack

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

## 🚀 Use Cases

- Farmers can securely add and manage products
- Buyers can browse products and manage wishlists
- Role-based security prevents unauthorized access
- Stateless authentication enables scalable backend services

---

## 📌 Conclusion

AgroNear Backend provides a secure, scalable, and maintainable backend foundation for a farmer–buyer marketplace by following industry best practices in authentication, authorization, and RESTful API design.

