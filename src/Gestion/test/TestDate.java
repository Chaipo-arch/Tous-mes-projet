/*
 * TestDate.java                        1 mai 2021
 * Iut info1 , Groupe projet, pas de droit
 */
package Gestion.test;

import Gestion.Date;

import java.util.Arrays;

/**
 * Tests unitaires de iut.datation.Date 
 * @author Valentin
 */
public class TestDate {
    
    /* 
     * tests de dates valides 
     * => possibilit� d'avoir une collection de dates valides 
     * pour tester le comportement d'instance
     */
    private final static Date[] VALIDES = {
        // premi�re et derni�re date valides
        new Date(1, 1, 2021),
        new Date(31, 12, 2022),
        // dernier jours valides de chaque mois (hors ann�e bissextiles)
        new Date(31, 1, 2021),
        new Date(28, 2, 2021),
        new Date(31, 3, 2021),
        new Date(30, 4, 2021),
        new Date(31, 5, 2021),
        new Date(30, 6, 2021),
        new Date(31, 7, 2021),
        new Date(31, 8, 2021),
        new Date(30, 9, 2021),
        new Date(31, 10, 2021),
        new Date(30, 11, 2021),
        new Date(31, 12, 2021),
        // jours � "l'int�rieur de chaque mois"
        new Date(15, 1, 2022),
        new Date(27, 2, 2022),
        new Date(22, 3, 2021),
        new Date(4, 4, 2021),
        new Date(29, 5, 2022),
        new Date(1, 6, 2021),
        new Date(10, 7, 2022), 
        new Date(13, 8, 2022),
        new Date(20, 9, 2022),
        new Date(14, 10, 2021), 
        new Date(26, 11, 2021),
        new Date(24, 12, 2022)


    };  

    private final static Date[] DATE_INF = {
        new Date(14, 1, 2022),
        new Date(26, 2, 2022),
        new Date(21, 3, 2021),
        new Date(3, 4, 2021),
        new Date(28, 5, 2022),
        new Date(1, 6, 2021),
        new Date(9, 7, 2022), 
        new Date(12, 8, 2022),
        new Date(19, 9, 2022),
        new Date(13, 10, 2021), 
        new Date(25, 11, 2021),
        new Date(23, 12, 2022)
    };  

    private final static Date[] DATE = {
        new Date(15, 1, 2022),
        new Date(27, 2, 2022),
        new Date(22, 3, 2021),
        new Date(4, 4, 2021),
        new Date(29, 5, 2022),
        new Date(2, 6, 2021),
        new Date(10, 7, 2022), 
        new Date(13, 8, 2022),
        new Date(20, 9, 2022),
        new Date(14, 10, 2021), 
        new Date(26, 11, 2021),
        new Date(24, 12, 2022)
    };  

    private final static Date[] DATE_SUP = {
        new Date(16, 1, 2022),
        new Date(28, 2, 2022),
        new Date(23, 3, 2021),
        new Date(5, 4, 2021),
        new Date(30, 5, 2022),
        new Date(3, 6, 2021),
        new Date(11, 7, 2022), 
        new Date(14, 8, 2022),
        new Date(21, 9, 2022),
        new Date(15, 10, 2021), 
        new Date(27, 11, 2021),
        new Date(25, 12, 2022)
    };  

    /**
     * Tests unitaires de Date(int, int, int)
     */
    public static void testDateIntIntInt() {

        /* indice du jour */
        final int JOUR = 0;
        /* indice du mois */
        final int MOIS = 1;
        /* indice de l'ann�e */
        final int AN = 2;

        /* combinaisons invalides */
        int[][] invalides = {
            // ann�es invalides (< AN_MIN)
            { 1, 5, -23580 },
            { 31, 12, 2020 },
            // ann�es invalides (> AN_MAX)
            { 1,  1, 2023 },
            { 15, 2, 2340 },
            // mois non gr�gorien (< 1)
            { 1, -1, 2022 },
            { 10, 0, 2021 },
            // mois non gr�gorien (> 12)
            { 7, 13, 2022 },
            { 12, 234567, 2021 },
            // jour non gr�gorien (< 1)
            { -1, 11, 2021 },
            {  0, 7, 2022 },
            // jour non gr�gorien (> 31)
            { 550, 1, 2022 },
            {  32, 3, 2022 },
            // compatibilit� jour/mois (sans ann�e bissextile)

        };


        for (int[] aEssayer : invalides) {
            System.out.print("Essai avec " + Arrays.toString(aEssayer));
            try {
                new Date(aEssayer[JOUR], aEssayer[MOIS], aEssayer[AN]);
                throw new RuntimeException("Echec du test DateIntINtINt avec " 
                                           + Arrays.toString(aEssayer));
            } catch(IllegalArgumentException lancee) {
                /* test Ok */
                System.out.println(Arrays.toString(aEssayer)
                                   + " => test Ok car invalide ");
            }
        }
    }

