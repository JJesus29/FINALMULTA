package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logica.Servicio;
import model.Multa;
import model.Respuesta;

public class Borrados extends javax.swing.JFrame {

    final MessageObservable observable = new MessageObservable();
    ArrayList<Multa> lstMultasBorradas = new ArrayList<>();
    public Borrados(ArrayList<Multa>lstMultasBorradas , Registro regiInstance) {
          initComponents();
        
        observable.addObserver(regiInstance);
        Servicio servicio = new Servicio();
        lstMultasBorradas = servicio.getMultas1();

        if (lstMultasBorradas == null) {
            System.err.println("La lista es null");
        } else {
            System.err.println("LISTA: " + lstMultasBorradas.size());
        }
        llenarTabla(lstMultasBorradas);
    }

    class MessageObservable extends Observable {

        MessageObservable() {
            super();
        }

        void changeData(Object data) {
            setChanged(); 
            notifyObservers(data);
        }
    }

    private void llenarTabla(ArrayList<Multa> lista) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"#", "DNI", "Multa", "Monto", "Correo", "Punto", "Id"}, 0);

        int i = 1;
        for (Multa m : lista) {
            model.addRow(new Object[]{i, m.getDni(), m.getMulta(), m.getMonto(), m.getCorreo(), m.getPunto(), m.getIdMulta()});
            i++;
        }
        tbBorrados = new JTable(model);
        tbBorrados.removeColumn(tbBorrados.getColumnModel().getColumn(6));
        ListSelectionListener lel = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    setearDatos();
                }
            }
        };
        tbBorrados.getSelectionModel().addListSelectionListener(lel);
        jScrollPane1.setViewportView(tbBorrados);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbBorrados = new javax.swing.JTable();
        BtnRestaurar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbBorrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Multa", "Monto", "Correo", "Puntos", "ID"
            }
        ));
        jScrollPane1.setViewportView(tbBorrados);

        BtnRestaurar.setText("Restaurar");
        BtnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestaurarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(BtnRestaurar)
                .addGap(50, 50, 50)
                .addComponent(btnEliminar)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnRestaurar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestaurarActionPerformed
     
        observable.changeData(multaRestaurar); 

      
        if (observable != null) {
            Servicio servicio = new Servicio();
            Respuesta rpta = new Respuesta();
            rpta = servicio.borrarMultas(idMulta);
            System.err.println(rpta.toString());
            if (rpta.getCodigo() == 0) {
                for (Multa m : lstMultasBorradas) {
                    if (m.getIdMulta() == idMulta) {
                        lstMultasBorradas.remove(m);

                        break;
                    }
                }
                idMulta = 0;
                llenarTabla(lstMultasBorradas);
            }
        }
    }//GEN-LAST:event_BtnRestaurarActionPerformed
    int idMulta = 0;
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estas seguro de borrar la muta?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            Servicio servicio = new Servicio();
            Respuesta rpta = new Respuesta();
            rpta = servicio.borrarMultas(idMulta);
            System.err.println(rpta.toString());
            if (rpta.getCodigo() == 0) {
                for (Multa m : lstMultasBorradas) {
                    if (m.getIdMulta() == idMulta) {
                        lstMultasBorradas.remove(m);

                        break;
                    }
                }
                idMulta = 0;
                llenarTabla(lstMultasBorradas);
            }
            JOptionPane.showMessageDialog(this, rpta.getMsj());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    Multa multaRestaurar = new Multa();

    private void setearDatos() {
        String dni = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 1) + "";
        String multa = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 2) + "";
        String monto = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 3) + "";
        String correo = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 4) + "";
        String punto = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 5) + "";
        String id = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 6) + "";
        idMulta = Integer.parseInt(id);
        System.err.println(dni + " - " + multa + " - " + monto + " - " + correo + " - " + punto);

        multaRestaurar.setCorreo(correo);
        multaRestaurar.setDni(dni);
        multaRestaurar.setMonto(Double.parseDouble(monto));
        multaRestaurar.setMulta(multa);
        multaRestaurar.setPunto(Integer.parseInt(punto));
        multaRestaurar.setFecha(new Date());
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
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Borrados(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRestaurar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbBorrados;
    // End of variables declaration//GEN-END:variables
}
