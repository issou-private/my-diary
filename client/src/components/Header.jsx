import react from 'react';

const Header = () => {
  return (
    <header>
      <h1>My Diary アプリ</h1>
      <nav>
        <ul>
          <li><a href="/">ホーム</a></li>
          <li><a href="/diary">日記一覧</a></li>
          {/* 他のナビゲーションリンクもここに追加できます */}
        </ul>
      </nav>
    </header>
  );
}