# ─────────────────────────────────────────────────────────────
# Stage 1: アプリケーションのビルド
# ─────────────────────────────────────────────────────────────
FROM amazoncorretto:17 AS build
WORKDIR /app

# pom.xml とソースコードをコンテナ内にコピー
COPY pom.xml .
COPY src/ ./src

# Maven を用いてパッケージ化（テストはスキップ）
RUN yum update -y && yum install -y maven && yum clean all && mvn package -DskipTests

# ─────────────────────────────────────────────────────────────
# Stage 2: アプリケーションの実行環境の構築
# ─────────────────────────────────────────────────────────────
FROM amazoncorretto:17
WORKDIR /app

# Stage 1 で作成された jar ファイルを、実行環境にコピー
COPY --from=build /app/target/*.jar app.jar

# コンテナのポート 8080 を公開（必要に応じて変更）
EXPOSE 8080

# コンテナ起動時にアプリケーションを実行
ENTRYPOINT ["java", "-jar", "app.jar"]
