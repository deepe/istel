/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package istel.jadro;

import istel.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t0ki
 */
public class OdhlasCuvac implements Runnable {
    private boolean running;
    public OdhlasCuvac() {
        running = true;
    }
    
    @Override
    public void run() {
        while(running)
            Main.getJadro().skontrolujAktivitu();
        try {
            ((java.lang.Object) this).wait(600l);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void stop() {
        running = false;
    }
}
