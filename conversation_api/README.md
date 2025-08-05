conversation_api/
├── Dockerfile
├── docker-compose.yml
├── .env
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/converse/
│       │       ├── AwsConverseApplication.java       # メインクラス
│       │       ├── config/
│       │       │   └── AwsConfig.java                # AWS SDK設定
│       │       ├── controller/
│       │       │   └── ConverseController.java       # REST APIエンドポイント
│       │       ├── service/
│       │       │   └── ConverseService.java          # Converse API呼び出しロジック
│       │       └── model/
│       │           └── MessageRequest.java           # リクエストモデル
│       └── resources/
│           ├── application.yml                       # Spring設定
│           └── logback.xml                           # ログ設定（任意）
└── README.md