/*
 * Agenda.java                                     16 mai 2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
package Gestion;

import java.util.ArrayList;

/**
 * g�re les rendez-vous de l'agenda ajoute des rendez-vous, supprime des
 * rendez-vous tout en v�rifiant que les rendez-vous que l'on tente de cr�er
 * peuvent co�ncider
 */
public class Agenda {

    /** Liste dynamique r�pertoriant tous les rendez-vous */
    private static ArrayList<RendezVous> listeRendezVous;
        
    /**
     * affiche la liste de tous les rendez-vous
     */
    public static void afficherRDV() {
        for(int i = 0 ; i < listeRendezVous.size() ; i++ ) {
            System.out.print(listeRendezVous.get(i).toString());
        }
    }
    
    /**
     * Ajoute un rendez-vous dans la liste et le classe suivant
     * sa date de d�but
     * @param nouveauRDV Nouveau rendez-vous � ajouter
     * @return Vrai si l'op�ration s'est correctement ex�cut�e
     */
    public static boolean ajouterRendezVous(RendezVous nouveauRDV) {
        
        boolean ajoutOk;
        ajoutOk = false;
        
        if(nouveauRDV == null ) {
            System.out.print("Un rendez-vous existe d�j� � cet date "
                             + "et cet horaire, ou est invalide\n");
        
        } else {
            // les erreurs sont g�r�es lors de la saisie 
            listeRendezVous.add(indexOrdonneListe(nouveauRDV),
                                nouveauRDV);
            ajoutOk = true;
        }
        return ajoutOk;
    }

    /** 
     * Determine si un rendez-vous existe � la date et l'heure de d�but en argument
     * @param dateRecherchee la date � laquelle on recherche 
     * @param horaireRecherchee L'horaire recherchee 
     * @return true si un rdv a �t� trouv� � la date et � l'horaire indiqu�
     */
    public static boolean rechercheHoraireDate(Date dateRecherchee, Horaire horaireRecherchee) {
        int index;
        boolean test = false;

        for (index = 0;
             index < listeRendezVous.size()
             // On continue tant que la date est diff�rente de dateRecherchee...
             && (   !(listeRendezVous.get(index).getDateRDV().equals(dateRecherchee))
             // ...ET que l'horaire est diff�rent de horaireRecherchee 
                 || !((listeRendezVous.get(index).getHoraireDebut()).equals(horaireRecherchee)));
             index++ ) {
        }

        if (index < listeRendezVous.size()) {
            test = true;
        }
        return test;
    }
    
    /**
     * Recherche l'index du tableau o� le rendez-vous doit �tre ins�r�
     * @param aIndexer  RendezVous � indexer
     * @return index du tableau o� ins�rer le rendez-vous
     */
    public static int indexOrdonneListe(RendezVous aIndexer) {
        
        int index;  // Valeur de parcours du tableau
        
        /*
         * Parcours la liste et s'arr�te � l'index o� la date et l'heure
         * sont ordonn�es avec les autres valeurs
         */
        for (index = 0;
             index < listeRendezVous.size()
             && (listeRendezVous.get(index).getDateRDV()
                  .compareTo(aIndexer.getDateRDV()) < 0
                 || (   listeRendezVous.get(index).getDateRDV()
                         .compareTo(aIndexer.getDateRDV()) == 0
                     && listeRendezVous.get(index).getHoraireDebut()
                         .compareTo(aIndexer.getHoraireDebut()) < 0));
             index++) {
        }
        
        return index;
    }
    
    /**
     * Affiche les RDV � partir de la date en arguments 
     * @param dateRecherche Date � laquelle on effectue la recherche
     * @param abrege true si on veut afficher les dates en abr�g�
     * @return Vrai si au moins un rendez-vous � �t� trouv�
     */
    public static boolean afficheParDate(Date dateRecherche, boolean abrege) {
        
        int index;
        boolean RDVTrouve = false;
        
        System.out.println("\n========================================================\n" +
                           "   Rendez-Vous trouv� � la date:" + dateRecherche.toString() + 
                           "\n========================================================\n" );
        
        for(index = 0 ; index < listeRendezVous.size() ; index++) {
                if (listeRendezVous.get(index).getDateRDV().equals(dateRecherche)) {
                    System.out.println((abrege ? listeRendezVous.get(index).toStringAbrege()
                                              : listeRendezVous.get(index).toString()) + "\n");
                    RDVTrouve = true;
                }
        }
        if (!RDVTrouve) { 
            System.out.println("Aucun rendez-vous � la date " 
                               + dateRecherche.toString()
                               + ".\nRetour...");
            
        }
        return RDVTrouve;
    }
    
