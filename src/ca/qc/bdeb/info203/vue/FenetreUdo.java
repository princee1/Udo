/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info203.vue;

import ca.qc.bdeb.info203.modele.Udo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Classe vue Classe héritant de JFrame et implemente ActionListener Fentre
 * s'occupant d'afficher les informations de l'instance de la classe modèle ,
 * creer la grille
 * @author David Madzou
 */
public class FenetreUdo extends JFrame implements ActionListener {

    private JMenuBar mnuBar = new JMenuBar();
    private JLabel lblTitre = new JLabel("UDO", JLabel.CENTER);
    private JPanel pnlPrincipal = (JPanel) this.getContentPane();
    private JPanel pnlAffichage = new JPanel(new BorderLayout());
    private JPanel pnlStat = new JPanel(new FlowLayout());
    private JLabel lblCoupPhrase = new JLabel("-  Nombre de coup : ");
    private JLabel lblNumPartiePhrase = new JLabel("Partie# ");
    private JLabel lblNumPartie = new JLabel();
    private JLabel lblCoup = new JLabel();
    private JMenu mnuAide = new JMenu("Aide");
    private JMenu mnuFichier = new JMenu("Fichier");
    private JMenu mnuPartie = new JMenu("Partie");
    private Udo jeuUdo = new Udo();
    private GrilleUdo grille;
    private boolean partieEnCours;

