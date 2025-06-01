### 📘 KAFKA_SETUP.md

# Kafka Setup for Expense Tracker Application

This guide explains how Kafka was installed and configured for the **Expense Tracker** project on a Windows system, along with key learnings and troubleshooting steps.

---

## ✅ Technologies Used

- **Spring Boot** `v3.5.0`
- **Kafka** `v3.x` (local installation on Windows)
- **Apache Zookeeper**
- **Spring Kafka** `3.1.1`
- \*\*Java 17+\`

---

## 📦 Kafka Dependencies (in `pom.xml`)

```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

---

## 💠 Kafka Installation on Windows

1. **Download Kafka**:

   - Go to: [https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
   - Download the binary for Windows (`.tgz` file).

2. **Extract to a Folder**:

   - Example: `D:\kafka`

3. **Start Zookeeper**:

   ```bash
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   ```

4. **Start Kafka Server**:

   ```bash
   .\bin\windows\kafka-server-start.bat .\config\server.properties
   ```

5. **Create a Topic**:

   ```bash
   .\bin\windows\kafka-topics.bat --create --topic userEvent --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
   ```

## 🐛 Common Issues & Fixes

### 1. **Kafka `LEADER_NOT_AVAILABLE` Warning**

- **Cause**: Kafka topic was created, but leader was not yet assigned.
- **Solution**: Wait 3–5 seconds after creating the topic or restart Kafka.

## 🧠 What I Learned

- Installing and configuring Kafka on Windows
- Creating Kafka topics via terminal
- Using KafkaProducer in Spring Boot
- Handling Java Time serialization with Jackson
- Resolving dependency and version conflicts
- Writing clean builder-based Kafka event objects

---

## 📌 Sample Kafka Event Object

```java
@Builder
public class UserEvent {
    private String userId;
    private String eventType;
    private Instant timestamp;
}


```
