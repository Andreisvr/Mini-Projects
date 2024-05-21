import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import {AppBar, Box, Button, Toolbar, Typography} from '@mui/material'
import CleaningServicesIcon from '@mui/icons-material/CleaningServices';
import { useNavigate} from 'react-router-dom'; 
import useAuth from '../../utils/Authentication';

const Navigation = () => {


  const navigate = useNavigate();
  const { isAuthenticated, logout } = useAuth();

  console.log(`Navigation rendering. Authenticated: ${isAuthenticated}`);
  const handleLogInClick = () => {
    navigate('/login');
  };

  const handleLogOutClick = () => {
    logout();
    navigate('/');  
  };

  const handleNavigateScrape = () => {
    navigate('/scrape');
  };

  const handleNavigateContact = () => {
    navigate('/contact');
  };

  const handleNavigateHelp = () => {
    navigate('/help');
  };

  const handleNavigateMonitor = () => {
    navigate('/monitor');
  };

  const handleNavigateHome = () => {
    navigate('/');
  };
 
    return (
      <AppBar position="static">
        <Toolbar>

    
<Box sx={{ display: 'flex', alignItems: 'center', flexGrow: 1 }}>
          <CleaningServicesIcon style={{ marginRight: '20px', height: '50px', width: '50px' }} />
          <Box onClick={handleNavigateHome} sx={{ cursor: 'pointer' }}>
            <Typography variant="h6" component="div">
              MailHarvest
            </Typography>
          </Box>
        </Box>
          <Button color="inherit"  sx={{ margin: '0 10px' }} onClick={handleNavigateScrape}>
            Scrape
          </Button>

          
          <Button color="inherit" sx={{ margin: '0 10px' }} onClick={handleNavigateContact}>
            Contact
          </Button>
          <Button color="inherit" sx={{ margin: '0 10px' }} onClick={handleNavigateMonitor}>
            Monitor
          </Button>
          <Button color="inherit" sx={{ margin: '0 10px' }} onClick={handleNavigateHelp}>
            Help
          </Button>
          {isAuthenticated ? (
          <Button color="inherit" sx={{ margin: '0 10px' }} onClick={handleLogOutClick}>
            Log Out
          </Button>
        ) : (
          <Button color="inherit" sx={{ margin: '0 10px' }} onClick={handleLogInClick}>
            Log In
          </Button>
        )}
        </Toolbar>
      </AppBar>
      );
}
export default Navigation;