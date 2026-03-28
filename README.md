# playwright-saucedemo

UI test automation for [saucedemo.com](https://www.saucedemo.com) using Playwright and TestNG.

## Stack

- Java 21
- [Playwright for Java](https://playwright.dev/java/) 1.52
- TestNG 7.11
- Maven

## Setup

**Prerequisites:** Java 21, Maven 3.x

Install Playwright browsers:

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

## Running tests

```bash
mvn test
```

Tests run in parallel (2 threads) as configured in `testng.xml`.

## Configuration

Edit `src/main/resources/config.properties`:

```properties
browser=chromium   # chromium | firefox | safari | chrome
baseUrl=https://www.saucedemo.com
```
