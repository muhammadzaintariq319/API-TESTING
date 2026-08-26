# 🧪 Automation Exercise — API Testing Project

A complete, real-world API testing project built with **Postman** and automated using **Newman**, targeting the publicly documented REST API of [automationexercise.com](https://automationexercise.com/api_list).

![Status](https://img.shields.io/badge/tests-83%20assertions%20passed-brightgreen)
![Requests](https://img.shields.io/badge/requests-26-blue)
![Pass Rate](https://img.shields.io/badge/pass%20rate-100%25-success)

---

## 📌 About This Project

This project is a hands-on demonstration of end-to-end API testing — from analyzing endpoints, to designing test cases, to full automation and reporting. It targets automationexercise.com's officially documented REST API, covering products, brands, search, authentication, and account management.

---

## ✅ Latest Test Run Results

| Metric | Result |
|---|---|
| Total Requests | 26 |
| Total Assertions | 83 |
| Failed Tests | 0 |
| Skipped Tests | 0 |
| Pass Rate | **100%** |
| Avg. Response Time | 376ms |
| Total Run Duration | 12s |

*(Generated via Newman with the `htmlextra` reporter — see [Running via Newman](#-running-via-newman) below.)*

---

## 📂 Project Structure

```
automation-exercise-api-testing/
│
├── AUTOMATION EXERCISE API TESTING.postman_collection.json   ← import into Postman
├── AutomationExercise.postman_environment.json                ← import into Postman
├── test-data-login.csv                                         ← data-driven test inputs
├── report.html                                                 ← Newman HTML test report
└── README.md
```

---

## 🔌 API Endpoints Under Test

All endpoints below are taken directly from the official docs (`automationexercise.com/api_list`).

| Endpoint | Method | Purpose | Expected Code |
|---|---|---|---|
| `/productsList` | GET | Get all products | 200 |
| `/productsList` | POST | Unsupported method | 405 |
| `/brandsList` | GET | Get all brands | 200 |
| `/brandsList` | PUT | Unsupported method | 405 |
| `/searchProduct` | POST | Search products | 200 / 400 |
| `/verifyLogin` | POST | Login verification | 200 / 400 / 404 |
| `/verifyLogin` | DELETE | Unsupported method | 405 |
| `/createAccount` | POST | Register user | 201 |
| `/deleteAccount` | DELETE | Delete user | 200 |
| `/updateAccount` | PUT | Update user | 200 |
| `/getUserDetailByEmail` | GET | Fetch user by email | 200 |

---

## 🗂️ Test Suite Coverage

| Folder | Focus |
|---|---|
| **01 - Products** | Product/brand retrieval, search, unsupported-method negatives |
| **02 - Authentication** | Valid login, missing params, invalid credentials, unsupported methods |
| **03 - Account Management** | Full CRUD lifecycle on a user account |
| **04 - Negative & Edge Case Testing** | Malformed JSON, missing fields, nonexistent records |
| **05 - Security Testing** | Header inspection, sensitive data exposure, basic input validation |
| **06 - Regression - Chained Flow** | End-to-end user journey using variable chaining across 5 requests |

---

## ⚙️ Tech / Concepts Demonstrated

- REST API testing fundamentals (GET / POST / PUT / DELETE)
- Postman test scripting (`pm.test`, `pm.expect`, JSON schema/field validation)
- Pre-request scripts for dynamic test data generation
- Environment variables and request chaining
- Positive, negative, boundary, and security testing
- Data-driven testing via CSV + Collection Runner
- CLI automation and HTML reporting via Newman

---

## 🚀 Running via Newman

```bash
npm install -g newman
npm install -g newman-reporter-htmlextra

newman run "AUTOMATION EXERCISE API TESTING.postman_collection.json" \
  -e AutomationExercise.postman_environment.json \
  --reporters cli,htmlextra \
  --reporter-htmlextra-export report.html
```

Open `report.html` in a browser to view the full interactive test dashboard.

---

## 🧭 Known Limitations

- The API does not expose a cart/checkout endpoint — cart-related flows are not covered.
- Authentication is stateless credential verification only — no JWT/Bearer token is issued by this API.

---

## 👤 Author

Built as a hands-on QA/API testing learning project — from endpoint analysis through automated CI-style reporting.
