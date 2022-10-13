/*
 * FichierTexte.java          30 mai 2022
 * IUT de Rodez,Info1 2021-2022 pas de copyright
 */
package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.TextArea;


/** TODO commenter la responsabilit� de cette classe
 * @author 33766
 *
 */
public class EspaceTampon {

    /**cr�ation d'un nom de fichier par d�faut*/
    public static String nomFichier="nomParD�faut";
    /**cr�ation d'un fichier par d�faut*/
    public static File fichier = new File(nomFichier+".txt");

    /** TODO commenter le r�le de ce champ (attribut,role associatif) */
    public static File fichierbis = new File(nomFichier+"bis.txt");
    /** TODO commenter le r�le de ce champ (attribut,role associatif) */
    public static String aEcrire;

    /** 
     * permet de supprimer le fichier texte courant
     * 
     */
    public static void suppressionFichier() {
        File fichier = new File(nomFichier+".txt"); 
        fichier.delete();
    }

    /** 
     * permet de supprimer le fichier texte avec le nom mentionn� en param�tre
     * @param nomFichier
     */
    public static void suppressionFichier(String nomFichier) {
        File fichier = new File(nomFichier+".txt"); 

        if(fichier.exists()) { 
            fichier.delete();
        } else { 
            System.out.println("le fichier n'existe pas ou le nom mentionn� n'est pas le bon"); 
        } 
    }

