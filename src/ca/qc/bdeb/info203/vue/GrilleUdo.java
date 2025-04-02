/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info203.vue;

import ca.qc.bdeb.info203.modele.Udo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Classe vue Classe héritant de la classe JPanel et s'occupe de creer les
 * buttons, leurs donner leur action, verfie sa propre grille
 * @author David Madzou
 */
public class GrilleUdo extends JPanel {

    private ButtonGrilleUdo[][] tabButton = new ButtonGrilleUdo[Udo.getRangee()][Udo.getColonne()];
    private static Dimension dmsGrille = new Dimension(600, 500);
    private ArrayList<ButtonGrilleUdo> listeBtnGrille = new ArrayList();

    /**
     * Constructeur de la classe Grille Creer une grille 4x4 (GridLayout) et
     * place les valeurs de tabPartie dans les btn et détermnier si un button
     * est clickable ou pas
     *
     * @param tabPartie Partie passé en parametre
     * @param e Action passé en parametre
     */
    public GrilleUdo(String tabPartie[][], ActionListener e) {
        this.setLayout(new GridLayout(Udo.getRangee(), Udo.getColonne()));
        this.setPreferredSize(dmsGrille);
        int cptNbButtons = 0;
        for (int i = 0; i < tabPartie.length; i++) {
            for (int j = 0; j < tabPartie.length; j++) {
                if (tabPartie[i][j].equals(" ")) {
                    listeBtnGrille.add(new ButtonGrilleUdo(ButtonGrilleUdo.getStringDepart()));
                    listeBtnGrille.get(cptNbButtons).addActionListener(e);
                    this.tabButton[i][j] = listeBtnGrille.get(cptNbButtons);
                    this.add(tabButton[i][j]);
                    cptNbButtons++;
                } else {
                    this.tabButton[i][j] = new ButtonGrilleUdo(tabPartie[i][j]);
                    this.tabButton[i][j].setForeground(Color.DARK_GRAY);
                    this.tabButton[i][j].setEnabled(false);
                    this.add(tabButton[i][j]);
                }

            }

        }

    }

    /**
     * Permet l'accessiblite au classe exterieur de la valeur tabBtn
     *
     * @return Le tableau de button
     */
    public ButtonGrilleUdo[][] getTabButton() {
        return tabButton;
    }

    /**
     * Permet l'accessiblite au classe exterieur de la variable listeBtn
     * @return La liste de button
     */
    public ArrayList<ButtonGrilleUdo> getListeBtn() {
        return listeBtnGrille;
    }

