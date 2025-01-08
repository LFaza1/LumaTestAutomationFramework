# Luma Test Automation Framework

This repository contains a test automation framework built with Java, Maven, TestNG, and Selenium, designed for automated testing of the [Luma web application](https://magento.softwaretestingboard.com/). This is an application specifically built for Web Automation. The framework leverages Apache POI for reading and writing data to and from Excel files and uses the Maven Surefire plugin to manage test execution.

## Features

- **Java & Maven**: Written in Java and structured as a Maven project for easy dependency management.
- **TestNG**: Used for organizing, running, and reporting test cases.
- **Selenium WebDriver**: Enables browser-based automation for testing web application functionalities.
- **Apache POI**: Reads and writes data from/to Excel files for data-driven testing.
- **Surefire Plugin**: Manages test execution and reporting.

## Prerequisites
- Java Development Kit (JDK) 8 or later.
- Maven (ensure it’s added to your PATH).
- Browser WebDriver (e.g., ChromeDriver for Chrome).

## Setup

1. **Clone the Project**

   ```bash
   git clone https://github.com/LFaza1/LumaTestAutomationFramework.git
   ```
2. **Install Dependencies**
   
    Navigate to the project directory and run the following Maven command to install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```
## Running Tests

To execute tests using the Surefire plugin, you can run the following Maven command:

```bash
mvn clean test
```
This will trigger the execution of all the tests and generate a test report in the ```/target/surefire-reports``` directory.
## Reports

After running tests, the Surefire plugin generates an emailable HTML report located at: ```target/surefire-reports/emailable-report.html```

This report contains detailed information about the test results, including success, failure, and skipped tests.
