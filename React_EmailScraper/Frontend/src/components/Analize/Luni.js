import React, { useEffect } from 'react';
import Chart from 'chart.js/auto';
import AppContext from '../../pages/Test/Appcontext';


function Luni_chart() {
  useEffect(() => {
    function drawChart(data) {
      const counts = {};

      if (!Array.isArray(data)) return;

      data.forEach((row) => {
        const date = new Date(row.Date);
        const month = date.toLocaleString('default', { month: 'short' });
        counts[month] = (counts[month] || 0) + 1;
      });

      const labels = Object.keys(counts);
      const dataCounts = Object.values(counts);

      const chartData = {
        labels: labels,
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

      const ctx = document.getElementById('myChart_luni').getContext('2d');

      if (window.myChart_luni instanceof Chart) {
        window.myChart_luni.destroy();
      }

      window.myChart_luni = new Chart(ctx, {
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
  }, [AppContext.fulldata]);

  return (
    <div>
      <canvas id="myChart_luni" width="400" height="200"></canvas>
      <div style={{ height: '20vh' }}></div>
    </div>
  );
}

export default Luni_chart;
