package fr.iutfbleau.SAE;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>KeyCellule</code> permet d'écouter les inputs du clavier afin d'intéragir avec les cellules
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class KeyCellule implements KeyListener {
	/*
	* Le JTextField avec lequel KeyListener communique pour comuniquer avec les cellules
	*/
    private ZoneTexte text;

   	/**
     * Constructeur de la classe KeyCellule
     * 
     * @param t le JTextField avec lequel il communique
     */
    public KeyCellule(ZoneTexte t){
       	this.text = t;       
    }

    /**
     * méthode qui écoute les clique qui sont écris dans le JTextField
     * 
     * @param e l'evenement qui permettra de savoir si le clique est la touche que l'on veut
     */
    @Override
    public void keyTyped(KeyEvent e){
	if (e.getKeyCode()==44){
	    System.out.println("test");	   
	}
    }

    /**
     * méthode qui écoute les clique qui sont écris dans le JTextField
     * 
     * @param e l'evenement qui permettra de savoir si le clique est la touche que l'on veut
     */
    @Override
    public void keyPressed(KeyEvent e){
	if(this.text.getCelluleSelected() != null){

	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
		System.out.println("boulot");
		this.text.getCelluleSelected().actualisation(this.text.getText(),this.text.getDictionnaire());
		this.text.getCelluleSelected().setText(this.text.getCelluleSelected().getResultat());
	    }
	}
    }

    @Override
    public void keyReleased(KeyEvent e){
    }

    
}
