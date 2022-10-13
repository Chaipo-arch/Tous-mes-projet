/*
 * TestHoraire.java                     11 mai 2021
 * IUT info1 2020-2021 groupe 2, pas de licence
 */
package Gestion.test;

import Gestion.Horaire;

import java.util.Arrays;

/**
 * classe de test de la classe Horaire
 * @author pierre.farret
 * @author ahmed.bribach
 */
public class TestHoraire {
    
    
    private final static Horaire[]  VALIDE = { 
            new Horaire(8,20,50),
            new Horaire(0,0,0),
            new Horaire(23,59,59),
            new Horaire(10,50,0),  
    };
    
    private final static int[][] INVALIDE = {
            { 32, -4, 81 },
            {  4, 102, 74 },
            // Heures invalides
            { 24, 35, 20 },
            { 35, 40, 20 },
            { -1, 50, 50 },
            { Integer.MIN_VALUE, 47, 35 },
            // Minutes invalides
            {  6, 60, 45 },
            { 19, 78, 21 },
            { 22, -8, 36 },
            // Secondes invalides
            {  9, 12, 60 },
            { 15,  4, 98 },
            { 21, 46, -42 },
            {  3, 23, Integer.MAX_VALUE }
    };
    
    /**
     * Tests unitaires du constructeur {@link Horaire#Horaire(int, int, int)}
     */
    public static void testHoraireIntIntInt() {
        
        int index;
        Horaire aEssayer;
        
        for (index = 0;
             index < VALIDE.length;
             index++) {
            
            try {
                aEssayer = VALIDE[index];
            } catch (Exception e) {
                System.err.println("testHoraireIntIntInt nok : "
                                   + "erreur sur jeu valide no " + index);
            }
        }
        
        for (int[] aTester : INVALIDE) {
            
            try {
                new Horaire(aTester[0],
                            aTester[1],
                            aTester[2]);
                System.err.println("testHoraireIntIntInt nok : "
                                   + "pas d'erreur sur jeu invalide "
                                   + Arrays.toString(aTester));
            } catch (IllegalArgumentException e) {
                // Test OK
            }
        }
        System.out.println("testHoraireIntIntInt ok");
    }
    
    /**
     * TODO Description de la méthode
     */
    public static void testCompareTo() {
        
        final Horaire[] HORAIRE_INFERIEURE = {
                new Horaire(0, 0, 0),
                new Horaire(12, 46, 2),
                new Horaire(9, 32, 49),
                new Horaire(3, 42, 10),
                new Horaire(21, 27, 56)
        };
        
        final Horaire[] HORAIRE_SUPERIEURE = {
                new Horaire(0, 0, 1),
                new Horaire(16, 25, 0),
                new Horaire(9, 47, 39),
                new Horaire(3, 42, 57),
                new Horaire(21, 30, 59)
        };
        
        int indexeur,  // Indexeur de la boucle
            nbErreur;  // Nombre d'erreurs détectés
        nbErreur = 0;
        
        for (indexeur = 0;
             indexeur < HORAIRE_INFERIEURE.length;
             indexeur++) {
            
            if (HORAIRE_INFERIEURE[indexeur].compareTo(HORAIRE_SUPERIEURE[indexeur]) < 0) {
                // test ok
            } else {
                System.err.println("testCompareTo nok : Erreur sur jeu no " + indexeur + " :\n"
                                   + HORAIRE_INFERIEURE[indexeur].toString() + " < "
                                   + HORAIRE_SUPERIEURE[indexeur].toString() + " retourne faux");
                nbErreur += 1;
            }
            
            if (HORAIRE_SUPERIEURE[indexeur].compareTo(HORAIRE_INFERIEURE[indexeur]) > 0) {
                // test ok
            } else {
                System.err.println("testCompareTo nok : Erreur sur jeu no " + indexeur + " :\n"
                                   + HORAIRE_SUPERIEURE[indexeur].toString() + " > "
                                   + HORAIRE_INFERIEURE[indexeur].toString() + " retourne faux");
                nbErreur += 1;
            }
            
            if (HORAIRE_INFERIEURE[indexeur].compareTo(HORAIRE_INFERIEURE[indexeur]) == 0) {
                // test ok
            } else {
                System.err.println("testCompareTo nok : Erreur sur jeu no " + indexeur + " :\n"
                                   + HORAIRE_INFERIEURE[indexeur].toString() + " == "
                                   + HORAIRE_INFERIEURE[indexeur].toString() + " retourne faux");
                nbErreur += 1;
            }
            
            if (HORAIRE_SUPERIEURE[indexeur].compareTo(HORAIRE_SUPERIEURE[indexeur]) == 0) {
                // test ok
            } else {
                System.err.println("testCompareTo nok : Erreur sur jeu no " + indexeur + " :\n"
                                   + HORAIRE_SUPERIEURE[indexeur].toString() + " == "
                                   + HORAIRE_SUPERIEURE[indexeur].toString() + " retourne faux");
                nbErreur += 1;
            }
        }
        
        if (nbErreur == 0) {
            System.out.println("testCompareTo ok");
        } else {
            System.out.println("testCompareTo nok : " + nbErreur + " erreur(s) détectée(s)");
        }
    }
    

    
    /**
     * lancement des méthodes de test
     * @param args non utiliser
     */
    public static void main(String[] args) {
        
        testHoraireIntIntInt();
        testCompareTo();
    
    }
    
}