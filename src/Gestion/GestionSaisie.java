/*
 * GestionSaisie.java                            26 mai 2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
package Gestion;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Cette classe contient des méthodes outils pour effectuer des saisies :
 *      - saisie d'une chaîne de caractères non vide
 *      - saisie d'une lettre majuscule
 * @author Groupe Agenda 1
 * @version 1.0
 *
 */
public class GestionSaisie {
    
    /** Objet Scanner pour effectuer des saisies */
    private static Scanner entree = new Scanner(System.in);
    
    /**
     * Permet la saisie d'un entier, dans un intervalle ou non
     * @param min      saisie minimum permise, null s'il n'y a pas de limite
     * @param max      saisie maximum permise, null s'il n'y a pas de limite
     * @param message  Message à afficher sur la console au moment de la saisie
     * @return l'entier saisi par l'utilisateur après vérification de sa validité
     */
    public static int saisirEntier(Integer min, Integer max, String message) {
        
        int aSaisir = 0;
        boolean saisieOk = false;
        
        do {
            System.out.print(message);
            
            if (entree.hasNextInt()) {
                aSaisir = entree.nextInt();
                
                if (    min != null && aSaisir < min
                    || (max != null && max < aSaisir)) {
                    System.out.println("Erreur, il faut entrer un entier compris entre "
                                       + min + " et " + max + ". Réessayez\n");
                } else {
                    saisieOk = true;
                }
                
            } else {
                System.out.println("Erreur, il faut entrer un nombre. Réessayer\n");
            }
            entree.nextLine();
            
        } while (!saisieOk);
        return aSaisir;
    }
    
    /**
     * Permet la saisie d'une chaine de caractères, avec possibilité de limiter
     * le nombre de caractères à entrer
     * @param maxCaracteres  Nombre maximum de caractères permis, null si aucune limite
     * @param message        Message à afficher sur la console au moment de la saisie
     * @return la chaine de caractères saisie après vérification de sa validité
     */
    public static String saisirString(Integer maxCaracteres, String message) {
        
        String aSaisir;
        
        do {
            System.out.print(message);
            
            aSaisir = entree.nextLine().trim();
            
            if (aSaisir.isBlank()) {
                System.out.println("Erreur, la saisie ne doit pas être vide. Réessayez");
                
            } else if (maxCaracteres != null && aSaisir.length() > maxCaracteres) {
                System.out.println("Erreur, la saisie comporte trop de caractères ("
                                   + maxCaracteres + " maximum). Réessayez");
            }
            
        } while (maxCaracteres != null && aSaisir.length() > maxCaracteres
                 || aSaisir.isBlank());
        
        System.out.println();
        return aSaisir;
    }
    
    /**
     * Methode qui gère la saisie d'une date 
     * @return la date saisie par l'utilisateur 
     */
    public static Date saisirDate() {
        
        int aaaa;
        int mm;
        int jj;

        aaaa = saisirEntier(Date.AN_MIN, Date.AN_MAX,
                            "Saisissez l'année (entre "
                            + Date.AN_MIN + " et "
                            + Date.AN_MAX + ") : ");
        
        mm =   saisirEntier(1,12, "Veuillez saisir le mois "
                            + "(entre 1 et 12) : ");
        
        jj =   saisirEntier(1, Date.DUREE_MOIS[mm],
                            "Veuillez saisir le jour "
                            + "(entre 1 et "
                            + Date.DUREE_MOIS[mm]
                            + ") : ");

        return new Date(jj,mm,aaaa);
    }

    /** 
     * Méthode qui demande à l'utilisateur de saisir un horaire 
     * ici les secondes ne sont pas demandées 
     * @param isHoraireDebut Vrai si horaire de début à rentrer
     * @return l'horaire saisie par l'utilisateur 
     */
    public static Horaire saisirHoraire(boolean isHoraireDebut) {
        
        System.out.println((isHoraireDebut ? "\nHoraire de début\n"
                                           : "\nHoraire de fin\n")
                           + (isHoraireDebut ? "----------------"
                                             : "--------------"));

        int hh = saisirEntier(0, 23, "Veuillez saisir l'heure (de 0 à 23) : ");
        int mm = saisirEntier(0, 59, "Veuillez saisir les minutes (de 0 à 59) : ");

        return new Horaire(hh, mm, 0);
    }

