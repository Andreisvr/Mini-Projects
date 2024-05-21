import { useEffect, useState } from 'react';
import { jwtDecode } from 'jwt-decode';

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [email, setEmail] = useState('');

  const checkAuthStatus = () => {
    const storedToken = localStorage.getItem('token');
    if (storedToken) {
      try {
        const decodedToken = jwtDecode(storedToken);
        if (decodedToken.exp * 1000 < new Date().getTime()) {
          logout();
        } else {
          setIsAuthenticated(true);
          setEmail(decodedToken.email);
        }
      } catch (err) {
        logout();
      }
    } else {
      setIsAuthenticated(false);
    }
  };

  useEffect(() => {
    checkAuthStatus();
    console.log(`Authentication status changed: ${isAuthenticated}`);
  }, [isAuthenticated]);

  const logout = () => {
    localStorage.removeItem('token');
    setIsAuthenticated(false);
    setEmail('');
    checkAuthStatus();
  };

  const login = (token) => {
    localStorage.setItem('token', token);
    checkAuthStatus();
  };

  return { isAuthenticated, email, logout, login };
};

export default useAuth;
