package fr.iutfbleau.SAE;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>NoeudReference</code> noeud stockant une référence dans l'arbre
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class NoeudReference extends Noeud {
	/*
	* cellule stocké dans le noeud
	*/
	private Cellule cellule;

	/*
	* dictionnaire stockant la valeurs des cellules du tableurs afin d'obtenir la valeur de la clé de la référence
	*/
	private Map<String, Cellule> dictionnaire;

	/*
	* le cle dans le noeud
	*/
	private String s;

	/**
     * contructeur du noeud Référence
     * 
     * @param s la clé de la cellule
     * @param dictionnaire stockant la valeur des cellules du tableur
     */
	public NoeudReference(String s, Map<String, Cellule> dictionnaire){		
		this.dictionnaire = dictionnaire;
		this.cellule = this.dictionnaire.get(s);
	}

	/**
     * méthode qui ajoute une valeur à une feuille
     * 
     * @param s la valeur sous forme de string à ajouter
     * @return false car une feuille est toujours sans fils
     */
	@Override
	public boolean addValeur(String s){
		return false;
	}

	/**
     * méthode qui demande si le noeud est une feuille
     * 
     * @return true car ce noeud est toujours une feuille
     */
	@Override
	public boolean isFeuille(){
		return true;
	}

	/**
     * méthode qui demande la valeur du noeud
     * 
     * @return la valeur en String dans la cellule stockée par le noeud
     */
	@Override
	public String calcul(){
		System.out.println(this.cellule.getValeur());
		return this.cellule.getValeur();
	}
}