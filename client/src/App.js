import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";

const App = () => {
  //const [isAuthenticated, setIsAuthenticated] = useState(false);
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />} />
        {/* 他のページのルートもここに追加できます */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;