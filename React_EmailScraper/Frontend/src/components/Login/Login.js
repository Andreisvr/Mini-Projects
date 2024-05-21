import { Grid, Paper, Typography, Box, TextField, Button, Link } from '@mui/material';
import { useState } from 'react';
import { useNavigate} from 'react-router-dom'; 
import axios from 'axios';
import { backendUrl } from '../../config/Config';
import useAuth from '../../utils/Authentication';

const Login = () => {
  const navigate = useNavigate();
  const { login } = useAuth();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    const credentials = {
      email: email,
      password: password,
    };
  
    try {
      const response = await axios.post(`${backendUrl}/api/login`, credentials);
      console.log('Login successful:', response.data);
  
      const token = response.data.token;
  
      if (token) {
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        navigate('/scrape');
        window.location.reload();
      } else {
        console.error('Token not provided in response.');
      }
    } catch (error) {
      console.error('Login error:', error.response || error.message);
    }
  };
  
  
       
  return (
    <Grid container justifyContent="center" alignItems="center" style={{ height: '100vh', backgroundColor: '#e6f2ff' }}>
      <Grid item xs={12} sm={6} md={4}>
        <Paper elevation={6} sx={{ margin: 2, padding: 2 }}>
          <Typography variant="h5" align="center" margin="dense">
            Sign-in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email"
              name="email"
              autoComplete="email"
              autoFocus
              variant="outlined"
              value={email}
              onChange={handleEmailChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              variant="outlined"
              value={password}
              onChange={handlePasswordChange}
            />
            <Button type="submit" fullWidth variant="contained" color="success" sx={{ mt: 3, mb: 2 }}>
              Login
            </Button>
            <Typography variant="body2" align="center" color="textSecondary">
              By logging in with your email account you agree to our terms and services
            </Typography>
          </Box>
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Login;
