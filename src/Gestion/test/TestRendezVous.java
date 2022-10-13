package Gestion.test;
import Gestion.RendezVous;

import java.util.Arrays;

import Gestion.Date;
import Gestion.Horaire;

public class TestRendezVous {

	private final static Date []  DATE_VALIDE = {
			 // premi�re et derni�re date valides
	        new Date(15, 8, 2021),
	        new Date(31, 12, 2022),
	        
	        new Date(31, 1, 2021),
	        new Date(28, 2, 2021),
	        
		
	};
	private final static Horaire [] HORAIRE_VALIDE_DEBUT = {
		new Horaire( 2 , 5 , 50 ), // heure , minute et seconde suppérieur
		new Horaire(5 , 15 , 0),// heure et minute suppérieur
		new Horaire(5 , 0 , 50), // heure et seconde suppérieur
		new Horaire(15 , 0 , 0), // heure supperieure
		new Horaire(4, 50 , 46), // minute et seconde suppérieur
		new Horaire(2, 50 , 15), // minute supérieur
		new Horaire(5 , 5 , 55), // seconde supérieur
			
	};
	private final static Horaire [] HORAIRE_VALIDE_FIN = {
		new Horaire(0 , 4 , 1), // heure minute et seconde inférieure
		new Horaire(4 , 6 , 51), // heure minute inferieur
		new Horaire (3 , 15 , 59 ), //heure et seconde inferieur
		new Horaire(2 , 50 , 51), // heure inferieur
		new Horaire(4, 30 , 30), // minute et seconde inferieur
		new Horaire(2, 10 , 55), // minute inferieur
		new Horaire(5 , 5 , 0), // seconde inferieure
		
	};
	private final static Date [] DATE_INVALIDE ={
		new Date( 20,04,2021), // mois invalide
		new Date( 18,04,2021), // jour invalide
		new Date(17,04,2021), // jour et mois invalide
	};
	private final static Horaire [] HORAIRE_INVALIDE_DEBUT = {
		new Horaire(0 , 4 , 1), // heure minute et seconde inférieure
		new Horaire(4 , 6 , 51), // heure minute inferieur
		new Horaire (3 , 15 , 59 ), //heure et seconde inferieur
		new Horaire(2 , 50 , 51), // heure inferieur
		new Horaire(4, 30 , 30), // minute et seconde inferieur
		new Horaire(2, 10 , 55), // minute inferieur
		new Horaire(5 , 5 , 0), // seconde inferieure
	
	};
	private final static Horaire [] HORAIRE_INVALIDE_FIN = {
		new Horaire( 2 , 5 , 50 ), // heure , minute et seconde suppérieur
		new Horaire(5 , 15 , 0),// heure et minute suppérieur
		new Horaire(5 , 0 , 50), // heure et seconde suppérieur
		new Horaire(15 , 0 , 0), // heure supperieure
		new Horaire(4, 50 , 46), // minute et seconde suppérieur
		new Horaire(2, 50 , 15), // minute supérieur
		new Horaire(5 , 5 , 55), // seconde supérieur
	};
			
	
	public static void testRendezVousValide() {
		 		
		new RendezVous( DATE_VALIDE[0] , HORAIRE_VALIDE_DEBUT[0] ,HORAIRE_VALIDE_FIN[0], "a", "b" , true);
		new RendezVous( DATE_VALIDE[1] , HORAIRE_VALIDE_DEBUT[1] ,HORAIRE_VALIDE_FIN[1], "5", "co",true);				
		new RendezVous( DATE_VALIDE[2] , HORAIRE_VALIDE_DEBUT[2] ,HORAIRE_VALIDE_FIN[2], "A", "c",true);
		new RendezVous( DATE_VALIDE[3] , HORAIRE_VALIDE_DEBUT[3] ,HORAIRE_VALIDE_FIN[3], "toto", ";",true);
		new RendezVous( DATE_VALIDE[0] , HORAIRE_VALIDE_DEBUT[4] ,HORAIRE_VALIDE_FIN[4], ",", "!",true);
		new RendezVous( DATE_VALIDE[1] , HORAIRE_VALIDE_DEBUT[5] ,HORAIRE_VALIDE_FIN[5], ":", ".",true);
		new RendezVous( DATE_VALIDE[2] , HORAIRE_VALIDE_DEBUT[6] ,HORAIRE_VALIDE_FIN[6], "+", "-",true);
		System.out.println("testRendezVousValide => ok");
		};
	public static void testRendezVousDateInvalide(){
		 try {
         new RendezVous( DATE_INVALIDE[0] , HORAIRE_VALIDE_DEBUT[0] ,HORAIRE_VALIDE_FIN[0], "a", "b",true);
		new RendezVous( DATE_INVALIDE[1] , HORAIRE_VALIDE_DEBUT[1] ,HORAIRE_VALIDE_FIN[1], "5", "co",true);
		new RendezVous( DATE_INVALIDE[2] , HORAIRE_VALIDE_DEBUT[2] ,HORAIRE_VALIDE_FIN[2], "A", "c",true);
		
	       
		
                throw new RuntimeException("Echec du test Date  " 
                                           );
            } catch(Exception bondour) {
                /* test Ok */
            
                 System.out.println(  "testRendezVousDateInvalide  => test Ok car invalide ");
            }
			
	}
	public static void testRendezVousHoraireInvalide(){
		try {
        new RendezVous( DATE_VALIDE[0] , HORAIRE_INVALIDE_DEBUT[0] ,HORAIRE_INVALIDE_FIN[0], "a", "b",true);
		new RendezVous( DATE_VALIDE[1] , HORAIRE_INVALIDE_DEBUT[1] ,HORAIRE_INVALIDE_FIN[1], "5", "co",true);
		new RendezVous( DATE_VALIDE[2] , HORAIRE_INVALIDE_DEBUT[2] ,HORAIRE_INVALIDE_FIN[2], "A", "c",true);
		new RendezVous( DATE_VALIDE[3] , HORAIRE_INVALIDE_DEBUT[3] ,HORAIRE_INVALIDE_FIN[3], " ", ";",true);
		new RendezVous( DATE_VALIDE[4] , HORAIRE_INVALIDE_DEBUT[4] ,HORAIRE_INVALIDE_FIN[4], ",", "!",true);
	       
		
                throw new RuntimeException("Echec du test Horaire  " 
                                           );
            } catch(IllegalArgumentException lancee) {
                /* test Ok */
            
                 System.out.print(  "testRendezVousHoraireInvalide => test Ok car invalide ");
		
			
	}
	}
	public static void main (String[]args) {
	      testRendezVousValide();
		  testRendezVousDateInvalide();
		  testRendezVousHoraireInvalide();
		
	}
}