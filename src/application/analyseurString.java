///*
// * analyseurString.java 	      21 juin 2022
// * IUT de Rodez,Info1 2021-2022 pas de copyright
// */
//package application;
//
//import java.util.ArrayList;
//import application.Controller;
//import javafx.scene.control.TextField;
//
///** TODO commenter la responsabilité de cette classe
// * @author 33766
// *
// */
//public class analyseurString {
//
//
//    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
//    public  double nb1;
//
//    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
//    public  double nb2;
//
//    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
//    public static ArrayList<Double> nombre = new ArrayList<Double>();
//
//    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
//    public String chaine =  new Controller().getDisplay().getText() ;
//
//    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
//    public String chaine1;
//
//    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
//    public static ArrayList<Character> signe = new ArrayList<Character>();
//    /** TODO commenter le rôle de cette méthode (SRP)
//     * 
//     */
//    public void analyseur() {
//        int indiceSauvegarde = 0;
//        for(int indice =0;indice < chaine.length();indice++) {
//            if(chaine.charAt(indice) =='+'||chaine.charAt(indice) == '-' ||chaine.charAt(indice) == 'x' || chaine.charAt(indice) == '/') {
//                for(int indice2 = 0 ; indice2 < indice ; indice2++) {
//                    chaine1 += chaine.charAt(indice2);
//                    indiceSauvegarde = indice2;
//                }
//                nb1 = Double.valueOf(chaine1);
//                if(chaine.charAt(indice) == '+') {
//                    signe.add('+');
//                } else if(chaine.charAt(indice) == '-') {
//                    signe.add('-');
//                } else if(chaine.charAt(indice) == 'x') {
//                    signe.add('x');
//                } else if(chaine.charAt(indice) == '/') {
//                    signe.add('/');
//                }
//            }
//
//        }
//        chaine="";
//        for(int indice = indiceSauvegarde+1;indice < chaine.length();indice++) {
//            chaine1+=chaine.charAt(indice);
//        }
//        nb2 = Double.valueOf(chaine1);
//    }
//    /** TODO commenter le rôle de cette méthode (SRP)
//     * @return un double
//     * 
//     */
//    public double calcul() {
//        analyseur();
//        double nombre = 0;
//        if(signe.contains('+')) {
//            nombre = nb1 + nb2;
//        }
//        if(signe.contains('-')) {
//            nombre = nb1 - nb2;
//        }
//        if(signe.contains('x')) {
//            nombre = nb1 * nb2;
//        }
//        if(signe.contains('/')) {
//            nombre = nb1 / nb2;
//        }
//        return nombre;
//    }
//}
