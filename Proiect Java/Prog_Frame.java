/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_pack;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 *
 * @author Andrei_Sviridov
 */
public class Prog_Frame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
	/**
     * Creates new form Prog_Frame
     */
    
    @SuppressWarnings("unused")
	private void afiseazaProgramariInTabel() {
    	
    	BTree bt = new BTree(3);
        bt = BTree.Citire_file();
         
        List<Programari> programariList = bt.getList();
        
        DefaultTableModel model = (DefaultTableModel) Tabel_prog.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Programari programare : programariList) {
            model.addRow(new Object[]{
                programare.getId(),
                programare.getNume_m(),
                programare.getPrenume_m(),
                programare.getTipMed(),
                programare.getNume_p(),
                programare.getData()
            });
        }
    }
    public Prog_Frame() {
        initComponents();
        initializeMedicBox();
        initPacientBox();
        afiseazaTable();
    }
    
    private void afiseazaTable() {
    	
    	BTree bt = new BTree(3);
        bt = BTree.Citire_file();
         
    List<Programari> programariList = bt.getList();

    DefaultTableModel model = (DefaultTableModel) Tabel_prog.getModel();
    model.setRowCount(0);

    for (Programari programare : programariList) {
        model.addRow(new Object[]{
            programare.getId(),
            programare.getNume_p(),
            programare.getPrenume_p(),
            programare.getNume_m()+" "+programare.getPrenume_m(),
            programare.getTipMed(),
            programare.getData()
        });
    }
    }
    
    private void initializeMedicBox() {
     
        List<Medic> listaMedici = MedicManager.deserializeMedici();
    
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (Medic medic : listaMedici) {
        	
            String displayText =  medic.getTipmedic()+","+medic.getNume() + "," + medic.getPrenume() ;
            comboBoxModel.addElement(displayText);
        }

        // Setează modelul combobox-ului
        MedicBox.setModel(comboBoxModel);
    }
    private void initPacientBox()
    {
    	  PacientManager pacientManager = new PacientManager();
          pacientManager.deserializePacienti();
          
          List<Pacient> pacienti = pacientManager.getPacientiList();
     
          DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

          
          for (Pacient pacient : pacienti) {
              String displayText =  pacient.getNume() + "," + pacient.getPrenume()+","+pacient.getAni()+','+pacient.getgen();
              comboBoxModel.addElement(displayText);
          }
         
          PacientiBox.setModel(comboBoxModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

       // afiseazaProgramariInTabel(); 
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        To_medic = new javax.swing.JButton();
        To_medic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Med_Frame medfr = new Med_Frame();
				medfr.setVisible(true);
				
				Prog_Frame prog = new Prog_Frame();
				prog.setVisible(false);
				
				  JTable table_medic = Med_Frame.getTableMedic();

					 DefaultTableModel model = (DefaultTableModel) table_medic.getModel();
	                 model.setRowCount(0);
	                 
	                 List<Medic> listaMedici = MedicManager.deserializeMedici();
	                 for (Medic m : listaMedici) {
	         	        model.addRow(new Object[]{m.getNume(), m.getPrenume(), m.getTipmedic(), m.getIstoric(), m.getData(), m.getGen()});
	         	    }
			            
				
        	}
        });
        To_pacient = new javax.swing.JButton();
        To_pacient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Programari_Frame programari_Frame = new Programari_Frame();
				programari_Frame.setVisible(true);
				
				Prog_Frame prog = new Prog_Frame();
				prog.setVisible(false);
				

 				  JTable table_pacient = Programari_Frame.getTablePacient();

 				 DefaultTableModel model = (DefaultTableModel) table_pacient.getModel();
                  model.setRowCount(0);
                  

                  @SuppressWarnings("unused")
  				RedBlackTree redBlackTree = new RedBlackTree();
                  redBlackTree = RedBlackTree.makeTree();
          	   List<Pacient> arr =  RedBlackTree.Preluare_Lista();
          	   
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
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel_prog = new javax.swing.JTable();
        MedicBox = new javax.swing.JComboBox<>();
        
    
        jButton2 = new javax.swing.JButton();
        search_btn = new javax.swing.JButton();
        search_btn.addActionListener(new ActionListener() {
        	@SuppressWarnings("static-access")
        	public void actionPerformed(ActionEvent e) {
        	   
        		int inputId = 0;

        		 try {
        	             inputId = Integer.parseInt(id_field.getText().trim());
        	            System.out.println(inputId);

        	            // Rest of your code...
        	        } catch (NumberFormatException ex) {
        	            JOptionPane.showMessageDialog(Prog_Frame.this, "Nu este id Valid", "Error", JOptionPane.ERROR_MESSAGE);
        	        }
       
        	    
        	        System.out.println(inputId);
        	    if (inputId >= 100 && inputId <= 999) {
        	    	
        	    	BTree bt = new BTree(3);
        	         bt = BTree.Citire_file();
    
        	        final int id = bt.Cauta_id(inputId);
        	        System.out.println(id);
        	        if (id == 0) {
        	            JOptionPane.showMessageDialog(Prog_Frame.this, "Câmpul id trebuie completat corect", "Avertisment", JOptionPane.WARNING_MESSAGE);
        	            return;
        	        }

        	        DefaultTableModel model = (DefaultTableModel) Tabel_prog.getModel();
        	        Tabel_prog.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        	            private static final long serialVersionUID = 1L;

        	            @Override
        	            public Component getTableCellRendererComponent(JTable table, Object value,
        	                    boolean isSelected, boolean hasFocus, int row, int column) {
        	                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        	                int id_m = (int) model.getValueAt(row, 0);

        	                if (id == id_m) {
        	                    c.setBackground(Color.GREEN);
        	                } else {
        	                    c.setBackground(table.getBackground());
        	                }

        	                return c;
        	            }
        	        });

        	        Tabel_prog.repaint();

        	        for (int i = 0; i < model.getRowCount(); i++) {
        	            int id_p = (int) model.getValueAt(i, 0);

        	            if (id == id_p) {
        	                break;
        	            }
        	        }
        	    }
        	}

        });
        delete_button = new javax.swing.JButton();
        delete_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    int idToDelete = Integer.parseInt(id_field.getText().trim());
                    int result = Prog_Manager.Search_ById(idToDelete);
                    if (result == 0) {
                        JOptionPane.showMessageDialog(Prog_Frame.this, "ID-ul nu a fost găsit!", "Avertisment", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Prog_Manager.Delete_ById(idToDelete);
                        DefaultTableModel model = (DefaultTableModel) Tabel_prog.getModel();
                        model.setRowCount(0); 
                     
                        BTree bt = new BTree(3);
                        bt = BTree.Citire_file();
                       
                        List<Programari> programariList =  bt.getList();
    
                        for (Programari programare : programariList) {
                            Object[] row = {programare.getId(), programare.getNume_p(), programare.getPrenume_p(), programare.getNume_m(), programare.getTipMed(), programare.getData()};
                            model.addRow(row);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Prog_Frame.this, "Introduceți un ID valid!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        
        jLabel3 = new javax.swing.JLabel();
        PacientiBox = new javax.swing.JComboBox<>();
        Data_prog = new javax.swing.JLabel();
        data_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 255, 255));
        jButton1.setText("Back");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MedClinic");
        jLabel1.setToolTipText("");

        To_medic.setBackground(new java.awt.Color(0, 153, 255));
        To_medic.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        To_medic.setForeground(new java.awt.Color(204, 255, 255));
        To_medic.setText("Medici");

        To_pacient.setBackground(new java.awt.Color(0, 153, 255));
        To_pacient.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        To_pacient.setForeground(new java.awt.Color(204, 255, 255));
        To_pacient.setText("Pacienti");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(To_pacient, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(To_medic, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(To_pacient)
                .addGap(108, 108, 108)
                .addComponent(To_medic)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel2.setText("Programari");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Medic");

        jScrollPane2.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Tabel_prog.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        Tabel_prog.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Tabel_prog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
              "Id","Nume", "Prenume", "Num_Medic", "Tip_Med", "Data"
            }
        ));
        jScrollPane2.setViewportView(Tabel_prog);

        MedicBox.setFont(new Font("Lucida Grande", Font.BOLD, 10)); // NOI18N
        MedicBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medic 1" }));

        jButton2.setBackground(new java.awt.Color(0, 153, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        search_btn.setBackground(new java.awt.Color(0, 204, 0));
        search_btn.setText("Search");

        delete_button.setBackground(new java.awt.Color(255, 0, 51));
        delete_button.setText("Delete");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pacient");

        PacientiBox.setFont(new Font("Lucida Grande", Font.BOLD, 10)); // NOI18N
        PacientiBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pacient", " " }));
        PacientiBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PacientiBoxActionPerformed(evt);
            }
        });

        Data_prog.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        Data_prog.setText("Data :");

        data_text.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        data_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Id : ");

        id_field.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MedicBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(115, 115, 115)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PacientiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(105, 105, 105)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(data_text, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Data_prog, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(87, 87, 87)
                                                .addComponent(jLabel4))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)))))))
                        .addContainerGap(48, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(Data_prog)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PacientiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MedicBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete_button)
                    .addComponent(search_btn)
                    .addComponent(jButton2))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    @SuppressWarnings("static-access")
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	String[] medicInfo = ((String) MedicBox.getSelectedItem()).split(",");
    	String tipMed = medicInfo[0];
    	String nume_m = medicInfo[1];
    	String prenume_m = medicInfo[2];

    	String[] pacientInfo = ((String) PacientiBox.getSelectedItem()).split(",");
    	String nume_p = pacientInfo[0];
    	String prenume_p = pacientInfo[1];
    	int ani = Integer.parseInt(pacientInfo[2]);
    	String gen_p = pacientInfo[3];

    	String data = data_text.getText().trim();

    	Programari programare = new Programari(
    	        tipMed, nume_m, prenume_m,
    	        nume_p, prenume_p, ani,
    	        gen_p, data
    	);

    	Prog_Manager p1  = new Prog_Manager(); 
    	@SuppressWarnings("static-access")
    	List<Programari> programariList = p1.deserializeProgramari();
		
//    	programariList.add(programare);
    	 BTree bt = new BTree(3);
         bt = BTree.Citire_file();
         bt.Insert(programare);

    	  afiseazaTable();
    	   System.out.println("Programare adăugată cu succes: " + programare.toString());
    	 
  	
      
    }


                                           

    private void PacientiBoxActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Prog_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prog_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prog_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prog_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prog_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel Data_prog;
    private javax.swing.JComboBox<String> MedicBox;
    private javax.swing.JComboBox<String> PacientiBox;
    private javax.swing.JTable Tabel_prog;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton search_btn;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton To_medic;
    private javax.swing.JButton To_pacient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField data_text;
    private javax.swing.JTextField id_field;
    // End of variables declaration                   
}