    /** 
     * permet de cr�er un nouveau fichier texte avec comme nom sp�cifi� en param�tre
     * @param nomFichier
     * @throws IOException
     */
    public static void creationFichier(String nomFichier) throws IOException {
        int numero = 0;
        File fichier = new File(nomFichier+".txt");

        while(fichier.exists()) {
            numero++;
            nomFichier+="("+numero+")";
            fichier = new File(nomFichier+".txt");
            fichier.renameTo(fichier);
        } 
        fichier.createNewFile();
        File fichierbis = new File(nomFichier+"bis.txt");

        fichierbis.createNewFile();
        //ouvrirFichier(nomFichier,textArea);
    }

    
    
    
    /**
     * 
     * @param ligne
     * @return true si la ligne existe false sinon
     * @throws FileNotFoundException 
     */
    public static boolean ligneExiste(int ligne) throws FileNotFoundException {
        Scanner lecture = new Scanner(fichierbis);
        for(int indice =0;indice<ligne;indice++) {

            if(!lecture.hasNextLine()) {
                return false;
            }
            lecture.nextLine();
        }
        lecture.close();
        return true;
    }
    /**
     * supprime une ligne mentionn� du fichier courant
     * @param fichier
     * @param ligne
     * @throws IOException 
     */
    public static void suppression1Ligne(int ligne) throws IOException {

        String ecritFinal="";
        int indice = 1;
        String ligneCourante = "";
        Scanner lire = new Scanner(fichierbis);


        if(ligne <= 0|| ligne>100) {
            lire.close();
            throw new IllegalArgumentException("Il n'existe pas de ligne n�gative ou sup�rieure a 100");

        }
        while(lire.hasNextLine()){
            if(indice==ligne) {
                ligneCourante+="";
                lire.nextLine();
            }

            ligneCourante += lire.nextLine()+"\n";
            indice++;
        }


        lire.close();
        fichierbis.delete();

        FileWriter fw = new FileWriter(fichierbis,true);
        BufferedWriter ecrire = new BufferedWriter(fw);
        ecrire.write(ligneCourante);

        ecrire.close();
        System.out.println(ligneCourante);

    }
    /** 
     * Supprime plusieur ligne entre la ligneA et la ligneB
     * @param nomFichier
     * @param ligneA
     * @param ligneB
     * @throws IOException 
     */
    public static void suppressionPlusieurLigne(int ligneA , int ligneB) throws IOException {
        if(!ligneExiste(ligneB)&& !ligneExiste(ligneA)) {
            throw new IllegalArgumentException("aucune des ligne s�l�ctionnez n'existe");
        }


        String ecritFinal="";
        int indice = 1;
        String ligneCourante = "";
        Scanner lire = new Scanner(fichierbis);
        while(lire.hasNextLine()){
            if((indice>= ligneA && indice <= ligneB) ||(indice>= ligneB && indice <= ligneA)){

                ligneCourante+="";
                lire.nextLine();
            }

            ligneCourante += lire.nextLine()+"\n";
            indice++;
        }


        lire.close();
        fichierbis.delete();

        FileWriter fw = new FileWriter(fichierbis,true);
        BufferedWriter ecrire = new BufferedWriter(fw);
        ecrire.write(ligneCourante);

        ecrire.close();
        System.out.println(ligneCourante);


    }
    /**
     * methode qui permet d'ecrire une ligne de texte avant la ligne en param�tre
     * @param ligne
     * @throws IOException 
     */
    public static void ecrireLigneAvant(int ligne) throws IOException {
        String ecritFinal="";
        int indice = 1;
        String ligneCourante = "";
        Scanner lire = new Scanner(fichierbis);


        for(int caractere = 0;caractere<75 && caractere < aEcrire.length();caractere++) {
            ecritFinal += aEcrire.charAt(caractere);
        }
        if(ligne <= 0|| ligne>100) {
            lire.close();
            throw new IllegalArgumentException("Il n'existe pas de ligne n�gative ou sup�rieure a 100");

        }else if(ligne ==1) {
            ligneCourante+= aEcrire+"\n"; 
        }
        while(lire.hasNextLine()){
            if(indice==ligne) {
                ligneCourante+=aEcrire+"\n";
            }

            ligneCourante += lire.nextLine()+"\n";
            indice++;
        }
        for(;indice<=ligne ;indice++) {
            if(indice==ligne) {
                ligneCourante+=aEcrire+"\n";
            }else {
                ligneCourante +="\n";
            }
        }

        lire.close();
        fichierbis.delete();

        FileWriter fw = new FileWriter(fichierbis,true);
        BufferedWriter ecrire = new BufferedWriter(fw);
        ecrire.write(ligneCourante);

        ecrire.close();
        System.out.println(ligneCourante);

    }

    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param ligne
     * @throws IOException
     */
    public static void ecrireLigneApres(int ligne) throws IOException {
        if(!ligneExiste(ligne)) {
            throw new IllegalArgumentException("la ligne n'existe pas ");
        }
        String ecritFinal="";
        int indice = 1;
        String ligneCourante = "";
        Scanner lire = new Scanner(fichierbis);


        for(int caractere = 0;caractere<75 && caractere < aEcrire.length();caractere++) {
            ecritFinal += aEcrire.charAt(caractere);
        }
        if(ligne <= 0) {
            lire.close();
            throw new IllegalArgumentException("Il n'existe pas de ligne n�gative");

        }
        while(lire.hasNextLine()){
            ligneCourante += lire.nextLine()+"\n";
            if(indice==ligne) {
                ligneCourante+=aEcrire+"\n";
            }
            indice++;

        }

        lire.close();
        fichierbis.delete();

        FileWriter fw = new FileWriter(fichierbis,true);
        BufferedWriter ecrire = new BufferedWriter(fw);
        ecrire.write(ligneCourante);

        ecrire.close();
        System.out.println(ligneCourante);

    }

    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param fichier
     * @throws IOException
     */
    public static void enregistrer(File fichier) throws IOException {
        Scanner lecture = new Scanner(fichierbis);
        FileWriter wesh = new FileWriter(fichier,true);
        BufferedWriter ecrire = new BufferedWriter(wesh);
        while(lecture.hasNextLine()) {
            ecrire.write(lecture.nextLine());
            ecrire.newLine();
        }
        lecture.close();
        ecrire.close();
        fermeture();;
    }

    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param ligne
     * @throws IOException
     */
    public static void modifier(int ligne) throws IOException {
        if(!ligneExiste(ligne)) {
            throw new IllegalArgumentException("la ligne n'existe pas ");
        }
        suppression1Ligne(ligne);
        ecrireLigneApres(ligne);

    }

    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param ligneDebut
     * @param ligneFin
     * @param emplacementCopie
     * @throws IOException
     */
    public static void copie(int ligneDebut , int ligneFin, int emplacementCopie) throws IOException {
        if(!ligneExiste(ligneDebut)|| !ligneExiste(emplacementCopie)|| !ligneExiste(ligneFin)) {
            throw new IllegalArgumentException("impossible de copier vers une ligne qui n'existe pas");
        }
        
        String ecritFinal="";
        int indice = 1;
        String ligneCourante = "";
        String sauvegarde="";
        Scanner lire = new Scanner(fichierbis);
        Scanner lire2 = new Scanner(fichierbis);
        while(lire.hasNextLine()){
            while(indice>= ligneDebut && indice<= ligneFin) {
                sauvegarde+=lire.next()+"\n";
                ligneCourante+=lire.nextLine();
                indice++;
            }
            lire.nextLine();
            indice++;
        }
        indice=1;
        while(lire2.hasNextLine()){

            if(indice==emplacementCopie) {
                ligneCourante += sauvegarde+"\n";

            }else {
                ligneCourante += lire2.nextLine()+"\n";
                
            }
            indice++;
        }

        lire.close();
        lire2.close();
        fichierbis.delete();

        FileWriter fw = new FileWriter(fichierbis,true);
        BufferedWriter ecrire = new BufferedWriter(fw);
        ecrire.write(ligneCourante);

        ecrire.close();

    }
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param ligne
     * @param emplacementCopie
     * @throws IOException
     */
    public static void copie(int ligne , int emplacementCopie) throws IOException {

        if(!ligneExiste(ligne)|| !ligneExiste(emplacementCopie)) {
            throw new IllegalArgumentException("impossible de copier vers une ligne qui n'existe pas");
        }
        String ecritFinal="";
        int indice = 1;
        String ligneCourante = "";
        String sauvegarde = "";
        Scanner lire = new Scanner(fichierbis);
        Scanner lire2 = new Scanner(fichierbis);
        while(lire.hasNextLine()){
            if(indice==ligne) {
                sauvegarde=lire.next();
            }
            lire.nextLine();
            indice++;
        }
        indice=1;

        while(lire2.hasNextLine()){

            if(indice==emplacementCopie) {
                ligneCourante += sauvegarde+"\n";

            }else {
                ligneCourante += lire2.nextLine()+"\n";
            }
            indice++;
        }


        lire.close();
        lire2.close();
        fichierbis.delete();

        FileWriter fw = new FileWriter(fichierbis,true);
        BufferedWriter ecrire = new BufferedWriter(fw);
        ecrire.write(ligneCourante);

        ecrire.close();
        System.out.println(ligneCourante);

    }
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param ligneADeplacer
     * @param ligne
     * @throws IOException
     */
    public static void deplacement(int ligneADeplacer , int ligne) throws IOException {
        if(!ligneExiste(ligneADeplacer)|| ! ligneExiste(ligne)) {
            throw new IllegalArgumentException("les ligne r�f�renc� n'existepas");
        }
        copie(ligneADeplacer,ligne);                                                                             
        suppression1Ligne(ligneADeplacer);
    }
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param ligneADeplacerA
     * @param ligneADeplacerB
     * @param ligne
     * @throws IOException
     */
    public static void deplacement(int ligneADeplacerA,int ligneADeplacerB,int ligne) throws IOException {
        copie(ligneADeplacerA, ligneADeplacerB, ligne);
        suppressionPlusieurLigne(ligneADeplacerA, ligneADeplacerB);

    }
    /** TODO commenter le r�le de cette m�thode (SRP)
     * 
     */
    public static void fermeture() {
        fichierbis.delete();
    }
}