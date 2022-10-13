/*
 * Horaire.java                     11 mai 2021
 * IUT info1 2020-2021 groupe 2, pas de licence
 */
package Gestion;

/**
 * Génère des horaires comprenant des heures, des minutes
 * et des secondes.
 * @author pierre.farret
 * @author ahmed.bribach
 * @author antonin.claudel
 */
public class Horaire implements Comparable<Horaire> {
    
    /** Heure associée à l'horaire(entre 0 et 23) */
    private int heure;
    
    /** Minutes associées à l'horaire (entre 0 et 59 compris) */
    private int seconde;
    
    /** Secondes associées à l'horaire (entre 0 et 59 compris) */
    private int minute;
    
    /**
     * Constructeur qui génère un horaire
     * @param heure heure à saisir
     * @param seconde seconde à saisir
     * @param minute minute à saisir
     */
    
    public Horaire(int heure, int minute, int seconde) {
        super();
        if (!isValide(heure, minute, seconde))  {
            throw new IllegalArgumentException("erreur dans l'horaire") ;  
        }
        this.heure = heure;
        this.seconde = seconde;
        this.minute = minute;
        
    }
    
    private static boolean isValide(int h ,int min, int sec ) {
        return 24 > h && h >= 0
        &&     60 > min && min >= 0
        &&     60 > sec && sec >= 0;
    }
    
    private static int convertirSeconde(Horaire aConvertir) {
        return aConvertir.getHeure() * 3600 
               + aConvertir.getMinute() *60
               + aConvertir.getSeconde();
    }
    
    /**
     * non javadoc
     * @see java.lang.Comparable#compareTo(java.lang.object)
     */
    @Override
    public int compareTo(Horaire aComparer) {
        
        if (convertirSeconde(this) < convertirSeconde(aComparer)) {
            return -1;
            
        } else if (convertirSeconde(this) == convertirSeconde(aComparer)){
            return 0;
            
        } else {
            return 1;
        }
    }

    /**
     * Methode qui permet de verifier si deux dates sont égales ou pas
     * utilise compareTo 
     * @param aTester
     * @return true si les dates sont égales
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Horaire && compareTo((Horaire) obj ) == 0 ;
    }
    
    /**
     * @return the heure
     */
    public int getHeure() {
        return heure;
    }
    /**
     * @return the seconde
     */
    public int getSeconde() {
        return seconde;
    }
    /**
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }
    
    public String toString() {
        return  Integer.toString(heure) + ":"  + minute + ":" + seconde;
    }
}
