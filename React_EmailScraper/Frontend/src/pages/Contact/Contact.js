import React, { useState } from 'react';
import { Navigate } from 'react-router-dom';
import SendIcon from '@mui/icons-material/Send';
import Button from '@mui/material/Button';
import styled from '@emotion/styled';
import { TextField } from '@mui/material';
import emailjs from 'emailjs-com';
import useAuth from '../../utils/Authentication';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';

const Container = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-color: #e6f2ff;
`;

const FormContainer = styled.div`
    background-color: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    width: 100%;
`;

const Title = styled.h1`
    margin-bottom: 20px;
    text-align: center;
`;

const SendButton = styled(Button)`
    width: 100%;
    margin-top: 20px;
    background-color: green;
    color: white;
    &:hover {
        background-color: darkgreen;
    }
`;

const SuccessMessage = styled.p`
    color: green;
    text-align: center;
`;

const Contact = () =>{
    const [emailSent, setEmailSent] = useState(false);
    const { isAuthenticated } = useAuth();

    const sendEmail = (e) => {
        e.preventDefault();

        emailjs.sendForm(process.env.REACT_APP_SERVICE_ID, process.env.REACT_APP_TEMPLATE_ID, e.target, process.env.REACT_APP_PUBLIC_ID)
            .then((result) => {
                console.log(result.text);
                setEmailSent(true);
            })
            .catch((error) => {
                console.log(error.text);
            });
    };

    if (emailSent) {
        return <Navigate to="/scrape" />;
    }

    return isAuthenticated ? (
        <Container >
            <FormContainer>
                <Title>Contact Us</Title>
                <form onSubmit={sendEmail}>
                    <TextField label="Message" name="message" multiline rows={4} required sx={{ width: '100%', marginBottom: '20px' }} />
                    <SendButton type="submit" variant="contained" endIcon={<SendIcon />} sx={{ width: '100%' }}>
                        Send
                    </SendButton>
                </form>
                {emailSent && <SuccessMessage>Email sent successfully!</SuccessMessage>}
            </FormContainer>
        </Container>
    ): (
        <Grid container justifyContent="center" alignItems="center" style={{ height: '100vh', backgroundColor: '#e6f2ff' }}>
        <Grid item xs={10} sm={6}>
            <Paper elevation={3} sx={{ height: '40vh', width: '63vh', padding: '30px', display: 'flex', alignItems: 'center', justifyContent: 'center', margin: 'auto' }}>
                <Typography variant="h5">You must be logged in</Typography>
            </Paper>
        </Grid>
    </Grid>
    
    );
};

export default Contact;
