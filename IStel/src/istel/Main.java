package istel;

import istel.jadro.OdhlasCuvac;
import istel.swingui.SwingUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author t0ki
 */
public class Main {

    public static Jadro jadro = null;

    public static Jadro getJadro() {
        if (jadro == null) {
            jadro = new Jadro();
        }
        return jadro;
    }

    public static void main(String[] args) {
        getJadro();
        new Thread(new OdhlasCuvac()).start();
        SwingUI okno = new SwingUI();
        okno.setVisible(true);
        //TODO: odriesit zatvorenie spojenia db
        // ak sa zatvori okno zavolat jadro.odpojDB();

    }
}
