# Build Instructions for SleepEase APK

The app is complete and ready to build! Here are multiple ways to build the APK:

## Option 1: Build Locally (Recommended if you have Android Studio)

### Prerequisites
- Android Studio (latest version)
- JDK 17 or higher

### Steps
1. Open Android Studio
2. Click "Open an Existing Project"
3. Navigate to this folder and open it
4. Wait for Gradle sync to complete
5. Click "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"
6. APK will be in: `app/build/outputs/apk/release/app-release.apk`

**Installation**: Transfer the APK to your Samsung Fold 7 and install it.

---

## Option 2: Build with Command Line

### Prerequisites
- JDK 17 or higher
- Android SDK installed (can be installed via Android Studio)
- Set `ANDROID_HOME` environment variable

### Setup Android SDK Path
```bash
# Linux/Mac
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools

# Windows (PowerShell)
$env:ANDROID_HOME="C:\Users\YourUsername\AppData\Local\Android\Sdk"
```

### Build Commands
```bash
# Make gradlew executable (Linux/Mac)
chmod +x ./gradlew

# Build release APK
./gradlew assembleRelease

# APK location
# app/build/outputs/apk/release/app-release.apk
```

---

## Option 3: Build with Docker (No Android Studio needed!)

### Prerequisites
- Docker installed on your computer

### Create Dockerfile
Save this as `Dockerfile` in the project root:

```dockerfile
FROM openjdk:17-slim

# Install wget and unzip
RUN apt-get update && apt-get install -y wget unzip

# Install Android SDK
ENV ANDROID_HOME=/opt/android-sdk
ENV ANDROID_SDK_ROOT=/opt/android-sdk
RUN mkdir -p ${ANDROID_HOME}/cmdline-tools && \
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip && \
    unzip -q commandlinetools-linux-9477386_latest.zip -d ${ANDROID_HOME}/cmdline-tools && \
    mv ${ANDROID_HOME}/cmdline-tools/cmdline-tools ${ANDROID_HOME}/cmdline-tools/latest && \
    rm commandlinetools-linux-9477386_latest.zip

# Add to PATH
ENV PATH=${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools

# Accept licenses and install build tools
RUN yes | sdkmanager --licenses && \
    sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew

CMD ["./gradlew", "assembleRelease"]
```

### Build with Docker
```bash
# Build Docker image
docker build -t sleepease-builder .

# Run build
docker run -v $(pwd)/app/build:/app/app/build sleepease-builder

# APK will be in: app/build/outputs/apk/release/app-release.apk
```

---

## Option 4: GitHub Actions (Automated Cloud Build)

If you push this to GitHub, create `.github/workflows/build.yml`:

```yaml
name: Build APK

on:
  push:
    branches: [ main ]
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Setup Android SDK
      uses: android-actions/setup-android@v2

    - name: Grant execute permission for gradlew
      run: chmod +x gradlew

    - name: Build Release APK
      run: ./gradlew assembleRelease

    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: SleepEase-APK
        path: app/build/outputs/apk/release/app-release.apk
```

Push to GitHub and download the APK from the Actions tab!

---

## Option 5: Online Build Services

### Using AppCenter (Microsoft)
1. Create account at appcenter.ms
2. Create new Android app
3. Connect this Git repository
4. Configure build
5. Download APK from builds

### Using Bitrise
1. Create account at bitrise.io
2. Add new app
3. Connect repository
4. Use Android build workflow
5. Download APK

---

## Quick Start (Easiest Method)

**If you have Android Studio installed:**
1. Open this project in Android Studio
2. Wait for Gradle sync
3. Click the green play button (▶️) or "Build APK"
4. Done!

**If you don't have Android Studio:**
1. Download and install [Android Studio](https://developer.android.com/studio)
2. Follow the steps above

---

## Troubleshooting

### "SDK not found"
- Install Android Studio first, it includes the SDK
- Or set ANDROID_HOME to your SDK location

### "Build failed"
- Make sure you have JDK 17 or higher: `java -version`
- Clean build: `./gradlew clean`
- Try again: `./gradlew assembleRelease`

### "Permission denied: ./gradlew"
```bash
chmod +x ./gradlew
```

---

## Installing on Samsung Fold 7

1. Transfer the APK to your phone (USB, email, cloud storage)
2. On your phone:
   - Go to Settings → Security
   - Enable "Install unknown apps" for your file manager
3. Open the APK file
4. Tap "Install"
5. Done!

---

## App Details

- **Package Name**: com.sleepease.insomnia
- **App Name**: SleepEase
- **Version**: 1.0
- **Target SDK**: 34 (Android 14)
- **Minimum SDK**: 26 (Android 8.0)
- **Optimized for**: Samsung Fold 7 (2184 x 1968 pixels)

---

## What's Included

✅ 4-7-8 Breathing Exercise (Dr. Andrew Weil's technique)
✅ Progressive Muscle Relaxation (14 muscle groups)
✅ Calming Visualization (animated night sky)
✅ Mindful Counting (meditative counting)
✅ Warm color palette (NO BLUE LIGHT)
✅ Optimized for Samsung Fold 7
✅ Landscape orientation
✅ High-resolution graphics
✅ No internet required
✅ No ads, no tracking

Enjoy better sleep! 🌙
