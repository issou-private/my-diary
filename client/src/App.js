import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";
import RegisterDiaryPage from "./pages/RegisterDiaryPage";

const App = () => {
  //const [isAuthenticated, setIsAuthenticated] = useState(false);
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<RegisterDiaryPage />} />
        {/* 他のページのルートもここに追加できます */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;