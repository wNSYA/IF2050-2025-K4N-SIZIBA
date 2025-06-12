# SIZIBA - Sistem Gizi Anak dan Balita (Child and Toddler Nutrition System)

## 1. Overview

**SIZIBA** is a desktop application developed with JavaFX designed to help parents or guardians monitor the health, growth, and nutritional intake of their children. The application provides features for tracking a child's physical development, recording their medical history (illnesses), and receiving meal recommendations based on a specified budget.

This application uses a local SQLite database to store all user and child data, ensuring that the information is persistent and private to the user's machine.

---

## 2. Features

* **User Authentication:** Secure registration and login for users.
* **Child Profile Management:** Add, view, and delete child profiles. Each profile includes key metrics like height, weight, and various circumference measurements.
* **Illness History Tracking:** Record and view a history of illnesses for each child, including the name of the illness, date, and a description.
* **Meal Recommendation System:** Get food recommendations for breakfast, lunch, or dinner based on a specified budget.
* **Persistent Local Storage:** All data is saved locally in an SQLite database file.

---

## 3. Technology Stack

* **Platform:** Java 21+
* **Framework:** JavaFX 17
* **Build Tool:** Apache Maven
* **Database:** SQLite
* **Testing:** JUnit 5, Mockito

---

## 4. Prerequisites

Before you begin, ensure you have the following software installed on your system:

* **Java Development Kit (JDK):** Version 21 or newer.
* **Apache Maven:** Version 3.8.x or newer.
* **An IDE (Optional but Recommended):** IntelliJ IDEA or Eclipse.

---

## 5. How to Run the Application

You can run the application either from the command line using Maven or directly from your IDE.

### From the Command Line (Using Maven)

1.  **Clone the repository:**
    ```sh
    git clone <your-repository-url>
    cd IF2050-2025-K4N-SIZIBA
    ```

2.  **Compile and Run the Application:**
    Use the JavaFX Maven plugin to run the application. This command will compile the code and launch the main window.
    ```sh
    mvn clean javafx:run
    ```

### From IntelliJ IDEA

1.  **Open the Project:**
    * Open IntelliJ IDEA.
    * Select **File > Open...** and navigate to the directory where you cloned the project.
    * Select the `pom.xml` file and open it as a project. IntelliJ will automatically detect it as a Maven project and import the dependencies.

2.  **Reload Maven Dependencies:**
    * After opening, allow IntelliJ a moment to index files and download dependencies.
    * Open the **Maven** tool window (View > Tool Windows > Maven) and click the **Reload All Maven Projects** button to ensure everything is synchronized.

3.  **Run the Application:**
    * Navigate to the `App.java` file located in `src/main/java/com/example/if20502025k4nsiziba/`.
    * Right-click on the file and select **Run 'App.main()'**.

---

## 6. How to Run Tests

The project includes a suite of JUnit tests to verify the functionality of the models and Data Access Objects (DAOs).

To run all the tests from the command line, execute the following Maven command from the project's root directory:

```sh
mvn clean test
```

Maven will compile the test sources, run the tests, and provide a summary report in the console indicating how many tests were run, passed, or failed.

---

## 7. Database Information

* The application uses **SQLite** as its database.
* The database file, named `child_health.db`, is automatically created in a `data/` directory within your project's root folder the first time you run the application.
* All necessary tables (`users`, `child`, `child_illness`, `food`) are also created automatically.
