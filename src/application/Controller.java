/*
 * Controller.java 	      21 juin 2022
 * IUT de Rodez,Info1 2021-2022 pas de copyright
 */
package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/** TODO commenter la responsabilité de cette classe
 * @author 33766
 *
 */
public class Controller {

    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
    public  double nb1;

    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
    public  double nb2;

    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
    public static ArrayList<Double> nombre = new ArrayList<Double>();

    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
    public String chaine1="";

    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
    public static ArrayList<Character> signe = new ArrayList<Character>();

    private String chaine;

    /** TODO commenter le rôle de ce champ (attribut,role associatif) */
    @FXML
    private TextField display;

    @FXML
    private void click0() {
        display.appendText("0");
    }
    @FXML
    private void click1() {
        display.appendText("1");
    }
    @FXML
    private void click2() {
        display.appendText("2");
    }
    @FXML
    private void click3() {
        display.appendText("3");
    }
    @FXML
    private void click4() {
        display.appendText("4");
    }
    @FXML
    private void click5() {
        display.appendText("5");
    }
    @FXML
    private void click6() {
        display.appendText("6");
    }
    @FXML
    private void click7() {
        display.appendText("7");
    }
    @FXML
    private void click8() {
        display.appendText("8");
    }
    @FXML
    private void click9() {
        display.appendText("9");
    }
    @FXML
    private void ajouter() {
        display.appendText("+");
    }
    @FXML
    private void soustraire() {
        display.appendText("-");
    }  
    @FXML
    private void multiplier() {
        display.appendText("x");
    }
    @FXML
    private void divise() {
        display.appendText("/");
    }
    @FXML
    private void supprimer() {
        display.deleteNextChar();
    }
    @FXML
    private void entree() {

        double resultat = calcul();
        display.clear();
        display.appendText("    "+resultat );
    }
    @FXML
    private void puissance() {
        display.appendText("^");
    }
    @FXML
    private void virgule() {
        display.appendText(".");
    }

    @FXML
    private void reset() {
        display.clear();
    }










    /** TODO commenter le rôle de cette méthode (SRP)
     * 
     */
    private int analyseur() {
        int nbIteration=0;
        double doubles;
        chaine = display.getText();
        int indiceSauvegarde1 = 0;
        int indiceSauvegarde2 = 0;
        for(int indice = 0 ; indice < chaine.length() ; indice++) {
            chaine1="";
            if(chaine.charAt(indice) =='+'||chaine.charAt(indice) == '^'||chaine.charAt(indice) == '-' ||chaine.charAt(indice) == 'x' || chaine.charAt(indice) == '/') {
                nbIteration++;
                indiceSauvegarde2 = indice ;

                for( ; indiceSauvegarde1 < indiceSauvegarde2 ; indiceSauvegarde1++) {
                    if( chaine.charAt(indiceSauvegarde1)=='x' 
                        || chaine.charAt(indiceSauvegarde1)=='/' || chaine.charAt(indiceSauvegarde1) == '^') {

                    }else {

                        chaine1 += chaine.charAt(indiceSauvegarde1);

                    }
                }
                indiceSauvegarde1 = indiceSauvegarde2;
                doubles = Double.valueOf(chaine1);
                nombre.add(doubles);

                if(chaine.charAt(indice) == '+') {
                    signe.add('+');
                } else if(chaine.charAt(indice) == '-') {
                    signe.add('-');
                } else if(chaine.charAt(indice) == 'x') {
                    signe.add('x');
                } else if(chaine.charAt(indice) == '/') {
                    signe.add('/');
                } else if(chaine.charAt(indice) == '^') {
                    signe.add('^');
                }
            }


        }
        chaine1="";
        indiceSauvegarde2++;
        for(;indiceSauvegarde2<chaine.length();indiceSauvegarde2++) {
            chaine1+=chaine.charAt(indiceSauvegarde2);
        }
        doubles = Double.valueOf(chaine1);
        nombre.add(doubles);

        return nbIteration;
    }
    /** TODO commenter le rôle de cette méthode (SRP)
     * @return un double
     * 
     */
    private double calcul() {
        int nb = analyseur();
        prioriteOperatoire();
        prioriteOpetatoire1();
        double nombreFinal = 0;
        nombreFinal = nombre.get(0);
        nombre.remove(0);

        while(!nombre.isEmpty()) {
            if(signe.get(0)=='x') {
                nombreFinal *= nombre.get(0);
                signe.remove(0);
            }
            else if(signe.get(0)=='/') {
                nombreFinal /= nombre.get(0);
                signe.remove(0);
            }
            else if(signe.get(0)=='+') {
                nombreFinal += nombre.get(0) ;
                signe.remove(0);
            }
            else if(signe.get(0)=='-') {
                nombreFinal -= nombre.get(0);
                signe.remove(0);
            }
            else if(signe.get(0)=='^') {
                nombreFinal = Math.pow( nombre.get(0),nombreFinal);
                signe.remove(0);
            }
            nombre.remove(0);
        }
        signe.clear();
        return nombreFinal;

    }

    private void prioriteOperatoire() {
        for(int indice = 0 ;indice < signe.size();indice++) {

            if(signe.get(indice)=='x') {
                nombre.add(0,nombre.get(indice));
                nombre.remove(indice+1);
                nombre.add(0,nombre.get(indice+1));
                nombre.remove(indice+2);
                signe.remove(indice);
                signe.add(0, 'x');
            } else if(signe.get(indice)=='/') {
                nombre.add(0,nombre.get(indice));
                nombre.remove(indice+1);
                nombre.add(0,nombre.get(indice+1));
                nombre.remove(indice+2);
                signe.remove(indice);
                signe.add(0, '/');
            }
        }

    }
    private void prioriteOpetatoire1() {
        for(int indice = 0 ;indice < signe.size();indice++) {
            if(signe.get(indice) == '^') {
                nombre.add(0,nombre.get(indice));
                nombre.remove(indice+1);
                nombre.add(0,nombre.get(indice+1));
                nombre.remove(indice+2);
                signe.remove(indice);
                signe.add(0, '^');
            }
        }
    }
   
}
