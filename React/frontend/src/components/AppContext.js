
import React, { createContext, useEffect, useState } from 'react';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [decodedToken, setDecodedToken] = useState(null);
    const [logined, setLogined] = useState(false);
    const [type, setType] = useState('');
    const [userInfo, setUserInfo] = useState(null); 
    const [program, setProgram] = useState(''); 
    const [faculty, setFaculty] = useState(''); 
    const [thesis_id , setIdThesis] = useState('');

    useEffect(() => {
        const storedName = localStorage.getItem('userName');
        const storedEmail = localStorage.getItem('userEmail');
        const storedLogined = localStorage.getItem('isLoggedIn') === 'true';
        const storedType = localStorage.getItem('userType'); 
        const storedUserInfo = localStorage.getItem('userInfo'); 
        const storedProgram = localStorage.getItem('userProgram'); 
        const storedFaculty = localStorage.getItem('userFaculty'); 
        
        if (storedLogined) {
            setName(storedName);
            setEmail(storedEmail);
            setLogined(true);
            setType(storedType);
            setProgram(storedProgram); 
            setFaculty(storedFaculty); 
            
            if (storedUserInfo) {
                setUserInfo(JSON.parse(storedUserInfo)); 
            }
        }
    }, []);
    const handleThesisId=(thesis_id)=>{
        setIdThesis(thesis_id);
        localStorage.setItem('thesis_id', thesis_id);
    }

    const handleLogin = (userName, userEmail, userType, userProgram, userFaculty) => {
        setName(userName);
        setEmail(userEmail);
        setLogined(true);
        setType(userType); 
        setProgram(userProgram);
        setFaculty(userFaculty); 

        localStorage.setItem('userName', userName);
        localStorage.setItem('userEmail', userEmail);
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('userType', userType);
        localStorage.setItem('userProgram', userProgram); 
        localStorage.setItem('userFaculty', userFaculty); 
    };

    const handleLogout = () => {
        setName('');
        setEmail('');
        setLogined(false);
        setDecodedToken(null);
        setType(''); 
        setUserInfo(null); 
        setProgram('');
        setFaculty('');

        localStorage.removeItem('userName');
        localStorage.removeItem('userEmail');
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('userType');
        localStorage.removeItem('userInfo'); 
        localStorage.removeItem('userProgram'); 
        localStorage.removeItem('userFaculty');
    };

    return (
        <AppContext.Provider value={{ 
            name, setName, 
            email, setEmail, 
            decodedToken, setDecodedToken, 
            logined, setLogined, 
            type, setType,  
            userInfo, 
            program, setProgram, 
            faculty, setFaculty, 
            handleLogin, 
            handleLogout,
            thesis_id,handleThesisId,
            
        }}>
            {children}
        </AppContext.Provider>
    );
};


