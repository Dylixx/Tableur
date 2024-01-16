package fr.iutfbleau.SAE;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * La classe <code>Cellule</code> représente une cellule dans une feuille de calcul.
 * Elle hérite de <code>JLabel</code> et gère les formules, les valeurs, les erreurs et les dépendances de la cellule.
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class Cellule extends JLabel{

	/*
	* String qui se verra dans le JTextField
	*/
	private String formule =null;

	/*
	* ancienne formule stockée pour gérer les dépendances
	*/	
	private String ancienneFormule = "";

	/*
	* String affiché dans la cellule
	*/
	private String resultat = null;

	/*
	* valeur de la cellule si elle en a une
	*/
	private Double valeur = null;

	/*
	* couleur de la cellule afin de la faire revenir à sa couleur intiale
	*/
	private Color couleurActuelle;

	/*
	* arbre de calcul de la cellule
	*/
	private ArbrePrefix arbre =null;

	/*
	* cle de la cellule dans le dictionnaire
	*/
	private String cle;

	/*
	* liste de cellule qui dépendent de la cellule dans laquelle on est
	*/
	private ArrayList<String> dependances = new ArrayList<>();

	/**
     * Constructeur de la classe Cellule.
     *
     * @param cle La clé identifiant la cellule.
     */
	public Cellule(String cle){
		this.cle = cle;
		this.setBackground(new Color(250,250,250));
		this.couleurActuelle = new Color(250,250,250);
	}

	/**
     * Actualise la cellule avec une nouvelle formule et met à jour ses dépendances et valeur.
     *
     * @param formule La nouvelle formule à assigner à la cellule.
     * @param dictionnaire Le dictionnaire des cellules pour gérer les dépendances.
     */
	public void actualisation(String formule, Map<String, Cellule> dictionnaire){
		
		
		if(this.formule==null){
			this.formule = formule;	
			update(dictionnaire);

		}else{
			this.ancienneFormule = this.formule;
			this.formule = formule;
			update(dictionnaire);
		}

		if(isReferenceCirculaire(dictionnaire, this.cle)){
				erreur("circulaire");
				return;
		}	
		
		verification(formule, dictionnaire);
		for(String cle : this.dependances){
			System.out.println("calcul de l'abre de la cellule : "+cle);
			dictionnaire.get(cle).reCalcul(dictionnaire);
		}			
	}

	/**
     * Recalcule la valeur de cette cellule en fonction de son arbre d'expression actuel.
     *
     * @param dictionnaire Le dictionnaire des cellules pour gérer les dépendances.
     */
	public void reCalcul(Map<String, Cellule> dictionnaire){
		System.out.println("on calcul l'abre de : "+this.cle);
		String res = this.arbre.calculArbre();
		if(erreur(res)){
			System.out.println("erreur produite : "+res);
			return;
		}
		this.valeur = Double.parseDouble(res);
		System.out.println("nouvelle valeur de la cellule "+this.cle+" "+this.valeur);
		this.resultat = Double.toString(this.valeur);
		updateCellule();

			for(String cle : this.dependances){
				System.out.println("calcul de l'arbre de la cellule : "+cle);
				dictionnaire.get(cle).reCalcul(dictionnaire);
			}
		if(this.dependances.isEmpty()){
			String res2 = this.arbre.calculArbre();
		if(erreur(res2)){
			System.out.println("erreur produite : "+res2);
			return;
		}
		this.valeur = Double.parseDouble(res2);
		System.out.println("nouvelle valeur de la cellule "+this.cle+" "+this.valeur);
		this.resultat = Double.toString(this.valeur);
		updateCellule();
		}
		isReferenceCirculaire(dictionnaire, this.cle);
	}

	/**
     * Permet de mettre à jour la cellule apres une modification des cellule dont elle dépend
     */
	private void updateCellule() {
		this.couleurActuelle = new Color(250,250,250);
        this.setText(getResultat());
        this.setBackground(getColor());
    }

    /**
     * Retourne la couleur actuelle de la cellule.
     *
     * @return La couleur actuelle.
     */
	public Color getColor(){
		return this.couleurActuelle;
	}

	/**
     * Retourne la formule de la cellule.
     *
     * @return La formule.
     */
	public String getFormule(){
		return this.formule;
	}

	/**
     * Retourne le résultat actuel de la cellule.
     *
     * @return Le résultat sous forme de chaîne de caractères.
     */
	public String getResultat(){
		return this.resultat;
	}

	/**
     * Retourne le résultat actuel de la cellule.
     *
     * @param cle code de la cellule à ajouter
     */
	public void addDependances(String cle){
		this.dependances.add(cle);
	}

	/**
     * Retourne la valeur actuel de la cellule.
     *
     * @param cle code de la cellule à ajouter
     * @return la valeur de la cellule sous forme de String
     */
	public String getValeur(){
		System.out.println("getValeur : "+this.valeur);
		return Double.toString(this.valeur);
	}

	/**
     * Retourne le résultat actuel de la cellule.
     *
     * @param cle code de la cellule à ajouter
     * @return la valeur de la cellule sous forme de String
     */
	public String getValeurCle(){
		System.out.println("Valeur de la case cliquée : "+this.valeur);
		return this.cle;
	}

	/**
     * Verifie le type de la cellule, et fait des calcul + receptionne les erreurs
     *
     * @param formule le String a traité
     * @param dictionnaire Le dictionnaire des cellules pour gérer les dépendances
     */
	private void verification(String formule, Map<String, Cellule> dictionnaire){
		String[] tableauText = formule.split(" ");

		if(tableauText[0].equals("+") || tableauText[0].equals("-") || tableauText[0].equals("/") || tableauText[0].equals("*")){
			System.out.println("dedans");
			this.arbre = new ArbrePrefix(dictionnaire);
			for(String s : tableauText){
				if(!this.arbre.addValeur(s)){
					erreur("incorrecte");
					return;
				}
			}
			String res = arbre.calculArbre();

			if(erreur(res)){
				return;
			}
			System.out.println("valeur de res avant valeur : "+res);
			this.valeur = Double.parseDouble(res);
			System.out.println("valeur cellule est : "+this.valeur);
			this.resultat = Double.toString(this.valeur);
		}else{
			try{
				this.valeur=Double.parseDouble(tableauText[0]);
				this.resultat=Double.toString(this.valeur);
				System.out.println("res :"+this.resultat);
			}catch(NumberFormatException e){
				this.resultat=this.formule;
				
			}		
		}
		updateCellule();
	}

	/**
     * Verifie le type de la cellule, et fait des calcul + receptionne les erreurs
     *
     * @param erreur String a traité qu'a retourné la création ou calcul de l'arbre
     * @return true si il y un erreur, false sinon
     */
	private boolean erreur(String erreur){

		switch(erreur){
		case "incomplet":
			this.couleurActuelle = new Color(255, 165, 0);
			this.resultat = "INCOMPLET";
			return true;

		case "incorrecte":
			this.couleurActuelle = new Color(255, 0, 0);
			this.resultat = "INCORRECTE";
			return true;

		case "incalculable":
			this.couleurActuelle = new Color(128, 0, 128);
			System.out.println("ancienne valeur de resultat : "+this.resultat);
			this.resultat = "INCALCULABLE";
			System.out.println("nouvelle valeur de resultat : "+this.resultat);
			return true;

		case "div par 0":
			this.couleurActuelle = new Color(255, 255, 0);
			this.resultat = "DIV PAR 0";
			return true; 

		case "circulaire":
			this.couleurActuelle = new Color(0, 0, 255);
			this.resultat = "REF CIRCULAIRE";
			return true;
		}
		return false;
	}

	/**
     * Verifie s'il y des références circulaires
     *
     * @param currentKey clé de la cellule qui lance cette méthode en premiere
     * @param dictionnaire Le dictionnaire des cellules pour gérer les dépendances
     * 
     * @return true s'il y une référence circulaire, false sinon
     */
	private boolean isReferenceCirculaire(Map<String, Cellule> dictionnaire, String currentKey) {
    // Vérifier si la clé actuelle est déjà dans les dépendances (c'est-à-dire déjà visitée)
    if (this.dependances.contains(currentKey)) {
        return true;
    }

    // Marquer la cellule actuelle comme visitée
    //this.dependances.add(currentKey);

    // Parcourir les dépendances de la cellule actuelle
    for (String s : this.dependances) {
        // Récursivement explorer la dépendance
        Cellule celluleDependante = dictionnaire.get(s);
        if (celluleDependante != null && celluleDependante.isReferenceCirculaire(dictionnaire, currentKey)) {
            return true;
        }
    }
    return false;
}
	
	/**
     * Verifie si le string donné est une Référence
     *
     * @param formule le String a traité
     * @return true si c'est une référence, false sinon
     */
	private boolean isReference(String s){
		String regex = "[A-I][1-9]";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		if(matcher.matches()){
			return true;
        }
        return false;
    }

    /**
     * Ajoute des clé au tableau des dépendances
     *
     * @param clé la clé à ajouter
     * @param dictionnaire Le dictionnaire des cellules pour gérer les dépendances
     */
    private void ajouter(String cle, Map<String, Cellule> dictionnaire){
    	if (!this.dependances.contains(cle)) {
        this.dependances.add(cle);
        System.out.println("On ajoute dans "+this.cle+" la cle "+cle);
    	}	
    }

    /**
     * Verifie le type de la cellule, et fait des calcul + receptionne les erreurs
     *
     * @param cle la clé à enlever du tableau des dépendances
     */
    private void enlever(String cle){
    	if (this.dependances.contains(cle)) {
    		System.out.println("On supprime dans "+this.cle+" la cle "+cle);
        this.dependances.remove(cle);
    	}
    }

    /**
     * Verifie le type de la cellule, et fait des calcul + receptionne les erreurs
     *
     * @param aSupprimer ArrayList stockant les références auxquelles la cellule ne dépend plus
	 * @param aAjouter ArrayList stockant les références auxquelles la cellule ne dépendait pas encore
     */
    private void comparer(ArrayList<String> aSupprimer, ArrayList<String> aAjouter){
    	ArrayList<String> ancienne, nouvelle;
    	ancienne = new ArrayList<>();
    	nouvelle = new ArrayList<>();
    	String[] tabStringAncien;
    	tabStringAncien = this.ancienneFormule.split(" ");

    	System.out.println("ancienne formule : "+this.ancienneFormule);
    	System.out.println("nouvelle formule : "+this.formule);
    	for(String s : tabStringAncien){
    		if(isReference(s)){
    			ancienne.add(s);		
    		}
    	}  	
    	String[] tabStringNouveau = this.formule.split(" ");

    	for(String s : tabStringNouveau){
    		if(isReference(s)){
    			nouvelle.add(s);
    		}
    	}

    	aSupprimer.addAll(ancienne);
    	aSupprimer.removeAll(nouvelle);
    	System.out.println("Elements à supprimer : " + aSupprimer);

    	aAjouter.addAll(nouvelle);
    	aAjouter.removeAll(ancienne);
    	System.out.println("Elements à ajouter : " + aAjouter);

    }

    /**
     * Permet de mettre à jour les dépendances de la cellule
     *
	 * @param dictionnaire Le dictionnaire des cellules pour gérer les dépendances
     */
    private void update(Map<String, Cellule> dictionnaire){
    	System.out.println("on est dans update");
    	ArrayList<String> aSupprimer, aAjouter;
    	aSupprimer = new ArrayList<>();
    	aAjouter = new ArrayList<>();
    	comparer(aSupprimer,aAjouter);

    	for(String cle : aSupprimer){
    		System.out.println("On supprime de choses");
    		dictionnaire.get(cle).enlever(this.cle);
    	}

    	for(String cle : aAjouter){
    		System.out.println("On ajoute de choses");
    		dictionnaire.get(cle).ajouter(this.cle, dictionnaire);
    	}
    	isReferenceCirculaire(dictionnaire, this.cle);
    }
}