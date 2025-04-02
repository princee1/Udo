/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info203.main;

import ca.qc.bdeb.info203.vue.FenetreUdo;
import javax.swing.UIManager;

/**
 *
 * @author 1869155
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         String unLook = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        try {
          UIManager.setLookAndFeel(unLook);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

       FenetreUdo fentreUdo = new FenetreUdo();
        
        
        
        
        
    }
    
}
