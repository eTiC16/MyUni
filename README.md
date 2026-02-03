MyUniApp – NIT3213 Final Assignment
MyUniApp is an Android application built for the NIT3213 Mobile Application Development unit.
It demonstrates modern Android development practices including MVVM architecture, Hilt dependency injection, Retrofit networking, View Binding, and unit testing.

This project follows a clean, scalable, and industry‑standard structure suitable for academic submission and real‑world development.

📌 Features
1. Login Screen
User enters campus and student ID

Sends a POST request to the campus /auth endpoint

Receives a keypass used for dashboard data

Displays loading and error states

2. Dashboard Screen
Fetches entity list using the keypass

Displays items in a RecyclerView

Uses a clean LinearLayout UI

Handles loading and error states

3. Details Screen
Shows full details of a selected entity

Receives data via Intent

Simple, clean layout

4. Architecture
MVVM (Model–View–ViewModel)

Repository pattern

View Binding

Kotlin Coroutines

Retrofit + OkHttp

Hilt for dependency injection

5. Unit Testing
Includes tests for:

LoginViewModelTest

DashboardViewModelTest

Using:

JUnit

MockK

CoroutineTestRule

InstantTaskExecutorRule

📂 Project Structure
Code
com.example.myuni
│
├── data
│   ├── api          # Retrofit services
│   ├── model        # Data models (EntityDto, LoginResponse, etc.)
│   └── repository   # Repository classes
│
├── di               # Hilt modules
│
├── ui
│   ├── login        # LoginActivity + LoginViewModel
│   ├── dashboard    # DashboardActivity + DashboardViewModel + Adapter
│   └── details      # DetailsActivity
│
└── MyUniApp.kt      # Application class with @HiltAndroidApp

🎨 Layout Files
Code
res/layout/
│
├── activity_login.xml
├── activity_dashboard.xml
├── activity_details.xml
└── item_entity.xml
All layouts use View Binding and follow clean, readable XML structure.

🔌 API Endpoints
Login
Code
POST /{campus}/auth
Body: { "studentId": "..." }
Response: { "keypass": "..." }
Dashboard
Code
GET /dashboard/{keypass}
Response: { "entities": [...], "entityTotal": n }
🧪 Unit Testing
Tests are located in:

Code
app/src/test/java/com.example.myuni/
Run tests via:

Android Studio → Run → Run All Tests

A full test report is generated at:

Code
app/build/reports/tests/testDebugUnitTest/index.html
⚙️ Build System
Kotlin DSL (build.gradle.kts)

Version catalog (libs.versions.toml)

ProGuard rules configured

Hilt code generation verified (hilt_aggregated_deps)

🚀 How to Run
Clone the repository

Open in Android Studio

Sync Gradle

Run on emulator or physical device

👤 Author
Kabuj Bin Alam
sID: s8163924
