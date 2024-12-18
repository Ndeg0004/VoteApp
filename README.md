# Votely App

Votely is an Android voting application where users can authenticate, view polls, submit votes, and manage questions. Firebase is used for authentication and database management.

---

## **Features**

- **Authentication**:
  - Login using Firebase Authentication.
- **Polls Management**:
  - Fetch polls dynamically from Firebase Realtime Database.
  - Submit votes for polls and view results.
- **Add Questions**:
  - Users can add questions dynamically.
- **Clean UI**:
  - Interactive layout designed using XML for Android.

---

## **Directory Structure**

```
VotelyApp/
│
├── manifests/
│   └── AndroidManifest.xml               # Application manifest file
│
├── java/com/example/voteapp/             # Application source code
│   ├── firebase/
│   │   ├── authenticationManager/
│   │   │   └── AuthenticationManager     # Handles Firebase Authentication
│   │   ├── realtimeDatabase/
│   │       ├── PollsManager              # Manages polls retrieval
│   │       ├── RealtimeDatabaseManager   # General database manager
│   │       └── VoteManager               # Handles vote submissions
│   │
│   ├── model/
│   │   ├── Poll                          # Poll model
│   │   └── Vote                          # Vote model
│   │
│   ├── AddQuestionActivity               # Activity for adding questions
│   ├── LoginActivity                     # Activity for user login
│   ├── MainActivity                      # Main activity screen
│   ├── QuestionAdapter                   # Adapter for displaying questions
│   ├── SplashActivity                    # Splash screen on app launch
│   └── VoteActivity                      # Activity for voting on polls
│
├── java/com/example/voteapp (androidTest)/
│   └── ExampleInstrumentedTest           # Instrumented tests for Android
│
├── java/com/example/voteapp (test)/
│   └── ExampleUnitTest                   # Unit tests
│
├── java (generated)/
│   ├── androidx/databinding/
│   │   ├── library.baseAdapters/
│   │   │   ├── DataBinderMapperImpl      # Data binding mapper
│   │   │   └── DataBindingComponent      # Data binding component
│
├── res/
│   ├── drawable/                         # App logos and drawable resources
│   ├── layout/
│   │   ├── activity_add_question.xml     # Layout for adding questions
│   │   ├── activity_login.xml            # Layout for login screen
│   │   ├── activity_main.xml             # Layout for main screen
│   │   ├── activity_splash.xml           # Layout for splash screen
│   │   ├── activity_vote.xml             # Layout for voting screen
│   │   └── item_question.xml             # Layout for individual question item
│
└── README.md                             # Project documentation
```

---

## **Setup Instructions**

### **1. Prerequisites**

- Install [Android Studio](https://developer.android.com/studio).
- Set up an Android device or emulator.
- Create a Firebase project in the [Firebase Console](https://console.firebase.google.com/).
- Enable Firebase **Authentication** and **Realtime Database**.

---

### **2. Clone the Repository**

Run the following commands to clone the project:

```bash
git clone https://github.com/yourusername/votely-app.git
cd votely-app
```

---

### **3. Configure Firebase**

1. Download the `google-services.json` file from your Firebase project.
2. Place it in the `app/` directory.
3. Enable Email/Password authentication in Firebase Authentication.
4. Update Firebase Realtime Database rules:

```json
{
  "rules": {
    ".read": "auth != null",
    ".write": "auth != null"
  }
}
```

---

### **4. Build and Run the App**

1. Open the project in **Android Studio**.
2. Sync Gradle by clicking **Sync Now**.
3. Run the app:
   - Click **Run > Run 'app'** or press `Shift + F10`.
4. Use the following credentials to log in:
   - **Username**: `anthon.n24@gmail.com`
   - **Password**: `123456`

---

## **How to Use**

1. **Login**:
   - Enter the provided credentials to access the app.
2. **View Polls**:
   - Browse polls fetched dynamically from Firebase.
3. **Vote**:
   - Select an option and submit your vote.
4. **Add Questions**:
   - Add new poll questions via the `AddQuestionActivity`.

---

## **Technologies Used**

- **Frontend**: Android (Java)
- **Backend**: Firebase Realtime Database
- **Authentication**: Firebase Authentication
- **UI Design**: XML Layouts

---

## **Troubleshooting**

1. **Gradle Sync Issues**:
   - Ensure dependencies are installed. Run:
     ```bash
     ./gradlew build
     ```
2. **Firebase Errors**:
   - Verify the `google-services.json` file.
   - Check database rules in Firebase.
3. **Login Issues**:
   - Confirm the email/password combination is correct:
     - **Username**: `anthon.n24@gmail.com`
     - **Password**: `123456`

---

## **License**

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

---

## **Contact**

For any questions or issues, please contact:

- **Email**: anthon.n24@gmail.com
- **GitHub**: https://github.com/Ndeg0004
