import React, { useState } from 'react';

const RegisterDiary = () => {
  const [userId, setUserId] = useState('');
  const [postDate, setPostDate] = useState('');
  const [comment, setComment] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);

    const data = {
      userId,
      postDate,
      comment,
    };

    try {
      const response = await fetch('http://localhost:8080/diary/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        alert('日記が作成されました！');
        setUserId('');
        setPostDate('');
        setComment('');
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
    </div>
  );
};

export default RegisterDiary;