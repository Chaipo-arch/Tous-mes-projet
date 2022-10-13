/*
 * GestionInterface.java                  21/05/2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
 package Gestion;

import java.io.IOException;

/**
  * Gère l'interface graphique de l'application Agenda
  * Les interactions se déroulent via la ligne de commande 
  * @author pierre.farret
  * @author valentin.simon
  */
public class GestionInterface {
    
    /** Définit le nombre de rendez-vous à afficher par page */
    private static final int NB_RDV_A_AFFICHER = 3;
    
    /** Titre et choix du menu principal */
    private static final String[] MENU_PRINCIPAL = {
                "AGENDA",
                "Consulter les rendez-vous",
                "Gestion des rendez-vous",
                "Quitter l'application"            
    };
    /** Options possibles du menu principal */
    private static final char[] OPTION_MENU_PRINCIPAL  =
                {'\000', 'c', 'g', 'q' };
    
    
    /** Titre et choix du menu consultation */
    private static final String[] MENU_CONSULTATION = {
                "MODE CONSULTATION",
                "Consulter les rendez-vous d'une journée",
                "Consulter tous les rendez-vous",
                "Retour"
    };
    /** Options possibles du menu consultation */
    private static final char[] OPTION_MENU_CONSULTATION  =
                {'\000', 'j', 't', 'q' };
    
    /** Titre et choix du menu consultation */
    private static final String[] MENU_MODIFICATION = {
                "MODE MODIFICATION",
                "Ajouter un rendez-vous",
                "Supprimer un rendez-vous",
                "Déplacer un rendez-vous",
                "Modifier un rendez-vous",
                "Réinitialiser les données",
                "Retour"
    };
    /** Options possibles du menu modification */
    private static final char[] OPTION_MENU_MODIFICATION  =
                {'\000', 'a', 'n', 'd', 'm', 'r', 'q' };
    
    /** Titre et choix du menu confirmer */
    private static final String[] MENU_CONFIRMER =
                { null, "Oui", "Non" };
    /** Options possibles du menu confirmer */
    private static final char[] OPTION_MENU_CONFIRMER  =
                {'\000', 'o', 'n' };
    
    /** Titre et choix du menu d'affichage des rendez-vous */
    private static final String[][] AFFICHAGE_RDV = {
                { null, "Page suivante", "Page précédente", "Retour" },
                { null, "Page suivante", "Retour" },
                { null, "Page précédente", "Retour" },
                { null, "Retour" }
    };
    /** Options possibles du menu d'affichage des rendez-vous */
    private static final char[][] OPTION_AFFICHAGE_RDV = {
                {'\000', 's', 'p', 'q' },
                {'\000', 's', 'q' },
                {'\000', 'p', 'q' },
                {'\000', 'q' },
    };
    
    /** Titre et choix du menu de modification des rendez-vous */
    private static final String[][] MODIFICATION_RDV = {
                { null, "Annuler un rendez-vous...", "Retour" },
                { null, "Déplacer un rendez-vous...", "Retour" },
                { null, "Modifier l'objet d'un rendez-vous...",
                  "Modifier la description d'un rendez-vous...",
                  "Modifier la nature d'un rendez-vous", "Retour" },
    };
    /** Options possibles du menu de modification des rendez-vous */
    private static final char[][] OPTION_MODIFICATION_RDV = {
                {'\000', 'n', 'q' },
                {'\000', 'd', 'q' },
                {'\000', 'o', 'm', 'r', 'q' },
    };

    /** Liste temporaire de rendez-vous accessible */
    private static RendezVous[] tempList;
    
    /** Retiens l'entrée de la date */
    private static Date dateRecherche;
    
    /** Gère le numéro de la page pour le menu d'affichage */
    private static int noPage;
    
    /** Gère les sorties du programme */
    private static boolean quitter;
    
