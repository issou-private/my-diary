import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import DiaryList from "../components/DiaryList";
import Header from "../components/Header";

const MainPage = () => {

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
      return (
        <div>
          <Header />
          <DiaryList />
        </div>
      );
}

export default MainPage;
