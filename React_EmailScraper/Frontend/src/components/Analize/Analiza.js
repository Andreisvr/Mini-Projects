import React, { useEffect, useState } from 'react';
import Chart from 'chart.js/auto';
import LuniChart from './Luni';
import { Grid } from '@mui/material';
import AppContext from '../../pages/Test/Appcontext';
import MeniuAnalysis from './MeniuAnalysis';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import axios from 'axios';
import BasicLineChart from './bar';
import useAuth from '../../utils/Authentication';

function Analize() {
  const [pdfSent, setPdfSent] = useState(false);
  const { isAuthenticated, email } = useAuth();
  const currentDate = new Date().toLocaleDateString();
  const emailSubject = `Mailharvest: Charts PDF - ${currentDate}`;
  const emailBody = `Attached is the charts PDF for ${currentDate}.`;

  useEffect(() => {
    if (!Array.isArray(AppContext.fulldata)) return;

    function extractEmail(sender) {
      const emailRegex = /<([^>]+)>/;
      const match = sender.match(emailRegex);
      return match ? match[1] : '';
    }

    function drawChart(data) {
      const counts = {};

      data.forEach((row) => {
        const senderEmail = extractEmail(row.Sender);
        counts[senderEmail] = (counts[senderEmail] || 0) + 1;
      });

      const chartLabels = Object.keys(counts);
      const dataCounts = Object.values(counts);

      const chartData = {
        labels: chartLabels,
        datasets: [
          {
            label: 'Număr de mesaje trimise',
            data: dataCounts,
            backgroundColor: 'rgba(54, 162, 235, 0.5)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
          },
        ],
      };

      const ctx = document.getElementById('myChart').getContext('2d');

      if (window.myChart instanceof Chart) {
        window.myChart.destroy();
      }

      window.myChart = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
          scales: {
            y: {
              beginAtZero: true,
              precision: 0,
            },
          },
        },
      });
    }

    drawChart(AppContext.fulldata);

    const updateChartVisibility = () => {
      const myChartElement = document.getElementById('myChart');
      const luniChartElement = document.getElementById('luniChart');
      const hourChartElement = document.getElementById('myChart_line');
      
      if (AppContext.checkSender === 0 && AppContext.checkMonth === 0 && AppContext.checkHours === 0) {
        myChartElement.style.display = 'none';
        luniChartElement.style.display = 'none';
        hourChartElement.style.display = 'none';
        return; 
      }
      
      if (AppContext.checkSender === 1) {
        myChartElement.style.display = 'block';
      } else {
        myChartElement.style.display = 'none';
      }
    
      if (AppContext.checkMonth === 1) {
        luniChartElement.style.display = 'block';
      } else {
        luniChartElement.style.display = 'none';
      }

      if (AppContext.checkHours === 1) {
        hourChartElement.style.display = 'block';
      } else {
        hourChartElement.style.display = 'none';
      }

      if (AppContext.checkPdf === 1) {
        const pdf = new jsPDF();
        
        let chartsToExport = [];
    
        if (AppContext.checkSender === 1) {
            chartsToExport.push(document.getElementById('myChart'));
        }
    
        if (AppContext.checkMonth === 1) {
            chartsToExport.push(document.getElementById('luniChart'));
        }
        if (AppContext.checkHours === 1) {
            chartsToExport.push(document.getElementById('myChart_line'));
        }
        if (chartsToExport.length === 0) {
            AppContext.checkPdf = 0; 
            return;
        }
    
        const exportPromises = chartsToExport.map((chartElement) => {
            return html2canvas(chartElement).then((canvas) => {
                return canvas.toDataURL('image/png');
            });
        });
    
        Promise.all(exportPromises).then((imageDataArray) => {
            let positionY = 10;
    
            imageDataArray.forEach((imgData, index) => {
                const imgWidth = 180;
                const imgHeight = 100;
                pdf.addImage(imgData, 'PNG', 10, positionY, imgWidth, imgHeight);
    
                if (index < imageDataArray.length - 1) {
                  pdf.addPage();
                  positionY = 10;
              } else {
                  positionY += imgHeight + 10;
              }
          });
  
          pdf.save('charts.pdf');
          AppContext.checkPdf = 0;
          return;
      }).catch(error => {
          console.error("Eroare în generarea PDF-ului:", error);
          AppContext.checkPdf = 0;
      });
    }
  
    if (AppContext.checkStat === 1 && pdfSent === false) {
      const pdf = new jsPDF();
      const chartsToExport = [];
    
      if (AppContext.checkSender === 1) {
        chartsToExport.push(document.getElementById('myChart'));
      }
    
      if (AppContext.checkMonth === 1) {
        chartsToExport.push(document.getElementById('luniChart'));
      }
    
      if (AppContext.checkHours === 1) {
        chartsToExport.push(document.getElementById('myChart_line'));
      }
    
      const exportPromises = chartsToExport.map((chartElement) => {
        return html2canvas(chartElement).then((canvas) => {
          return canvas.toDataURL('image/png');
        });
      });
    
      Promise.all(exportPromises).then((imageDataArray) => {
        let positionY = 10;
        imageDataArray.forEach((imgData, index) => {
          const imgWidth = 180;
          const imgHeight = 100;
          pdf.addImage(imgData, 'PNG', 10, positionY, imgWidth, imgHeight);

          if (index < imageDataArray.length - 1) {
              pdf.addPage(); 
              positionY = 10; 
              positionY = 10; 
            } else {
                positionY += imgHeight + 10;
            }
            
          });
      
          const pdfData = pdf.output('blob');
      
          const formData = new FormData();
          formData.append('pdf', pdfData, 'charts.pdf');
          formData.append('to', email);
          formData.append('subject', emailSubject); 
          formData.append('body', emailBody);
      
          axios.post('http://localhost:8081/send-email', formData)
            .then(response => {
              alert('Email sent successfully');
              setPdfSent(true);
              AppContext.checkStat = 0;
            })
            .catch(error => {
              console.error('Error sending email:', error);
              alert('Failed to send email');
              AppContext.checkStat = 0;
            });
        });
      
        AppContext.checkStat = 0;
        setPdfSent(false);
      }
    };

    const visibilityInterval = setInterval(() => {
      updateChartVisibility();
    }, 500); 

    return () => clearInterval(visibilityInterval);
  }, [pdfSent, email]);

  return (
    <Grid container style={{ minHeight: '100vh', backgroundColor: '#e6f2ff', position: 'relative' }}>
      <Grid item xs={12} md={8} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', position: 'relative' }}>
        <div style={{ width: '70%' }}>
          <h3>Analiza mesajelor trimise</h3>
          <canvas id="myChart" width="400" height="200"></canvas>
          <div style={{ height: '10vh' }}></div>
          <div id="luniChart" style={{ display: 'none' }}>
            <LuniChart />
          </div>
          <div id="stack" style={{ display: 'block' }}>
            <BasicLineChart />
          </div>
        </div>
      </Grid>
      <Grid item xs={12} md={4} style={{ display: 'fixed', justifyContent: 'center', alignItems: 'center', paddingTop: '10vh' }}>
        <MeniuAnalysis />
      </Grid>
    </Grid>
  );
}

export default Analize;

