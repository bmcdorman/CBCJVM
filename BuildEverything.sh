#!/bin/bash
echo "[COMPILER] ------------------"
echo "[COMPILER] Building CBCWrapper"
cd cbc
sh Build.sh
cd ..

echo "[COMPILER] Building Installer"
cd installer
sh make_latest_installer