    /**
     * Affiche les RDV de la date en arguments 
     * @param recherche Tableau de Rendez-vous o� chercher
     * @param dateRecherche Date � laquelle on effectue la recherche
     * @param horaireRecherche Horaire � laquelle on effectue la recherche
     * @return l'indice correspondant � l'�l�ment trouv�, ou -1 si non trouv�
     */
    public static int rechercherRDV(RendezVous[] recherche,
                                    Date dateRecherche,
                                    Horaire horaireRecherche) {
        int index;
        Horaire tampon = new Horaire(horaireRecherche.getHeure(),
                                     horaireRecherche.getMinute(),
                                     59);
        RendezVous aRechercher = 
                new RendezVous(dateRecherche, horaireRecherche,
                               tampon, "default", "default", false);
        
        if (recherche != null) {
            for(index = 0;
                index < recherche.length
                && aRechercher.compareTo(recherche[index]) != 0;
                index++) {
                // Corps vide
            }
            
            if (index == recherche.length) {
                
                index = -1;
            }
            
        } else {
            for (index = 0;
                 index < listeRendezVous.size()
                 && aRechercher.compareTo(listeRendezVous.get(index)) != 0;
                 index++) {
                // Corps vide
            }
            
            if (index == listeRendezVous.size()) {
                
                index = -1;
            }
        }
        return index;
    }
    
    /**
     * Recherche tous les rendez-vous � une date donn�e dans l'ArrayList
     * @param dateRecherche Date � laquelle on effectue la recherche
     * @return Un tableau contenant tous les �l�ments correspondant
     *         � la recherche
     */
    public static RendezVous[] rechercheJour(Date dateRecherche) {
        
        ArrayList<RendezVous> tempListe = new ArrayList<>();
        
        for(int i = 0 ; i < listeRendezVous.size() ; i++) {
            
            if (listeRendezVous.get(i).getDateRDV().equals(dateRecherche)) {
                
                tempListe.add(listeRendezVous.get(i));
            }
        }
        RendezVous[] castList = new RendezVous[tempListe.size()];
        return tempListe.toArray(castList);
    }
    
    /**
     * Recherche de tous les rendez-vous d'une semaine associ�e
     * � une date. Affecte tous les rendez-vous correspondants
     * dans une liste ensuite convertie en tableau
     * @param dateJourSemaine Date d'un des jours de la semaine
     * @return Un tableau contenant tous les rendez-vous de la semaine
     */
    public static RendezVous[] rechercheSemaine(Date dateJourSemaine) {
        
        int noJour;  // Valeur du jour de la semaine
        
        ArrayList<RendezVous> resultatRecherche = new ArrayList<>();
        
        /* Parcours la liste des rendez-vous */
        for (int index = 0 ; index < listeRendezVous.size() ; index++) {
            
            /* 
             * Teste si le rendez-vous est pr�sent dans
             * un des 7 jours de la semaine.
             * Si oui on l'ajoute � la liste temporaire
             */
            for (noJour = 0;
                 noJour < 7
                 && listeRendezVous.get(index).getDateRDV()
                     .compareTo(dateJourSemaine
                                .searchDayOfWeek(noJour)) == 0;
                 noJour++) {
                // Corps vide
            }
            
            if (noJour < 7) {
                resultatRecherche.add(listeRendezVous.get(index));
            }
        }
        RendezVous[] castList = new RendezVous[resultatRecherche
                                               .size()];
        /* Conversion de la liste en tableau avant de la retourner */
        return resultatRecherche.toArray(castList);
    }
    
    /**
     * Supprime un rendez-vous en fonction de sa date et son horaire
     * dans l'ArrayList principale.
     * @param date Date � laquelle on recherche l'�l�ment � supprimer
     * @param horaire Horaire � laquelle on recherche l'�l�ment � supprimer
     * @return vrai si l'�l�ment a �t� trouv� et supprim�
     */
    public static boolean supprimerRendezVous(Date date,
                                              Horaire horaire) {
        
        int indexASupprimer = rechercherRDV(null, date, horaire);
        
        /* D�tecte les erreurs d'indexage */
        if (indexASupprimer < 0
            || listeRendezVous.size() <= indexASupprimer) {
            return false;
        }
        listeRendezVous.remove(indexASupprimer);
        return true;
    }
    
