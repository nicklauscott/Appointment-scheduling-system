#!/bin/bash

set -e  # Exit on any error

echo "Building api-gateway image..."
cd api-gateway
./gradlew jibDockerBuild
cd ..

echo "Building auth-service image..."
cd auth-service
./gradlew jibDockerBuild
cd ..

echo "Building appointment-service image..."
cd appointment-service
./gradlew jibDockerBuild
cd ..

echo "Building tenant-service image..."
cd tenant-service
./gradlew jibDockerBuild
cd ..

echo "Building notification-service image..."
cd notification-service
./gradlew jibDockerBuild
cd ..

echo "Spinning up services with Docker Compose..."
docker compose up
