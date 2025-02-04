import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router";
import '/Users/Andrei_Sviridov/Desktop/React/frontend/src/page_css/Prof_role/addthesis.css';
import { AppContext } from '/Users/Andrei_Sviridov/Desktop/React/frontend/src/components/AppContext.js';

export default function AddThesis({ 
    thesisName, 
    faculty, 
    study_program, 
    description,
    requirements,
    date_start,
    date_end,
    professor_name,
    onWithdraw,
    viewType,
    onClick,
    id, 
    
}) { 
    
    
        
    const { name, email, logined, type } = useContext(AppContext);

    const [allAplies, setAllAplies] = useState([]);
    const [theses, setTheses] = useState([]); 
    
    const [allApplications, setAllApplications] = useState([]);
   
    const [match, setMatch] = useState(null); 

    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
   
 
  

    function handleMyAplication_delet(id) {
       
        fetch(`http://localhost:8081/delMyAplication/${id}`, { 
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        })
        .then(response => {
            if (!response.ok) throw new Error("Failed to withdraw thesis");
            setTheses(prevTheses => prevTheses.filter(thesis => thesis.id !== id));
        })
        .catch(error => console.error("Error withdrawing thesis:", error));
      
        window.location.reload();
    }

    function handleAplication_delet(id) {
       

        fetch(`http://localhost:8081/accept/${id}`, { 
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        })
        .then(response => {
            if (!response.ok) throw new Error("Failed to withdraw thesis");
            setTheses(prevTheses => prevTheses.filter(thesis => thesis.id !== id));
        })
        .catch(error => console.error("Error withdrawing thesis:", error));
       
        window.location.reload();
    }

    async function handleAcceptStudent(thesisId) {
        try {
            const userInfo = JSON.parse(localStorage.getItem('userInfo'));
            const studentId = userInfo.id;
            
            if (!studentId) {
                console.error("Student ID not found");
                return;
            }
            console.log(studentId);
    
            
            const response = await fetch(`http://localhost:8081/aplies/${studentId}`, {
                method: "GET",
                headers: { "Content-Type": "application/json" },
            });
    
            if (!response.ok) {
                throw new Error("Failed to fetch applications");
            }
    
            const data = await response.json();
            setAllAplies(data);  
    
          
            const matchedApplication = data.find(application => application.id === thesisId);
            
           
    
            if (!matchedApplication) {
                console.error("No matching application found for thesis id:", thesisId);
                return;
            }
    
          
          
            
            const acceptedApplicationData = {
                id_thesis: matchedApplication.id_thesis,
                faculty: matchedApplication.faculty,
                title: matchedApplication.title,
                id_prof: matchedApplication.id_prof,
                prof_name: matchedApplication.prof_name,
                prof_email: matchedApplication.prof_email,
                stud_id: matchedApplication.id_stud,
                stud_email: matchedApplication.stud_email,
                stud_name: matchedApplication.stud_name,
                stud_program: matchedApplication.student_program,
                date: new Date().toISOString().split('T')[0]
            };
    
            
    
        
            const acceptResponse = await fetch("http://localhost:8081/acceptedApplications", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(acceptedApplicationData)
            });
    
            if (!acceptResponse.ok) {
                throw new Error("Failed to accept application");
            }
    
            console.log("Application accepted successfully:", acceptedApplicationData);
    
           
             handleAplication_delet(thesisId);
    
        } catch (error) {
            console.error("Error in handleAcceptStudent:", error);
        }
    }
    
   
    function formatDate(isoDateString) {
        const date = new Date(isoDateString);
        if (date.getTime() === 0) return ''; 
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0'); 
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

   
    return (
        <form className="applied_form" onClick={onClick}>
            <p className="text title">Title: {thesisName}</p>
    
           
            
                    <p className="text">Faculty: {faculty} {study_program && `Program: ${study_program}`}</p>
                    <p className="text">Professor: {professor_name}</p>
                    <p className="text description">Description: {description}</p>
                    {requirements && (
                        <p className="text requirements">Requirements: {requirements}</p>
                    )}
                    <div className="add_date">
                        <p className="text">{formatDate(date_start)}</p>
                        <p className="text">{formatDate(date_end)}</p>
                    </div>
              
            
    
            
    
        
        </form>
    );
}    