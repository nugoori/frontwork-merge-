import React, { useEffect } from 'react';
import './App.css';
import { Container, Grid, } from '@mui/material'
import MainHeader from './views/Main/MainHeader/intex';
import MainMenus from './views/Main/MainMenus';
import { Route, Routes, useLocation } from 'react-router-dom';
import { useUserStore } from './stores';
import { useCookies } from 'react-cookie';
import { GET_USER_URL, authorizationHeader } from './constants/api';
import axios, { AxiosResponse } from 'axios';
import ResponseDto from './apis/response';

const path = useLocation();
  // const { setUser } = useUserStore();
  // const [cookies] = useCookies();

  // const getUser = (accessToken: string) => {
  //   axios.get(GET_USER_URL, authorizationHeader(accessToken))
  //   .then((response) => getUserResponseHandler(response))
  //   .catch((error) => getUserErrorHandler(error));
  // }

  // const getUserResponseHandler = (response: AxiosResponse<any, any>) => {
  //   const { result, message, data } = response.data as ResponseDto<any>;
  //   if (!result || !data) {
  //     return;
  //   }
  //   const user = data as GetUserResponseDto ;
  //   setUser(user);
  // }

  // const getUserErrorHandler = (error: any) => {
  //   console.log(error.message);
  // }

  // useEffect(() => {
  //   const accessToken = cookies.accessToken;
  //   if (accessToken) getUser(accessToken);
  // }, [path]);

function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={(<Main />)} />
        <Route path='/auth' element={(<AuthenticationView />)} />
        <Route path='/myPage' element={(<MyPageView />)} />
        <Route path='/board'>
          <Route path='write' element={(<BoardWriteView />)} />
          <Route path='search/:content' element={(<SearchView />)} />
          <Route path='detail/:boardNumber' element={(<BoardDetailView />)} />
          <Route path='update/:boardNumber' element={(<BoardUpdateView />)} />
        </Route>
      </Routes>
      { path.pathname !== '/auth' && (<Footer />) }
    </>
  );
}

export default App;
