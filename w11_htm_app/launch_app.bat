@echo off
title Veepoo Smartwatch BLE Dashboard
set PORT=8899
echo ===================================================
echo   Veepoo / H Band BLE Dashboard (Port %PORT%)
echo ===================================================
echo Starting local web server...
start "" "http://localhost:%PORT%/index.html"
"D:\veepoo\tools\python\python.exe" -m http.server %PORT% --directory "D:\veepoo\w11_htm_app"
pause
