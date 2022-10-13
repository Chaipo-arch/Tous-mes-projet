/*
 * Agenda.java                                     16 mai 2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
package Gestion;

import java.util.ArrayList;

/**
 * gère les rendez-vous de l'agenda ajoute des rendez-vous, supprime des
 * rendez-vous tout en vérifiant que les rendez-vous que l'on tente de créer
 * peuvent coïncider
 */
public class Agenda {

    /** Liste dynamique répertoriant tous les rendez-vous */
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
     * sa date de début
     * @param nouveauRDV Nouveau rendez-vous à ajouter
     * @return Vrai si l'opération s'est correctement exécutée
     */
    public static boolean ajouterRendezVous(RendezVous nouveauRDV) {
        
        boolean ajoutOk;
        ajoutOk = false;
        
        if(nouveauRDV == null ) {
            System.out.print("Un rendez-vous existe déjà à cet date "
                             + "et cet horaire, ou est invalide\n");
        
        } else {
            // les erreurs sont gérées lors de la saisie 
            listeRendezVous.add(indexOrdonneListe(nouveauRDV),
                                nouveauRDV);
            ajoutOk = true;
        }
        return ajoutOk;
    }

    /** 
     * Determine si un rendez-vous existe à la date et l'heure de début en argument
     * @param dateRecherchee la date à laquelle on recherche 
     * @param horaireRecherchee L'horaire recherchee 
     * @return true si un rdv a été trouvé à la date et à l'horaire indiqué
     */
    public static boolean rechercheHoraireDate(Date dateRecherchee, Horaire horaireRecherchee) {
        int index;
        boolean test = false;

        for (index = 0;
             index < listeRendezVous.size()
             // On continue tant que la date est différente de dateRecherchee...
             && (   !(listeRendezVous.get(index).getDateRDV().equals(dateRecherchee))
             // ...ET que l'horaire est différent de horaireRecherchee 
                 || !((listeRendezVous.get(index).getHoraireDebut()).equals(horaireRecherchee)));
             index++ ) {
        }

        if (index < listeRendezVous.size()) {
            test = true;
        }
        return test;
    }
    
