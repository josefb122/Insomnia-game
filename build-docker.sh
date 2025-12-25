#!/bin/bash

echo "🌙 Building SleepEase APK using Docker..."
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    echo "   Visit: https://docs.docker.com/get-docker/"
    exit 1
fi

echo "📦 Building Docker image..."
docker build -t sleepease-builder .

if [ $? -ne 0 ]; then
    echo "❌ Docker build failed"
    exit 1
fi

echo ""
echo "🔨 Building APK..."
docker run --rm -v "$(pwd)/app/build:/app/app/build" sleepease-builder

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build successful!"
    echo "📱 APK location: app/build/outputs/apk/release/app-release.apk"
    echo ""
    echo "Transfer this file to your Samsung Fold 7 and install it!"
else
    echo "❌ Build failed"
    exit 1
fi
