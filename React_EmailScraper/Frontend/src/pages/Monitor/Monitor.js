import React, { useState } from 'react';
import { Grid, TextField, Button, Typography, Paper, CircularProgress, List, ListItem, ListItemText } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import useAuth from '../../utils/Authentication';
import axios from 'axios';
import { backendUrl } from '../../config/Config';

const Monitor = ({ callbackEmailData }) => {
    const { isAuthenticated } = useAuth();
    const [from, setFrom] = useState('');
    const [to, setTo] = useState('');
    const [subject, setSubject] = useState('');
    const [bodyContains, setBodyContains] = useState('');
    const [dateFrom, setDateFrom] = useState('');
    const [dateTo, setDateTo] = useState('');
    const [limit, setLimit] = useState('');
    const [loading, setLoading] = useState(false);
    const [results, setResults] = useState([]);

    const postDataWithFilters = async () => {
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
            const response = await axios.post(`${backendUrl}/api/getaccountfilters`, scrapeCriteria, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (response.data) {
                setResults(response.data);
                callbackEmailData(response.data);
            } else {
                console.error('Empty response data.');
            }
        } catch (error) {
            console.error('Error posting filters data:', error.response || error.message);
        } finally {
            setLoading(false);
        }
    };

    const transferFiltersToResults = () => {
        const filters = {
            from,
            to,
            subject,
            bodyContains,
            dateFrom,
            dateTo,
            limit
        };
        setResults([filters]);
    };

    return (
        <Grid container spacing={2}>
            <Grid item xs={12} sm={4}>
                <Paper elevation={3} sx={{ height: 'auto', padding: 1.90, display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                    <Typography variant="h6" sx={{ marginBottom: 1 }}>MailHarvest Filters</Typography>
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
                        onClick={postDataWithFilters}
                        sx={{ marginBottom: 1 }}
                    >
                        {loading ? (
                            <CircularProgress size={24} color="inherit" />
                        ) : (
                            "Apply Filters"
                        )}
                    </Button>
                    <Button variant="contained" fullWidth onClick={transferFiltersToResults}>Transfer Filters to Results</Button>
                </Paper>
            </Grid>
            <Grid item xs={12} sm={8}>
                <Paper elevation={3} sx={{ height: 'auto', padding: 1.90 }}>
                    <Typography variant="h6" sx={{ marginBottom: 1 }}>Results</Typography>
                    <List>
                        {results.map((result, index) => (
                            <ListItem key={index}>
                                {/* Afiseaza valorile din filtre ca si rezultate */}
                                <ListItemText primary={`From: ${result.from}, To: ${result.to}, Subject: ${result.subject}, Body Contains: ${result.bodyContains}, Date From: ${result.dateFrom}, Date To: ${result.dateTo}, Limit: ${result.limit}`} />
                            </ListItem>
                        ))}
                    </List>
                </Paper>
            </Grid>
        </Grid>
    );
};

export default Monitor;


