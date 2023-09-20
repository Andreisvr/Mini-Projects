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

namespace windowapp
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class Window1 : Window
    {
      
        public Window1()
        {
            InitializeComponent();
    Student colea = new Student();
            colea.student_name = "Alehandro";
            colea.student_year_study = "2";
            colea.student_age = "21";

            Data_grid.Items.Add(colea);
         //   List<Student> lista_st = new List<Student>();
            lista_st.Add(colea);
        }

        List<Student> lista_st = new List<Student>();
        
        
        public class Student
        { 
        public string student_name { get; set; }
        public string student_year_study { get; set; }
        public string student_age { get; set; }

        }

        private void add_btn_Click(object sender, RoutedEventArgs e)
        {
            Student temp = new Student();
            temp.student_name = Name.Text;
            temp.student_year_study = study_year.Text;
            temp.student_age = Age.Text;
            
            if (temp.student_name != "" && temp.student_year_study != "" && temp.student_age != "")
            { Data_grid.Items.Add(temp);
                
                lista_st.Add(temp);
            }
            else { MessageBox.Show("        Completeaza tot, tester orb !!!    "); }

        }

        private void delet_btn_Click(object sender, RoutedEventArgs e)
        {

            Student temp = new Student();
            temp.student_name = Name.Text;
            temp.student_year_study = study_year.Text;
            temp.student_age = Age.Text;

            bool studentGasit = false; 

            foreach (var item in Data_grid.Items)
            {
                if (item is Student student)
                {
                    if (student.student_name == temp.student_name && student.student_year_study == temp.student_year_study && student.student_age == temp.student_age)
                    {
                       
                        Data_grid.Items.Remove(item);
                        studentGasit = true; // Studentul a fost găsit
                        break;
                    }
                }
            }

            if (!studentGasit)
            {
                MessageBox.Show("Ma boi nu exista");
            }

        }

        private void search_Click(object sender, RoutedEventArgs e)
        {
            Student temp = new Student();
            temp.student_name = Name.Text;
            temp.student_year_study = study_year.Text;
            temp.student_age = Age.Text;

            string search_Name = temp.student_name.ToLower(); // Obține criteriul de căutare și il transforma in litere mici
            string search_year = temp.student_year_study.ToLower();
            string search_age = temp.student_age.ToLower();
            // Parcurge rândurile din DataGrid
            foreach (var item in Data_grid.Items)
            {
                if (item is Student student &&
                    (student.student_name.ToLower().Contains(search_Name) &&
                     student.student_year_study.ToString().Contains(search_year) &&
                     student.student_age.ToString().Contains(search_age)))
                {
                    // Găsește indexul rândului care corespunde căutării
                    int rowIndex = Data_grid.Items.IndexOf(item);

                    // Evidențiază rândul selectat în DataGrid
                    Data_grid.SelectedItem = item;

                    // Derulează DataGrid pentru a aduce rândul în vizualizare (opțional)
                    Data_grid.ScrollIntoView(item);

                    break; // Odată ce am găsit o potrivire, putem ieși din buclă
                }
            }
        }
    }
}
