import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import RegisterDiary from "../components/RegisterDiary";
import Header from "../components/Header";

const RegisterDiaryPage = () => {

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
          <RegisterDiary />
        </div>
      );
}

export default RegisterDiaryPage;
