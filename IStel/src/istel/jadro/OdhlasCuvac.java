/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package istel.jadro;

import istel.Main;

/**
 *
 * @author t0ki
 */
public class OdhlasCuvac implements Runnable {

    public void run() {
        Main.jadro.skontrolujAktivitu();
    }
    
}