    /**
     * Gère le choix de l'utilisateur pour chacun des différents menu
     * @param choixMenu Liste de caractères correspondant aux choix permis
     * @return vrai si la saisie est correcte
     */
    public static boolean choixUtilisateur(char[] choixMenu) {

        int index;
        char saisieChoix;
        boolean success = true;
        
        saisieChoix = GestionSaisie.saisirString(null,
                "\n       ====> ").toLowerCase()
                                   .charAt(0);
        for (index = 0;
             index < choixMenu.length
             && choixMenu[index] != saisieChoix;
             index++) {
            // Boucle vide
        }
        // Affecte 
        if (index < choixMenu.length) {

            if (choixMenu.equals(OPTION_MENU_PRINCIPAL)) {
                choixMenuPrincipal(saisieChoix);
            
            } else if (choixMenu.equals(OPTION_MENU_CONSULTATION)) {
                choixMenuConsultation(saisieChoix);
                
            } else if (choixMenu.equals(OPTION_MENU_MODIFICATION)) {
                choixMenuModification(saisieChoix);
                
            } else if (choixMenu.equals(OPTION_MENU_CONFIRMER)) {
                choixMenuConfirmation(saisieChoix);
                
            } else if (   choixMenu.equals(OPTION_AFFICHAGE_RDV[0])
                       || choixMenu.equals(OPTION_AFFICHAGE_RDV[1])
                       || choixMenu.equals(OPTION_AFFICHAGE_RDV[2])
                       || choixMenu.equals(OPTION_AFFICHAGE_RDV[3])) {
                choixMenuAffichageRDV(saisieChoix);
                
            } else if (   choixMenu.equals(OPTION_MODIFICATION_RDV[0])
                       || choixMenu.equals(OPTION_MODIFICATION_RDV[1])
                       || choixMenu.equals(OPTION_MODIFICATION_RDV[2])) {
             choixMenuModificationRDV(saisieChoix);
         }
        } else {
            System.out.println("La lettre saisie (" + saisieChoix
                    + ") ne constitue pas un choix valide."
                    + "\nVeuillez réessayer :\n");
            success = false;
        }
        return success;
    }

    /* **************************************************************************** */
    /* ***    Textes utilisés pour le menu Principal et options de celui-ci      ** */
    /* **************************************************************************** */


    /**
     * Génère un menu à choix multiples à afficher
     * @param titreMenu  Titre à afficher en en-tête
     * @param choixMenu  Labels associés à chaque choix
     * @param optionMenu Tableau de caractères correspondant
     *                   aux différents choix possibles
     */
    public static void afficherMenu(String[] choixMenu,
                                    char[] optionMenu) {
                
        if (choixMenu[0] != null) {
            System.out.println(
                "\n+" + "-".repeat(choixMenu[0].length() + 30) + "+\n"
                + "|               " + choixMenu[0] + "               |\n"
                + "+" + "-".repeat(choixMenu[0].length() + 30) + "+");
        }
        
        do {
            // on affiche toutes les options et les libellés
            for(int i = 1;
                i < optionMenu.length && i < choixMenu.length;
                i++) {
                
                System.out.print("   => " + optionMenu[i] 
                                 + " - " + choixMenu[i] + "\n");
            }
        } while (!choixUtilisateur(optionMenu));
    }
    
    /**
     * Génère un menu à choix multiples à afficher
     * @param state      Etat de l'objet dynamique
     * @param titreMenu  Titre à afficher en en-tête
     * @param choixMenu  Labels associés à chaque choix
     * @param optionMenu Tableau de caractères correspondant
     *                   aux différents choix possibles
     */
    public static void afficherMenuDynamique(int state,
                                             String[][] choixMenu,
                                             char[][] optionMenu) {
                
        if (choixMenu[state][0] != null) {
            System.out.println(
                "\n+" + "-".repeat(choixMenu[state][0].length() + 30) + "+\n"
                + "|               " + choixMenu[state][0]
                + "               |\n"
                + "+" + "-".repeat(choixMenu[state][0].length() + 30) + "+");
        }
        
        do {
            // on affiche toutes les options et les libellés
            for(int i = 1;
                i < optionMenu[state].length
                && i < choixMenu[state].length;
                i++) {
                
                System.out.print("   => " + optionMenu[state][i] 
                                 + " - " + choixMenu[state][i] + "\n");
            }
        } while (!choixUtilisateur(optionMenu[state]));
    }
    
