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
    /// Interaction logic for LD400P.xaml
    /// </summary>
    public partial class LD400P : Window
    {
        public LD400P()
        {
            InitializeComponent();
        }

        private void back_to_second_Click(object sender, RoutedEventArgs e)
        {

            this.Hide();

            MainWindow form2 = new MainWindow();
            form2.Show();
            this.Close();
        }
    }
}
