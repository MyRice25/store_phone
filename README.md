**Store Phone**
Store Phone is an Android application developed in Java that allows users to manage orders and products. The application uses Firebase for real-time data storage and user authentication, 
and integrates with a PHP back-end to handle API requests. The user interface is built using XML and RecyclerView, providing a smooth and easy-to-use experience.
**Table of Contents**
**Features**
- Product Management: Add, edit, delete contacts via Android interface, sync with Firebase Realtime Database.
- User Authentication: Login/Register using Firebase Authentication (email or Google).
- PHP API: Integrate with PHP back-end to handle additional logic, e.g. fetch product list or store contact data.
- Friendly interface: Use RecyclerView to display contact list and XML layouts for interface.
**Installation**
Android Client
**1. Clone the repository:**
  git clone https://github.com/your-username/store-phone.git
**2. Open the project in Android Studio:**
  Launch Android Studio, select File > Open, and navigate to the cloned project folder.
**3. Configure Firebase:**
- Create a Firebase project in the Firebase Console.
- Download the google-services.json file and place it in the app/ directory.
- Add Firebase dependencies to build.gradle
**Backend Setup**
**1. Set up Firebase Realtime Database**
- Enable Realtime Database in the Firebase Console.
- Configure security rules
**2. Enable Firebase Authentication**
- Turn on Email/Password and Google Sign-In in the Firebase Console.
**Project Structure**
  store-phone/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/storephone/
│   │   │   │   ├── MainActivity.java        # Main activity
│   │   │   │   ├── ContactAdapter.java      # RecyclerView adapter
│   │   │   │   ├── FirebaseHelper.java      # Firebase operations
│   │   │   │   ├── ApiHelper.java           # PHP API handling
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml    # Main layout
│   │   │   │   │   ├── item_contact.xml     # Contact item layout
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml          # String resources
│   │   │   ├── AndroidManifest.xml          # App configuration
│   ├── build.gradle                         # Gradle configuration
├── backend/
│   ├── api/
│   │   ├── contacts.php                     # PHP API for contacts
│   ├── composer.json                        # PHP dependencies
├── README.md                               # This file
