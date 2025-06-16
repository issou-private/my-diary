import React from 'react';

const Header = () => {
  return (
    <header>
      <h1>My Diary アプリ</h1>
      <nav>
        <ul>
          <li><a href="/">ホーム</a></li>
          <li><a href="/create">日記を作成</a></li>
          {/* 他のナビゲーションリンクもここに追加できます */}
        </ul>
      </nav>
    </header>
  );
}

export default Header;