    /**
     * Génère la vue d'affichage des rendez-vous
     * @param typeMenu Type du menu à afficher
     * @param liste Liste des rendez-vous à afficher
     */
    public static void afficherVueRDV(int typeMenu,
                                      RendezVous[] liste) {
        
        int indiceur = (noPage - 1) * 3;
        
        System.out.println(
                "\n==============================================\n"
                + "    Visualisation des rendez-vous (page "
                + noPage + "/" + (liste.length + 2)/3 + ")    \n"
                + "==============================================\n");
        
        if (typeMenu <= 1) {
            System.out.println(liste[indiceur]
                                      .toString() + "\n");
            System.out.println(liste[indiceur + 1]
                               .toString() + "\n");
            System.out.println(liste[indiceur + 2]
                               .toString() + "\n");
        
        } else {
            if (indiceur < liste.length) {
            System.out.println(liste[indiceur]
                                      .toString() + "\n");
            }
            if (indiceur + 1 < liste.length) {
                System.out.println(liste[indiceur + 1]
                                   .toString() + "\n");
            }
            if (indiceur + 2 < liste.length) {
                System.out.println(liste[indiceur + 2]
                                   .toString() + "\n");
            }
        }
        afficherMenuDynamique(typeMenu, AFFICHAGE_RDV,
                              OPTION_AFFICHAGE_RDV);
    }
    
    /**
     * Génère une vue globale des rendez-vous
     * @param state état de l'objet pour créer un menu dynamique
     */
    public static void afficherVueGlobaleRDV(int state) {
        
        RendezVous[] liste;
        
        dateRecherche = GestionSaisie.saisirDate();
        if (Agenda.afficheParDate(dateRecherche, true)) {
            
            liste = Agenda.rechercheJour(dateRecherche);
            tempList = liste;
            
            afficherMenuDynamique(state,
                                  MODIFICATION_RDV,
                                  OPTION_MODIFICATION_RDV);
        }
    }
    
    /**
     * Génère le menu pour le menu principal
     * Exécute le choix associé à la saisie de l'utilisateur
     * @param saisi Caractère saisi illustrant le choix de l'utilisateur
     */
    public static void choixMenuPrincipal(char saisi) {
        
        switch(saisi) {
            // Menu consultation
            case 'c':
                do {
                    afficherMenu(MENU_CONSULTATION,
                                 OPTION_MENU_CONSULTATION);
                } while (!quitter);
                
                quitter = false;
                break;
            
            // menu modification
            case 'g':
                do {
                    afficherMenu(MENU_MODIFICATION,
                                 OPTION_MENU_MODIFICATION);
                } while (!quitter);
                
                quitter = false;
                break;
            
            // Quitter l'agenda
            default:
                quitter = true;
        }
    }
    
    /**
     * Génère le menu pour le mode consultation
     * Exécute le choix associé à la saisie de l'utilisateur
     * @param saisi Caractère saisi illustrant le choix de l'utilisateur
     */
    public static void choixMenuConsultation(char saisi) {
        
        RendezVous[] aAfficher;
        Date aChercher;
        
        switch(saisi) {
            // Affichage des rendez-vous sur une journée
            case 'j':
                System.out.println("\nAffiche tous les rendez-vous de la "
                                   + "journée saisie :\n");
                aChercher = GestionSaisie.saisirDate();
                
                aAfficher = Agenda.rechercheJour(aChercher);
                noPage = 1;
                
                if (aAfficher.length == 0) {
                    System.out.println("Aucun rendez-vous n'est programmé "
                                       + "pour la date du " + aChercher);
                    
                } else if (aAfficher.length <= NB_RDV_A_AFFICHER) {
                    
                    afficherVueRDV(3, aAfficher);
                    
                } else {
                    tempList = aAfficher;
                    afficherVueRDV(1, aAfficher);
                }
                break;
                
            // Afficher tous les rendez-vous
            case 't':
                aAfficher = Agenda.convertirListe();
                noPage = 1;
                
                if (aAfficher.length == 0) {
                    System.out.println("Aucun rendez-vous n'est attribué "
                                       + "à cet agenda.\nRetour...");
                    
                } else if (aAfficher.length <= NB_RDV_A_AFFICHER) {
                    
                    afficherVueRDV(3, aAfficher);
                    
                } else {
                    tempList = aAfficher;
                    afficherVueRDV(1, aAfficher);
                }
                break;
             
            // Sortie du menu
            default:
                quitter = true;
        }
    }
    
