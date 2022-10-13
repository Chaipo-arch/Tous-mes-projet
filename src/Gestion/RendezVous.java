/*
 * RendezVous.java                                 14 mai 2021                      Avril 2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
package Gestion;

/**
 * Classe permettant de prendre un rendez-vous, ainsi que
 * de voir, modifier, supprimer les rendez-vous.
 * D'autres informations concernant le rendez-vous peuvent
 * �tre modifi�es, telles que le libell�.
 * @author ahmed.bribach
 * @author pierre.farret
 * @author alexis.cabrol
 * @author valentin.simon
 */
public class RendezVous implements Comparable<RendezVous> {
    
    /** Taille maximum du titre du rendez-vous */
    public static final int TAILLE_MAX_LIBELLE_CONCIS = 8;

    /** Taille maximum de la description du rendez-vous */
    public static final int TAILLE_MAX_LIBELLE_COMPLET = 250;
    
    /** Date jour/mois/an associ�e au rendez-vous */
    private Date dateRDV;
    
    /** Horaire heure/minute/seconde associ� au d�but du rendez-vous */
    private Horaire horaireDebut;
    
    /** Horaire heure/minute/seconde associ� � la fin du rendez-vous */
    private Horaire horaireFin;
    
    /** Titre non vide et de longueur maximum TAILLE_MAX_LIBELLE_CONCIS(8) */
    private String libelleConcis;
    
    /** Description non vide */
    private String libelleComplet;
    
    private boolean isProfessionnal ;

    /**
     * Cr�e une instance d'un objet rendez-vous
     * @param dateRDV         La date du rendez-vous
     * @param horaireDebut    L'horaire de d�but du rendez-vous
     * @param horaireFin      L'horaire de fin du rendez-vous
     * @param libelleConcis   Le titre du rendez-vous
     * @param libelleComplet  La description du rendez-vous
     * @param isProfessionnal True pour professionnel et False pour personnel
     */
    public RendezVous(Date dateRDV,
                      Horaire horaireDebut, Horaire horaireFin,
                      String libelleConcis, String libelleComplet,
                      boolean isProfessionnal) {
           
        if (dateRDV == null) {
            throw new IllegalArgumentException(
                    "La date  sp�cifi�e dans le rendez-vous est invalide");
        
        } else if(horaireDebut == null || horaireFin == null || 
            // Horaire debut anterieur � horaire fin 
            horaireDebut.compareTo(horaireFin) >= 0 ) {
            throw new IllegalArgumentException(
                    "La plage horaire sp�cifi�e dans le rendez-vous est invalide");

        } else if (libelleConcis == null || libelleConcis.isBlank()
                   || libelleConcis.length() > TAILLE_MAX_LIBELLE_CONCIS) {
            throw new IllegalArgumentException(
                    "Le titre sp�cifi� pour le rendez-vous est invalide");
            
        } else if (libelleComplet == null || libelleComplet.isBlank()) {
            throw new IllegalArgumentException(
                    "La description sp�cifi�e pour le rendez-vous est invalide");
        } 
        
        this.dateRDV = dateRDV;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.libelleConcis = libelleConcis;
        this.libelleComplet = libelleComplet;
        this.isProfessionnal = isProfessionnal;
    }

    /**
     * @return la valeur de dateRDV
     */
    public Date getDateRDV() {
        return dateRDV;
    }

    /**
     * @return la valeur de horaireDebut
     */
    public Horaire getHoraireDebut() {
        return horaireDebut;
    }

    /**
     * @return la valeur de horaireFin
     */
    public Horaire getHoraireFin() {
        return horaireFin;
    }

    /**
     * @return la valeur de libelleConcis
     */
    public String getLibelleConcis() {
        return libelleConcis;
    }

    /**
     * @return la valeur de libelleComplet
     */
    public String getLibelleComplet() {
        return libelleComplet;
    }

    /**
     * @return la valeur de isProfessionnal
     */
    public boolean getIsProfessionnal() {
        return isProfessionnal;
    }

    /**
     * @param dateRDV valeur de dateRDV � saisir
     */
    public void setDateRDV(Date dateRDV) {
        this.dateRDV = dateRDV;
    }

    /**
     * @param horaireDebut horaire de d�but de la plage horaire
     * @param horaireFin   horaire de fin de la plage horaire
     */
    public void setPlageHoraire(Horaire horaireDebut,
                                Horaire horaireFin) {
        
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
    }

    /**
     * @param libelleConcis valeur de libelleConcis � saisir
     */
    public void setLibelleConcis(String libelleConcis) {
        
        if (libelleConcis == null || libelleConcis.isBlank()
            || libelleConcis.length() > TAILLE_MAX_LIBELLE_CONCIS) {
            
            throw new IllegalArgumentException(
                 "Le titre sp�cifi� pour le rendez-vous est invalide");
        }
        this.libelleConcis = libelleConcis;
    }

    /**
     * @param libelleComplet valeur de libelleComplet � saisir
     */
    public void setLibelleComplet(String libelleComplet) {
        
        if (libelleComplet == null || libelleComplet.isBlank()) {
            throw new IllegalArgumentException(
                    "La description sp�cifi�e pour le rendez-vous est invalide");
        }
        this.libelleComplet = libelleComplet;
    }

    /**
     * @param isProfessionnal valeur de isProfessionnal � saisir
     */
    public void setIsProfessionnal(boolean isProfessionnal) {
        this.isProfessionnal = isProfessionnal;
    }

    /**
     * non-javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return  "Rendez-Vous le : " + dateRDV.toString() 
                + "\nDe : " + horaireDebut.toString()
                + " ,� : " + horaireFin.toString()
                + "\nNature : " + (isProfessionnal ? "Professionnel" : "Personnel")
                + "\nObjet : " + libelleConcis
                + "\nDescription : " + libelleComplet;
    }

    /**
     * Transforme le rendez-vous sous forme
     * de cha�ne de caract�re br�ve
     * @return String le rendez-vous abr�g�
     */
    public String toStringAbrege() {
        return  "Rendez-Vous le : " + dateRDV.toString() 
                + "  De : " + horaireDebut.toString()
                + " ,� : " + horaireFin.toString()
                + "  Objet : " + libelleConcis;
    }

    /**
     * non-javadoc
     * @see java.lang.Comparable#compareTo(java.lang.object)
     */
    @Override
    public int compareTo(RendezVous aComparer) {
        
        if (dateRDV.compareTo(aComparer.getDateRDV()) < 0) {
            // aComparer est sup�rieur 
            return -1;
        // les dates sont �gales 
        } else if(dateRDV.equals(aComparer.getDateRDV())) {
            // on compare les horaire de d�but 
            // aComparer est sup�rieur 
            if (horaireDebut.compareTo(aComparer.getHoraireDebut()) > 0 ) {
                return -1;
            // les RDV sont �gaux 
            } else if(horaireDebut.equals(aComparer.getHoraireDebut())) {
                return 0;
            // aComparer est inf�rieur
            } else {
                return 1;
            }
        // les dates ne sont pas ordonn�es 
        } else {
            return 1;
        }
    }
}
