# ─────────────────────────────────────────────────────────────
# Stage 1: React アプリケーションのビルド
# ─────────────────────────────────────────────────────────────
FROM node:18-alpine AS build
WORKDIR /app

# package.json と package-lock.json をコピーして依存関係をインストール
COPY package.json package-lock.json ./
RUN npm install

# React のソースコードを全てコピー
COPY . ./

# Babel のプリセットを設定して JSX を変換
RUN npm run build

# ─────────────────────────────────────────────────────────────
# Stage 2: Nginx を使って React の静的ファイルを配信
# ─────────────────────────────────────────────────────────────
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

# ポート80（HTTP）を公開
EXPOSE 80

# コンテナ起動時に Nginx を実行
CMD ["nginx", "-g", "daemon off;"]