package View;

import entity.Cars;
import java.awt.Component;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.NewHibernateUtil;

public class ShowCars extends javax.swing.JFrame {

    private static String hql_brand = "from Cars c where c.marka like '";
    private static String hql_year = "from Cars c where c.rok between ";
    private static String hql_type = "from Cars c where c.nadwozie= '";
    private static String hql_fuel = "from Cars c where c.paliwo= '";
    private static String hql_damage = "from Cars c  where c.stan = 'Uszkodzony'";
    private static String hql_all = "from Cars";

    private void searchCars() {
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        model.setRowCount(0);
        if (CheckboxDamage.isSelected()) {
            runQueryDamage();
        }
        if (checkBoxShowAll.isSelected()) {
            runQueryShowAllCars();
        }
        if (labelBrand.getText().trim().equals("") && labelYearFrom.getText().trim().equals("") && ComboBoxFuel.getSelectedItem().toString().equals("-")) {
            runQueryType();

        } else if (labelBrand.getText().trim().equals("") && labelYearFrom.getText().trim().equals("") && ComboBoxType.getSelectedItem().toString().equals("-")) {
            runQueryFuel();
        } else if (labelBrand.getText().trim().equals("") && labelYearFrom.getText().trim().equals("")) {
            runQeryFuelType();
        } else if (ComboBoxFuel.getSelectedItem().toString().equals("-") && ComboBoxType.getSelectedItem().toString().equals("-") && labelYearFrom.getText().trim().equals("")) {
            runQueryBrand();
        } else if (labelBrand.getText().trim().equals("") && ComboBoxFuel.getSelectedItem().toString().equals("-") && ComboBoxType.getSelectedItem().toString().equals("-")) {
            runQueryYear();
        } else if (ComboBoxFuel.getSelectedItem().toString().equals("-") && ComboBoxType.getSelectedItem().toString().equals("-")) {
            runQueryBrandYear();
        }

    }

