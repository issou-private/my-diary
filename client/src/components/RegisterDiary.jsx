import React, { useState,useEffect } from 'react';
import { sendCommentToOkanAI } from './OkanAI'; // 追加

const RegisterDiary = () => {
  const [userId, setUserId] = useState('');
  const [postDate, setPostDate] = useState('');
  const [comment, setComment] = useState('');
  const [loading, setLoading] = useState(false);
  const [okanaiResponse, setOkanaiResponse] = useState(''); // 追加

  // ✅ 再マウント検出
  useEffect(() => {
    console.log('🆕 RegisterDiary マウントされました');
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);

    const data = {
      userId,
      postDate,
      comment,
    };

    try {
      // OkanAI APIへコメント送信
      const okanaiRes = await sendCommentToOkanAI(comment);
      setOkanaiResponse(okanaiRes.reply || 'OkanAIからの応答: 受信しました');
    } catch (error) {
      setOkanaiResponse('OkanAI APIへの送信に失敗しました');
    }

    try {
      // 日記登録APIへ送信
      const response = await fetch('http://localhost:8080/diary/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        alert('日記が作成されました！');
      } else {
        const errorText = await response.text();
        alert(`日記の作成に失敗しました: ${errorText}`);
      }
    } catch (error) {
      console.error('日記の作成中にエラーが発生しました:', error);
      alert('日記の作成中にエラーが発生しました。');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>日記を作成</h2>
      <form onSubmit={handleSubmit}>
        <label>
          ユーザーID:
          <input
            type="text"
            name="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          日付:
          <input
            type="datetime-local"
            name="postDate"
            value={postDate}
            onChange={(e) => setPostDate(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          コメント:
          <textarea
            name="comment"
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            required
          ></textarea>
        </label>
        <br />
        <button type="submit" disabled={loading}>
          {loading ? "送信中..." : "日記を作成"}
        </button>
      </form>
      {okanaiResponse && (
        <div style={{ marginTop: '1em', color: 'green' }}>
          {okanaiResponse}
        </div>
      )}
    </div>
  );
};

export default RegisterDiary;