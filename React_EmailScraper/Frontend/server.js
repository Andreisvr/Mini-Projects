const express = require('express');
const multer = require('multer');
const nodemailer = require('nodemailer');
const cors = require('cors');
const app = express();
const port = 8081;

app.use(cors());

const storage = multer.memoryStorage();
const upload = multer({ storage: storage });

const transporter = nodemailer.createTransport({
    service: 'Gmail',
    auth: {
        user: process.env.MAIL,
        pass: process.env.PASS 
    }
});

app.post('/send-email', upload.single('pdf'), (req, res) => {
    const { to, subject, body } = req.body;
    const pdfAttachment = {
        filename: req.file.originalname,
        content: req.file.buffer
    };

    const mailOptions = {
        from: 'mailharvest38@gmail.com',////--------------Trebuie Harvest cu  
        to: to,
        subject: subject,
        text: body,
        attachments: [pdfAttachment]
    };

    transporter.sendMail(mailOptions, (error, info) => {
        if (error) {
            console.error('Error sending email:', error);
            res.status(500).json({ success: false, message: 'Failed to send email' });
        } else {
            console.log('Email sent:', info.response);
            res.status(200).json({ success: true, message: 'Email sent successfully' });
        }
    });
});

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
