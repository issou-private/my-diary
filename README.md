#今回の開発の方針
dockerとawsを用いた開発の学習

リポジトリ構成（co-pilotに出力してもらった）

my-diary
├── README.md                # プロジェクト概要、セットアップ方法のドキュメント
├── .gitignore               # 不要なファイルやディレクトリ（例：ビルド成果物、node_modulesなど）を除外する設定
├── Dockerfile               # マルチステージビルドなど、全体のDockerイメージを作成するためのDockerfile
├── docker-compose.yml       # （任意）複数コンテナを利用する場合のサービス定義ファイル ※シングルコンテナの場合は不要
├── server/                  # サーバー側（Spring Boot）のコード関連
│   ├── pom.xml              # Mavenの場合のプロジェクト定義ファイル（Gradleを使うなら build.gradle など）
│   ├── src/                 # Javaソースコードおよびリソース（通常は src/main/java, src/main/resources など）
│   └── ...                  # その他、Spring Bootの設定ファイル等（application.yml / application.properties など）
└── client/                  # クライアント側（React）のコード関連
    ├── package.json         # Reactプロジェクトの依存管理ファイル（npmやyarn）
    ├── package-lock.json    # 依存解決のためのロックファイル（または yarn.lock）
    ├── public/              # 静的なHTMLや画像など（Reactプロジェクトの公開用フォルダ）
    ├── src/                 # Reactのコンポーネントやスタイルシート等のソースコード
    └── ...                  # その他の設定ファイル（.env など、Git管理が必要なら含める）
