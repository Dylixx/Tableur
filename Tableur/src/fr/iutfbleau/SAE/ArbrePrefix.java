package fr.iutfbleau.SAE;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
    * La classe <code>ArbrePrefix</code> permet de construire l'abre de calcul
    *  
    * @version 1.1
    * @author Oscar Williatte et Edouard Schnur et Hassan Meite
    */
public class ArbrePrefix {
	private NoeudSigne racine=null;
	private Map<String, Cellule> dictionnaire;

	public ArbrePrefix(Map<String, Cellule> dictionnaire){
		this.dictionnaire = dictionnaire;
	}

	public boolean addValeur(String s){	
		if(racine == null){
			this.racine = new NoeudSigne(s, this.dictionnaire);	
			return true;    
		}else{
			if(racine.addValeur(s)){
				return true;
			}
		}
		return false;
	}

	public String calculArbre(){
		return this.racine.calcul();
	}
}