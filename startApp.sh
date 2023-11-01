#! /bin/bash

echo ""
echo "[INFO] Starting application"

echo ""
echo "[INFO] Creating application JAR file"
echo ""
./mvnw install
echo ""
echo "[INFO] JAR file created in target folder successfully"
echo ""

echo ""
echo "[INFO] Creating docker image" 
echo ""
echo "[IMPORTANT] Docker Desktop must be running"
echo ""
docker build -t rick-and-morty-img .
echo ""
echo "[INFO] Docker image created successfully"
echo ""

echo ""
echo "[INFO] Creating docker container with created image" 
echo ""
echo "[IMPORTANT] Docker Desktop must be running"
echo ""
echo "[INFO] Possible error trying to STOP rick-and-morty-container if not already exist"
docker container stop rick-and-morty-container
echo "[INFO] Possible error trying to REMOVE rick-and-morty-container if not already exist"
docker container rm rick-and-morty-container
echo "[INFO] Trying to Create container"
docker container create -i -t --name rick-and-morty-container --publish 3456:3456 rick-and-morty-img
echo ""
echo "[INFO] Docker container created successfully"
echo ""

echo ""
echo "[INFO] Running Rick and Morty Rest Api Application on Docker Container" 
echo ""
echo "[IMPORTANT] Docker Desktop must be running"
echo ""
docker start rick-and-morty-container
echo ""
echo "[INFO] Docker container Running successfully"
echo ""
