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
using System.Windows.Shapes;
using System.Management.Automation;
using System.Diagnostics;
using System.IO;
using System.IO.Ports;
using System.Globalization;



namespace Control_Rohde_Schwarz
{


    /// <summary>
    /// Interaction logic for LD400P.xaml
    /// </summary>
    public partial class LD400P : Window
    {

        SerialPort port = new SerialPort("COM7"); 
        int range = -1;
        int slow = -1;
        public LD400P()
        {
            InitializeComponent();
        }

        private void back_to_second_Click(object sender, RoutedEventArgs e)
        {

            this.Hide();

            Window2 form2 = new Window2();
            form2.Show();
            this.Close();
        }

        private void Level_a_Click(object sender, RoutedEventArgs e)
        {
            string leva = lvl_a_in.Text;
            if (float.TryParse(leva, NumberStyles.Float, CultureInfo.InvariantCulture, out float leva_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (leva_float > 0 && leva_float <= 9)
                {
                    port.Open();
                    port.WriteLine("A " + leva);
                    port.Close();
                }
                else { MessageBox.Show("Error check USB PORT, or change value (max=9)"); }

            }
            else
            {
                MessageBox.Show("introduceti valoare valida sau cu . ca delimitare ");
            }



        }

        private void Level_b_Click(object sender, RoutedEventArgs e)
        {
            string levb = lvl_b_in.Text;
            if (float.TryParse(levb, NumberStyles.Float, CultureInfo.InvariantCulture, out float levb_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (levb_float > 0 && levb_float <= 9)
                {
                    port.Open();
                    port.WriteLine("B " + levb);
                    port.Close();
                }
                else { MessageBox.Show("Error check USB PORT, or change value (max=9)"); }

            }
            else
            {
                MessageBox.Show("introduceti valoare valida sau cu . ca delimitare ");
            }

        }

        private void Drop_out_Click(object sender, RoutedEventArgs e)
        {
            string levd = drop_in.Text;
            if (float.TryParse(levd, NumberStyles.Float, CultureInfo.InvariantCulture, out float drop_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (drop_float > 0 && drop_float <= 9)
                {
                    port.Open();
                    port.WriteLine("DROP " + levd);
                    port.Close();
                }
                else { MessageBox.Show("Error check USB PORT, or change value (max=9)"); }

            }
            else
            {
                MessageBox.Show("introduceti valoare valida sau cu . ca delimitare ");
            }
        }

        private void Slew_Click(object sender, RoutedEventArgs e)
        {
            string levs = slew_in.Text;
            if (float.TryParse(levs, NumberStyles.Float, CultureInfo.InvariantCulture, out float slew_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (slew_float > 0 && slew_float <= 9)
                {
                    port.Open();
                    port.WriteLine("SLEW " + levs);
                    port.Close();
                }
                else { MessageBox.Show("Error check USB PORT, or change value (max=9)"); }

            }
            else
            {
                MessageBox.Show("introduceti valoare valida sau cu . ca delimitare ");
            }
        }

        private void Duty_Click(object sender, RoutedEventArgs e)
        {
            string levdt = duty_in.Text;
            if (float.TryParse(levdt, NumberStyles.Float, CultureInfo.InvariantCulture, out float duty_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (duty_float > 0 && duty_float <= 100)
                {
                    port.Open();
                    port.WriteLine("DUTY " + levdt);
                    port.Close();
                }
                else { MessageBox.Show("Error check USB PORT, or change value (max=100)"); }

            }
            else
            {
                MessageBox.Show("introduceti valoare valida sau cu . ca delimitare ");
            }
        }

        private void Freq_Click(object sender, RoutedEventArgs e)
        {
            string levf = freq_in.Text;
            if (float.TryParse(levf, NumberStyles.Float, CultureInfo.InvariantCulture, out float freq_float))
            {
                // Verificați dacă numărul este între 0 și 5
                if (freq_float > 0 && freq_float <= 9990)
                {
                    port.Open();
                    port.WriteLine("FREQ " + levf);
                    port.Close();
                }
                else { MessageBox.Show("Error check USB PORT, or change value (max=9990)"); }

            }
            else
            {
                MessageBox.Show("introduceti valoare valida sau cu . ca delimitare ");
            }
        }

        private void LVL_RANGE_Click(object sender, RoutedEventArgs e)
        {
            range = range * (-1);
            try
            {
                port.Open();




                if (range < 0)
                {
                    port.WriteLine("RANGE 0");
                    // Check if there's text in the TextBox
                    if (!string.IsNullOrEmpty(lvl_a_in.Text))
                    {
                        // Remove the last character
                        lvl_a_in.Text = lvl_a_in.Text.Substring(0, lvl_a_in.Text.Length - 1);
                    }
                    if (!string.IsNullOrEmpty(lvl_b_in.Text))
                    {
                        // Remove the last character
                        lvl_b_in.Text = lvl_b_in.Text.Substring(0, lvl_b_in.Text.Length - 1);
                    }

                    if (!string.IsNullOrEmpty(slew_in.Text))
                    {
                        // Remove the last character
                        slew_in.Text = slew_in.Text.Substring(0, slew_in.Text.Length - 1);
                    }



                }
                else
                {
                    port.WriteLine("RANGE 1");
                    lvl_a_in.Text += "0";
                    lvl_b_in.Text += "0";

                    slew_in.Text += "0";


                }
                port.Close();
            }
            catch (Exception s)
            {
                MessageBox.Show("Check USB PORT");
            }



        }

        private void slow_start_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                SolidColorBrush redBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0x00, 0x00));
                slow = slow * (-1);
                port.Open();
                if (range < 0)
                {
                    port.WriteLine("SLOW 1");
                    slow_start.Background = redBrush;
                }
                if(range>0) 
                {
                    SolidColorBrush whiteBrush = new SolidColorBrush(Color.FromRgb(0xFF, 0xFF, 0xFF));
                    port.WriteLine("SLOW 0");
                    slow_start.Background = whiteBrush;
                }
                port.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("An error occurred: " + ex.Message);
            }


        }

        private void go_hmc_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();

            MainWindow form2 = new MainWindow();
            form2.Show();
            this.Close();
        }
    }
}
