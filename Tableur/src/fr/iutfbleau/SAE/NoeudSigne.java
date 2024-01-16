package fr.iutfbleau.SAE;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>NoeudSigne</code> représente un nœud de signe dans un arbre d'expression.
 * Elle hérite de la classe <code>Noeud</code> et est utilisée pour construire et évaluer des expressions mathématiques.
 * 
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class NoeudSigne extends Noeud {
	/*
	* attribut détérminant le noeud gauche du noeudSigne
	*/
	private Noeud droite=null;

	/*
	* attribut détérminant le noeud droit du noeudSigne
	*/
	private Noeud gauche=null;

	/*
	* attribut représentant le signe de cacul du noeudSigne
	*/
	private String signe;

	/*
	* attribut possédant l'accès à toutes les cellules
	*/
	private Map<String, Cellule> dictionnaire;


	/**
     * Constructeur de la classe NoeudSigne.
     *
     * @param s            Le signe de l'opération (+, -, *, /).
     * @param dictionnaire Le dictionnaire associant des chaînes de caractères à des cellules.
     */
	public NoeudSigne(String s, Map<String, Cellule> dictionnaire){
		this.signe = s;
		this.dictionnaire = dictionnaire;
	}


    /**
     * Méthode pour déterminer si le noeud est une feuille de l'arbre.
     *
     * @return false car un NoeudSigne n'est jamais une feuille.
     */
	@Override
	public boolean isFeuille(){
		return false;
	}

	/**
     * Ajoute une valeur au nœud actuel ou à l'un de ses enfants.
     *
     * @param s La chaîne de caractères représentant la valeur à ajouter.
     * @return true si la valeur a été ajoutée avec succès, false sinon.
     */
	@Override
	public boolean addValeur(String s){
		if(isReference(s) || isSigne(s) || isDouble(s)){
			if(this.gauche==null){
				switch(this.etat){
				case "reference":
					this.gauche=new NoeudReference(s, this.dictionnaire);
					break;

				case "signe":
					this.gauche=new NoeudSigne(s, this.dictionnaire);
					break;

				case "double":
					this.gauche=new NoeudDouble(s, this.dictionnaire);
					break;
				}
				return true;

			}else if(this.droite==null){
				switch(this.etat){
				case "reference":
					this.droite=new NoeudReference(s, this.dictionnaire);
					break;

				case "signe":
					this.droite=new NoeudSigne(s, this.dictionnaire);
					break;

				case "double":
					this.droite=new NoeudDouble(s, this.dictionnaire);
				}
				return true;
			}

			if(this.gauche.addValeur(s)){
				return true;
			}

			if(this.droite.addValeur(s)){
				return true;
			}
		}
		return false;
	}

	/**
     * Calcule la valeur de l'expression représentée par ce nœud et ses enfants.
     *
     * @return La chaîne de caractères représentant le résultat de l'expression.
     *         Peut retourner "incomplet", "div par 0", "incalculable", ou "erreur" si l'expression ne peut pas être évaluée.
     */
	@Override
	public String calcul(){
		double gauche, droite;

		if(this.droite==null || this.gauche==null){
			return "incomplet";
		}

		if(this.gauche.isFeuille()){
			try{
				gauche = Double.parseDouble(this.gauche.calcul());
			}catch(NullPointerException e){
				return "incalculable";
			}
		}else{
			String s = this.gauche.calcul();
			if(s.equals("incomplet")){
				return s;
			}

			if(s.equals("div par 0")){
				System.out.println("DIV PAR 0");
				return s;
			}

			if(s.equals("incalculable")){
				return s;
			}
			gauche = Double.parseDouble(s);
		}


		if(this.droite.isFeuille()){
			try{
				droite = Double.parseDouble(this.droite.calcul());
			}catch(NullPointerException e){
				return "incalculable";
			}
		}else{
			String s = this.droite.calcul();
			if(s.equals("incomplet")){
				return s;
			}

			if(s.equals("div par 0")){
				return s;
			}

			if(s.equals("incalculable")){
				return s;
			}
			droite = Double.parseDouble(s);
		}

		switch (this.signe) {
		case "+":
			return Double.toString(gauche + droite);
			
		case "-":		
			return Double.toString(gauche - droite);

		case "*":
			return Double.toString(gauche * droite);
			
		case "/":
			if(droite == 0.0){
				System.out.println("div par 0 ici");
				return "div par 0";
			}
			return Double.toString(gauche / droite);
		}
		return "erreur";
	}
}
