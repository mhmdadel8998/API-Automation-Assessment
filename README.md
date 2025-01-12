# API Automation Assessment

This document provides the steps to set up and run the API automation tests, along with the tools and technologies used.

---

## **Tools and Technologies**

- **Programming Language**: Java
- **Automation Framework**: Rest Assured
- **Test Runner**: TestNG
- **IDE**: IntelliJ IDEA 
- **Build Tool**: Maven
- **Reporting**: Extent Reports / Allure
- **Data Handling**: JSON or Excel (as test data sources)

---

## **Setup Instructions**

### **1. Prerequisites**

Ensure the following are installed on your machine:

- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: Ensure it is added to the system path
- **IDE**: IntelliJ IDEA or Eclipse
- **Git**: For cloning the repository
- **Browser**: Chrome, Firefox, or any preferred

### **2. Clone the Repository**

Clone the project repository from GitHub:
```bash
$ git clone https://github.com/mhmdadel8998/API-Automation-Assessment.git
```

### **3. Import the Project**

1. Open your IDE (e.g., IntelliJ IDEA or Eclipse).
2. Import the cloned project as a Maven project.
3. Wait for the IDE to download dependencies from the `pom.xml` file.

---

## **Running the Tests**

### **1. Configure Test Data**

- **JSON-based Test Data**: Ensure `data.json` or equivalent file is updated with valid test data.

### **2. Run the Tests**

#### Using IDE:
1. Navigate to the `test` package.
2. Right-click on the TestNG test class or suite file (e.g., `APIExecution.xml`).
3. Select **Run As > TestNG Suite**.

#### Using Maven:
Execute the following command in the terminal:
```bash
$ mvn clean test
```

---

## **Test Scenarios**

### **Scenario 1**: Verify Response Not Empty and Token Generation
- API: `POST`
  `https://restful-booker.herokuapp.com/auth`
- Validates that the response is not empty and a token is generated.

### **Scenario 2**: Verify Book Creation
- API: `POST`
  `https://restful-booker.herokuapp.com/booking`
- Validates successful book creation and matches actual data in the response.

### **Scenario 3**: Verify List of Books
- API: `GET`
  `https://restful-booker.herokuapp.com/booking`
- Asserts that the response contains a list with more than zero entries.

---



