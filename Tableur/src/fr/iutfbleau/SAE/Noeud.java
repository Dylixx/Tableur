package fr.iutfbleau.SAE;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>Noeud</code> classe abstrait permettant de créer la base des noeud de l'arbre
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public abstract class Noeud {
    /*
    * permet de donner un signe au noeud
    */
    protected String etat;

    /**
     * Méthode abstraite pour ajouter une valeur au nœud.
     *
     * @param s La chaîne de caractères représentant la valeur à ajouter.
     * @return true si la valeur a été ajoutée avec succès, false sinon.
     */
    public abstract boolean addValeur(String s);

    /**
     * Méthode abstraite pour calculer la valeur du nœud.
     *
     * @return La chaîne de caractères représentant le résultat du calcul.
     */
    public abstract String calcul();

    /**
     * Méthode abstraite pour déterminer si le nœud est une feuille.
     *
     * @return true si le nœud est une feuille, false sinon.
     */
    public abstract boolean isFeuille();

    /**
     * Vérifie si la chaîne donnée est une référence
     *
     * @param s La chaîne à vérifier.
     * @return true si la chaîne est une référence valide, false sinon.
     */
    public boolean isReference(String s){
        String regex = "[A-I][1-9]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches()){
            this.etat = "reference";
            return true;
        }
        return false;
    }

    /**
     * Vérifie si la chaîne donnée est un nombre double valide.
     *
     * @param s La chaîne à vérifier.
     * @return true si la chaîne est un double valide, false sinon.
     */
    public boolean isDouble(String s){
        try {
            Double.parseDouble(s);
            this.etat = "double";
            return true;
        } catch (NumberFormatException e) {
         return false; 
     }
     }

     /**
     * Vérifie si la chaîne donnée est un signe d'opération mathématique valide (+, -, *, /).
     *
     * @param s La chaîne à vérifier.
     * @return true si la chaîne est un signe valide, false sinon.
     */
     public boolean isSigne(String s){
        if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")){
            this.etat = "signe";
            return true;
        }  

        return false;
    }
}