# REST Assured API Automation Framework 🚀

A comprehensive, robust, and fully automated API testing framework built to validate the [Automation Exercise APIs](https://automationexercise.com/api). This project demonstrates industry-standard QA practices, including End-to-End (E2E) workflows, negative testing, and security checks.

## 🛠️ Tech Stack & Tools
* **Programming Language:** Java (JDK 8+)
* **API Testing Tool:** REST Assured
* **Testing Framework:** TestNG
* **Build Management:** Maven
* **Reporting:** ExtentReports (Interactive HTML Dashboard)
* **IDE:** Eclipse / VS Code

## ✨ Key Features
This framework is designed to handle 22 distinct test cases distributed across various modules:

1. **Products & Brands Validation:** GET, POST, and PUT requests validating happy paths and 405 Method Not Allowed scenarios.
2. **Search Functionality:** Parameterized search tests including edge cases (missing parameters resulting in 400 Bad Request).
3. **Authentication:** Secure login validation handling valid credentials, missing emails, and invalid body formats.
4. **End-to-End (E2E) Account Lifecycle:** A chained regression flow that dynamically:
   * Creates an account (201 Created)
   * Verifies login (200 OK)
   * Fetches user details to confirm data persistence
   * Updates the account
   * Deletes the account (Cleanup)
5. **Security Testing (Out-of-the-Box QA):**
   * Response Header Inspection (X-Content-Type-Options, X-Frame-Options)
   * Sensitive Data Exposure Checks
   * Input Validation & Script Injection prevention

## 📂 Project Structure
```text
ApiAutomationTesting
├── src/main/java
│   └── com.automationexercise.utils
│       ├── BaseTest.java             # Core setup and teardown configurations
│       └── ConfigReader.java         # Utility to read environment variables
├── src/test/java
│   ├── com.automationexercise.tests.account
│   │   └── AccountManagementTests.java
│   ├── com.automationexercise.tests.authentication
│   │   └── AuthenticationTests.java
│   ├── com.automationexercise.tests.edgecases
│   │   └── EdgeCasesTest.java
│   ├── com.automationexercise.tests.products
│   │   └── ProductsTests.java
│   ├── com.automationexercise.tests.regression
│   │   └── EndToEndAccountFlowTest.java
│   ├── com.automationexercise.tests.security
│   │   └── SecurityTest.java
│   └── com.automationexercise.utils
│       └── ExtentReportListener.java # Custom listener for ExtentReports generation
└── pom.xml                           # Maven dependencies and build configuration