    /** 
     * Méthode qui demande à l'utilisateur de saisir la nature 
     * @return un booleen en fonction de l'une des deux options saisies 
     */
    public static boolean saisirNature() {
        
        char aSaisir;
        
        do {
            System.out.print("Veuillez renseigner la nature de votre RDV \n"
                          + " o => Professionnel \n"
                          + " n => Personnel\n\n"
                          + "    ===> "  );
        
            aSaisir = entree.nextLine().toLowerCase().charAt(0);

            if (aSaisir != 'o' && aSaisir != 'n') {
                System.out.print("Saisie incorrecte, veuillez réessayer : \n");
            }

        } while(aSaisir != 'o' && aSaisir != 'n');

        return (aSaisir == 'o' ? true : false);
    }

    /**
     * Permet de créer un rendez-vous à partir des saisies de l'utilisateur
     * @return Rendez-vous créé
     */
    public static RendezVous saisirRDV() {
        Calendar calendar = Calendar.getInstance();
        Date dateSaisie; 
        Horaire horaireDebut;
        Horaire horaireFin;
        String libelleConcis; 
        String libelleComplet; 
        boolean isProfessionnal; 
        Date dateCourante = new Date(calendar.get(Calendar.DAY_OF_MONTH),
                                     calendar.get(Calendar.MONTH) + 1,
                                     calendar.get(Calendar.YEAR));
        Horaire heureCourante = new Horaire(calendar.get(Calendar.AM_PM) == 1 
        								    ? calendar.get(Calendar.HOUR) + 12 
        								    : calendar.get(Calendar.HOUR),
                                            calendar.get(Calendar.MINUTE),
                                            0);
        // Saisie de la date 
        do {
            dateSaisie = saisirDate();
            if (dateSaisie.compareTo(dateCourante) < 0 ) {

                System.out.println("Erreur, la date est antérieure à la date"
                                    + " actuelle\nVeuillez Recommencer : \n");
            }
        } while(dateSaisie.compareTo(dateCourante) < 0); 

        
        //On saisit l'horaire de début
        if( dateSaisie.equals(dateCourante)) {
            do {
                horaireDebut = saisirHoraire(true);
                if (horaireDebut.compareTo(heureCourante) < 0 ) {

                    System.out.println("Erreur, l'horaire de début est inférieur ou égal"
                                        + " à l'heure courante \nVeuillez Recommencer : \n");
                }
                entree.nextLine();
            } while(horaireDebut.compareTo(heureCourante) < 0 );
        } else {
            horaireDebut = saisirHoraire(true);
        }
        

        if (Agenda.rechercheHoraireDate(dateSaisie, horaireDebut)) { 
                System.out.println("Un Rendez-Vous existe déjà à cette date et cet horaire");
                return null;   // TODO Afficher le rdv en question 
                
        } else {
            // on ajoute le reste du rdv 

            // On saisie l'horaire de fin tant qu'elle n'est pas supérieure à l'horaire de début
            do {
                horaireFin = saisirHoraire(false);
                if (horaireDebut.compareTo(horaireFin) >= 0 ) {

                    System.out.println("Erreur, l'horaire de fin est antérieur"
                                        + " à l'horaire de début \nVeuillez Recommencer : \n");
                }
            } while(horaireDebut.compareTo(horaireFin) >= 0 );
                
            libelleConcis = saisirString(
                    RendezVous.TAILLE_MAX_LIBELLE_CONCIS, 
                    "\nVeuillez saisir l'objet de votre Rendez-vous\n("
                    + RendezVous.TAILLE_MAX_LIBELLE_CONCIS
                    + " caractères max) : ");
                
            libelleComplet = saisirString(
                    RendezVous.TAILLE_MAX_LIBELLE_COMPLET, 
                    "\nVeuillez saisir la description de "
                    + "votre Rendez-vous\n("
                    + RendezVous.TAILLE_MAX_LIBELLE_COMPLET
                    + " caractère max) : ");

            isProfessionnal = saisirNature();
        }
        RendezVous rdvSaisie = new RendezVous(dateSaisie,horaireDebut,horaireFin, 
                                              libelleConcis, libelleComplet,
                                              isProfessionnal);
        return rdvSaisie;

    }
}
