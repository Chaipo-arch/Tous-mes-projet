/*
 * Date.java                              11 mai 2021
 * IUT info1 2020-2021 groupe agenda 1, pas de droits d'auteur
 */
package Gestion;

/**
 * Calendrier de date grégorienne 
 * format jj/mm/aaaa
 * @author valentin.simon
 * @author ahmed.bribach
 * @author pierre.farret
 */
public class Date implements Comparable<Date> {

    /** Année minimale gérée par l'agenda */
    public static final int AN_MIN = 2021;
    
    /** Année maximale gérée par l'agenda */
    public static final int AN_MAX = 2022;
    
    /** Nombre de jour dans chaque mois */
    public static final int[] DUREE_MOIS =
           { 0 , // Le mois 0 n'existe pas
            31 , 28 , 31 , 30 , 31 , 30, 
            31 , 31 , 30 , 31 , 30 , 31 };
    
    /** Numéro de l'année compris entre 2021 et 2022 */
    private int an;
    
    /** Numéro du mois compris entre 1 et 12 */
    private int mois;
    
    /** Numéro du jour compris entre 1 et 31 */
    private int jour;
    
    /**
     * Crée une date jj/mm/aaaa avec les paramètres entrés
     * @param jour  numéro du jour dans le mois
     * @param mois  numéro du mois dans l'année
     * @param an    numéro de l'année
     */
    public Date(int jour , int mois , int an) {
        
        if (!isValide(jour,mois,an)) {
            throw new IllegalArgumentException("combinaison invalide"
                    + "ereur au niveau de la saisie de la date");
        }
        this.an = an;
        this.mois = mois;
        this.jour = jour;
    }
    
    /**
     * Vérifie la validité du jour, du mois et de l'année entrés en paramètre
     * @param jj    jour du mois
     * @param mm    mois de l'année
     * @param aaaa  année
     * @return vrai si la date est valide
     */
    private static boolean isValide(int jj, int mm, int aaaa) {
        
        return AN_MIN <= aaaa && aaaa <= AN_MAX 
               && 1 <= mm && mm <= 12 
               && jj >= 1 && jj<= DUREE_MOIS[mm];
    }
    
    /**
     * Détermine le jour de la semaine de la date en instance
     * @return un entier entre 0 et 6, représentant lundi(0),
     *         mardi(1), mercredi(2), jeudi(3), vendredi(4),
     *         samedi(5) ou dimanche(6)   
     */
    public int getDayOfWeek() {
        
        /* Constantes utiles à l'algorithme associées à chaque mois */
        final int[] VALEUR_MOIS = { 0, // Valeur inutilisée
                4, 0, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2
        };
        
        int noJourSemaine;  // Numéro du jour de la semaine
        
        /*
         * Utilisation de l'algorithme de Delambre pour déterminer
         * le jour de la semaine d'une date suivant le calendrier Grégorien
         */
        noJourSemaine = an % 100 / 4 + an / 400 + an % 100
                        + VALEUR_MOIS[mois] + jour + 2 + 5 * an / 100;
        
        return noJourSemaine % 7;
    }
    
    /**
     * Détermine la date d'un jour appartenant à la semaine
     * de la date en instance
     * @param noDayOfWeek numéro du jour de la semaine de 0 à 6
     *                    ( de lundi(0) jusqu'à dimanche(6) ) 
     * @return La date correspondant à ce jour en question ou
     *         null si la date générée est incorrecte
     */
    public Date searchDayOfWeek(int noDayOfWeek) {
        
        int differenceNbJour,
            jourATrouver,
            moisATrouver,
            anATrouver;
        
        jourATrouver = jour;
        moisATrouver = mois;
        anATrouver = an;
        
        /*
         * Calcule la différence entre le jour de la semaine
         * entré en paramètre et celui de la date d'instance.
         */
        differenceNbJour = noDayOfWeek - this.getDayOfWeek();
        
        /* Ajoute ou soustrait le résultat de la différence */
        jourATrouver += differenceNbJour;
        
        /* Si la somme vaut moins de 1, on passe au mois inférieur */
        if (jourATrouver < 1) {
            
            moisATrouver -= 1;
            
            /* Si le mois vaut 0, passage au mois 12 de l'année an - 1 */
            if (moisATrouver < 1) {
                moisATrouver = 12;
                anATrouver -= 1;
            }
            /* Calcul du nouveau jour à trouver */
            jourATrouver += DUREE_MOIS[moisATrouver];
        
        /* Si la somme dépasse la durée du mois, on passe au mois supérieur */
        } else if (jourATrouver > DUREE_MOIS[moisATrouver]) {
            
            /* Calcul du nouveau jour à trouver */
            jourATrouver -= DUREE_MOIS[moisATrouver];
            moisATrouver += 1;
            
            /* Si le mois vaut 13, passage au mois 1 de l'année an + 1 */
            if (moisATrouver > 12) {
                moisATrouver = 1;
                anATrouver += 1;
            }
        }
        
        /* Si la date nouvellement générée n'est pas valide alors elle retourne null */
        if (!Date.isValide(jourATrouver, moisATrouver, anATrouver)) {
            return null;
        }
        return new Date(jourATrouver, moisATrouver, anATrouver);
    }
    
// /** 
//  * Prédicat de test de an bissextile : an divisible par 4 mais pas par 100
//  * ou alors divisible par 400 utile uniquement en cas de mise a jour de l'application si on souhaite changer la constante AN_MAX
//  * @param an année à tester
//  * @return true si an bissextile, false sinon
//  */
// public static boolean isBissextile(int an) {
//     return an % 4 == 0 && (an % 100 != 0 || an % 400 == 0);
// }
    
    /**
     * Methode qui permet de verifier si deux dates sont égales ou pas
     * utilise compareTo 
     * @param aTester
     * @return true si les dates sont égales
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Date && compareTo((Date) obj ) == 0 ;
    }

    /**
     * Conversion d'une date en un entier pour faciliter les opérations
     * @param aConvertir  Date à convertir
     * @return la date en format integer de la forme aaaammjj
     */
    public int convertirInt(Date aConvertir) {
        return aConvertir.getAn() * 10000 + aConvertir.getMois() * 100 + aConvertir.getJour(); 
    }

    /**
     * 
     * @param date1
     * @param date2
     * @return un booleen qui indique si les dates sont ordonnees 
     */
    public static boolean dateOrdonnee(Date date1 , Date date2) {
        
        if (date1.compareTo(date2) < 0) {
            return true;
            
        } else {
            return false;
        }
    }
    
    /**
     * @return la valeur de jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * @return la valeur de mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * @return la valeur de an
     */
    public int getAn() {
        return an;
    }

    /**
     * Non-javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Integer.toString(getJour()) + '/' + getMois() + '/' + getAn();
    }
    
    /**
     * non javadoc 
     * @see java.lang.Comparable#compareTo(java.lang.object)
     */
    @Override
    public int compareTo(Date aComparer) {
        
        if (convertirInt(this) < convertirInt(aComparer)) {
            return -1;
            
        } else if (convertirInt(this) == convertirInt(aComparer)){
            return 0;
            
        } else {
            return 1;
        }
    }
}
