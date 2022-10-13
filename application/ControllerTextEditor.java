package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EventObject;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** TODO commenter la responsabilité de cette classe
 * @author 33766
 *
 */
public class ControllerTextEditor {



	@FXML
	private TextField TextField;

	@FXML
	private Button buttonAide;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonOpen;

	@FXML
	private  TextArea textArea;

	@FXML
	private Parent root;

	@FXML
	private Stage PrimaryStage;

	@FXML
	private Scene scene;

	public String text;

	private static char commande;

	private static String texteAjoute;

	private static int[] encadrementLigne = new int[2];

	private static int emplacement;

	private static String nomFichier;
	/**création d'un fichier par défaut*/
	public static File fichier ;

	/** TODO commenter le rôle de ce champ (attribut,role associatif) */
	public static File fichierbis ;

	@FXML
	void open(ActionEvent open) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
				);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		File fileToLoad = fileChooser.showOpenDialog(null);
		if(fileToLoad != null){
			lireFile(fileToLoad);		}
	}

	/**
	 * lit le fichier courant en entier
	 * @param String
	 * @throws FileNotFoundException
	 */
	public  void lireFichier(String nomFichier) throws FileNotFoundException {
		fichier = new File(nomFichier+".txt");
		Scanner lecture = new Scanner(fichier);
		textArea.setText("");
		String sauvegarde = "";
		while(lecture.hasNextLine()) {
			sauvegarde +=lecture.nextLine()+"\n";
		}
		lecture.close();

		textArea.setText(sauvegarde);

	}

	public void lireFile(File nomFichier) throws FileNotFoundException {
		Scanner lecture = new Scanner(fichier);
		textArea.setText("");
		String sauvegarde = "";
		while(lecture.hasNextLine()) {
			sauvegarde +=lecture.nextLine()+"\n";
			textArea.setText("");
		}
		lecture.close();
		textArea.setText(sauvegarde);

	}
	/** TODO commenter le rôle de cette méthode (SRP)
	 * 
	 */
	public static void fermeture() {
		fichierbis.delete();
	}
	/** TODO commenter le rôle de cette méthode (SRP)
	 * @param nomFichier
	 * @throws IOException
	 */
	public void ouvrirFichier(String nomFichier) throws IOException {
		File fichier = new File(nomFichier+".txt");
		Scanner lire = new Scanner(fichier);
		String ligneCourante="";

		if(fichier.exists()) {

			fichierbis = new File(nomFichier+"bis.txt");
			fichierbis.createNewFile();
			while(lire.hasNextLine()){
				ligneCourante+=lire.nextLine()+"\n";
			}
			lire.close();
			FileWriter writer = new FileWriter(fichierbis);
			BufferedWriter ecrire = new BufferedWriter(writer);
			ecrire.write(ligneCourante);
			ecrire.close();


		}else {
			throw new FileNotFoundException("fichier introuvable");
		}
	}
	/** TODO commenter le rôle de cette méthode (SRP)
	 * @param fichier
	 * @throws IOException
	 */
	public static void enregistrer(File fichier) throws IOException {
		fichier.delete();
		fichier.createNewFile();
		Scanner lecture = new Scanner(fichierbis);
		FileWriter wesh = new FileWriter(fichier,true);
		BufferedWriter ecrire = new BufferedWriter(wesh);
		while(lecture.hasNextLine()) {
			ecrire.write(lecture.nextLine());
			ecrire.newLine();
		}
		lecture.close();
		ecrire.close();
		fermeture();
	}
	public static void suppression1Ligne(int ligne) throws IOException {

		String ecritFinal="";
		int indice = 1;
		String ligneCourante = "";
		Scanner lire = new Scanner(fichierbis);


		if(ligne <= 0|| ligne>100) {
			lire.close();
			throw new IllegalArgumentException("Il n'existe pas de ligne négative ou supérieure a 100");

		}
		while(lire.hasNextLine()){
			if(indice==ligne) {
				ligneCourante+="";
				lire.nextLine();
			}

			ligneCourante += lire.nextLine()+"\n";
			indice++;
		}
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
			throw new IllegalArgumentException("aucune des ligne séléctionnez n'existe");
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






	@FXML
	void save(ActionEvent event) {

	}
	/** TODO commenter le rôle de cette méthode (SRP)
	 * @param aide
	 * @throws IOException
	 */
	@FXML
	public void aide(ActionEvent aide) throws IOException {

		root = FXMLLoader.load(getClass().getResource("HelpMenuInterface.fxml"));
		PrimaryStage = (Stage)((Node)aide.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("helpMenu.css").toExternalForm());
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
	}

	@FXML
	void textField(ActionEvent event) {
		TextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					text = TextField.getText();
					TextField.setText("");
					commande = EspaceCommande.analyseurCommande(text);
					text.toLowerCase();
					if (commande == 'q') {
						System.exit(0);
					} else if (commande == '?') {

					} else if (commande== 't') {
						try {
							nomFichier = EspaceCommande.traitementNomFichier(text);
							ouvrirFichier(EspaceCommande.traitementNomFichier(text));
							lireFichier(EspaceCommande.traitementNomFichier(text));
						} catch(IOException io) {
						}
					} else if (commande== 'f') {
						try {
							enregistrer(fichier);
						} catch (IOException e) {

						}


					} else if (commande== 'e') {
						int nb1 =EspaceCommande.traitement(text)[0];
						int nb2 = EspaceCommande.traitement(text)[1];
						if (nb2 !=0) {
							try {
								suppressionPlusieurLigne(nb1,nb2);
								enregistrer(fichier);
								lireFile(fichier);
							} catch (IOException e) {

							}
						}else {
							try {
								suppression1Ligne(nb1);
								enregistrer(fichier);
								lireFile(fichier);
							} catch (IOException e1) {
								
							}
						}

					} else if (commande== 'a') {
						System.out.println("commande a");
						EspaceCommande.traitementLigneUnique(text);
					} else if (commande== 'i') {
						System.out.println("commande i");
						EspaceCommande.traitementLigneUnique(text);
					} else if (commande== 'm') {
						System.out.println("commande m");
						EspaceCommande.traitementLigneUnique(text);
					} else if (commande== 'c') {
						System.out.println("commande c");
						EspaceCommande.traitementEmplacement(text);
					} else if (commande== 'd') {
						System.out.println("commande d");
						EspaceCommande.traitementEmplacement(text);
					} else {
						System.err.println(
								"ERREUR: Nom de commande Invalide.\n"
										+ "Vous pouvez vous renseigner dans"
										+ "l'aide grace à la commande : > ?");
						throw new IllegalArgumentException();
					}


				};
			}


		});
	}

}