
import React from 'react';

import backgroundImage from '../../components/images/background.jpg';
const ImageWithText = ({ imageSrc, text }) => {
    return (
        <div style={{ position: 'relative', width: '100%' }}>
            <img src={backgroundImage} alt="Background" style={{ width: '100%' }} />
            <div style={{ position: 'absolute', top: '50%', right: '10%', transform: 'translateY(-50%)', color: 'white', fontSize: '64px' }}>
                {text}
            </div>
        </div>
    );
};

export default ImageWithText;
