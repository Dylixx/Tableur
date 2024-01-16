package fr.iutfbleau.SAE;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>Fenetre</code> représente la fenêtre principale de l'interface utilisateur pour un tableur.
 * Elle hérite de <code>JFrame</code> et contient des méthodes pour initialiser et gérer les éléments de l'interface graphique.
 *  
 * @version 1.1
 * @author Oscar Williatte et Edouard Schnur et Hassan Meite
 */
public class Fenetre extends JFrame {
	/*
	* panel contenant la grille du tableur
	*/
	private JPanel panelTableur;

    /*
	* panel contenant le JTextfield pour écire dans les cellules
	*/
	private JPanel panelText;

    /*
	* dictionnaire permmettant de stocker les cellules
	*/
	private Map<String, Cellule> grille = new HashMap<>();

    /*
	* lettres pour créer la grille
	*/
	private static final String[] lettres = {"A","B","C","D","E","F","G","H","I"};

    /*
	* JTextField qui servira à écrire dans les cellules
	*/
	private ZoneTexte text;

    /*
	* Tableau d'une ancienne cellule permettant de modifier le contenu de JTextField et la couleurs des cellules lors des cliques
	*/
	private Cellule[] ancienJLabel = new Cellule[]{null};

    /**
 	* La classe <code>Fenetre</code> représente la fenêtre principale de l'interface utilisateur pour un tableur.
 	* Elle hérite de <code>JFrame</code> et contient des méthodes pour initialiser et gérer les éléments de l'interface graphique.
 	*/
	public Fenetre () { 
		this.text = new ZoneTexte(this.grille);
		this.panelText = new JPanel();
		this.panelTableur = new JPanel();
		this.panelTableur.setLayout(new GridBagLayout());

		KeyCellule key = new KeyCellule(this.text);
		this.text.addKeyListener(key);
		
		for(int y=0;y<10;y++){
			for(int x=0;x<10;x++){			
				GridBagConstraints contrainte=new GridBagConstraints();
				JPanel p = new JPanel();
				
		//séléctionne la case haut gauche qui doit etre vide
				if(x==0 && y==0){
					creerPanelVide(y,x,p,contrainte);
				}else{
					Border b = BorderFactory.createLineBorder(Color.GRAY,2);
					p.setBorder(b);

		    //permet de séléctionner les cases avec les chiffres et lettres
					if(x==0 | y==0){
						creerPanelInfo(y,x,p,contrainte);
					}else{
						String cle = "" + this.lettres[x-1] + y;

						this.grille.put(cle, creerPanelCellule(y,x,p,contrainte,cle));					 
					}
				}

			}
		}

		this.add(panelText, BorderLayout.NORTH);
		this.panelText.setLayout(new GridLayout(1,1));
		this.panelText.add(text);
		this.add(panelTableur, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920,1080);   	
	}

	/**
     * Crée un panneau vide dans le tableau.
     *
     * @param y Position Y du panneau dans la grille.
     * @param x Position X du panneau dans la grille.
     * @param p Le panneau à ajouter.
     * @param contrainte Les contraintes de disposition pour le panneau.
     */
	private void creerPanelVide(int y, int x, JPanel p, GridBagConstraints contrainte){
		contrainte.gridx=x;
		contrainte.gridy=y;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.fill=GridBagConstraints.BOTH;
		contrainte.anchor=GridBagConstraints.SOUTH;
		contrainte.insets=new Insets(0,0,0,0);
		contrainte.weightx=0.2f;
		contrainte.weighty=0.2f;
		this.panelTableur.add(p,contrainte); 
	}

	/**
     * Crée un panneau d'information (lettres ou chiffres) dans le tableau.
     *
     * @param y Position Y du panneau dans la grille.
     * @param x Position X du panneau dans la grille.
     * @param p Le panneau à ajouter.
     * @param contrainte Les contraintes de disposition pour le panneau.
     */
	private void creerPanelInfo(int y, int x, JPanel p, GridBagConstraints contrainte){
		p.setLayout(new GridBagLayout());

		contrainte.gridx=x;
		contrainte.gridy=y;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.fill=GridBagConstraints.BOTH;
		contrainte.anchor=GridBagConstraints.SOUTH;
		contrainte.insets=new Insets(0,0,0,0);
		contrainte.weightx=0.2f;
		contrainte.weighty=0.2f;
		p.setBackground(new Color(220,220,220));
		this.panelTableur.add(p,contrainte); 

		JLabel l = new JLabel();
		Font customFont = new Font("Arial", Font.PLAIN, 20);
		l.setFont(customFont);
		if(x==0){
			l.setText(""+y);
		}

		if(y==0){
			l.setText(this.lettres[x-1]);
		}
		p.add(l);

	}

	/**
     * Crée un panneau de cellule dans le tableau.
     *
     * @param y Position Y du panneau dans la grille.
     * @param x Position X du panneau dans la grille.
     * @param p Le panneau à ajouter.
     * @param contrainte Les contraintes de disposition pour le panneau.
     * @param cle La clé identifiant la cellule.
     * @return L'objet Cellule créé.
     */
	private Cellule creerPanelCellule(int y, int x, JPanel p, GridBagConstraints contrainte,String cle){
		Cellule c = new Cellule(cle);
	//contraintes du JPanel principal
		contrainte.gridx=x;
		contrainte.gridy=y;
		contrainte.gridwidth=1;
		contrainte.gridheight=1;
		contrainte.fill=GridBagConstraints.BOTH;
		contrainte.anchor=GridBagConstraints.SOUTH;
		contrainte.insets=new Insets(0,0,0,0);
		contrainte.weightx=0.2f;
		contrainte.weighty=0.2f;

	//contrainte pour les JPanel des cellules
		GridBagConstraints contrainteCellule = new GridBagConstraints();
		contrainteCellule.gridx = 0;
		contrainteCellule.gridy = 0;
		contrainteCellule.gridwidth=1;
		contrainteCellule.gridheight=1;
		contrainteCellule.fill = GridBagConstraints.BOTH; 
		contrainteCellule.insets = new Insets(0, 0, 0, 0); 
		contrainteCellule.weightx=1.0f;
		contrainteCellule.weighty=1.0f;

		
		MouseCellule mouse = new MouseCellule(c,this.text,this.ancienJLabel,p);
		c.addMouseListener(mouse);	       
		c.setOpaque(true);
	/*c.addFocusListener(new ControlerCellule(c, this.text));
	  c.setFocusable(true);*/

	  p.setLayout(new GridLayout(1,1));
	  p.add(c, contrainteCellule);
	  p.setBackground(new Color(250,250,250));
	  this.panelTableur.add(p,contrainte);

	//p.addMouseListener(mouse);

	  return c;
	}
}
