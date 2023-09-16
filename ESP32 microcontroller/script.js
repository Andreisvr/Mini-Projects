let button=1;
function ChangeButtonState() {
   
    get("/update?state=" + (button % 2));
    button++;
    let btn = document.getElementById("builtin-led-button");
    let root=document.querySelector(":root");

    if (button % 2 == 0) {
      // buton pornit
    

      root.style.setProperty("--perc-led",`100%`);
      btn.classList.remove("OFF");
      btn.classList.add("ON");
      document.querySelector('#builtin-led-button');
    } else {
      
      root.style.setProperty("--perc-led",`0%`);
      btn.classList.remove("On");
      btn.classList.add("Off");
    }
  }


 function PWM()
 {  
    let txt2= document.getElementById("E");
    let root=document.querySelector(":root");
    root.style.setProperty("--pwm-led",`${txt2.value}%`);
    get(`/pwm?pwm=${txt2.value}`);
 } 
  
  setInterval(PWM,100);


let response= fetch("/IP",{
  method: 'GET',
  headers: {
      'content-type': 'application/text'
  }
  
}).then (async(response)=>{
  let IPvalue= await response.text();
  console.log(IPvalue);
	var ws = new WebSocket(`ws://${IPvalue}/ws`);
  ws.onopen = function() 
	{
	   window.alert("Connected"); 
	};
	
	ws.onmessage = function(evt) 
	{
		console.log(evt.data);
   	let y = document.getElementById("x");
    let vr=evt.data.split(";");
		y.innerHTML=vr[0];
    
   let root=document.querySelector(":root");
   if(vr[0] > 1000) vr[0] = 1000;

   root.style.setProperty("--percentage-g",`${100 - vr[0] / 10}%`);
    let txt=document.getElementById("aed");
    ws.send(txt.value+'\n');

    addDataChart(vr[1],vr[2]);
  
	}	


  
	ws.close = function() 
	{
	   window.alert("Closed"); 
	};
})  


function addDataChart(param,param2)
{
  y2values=y2Values.shift(0);
  y2Values.push(param2);
  yvalues=yValues.shift(0);
  yValues.push(param);
  config.update();
}


	
async function get(url){
  const response = await fetch(url, {
      method: 'GET',
      headers: {
          'content-type': 'application/text'
      }

  });

  const responseData = await response.text();
  console.log(responseData);
  return responseData
}

async function post(url, body_txt){
  const response = await fetch(url, {
      method: 'POST',
      headers: {
          'content-type': 'application/text'
      },
  body: body_txt
  });

  const responseData = await response.text();

console.log(responseData);

  return responseData
}

//chart
var yValues = [];
var y2Values =[];
const xValues =[];
for(let i=0;i<20;i++)
 { xValues[i]=i;
  yValues[i]=i;
  y2Values[i]=i;
 }

const chartX = document.getElementById("chart").getContext("2d");
const config = new Chart(chartX, {
  type: "line",
  responsive:true,
  maintainAspectRatio:false,
  data: {
    lineThickness: 5,
    labels: xValues,
    
    datasets: [{
      label: "touch2",
      fill: false,
      lineTension: 0,
      borderColor: "red",
      data: yValues,
      color: "black",
      backgroundColor: yValues.map((value) => (value < 50 ? "red" : "pink")),
    },
    {
      label: 'ADC',
      data: y2Values,
      borderColor: "green",
      backgroundColor: "red",
      color:"black"
    }]
  },
  options:{
   
    scales:{
      y:{
        grid:{
          lineWidth:2,
          color :"black",
          fontSize: 20
        },
        ticks:{color:"black", font:{size:24, weight:"bold" }}
      },
      x:{
        grid:{
          lineWidth:2,
          color: "black"
          
        },
        ticks:{color:"black", font:{size:24,  weight:"bold" }},
        
      }
    }
  }
});
var cnt=0;
function changecolor()
{
  let b=document.body;
  let button=document.getElementById("mode");
cnt++;
  if(cnt%2!=0)
  {
  button.value=String.fromCodePoint(0x1F311);


   b.style.background="radial-gradient(circle, rgb(179, 159, 167) 38% , rgba(148,187,233,1) 100%)";
    }

    else{

 button.value=String.fromCodePoint(0x1F31E);
  b.style.background="radial-gradient(circle, rgb(179, 159, 167) 0%, rgb(12, 12, 12) 100%)";
}
console.log(cnt);
}
var x1=0;
function vis1()
{x1++;
  // let t=document.getElementById("builtin-led-button");
  let t=document.h2;
  if(x1%2!=0)
  t.style.display="none";
  else
  {
    t.style.display="block";
    t.style.marginLeft="auto";
    t.style.marginRight="auto";
    
  }
  
}


