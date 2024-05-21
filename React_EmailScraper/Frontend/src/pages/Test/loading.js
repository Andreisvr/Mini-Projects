import React, { useState, useEffect } from 'react';
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';
import { Typography } from '@mui/material';

const time = 10000;

export const ShowCircularWithTimeout = () => {
    const [showCircular, setShowCircular] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => {
            setShowCircular(false);
        }, time);

        return () => clearTimeout(timer);
    }, []);

    return (
        showCircular && (
            <Box sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', minHeight: '50vh' }}>
                <CircularProgress size={120} />
                {/* Adăugăm textul "Loading" sub obiectul circular de progres */}
                <Typography variant="body1" sx={{ marginTop: 2 }}>Loading</Typography>
            </Box>
        )
    );
};

const CircularIndeterminate = () => {
    return ShowCircularWithTimeout();
};

export default CircularIndeterminate;
