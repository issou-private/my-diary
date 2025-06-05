import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";

const MainPage = () => {
    const [userData, setUserData] = useState('');
    const [diaryList, setDiaryList] = useState([]);

//    useEffect(() => {
 //       const loadUserData = async () => {
 //           try {
  //              const response = await fetch(`/user/${id}`);
 //               if (response.ok) {
   //                 const data = await response.json();
   //                 delete data.password;
    //                setUserData(data);
    //            } else {
     //               const data = await response.text();
     //               console.error(response.status, data);
      //          }
      //      } catch (error) {
       //         console.error(error);
      //      }
      //  };
     //   loadUserData();
  ///  }, [id]);

  useEffect(() => {
    const loadDiaryList = async () => {
        try {
            const response = await fetch(`/diary/${id}`);
            if (response.ok) {
                const data = await response.json();
                setDiaryList(data);
            } else {
                const data = await response.text();
                console.error(response.status, data);
                if (response.status === 500) {
                    alert("サーバーが現在リクエストを処理できない状態にあります。");
                }
            }
        } catch (error) {
            console.error(error);
        }
    };
    loadDiaryList();
}, [id]);


}