    /**
     * Permet de modifier la date et l'horaire d'un rendez-vous
     * @param date Date � laquelle on recherche l'�l�ment � d�placer
     * @param horaire Horaire � laquelle on recherche l'�l�ment � d�placer
     * @return Vrai si l'�l�ment � �t� correctement modifi�
     */
    public static boolean deplacerRendezVous(Date date,
                                             Horaire horaire) {
        Date nouvDate;
        Horaire nouvHoraireDebut,
                nouvHoraireFin;
        
        RendezVous aDeplacer = listeRendezVous
                               .get(rechercherRDV(null, date, horaire));
        
        System.out.println(  "============================================\n"
                           + "  Saisie de la nouvelle p�riode temporelle  \n"
                           + "============================================\n"
                           + "\n");
        
        nouvDate = GestionSaisie.saisirDate();
        do {
            nouvHoraireDebut = GestionSaisie.saisirHoraire(true);
            nouvHoraireFin = GestionSaisie.saisirHoraire(false);
            
            if (nouvHoraireDebut.compareTo(nouvHoraireFin) >= 0) {
                System.out.println("Erreur, l'horaire de fin ne peut "
                                   + "�tre ni inf�rieur, ni �gal � l'horaire "
                                   + "de d�but. R�essayez.\n");
            }
            
        } while (nouvHoraireDebut.compareTo(nouvHoraireFin) >= 0);
        
        supprimerRendezVous(aDeplacer.getDateRDV(),
                            aDeplacer.getHoraireDebut());
        
        aDeplacer.setDateRDV(nouvDate);
        aDeplacer.setPlageHoraire(nouvHoraireDebut, nouvHoraireFin);
        
        return ajouterRendezVous(aDeplacer);
    }
    
    /**
     * Permet de modifier un �l�ment de la liste par un nouvel �l�ment
     * donn� en param�tre
     * @param aRemplacer l'�l�ment qui remplacera l'�l�ment � modifier
     * @param date Date � laquelle on recherche l'�l�ment � modifier
     * @param horaire Horaire � laquelle on recherche l'�l�ment � modifier
     * @return Vrai si l'�l�ment a �t� correctement modifi�
     */
    public static boolean modifierRendezVous(RendezVous aRemplacer,
                                          Date date, Horaire horaire) {
        boolean success = false;
        
        if (supprimerRendezVous(date, horaire)) {
            
            success = ajouterRendezVous(aRemplacer);
        }
        
        return success;
    }

    /**
     * Efface tous les rendez-vous � une ann�e donn�e.
     * Efface tout si le param�tre -1 est assign�
     * @param annee Ann�e � effacer, -1 pour tout effacer
     */
    public static void reinitialiser(int annee) {
        
        if (annee == -1) {
            listeRendezVous.clear();
            
        } else {
            int index;
            
            do {
                for (index = 0;
                     index < listeRendezVous.size()
                     && listeRendezVous.get(index)
                        .getDateRDV().getAn() != annee;
                     index++) {
                    // Corps vide
                }
                if (index < listeRendezVous.size()) {
                    supprimerRendezVous(listeRendezVous.get(index)
                                        .getDateRDV(),
                                        listeRendezVous.get(index)
                                        .getHoraireDebut());
                }
            } while (index < listeRendezVous.size());
        }
    }
    
    /**
     * Permet de convertir l'ArrayList de la classe en un tableau
     * @return le tableau correspondant � l'ArrayList
     */
    public static RendezVous[] convertirListe() {
        
        RendezVous[] target = new RendezVous[listeRendezVous.size()];
        
       /* Conversion de la liste en tableau avant de la retourner */
       return listeRendezVous.toArray(target);
    }
    
    /**
     * Lancement de l'application Agenda.
     * Ouverture ou cr�ation du fichier binaire contenant les donn�es
     * de l'agenda, puis appel de la classe GestionInterface
     * pour la g�n�ration de l'interface graphique
     * Fermeture de l'agenda en sauvegardant les donn�es
     * @param args non utilis�
     */
    public static void main (String[] args) {
                        
        /* Sauvegarde temporaire de la liste provenant du fichier */
        ArrayList<RendezVous> listeTemp;
        
        /*
         * Tentative de r�cup�ration des donn�es du fichier.
         * Vaut null si la tentative �choue
         */
        listeTemp = GestionFichier.restaurerAgenda();
        
        if (listeTemp == null) {
            System.out.println("Le fichier n'existe pas ou n'a pas pu �tre "
                               + "charg�.\n"
                               + "Cr�ation d'un nouvel Agenda.\n");
            
            /* Cr�ation d'une nouvelle liste de rendez-vous */
            listeRendezVous = new ArrayList<RendezVous>();
            
            /* Cr�ation ou �crasement s'il existe du fichier */
            GestionFichier.enregistrerAgenda(listeRendezVous);
            
        } else {
            System.out.println("Le fichier a correctement �t� charg�.\n");
            
            /* Restauration de la liste de rendez-vous dans la classe */
            listeRendezVous = listeTemp;
        }
        /* Lancement de l'interface de l'application */
        GestionInterface.lancementInterface();
        
        /* Sauvegarde des modifications */
        GestionFichier.enregistrerAgenda(listeRendezVous);
        
        System.out.println("L'agenda a correctement �t� sauvegard�.\n"
                           + "Au revoir !");
    }
}
