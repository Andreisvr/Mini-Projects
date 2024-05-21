import React from 'react';
import { Grid } from '@mui/material';
import EnhancedTable from './Table';
import backgroundImage from '../../img/background.jpg'
import handleClick from './Buttons_Logic'; // Import the handleClick function
import IconMenu from './Meniu'
import Analize from './Analiza'; // Import the Analize component

const Scrape = () => {
    const [emailData, setEmailData] = useState([]);

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
        } else {
        }
      };
      
      const updateEmailData = (updatedEmailData) => {
        setEmailData(updatedEmailData);
      };

    return (
        <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
            style={{ minHeight: '100vh' }}
        >
            <div style={{ position: 'relative', width: '100%' }}>
                <img src={backgroundImage} alt="Background" style={{ width: '100%', height: '85vh' }} />
                <div style={{ position: 'absolute', top: '28%', left: '10%', transform: 'translateY(-50%)', color: 'black', fontSize: '74px' }}>
                    {"MailHarvest "}
                </div>
                <button onClick={handleClick} className="go_down" style={{ position: 'absolute', top: '45%', left: '28%', transform: 'translateX(-50%)', backgroundColor: 'transparent', border: '1px solid gray', color: 'black', fontSize: '30px', padding: '10px 20px', cursor: 'pointer' }}>
                    Click me
                </button>
                <div style={{ height: '20vh' }}></div>
            </div>

            {/* Container for table and icon menu */}
            <Grid item xs={12} sm={6} style={{ position: 'relative', marginTop: '20px' }}>
                {/* Icon menu */}
                <div style={{ position: 'absolute', top: '28%', left: '101%', transform: 'translateY(-50%)' }}>
                    <IconMenu></IconMenu>
                </div>
                {/* Table */}
                <EnhancedTable />
                
                {/* Add the Analize component below the table */}
            </Grid>

            <div style={{ height: '20vh' }}></div>
            
            <Grid item xs={12} style={{ backgroundColor: 'lightgray', padding: '20px', textAlign: 'center' }}>
                'Footer content goes here'
            </Grid>
        </Grid>
    );
};

export default Scrape;
