/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info203.vue;

import ca.qc.bdeb.info203.modele.Udo;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;

/**
 * Classe vue Classe héritant de JButton s'occupant des buttons
 * @author David Madzou
 */
public class ButtonGrilleUdo extends JButton {

    private int x = 0;
    private static final Font font = new Font("Jokerman", Font.BOLD, 35);
    private static final String STRING_DEPART = " ";

    /**
     * Constructeur de la classe
     * @param text Texte mis au button
     */
    public ButtonGrilleUdo(String text) {
        this.setText(text);
        this.setFont(font);
    }

    /**
     * Augmente la valeur d'une unite et recommence a 1 quand c'est egal a 5 et
     * le met en String et modifie le texte du button
     */
    public void actionIncreBtn() {
        x++;
        if (x == 5) {
            x = 1;
        }
        this.setText(Integer.toString(x));
    }

    /**
     * Permet l'accessiblite de la valeur STRING_DEPART au classe exterieur
     * @return La valeur STRING_DEPART
     */
    public static String getStringDepart() {
        return STRING_DEPART;
    }

    /**
     * Déterminier la couleur dépendament du nombre de ligne, colonne et
     * quadrant qui sont bonne
     * @param nbL Nombre de ligne
     * @param nbC Nombre de colonne
     * @param nbQ Nombre de quadrant
     */
    private void savoirColor(int nbL, int nbC, int nbQ) {
        int nbTotal = nbC + nbL + nbQ;
        if (nbC >= 1 && nbL >= 1 && nbQ >= 1) {
            this.setForeground(Color.GREEN.darker().darker().darker());
        } else if ((nbC >= 1 && nbL >= 1) || (nbC >= 1 && nbQ >= 1) || (nbQ >= 1 && nbL >= 1)) {
            this.setForeground(Color.GREEN.darker());
        } else if (nbTotal == 1) {
            this.setForeground(Color.GREEN.brighter().brighter().brighter());
        } else if (nbTotal == 0) {
            this.setForeground(Color.BLACK);

        }

    }

    /**
     * Modifie la couleur du buttons depenadement du nombre de verification correctes
     * @param tabTempL tableau de string contenat les valeur d'une ligne 
     * @param tabTempC tableau de string contenat les valeur d'une colonne 
     * @param tabTempQ tableau de string contenat les valeur d'un  quadreant 
     */
    public void modificationCouleur(String[] tabTempL, String[] tabTempC, String[] tabTempQ) {

        int nbC = 0, nbL = 0, nbQ = 0;
        ArrayList<String> listeTempL = new ArrayList<String>(Arrays.asList(tabTempL));
        ArrayList<String> listeTempC = new ArrayList<String>(Arrays.asList(tabTempC));
        ArrayList<String> listeTempQ = new ArrayList<String>(Arrays.asList(tabTempQ));


        if (listeTempL.containsAll(Udo.getListeSolution())) {
            nbL++;
        }
        if (listeTempC.containsAll(Udo.getListeSolution())) {
            nbC++;
        }
        if (listeTempQ.containsAll(Udo.getListeSolution())) {
            nbQ++;
        }
        savoirColor(nbL, nbC, nbQ);

    }
}
