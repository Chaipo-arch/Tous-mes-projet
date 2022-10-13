/**
 * GestionFichier.java                     23 mai 2021
 * IUT info1 2020-2021 groupe 2, pas de licence
 */
package Gestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Gère la création, l'écriture et la lecture du fichier
 * contenant la liste des rendez-vous
 * @author pierre.farret
 */
public class GestionFichier {

    /** Nom du fichier contenant les données de l'agenda */
    public static final String NOM_FICHIER_AGENDA = "fichier_agenda.bin";
    
    private static File fichier = new File(NOM_FICHIER_AGENDA);
    
    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_HORAIRE
     * l'état actuel de l'agenda au travers de listeRendezVous
     * C'est un objet ArrayList contenant tous les rendez-vous
     * @param toSave La liste de rendez-vous à enregistrer
     * @return un booléen égal à vrai ssi la sauvegarde a bien été effecuée
     */
    public static boolean enregistrerAgenda(ArrayList<RendezVous> toSave) {
        
        boolean aReussi;      // vrai ssi l'enregistrement a réussi
        aReussi = true;
                
        // création et ouverture du fichier NOM_FICHIER_AGENDA
        try(ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(fichier))) {
                       
            // on écrit l'objet listeRendezVous dans le fichier
            oos.writeObject(convertirListe(toSave));
            
            oos.close();
            
        } catch (IOException erreur) {
            // une erreur s'est produite lors de l'accès au fichier
            aReussi = false;
        }
        return aReussi;        
    }
    
    /**
     * Restauration d'un agenda
     * Le fichier NOM_FICHIER_AGENDA contient la liste des rendez-vous
     * sous forme d'une ArrayList contenant des objets de type RendezVous
     * @return Un objet ArrayList contenant des objets RendezVous, ou
     *         null si la lecture du fichier échoue
     */
    public static ArrayList<RendezVous> restaurerAgenda()  {
        
        // objet tampon dans lequel est placé l'objet lu dans le fichier  
        ArrayList<RendezVous> toLoad = null;  
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fichier))) {           
            
            // lecture de l'objet contenu dans le fichier
            toLoad = convertirListe((String[][])ois.readObject());
            
            ois.close();
            
        } catch (ClassNotFoundException erreur) {
            // la donnée présente dans le fichier n'est pas un objet  
            
        } catch (ClassCastException erreur) {
            // la donnée lue dans le fichier n'est pas un objet à 2 dimensions   
            
        } catch (IOException erreur) {
            // problème d'accès au fichier
            
        }
        return toLoad;
    }
    
    /**
     * Permet de convertir le double tableau de String
     * en une ArrayList pour récupérer les données d'agenda
     * @param aConvertir Le tableau à convertir
     * @return L'arrayList converti
     */
    public static ArrayList<RendezVous> convertirListe(String[][] aConvertir) {
        
        ArrayList<RendezVous> converti = new ArrayList<>();
        RendezVous rdv;
        
        for (int i = 0 ; i < aConvertir.length ; i++) {
            
            rdv = new RendezVous(new Date(Integer.valueOf(aConvertir[i][2]),
                                          Integer.valueOf(aConvertir[i][1]),
                                          Integer.valueOf(aConvertir[i][0])),
                    
                                 new Horaire(Integer.valueOf(aConvertir[i][3]),
                                             Integer.valueOf(aConvertir[i][4]), 0),
                           
                                 new Horaire(Integer.valueOf(aConvertir[i][5]),
                                             Integer.valueOf(aConvertir[i][6]), 0),
                           
                                 aConvertir[i][7], aConvertir[i][8],
                           
                                 Boolean.valueOf(aConvertir[i][9]));
            
            converti.add(rdv);
        }
        return converti;
    }
    
    /**
     * Permet de convertir une ArrayList constitué de rendez-vous
     * en un double tableau de String
     * Utile pour pouvoir sérialiser l'objet dans un fichier 
     * @param aConvertir l'ArrayList à convertir
     * @return Un double tableau de String converti
     */
    public static String[][] convertirListe(ArrayList<RendezVous> aConvertir) {
        
        String[][] converti = new String[aConvertir.size()][10];
        
        for(int ligne = 0 ; ligne < aConvertir.size() ; ligne++ ) {

            // Date 
            converti[ligne][0] = Integer.toString(aConvertir.get(ligne).getDateRDV().getAn());
            converti[ligne][1] = Integer.toString(aConvertir.get(ligne).getDateRDV().getMois());
            converti[ligne][2] = Integer.toString(aConvertir.get(ligne).getDateRDV().getJour());
            // HoraireDebut
            converti[ligne][3] = Integer.toString(aConvertir.get(ligne).getHoraireDebut().getHeure());
            converti[ligne][4] = Integer.toString(aConvertir.get(ligne).getHoraireDebut().getMinute());
            // Horaire fin 
            converti[ligne][5] = Integer.toString(aConvertir.get(ligne).getHoraireFin().getHeure());
            converti[ligne][6] = Integer.toString(aConvertir.get(ligne).getHoraireFin().getMinute());
            
            converti[ligne][7] = aConvertir.get(ligne).getLibelleConcis();
            converti[ligne][8] = aConvertir.get(ligne).getLibelleComplet();
            converti[ligne][9] = Boolean.toString(aConvertir.get(ligne).getIsProfessionnal());

        }
        return converti;
    }
}
