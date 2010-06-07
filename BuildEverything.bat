@echo on
echo "[COMPILER] ------------------"
echo "[COMPILER] Building CBCWrapper"
cd cbc
mvn clean install
Build.bat
cd ..

echo "[COMPILER] Building Installer"
cd installer
make_latest_installer.bat

echo "[COMPILER] Building Download Tool"
cd consoleDownload
ant
