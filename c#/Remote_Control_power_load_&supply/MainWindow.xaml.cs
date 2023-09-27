using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

using RohdeSchwarz.RsHmc804x;
using System.Timers;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace Control_Rohde_Schwarz
{
    public partial class MainWindow : System.Windows.Window
    {
        RsHmc804x driver;
       
        int master_click = -1;
        int ch1_click = -1;
        int ch2_click = -1;
        int ch3_click = -1;
        string ch1_volt;
        string ch1_am;

        string ch2_volt;
        string ch2_am;

        string ch3_volt;
        string ch3_am;

        public MainWindow()
        {
            InitializeComponent();
            //Before running, change the resource name to fit your device:
            //For LAN interface use TCPIP::<IP_Address>::INSTR e.g. TCPIP::10.64.1.36::INSTR
            //For GPIB interface use the GPIB::<Address>::INSTR e.g. GPIB::28::INSTR
            //For USB-TMC interface use the USB::0x0AAD::<PID>::<SERIAL_NUMBER>::INSTR e.g. USB::0x0AAD::0x0197::100433::INSTR
            try
            {
                driver = new RsHmc804x("USB0::0x0AAD::0x0135::029601173::INSTR", true, true, "Simulate=False");
               
            }
                //in case of the initializing error the driver throws an Ivi.Driver.IOException
            catch (Ivi.Driver.IOException e)
            {
             
               
                 return;
            }

            System.Timers.Timer timer = new System.Timers.Timer();
            timer.Elapsed += new ElapsedEventHandler(realTimeUpdate);
            timer.Interval = 100; //miliseconds
            timer.Enabled = true;
            
        }
       
        private void MASTER_ON_Click(object sender, RoutedEventArgs e)
        {
           
            master_click = master_click * (-1);
            // Master output OFF
            if (master_click > 0)
            {
                driver.Outputs.MasterEnabled = true;
                SolidColorBrush redBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0x00, 0x00));

            
            MASTER_ON.Background = redBrush;

            }
            else
            {
                driver.Outputs.MasterEnabled = false;
                SolidColorBrush grayBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0xE5, 0xE5));

               
                MASTER_ON.Background = grayBrush;
            }
           
        }

        private void CH1_btn_on_Click(object sender, RoutedEventArgs e)
        {
            ch1_click = ch1_click * (-1);
            ch1_volt = CH1_V1.Text;
            ch1_am = CH1_V2.Text;
            try
            {
                driver.Outputs.SelectedChannel = 1;



                // Încercați să convertiți valoarea într-un număr întreg
                if (float.TryParse(ch1_volt, out float ch1_volt_float) && float.TryParse(ch1_am, out float ch1_am_float))
                {
                    // Verificați dacă numărul este între 0 și 5
                    if (ch1_volt_float > 0 && ch1_volt_float <= 9 && ch1_am_float > 0 && ch1_am_float <= 4)
                    {
                        // Valoarea introdusă este o cifră între 0 și 5
                        driver.Outputs.VoltageLevel = ch1_volt_float;
                        driver.Outputs.CurrentLimit = ch1_am_float;
                        driver.Outputs.Ovp.Limit = 5;
                        driver.Outputs.Ovp.Enabled = false;
                        if (ch1_click > 0)
                        {
                            driver.Outputs.ChannelOnlyEnabled = true;
                            SolidColorBrush redBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0x00, 0x00));



                            CH1_btn_on.Background = redBrush;
                        }
                        else
                        {
                            driver.Outputs.ChannelOnlyEnabled = false;
                            SolidColorBrush grayBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0xE5, 0xE5));
                            CH1_btn_on.Background = grayBrush;
                        }
                    }
                    else
                    {
                        // Valoarea introdusă nu este între 0 și 5
                        System.Windows.MessageBox.Show("nu este valoare valida  mai mare ca 5");
                    }
                }
                else
                {
                    // Valoarea introdusă nu este o cifră validă
                    System.Windows.MessageBox.Show("nu este valoare valida");
                }
            }catch (Exception ex) { System.Windows.MessageBox.Show("numerge"); }

          
        }

        private void CH2_btn_on_Click(object sender, RoutedEventArgs e)
        {

            ch2_click = ch2_click * (-1);
            ch2_volt = CH2_V1.Text;
            ch2_am = CH2_V2.Text;
            try
            {
                driver.Outputs.SelectedChannel = 2;

                // Încercați să convertiți valoarea într-un număr întreg
                if (float.TryParse(ch2_volt, out float ch2_volt_float) && float.TryParse(ch2_am, out float ch2_am_float))
                {
                    // Verificați dacă numărul este între 0 și 5
                    if (ch2_volt_float > 0 && ch2_volt_float <= 9 && ch2_am_float > 0 && ch2_am_float <= 4)
                    {

                        driver.Outputs.VoltageLevel = ch2_volt_float;
                        driver.Outputs.CurrentLimit = ch2_am_float;

                        driver.Outputs.Ovp.Limit = 5;
                        driver.Outputs.Ovp.Enabled = false;
                        if (ch2_click > 0)
                        {
                            driver.Outputs.ChannelOnlyEnabled = true;
                            SolidColorBrush redBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0x00, 0x00));

                            // Set the Background property of the UI element (e.g., a Window or a Button)

                            CH2_btn_on.Background = redBrush;
                        }
                        else
                        {
                            driver.Outputs.ChannelOnlyEnabled = false;
                            SolidColorBrush grayBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0xE5, 0xE5));

                            // Set the Background property of the UI element (e.g., a Window or a Button)
                            CH2_btn_on.Background = grayBrush;
                        }

                    }
                    else
                    {
                        // Valoarea introdusă nu este între 0 și 5
                        System.Windows.MessageBox.Show("nu este valoare valida  mai mare ca 5");
                    }
                }
                else
                {
                    // Valoarea introdusă nu este o cifră validă
                    System.Windows.MessageBox.Show("nu este valoare valida");
                }

            }catch (Exception ex) { System.Windows.MessageBox.Show(ex.Message); }  
        }

        private void CH3_btn_on_Click(object sender, RoutedEventArgs e)
        {
            ch3_click = ch3_click * (-1);
            ch3_volt = CH3_V1.Text;
            ch3_am = CH3_V2.Text;

            driver.Outputs.SelectedChannel = 3;

            // Încercați să convertiți valoarea într-un număr întreg
            if (float.TryParse(ch3_volt, out float ch3_volt_float) && float.TryParse(ch3_am, out float ch3_am_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (ch3_volt_float > 0 && ch3_volt_float <= 10 && ch3_am_float > 0 && ch3_am_float <= 4)
                {
                    // Valoarea introdusă este o cifră între 0 și 5
                    driver.Outputs.VoltageLevel = ch3_volt_float;
                    driver.Outputs.CurrentLimit = ch3_am_float;
                    driver.Outputs.Ovp.Limit = 5;
                    driver.Outputs.Ovp.Enabled = false;
                    if (ch3_click > 0)
                    {
                        driver.Outputs.ChannelOnlyEnabled = true;
                        SolidColorBrush redBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0x00, 0x00));
                        CH3_btn_on.Background = redBrush;
                    }
                    else
                    {
                        driver.Outputs.ChannelOnlyEnabled = false;
                        SolidColorBrush grayBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0xE5, 0xE5));

                        
                        CH3_btn_on.Background = grayBrush;
                    }
                }
                else
                {
                    // Valoarea introdusă nu este între 0 și 5
                     System.Windows.MessageBox.Show("nu este valoare valida  mai mare ca 5");
                }
            }
            else
            {
                // Valoarea introdusă nu este o cifră validă
                System.Windows.MessageBox.Show("nu este valoare valida");
            }

          
        }

        private void get_out_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();

            Window2 form1 = new Window2();
            form1.ShowDialog();
            this.Close();
        }

        private void  realTimeUpdate(object sender, ElapsedEventArgs e) 
        {
            this.Dispatcher.Invoke(() =>
            {
                double voltagetimer = driver.Outputs.Measure(MeasurementType.Voltage);
                channel1realtimeV.Text = Convert.ToString(voltagetimer);

                double currenttimer = driver.Outputs.Measure(MeasurementType.Current);
                channel1realtimeA.Text = Convert.ToString(currenttimer);

                double pow = driver.Outputs.Measure(MeasurementType.Power);
                channel1realtimeP.Text = Convert.ToString(pow);
            });
        }

        private void go_ld400_Click(object sender, RoutedEventArgs e)
        {

            this.Hide();

            LD400P form1 = new LD400P();
            form1.ShowDialog();
            this.Close();
        }
    }
  
}

