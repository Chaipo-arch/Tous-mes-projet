/*
 * EspaceCommande.java                                             18/05/2022
 * INF01 S2 pas de copy right ni de droit d'auteur
 */
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * Classe de l'espace de saisie pour les commandes qui pourront
 * interagir avec l'espace texte.
 * @author mateo.faussurier emmanuel.gomes-soares INFO1
 *
 */
public class EspaceCommande {

	public static char commande;

	public static String texteAjoute;

	public static int[] encadrementLigne = new int[2];

	public static int emplacement;

	public static String nomFichier;

	/**
	 * 
	 * @param texteEntree
	 * @throws FileNotFoundException 
	 */
	
	/**
	 * Attrape le nom de la commande désignée par le premier caractère de la
	 * commande.
	 * @param texteEntree commande entrée dans l'espace de commande
	 * @return commande le caractere désignant la commande
	 */
	public static  char analyseurCommande(String texteEntree) {
		texteEntree = texteEntree.trim();
		String[] chaine = texteEntree.split(" ");
		if (chaine[0].length() > 1) {
			System.err.println("ERREUR: Nom de la commande invalide \n"
					+ "vous pouvez vous renseigner d'ans l'aide grace a la commande : > ?");
			throw new IllegalArgumentException();
		}
		commande = chaine[0].charAt(0);
		return commande;
	}

	/**
	 * Est invoquée seulement en cas de besoin. Attrape dans un tableau
	 * l'encadrement ou la ligne désignée par l'utilisateur sur la/lesquelle(s)
	 * il veut invoquer sa commande.
	 * @param texteEntree la commande entrée par l'utilisateur
	 * @return encadrementLigne est un encadrement à deux valeurs incluses
	 * 			désignant un encadrement de ligne,
	 * 			ou à une valeur désignant le numéro d'une ligne.
	 */
	public static int[] traitement(String texteEntree) {
		texteEntree = texteEntree.trim();
		texteEntree = texteEntree.replace(' ', ',');

		String[] chaine = texteEntree.split(",");

		boolean nombreTrouve = false;

		int premier = 0;
		int premierPrecedent;
		int unite = 1;
		int nombre;
		int rang;

		for (rang = 1;!nombreTrouve && rang < chaine.length; rang++) {
			if (chaine[rang].length() != 0) {

				for (int i = chaine[rang].length(); i > 0; i-- ) {
					if (!Character.isDigit(chaine[rang]
							.charAt(i - 1))) {
						System.err.println("ERREUR: le numero de ligne "
								+ "est incorrecte. Il faut que se soit un "
								+ " entier compris entre 1 (inclu) et 100 "
								+ "(inclu).");
						throw new IllegalArgumentException();
					}
					nombre = Character.getNumericValue(chaine[rang]
							.charAt(i - 1));
					premierPrecedent = unite * nombre;
					unite *= 10;
					premier += premierPrecedent;
					nombreTrouve = true;
				}
				encadrementLigne[0] = premier;
			}
		}
		nombreTrouve= false;
		premier = 0;
		unite = 1;

		for (int indice = rang;!nombreTrouve && indice < chaine.length; indice++) {

			if (chaine[indice].length() != 0) {

				for (int i = chaine[indice].length(); i > 0; i-- ) {

					if (!Character.isDigit(chaine[indice]
							.charAt(i - 1))) {
						System.err.println("ERREUR: le numero de ligne "
								+ "est incorrecte. Il faut que se soit un "
								+ " entier compris entre 1 (inclu) et 100 "
								+ "(inclu).");
						throw new IllegalArgumentException();
					}
					nombre = Character.getNumericValue(chaine[indice].charAt(i - 1));
					premierPrecedent = unite * nombre;
					unite *= 10;
					premier += premierPrecedent;
					nombreTrouve = true;
				}
				encadrementLigne[1] = premier;

				if (encadrementLigne[0] > encadrementLigne[1]) {
					encadrementLigne[1] += encadrementLigne[0];
					encadrementLigne[0] = encadrementLigne[1] - encadrementLigne[0];
					encadrementLigne[1] = encadrementLigne[1] - encadrementLigne[0];
				}
			}
		}
		return encadrementLigne;
	}
	
