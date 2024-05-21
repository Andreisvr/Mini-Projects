import React, { useState } from 'react';
import { Grid, Typography, Paper, Checkbox, Button } from '@mui/material';
import DownloadIcon from '@mui/icons-material/Download';
import AppContext from '../../pages/Test/Appcontext';
import EmailIcon from '@mui/icons-material/Email';

const MeniuAnalysis = () => {
    const [showFromSender, setShowFromSender] = useState(true);
    const [showMonth, setShowMonth] = useState(false);
    const [showHours, setShowHours] = useState(false);
    const [sendButtonDisabled, setSendButtonDisabled] = useState(false); // Starea pentru a dezactiva butonul "Send Statistic to Email"

    const handleShowFromSenderChange = (e) => {
        const isChecked = e.target.checked;
        setShowFromSender(isChecked);
        AppContext.checkSender = isChecked ? 1 : 0;
    };

    const handleShowMonthChange = (e) => {
        const isChecked = e.target.checked;
        setShowMonth(isChecked);
        AppContext.checkMonth = isChecked ? 1 : 0;
    };

    const handleShowHours = (e) => {
        const isChecked = e.target.checked;
        setShowHours(isChecked);
        AppContext.checkHours = isChecked ? 1 : 0;
    };

    const downloadStatistic = () => {
        AppContext.checkPdf = 1;
    };

    const sendStatistic = () => {
        if (!sendButtonDisabled) {
            setSendButtonDisabled(true); // Dezactivăm butonul
            AppContext.checkStat = 1;
            setTimeout(() => {
                setSendButtonDisabled(false); // Re-activăm butonul după 3 secunde
            }, 5000);
        }
    };

    return (
        <Grid item xs={12} sm={8}>
            <Paper elevation={3} sx={{ height: 'auto', padding: 1.90, display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                <Typography variant="h6" sx={{ marginBottom: 1 }}>MailHarvest</Typography>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <Checkbox
                        checked={showFromSender}
                        onChange={handleShowFromSenderChange}
                        color="primary"
                    />
                    <Typography variant="body1" sx={{ marginRight: 'auto' }}>Show from sender</Typography>
                </div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <Checkbox
                        checked={showMonth}
                        onChange={handleShowMonthChange}
                        color="primary"
                    />
                    <Typography variant="body1" sx={{ marginRight: 'auto' }}>Show from Months</Typography>
                </div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <Checkbox
                        checked={showHours}
                        onChange={handleShowHours}
                        color="primary"
                    />
                    <Typography variant="body1" sx={{ marginRight: 'auto' }}>Show Activity Hours</Typography>
                </div>
                <Button
                    variant="contained"
                    fullWidth
                    startIcon={<DownloadIcon />}
                    onClick={downloadStatistic}
                    sx={{ marginBottom: 1 }}
                >
                    Download Statistic
                </Button>
                <Button
                    variant="contained"
                    fullWidth
                    startIcon={<EmailIcon />}
                    onClick={sendStatistic}
                    disabled={sendButtonDisabled} // Butonul este dezactivat atunci când sendButtonDisabled este true
                    sx={{ marginBottom: 1 }}
                >
                    Send Statistic to Email
                </Button>
            </Paper>
        </Grid>
    );
};

export default MeniuAnalysis;
