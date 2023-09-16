#include "WiFi.h"
#include "ESPAsyncWebServer.h"
#include <AsyncTCP.h>
#include "SPIFFS.h"
#include <driver/mcpwm.h>
#include <driver/gpio.h>
#include <soc/mcpwm_reg.h>
#include <soc/soc.h>
#include <soc/mcpwm_struct.h>
#include <Arduino_JSON.h>
#include "soc/touch_sensor_channel.h"
#include "driver/touch_sensor.h"
#include "esp32-hal-touch.h"
#include <driver/adc.h>
#include <driver/dac.h>

int result=0, pressed=1,i=0;

const mcpwm_config_t time1 = {62000, 50, 1, MCPWM_DUTY_MODE_0, MCPWM_UP_COUNTER};
AsyncWebSocket ws("/ws");
AsyncWebSocketClient * globalClient = NULL;
AsyncWebServer server(80);

uint16_t touchValue=0,touch2=0;
bool led_state = false;

const char* ssid = "LEAGOO S8";
const char* password = "11111111";

String a;
int c=0,cnt=0;

 void initSPIFFS() {
  if (!SPIFFS.begin()) {
    Serial.println("An error has occurred while mounting SPIFFS");
  }
  Serial.println("SPIFFS mounted successfully");
}



void onWsEvent(AsyncWebSocket * server, AsyncWebSocketClient * client, AwsEventType type, void * arg, uint8_t *data, size_t len)
{
    if(type == WS_EVT_CONNECT)
    {
     
      
      Serial.println("Websocket client connection received");
      globalClient = client;
    } 
    else if(type == WS_EVT_DATA)
    {for (size_t i = 0; i < len; i++) {
      Serial.write(data[i]);
    
      String dataStr = String((char)data[i]);
      
    if (dataStr.equals("1"))
            mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 10);
   if (dataStr.equals("2"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 20);
     if (dataStr.equals("3"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 30);
     if (dataStr.equals("4"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 40);
     if (dataStr.equals("5"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 50);
     if (dataStr.equals("6"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 60);
     if (dataStr.equals("7"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 70);
     if (dataStr.equals("8"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 80);
     if (dataStr.equals("9"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 90);
     if (dataStr.equals("s"))
     mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, 100);
     

    }
    }
    else if(type == WS_EVT_DISCONNECT)
    {
      Serial.println("Websocket client connection finished");
      globalClient = NULL;
    }
}


//----------------------------------------------------------------------


 void socket(int &cnt)
 {
    if(globalClient != NULL && globalClient->status() == WS_CONNECTED)
   {   
    touch_pad_read_raw_data(TOUCH_PAD_GPIO13_CHANNEL, &touchValue);
touch_pad_read_raw_data(TOUCH_PAD_GPIO4_CHANNEL, &touch2);

 //dac_output_voltage(DAC_CHANNEL_1, i);
 
 if(cnt<=c)
 dac_output_voltage(DAC_CHANNEL_1,255),cnt+=10;
 else if(cnt>c&&cnt<100)
 dac_output_voltage(DAC_CHANNEL_1,0),cnt+=10;
 else cnt=0;
 
  result= adc1_get_raw(ADC1_CHANNEL_5);
globalClient->text((String)touchValue+";"+(String)touch2+";"+(String)result);
    
    
   }
 }

 //-----------------------------------------
void setup(){
  Serial.begin(115200);
  
  initSPIFFS();
  
    

  WiFi.begin(ssid, password);
  server.addHandler(&ws);
  ws.onEvent(onWsEvent);
    
  //touch pad setup
  gpio_set_direction(GPIO_NUM_2, GPIO_MODE_OUTPUT );
  gpio_set_direction(GPIO_NUM_27, GPIO_MODE_OUTPUT );

  touch_pad_init();
  touch_pad_config(TOUCH_PAD_GPIO13_CHANNEL, -1);
  touch_pad_config(TOUCH_PAD_GPIO4_CHANNEL, -1);
  touch_pad_set_voltage(TOUCH_HVOLT_2V7,TOUCH_LVOLT_0V5,TOUCH_HVOLT_ATTEN_1V);
  touch_pad_set_fsm_mode(TOUCH_FSM_MODE_SW);
  touch_pad_filter_start(10);
  touch_pad_sw_start();
  //pwm setup
  
  mcpwm_gpio_init(MCPWM_UNIT_0,MCPWM0A, 2);
  mcpwm_init(MCPWM_UNIT_0, MCPWM_TIMER_0, &time1);
  mcpwm_deadtime_enable(MCPWM_UNIT_0,MCPWM_TIMER_0,MCPWM_ACTIVE_HIGH_COMPLIMENT_MODE,1,1);
  mcpwm_start(MCPWM_UNIT_0, MCPWM_TIMER_0);
  adc1_config_width(ADC_WIDTH_BIT_9);
  adc1_config_channel_atten(ADC1_CHANNEL_5, ADC_ATTEN_DB_11);
  dac_output_enable(DAC_CHANNEL_1);


  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println(WiFi.localIP());
  String IPv;
  for(int i=0;i<4;i++)
  {
  IPv+=String(WiFi.localIP()[i]);
  if(i<3)
  IPv+=".";
  }
  
  Serial.println(IPv);
  
  server.on("/IP", HTTP_GET, [IPv](AsyncWebServerRequest *request){
    request->send(200, "/text.plain", IPv);});
    
     server.on("/t_site.html", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/t_site.html", "text/html");});
    
     
     server.on("/index.html", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/index.html", "text/html");});
    
     server.on("/esp32.png", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/esp32.png", "image/png");});
    
         server.on("/chart.js", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/chart.js", "application/javascript");});
    
  server.on("/s.css",HTTP_GET,[](AsyncWebServerRequest *request){
      request->send(SPIFFS,"/s.css","text/css");});  
      
      server.on("/led2",HTTP_GET,[](AsyncWebServerRequest *request){
      request->send(200);});  
      
  server.on("/script.js",HTTP_GET,[](AsyncWebServerRequest *request){
      request->send(SPIFFS,"/script.js","application/javascript");});

 server.on("/builtin-led-button",HTTP_GET,[](AsyncWebServerRequest *request){
      request->send(200);});

      
server.on("/text",HTTP_GET,[](AsyncWebServerRequest *request){
      request->send(SPIFFS,"/script.js","application/javascript");
      String T;
      
      if(request->hasParam("text"))
    {
      T= request ->getParam("text")->value();
       
      Serial.println(T);
    request->send(200, "text/plain", "da");
    }
    else
    
    request->send(200, "text/plain", "nu");
      });
    
server.on("/update", HTTP_GET, [](AsyncWebServerRequest *request){
    String param;
    
    if(request->hasParam("state"))
    {
      param= request ->getParam("state")->value();
      led_state = param.toInt()==0?false:true;
    request->send(200, "text/plain", "da");
    }
    else
    request->send(200, "text/plain", "nu");});


 
  
server.on("/pwm", HTTP_GET, [](AsyncWebServerRequest *request){
    String param;
    if(request->hasParam("pwm"))
    {
      param= request ->getParam("pwm")->value();
  
      c =param.toInt();
      if (c>100)
      c=100;
      if(c<0)
      c=0;
      
    request->send(200, "text/plain", "da");
    }
    else
    request->send(200, "text/plain", "nu");});

 
    server.on("/action",HTTP_POST, [](AsyncWebServerRequest * request){}, 
    NULL, [](AsyncWebServerRequest * request, uint8_t *data, size_t len, size_t index, size_t total){
      
    int params = request->params();
    
    for (int i = 0; i < params; i++) {
      AsyncWebParameter* p = request->getParam(i);
      Serial.printf("POST[%s]: %s\n", p->name().c_str(), p->value().c_str());
    }
      for (size_t i = 0; i < len; i++){
        Serial.write(data[i]);
      }
    Serial.println();
    request->send(200);
    });
server.on("/Second.html", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/Second.html", "text/html");});
    
    server.on("/sec.css", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/sec.css", "text/css");});
    
    server.on("/sec.js", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/sec.js", "aplication/javascript");});
    
    server.on("/esp.png", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send(SPIFFS, "/esp.png", "image/png");});
    
    
  server.begin();
}



void loop(){
  gpio_set_level(GPIO_NUM_27,led_state);
  Serial.println(led_state);
  Serial.println(c);
    mcpwm_set_duty(MCPWM_UNIT_0, MCPWM_TIMER_0, MCPWM_OPR_A, c);
    
  i+=5;
  Serial.println(i);
   if (i>=255)
  {i=0;}

socket(cnt);
delay(500);
  }




  
