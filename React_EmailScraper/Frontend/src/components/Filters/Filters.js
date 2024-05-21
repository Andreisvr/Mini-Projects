import { Grid, TextField, Button, Typography, Paper, CircularProgress } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import DownloadIcon from '@mui/icons-material/Download';
import useAuth from '../../utils/Authentication';
import axios from 'axios';
import { backendUrl } from '../../config/Config';
import { useState } from 'react';

const Filters = ({ callbackEmailData }) => {
    const { isAuthenticated } = useAuth();
    const [from, setFrom] = useState('');
    const [to, setTo] = useState('');
    const [subject, setSubject] = useState('');
    const [bodyContains, setBodyContains] = useState('');
    const [dateFrom, setDateFrom] = useState('');
    const [dateTo, setDateTo] = useState('');
    const [limit, setLimit] = useState('');
    const [loading, setLoading] = useState(false);

    
    const handleSendScrapeCriteria = async () => {
        setLoading(true);
    
        const scrapeCriteria = {
            from,
            to,
            subject,
            bodyContains,
            dateFrom,
            dateTo,
            limit
        };
    
        const token = localStorage.getItem('token');
    
        try {
            const response = await axios.post(`${backendUrl}/api/scrape`, scrapeCriteria, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
    
            if (response.data) {
                callbackEmailData(response.data);
            } else {
                console.error('Empty response data.');
            }
        } catch (error) {
            console.error('Error posting scrape criteria:', error.response || error.message);
        } finally {
            setLoading(false);
        }
    };


    const downloadCSV = async () => {
        const token = localStorage.getItem('token');
    
        try {
            const response = await axios.get(`${backendUrl}/api/download/csv`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                responseType: 'blob' 
            });
    
            const url = window.URL.createObjectURL(new Blob([response.data]));
    
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'emails.csv');
            document.body.appendChild(link);
            link.click();
    
            window.URL.revokeObjectURL(url);
        } catch (error) {
            console.error('Error downloading CSV:', error.response || error.message);
        }
    };

    return isAuthenticated ? (
        <Grid item xs={12} sm={8}>
            <Paper elevation={3} sx={{ height: 'auto', padding: 1.90, display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                <Typography variant="h6" sx={{ marginBottom: 1 }}>MailHarvest</Typography>
                <TextField fullWidth label="From:" variant="outlined" margin="normal" value={from} onChange={(e) => setFrom(e.target.value)} />
                <TextField fullWidth label="To:" variant="outlined" margin="normal" value={to} onChange={(e) => setTo(e.target.value)} />
                <TextField fullWidth label="Subject:" variant="outlined" margin="normal" value={subject} onChange={(e) => setSubject(e.target.value)} />
                <TextField fullWidth label="Body contains words:" variant="outlined" margin="normal" value={bodyContains} onChange={(e) => setBodyContains(e.target.value)} />
                <TextField fullWidth label="Limit Results:" variant="outlined" margin="normal" type="number" value={limit} onChange={(e) => setLimit(e.target.value)} InputProps={{ inputProps: { min: 1 } }} />
                <Grid container spacing={2} marginY={2}>
                    <Grid item xs={6}>
                        <TextField
                            fullWidth
                            label="Date from:"
                            type="date"
                            variant="outlined"
                            value={dateFrom}
                            onChange={(e) => setDateFrom(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            fullWidth
                            label="Date to:"
                            type="date"
                            variant="outlined"
                            value={dateTo}
                            onChange={(e) => setDateTo(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </Grid>
                </Grid>
                <Button
                    variant="contained"
                    fullWidth
                    startIcon={<SearchIcon />}
                    onClick={handleSendScrapeCriteria}
                    sx={{ marginBottom: 1 }}
                >
                    {loading ? (
                        <CircularProgress size={24} color="inherit" />
                    ) : (
                        "Scrape"
                    )}
                </Button>
                <Button variant="contained" fullWidth startIcon={<DownloadIcon />} onClick={downloadCSV} sx={{ marginBottom: 1 }}>Download CSV</Button>
            </Paper>
        </Grid>
    ) : (
        <Grid item xs={12} sm={6}>
            <Paper elevation={3} sx={{ height: '73vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <Typography variant="h5">You must be logged in</Typography>
            </Paper>
        </Grid>
    );
};

export default Filters;
