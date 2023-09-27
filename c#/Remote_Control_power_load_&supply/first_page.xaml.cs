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
    /// Interaction logic for first_page.xaml
    /// </summary>
    public partial class first_page : Window
    {
        public first_page()
        {
            InitializeComponent();
        }

        private void get_in_Click(object sender, RoutedEventArgs e)
        {
            this.Hide();

            Window2 form2 = new Window2();
            form2.Show();
            this.Close();
        }
    }
}
