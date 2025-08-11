export async function sendCommentToOkanAI(comment) {
  try {
    const response = await fetch('http://localhost:8081/api/okanai', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ content: comment }) // commentをcontentフィールドとして送信
    });

    if (!response.ok) {
      throw new Error('OkanAI APIへの送信に失敗しました');
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('OkanAI API送信エラー:', error);
    throw error;
  }
}