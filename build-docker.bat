@echo off
echo Building SleepEase APK using Docker...
echo.

REM Check if Docker is installed
docker --version >nul 2>&1
if errorlevel 1 (
    echo Docker is not installed. Please install Docker first.
    echo Visit: https://docs.docker.com/get-docker/
    exit /b 1
)

echo Building Docker image...
docker build -t sleepease-builder .

if errorlevel 1 (
    echo Docker build failed
    exit /b 1
)

echo.
echo Building APK...
docker run --rm -v "%cd%/app/build:/app/app/build" sleepease-builder

if errorlevel 0 (
    echo.
    echo Build successful!
    echo APK location: app\build\outputs\apk\release\app-release.apk
    echo.
    echo Transfer this file to your Samsung Fold 7 and install it!
) else (
    echo Build failed
    exit /b 1
)
