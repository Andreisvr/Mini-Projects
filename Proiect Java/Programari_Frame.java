package main_pack;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 *clasa de afisare si logica pentru lista pe pacienti
 * @author Andrei_Sviridov
 */
public class Programari_Frame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
	/**
     * Creates new form Programari_Frame
     */
    public Programari_Frame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        back_from_progrmr = new javax.swing.JButton();
        back_from_progrmr.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Programari_Frame programari_Frame = new Programari_Frame();
				programari_Frame.setVisible(false);
                 
                   Med_Frame med_Frame = new Med_Frame();
                   med_Frame.setVisible(true);
        	}
        });
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        medclinic_lab = new javax.swing.JLabel();
        java.awt.event.MouseAdapter labelClickListener = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Prog_Frame altFrame = new Prog_Frame();
                altFrame.setVisible(true);
                Programari_Frame medFrame = new Programari_Frame();
                setAllComponentsVisibility(medFrame.getContentPane(), false);
                medFrame.setVisible(false);

            }
        };
        medclinic_lab.addMouseListener(labelClickListener); 
        to_pacients_p = new javax.swing.JButton();
        to_med_p = new javax.swing.JButton();
        to_med_p.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Programari_Frame programari_Frame = new Programari_Frame();
				
        		programari_Frame.setVisible(false);
				 
				Med_Frame med_frame = new Med_Frame();
				med_frame.setVisible(true);
		
				  JTable table_medic = Med_Frame.getTableMedic();

				 DefaultTableModel model = (DefaultTableModel) table_medic.getModel();
                 model.setRowCount(0);
                 List<Medic> listaMedici = MedicManager.deserializeMedici();
                 for (Medic m : listaMedici) {
         	        model.addRow(new Object[]{m.getNume(), m.getPrenume(), m.getTipmedic(), m.getIstoric(), m.getData(), m.getGen()});
         	    }
		            
			
			       
			        
        	}
        });
        
        programari_lab = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        prename_p = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        name_p = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        data_p = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        gender_p = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        istoric_p = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_pacient = new javax.swing.JTable();
        ani_p = new javax.swing.JTextField();
        add_p = new javax.swing.JButton();
        search_p = new javax.swing.JButton();
        search_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String numeCautat = name_p.getText();
                String prenumeCautat = prename_p.getText();
                if (numeCautat.isEmpty() || prenumeCautat.isEmpty()) {
                    JOptionPane.showMessageDialog(Programari_Frame.this, " Câmpurile nume si prenume trebuie completate!", "Avertisment", JOptionPane.WARNING_MESSAGE);
                    return;
                    }
                if (!numeCautat.isEmpty() || !prenumeCautat.isEmpty()) {
                    DefaultTableModel model = (DefaultTableModel) table_pacient.getModel();
                    boolean pacientGasit = false;

                    table_pacient.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value,
                                boolean isSelected, boolean hasFocus, int row, int column) {
                            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                            String numePacient = (String) model.getValueAt(row, 0);
                            String prenumePacient = (String) model.getValueAt(row, 1);

                            if ((numeCautat.isEmpty() || numePacient.toLowerCase().contains(numeCautat.toLowerCase()))
                                    && (prenumeCautat.isEmpty() || prenumePacient.toLowerCase().contains(prenumeCautat.toLowerCase()))) {
                                c.setBackground(Color.GREEN);
                            } else {
                                c.setBackground(table.getBackground());
                            }

                            return c;
                        }
                    });

                    table_pacient.repaint();

                    for (int i = 0; i < model.getRowCount(); i++) {
                        String numePacient = (String) model.getValueAt(i, 0);
                        String prenumePacient = (String) model.getValueAt(i, 1);

                        if ((numeCautat.isEmpty() || numePacient.toLowerCase().contains(numeCautat.toLowerCase()))
                                && (prenumeCautat.isEmpty() || prenumePacient.toLowerCase().contains(prenumeCautat.toLowerCase()))) {
                            pacientGasit = true;
                            break;
                        }
                    }

                    if (!pacientGasit) {
                        JOptionPane.showMessageDialog(Programari_Frame.this, "Niciun pacient găsit pentru criteriile introduse.");
                    }
                }
            }
        });


        delet_p = new javax.swing.JButton();
        delet_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nume = name_p.getText();
                String prenume = prename_p.getText();
                String istoric = istoric_p.getText();
                String data = data_p.getText();
                String gen = gender_p.getSelectedItem().toString();
                int ani = -1;
                String temp = ani_p.getText();

                if (nume.isEmpty() || prenume.isEmpty() || istoric.isEmpty() || data.isEmpty() || temp.isEmpty()) {
                    JOptionPane.showMessageDialog(Programari_Frame.this, "Toate câmpurile trebuie completate!", "Avertisment", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ani = Integer.parseInt(temp);

                        PacientManager pacientManager = new PacientManager();
                        pacientManager.deserializePacienti();
                        List<Pacient> pacienti = pacientManager.getPacientiList();

                        boolean exista = false;
                       

                        for (Pacient it : pacienti) {
              if (it.getNume().trim().equals(nume.trim()) && it.getPrenume().trim().equals(prenume.trim()) && it.getgen().equals(gen.trim())) {
 
                                exista = true;
                              
                                break;
                            }
                        }

                        if (exista) {
                            pacientManager.stergePacient(nume, prenume, ani);
                            DefaultTableModel model = (DefaultTableModel) table_pacient.getModel();
                            model.setRowCount(0);

                            for (Pacient p : pacienti) {
                                model.addRow(new Object[]{p.getNume(), p.getPrenume(), p.getAni(), p.getIstoric(), p.getProgramare(), p.getgen()});
                            }

                            pacientManager.serializePacienti(pacienti);

                            name_p.setText("");
                            prename_p.setText("");
                            istoric_p.setText("");
                            data_p.setText("");
                            gender_p.setSelectedIndex(0);
                            ani_p.setText("");
                        } else {
                            JOptionPane.showMessageDialog(Programari_Frame.this, "Pacientul nu există!", "Avertisment", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Programari_Frame.this, "Anul trebuie să fie un număr valid!", "Avertisment", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        back_from_progrmr.setBackground(new java.awt.Color(255, 51, 51));
        back_from_progrmr.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        back_from_progrmr.setForeground(new java.awt.Color(204, 255, 255));
        back_from_progrmr.setText("Back");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back_from_progrmr)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back_from_progrmr)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        medclinic_lab.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        medclinic_lab.setForeground(new java.awt.Color(204, 255, 255));
        medclinic_lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        medclinic_lab.setText("MedClinic");
        medclinic_lab.setToolTipText("");

        to_pacients_p.setBackground(new java.awt.Color(0, 153, 255));
        to_pacients_p.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        to_pacients_p.setForeground(new java.awt.Color(204, 255, 255));
        to_pacients_p.setText("Pacienti");

        to_med_p.setBackground(new java.awt.Color(0, 153, 255));
        to_med_p.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        to_med_p.setForeground(new java.awt.Color(204, 255, 255));
        to_med_p.setText("Medici");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(medclinic_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to_pacients_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(to_med_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(medclinic_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181)
                .addComponent(to_pacients_p)
                .addGap(126, 126, 126)
                .addComponent(to_med_p)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        programari_lab.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        programari_lab.setText("Pacienti");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Nume:");

        prename_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel6.setText("Prenume:");
        jLabel6.setToolTipText("");

        name_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setText("Ani :");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("Istoric:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel9.setText("Data:");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel10.setText("Gender:");

        gender_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barbat", "Femeie", "Elicopter de lupta", "Printre primele 3 optiuni", "Nu stiu !", " ", " ", " " }));

        istoric_p.setColumns(20);
        istoric_p.setRows(5);
        jScrollPane1.setViewportView(istoric_p);

        jScrollPane2.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        table_pacient.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        table_pacient.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        table_pacient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "Nume", "Prenume", "ani", "Istoric", "Data", "Gender"
            }
        ));
        jScrollPane2.setViewportView(table_pacient);

        add_p.setBackground(new java.awt.Color(0, 153, 255));
        add_p.setText("Add");
        add_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        search_p.setBackground(new java.awt.Color(0, 204, 0));
        search_p.setText("Search");

        delet_p.setBackground(new java.awt.Color(255, 0, 51));
        delet_p.setText("Delete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(prename_p, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(155, 155, 155)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(name_p)
                                .addGap(131, 131, 131)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ani_p, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(data_p, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gender_p, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(search_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delet_p, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(add_p, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(programari_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(programari_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(name_p, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(data_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ani_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gender_p, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prename_p, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(add_p)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search_p)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delet_p)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    @SuppressWarnings("static-access")
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        String nume = name_p.getText();
        String prenume = prename_p.getText();
        String istoric = istoric_p.getText();
        String data = data_p.getText();
        String gen = gender_p.getSelectedItem().toString();
        int ani = -1;
        String temp = ani_p.getText();

        if (nume.isEmpty() || prenume.isEmpty() || istoric.isEmpty() || data.isEmpty() || temp.isEmpty()) {
            JOptionPane.showMessageDialog(Programari_Frame.this, "Toate câmpurile trebuie completate!", "Avertisment", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ani = Integer.parseInt(temp);
//
//                PacientManager pacientManager = new PacientManager();
//                pacientManager.deserializePacienti();
                
                RedBlackTree redBlackTree = new RedBlackTree();
                redBlackTree = RedBlackTree.makeTree();

                @SuppressWarnings("static-access")
				List<Pacient> pacienti = redBlackTree.Preluare_Lista();

                boolean exista = false;

                for (Pacient it : pacienti) {
                    if (it.getNume().equals(nume) && it.getPrenume().equals(prenume) && it.getAni() == ani
                            && it.getProgramare().equals(data) && it.getIstoric().equals(istoric) && it.getgen().equals(gen)) {
                        exista = true;
                        JOptionPane.showMessageDialog(this, "Pacientul deja există", "Avertisment", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }

                if (!exista) {
                    Pacient pacient = new Pacient(nume, prenume, ani, istoric, data, gen);
                   redBlackTree.insert(pacient);
                }

                DefaultTableModel model = (DefaultTableModel) table_pacient.getModel();
                model.setRowCount(0);
                List<Pacient> pacienti_2 = redBlackTree.Preluare_Lista();
                
                for (Pacient p : pacienti_2) {
                    model.addRow(new Object[]{p.getNume(), p.getPrenume(), p.getAni(), p.getIstoric(), p.getProgramare(), p.getgen()});
                }

               redBlackTree.salvare_lista();

                name_p.setText("");
                prename_p.setText("");
                istoric_p.setText("");
                data_p.setText("");
                gender_p.setSelectedIndex(0);
                ani_p.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(Programari_Frame.this, "Anul trebuie să fie un număr valid!", "Avertisment", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
                                     

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Programari_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programari_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programari_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programari_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programari_Frame().setVisible(true);
                
                
                @SuppressWarnings("unused")
				RedBlackTree redBlackTree = new RedBlackTree();
                redBlackTree = RedBlackTree.makeTree();

        	   List<Pacient> arr =  RedBlackTree.Preluare_Lista();
        	        DefaultTableModel model = (DefaultTableModel) table_pacient.getModel();
        	     
        	        model.setRowCount(0);
        	          for (Pacient pacient : arr) {
        	            model.addRow(new Object[]{
        	                pacient.getNume(),
        	                pacient.getPrenume(),
        	                pacient.getAni(),
        	                pacient.getIstoric(),
        	                pacient.getProgramare(),
        	                pacient.getgen()
        	            });
        	        }

        	       
        	        table_pacient.setModel(model);
            }
        	     
        	            
        });
        
        
    }
    private void setAllComponentsVisibility(Container container, boolean visible) {
        Component[] components = container.getComponents();
        
        for (Component component : components) {
            if (component instanceof Container) {
               
                setAllComponentsVisibility((Container) component, visible);
            }
          
            component.setVisible(visible);
        }
    }

    public static JTable getTablePacient() {
        return table_pacient;
    }
    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> gender_p;
    private javax.swing.JButton back_from_progrmr;
    private javax.swing.JButton add_p;
    private javax.swing.JButton search_p;
    private javax.swing.JButton delet_p;
    private javax.swing.JButton to_pacients_p;
    private javax.swing.JButton to_med_p;
    private javax.swing.JLabel medclinic_lab;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel programari_lab;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable table_pacient;
    private javax.swing.JTextArea istoric_p;
    private javax.swing.JTextField prename_p;
    private javax.swing.JTextField ani_p;
    private javax.swing.JTextField name_p;
    private javax.swing.JTextField data_p;
    // End of variables declaration                   
}