    /**
     * Génère le menu pour le menu modification
     * Exécute le choix associé à la saisie de l'utilisateur
     * @param saisi Caractère saisi illustrant le choix de l'utilisateur
     */
    public static void choixMenuModification(char saisi) {
        
        switch(saisi) {
            // Ajouter un RDV
            case 'a':
                RendezVous nouveauRDV;
                boolean success;
                
                nouveauRDV = GestionSaisie.saisirRDV();
                success = Agenda.ajouterRendezVous(nouveauRDV);
                System.out.print(success ? "Rendez-vous ajouté "
                                           + "avec succès.\n"
                                         : "Échec de l'ajout.\n");
                break;
            
            // Annuler un RDV
            case 'n':
                afficherVueGlobaleRDV(0);
                break;
            
            // Déplacer un RDV
            case 'd':
                afficherVueGlobaleRDV(1);
                break;
            
            // Modifier un RDV
            case 'm':
                afficherVueGlobaleRDV(2);
                break;
              
            // Réinitialiser une année
            case 'r':
                int aSupprimer;
                boolean saisieOk = false;
                
                do {
                    aSupprimer = GestionSaisie.saisirEntier(-1, null,
                                  "Saisissez l'année à laquelle vous souhaitez "
                                  + "supprimer les rendez-vous\n(entre "
                                  + Date.AN_MIN + " et " + Date.AN_MAX + ", "
                                  + "-1 pour tout supprimer) :\n");
                    
                    if (aSupprimer == -1) {
                        System.out.println("Voulez-vous vraiment supprimer "
                                           + "toutes les données de l'agenda ?\n"
                                           + "(Attention cette action est "
                                           + "irréversible)\n");
                        saisieOk = true;
                        
                    } else if (aSupprimer >= Date.AN_MIN && Date.AN_MAX >= aSupprimer) {
                        System.out.println("Voulez-vous vraiment supprimer "
                                           + "les données de l'année " + aSupprimer
                                           + " ?\n(Attention cette action est "
                                           + "irréversible)\n");
                        saisieOk = true;
                        
                    } else {
                        System.out.println("L'année saisie est incorrecte. "
                                           + "Veuillez réessayer.\n");
                    }
                } while (!saisieOk);
                
                
                afficherMenu(MENU_CONFIRMER, OPTION_MENU_CONFIRMER);
                
                if (quitter != true) {
                    Agenda.reinitialiser(aSupprimer);
                    
                    System.out.println("Succès.\n");
                    
                } else {
                    System.out.println("Les données n'ont pas été "
                                       + "supprimées.\n");
                }
                quitter = false;
                break;
                
            // Sortie du menu
            default:
                quitter = true;
        }
    }
    
    /**
     * Génère le menu pour le menu confirmation
     * Utilise la variable de classe quitter pour fonctionner
     * Exécute le choix associé à la saisie de l'utilisateur
     * @param saisi Caractère saisi illustrant le choix de l'utilisateur
     */
    public static void choixMenuConfirmation(char saisi) {
        
        switch(saisi) {
            // Confirmer
            case 'o':
                quitter = false;
                break;
                
            // Annuler
            case 'n':
                quitter = true;
                break;
                
            default:
                quitter = true;
        }
    }
    