    /**
     * Tests unitaires de getJour()
     */
    public static void testGetJour() {

        int[] jourAttendu 
        = { 1, 31, 
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
            15, 27, 22, 4, 29, 1, 10, 13, 20, 14, 26, 24 };
        
        int noJeu;  // indice de liaison entre jourAttendu et VALIDES
        for (noJeu = 0 ; 
             noJeu < jourAttendu.length 
             && jourAttendu[noJeu] == VALIDES[noJeu].getJour();
             noJeu++)
            ; // corps vide
        
        if (noJeu < jourAttendu.length) {
            System.err.println("testGetJour() nok : erreur sur jeu " + noJeu);
        } else {
            System.out.println("testGetJour() r�ussi");
        } 
    }

     /**
     * Tests unitaires de getMois()
     */
    public static void testGetMois() {
        
        final int[] MOIS_ATTENDU 
        = {1, 12, 
           1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
           1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        
        int noJeu;  // indice de liaison entre jourAttendu et VALIDES
        for (noJeu = 0 ; 
             noJeu < MOIS_ATTENDU.length 
             && MOIS_ATTENDU[noJeu] == VALIDES[noJeu].getMois();
             noJeu++)
            ; // corps vide
        
        if (noJeu < MOIS_ATTENDU.length) {
            System.err.println("testGetMois() nok : erreur sur jeu " + noJeu);
        } else {
            System.out.println("testGetMois() r�ussi");
        } 
    }

    /**
     * Tests unitaires de getAnnee()
     */
    public static void testGetAn() {
        
        final int[] AN_ATTENDU
        = { 2021, 2022,
            2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021, 2021,
            2022, 2022, 2021, 2021, 2022, 2021, 2022, 2022, 2022, 2021, 2021, 2022 };
        
        int noJeu;  // indice de liaison entre jourAttendu et VALIDES
        for (noJeu = 0 ; 
             noJeu < AN_ATTENDU.length 
             && AN_ATTENDU[noJeu] == VALIDES[noJeu].getAn();
             noJeu++)
            ; // corps vide
        
        if (noJeu < AN_ATTENDU.length) {
            System.err.println("testGetAn() nok : erreur sur jeu " + noJeu);
        } else {
            System.out.println("testGetAn() r�ussi");
        } 
    }

    /**
     * Tests unitaires de toString()
     */
    public static void testToString() {
        
        final String[] FORMAT_ATTENDU = {
        "1/1/2021",
        "31/12/2022",
        // dernier jours valides de chaque mois (hors ann�e bissextiles)
        "31/1/2021",
        "28/2/2021",
        "31/3/2021",
        "3/4/2021",
        "31/5/2021",
        "30/6/2021",
        "31/7/2021",
        "31/8/2021",
        "30/9/2021",
        "31/10/2021",
        "30/11/2021",
        "31/12/2021",
        // jours � "l'int�rieur de chaque mois"
        "15/1/2022",
        "27/2/2022",
        "22/3/2021",
        "4/4/2021",
        "29/5/2022",
        "1/6/2021",
        "10/7/2022", 
        "13/8/2022",
        "20/9/2022",
        "14/10/2021", 
        "26/11/2021",
        "24/12/2022"
        };
        
        int noJeu;  // indice de liaison entre jourAttendu et VALIDES
        for (noJeu = 0 ; 
             noJeu < VALIDES.length 
             && FORMAT_ATTENDU[noJeu].equals(VALIDES[noJeu].toString());
             noJeu++)
            ; // corps vide
        
        if (noJeu < VALIDES.length) {
            System.err.println("testToString() nok : erreur sur jeu " + noJeu);
        } else {
            System.out.println("testToString() r�ussi");
            System.out.println("VALIDES = " + Arrays.toString(VALIDES));
        } 
    }

    public static void testCompareTo() {
        for(int i = 0 ; i < DATE.length ; i++) {
            System.out.print(DATE[i].compareTo(DATE_INF[i]));
        }

    }

    //public static void testEquals() {

    //}

    //public static void testDateOrdonnee() {

    //}

    /**
     * Lancement des m�thodes de test
     * avec arret en cas d'�chec d'un test 
     * @param args non utilise
     */
    public static void main(String... args) {

        try {
            testDateIntIntInt();
        } catch(Exception echec) {
            System.err.println("Echec test : " + echec.getMessage());
        }

        //testGetJour();
        //testGetMois();
        //testGetAn();
        //testToString();
        testCompareTo();
        //testEquals();
        //testDateOrdonnee();
    }
    
}