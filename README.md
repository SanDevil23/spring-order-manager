# Order Management Software
## Order Manager




# 🔐 Setting Up Global Constants (e.g., `CLIENT_ID` and `CLIENT_SECRET`)

This guide explains how to store sensitive values such as `CLIENT_ID` and `CLIENT_SECRET` as **environment variables** instead of hardcoding them into your Java application. This is a best practice for security and maintainability.

---

## ✅ Why Use Environment Variables?

- 🔒 Keeps sensitive data out of your source code
- 🚀 Makes your application easier to configure across environments (dev/stage/prod)
- 🛠️ Works well with CI/CD pipelines and containerization tools like Docker

---

## 1. 🖥️ Set Environment Variables on Your System

### 🪟 Windows (Command Prompt)

```cmd
setx CLIENT_ID "your-client-id"
setx CLIENT_SECRET "your-client-secret"