    /**
     * Recherche l'index du tableau où le rendez-vous doit être inséré
     * @param aIndexer  RendezVous à indexer
     * @return index du tableau où insérer le rendez-vous
     */
    public static int indexOrdonneListe(RendezVous aIndexer) {
        
        int index;  // Valeur de parcours du tableau
        
        /*
         * Parcours la liste et s'arrête à l'index où la date et l'heure
         * sont ordonnées avec les autres valeurs
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
     * Affiche les RDV à partir de la date en arguments 
     * @param dateRecherche Date à laquelle on effectue la recherche
     * @param abrege true si on veut afficher les dates en abrégé
     * @return Vrai si au moins un rendez-vous à été trouvé
     */
    public static boolean afficheParDate(Date dateRecherche, boolean abrege) {
        
        int index;
        boolean RDVTrouve = false;
        
        System.out.println("\n========================================================\n" +
                           "   Rendez-Vous trouvé à la date:" + dateRecherche.toString() + 
                           "\n========================================================\n" );
        
        for(index = 0 ; index < listeRendezVous.size() ; index++) {
                if (listeRendezVous.get(index).getDateRDV().equals(dateRecherche)) {
                    System.out.println((abrege ? listeRendezVous.get(index).toStringAbrege()
                                              : listeRendezVous.get(index).toString()) + "\n");
                    RDVTrouve = true;
                }
        }
        if (!RDVTrouve) { 
            System.out.println("Aucun rendez-vous à la date " 
                               + dateRecherche.toString()
                               + ".\nRetour...");
            
        }
        return RDVTrouve;
    }
    
    /**
     * Affiche les RDV de la date en arguments 
     * @param recherche Tableau de Rendez-vous où chercher
     * @param dateRecherche Date à laquelle on effectue la recherche
     * @param horaireRecherche Horaire à laquelle on effectue la recherche
     * @return l'indice correspondant à l'élément trouvé, ou -1 si non trouvé
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
     * Recherche tous les rendez-vous à une date donnée dans l'ArrayList
     * @param dateRecherche Date à laquelle on effectue la recherche
     * @return Un tableau contenant tous les éléments correspondant
     *         à la recherche
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
     * Recherche de tous les rendez-vous d'une semaine associée
     * à une date. Affecte tous les rendez-vous correspondants
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
             * Teste si le rendez-vous est présent dans
             * un des 7 jours de la semaine.
             * Si oui on l'ajoute à la liste temporaire
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
     * @param date Date à laquelle on recherche l'élément à supprimer
     * @param horaire Horaire à laquelle on recherche l'élément à supprimer
     * @return vrai si l'élément a été trouvé et supprimé
     */
    public static boolean supprimerRendezVous(Date date,
                                              Horaire horaire) {
        
        int indexASupprimer = rechercherRDV(null, date, horaire);
        
        /* Détecte les erreurs d'indexage */
        if (indexASupprimer < 0
            || listeRendezVous.size() <= indexASupprimer) {
            return false;
        }
        listeRendezVous.remove(indexASupprimer);
        return true;
    }
    
    /**
     * Permet de modifier la date et l'horaire d'un rendez-vous
     * @param date Date à laquelle on recherche l'élément à déplacer
     * @param horaire Horaire à laquelle on recherche l'élément à déplacer
     * @return Vrai si l'élément à été correctement modifié
     */
    public static boolean deplacerRendezVous(Date date,
                                             Horaire horaire) {
        Date nouvDate;
        Horaire nouvHoraireDebut,
                nouvHoraireFin;
        
        RendezVous aDeplacer = listeRendezVous
                               .get(rechercherRDV(null, date, horaire));
        
        System.out.println(  "============================================\n"
                           + "  Saisie de la nouvelle période temporelle  \n"
                           + "============================================\n"
                           + "\n");
        
        nouvDate = GestionSaisie.saisirDate();
        do {
            nouvHoraireDebut = GestionSaisie.saisirHoraire(true);
            nouvHoraireFin = GestionSaisie.saisirHoraire(false);
            
            if (nouvHoraireDebut.compareTo(nouvHoraireFin) >= 0) {
                System.out.println("Erreur, l'horaire de fin ne peut "
                                   + "être ni inférieur, ni égal à l'horaire "
                                   + "de début. Réessayez.\n");
            }
            
        } while (nouvHoraireDebut.compareTo(nouvHoraireFin) >= 0);
        
        supprimerRendezVous(aDeplacer.getDateRDV(),
                            aDeplacer.getHoraireDebut());
        
        aDeplacer.setDateRDV(nouvDate);
        aDeplacer.setPlageHoraire(nouvHoraireDebut, nouvHoraireFin);
        
        return ajouterRendezVous(aDeplacer);
    }
    
    /**
     * Permet de modifier un élément de la liste par un nouvel élément
     * donné en paramètre
     * @param aRemplacer l'élément qui remplacera l'élément à modifier
     * @param date Date à laquelle on recherche l'élément à modifier
     * @param horaire Horaire à laquelle on recherche l'élément à modifier
     * @return Vrai si l'élément a été correctement modifié
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
     * Efface tous les rendez-vous à une année donnée.
     * Efface tout si le paramètre -1 est assigné
     * @param annee Année à effacer, -1 pour tout effacer
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
     * @return le tableau correspondant à l'ArrayList
     */
    public static RendezVous[] convertirListe() {
        
        RendezVous[] target = new RendezVous[listeRendezVous.size()];
        
       /* Conversion de la liste en tableau avant de la retourner */
       return listeRendezVous.toArray(target);
    }
    
    /**
     * Lancement de l'application Agenda.
     * Ouverture ou création du fichier binaire contenant les données
     * de l'agenda, puis appel de la classe GestionInterface
     * pour la génération de l'interface graphique
     * Fermeture de l'agenda en sauvegardant les données
     * @param args non utilisé
     */
    public static void main (String[] args) {
                        
        /* Sauvegarde temporaire de la liste provenant du fichier */
        ArrayList<RendezVous> listeTemp;
        
        /*
         * Tentative de récupération des données du fichier.
         * Vaut null si la tentative échoue
         */
        listeTemp = GestionFichier.restaurerAgenda();
        
        if (listeTemp == null) {
            System.out.println("Le fichier n'existe pas ou n'a pas pu être "
                               + "chargé.\n"
                               + "Création d'un nouvel Agenda.\n");
            
            /* Création d'une nouvelle liste de rendez-vous */
            listeRendezVous = new ArrayList<RendezVous>();
            
            /* Création ou écrasement s'il existe du fichier */
            GestionFichier.enregistrerAgenda(listeRendezVous);
            
        } else {
            System.out.println("Le fichier a correctement été chargé.\n");
            
            /* Restauration de la liste de rendez-vous dans la classe */
            listeRendezVous = listeTemp;
        }
        /* Lancement de l'interface de l'application */
        GestionInterface.lancementInterface();
        
        /* Sauvegarde des modifications */
        GestionFichier.enregistrerAgenda(listeRendezVous);
        
        System.out.println("L'agenda a correctement été sauvegardé.\n"
                           + "Au revoir !");
    }
}
