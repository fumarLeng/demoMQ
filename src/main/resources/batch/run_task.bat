@echo off
setlocal enabledelayedexpansion

set JAR_FILE=demoMQ.jar
set MAIN_CLASS=com.example.demomq.NewTask
set MESSAGE_PREFIX=message

for /l %%i in (1,1,10) do (
    set MESSAGE=%MESSAGE_PREFIX% %%i
    echo  %%i : !MESSAGE!
    java -cp %JAR_FILE% %MAIN_CLASS% "!MESSAGE!"
    timeout /t 2 >nul
)

echo success!
pause