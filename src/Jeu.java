import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class Jeu {
	public static void main(String[] args) throws Exception {
		boolean stop, quitterJeu, saisieOK;
		int finPartie;
		String reponse, texte;
		Vector<Integer> v = new Vector<Integer>();

		
		//pour gérer le jeu
		GestionJeu jeu = new GestionJeu();
		quitterJeu = false;
		do
		{	//initialisation des points
			jeu.Init();
			
			finPartie = 0;
			stop = false;
			do
			{ //saisie le  nombre du joueur parmi ceux disponibles 
				do
				{  //le joueur donne un chiffre compris entre 1 et 9 parmi ceux encore disponibles
				
					System.out.println("Votre nombre : ");
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					texte = in.readLine();
					jeu.set_nbJoueur(Integer.parseInt(texte));
					if ((jeu.get_nbJoueur()>0) && (jeu.get_nbJoueur()<=9))
						saisieOK = jeu.EstChiffreDispo(jeu.get_nbJoueur());
					else if (jeu.get_nbJoueur() ==0)
						saisieOK = true ;
					else
						saisieOK = false;
							
				}while (!saisieOK);
				
				if (jeu.get_nbJoueur() == 0)
					//le joueur a donné 0 : il veut donc arrêter le jeu
					stop = true;
				else
				{ 
					//le chiffre est validé, il est affecté au joueur 1
					jeu.ChiffrePris(1, jeu.get_nbJoueur());
						
					v.clear();
					finPartie = jeu.FinJeu(v);
					if (finPartie==0)
					{
						//choix aléatoire du nombre de l'ordinateur 
						jeu.set_nbOrdi(jeu.ChoixOrdinateur());
						System.out.println("nbord = " + jeu.get_nbOrdi());				
						jeu.ChiffrePris(2, jeu.get_nbOrdi());
						v.clear();
						finPartie = jeu.FinJeu(v);
						
					}
					
				}
			}	
			while ((finPartie==0) && (!stop));

			//le jeu s'arrête: affichage du résultat
			if (! stop) 
				if (finPartie == 3)
					System.out.println("Egalité : il n'y a plus de chiffres de disponibles");
				else if (finPartie == 1)
					System.out.println("Bravo, vous avez trouvé en premier 3 chiffres dont la somme est 15 : " + v);
				else 
					System.out.println("L'ordinateur a trouvé avant vous 3 chiffres dont la somme est 15 : " + v);
			else 
				System.out.println("Vous avez choisi d'interrompre la partie.");
		    
			//on demande au joueur s'il veut faire une autre partie
			System.out.println("Voulez-vous rejouer (o pour oui)?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			reponse = in.readLine();
		    quitterJeu = (reponse.compareTo("o") != 0);
		}
		while (!quitterJeu);
	}
	
	

}
