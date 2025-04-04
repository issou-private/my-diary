# ─────────────────────────────────────────────────────────────
# Stage 1: アプリケーションのビルド
# ─────────────────────────────────────────────────────────────
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# pom.xml とソースコードをコンテナ内にコピー
COPY pom.xml .
COPY src/ ./src

RUN mvn -v

# Maven を用いてパッケージ化（テストはスキップ）
RUN mvn package -DskipTests

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
