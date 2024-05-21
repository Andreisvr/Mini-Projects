import React, { useState } from 'react';
import { Typography, Grid } from '@mui/material';
import EnhancedTable from '../../pages/Test/Table';
import useAuth from '../../utils/Authentication';

const ScrapeResults = ({ emailData, updateEmailData }) => {
  const { isAuthenticated } = useAuth();
  const [showAnalize, setShowAnalize] = useState(false);

  const handleToggleAnalize = () => {
    setShowAnalize(!showAnalize);
  };

  if (!isAuthenticated) {
    return (
      <Grid container justifyContent="center" alignItems="center" style={{ height: '100vh' }}>
        <Typography variant="h5">You must be logged in</Typography>
      </Grid>
    );
  }

  return (
    <div style={{ position: 'relative' }}>
      <EnhancedTable rows={emailData} updateEmailData={updateEmailData} />
     
    </div>
  );
};

export default ScrapeResults;