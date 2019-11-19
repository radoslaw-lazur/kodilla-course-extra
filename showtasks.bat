call runcrud.bat

if "%ERRORLEVEL%" == "0" goto start
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:fail
echo.
echo There were errors

:start
echo.
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
echo Task has been executed

:end
echo.
echo Work is finished