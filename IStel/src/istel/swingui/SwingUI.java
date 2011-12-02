package istel.swingui;

import istel.Main;
import istel.swingui.formular.PridajKontakt;
import istel.swingui.formular.VyhladajKontakt;
import istel.swingui.formular.VymazKontakt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
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

    private javax.swing.JButton prihlasenieButton;
    private javax.swing.JButton odhlasenieButton;
    private javax.swing.JButton pridajKontaktButton;
    private javax.swing.JButton vymazKontaktButton;
    private javax.swing.JButton hladajKontaktButton;
    private javax.swing.JButton pridajObsluhaButton;
    private javax.swing.JButton vymazObsluhaButton;
    private VymazKontakt vymazKontakt;
    private PridajKontakt pridajKontakt;
<<<<<<< HEAD
    private VyhladajKontakt vyhladajKontakt;

=======
    private Timer timer;
>>>>>>> 2875bdcd4b8edb1634700cf244bfb4fdf65ce94e

    /** Creates new form SwingUI */
    public SwingUI() {
        prihlasenieButton = new javax.swing.JButton();
        odhlasenieButton = new javax.swing.JButton();
        pridajKontaktButton = new javax.swing.JButton();
        vymazKontaktButton = new javax.swing.JButton();
        hladajKontaktButton = new javax.swing.JButton();
        pridajObsluhaButton = new javax.swing.JButton();
        vymazObsluhaButton = new javax.swing.JButton();

        vymazKontakt = new VymazKontakt();
        pridajKontakt = new PridajKontakt();
<<<<<<< HEAD
        vyhladajKontakt = new VyhladajKontakt();
        
=======

>>>>>>> 2875bdcd4b8edb1634700cf244bfb4fdf65ce94e
        buttonsModifikator();
        initComponents();
        initCustomComponents();
        jPanelTelo.add(new PridajKontakt());
        jPanelTelo.updateUI();

        ActionListener timeListener = new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                initCustomComponents();
            }
        };
        timer = new Timer(100, timeListener);
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

        prihlasenieButton.setText("Prihlasenie");
        prihlasenieButton.setName("prihlasenieButton");
        prihlasenieButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prihlasenieActionPerformed(evt);
            }
        });

        odhlasenieButton.setText("Odhlasenie sa");
        odhlasenieButton.setName("odhlasenieButton");
        odhlasenieButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odhlasenieActionPerformed(evt);
            }
        });


        pridajKontaktButton.setText("Pridaj kontakt");
        pridajKontaktButton.setName("pridajKontaktButton");
        pridajKontaktButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pridajKontaktActionPerformed(evt);
            }
        });

        vymazKontaktButton.setText("Vymaz kontakt");
        vymazKontaktButton.setName("vymazKontaktButton");
        vymazKontaktButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vymazKontaktActionPerformed(evt);
            }
        });

        hladajKontaktButton.setText("Hladaj kontakt");
        hladajKontaktButton.setName("hladajKontaktButton");
        hladajKontaktButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hladajKontaktActionPerformed(evt);
            }
        });


        pridajObsluhaButton.setText("Pridaj obsluhu");
        pridajObsluhaButton.setName("pridajObsluhatButton");
        pridajObsluhaButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pridajObsluhaButtonActionPerformed(evt);
            }
        });

        vymazObsluhaButton.setText("Vymaz obsluhu");
        pridajObsluhaButton.setName("vymazObsluhaButton");
        pridajObsluhaButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vymazObsluhaButtonButtonActionPerformed(evt);
            }
        });

    }
<<<<<<< HEAD
    
   
    //tato trieda sa bude spustat po kazom evente v kazom buttone, aby sa zistilo ze ayk default skyn sa ma zobrazit
    private void initCustomComponents(){
        
        if(Main.getJadro().getUzivatel().jeAnonym()){
            //toto je prihlaseny anonymny uzivatel
            //anonymnyPouzivateMenu();
            obsluhaPouzivatelMenu();
            
        }else if(Main.getJadro().getUzivatel().jeObsluha()){
            //toto je prihlasena obsluha
            obsluhaPouzivatelMenu();
        }else if(Main.getJadro().getUzivatel().jeAdministrator()) {
            //toto je prihlaseny admin
            adminPouzivatelMenu();           
        }
        
   }
    
    
=======

