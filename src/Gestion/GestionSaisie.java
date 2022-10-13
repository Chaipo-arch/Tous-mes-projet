/*
 * GestionSaisie.java                            26 mai 2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
package Gestion;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Cette classe contient des m�thodes outils pour effectuer des saisies :
 *      - saisie d'une cha�ne de caract�res non vide
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
     * @param message  Message � afficher sur la console au moment de la saisie
     * @return l'entier saisi par l'utilisateur apr�s v�rification de sa validit�
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
                                       + min + " et " + max + ". R�essayez\n");
                } else {
                    saisieOk = true;
                }
                
            } else {
                System.out.println("Erreur, il faut entrer un nombre. R�essayer\n");
            }
            entree.nextLine();
            
        } while (!saisieOk);
        return aSaisir;
    }
    
    /**
     * Permet la saisie d'une chaine de caract�res, avec possibilit� de limiter
     * le nombre de caract�res � entrer
     * @param maxCaracteres  Nombre maximum de caract�res permis, null si aucune limite
     * @param message        Message � afficher sur la console au moment de la saisie
     * @return la chaine de caract�res saisie apr�s v�rification de sa validit�
     */
    public static String saisirString(Integer maxCaracteres, String message) {
        
        String aSaisir;
        
        do {
            System.out.print(message);
            
            aSaisir = entree.nextLine().trim();
            
            if (aSaisir.isBlank()) {
                System.out.println("Erreur, la saisie ne doit pas �tre vide. R�essayez");
                
            } else if (maxCaracteres != null && aSaisir.length() > maxCaracteres) {
                System.out.println("Erreur, la saisie comporte trop de caract�res ("
                                   + maxCaracteres + " maximum). R�essayez");
            }
            
        } while (maxCaracteres != null && aSaisir.length() > maxCaracteres
                 || aSaisir.isBlank());
        
        System.out.println();
        return aSaisir;
    }
    
    /**
     * Methode qui g�re la saisie d'une date 
     * @return la date saisie par l'utilisateur 
     */
    public static Date saisirDate() {
        
        int aaaa;
        int mm;
        int jj;

        aaaa = saisirEntier(Date.AN_MIN, Date.AN_MAX,
                            "Saisissez l'ann�e (entre "
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
     * M�thode qui demande � l'utilisateur de saisir un horaire 
     * ici les secondes ne sont pas demand�es 
     * @param isHoraireDebut Vrai si horaire de d�but � rentrer
     * @return l'horaire saisie par l'utilisateur 
     */
    public static Horaire saisirHoraire(boolean isHoraireDebut) {
        
        System.out.println((isHoraireDebut ? "\nHoraire de d�but\n"
                                           : "\nHoraire de fin\n")
                           + (isHoraireDebut ? "----------------"
                                             : "--------------"));

        int hh = saisirEntier(0, 23, "Veuillez saisir l'heure (de 0 � 23) : ");
        int mm = saisirEntier(0, 59, "Veuillez saisir les minutes (de 0 � 59) : ");

        return new Horaire(hh, mm, 0);
    }

    /** 
     * M�thode qui demande � l'utilisateur de saisir la nature 
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
                System.out.print("Saisie incorrecte, veuillez r�essayer : \n");
            }

        } while(aSaisir != 'o' && aSaisir != 'n');

        return (aSaisir == 'o' ? true : false);
    }

    /**
     * Permet de cr�er un rendez-vous � partir des saisies de l'utilisateur
     * @return Rendez-vous cr��
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

                System.out.println("Erreur, la date est ant�rieure � la date"
                                    + " actuelle\nVeuillez Recommencer : \n");
            }
        } while(dateSaisie.compareTo(dateCourante) < 0); 

        
        //On saisit l'horaire de d�but
        if( dateSaisie.equals(dateCourante)) {
            do {
                horaireDebut = saisirHoraire(true);
                if (horaireDebut.compareTo(heureCourante) < 0 ) {

                    System.out.println("Erreur, l'horaire de d�but est inf�rieur ou �gal"
                                        + " � l'heure courante \nVeuillez Recommencer : \n");
                }
                entree.nextLine();
            } while(horaireDebut.compareTo(heureCourante) < 0 );
        } else {
            horaireDebut = saisirHoraire(true);
        }
        

        if (Agenda.rechercheHoraireDate(dateSaisie, horaireDebut)) { 
                System.out.println("Un Rendez-Vous existe d�j� � cette date et cet horaire");
                return null;   // TODO Afficher le rdv en question 
                
        } else {
            // on ajoute le reste du rdv 

            // On saisie l'horaire de fin tant qu'elle n'est pas sup�rieure � l'horaire de d�but
            do {
                horaireFin = saisirHoraire(false);
                if (horaireDebut.compareTo(horaireFin) >= 0 ) {

                    System.out.println("Erreur, l'horaire de fin est ant�rieur"
                                        + " � l'horaire de d�but \nVeuillez Recommencer : \n");
                }
            } while(horaireDebut.compareTo(horaireFin) >= 0 );
                
            libelleConcis = saisirString(
                    RendezVous.TAILLE_MAX_LIBELLE_CONCIS, 
                    "\nVeuillez saisir l'objet de votre Rendez-vous\n("
                    + RendezVous.TAILLE_MAX_LIBELLE_CONCIS
                    + " caract�res max) : ");
                
            libelleComplet = saisirString(
                    RendezVous.TAILLE_MAX_LIBELLE_COMPLET, 
                    "\nVeuillez saisir la description de "
                    + "votre Rendez-vous\n("
                    + RendezVous.TAILLE_MAX_LIBELLE_COMPLET
                    + " caract�re max) : ");

            isProfessionnal = saisirNature();
        }
        RendezVous rdvSaisie = new RendezVous(dateSaisie,horaireDebut,horaireFin, 
                                              libelleConcis, libelleComplet,
                                              isProfessionnal);
        return rdvSaisie;

    }
}
