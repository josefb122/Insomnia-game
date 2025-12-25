# Complete Step-by-Step Installation Guide for Samsung Fold 7

## Method 1: Download APK from GitHub Actions (If workflow completed)

### Step 1: Check if GitHub Actions Build Completed

1. Go to your GitHub repository in a web browser
2. Click the **"Actions"** tab at the top
3. You should see a workflow run called **"Build SleepEase APK"**
4. Check the status:
   - ✅ **Green checkmark** = Build successful! Continue to Step 2
   - 🟡 **Yellow circle** = Still building, wait a few minutes
   - ❌ **Red X** = Build failed, use Method 2 instead

### Step 2: Download the APK from GitHub Actions

1. Click on the successful workflow run (green checkmark)
2. Scroll down to the **"Artifacts"** section at the bottom
3. You'll see two files:
   - **SleepEase-Debug** (recommended for testing)
   - **SleepEase-Release** (production version)
4. Click **"SleepEase-Debug"** to download it
5. Extract the ZIP file on your computer
6. Inside you'll find **app-debug.apk**

### Step 3: Transfer APK to Your Samsung Fold 7

Choose ONE method:

**Option A: USB Cable Transfer**
1. Connect your Fold 7 to computer via USB cable
2. On phone: Swipe down and tap the USB notification
3. Select **"File Transfer"** or **"Transfer files"**
4. On computer: Open your phone's storage in File Explorer (Windows) or Finder (Mac)
5. Copy **app-debug.apk** to your phone's **Downloads** folder
6. Disconnect phone

**Option B: Email Transfer**
1. Email **app-debug.apk** to yourself
2. Open email on your Fold 7
3. Download the APK attachment
4. APK will be in Downloads folder

**Option C: Cloud Storage (Google Drive, Dropbox, etc.)**
1. Upload **app-debug.apk** to your cloud storage
2. Open cloud app on your Fold 7
3. Download the APK file

**Option D: Direct Download on Phone**
1. On your Fold 7, open Chrome/Samsung Internet
2. Go to your GitHub repository
3. Click **Actions** tab
4. Click the successful workflow run
5. Scroll to **Artifacts**
6. Tap **SleepEase-Debug** to download directly
7. Extract the ZIP using a file manager app

### Step 4: Enable Installation from Unknown Sources

1. On your Fold 7, open **Settings**
2. Go to **"Apps"**
3. Tap the three dots (⋮) in top right → **"Special access"**
4. Tap **"Install unknown apps"**
5. Find **"My Files"** (or **"Files"** or **"Chrome"** - whichever app you'll use to open the APK)
6. Toggle **"Allow from this source"** to ON

### Step 5: Install the APK

1. Open **My Files** app on your Fold 7
2. Tap **"Downloads"**
3. Find **app-debug.apk**
4. Tap it
5. Tap **"Install"**
6. Wait for installation to complete
7. Tap **"Open"** or find **"SleepEase"** in your app drawer

### Step 6: Enjoy!

1. Open fold to use the inner 8-inch screen
2. Phone will rotate to landscape automatically
3. Choose your preferred relaxation technique
4. Use when you wake up at night

---

## Method 2: Build Locally with Android Studio (If GitHub Actions Failed)

If GitHub Actions didn't work or you want to build it yourself:

### What You Need:
- A computer (Windows, Mac, or Linux)
- Android Studio (free download)
- USB cable for your phone

### Step-by-Step:

1. **Download Android Studio**
   - Go to: https://developer.android.com/studio
   - Download and install (takes 10-15 minutes)

2. **Clone/Download the Repository**
   - Download the repository as ZIP from GitHub
   - Or if you have git: `git clone [your-repo-url]`
   - Extract to a folder

3. **Open Project in Android Studio**
   - Open Android Studio
   - Click **"Open"**
   - Navigate to the Insomnia-game folder
   - Click **"OK"**
   - Wait for Gradle sync (first time takes 5-10 minutes)

4. **Connect Your Phone**
   - Connect Fold 7 via USB cable
   - On phone: Enable **Developer Options**:
     - Settings → About phone → Tap "Build number" 7 times
   - Go to Settings → Developer options
   - Enable **"USB debugging"**
   - On computer: Click "Allow" when phone asks to trust computer

5. **Build and Install**
   - In Android Studio, click the green play button (▶️) at the top
   - Or go to: **Build → Build Bundle(s) / APK(s) → Build APK(s)**
   - Wait for build to complete
   - If using play button: App installs and opens automatically!
   - If building APK: Find it at `app/build/outputs/apk/debug/app-debug.apk`

---

## Method 3: Quick Build with Docker (For Advanced Users)

If you have Docker installed:

```bash
# Build with Docker
./build-docker.sh

# APK will be in:
# app/build/outputs/apk/release/app-release.apk
```

Then transfer to phone using Method 1, Step 3-6.

---

## Troubleshooting

### "App not installed" error
- Make sure you enabled "Install unknown apps" for the right app (Files/My Files)
- Try uninstalling any previous version
- Restart your phone

### Can't find the APK file
- Check Downloads folder in My Files app
- Try using Samsung's "My Files" app instead of other file managers

### Phone says "This app may harm your device"
- This is normal for apps not from Play Store
- Tap "Install anyway" or "More details" → "Install anyway"
- The app is safe (you have the source code!)

### GitHub Actions still running
- Wait 10-15 minutes for first build
- Check Actions tab, refresh page
- If it fails, use Method 2 (Android Studio)

### Need help?
- Check BUILD_INSTRUCTIONS.md in the repository
- All source code is visible - it's safe!

---

## Quick Reference Card

**Fastest method:** GitHub Actions (if working) → Download artifact → Transfer via USB → Install

**Most reliable:** Android Studio → Build APK → Transfer → Install

**Easiest (if you have Android Studio):** Connect phone → Click play button ▶️

---

## After Installation

🌙 **When to Use:**
- When you wake up at 3 AM stressed
- Can't fall back asleep
- Mind racing
- Body tense

📱 **How to Use:**
1. Open your Fold 7 completely (use inner screen)
2. Keep phone in landscape mode
3. Choose an exercise:
   - **Breathing** if anxious
   - **Relaxation** if tense
   - **Counting** if mind racing
   - **Visualization** for calm
4. Follow the guidance
5. Don't force sleep, let it come

💡 **Tips:**
- Lower phone brightness to minimum
- Use in dark room
- Try different techniques
- Don't stress if one doesn't work immediately

---

Ready to sleep better! 😴
