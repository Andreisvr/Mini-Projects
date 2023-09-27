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

namespace Control_Rohde_Schwarz
{
    /// <summary>
    /// Interaction logic for Window2.xaml
    /// </summary>
    public partial class Window2 : Window
    {
        public Window2()
        {
            InitializeComponent();
        }

        private void HMC_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();
            MainWindow form2 = new MainWindow();
            form2.Show();
            this.Close();
        }

       

        private void LD400_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();
            LD400P form2 = new LD400P();
            form2.Show();
            this.Close();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();
            first_page form2 = new first_page();
            form2.Show();
            this.Close();
        }
    }
}
