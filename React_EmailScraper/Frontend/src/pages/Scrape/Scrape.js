import React, { useState } from 'react';
import { Grid, Button } from '@mui/material';
import Filters from '../../components/Filters/Filters';
import ScrapeResults from '../../components/ScrapeResults/ScrapeResults';
import Analize from '../../components/Analize/Analiza';
import useAuth from '../../utils/Authentication';

const Scrape = () => {
  const [emailData, setEmailData] = useState([]);
  const [showAnalize, setShowAnalize] = useState(false);
  const [buttonPosition, setButtonPosition] = useState({ bottom: '30px', left: '33.5%', transform: 'translateX(-50%)' });
  const { isAuthenticated } = useAuth();

  const handleToggleAnalize = () => {
    setShowAnalize(!showAnalize);
    if (!showAnalize) {
      setButtonPosition({ top: '10px', left: '50%', transform: 'translateX(-50%)' });
    } else {
      setButtonPosition({ bottom: '30px', left: '33.5%', transform: 'translateX(-50%)' });
    }
  };

  const handleSendDataToTable = (data) => {
    if (data.emails) {
      const parsedData = JSON.parse(data.emails);
      const formattedData = parsedData.map((email, index) => ({
        id: index + 1,
        Sender: email.sender,
        Date: email.date,
        Subject: email.subject,
        Body: email.body
      }));
      setEmailData(formattedData);
    }
  };

  const updateEmailData = (updatedEmailData) => {
    setEmailData(updatedEmailData);
  };

  return (
    <Grid container style={{ minHeight: '100vh', backgroundColor: '#e6f2ff', position: 'relative' }}>
      <Grid item xs={12} md={8} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', position: 'relative' }}>
        {!showAnalize && (
          <ScrapeResults emailData={emailData} updateEmailData={updateEmailData} />
        )}
      </Grid>
      <Grid item xs={12} md={4} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', paddingTop: '5px' }}>
        {!showAnalize && <Filters callbackEmailData={handleSendDataToTable} />}
      </Grid>
      {showAnalize && <Analize />}
      {isAuthenticated && (
        <div style={{ position: 'absolute', ...buttonPosition }}>
          <Button onClick={handleToggleAnalize} variant="outlined" style={{ fontSize: '16px', zIndex: 1 }}>
            {showAnalize ? 'Hide Analysis' : 'Show Analysis'}
          </Button>
        </div>
      )}
    </Grid>
  );
}

export default Scrape;