>>>>>>> 2875bdcd4b8edb1634700cf244bfb4fdf65ce94e
    //vytvorenie prostredia pre anonymneho uzivatela, buttony a vlozenie do pola :)
    public void anonymnyPouzivateMenu() {

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(prihlasenieButton).addContainerGap(450, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(prihlasenieButton).addContainerGap(12, Short.MAX_VALUE)));
        //do spodneho panelu najeb formular na vytvorenie
        //stale volat nakonci pack();
        pack();
    }

    public void obsluhaPouzivatelMenu() {


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(odhlasenieButton).addGap(18, 18, 18).addComponent(pridajKontaktButton).addGap(18, 18, 18).addComponent(vymazKontaktButton).addGap(18, 18, 18).addComponent(hladajKontaktButton).addContainerGap(120, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(odhlasenieButton).addComponent(pridajKontaktButton).addComponent(vymazKontaktButton).addComponent(hladajKontaktButton)).addContainerGap(12, Short.MAX_VALUE)));
        pack();
    }

    public void adminPouzivatelMenu() {

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(odhlasenieButton).addGap(18, 18, 18).addComponent(pridajObsluhaButton).addGap(18, 18, 18).addComponent(vymazObsluhaButton).addContainerGap(236, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(odhlasenieButton).addComponent(pridajObsluhaButton).addComponent(vymazObsluhaButton)).addContainerGap(12, Short.MAX_VALUE)));
        pack();

    }

    //***********************************EVENTY NA TLACITKA*********************************//
    //event na prihlasenie sa uzivatela
    private void prihlasenieActionPerformed(ActionEvent evt) {
        jPanelTelo.removeAll();
        // jPanelTelo.add( new PridajKontakt() );
        jPanelTelo.updateUI();
        initCustomComponents();
<<<<<<< HEAD
      }
      
       //event na odhlasenie sa admina alebo obsluhy
       private void odhlasenieActionPerformed(ActionEvent evt) {
           //nastav premennu v jadre na anonyma
            Main.getJadro().odhlasit();
            initCustomComponents();
            jPanelTelo.removeAll();
            //jPanelTelo.add();
            jPanelTelo.updateUI();
            }
      
        //event na zavolanie formulara pre pridanie kontaktu
        private void pridajKontaktActionPerformed(ActionEvent evt) {
            jPanelTelo.removeAll();
            jPanelTelo.add(pridajKontakt);
            jPanelTelo.updateUI();
            initCustomComponents();
            }
        //event na zavolanie formulara pre zmazanie kontaktu
        private void vymazKontaktActionPerformed(ActionEvent evt) {
                jPanelTelo.removeAll();
                jPanelTelo.add(vymazKontakt);   
                jPanelTelo.updateUI();
                initCustomComponents();
            }
        //event na zavolanie formulara pre hladanie kontaktu
        private void hladajKontaktActionPerformed(ActionEvent evt) {
               jPanelTelo.removeAll();
               //pridaj okni na hladanie jPanelTelo.add();
               jPanelTelo.add(vyhladajKontakt);
               
               jPanelTelo.updateUI();
               initCustomComponents();
            }
        
        private void pridajObsluhaButtonActionPerformed(ActionEvent evt) {
                jPanelTelo.removeAll();
                //pridaj okno na pridanie obsluhy jPanelTelo.add()
                jPanelTelo.updateUI();
                initCustomComponents();
            }
        
        private void vymazObsluhaButtonButtonActionPerformed(ActionEvent evt) {
                jPanelTelo.removeAll();
                //pridaj okno na zmazanie obsluhy jPanelTelo.add()
                jPanelTelo.updateUI();
                initCustomComponents();
            }
      //*****************************KONIEC EVENTY TLACITKA**********************************//
=======
    }

    //event na odhlasenie sa admina alebo obsluhy
    private void odhlasenieActionPerformed(ActionEvent evt) {
        //nastav premennu v jadre na anonyma
        Main.getJadro().odhlasit();
        initCustomComponents();
        jPanelTelo.removeAll();
        //jPanelTelo.add(); hladanie dat
        jPanelTelo.updateUI();
    }

    //event na zavolanie formulara pre pridanie kontaktu
    private void pridajKontaktActionPerformed(ActionEvent evt) {
        jPanelTelo.removeAll();
        jPanelTelo.add(pridajKontakt);
        jPanelTelo.updateUI();
        initCustomComponents();
    }
    //event na zavolanie formulara pre zmazanie kontaktu

    private void vymazKontaktActionPerformed(ActionEvent evt) {
        jPanelTelo.removeAll();
        jPanelTelo.add(vymazKontakt);
        jPanelTelo.updateUI();
        initCustomComponents();
    }
    //event na zavolanie formulara pre hladanie kontaktu

    private void hladajKontaktActionPerformed(ActionEvent evt) {
        jPanelTelo.removeAll();
        //jPanelTelo.add(vymazKontakt);
        jPanelTelo.updateUI();
        initCustomComponents();
    }

    private void pridajObsluhaButtonActionPerformed(ActionEvent evt) {
        jPanelTelo.removeAll();
        //pridaj okno na pridanie obsluhy jPanelTelo.add()
        jPanelTelo.updateUI();
        initCustomComponents();
    }

    private void vymazObsluhaButtonButtonActionPerformed(ActionEvent evt) {
        jPanelTelo.removeAll();
        //pridaj okno na zmazanie obsluhy jPanelTelo.add()
        jPanelTelo.updateUI();
        initCustomComponents();
    }
    //*****************************KONIEC EVENTY TLACITKA**********************************//
>>>>>>> 2875bdcd4b8edb1634700cf244bfb4fdf65ce94e

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelTelo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(254, 254, 254));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jPanelTelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTelo, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelTelo;
    // End of variables declaration//GEN-END:variables
}
