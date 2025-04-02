/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info203.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Classe modèle
 * Coeur du programme, s'occupe des données de chaque partie
 * @author David Madzou
 */
public class Udo {

    private static ArrayList<String[][]> listePartie = new ArrayList();
    private static final int NB_COLONNE = 4;
    private static final int NB_RANGEE = 4;
    private static final String tabStringSolution[] = {"1", "2", "3", "4"};
    private static ArrayList<String> listeSolution = new ArrayList();
    private static final int NB_VERRIFICATION_GAGNER = 12;
    private static String aboutMe;
    private static String regle;
    private static String about;
    private int choixPartie;
    private String[][] tabPartieActu;
    private int cptCoup;
    private boolean partieFin;

    /**
     * Charge les valeurs du fichier "partie.txt" et les mets dans sa liste et
     * gère les execptions liés
     */
    public static void chargerListePartie() {

        BufferedReader lecture;
        String line;
        String tabPartieTemp[][];
        ArrayList<String> listeTemp = new ArrayList();

        try {
            lecture = new BufferedReader(new FileReader("partie.txt"));
            line = lecture.readLine();
            while (line != null) {
                if (!line.equals("---")) {
                    listeTemp.add(line);
                }
                line = lecture.readLine();
            }
            lecture.close();

            while (!listeTemp.isEmpty()) {
                tabPartieTemp = new String[4][4];
                for (int i = 0; i < tabPartieTemp.length; i++) {
                    for (int j = 0; j < tabPartieTemp.length; j++) {
                        tabPartieTemp[i][j] = Character.toString(listeTemp.get(i).charAt(j));
                    }
                }
                for (int i = 0; i < tabPartieTemp.length; i++) {
                    listeTemp.remove(0);
                }
                listePartie.add(tabPartieTemp);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Probleme de chargement de la grille\n Veuiller ressayer plus tard", "ERREUR", JOptionPane.ERROR_MESSAGE);

            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Probleme de chargement de la grille\n Veuiller ressayer plus tard", "ERREUR", JOptionPane.ERROR_MESSAGE);

            System.exit(0);
        }
        remplirListeReponse();

    }

    /**
     * Appel la methode initiatialiser et donne une valeur de la listepartie
     * choisi dans la variable tabPartieActu qui est le tableau de la partie
     * actuelle
     *
     */
    private void jouerPartie() {

        initialisation();
        tabPartieActu = listePartie.get(choixPartie);

    }

    /**
     * Appel de la methode jouer() et choisi une valeur au hasard entre 0 et la
     * taille de la listePartie qui sera la position de la partie de la
     * listePartie et gère les execptions liés
     */
    public void nouvellePartieHasard() {
        Random rnd = new Random();
        try {
            choixPartie = rnd.nextInt(listePartie.size());
            jouerPartie();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Probleme de chargement de la partie\n Veuiller ressayer plus tard", "ERREUR", JOptionPane.ERROR);

        }

    }

    /**
     * Appel la methode modifierChoixPartie et appel la methode jouerPartie
     *
     * @param choixPartie La valeur que l'utilisateur a entrer
     */
    public void nouvellePartieChoisir(int choixPartie) {
        modifierChoixPartie(choixPartie);
        jouerPartie();
    }

    /**
     * Permet la modification de variable choixPartie qui est la postion d'une
     * partie dans la liste de partie
     *
     * @param choixPartie La valeur que l'utilisateur a entrer
     */
    private void modifierChoixPartie(int choixPartie) {

        this.choixPartie = choixPartie - 1;

    }

    /**
     * Initialise les valeurs cptCoup et partieFin au valeur necessaire pour
     * chaque nouvelle partie
     */
    private void initialisation() {
        cptCoup = 0;
        partieFin = false;
    }

    /**
     * Remplie la listeSolution avec les valeur de tabStringSolution
     */
    private static void remplirListeReponse() {
        listeSolution.addAll(Arrays.asList(tabStringSolution));
    }

    /**
     * Incremente la valeur de cptCoup d'une unité
     */
    public void incrementationCptCoup() {
        cptCoup++;
    }

    /**
     * Permet l'accessibilite de la valeur NB_COLONNE à l'exterieur de cette
     * classe
     *
     * @return la valeur de NB_COLONNE
     */
    public static int getColonne() {
        return NB_COLONNE;
    }

    /**
     * Permet l'accessibilite de la valeur NB_RANGEE à l'exterieur de cette
     * classe
     *
     * @return la valeur de NB_RANGEE
     */
    public static int getRangee() {
        return NB_RANGEE;
    }

    /**
     * Permet l'accessibilite de la valeur NB_VERRIFICATION_MAX_GAGNER à
     * l'exterieur de cette classe
     *
     * @return NB_VERRIFICATION_MAX_GAGNER
     */
    public static int getNbVerrifiGagner() {
        return NB_VERRIFICATION_GAGNER;
    }

    /**
     * Initalise la valeur regle Permet l'accessibilite de la valeur regle à
     * l'exterieur de cette classe
     *
     * @return La valeur
     */
    public static String getRegle() {
        regle = "                                                                  Règles - Udo\n"
                + "L’Udo se joue à l’aide d’une grille de 4 x 4. Chaque case contient une valeur numérique de 1 à 4.\n"
                + "Une solution valide comporte les caractéristiques suivantes :\n\n"
                + "Chaque ligne comporte les valeurs 1-2-3-4, dans n’importe quel ordre.\n"
                + "Chaque colonne comporte les valeurs 1-2-3-4, dans n’importe quel ordre.\n"
                + "Les quatre cases de chaque quadrant (coin) comportent les valeurs 1-2-3-4, dans n’importe quel ordre.";

        return regle;
    }

    /**
     * Permet l'accessibilite de la valeur listePartie à l'exterieur de cette
     * classe
     *
     * @return La valeur de listePartie
     */
    public static ArrayList<String[][]> getLsitePartie() {
        return listePartie;
    }

    /**
     * Permet l'accessibilite de la valeur de listeSolution à l'exterieur de
     * cette classe
     *
     * @return La valeur de listeSolution
     */
    public static ArrayList<String> getListeSolution() {
        return listeSolution;
    }

    /**
     * Initalise la valeur about Permet l'accessibilite de la valeur about à
     * l'exterieur de cette classe
     *
     * @return La valeur de about
     */
    public static String getAbout() {

        about = "De plus en plus d’études encore plus récentes semblent indiquer que les\n "
                + "habitués de Sudoku sont plus susceptibles de souffrir de dépression,\n"
                + "de troubles d’anxiété,d’hypertension, de décollement de la rétine, de \n"
                + "tendinites et de combustion spontanée.\n\n"
                + "Le Udo épargne ces ennuis à la nouvelle génération,optez pour une version\n"
                + "moins dangereuse du Sudoku. ";

        return about;
    }

    /**
     * Initalise la valeur aboutMe Permet l'accessibilite de la valeur about à
     * l'exterieur de cette classe
     *
     * @return La valeur aboutMe
     */
    public static String getAboutMe() {
        aboutMe = "Udo\n"
                + "Developer: Prince Madzou\n"
                + "Genre: Self Entertainment\n"
                + "Version: 64\n"
                + "Copyright PrinceCorporation 2019 All right reserved\n";

        return aboutMe;
    }

    /**
     * Permet l'accessibilite de la valeur de cptCoup à l'exterieur de cette
     * classe
     *
     * @return La valeur de cptCoup
     */
    public int getCptCoup() {
        return cptCoup;
    }

    /**
     * Permet l'accessibilite de la valeur de tabPartieActu à l'exterieur de
     * cette classe
     *
     * @return La valeur de tabPartieActu
     */
    public String[][] getTabActu() {
        return tabPartieActu;
    }

    /**
     * Permet l'accessibilite de la valeur de choixPartie à l'exterieur de cette
     * classe
     *
     * @return La valeur de choixPartie
     */
    public int getChoixPartie() {
        return choixPartie;
    }

    /**
     * Permet l'accessibilite de la valeur de partieFin à l'exterieur de cette
     * classe
     *
     * @return La valeur de partieFin
     */
    public boolean getPartieFini() {
        return partieFin;

    }

    /**
     * Permet la modification exterieur de la valeur de partieFin
     *
     * @param partieFin La valeur qu'une autre classe modifira
     */
    public void setPartieFini(boolean partieFin) {
        this.partieFin = partieFin;

    }

}
