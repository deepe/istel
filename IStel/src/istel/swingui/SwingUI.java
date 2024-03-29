package istel.swingui;

import istel.Main;
import istel.swingui.formular.PridajKontakt;
import istel.swingui.formular.PrihlasitSa;
import istel.swingui.formular.VyhladajKontakt;
import istel.swingui.formular.VymazKontakt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SwingUI.java
 *
 * Created on 30.11.2011, 2:22:43
 */
/**
 *
 * @author t0ki
 */
/*
 * javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addComponent(jButton1)
.addGap(18, 18, 18)
.addComponent(jButton2)
.addGap(18, 18, 18)
.addComponent(jButton4)
.addGap(18, 18, 18)
.addComponent(jButton3)
.addContainerGap(124, Short.MAX_VALUE))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton1)
.addComponent(jButton2)
.addComponent(jButton4)
.addComponent(jButton3))
.addContainerGap(12, Short.MAX_VALUE))
);

-------------------------------------------------
javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addComponent(jButton1)
.addGap(18, 18, 18)
.addComponent(jButton2)
.addGap(18, 18, 18)
.addComponent(jButton4)
.addContainerGap(236, Short.MAX_VALUE))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton1)
.addComponent(jButton2)
.addComponent(jButton4))
.addContainerGap(12, Short.MAX_VALUE))
);

 * ----------------------------------------------------------
javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addComponent(jButton1)
.addGap(18, 18, 18)
.addComponent(jButton2)
.addContainerGap(348, Short.MAX_VALUE))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton1)
.addComponent(jButton2))
.addContainerGap(12, Short.MAX_VALUE))
);
 * --------------------------------------------------------
 * 
 */
public class SwingUI extends javax.swing.JFrame {

    private javax.swing.JButton buttonPrihlasenie;
    private javax.swing.JButton buttonOdhlasenie;
    private javax.swing.JButton buttonPridajKonstakt;
    private javax.swing.JButton buttonVymazKonstakt;
    private javax.swing.JButton buttonVyhladajKontakt;
    private javax.swing.JButton buttonPridajObsluha;
    private javax.swing.JButton buttonVymazObsluha;
    private JPanel jPanelPrihlasitSa, jPanelVyhladajKontakt, jPanelVymazKontakt, jPanelPridajKontakt;
    private Timer timer;
    private String borderName;

