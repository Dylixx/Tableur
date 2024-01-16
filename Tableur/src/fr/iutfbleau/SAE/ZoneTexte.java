package fr.iutfbleau.SAE;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>ZoneTexte</code> est le JTextField dans lequel on écrira pour modifier une cellule.
 * Elle hérite de la classe <code>JTextField</code>.
 * 
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */


public class ZoneTexte extends JTextField {
    /*
    * cellule qui sera modifié par le contenu du JTextField
    */
    private Cellule celluleSelected = null;

    /*
    * dictionnaire stockant les cellules du tableurs afin de savoir à qui s'adresse le JTextField
    */
    private Map<String, Cellule> grille;
    
    /**
     * contructeur de ZoneTexte
     * 
     * @param grille permettant de stocker les cellules du tableur
     */
    public ZoneTexte(Map<String, Cellule> grille){
        this.grille = grille;
    }

    /**
     * méthode qui ajoute une valeur à une feuille
     * 
     * @param c permet de trouver quelle cellule est séléctionné selon la clé
     */
    public void setCelluleSelected(Cellule c){
	this.celluleSelected=c;
    }

    /**
     * méthode qui ajoute une valeur à une feuille
     * 
     * @return la cellule séléctionné par le JTextField
     */
    public Cellule getCelluleSelected(){
	return this.celluleSelected;
    }

    /**
     * méthode qui ajoute une valeur à une feuille
     * 
     * @return le dictionnaire stockant les cellules du tableur
     */
    public Map<String, Cellule> getDictionnaire(){
        return this.grille;
    }
}
