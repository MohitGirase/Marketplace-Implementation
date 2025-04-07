
# 🪙 PQoin Marketplace Simulation

## 📌 Overview

This project simulates a marketplace for an in-game cryptocurrency called **PQoin**, allowing traders to place buying and selling orders. The platform uses **priority queues** to match orders and perform transactions efficiently, reflecting real-world market behavior.

---

## 🚀 Features

- Traders with wallets holding **USD and PQoin**
- **Buy and sell orders** handled using priority queues
- **Automatic order matching** based on price
- **Wallet management** with blocked funds for pending orders
- **Transaction fees** handled by the marketplace
- **Detailed transaction recording**
- **Simulation of open market operations**
- **Handling of invalid or insufficient fund orders**

---

## 🧱 System Design

### 🔄 Core Components

- **Trader**: Places orders and maintains a wallet  
- **Wallet**: Manages funds (USD and PQoin), blocking amounts for pending orders  
- **Market**: Matches buy/sell orders, executes transactions, and collects fees  
- **Order**:  
  - `BuyingOrder`  
  - `SellingOrder`

---

### 🧠 OOP Concepts

- **Encapsulation**: Each class manages its internal state  
- **Polymorphism**: Custom comparison logic in buy/sell orders via inheritance  
- **Abstraction**: High-level methods like `giveBuyOrder()` simplify user interaction  
- **Modularity**: Classes are cleanly separated and independently maintainable

---

### 🧮 Data Structures

**Priority Queues**:  
- Buy Orders → **Max-Heap** (highest price first)  
- Sell Orders → **Min-Heap** (lowest price first)

---

## 📊 Market Simulation

- Simulates **real-time trading behavior**
- Handles **partial and full matches** of orders
- Records all transactions with **accurate wallet updates**
- Tracks and reports **invalid queries**

---

## ⚠️ Challenges Tackled

- **Order Matching Optimization**: Priority queues ensure efficient, constant-time access to best prices  
- **Concurrency (Future Enhancement)**: Assumed single-threaded execution for now; can be upgraded for multithreading

---

## 📈 Potential Enhancements

- Add **thread safety** for concurrent trader actions  
- Integrate **real-time UI or API** for interaction  
- Introduce **advanced order types** (e.g., stop-loss, limit orders)  
- Enable **persistent storage** and **user authentication**

---

## 🧩 Real-World Application

This simulation reflects foundational concepts of **financial trading platforms** and **crypto exchanges**, giving valuable insights into **system design** and **market behavior**.

---

## 🧠 Author Notes

This project demonstrates a solid grasp of **object-oriented programming**, **data structures**, and **system design**.  
It can be a great talking point in interviews, especially for roles involving **backend systems** or **financial applications**.
