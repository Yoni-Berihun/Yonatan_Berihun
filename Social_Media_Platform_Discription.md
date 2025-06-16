# Social Media Platform

## Classes and Their Responsibilities

### 1. **User Class**
Handles user-related operations such as registration, login, updating user profiles, and following other users. Stores user credentials securely and monitors relationships between users.

### 2. **Post Class**
Handles the creation, editing, and storage of user-generated content such as text, images, and videos. Monitors interactions such as shares and ensures data consistency.

### 3. **Comment Class
Manages threaded conversations by allowing users to reply to posts or comments. It enforces nesting depth limits to make reading easier.

### 4. **Notification Class**
Delivers real-time notifications to users for events like new followers, likes, or comments. It also supports rate-limiting and prioritization for optimal notification delivery.

### 5. **Feed Class
Generates personalized content feeds for users, based on their interests and activity. It ranks and filters content using algorithms for relevance.

### 6. **PrivacySettings Class**
Handles user privacy, e.g., checking block status and setting limits on user interactions.
# OOP Principles Implementation

## 1. Encapsulation  
- Strict access control using private fields with public getters/setters  
- Validation logic embedded within entity classes (e.g., password strength checks)  
- Internal state protection through immutable collections  

## 2. Inheritance  
- Hierarchical notification system (Push/Email/SMS notifications extending base class)  
- Abstract repository pattern for database operations  

## 3. Polymorphism  
- Interface-based content moderation system  
- Strategy pattern for dynamic feed sorting  
- Template method pattern in notification delivery  

## 4. Relations
- **Composition**: User owns Feed lifecycle  
- **Aggregation**: Posts exist independently of users  
- **Association**: Bidirectional follower relationships!