    /**
     * Génère le menu pour le menu d'affichage des RDV
     * Exécute le choix associé à la saisie de l'utilisateur
     * @param saisi Caractère saisi illustrant le choix de l'utilisateur
     */
    public static void choixMenuAffichageRDV(char saisi) {
        
        int indiceur;
        
        switch(saisi) {
            // Afficher page suivante
            case 's':
                noPage += 1;
                indiceur = (noPage-1) * 3;
                
                if (tempList.length <= noPage * 3) {
                    afficherVueRDV(2, tempList);
                
                } else {
                    afficherVueRDV(0, tempList);
                }
                break;
              
            // Afficher page précédente
            case 'p':
                noPage -= 1;
                indiceur = (noPage-1) * 3;
                
                if (noPage > 1) {
                    afficherVueRDV(0, tempList);
                    
                } else {
                    afficherVueRDV(1, tempList);
                }
                break;
            
            // Sortie par défaut
            default:
                quitter = true;
        }
    }
    
    /**
     * Génère le menu pour le menu modification
     * Exécute le choix associé à la saisie de l'utilisateur
     * @param saisi Caractère saisi illustrant le choix de l'utilisateur
     */
    public static void choixMenuModificationRDV(char saisi) {
        
        int index;
        String saisie;
        boolean success;
        Horaire recherche;
        
        switch(saisi) {
            // Annuler un RDV
            case 'n':                
                recherche = GestionSaisie.saisirHoraire(true);
                
                index = Agenda.rechercherRDV(tempList,
                                             tempList[0].getDateRDV(),
                                             recherche);
                
                if (index >= 0 && index < tempList.length) {
                    
                    System.out.println("Souhaitez-vous vraiment annuler "
                                       + "le rendez-vous suivant ?\n"
                                       + tempList[index].toString()
                                       + "\n\n");
                    
                    afficherMenu(MENU_CONFIRMER, OPTION_MENU_CONFIRMER);
                    
                    if (quitter != true) {
                        success = Agenda.supprimerRendezVous(
                                          tempList[index].getDateRDV(),
                                          tempList[index].getHoraireDebut());
                        
                        System.out.println(success ? "Succès.\n"
                                                   : "Échec.\n");
                    } else {
                        System.out.println("Le rendez-vous ciblé n'a pas "
                                           + "pu été supprimé.");
                    }
                    quitter = false;
                    
                } else {
                    System.out.println("Le rendez-vous ciblé n'a pas "
                                       + "été trouvé\n");
                }
                break;
                
            // Déplacer un RDV
            case 'd':
                recherche = GestionSaisie.saisirHoraire(true);
                
                index = Agenda.rechercherRDV(tempList,
                                             tempList[0].getDateRDV(),
                                             recherche);
                
                if (index >= 0 && index < tempList.length) {
                    
                    System.out.println("Souhaitez-vous vraiment déplacer "
                                       + "le rendez-vous suivant ?\n"
                                       + tempList[index].toString()
                                       + "\n\n");
                    
                    afficherMenu(MENU_CONFIRMER, OPTION_MENU_CONFIRMER);
                    
                    if (quitter != true) {
                        success = Agenda.deplacerRendezVous(tempList[0].getDateRDV(),
                                                            recherche);
                        
                        System.out.println(success ? "Succès.\n"
                                                   : "Échec.\n");
                    } else {
                        System.out.println("Le rendez-vous ciblé n'a pas "
                                           + "été déplacé.\n");
                    }
                    quitter = false;
                    
                } else {
                    System.out.println("Le rendez-vous ciblé n'a pas "
                                       + "été trouvé\n");
                }
                break;
                
            // Modifier titre d'un RDV
            case 'o':
                recherche = GestionSaisie.saisirHoraire(true);
                
                index = Agenda.rechercherRDV(tempList,
                                             tempList[0].getDateRDV(),
                                             recherche);
                
                if (index >= 0 && index < tempList.length) {
                    
                    System.out.println("Souhaitez-vous vraiment modifier "
                                       + "le titre du rendez-vous "
                                       + "suivant ?\n"
                                       + tempList[index].toString()
                                       + "\n\n");
                    
                    afficherMenu(MENU_CONFIRMER, OPTION_MENU_CONFIRMER);
                    
                    if (quitter != true) {
                        
                        saisie = GestionSaisie.saisirString(
                                   RendezVous.TAILLE_MAX_LIBELLE_CONCIS, 
                                   "Veuillez saisir l'objet de votre "
                                   + "Rendez-vous ("
                                   + RendezVous.TAILLE_MAX_LIBELLE_CONCIS
                                   + " caractère max) : ");
                        
                        tempList[index].setLibelleConcis(saisie);
                        
                        Agenda.modifierRendezVous(tempList[index],
                                                  tempList[0].getDateRDV(),
                                                  recherche);
                    } else {
                        System.out.println("Le rendez-vous ciblé n'a pas "
                                           + "été modifié.\n");
                    }
                    quitter = false;
                    
                } else {
                    System.out.println("Le rendez-vous ciblé n'a pas "
                                       + "été trouvé\n");
                }
                break;
                
            // Modifier libellé d'un RDV
            case 'm':
                recherche = GestionSaisie.saisirHoraire(true);
                
                index = Agenda.rechercherRDV(tempList,
                                             tempList[0].getDateRDV(),
                                             recherche);
                
                if (index >= 0 && index < tempList.length) {
                    
                    System.out.println("Souhaitez-vous vraiment modifier "
                                       + "la description du rendez-vous "
                                       + "suivant ?\n"
                                       + tempList[index].toString()
                                       + "\n\n");
                    
                    afficherMenu(MENU_CONFIRMER, OPTION_MENU_CONFIRMER);
                    
                    if (quitter != true) {
                        
                        saisie = GestionSaisie.saisirString(
                                   RendezVous.TAILLE_MAX_LIBELLE_COMPLET, 
                                   "Veuillez saisir la description de "
                                   + "votre Rendez-vous ("
                                   + RendezVous.TAILLE_MAX_LIBELLE_COMPLET
                                   + " caractère max) : ");
                        
                        tempList[index].setLibelleComplet(saisie);
                        
                        Agenda.modifierRendezVous(tempList[index],
                                                  tempList[0].getDateRDV(),
                                                  recherche);
                    } else {
                        System.out.println("Le rendez-vous ciblé n'a pas "
                                           + "été modifié.\n");
                    }
                    quitter = false;
                    
                } else {
                    System.out.println("Le rendez-vous ciblé n'a pas "
                                       + "été trouvé\n");
                }
                break;

            // Modifier nature d'un rendez-vous
            case 'r':
                recherche = GestionSaisie.saisirHoraire(true);
                
                index = Agenda.rechercherRDV(tempList,
                                             tempList[0].getDateRDV(),
                                             recherche);
                
                if (index >= 0 && index < tempList.length) {
                    
                    System.out.println("Souhaitez-vous vraiment modifier "
                                       + "la nature du rendez-vous "
                                       + "suivant ?\n"
                                       + tempList[index].toString()
                                       + "\n\n");
                    
                    afficherMenu(MENU_CONFIRMER, OPTION_MENU_CONFIRMER);
                    
                    if (quitter != true) {
                                                
                        tempList[index].setIsProfessionnal(GestionSaisie
                                                           .saisirNature());
                        
                        Agenda.modifierRendezVous(tempList[index],
                                                  tempList[0].getDateRDV(),
                                                  recherche);
                    } else {
                        System.out.println("Le rendez-vous ciblé n'a pas "
                                           + "été modifié.");
                    }
                    quitter = false;
                    
                } else {
                    System.out.println("Le rendez-vous ciblé n'a pas "
                                       + "été trouvé");
                }
                break;
                
            // sortie par défaut
            default:
                System.out.println("Retour...");
                quitter = true;
        }
    }

    /**
     * Lancement de l'interface en lançant le menu principal
     */
    public static void lancementInterface() {
        
        quitter = false;
        
        do {
            afficherMenu(MENU_PRINCIPAL,
                         OPTION_MENU_PRINCIPAL);
        } while (!quitter);
        if(quitter) {
        	System.out.println("passez une bonne journée");
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
			}
        	try {
        		Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
			} catch (IOException e) {
			}
        }
    }
}