	/**
	 * Vérifie l'encadrement des lignes et l'emplacement mentionné dans la
	 * commande.
	 * @param texteEntree le texte entrée dans l'espace commande
	 */
	public static void traitementEmplacement(String texteEntree) {
		texteEntree = texteEntree.trim();
		texteEntree = texteEntree.replace(' ', ',');

		String[] chaine = texteEntree.split(",");

		boolean nombreTrouve = false;

		int premier = 0;
		int premierPrecedent;
		int unite = 1;
		int nombre;
		int rang;

		encadrementLigne[1] = 0;

		for (rang = 1; !nombreTrouve && rang < chaine.length; rang++) {
			if (chaine[rang].length() != 0) {

				for (int i = chaine[rang].length(); i > 0; i-- ) {
					if (!Character.isDigit(chaine[rang]
							.charAt(i - 1))) {
						System.err.println("ERREUR: le numero de ligne "
								+ "est incorrecte. Il faut que se soit un "
								+ " entier compris entre 1 (inclu) et 100 "
								+ "(inclu).");
						throw new IllegalArgumentException();
					}
					nombre = Character.getNumericValue(chaine[rang]
							.charAt(i - 1));
					premierPrecedent = unite * nombre;
					unite *= 10;
					premier += premierPrecedent;
					nombreTrouve = true;
				}
				encadrementLigne[0] = premier;
			}
		}
		nombreTrouve= false;
		premier = 0;
		unite = 1;

		for (int indice = rang; !nombreTrouve && indice < chaine.length; indice++) {

			if (chaine[indice].length() != 0) {

				for (int i = chaine[indice].length(); i > 0; i-- ) {
					if (!Character.isDigit(chaine[indice]
							.charAt(i - 1))) {
						System.err.println("ERREUR: le numero de ligne "
								+ "est incorrecte. Il faut que se soit un "
								+ " entier compris entre 1 (inclu) et 100 "
								+ "(inclu).");
						throw new IllegalArgumentException();
					}
					nombre = Character.getNumericValue(chaine[indice]
							.charAt(i - 1));

					premierPrecedent = unite * nombre;
					unite *= 10;
					premier += premierPrecedent;
					nombreTrouve = true;
				}
				emplacement = premier;
			}
			rang++;
		}

		nombreTrouve= false;
		premier = 0;
		unite = 1;
		for (int indice = rang ; !nombreTrouve && indice < chaine.length; indice++) {

			if (chaine[indice].length() != 0) {
				encadrementLigne[1] = emplacement;

				for (int i = chaine[indice].length(); i > 0; i-- ) {
					if (!Character.isDigit(chaine[indice]
							.charAt(i - 1))) {
						System.err.println("ERREUR: le numero de ligne "
								+ "est incorrecte. Il faut que se soit un "
								+ " entier compris entre 1 (inclu) et 100 "
								+ "(inclu).");
						throw new IllegalArgumentException();
					}
					nombre = Character.getNumericValue(chaine[indice]
							.charAt(i - 1));

					premierPrecedent = unite * nombre;
					unite *= 10;
					premier += premierPrecedent;
					nombreTrouve = true;
				}
				emplacement = premier;

				if (encadrementLigne[0] > encadrementLigne[1]) {
					encadrementLigne[1] += encadrementLigne[0];
					encadrementLigne[0] = encadrementLigne[1] - encadrementLigne[0];
					encadrementLigne[1] = encadrementLigne[1] - encadrementLigne[0];
				}
			}
		}
	}

	/**
	 * @param texteEntree
	 * @return encadrementLigne l'encadrement des lignes à sélectionner
	 * 			ou la ligne à sélectionner
	 */
	public static int[] traitementLigneUnique(String texteEntree) {
		texteEntree = texteEntree.trim();
		texteEntree = texteEntree.replace(' ', ',');

		String[] chaine = texteEntree.split(",");

		boolean nombreTrouve = false;

		int premier = 0;
		int premierPrecedent;
		int unite = 1;
		int nombre;
		int rang;

		for (rang = 1; !nombreTrouve && rang < chaine.length; rang++) {
			if (chaine[rang].length() != 0) {

				for (int i = chaine[rang].length(); i > 0; i-- ) {

					if (!Character.isDigit(chaine[rang]
							.charAt(i - 1))) {
						System.err.println("ERREUR: le numero de ligne "
								+ "est incorrecte. Il faut que se soit un "
								+ " entier compris entre 1 (inclu) et 100 "
								+ "(inclu).");
						throw new IllegalArgumentException();
					}
					nombre = Character.getNumericValue(chaine[rang].charAt(i - 1));

					premierPrecedent = unite * nombre;
					unite *= 10;
					premier += premierPrecedent;
					nombreTrouve = true;
				}
				encadrementLigne[0] = premier;
			}
		}
		return encadrementLigne;
	}

	/**
	 * Prend le nom du fichier mentionné dans la commande entrée,
	 * si le fichier n'existe pas il sera créé.
	 * @param texteEntree
	 * @return nomFichier le nom du fichier intercepté
	 */
	public static String traitementNomFichier(String texteEntree) {
		texteEntree = texteEntree.trim();
		texteEntree = texteEntree.replace(' ', ',');

		String[] chaine = texteEntree.split(",");

		for (int rang = 1;  rang < chaine.length; rang++) {
			if (chaine[rang].length() != 0) {
				nomFichier = chaine[rang];
			}
		}
		return nomFichier;
	}
	//	@Override
	//	S String toString() {
	//		return encadrementLigne[0] + " " + encadrementLigne[1]
	//				+ " " + emplacement;
	//	}
}