    /**
     * Ajoute les valeurs de la grille dans des tableau temporaire passe en
     * parametre pour verifier si chaque buttons verrifie les condition et appel
     * la methode pour changer les couleurs et verifier les conditions.si la
     * grille est conforme, dit si la partie est fini ou pas
     *
     * @return La valeur de partieFin
     */
    public boolean ajouterElemenEtVerrificationtReponse() {

        ArrayList<String> listeStrinRepLigne = new ArrayList();
        ArrayList<String> listeStrinRepColonne = new ArrayList();
        ArrayList<String> listeStrinRepQuadrant = new ArrayList();

        ArrayList<String> listeTempL = new ArrayList();
        ArrayList<String> listeTempC = new ArrayList();
        ArrayList<String> listeTempQ = new ArrayList();

        String tabStringTempL[] = new String[4];
        String tabStringTempC[] = new String[4];
        String tabStringTempQ[] = new String[4];

        ArrayList<String[]> listeL1 = new ArrayList();
        ArrayList<String[]> listeC1 = new ArrayList();
        ArrayList<String[]> listeQ1 = new ArrayList();

        final int LISTE_SIZE_A_B_C = 16;

        int nbL = 0;
        int nbC = 0;
        int nbQ = 0;

        for (int i = 0; i < tabButton.length; i++) {
            for (int j = 0; j < tabButton.length; j++) {
                listeStrinRepLigne.add(tabButton[i][j].getText());
                listeStrinRepColonne.add(tabButton[j][i].getText());
            }
        }

        for (int i = 0; i < (LISTE_SIZE_A_B_C / 4); i++) {
            listeStrinRepQuadrant.add(listeStrinRepLigne.get((i)));
            listeStrinRepQuadrant.add(listeStrinRepLigne.get((i + 4)));
        }
        for (int i = 8; i < (LISTE_SIZE_A_B_C - 4); i++) {
            listeStrinRepQuadrant.add(listeStrinRepLigne.get((i)));
            listeStrinRepQuadrant.add(listeStrinRepLigne.get((i + 4)));
        }

        for (int i = 0; i < LISTE_SIZE_A_B_C; i++) {
            listeTempL.add(listeStrinRepLigne.get(i));
            listeTempC.add(listeStrinRepColonne.get(i));
            listeTempQ.add(listeStrinRepQuadrant.get(i));
            if ((i + 1) % 4 == 0) {

                if (listeTempL.containsAll(Udo.getListeSolution())) {
                    nbL++;
                }
                if (listeTempC.containsAll(Udo.getListeSolution())) {
                    nbC++;
                }
                if (listeTempQ.containsAll(Udo.getListeSolution())) {
                    nbQ++;
                }

                for (int j = 0; j < tabStringTempL.length; j++) {
                    tabStringTempL[j] = listeTempL.get(j);
                    tabStringTempC[j] = listeTempC.get(j);
                    tabStringTempQ[j] = listeTempQ.get(j);

                }
                listeL1.add(tabStringTempL);
                listeC1.add(tabStringTempC);
                listeQ1.add(tabStringTempQ);

                for (int j = 0; j < tabStringTempQ.length; j++) {
                    listeTempL.remove(0);
                    listeTempC.remove(0);
                    listeTempQ.remove(0);

                }
                tabStringTempL = new String[4];
                tabStringTempC = new String[4];
                tabStringTempQ = new String[4];

            }
        }
        changementColorBtn(listeL1, listeC1, listeQ1);

        boolean partieFin = false;
        listeStrinRepLigne.clear();
        listeStrinRepColonne.clear();
        listeStrinRepQuadrant.clear();
        int nbTotal = nbC + nbL + nbQ;
        if (nbTotal == Udo.getNbVerrifiGagner()) {
            partieFin = true;
        }
        return partieFin;
    }

    /**
     * Appelle pour chaque button la methode modificationCouleur pour changer
     * les couleurs depenandement des valeurs de la grille
     *
     * @param listeL1 Liste de tableau contenant chacun les valeurs d'une ligne
     * de la matrice
     * @param listeC1 Liste de tableau contenant chacun les valeurs d'une
     * colonne de la matrice
     * @param listeQ1 Liste de tableau contenant chacun les valeurs d'un
     * quadreant de la matrice
     */
    private void changementColorBtn(ArrayList<String[]> listeL1, ArrayList<String[]> listeC1, ArrayList<String[]> listeQ1) {
        for (int i = 0; i < tabButton.length; i++) {
            for (int j = 0; j < tabButton.length; j++) {

                if (i < 2 && j < 2) {
                    tabButton[i][j].modificationCouleur(listeL1.get(i), listeC1.get(j), listeQ1.get(0));
                } else if (i >= 2 && j < 2) {
                    tabButton[i][j].modificationCouleur(listeL1.get(i), listeC1.get(j), listeQ1.get(2));
                } else if (i < 2 && j >= 2) {
                    tabButton[i][j].modificationCouleur(listeL1.get(i), listeC1.get(j), listeQ1.get(1));
                } else if (i >= 2 && j >= 2) {
                    tabButton[i][j].modificationCouleur(listeL1.get(i), listeC1.get(j), listeQ1.get(3));
                }

            }
        }
    }
}