    /**
     * Constructeur de la classe Modifie les valeurs de l'instance Ajoute les
     * objets aux panel respectif Appel de la methode initialiserMenuBar et la
     * methode Udo.chargerListePartie()(pour charger les parties Mot de
     * bienvenue Appel de la methode nouvellePartie de l'instance modele
     */
    public FenetreUdo() {

        this.setTitle("UDO");
        this.setVisible(true);
        this.setSize(800, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(mnuBar);

        lblTitre.setFont(new Font("Jokerman", Font.BOLD, 53));
        pnlPrincipal.setLayout(new BorderLayout());

        pnlStat.add(lblNumPartiePhrase);
        pnlStat.add(lblNumPartie);
        pnlStat.add(lblCoupPhrase);
        pnlStat.add(lblCoup);

        pnlAffichage.add(lblTitre, BorderLayout.NORTH);
        pnlAffichage.add(pnlStat);
        pnlPrincipal.add(pnlAffichage, BorderLayout.NORTH);

        initialiserMenuBar();
        Udo.chargerListePartie();
        JOptionPane.showMessageDialog(FenetreUdo.this, "BIENVENUE AU JEU UDO !!");
        jeuUdo.nouvellePartieHasard();
        creerPartie();

    }

    /**
     * Demande a l'utilisateur ce qu'il voudrait faire apres avoir terminé la
     * partie Appel de la methode nouvellePartieHasard de l'instance modele si
     * l'utilisateur veut en jouer une autre Quitte le programme s'il veut plus
     * jouer
     */
    private void savoirFinPartie() {
        if (partieEnCours) {
            partieEnCours = false;
            int restart;
            JOptionPane.showMessageDialog(this, "Félicitation vous avez terminé la partie\n"
                    + "Nombre de coup final: " + jeuUdo.getCptCoup() + "", "PARTIE TERMINE", 1);
            restart = JOptionPane.showConfirmDialog(FenetreUdo.this, "Voulez-vous refaire une partie", "Ensuite...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (restart == JOptionPane.YES_OPTION) {
                jeuUdo.nouvellePartieHasard();
                nouvellePartie();
            } else {
                int quitter = JOptionPane.showConfirmDialog(FenetreUdo.this, "Voulez-vous quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (quitter == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(FenetreUdo.this, "Au revoir !!");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Initialise les valeur dans le menuBar, donne les action aux item ajouté
     */
    private void initialiserMenuBar() {
        JMenuItem mnuPartieNouvellePhasard = new JMenuItem("Nouvelle Partie ");
        JMenuItem mnuPartieNouvellePchoisir = new JMenuItem("Choisir Partie...");
        JMenuItem mnuPartieNouvellePrecommencer = new JMenuItem("Recommencer ");

        JMenuItem mnuFichierQuitter = new JMenuItem("Quitter");
        JMenuItem mnuAideAide = new JMenuItem("Aide");
        JMenuItem mnuAideApropos = new JMenuItem("À propos");
        JMenuItem mnuAideAproposUdo = new JMenuItem("À propos - Udo");

        mnuAideAide.setAccelerator(KeyStroke.getKeyStroke("F1"));
        
        mnuPartieNouvellePhasard.setAccelerator(KeyStroke.getKeyStroke("F2"));
        mnuPartieNouvellePchoisir.setAccelerator(KeyStroke.getKeyStroke("F3"));
        mnuPartieNouvellePrecommencer.setAccelerator(KeyStroke.getKeyStroke("F4"));

        mnuFichierQuitter.addActionListener(new ActionListener() {
            
            /**
             * Quitte le programme a l'appel
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mnuAideAide.addActionListener(new ActionListener() {
            /**
             * Afficher a l'aide d'un JOptionPane la valeur de regle
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FenetreUdo.this, Udo.getRegle(), "Comment jouer...", 3);
            }
        });

        mnuAideAproposUdo.addActionListener(new ActionListener() {
            /**
             * Afficher a l'aide d'un JOptionPane la valeur de about
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FenetreUdo.this, Udo.getAbout(), "A propos - Udo", 1);
            }
        });
        mnuAideApropos.addActionListener(new ActionListener() {
            /**
             * Afficher a l'aide d'un JOptionPane la valeur de aboutMe
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FenetreUdo.this, Udo.getAboutMe(), "A propos", 1);
            }
        });

        mnuPartieNouvellePhasard.addActionListener(new ActionListener() {
            /**
             * Permet a l'utilisateur de faire une nouvelle partie au hasard
             * pendant une autre partie Appel de la methode nouvellePartieHasard
             * de l'instance modele
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partieEnCours) {
                    int nouvellePartie = JOptionPane.showConfirmDialog(FenetreUdo.this, "ATTENTION! - Si vous quitter, votre partie ne sera pas sauvegardée \nVoulez-vous vraiment faire une nouvelle partie?",
                            "Votre partie en cours n'est pas encore terminé...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (nouvellePartie == JOptionPane.YES_OPTION) {
                        jeuUdo.nouvellePartieHasard();
                        nouvellePartie();
                    }

                } else {
                    jeuUdo.nouvellePartieHasard();
                    nouvellePartie();
                }
            }
        });
        mnuPartieNouvellePchoisir.addActionListener(new ActionListener() {
            /**
             * Permet a l'utilisateur de faire une nouvelle partie (et de
             * choisir la partie) au hasard pendant une autre partie Appel de la
             * methode nouvellePartieChoisir de l'instance modele
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (partieEnCours) {
                    int nouvellePartie = JOptionPane.showConfirmDialog(FenetreUdo.this, "ATTENTION! - Si vous quitter, votre partie ne sera pas sauvegardée "
                            + "\nVoulez-vous vraiment faire une nouvelle partie?", "Votre partie en cours n'est pas encore terminée...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (nouvellePartie == JOptionPane.YES_OPTION) {
                        choisirPartie();
                    }

                } else {
                    choisirPartie();
                }
            }

            /**
             * Demande a l'utilisateur la partie qui voudrait jouer et gère les
             * exceptions liées
             */
            private void choisirPartie() {
                boolean erreur = true;
                String choisirPartie;
                do {
                    try {
                        choisirPartie = JOptionPane.showInputDialog(FenetreUdo.this, "Veuillez choisir une partie entre # 1 et #" + (Udo.getLsitePartie().size()), "Choisir votre partie", JOptionPane.OK_CANCEL_OPTION);
                        if (choisirPartie == null) {
                            erreur = false;
                        } else {
                            jeuUdo.nouvellePartieChoisir(Integer.parseInt(choisirPartie));
                            nouvellePartie();
                        }

                        erreur = false;
                    } catch (NumberFormatException ea) {

                        JOptionPane.showMessageDialog(FenetreUdo.this, "Erreur sur la saisie \n Veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);

                    } catch (IndexOutOfBoundsException ea) {
                        JOptionPane.showMessageDialog(FenetreUdo.this, "Dépacement des limites \n Veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);

                    }
                } while (erreur);
            }
        });

        mnuPartieNouvellePrecommencer.addActionListener(new ActionListener() {
            /**
             * Permet a l'utilisateur de recommencer la partie Appel de la
             * methode nouvellePartieHasard de l'instance modele
             *
             * @param e Non utilisé
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                int partieArestart = Udo.getLsitePartie().indexOf(jeuUdo.getTabActu());
                if (!lblCoup.getText().equals("0")) {
                    if (partieEnCours) {
                        int nouvellePartie = JOptionPane.showConfirmDialog(FenetreUdo.this, "ATTENTION! - Si vous quitter, votre partie ne sera pas sauvegardée \nVoulez-vous vraiment recommencer?",
                                "Votre partie en cours n'est pas encore terminée...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (nouvellePartie == JOptionPane.YES_OPTION) {
                            jeuUdo.nouvellePartieChoisir(partieArestart + 1);
                            nouvellePartie();
                        }

                    } else {

                        jeuUdo.nouvellePartieChoisir(partieArestart + 1);
                        nouvellePartie();
                    }

                }
            }
        });

        mnuPartie.add(mnuPartieNouvellePhasard);
        mnuPartie.add(mnuPartieNouvellePchoisir);
        mnuPartie.addSeparator();
        mnuPartie.add(mnuPartieNouvellePrecommencer);

        mnuFichier.add(mnuFichierQuitter);

        mnuAide.add(mnuAideAide);
        mnuAide.addSeparator();
        mnuAide.add(mnuAideApropos);
        mnuAide.add(mnuAideAproposUdo);

        mnuBar.add(mnuFichier);
        mnuBar.add(mnuPartie);
        mnuBar.add(mnuAide);
    }

    /**
     * Override la methode actionPerformed et appel les methodes necéssitant de
     * l'action pour bien faire fonctionner le programme
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!jeuUdo.getPartieFini()) {
            for (int i = 0; i < grille.getListeBtn().size(); i++) {
                if (e.getSource() == grille.getListeBtn().get(i)) {
                    grille.getListeBtn().get(i).actionIncreBtn();
                }
            }
            jeuUdo.incrementationCptCoup();
            lblCoup.setText(Integer.toString(jeuUdo.getCptCoup()));
            jeuUdo.setPartieFini(grille.ajouterElemenEtVerrificationtReponse());
        } else {
            savoirFinPartie();

        }
    }

    /**
     * Initialise les valeurs necéssitant a l'affichage lors d'une nouvelle
     * partie
     */
    private void creerPartie() {
        grille = new GrilleUdo(jeuUdo.getTabActu(), this);
        lblNumPartie.setText(Integer.toString(jeuUdo.getChoixPartie() + 1));
        lblCoup.setText(Integer.toString(0));
        pnlPrincipal.add(grille, BorderLayout.CENTER);
        partieEnCours = true;

    }

    /**
     * Enleve la grille actuelle et appel de jouerParite
     */
    private void nouvellePartie() {
        pnlPrincipal.remove(grille);
        grille = null;
        creerPartie();
    }

}
