import React, { useEffect, useState } from "react";

const DiaryList = () => {
  const [diaries, setDiaries] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/diary")
      .then((res) => res.json())
      .then((data) => {
        setDiaries(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("日記の取得に失敗しました", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>読み込み中...</div>;

  return (
    <div>
      <h2>日記一覧</h2>
      <ul>
        {diaries.map((diary) => (
          <li key={diary.id}>
            <strong>ユーザーID:</strong> {diary.userId} <br />
            <strong>日付:</strong> {diary.postDate} <br />
            <strong>コメント:</strong> {diary.comment}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default DiaryList;