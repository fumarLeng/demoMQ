# RabbitMQ 設置和運行方法


## 前提條件

- Linux 系統（用於運行 Docker）
- Docker 已安裝
- Java 運行環境 (JRE)
- 示例 Java 應用程序 JAR 文件（`demoMQ.jar`）

## 步驟 1: 確定 Linux IP 地址

在 Linux 系統上運行以下命令來獲取 IP 地址：

```bash
ip ro
```
## 步驟 2: 在 Linux 上使用 Docker 運行 RabbitMQ
運行以下命令來啟動 RabbitMQ 容器：

````bash
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
````
這個命令會：

啟動一個名為 "rabbitmq" 的容器
映射 AMQP 協議端口 (5672) 和管理界面端口 (15672)
使用 RabbitMQ 3.13 版本的管理鏡像
在容器停止時自動刪除容器（--rm 選項）

## 步驟 3: 運行 Worker 應用程序
在包含 demoMQ.jar 文件的目錄中，運行以下命令來啟動 Worker：

````bash
java -cp demoMQ.jar com.example.demomq.Worker
````

## 步驟 4: 運行 Task 應用程序
您有兩種選擇來運行 Task 應用程序：
### 選項 A: 使用批次處理文件

創建一個名為 run_tasks.bat 的文件，內容如下：
````batch
@echo off
setlocal enabledelayedexpansion

set JAR_FILE=demoMQ.jar
set MAIN_CLASS=com.example.demomq.NewTask
set MESSAGE_PREFIX=訊息

for /l %%i in (1,1,10) do (
    set MESSAGE=%MESSAGE_PREFIX% %%i
    echo 執行第 %%i 次: !MESSAGE!
    java -cp %JAR_FILE% %MAIN_CLASS% "!MESSAGE!"
    timeout /t 2 >nul
)

echo 所有任務執行完畢！
pause
````

運行 run_tasks.bat 文件來自動執行 10 次任務測試

### 選項 B: 手動執行
在命令提示符中運行以下命令來手動執行單個任務：

````bash
java -cp demoMQ.jar com.example.demomq.NewTask
````

