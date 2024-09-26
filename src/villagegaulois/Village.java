package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche[] marché;
	

	public Village(String nom, int nbVillageoisMaximum,int nbEtalsMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marché=new Marche[nbEtalsMarche];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	private static class Marche{
		private Etal[] etals;
		private int nbEtals;
		
		private Marche(int nbEtals) {
			this.nbEtals=nbEtals;
			etals=new Etal[nbEtals];
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for(int i=0;i<nbEtals;i++) {
				if  (!etals[i].isEtalOccupe()) {
					return i;
				}	
		}
		return -1;
		}
		
		private Etal[] trouverEtal(String produit) {
			Etal[] etalsProduit= new Etal[nbEtals];
			int p=0;
			for (int i=0;i<nbEtals;i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[p]=etals[i];
					p++;
				}
			}
			return etalsProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0;i<nbEtals;i++) {
				if (gaulois==etals[i].getVendeur()) {
				return etals[i];
			}
			}
			return null;
		}
		
		
		private String afficheMarche() {
			int nbEtalVide=nbEtals;
			for (int i=0;i<nbEtals;i++) {
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
					nbEtalVide--;
				}
			}
			return  "Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n";

		}
		
		public String installerVendeur(Gaulois vendeur,String produit,int nbProduit) {
			StringBuilder chaine=new StringBuilder();
			chaine.append(vendeur.getNom()+" cherche un endroit pour vendre "+produit+" "+nbProduit+".\n");
			int i =marché.trouverEtalLibre();
			marché.utiliserEtal(i,vendeur,produit,nbProduit);
			chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+i+".\n");
			return chaine.toString();
		}
		
		
		
	}
}