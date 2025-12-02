@echo off
echo Starting MongoDB...
docker start mongodb_container
timeout /t 3 /nobreak > nul

echo.
echo MongoDB is running!
echo.
echo To run the application:
echo 1. Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
echo 2. Run DemoApplication.java
echo.
echo Or use Maven command:
echo mvn spring-boot:run
echo.
pause