    /** Creates new form SwingUI */
    public SwingUI() {
        buttonPrihlasenie = new javax.swing.JButton();
        buttonPrihlasenie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/obrazky/loginIcon.png")));
        buttonOdhlasenie = new javax.swing.JButton();
        buttonOdhlasenie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/obrazky/logoutIcon.png")));
        buttonPridajKonstakt = new javax.swing.JButton();
        buttonPridajKonstakt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/obrazky/ikona-32-novy.png")));
        buttonVymazKonstakt = new javax.swing.JButton();
        buttonVymazKonstakt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/obrazky/ikona-32-zmaz.png")));
        buttonVyhladajKontakt = new javax.swing.JButton();
        buttonVyhladajKontakt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/obrazky/ikona-32-vyhladaj.png")));
        buttonPridajObsluha = new javax.swing.JButton();
        buttonVymazObsluha = new javax.swing.JButton();
        borderName = "";

        jPanelPrihlasitSa = new PrihlasitSa();
        jPanelVyhladajKontakt = new VyhladajKontakt();
        jPanelPridajKontakt = new PridajKontakt();
        jPanelVymazKontakt = new VymazKontakt();
        
        

        buttonsModifikator();
        initComponents();
        initCustomComponents();
        jPanelTelo.add(jPanelVyhladajKontakt);
        jPanelTelo.setBorder(javax.swing.BorderFactory.createTitledBorder("Hladaj kontakt")); //natvrdo nabite na prvu inicializaciu
        jPanelTelo.updateUI();

        ActionListener timeListener = new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                initCustomComponents();
            }
        };
        timer = new Timer(2000, timeListener);
        timer.start();
    }

    //tato trieda sa bude spustat po kazom evente v kazom buttone, aby sa zistilo ze ayk default skyn sa ma zobrazit
    private void initCustomComponents() {
        
        if (Main.getJadro().getUzivatel().jeAnonym()) {
            //toto je prihlaseny anonymny uzivatel
            anonymnyPouzivateMenu();
        } else if (Main.getJadro().getUzivatel().jeObsluha()) {
            //toto je prihlasena obsluha
            obsluhaPouzivatelMenu();
        } else if (Main.getJadro().getUzivatel().jeAdministrator()) {
            //toto je prihlaseny admin
            adminPouzivatelMenu();
        }

    }

    private void buttonsModifikator() {
        buttonPrihlasenie.setText("Prihlasenie");
        buttonPrihlasenie.setName("prihlasenieButton");
        buttonPrihlasenie.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prihlasenieActionPerformed(evt);
            }
        });

        buttonOdhlasenie.setText("Odhlasenie sa");
        buttonOdhlasenie.setName("odhlasenieButton");
        buttonOdhlasenie.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odhlasenieActionPerformed(evt);
            }
        });


        buttonPridajKonstakt.setText("Pridaj kontakt");
        buttonPridajKonstakt.setName("Pridaj kontakt");
        buttonPridajKonstakt.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pridajKontaktActionPerformed(evt);
            }
        });

        buttonVymazKonstakt.setText("Vymaz kontakt");
        buttonVymazKonstakt.setName("vymazKontaktButton");
        buttonVymazKonstakt.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vymazKontaktActionPerformed(evt);
            }
        });

        buttonVyhladajKontakt.setText("Hladaj kontakt");
        buttonVyhladajKontakt.setName("hladajKontaktButton");
        buttonVyhladajKontakt.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hladajKontaktActionPerformed(evt);
            }
        });


        //buttonPridajObsluha.setText("Pridaj obsluhu");
        //buttonPridajObsluha.setName("pridajObsluhatButton");
        buttonPridajObsluha.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pridajObsluhaButtonActionPerformed(evt);
            }
        });

        //buttonVymazObsluha.setText("Vymaz obsluhu");
        //buttonPridajObsluha.setName("vymazObsluhaButton");
        buttonPridajObsluha.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vymazObsluhaButtonButtonActionPerformed(evt);
            }
        });

    }

    //vytvorenie prostredia pre anonymneho uzivatela, buttony a vlozenie do pola :)
    public void anonymnyPouzivateMenu() {
        jPanelMenu.removeAll();
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(buttonPrihlasenie).addContainerGap(450, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(buttonPrihlasenie).addContainerGap(12, Short.MAX_VALUE)));
        //do spodneho panelu najeb formular na vytvorenie
        //stale volat nakonci pack();
        //pack();
    }

    public void obsluhaPouzivatelMenu() {
        jPanelMenu.removeAll();
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(buttonOdhlasenie).addGap(18, 18, 18).addComponent(buttonPridajKonstakt).addGap(18, 18, 18).addComponent(buttonVymazKonstakt).addGap(18, 18, 18).addComponent(buttonVyhladajKontakt).addContainerGap(120, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(buttonOdhlasenie).addComponent(buttonPridajKonstakt).addComponent(buttonVymazKonstakt).addComponent(buttonVyhladajKontakt)).addContainerGap(12, Short.MAX_VALUE)));
        
        pack();
    }

    public void adminPouzivatelMenu() {
        jPanelMenu.removeAll();
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(buttonOdhlasenie).addGap(18, 18, 18).addComponent(buttonPridajObsluha).addGap(18, 18, 18).addComponent(buttonVymazObsluha).addContainerGap(236, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(buttonOdhlasenie).addComponent(buttonPridajObsluha).addComponent(buttonVymazObsluha)).addContainerGap(12, Short.MAX_VALUE)));
       
        pack();
    }

    //***********************************EVENTY NA TLACITKA*********************************//
    //event na prihlasenie sa uzivatela
    private void prihlasenieActionPerformed(ActionEvent evt) {
//        jPanelTelo.removeAll();
//        jPanelTelo.add( prihlasitSa);
//        jPanelTelo.updateUI();
//        initCustomComponents();
        borderName = "Prihlásenie";
        this.zobrazFromular(new PrihlasitSa());
    }

    //event na odhlasenie sa admina alebo obsluhy
    private void odhlasenieActionPerformed(ActionEvent evt) {
        //nastav premennu v jadre na anonyma
        Main.getJadro().odhlasit();
        this.zobrazFromular(jPanelVyhladajKontakt);
        borderName = "Hľadaj kontakt";
    }

    //event na zavolanie formulara pre pridanie kontaktu
    private void pridajKontaktActionPerformed(ActionEvent evt) {
        borderName = "Pridaj kontakt";
        this.zobrazFromular(jPanelPridajKontakt);
    }
    //event na zavolanie formulara pre zmazanie kontaktu

    private void vymazKontaktActionPerformed(ActionEvent evt) {
        borderName ="Vymaž kontakt";
        this.zobrazFromular(jPanelVymazKontakt);
    }
    //event na zavolanie formulara pre hladanie kontaktu

    private void hladajKontaktActionPerformed(ActionEvent evt) {
        borderName ="Hľadaj kontakt";
        this.zobrazFromular(jPanelVyhladajKontakt);

    }

    private void pridajObsluhaButtonActionPerformed(ActionEvent evt) {
//        jPanelTelo.removeAll();
//        //pridaj okno na pridanie obsluhy jPanelTelo.add()
//        jPanelTelo.updateUI();
//        initCustomComponents();
        //this.zobrazFromular(new PridajObsluha());
    }

    private void vymazObsluhaButtonButtonActionPerformed(ActionEvent evt) {
//        jPanelTelo.removeAll();
//        //pridaj okno na zmazanie obsluhy jPanelTelo.add()
//        jPanelTelo.updateUI();
//        initCustomComponents();
        //this.zobrazFromular(new VymazObsluha());
    }

    private void zobrazFromular(JPanel panel) {
        jPanelTelo.removeAll();
        jPanelTelo.add(panel);
        jPanelTelo.updateUI();
        jPanelTelo.setBorder(javax.swing.BorderFactory.createTitledBorder(borderName));
    }

    //*****************************KONIEC EVENTY TLACITKA**********************************//
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenu = new javax.swing.JPanel();
        jPanelTelo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(254, 254, 254));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        jPanelMenu.setName("jPanelMenu"); // NOI18N

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jPanelTelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelTelo.setName("jPanelTelo"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTelo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Main.getJadro().odpojdDB();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelTelo;
    // End of variables declaration//GEN-END:variables
}
