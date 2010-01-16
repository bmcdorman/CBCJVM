#!/bin/bash
echo "[COMPILER] ------------------"
echo "[COMPILER] Building CBCWrapper"
cd cbc
./Build.sh
cd ..

echo "[COMPILER] Building Installer"
cd installer
./make_latest_installer