    private void displayResult(List resultList) {
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        resultTable.setAutoCreateRowSorter(true);
        List<Cars> list = resultList;
        Object rowData[] = new Object[11];

        for (int i = 0; i < list.size(); i++) {
//            rowData[0] = list.get(i).getCarId();
            rowData[0] = list.get(i).getMarka();
            rowData[1] = list.get(i).getModel();
            rowData[2] = list.get(i).getRok();
            rowData[3] = list.get(i).getNadwozie();
            rowData[4] = list.get(i).getPrzebieg();
            rowData[5] = list.get(i).getPaliwo();
            rowData[6] = list.get(i).getKolor();
            rowData[7] = list.get(i).getSkrzynia();
            rowData[8] = list.get(i).getStan();
            rowData[9] = list.get(i).getOpis();
            model.addRow(rowData);

        }

    }

    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void execute(String hql) {

        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            displayResult(resultList);
            resizeColumnWidth(resultTable);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    private void enabledFields() {
        labelBrand.setEnabled(true);
        labelYearFrom.setEnabled(true);
        labelYearTo.setEnabled(true);
        ComboBoxFuel.setEnabled(true);
        ComboBoxType.setEnabled(true);
    }

    private void disabledFields() {
        labelBrand.setEnabled(false);
        labelYearFrom.setEnabled(false);
        labelYearTo.setEnabled(false);
        ComboBoxFuel.setEnabled(false);
        ComboBoxType.setEnabled(false);
    }

    private void aboutProgram() throws HeadlessException {
        JOptionPane.showMessageDialog(null, "Bazodanowy program \"Car Station\" wykorzystuje framework \"Hibernate\". Pozwala na wyszukiwanie pojazdów z bazy danych,\n"
                + "w zależności od zadanych parametrów takich jak marka, lata produkcji, czy typ nadwozia.\n\nAutor: Grzegorz Filewicz\nLublin,1.08.2018", "O Programie", JOptionPane.INFORMATION_MESSAGE);
    }

    private void runQueryBrand() {
        execute(hql_brand + labelBrand.getText() + "%'");
    }

    private void runQueryYear() {
        execute(hql_year + labelYearFrom.getText() + " and " + labelYearTo.getText());
    }

    private void runQueryType() {
        execute(hql_type + ComboBoxType.getSelectedItem().toString() + "'");
    }

    private void runQueryFuel() {
        execute(hql_fuel + ComboBoxFuel.getSelectedItem().toString() + "'");
    }

    private void runQueryDamage() {
        execute(hql_damage);
    }

    private void runQueryShowAllCars() {
        execute(hql_all);
    }

    private void runQeryFuelType() {
        execute(hql_type + ComboBoxType.getSelectedItem().toString() + "' and c.paliwo='" + ComboBoxFuel.getSelectedItem().toString() + "'");
    }

    private void runQueryBrandYear() {
        execute(hql_brand + labelBrand.getText() + "%'" + " and c.rok between " + labelYearFrom.getText() + " and " + labelYearTo.getText());
    }

    public ShowCars() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelBrand = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        labelYearFrom = new javax.swing.JTextField();
        labelYearTo = new javax.swing.JTextField();
        labelType = new javax.swing.JLabel();
        ComboBoxType = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        ComboBoxFuel = new javax.swing.JComboBox<>();
        CheckboxDamage = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        buttonSearch = new javax.swing.JButton();
        buttonAbout = new javax.swing.JButton();
        checkBoxShowAll = new javax.swing.JCheckBox();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stacja samochodowa");
        setMinimumSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jLabel1.setText("Marka:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 80, 40, 30);
        jPanel1.add(labelBrand);
        labelBrand.setBounds(70, 80, 140, 30);

        jLabel4.setText("Lata Produkcji:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 150, 100, 20);
        jPanel1.add(labelYearFrom);
        labelYearFrom.setBounds(150, 130, 60, 24);
        jPanel1.add(labelYearTo);
        labelYearTo.setBounds(150, 160, 60, 24);

        labelType.setText("Nadwozie:");
        jPanel1.add(labelType);
        labelType.setBounds(290, 60, 70, 30);

        ComboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Sedan", "Kombi", "SUV", "Hatchback", "Minivan" }));
        jPanel1.add(ComboBoxType);
        ComboBoxType.setBounds(360, 60, 80, 30);

        jLabel3.setText("Paliwo:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(290, 120, 60, 30);

        ComboBoxFuel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Diesel", "Benzyna+LPG", "Benzyna" }));
        jPanel1.add(ComboBoxFuel);
        ComboBoxFuel.setBounds(360, 120, 108, 26);

        CheckboxDamage.setText("Uszkodzony");
        CheckboxDamage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckboxDamageActionPerformed(evt);
            }
        });
        jPanel1.add(CheckboxDamage);
        CheckboxDamage.setBounds(290, 160, 96, 24);

        jLabel5.setText("Od:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(120, 130, 41, 30);

        jLabel6.setText("Do:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(120, 160, 40, 30);

        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 50, 250, 140);

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel8);
        jLabel8.setBounds(280, 50, 240, 140);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Stacja Samochodowa");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(190, 0, 180, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(6, 6, 590, 230);

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Marka", "Model", "Rok", "Nadwozie", "Przebieg", "Paliwo", "Kolor", "Skrzynia", "Stan", "Opis"
            }
        ));
        resultTable.setEnabled(false);
        resultTable.setMinimumSize(new java.awt.Dimension(600, 300));
        resultTable.setRowHeight(40);
        resultTable.setRowMargin(3);
        jScrollPane2.setViewportView(resultTable);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 260, 1140, 370);

        buttonSearch.setText("Szukaj");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSearch);
        buttonSearch.setBounds(600, 90, 100, 40);

        buttonAbout.setText("O programie");
        buttonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAboutActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAbout);
        buttonAbout.setBounds(1077, 10, 110, 32);

        checkBoxShowAll.setText("Wszystkie auta");
        checkBoxShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxShowAllActionPerformed(evt);
            }
        });
        getContentPane().add(checkBoxShowAll);
        checkBoxShowAll.setBounds(600, 190, 130, 24);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        searchCars();

    }//GEN-LAST:event_buttonSearchActionPerformed


    private void CheckboxDamageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckboxDamageActionPerformed
        if (CheckboxDamage.isSelected()) {
            disabledFields();

        } else {
            enabledFields();
        }
    }//GEN-LAST:event_CheckboxDamageActionPerformed


    private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
        aboutProgram();
    }//GEN-LAST:event_buttonAboutActionPerformed

    private void checkBoxShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxShowAllActionPerformed
        if (checkBoxShowAll.isSelected()) {
            disabledFields();
            CheckboxDamage.setEnabled(false);

        } else {
            enabledFields();
            CheckboxDamage.setEnabled(true);
        }
    }//GEN-LAST:event_checkBoxShowAllActionPerformed

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
            java.util.logging.Logger.getLogger(ShowCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowCars().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckboxDamage;
    private javax.swing.JComboBox<String> ComboBoxFuel;
    private javax.swing.JComboBox<String> ComboBoxType;
    private javax.swing.JButton buttonAbout;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JCheckBox checkBoxShowAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField labelBrand;
    private javax.swing.JLabel labelType;
    private javax.swing.JTextField labelYearFrom;
    private javax.swing.JTextField labelYearTo;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables

}
