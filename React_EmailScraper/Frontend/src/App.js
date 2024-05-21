import logo from './logo.svg';
import './App.css';
import Navigation from './components/Navigation/Navigation';
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom';
import Home from './pages/Home/Home';
import Scrape from './pages/Scrape/Scrape';
import Contact from './pages/Contact/Contact';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import LoginPage from './pages/LoginPage/LoginPage';
import Help from './pages/Help/help';
import Analize from './components/Analize/Analiza';
import Monitor from './pages/Monitor/Monitor';

const theme = createTheme({
  palette: {
    primary: {
      main: '#3f3f4f',
    },
    secondary: {
    main: '#yourSecondaryColor',
    },
  },
});


function App() {
  return (
    <ThemeProvider theme={theme}>
      <div className="App"> 
        <BrowserRouter>
        <Navigation/>
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/login" element={<LoginPage/>} />
            <Route path="/scrape" element={<Scrape />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/help" element={<Help />} />
            <Route path="/Monitor" element={<Monitor />} />
            <Route path="/Analize" element={<Analize />} />
          </Routes>
        </BrowserRouter>
      </div>
    </ThemeProvider>
    
  );
}

export default App;
