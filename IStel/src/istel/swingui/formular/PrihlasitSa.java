/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrihlasitSa.java
 *
 * Created on 1.12.2011, 3:11:09
 */
package istel.swingui.formular;

import istel.Main;
import istel.swingui.IFormObsluha;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author t0ki
 */
public class PrihlasitSa extends javax.swing.JPanel implements IFormObsluha,KeyListener  {

    /** Creates new form PrihlasitSa */
    public PrihlasitSa() {
        initComponents();
        jLabelChyba.setText("");
        jPasswordField1.addKeyListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextMeno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabelChyba = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabelKladka = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(550, 400));

        jLabel1.setText("Prihlasovacie meno");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Heslo");
        jLabel2.setName("jLabel2"); // NOI18N

        jTextMeno.setName("jTextMeno"); // NOI18N

        jButton1.setText("Prihlasit sa");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelChyba.setForeground(new java.awt.Color(255, 0, 0));
        jLabelChyba.setText("Zle zadane meno alebo heslo!");
        jLabelChyba.setName("jLabelChyba"); // NOI18N

        jPasswordField1.setName("jPasswordField1"); // NOI18N

        jLabelKladka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/obrazky/lock.png"))); // NOI18N
        jLabelKladka.setName("jLabelKladka"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabelKladka))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelChyba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addComponent(jPasswordField1)
                    .addComponent(jTextMeno, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextMeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelChyba))
                    .addComponent(jLabelKladka))
                .addContainerGap(171, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(Main.jadro.prihslasitSa(jTextMeno.getText(),
               jPasswordField1.getText())) {
           jLabelChyba.setText("Penis si uz prihlaseny");
           jButton1.setVisible(false);
           jLabel1.setVisible(false);
           jLabel2.setVisible(false);
           jPasswordField1.setVisible(false);
           jTextMeno.setVisible(false);
           jLabelChyba.setVisible(false);
           jLabelKladka.setVisible(false);
       } else { 
           jLabelChyba.setText("Nepodarilo sa overit uzivatela!");
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelChyba;
    private javax.swing.JLabel jLabelKladka;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextMeno;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if( key == KeyEvent.VK_ENTER) jButton1.doClick();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      
    }
}
