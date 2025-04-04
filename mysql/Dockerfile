# MySQLの公式イメージを使用
FROM mysql:8.4.4

# コンテナ内の環境変数を設定
ENV MYSQL_ROOT_PASSWORD=rootpass
ENV MYSQL_DATABASE=dev_my_diary
ENV MYSQL_USER=my_diary_user
ENV MYSQL_PASSWORD=Mydiary!2025

# 必要に応じて初期SQLスクリプトをコピーして実行
# 例えば、テーブル作成や初期データの投入用スクリプト
COPY ./init.sql /docker-entrypoint-initdb.d/init.sql

# ポートを公開（デフォルトのMySQLポートは3306）
EXPOSE 3306
