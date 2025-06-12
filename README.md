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

- **Java Development Kit (JDK):** Version 21 or newer.
- **Apache Maven:** Version 3.8.x or newer.
- **An IDE (Optional but Recommended):** IntelliJ IDEA or Eclipse.

### 4.1 How to Install Maven

Apache Maven is required to build and run this application. Follow the instructions below based on your operating system.

---

#### **Windows**

1. **Download Maven:**
    - Visit the official [Maven download page](https://maven.apache.org/download.cgi).
    - Download the **binary zip archive** (e.g., `apache-maven-3.8.x-bin.zip`).

2. **Extract the Archive:**
    - Extract the zip file to a preferred location, such as:
      ```
      C:\Program Files\Apache\Maven
      ```

3. **Configure Environment Variables:**
    - Open **System Properties > Environment Variables**.
    - Add a new **System Variable**:
        - **Variable name:** `MAVEN_HOME`
        - **Variable value:**
          ```
          C:\Program Files\Apache\Maven\apache-maven-3.8.x
          ```
    - Edit the `Path` system variable and add:
      ```
      C:\Program Files\Apache\Maven\apache-maven-3.8.x\bin
      ```

4. **Verify Installation:**
   Open **Command Prompt** and run:
   ```sh
   mvn -v

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
* All necessary tables are also created automatically. You can add new tables in the `src/main/java/com/example/if20502025k4nsiziba/database/DatabaseHelper.java` file if you wish to create more features.

### Table Schemas

#### `users`
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | INTEGER | PRIMARY KEY AUTOINCREMENT | Unique identifier for the user. |
| `name` | TEXT | NOT NULL | Full name of the user. |
| `username` | TEXT | UNIQUE NOT NULL | Unique username for login. |
| `password` | TEXT | NOT NULL | User's password. |

#### `child`
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | INTEGER | PRIMARY KEY AUTOINCREMENT | Unique identifier for the child. |
| `name` | TEXT | NOT NULL | Full name of the child. |
| `gender` | INTEGER | | Gender of the child (1 for Male, 0 for Female). |
| `birth_date` | TEXT | | Child's date of birth (e.g., '2023-01-30'). |
| `height` | REAL | | Height in centimeters. |
| `weight` | REAL | | Weight in kilograms. |
| `head_circumference`| REAL | | Head circumference in centimeters. |
| `hand_circumference`| REAL | | Hand circumference in centimeters. |
| `abdominal_circumference` | REAL | | Abdominal circumference in centimeters. |
| `date_added` | TEXT | | Date the child's profile was created. |
| `user_id` | INTEGER | NOT NULL, FOREIGN KEY | Links to the `id` in the `users` table. |

#### `child_illness`
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | INTEGER | PRIMARY KEY AUTOINCREMENT | Unique identifier for the illness record. |
| `child_id` | INTEGER | NOT NULL, FOREIGN KEY | Links to the `id` in the `child` table. |
| `illness_name`| TEXT | NOT NULL | The name of the illness. |
| `description` | TEXT | | A description or notes about the illness. |
| `date_of_illness`| TEXT | NOT NULL | The date the illness occurred. |

#### `food`
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | INTEGER | PRIMARY KEY AUTOINCREMENT | Unique identifier for the food item. |
| `name` | TEXT | NOT NULL | Name of the food. |
| `price` | REAL | NOT NULL | Price of the food item. |
| `meal_time` | TEXT | NOT NULL | Recommended meal time (e.g., 'Pagi', 'Siang'). |
| `category` | TEXT | | Category of the food (e.g., 'Makanan Bayi'). |
| `description` | TEXT | | A brief description of the food. |
| `is_available`| INTEGER | DEFAULT 1 | Availability status (1 for true, 0 for false). |
| `created_at` | DATETIME| DEFAULT CURRENT_TIMESTAMP | Timestamp of when the record was created. |

## 8. Task Distribution
| NIM | Name | Task Distribution 
| --- | --- | --- |
| 14422028 | Muhammad Dhani Rizqiawan | Documentation 
| 14422034 | Robincar Tua Tambunan | Documentation 
| 18222004 | Muhammad Rifa Ansyari | App Development, Documentation
| 18222022 | Louis Ferdyo Gunawan | App Development , Documentation
| 18222054 | Natanael Steven Simangunsong | App Development, Documentation