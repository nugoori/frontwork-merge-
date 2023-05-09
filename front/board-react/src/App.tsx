import React from 'react';
import './App.css';
import { Container, Grid, } from '@mui/material'
import MainHeader from './views/Main/MainHeader/intex';
import MainMenus from './views/Main/MainMenus';
import Authentication from './views';

function App() {
  return (
    <>
    <Container fixed>
      <MainHeader />
      <Grid container>
        <Grid item xs={2.5} >
            <MainMenus />
        </Grid>
        <Authentication /> 
      </Grid>
    </Container>
    </>
  );
}

export default App;
