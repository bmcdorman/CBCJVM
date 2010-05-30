#!/bin/bash
echo "[COMPILER] ------------------"
echo "[COMPILER] Building CBCWrapper"
cd cbc
mvn clean install
sh Build.sh
cd ..

echo "[COMPILER] Building Installer"
cd installer
sh make_latest_installer

echo "[COMPILER] Building Download Tool"
cd consoleDownload
ant
