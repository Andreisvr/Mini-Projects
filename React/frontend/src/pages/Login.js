import React, { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import '/Users/Andrei_Sviridov/Desktop/React/frontend/src/page_css/Login.css';
import GoogleBtn from '../components/login_btn';
import axios from 'axios';
import '/Users/Andrei_Sviridov/Desktop/React/frontend/src/images/wallpaperflare.com_wallpaper.jpg'
import { AppContext } from '../components/AppContext';

function LogIn() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const navigate = useNavigate();
  const { handleLogin } = useContext(AppContext); 

  const handleGmailLogin = async (decodedToken) => {
    const gmailEmail = decodedToken.email;
    const gmailName = `${decodedToken.given_name} ${decodedToken.family_name}`;
    const gmail_password =  decodedToken.jti;
    
    try {
      const response = await axios.post('http://localhost:8081/login', {
        email: gmailEmail,
        pass : gmail_password
      });

      if (response.data.success) {
        const { name, email, prof, faculty, program } = response.data.user;
        const userType = prof === 1 ? 'professor' : 'student'; 
        console.log(email, gmailName, userType, program, faculty);

        handleLogin(name, email, userType, userType === 'student' ? program : null, faculty); 
       
        navigate('/prof'); 
      } else {
        alert('Apărea o eroare');
      }
    } catch (error) {
      console.log("Error logging in:", error);
      setEmailError('An error occurred. Please try again later.');
    }
  };

  const onButtonClick = async () => {
    setEmailError('');
    setPasswordError('');

    
    if (email === '') {
        setEmailError('Please enter your email');
        return;
    }
    
    // if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
    //     setEmailError('Please enter a valid email');
    //     return;
    // }

    // Validare parolă
    if (password === '') {
        setPasswordError('Please enter a password');
        return;
    }

    // if (password.length < 8) {
    //     setPasswordError('The password must be 8 characters or longer');
    //     return;
    // }

    //console.log('em pass', email, password);
    try {
        const response = await axios.post('http://localhost:8081/login', {
            email,
            password,
        });

        if (response.data.success) {
          const { name, email, prof, faculty, program } = response.data.user;
          const userType = prof === 1 ? 'professor' : 'student'; 
          console.log(email, password, name, userType, program, faculty);
          
          handleLogin(name, email, userType, userType === 'student' ? program : null, faculty); 
       
          navigate('/prof');
        } else {
            setEmailError(response.data.message || 'Invalid credentials');
        }
    } catch (error) {
        console.log("Error logging in:", error);
        setEmailError('An error occurred. Please try again later.');
    }
  };

  return (
    <div className='body_login'>
      <form className='form_login'>
        <h1 className='title'>Login</h1>
        <div className={'field_container'}>
          <input
            value={email}
            placeholder="Enter your email here"
            onChange={(ev) => setEmail(ev.target.value)}
            className={'inputBox'}
          />
          <label className="errorLabel">{emailError}</label>
        </div>
        <div className={'field_container'}>
          <input
            type="password"
            value={password}
            placeholder="Enter your password here"
            onChange={(ev) => setPassword(ev.target.value)}
            className={'inputBox'}
          />
          <label className="errorLabel">{passwordError}</label>
        </div>
        <div className="links_container">
          <Link className="link_forget" to="/restore_pass">Forget the password</Link>
          <Link className="link_register" to="/type">Register</Link>
        </div>
        <div className='google_btn'>
          <GoogleBtn onSuccessLogin={handleGmailLogin} />
        </div>
        <div className={'inputContainer'}>
          <input className={'Login_btn'} type="button" onClick={onButtonClick} value={'Log in'} />
        </div>
      </form>
    </div>
  );
}

export default LogIn;
