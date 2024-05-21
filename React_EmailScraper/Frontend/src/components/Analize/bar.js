import React, { useEffect } from 'react';
import Chart from 'chart.js/auto';
import AppContext from '../../pages/Test/Appcontext';

export default function BasicLineChart() {
  useEffect(() => {
    function drawLineChart(data) {
      const messageCountPerHour = {};

      if (!Array.isArray(data)) return;

      data.forEach((message) => {
        const hour = new Date(message.Date).getHours();
        if (hour >= 6) { // Exclude hours before 6
          messageCountPerHour[hour] = (messageCountPerHour[hour] || 0) + 1;
        }
      });

      const labels = Object.keys(messageCountPerHour);
      const dataCounts = Object.values(messageCountPerHour);

      const chartData = {
        labels: labels,
        datasets: [
          {
            label: 'Număr de mesaje trimise pe oră',
            data: dataCounts,
            backgroundColor: 'rgba(54, 162, 235, 0.5)', 
            borderColor: 'rgba(54, 162, 235, 1)', 
            borderWidth: 1,
            borderRadius: 20, // Setează raza de rotunjire a colțurilor
          },
        ],
      };

      const ctx = document.getElementById('myChart_line').getContext('2d');

      if (window.myChart_line instanceof Chart) {
        window.myChart_line.destroy();
      }

      window.myChart_line = new Chart(ctx, {
        type: 'line',
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

    drawLineChart(AppContext.fulldata);
  }, []);

  return (
    <div>
      <canvas id="myChart_line" width="400" height="200"></canvas>
      <div style={{ height: '20vh' }}>
       
      </div>
    </div>
  );
}
