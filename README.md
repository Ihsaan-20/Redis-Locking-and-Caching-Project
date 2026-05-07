# Redis Flash Sale & Caching System

A high-performance Spring Boot application demonstrating how to handle massive traffic and optimize database hits.

### Key Features:
*   **Distributed Locking:** Used **Redisson** to prevent race conditions during a flash sale (handling 1000+ concurrent requests).
*   **Smart Caching:** Implemented Cache-Aside pattern to reduce MySQL load.
*   **Real-time Logs:** Custom logging to track Redis **HIT** and **MISS** scenarios.
*   **Data Consistency:** Handled stale data using `@CacheEvict` and manual cache invalidation.

### Tech Stack:
*   Java 17 / Spring Boot 3
*   Redis (Data Structures & Pub/Sub)
*   MySQL (Persistence)
*   Redisson (Distributed Objects)
*   Maven
