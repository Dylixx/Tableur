package fr.iutfbleau.SAE;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>NoeudDouble</code> modifie les classes de noeud afin de calculer l'arbre de notation préfixée
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class NoeudDouble extends Noeud {
	/*
	* valeur du double du noeud sous forme de String, au cas où il y une erreur et non un double
	*/
	private String valeur;

	/*
	* dictionnaire stockant les cellules du tableur
	*/
	private Map<String, Cellule> dictionnaire;

	/**
     * Constructeur du noeud double
     * 
     * @param s le double sous forme de String du noeud
     * @param dictionnaire dictionnaire stockant les cellules du tableur
     */
	public NoeudDouble(String s, Map<String, Cellule> dictionnaire){
		this.valeur = s;
		this.dictionnaire = dictionnaire;
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
     * @return la valeur en String du noeud
     */
	@Override 
	public String calcul(){
		return this.valeur;
	}


}