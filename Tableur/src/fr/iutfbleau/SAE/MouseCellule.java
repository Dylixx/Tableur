package fr.iutfbleau.SAE;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.border.Border;

/**
 * La classe <code>MouseCellule</code> qui permet de séléctionner les cellules avec la souris
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class MouseCellule implements MouseListener {
    /*
    * La cellule avec laquelle elle communique
    */
    private Cellule cellule;

    /*
    * Le JTextField qui sera modifié lors des changement de cellules
    */
    private ZoneTexte text;

    /*
    * L'ancienne cellule dont la couleur va être modifié
    */
    private Cellule[] ancienJLabel;

    /*
    * le panel de la cellule dans laquelle on se trouve
    */
    private JPanel panel;

     /**
     * Constructeur de la classe MouseCellule.
     *
     * @param cellule La cellule sur laquelle le listener de souris est appliqué.
     * @param text Le champ de texte associé pour l'affichage et la modification de la formule de la cellule.
     * @param ancienJLabel Un tableau contenant la dernière cellule sélectionnée, permettant de gérer le changement de focus.
     * @param p Le panneau contenant la cellule.
     */
    public MouseCellule(Cellule cellule,ZoneTexte text, Cellule[] ancienJLabel, JPanel p) {  	
        this.cellule = cellule;
        this.text = text;
        this.ancienJLabel = ancienJLabel;
        this.panel = p;
    }

    /**
     * Gère les événements de clic de souris sur la cellule.
     * Modifie l'affichage de la cellule sélectionnée et met à jour le champ de texte avec la formule de la cellule.
     *
     * @param e L'événement de souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
	this.text.setCelluleSelected(this.cellule);
    	if(this.ancienJLabel[0]!=null){    //Modifie la couleur de la cellule précedemment séléctionnée
	    this.ancienJLabel[0].setBackground(this.ancienJLabel[0].getColor());
            //this.ancienJLabel[0].actualisation(this.text.getText());
            //this.ancienJLabel[0].setText(this.ancienJLabel[0].getResultat());
    	}   
    	System.out.println("cliqué");
        System.out.println(this.cellule.getValeurCle());
        this.cellule.setBackground(Color.GREEN);
        this.text.setText(this.cellule.getFormule());

        this.ancienJLabel[0]=this.cellule;
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    /**
     * Gère les cellules survolé par la souris
     *
     * @param e L'événement de souris.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        Border b = BorderFactory.createLineBorder(Color.BLACK,2);
        this.panel.setBorder(b);
    }

    /**
     * Gère les cellules qui viennent d'être quitté par le survol de la souris
     *
     * @param e L'événement de souris.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        Border b = BorderFactory.createLineBorder(Color.GRAY,2);
        this.panel.setBorder(b);
    }
}
