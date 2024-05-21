import React from 'react';
import { Box } from '@mui/material';
import enablePpassPdf from './enable_ppass.pdf';

const Help = () => {
    return (
        <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
            minHeight="100vh"
            minWidth="100vw"
        >
            <embed src={enablePpassPdf} type="application/pdf" width="100%" height="100%" style={{ minHeight: 'calc(100vh - 64px)', minWidth: 'calc(100vw - 16px)' }} />
        </Box>
    );
};

export default Help;