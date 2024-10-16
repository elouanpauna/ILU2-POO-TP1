package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder();
		try {
		chaine.append("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
		} catch(NullPointerException e) {
			System.out.println("Etal Vide");
		}
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append(
					"il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		
		return chaine.toString();
	
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
			if (quantiteAcheter<0) {
				throw new IllegalArgumentException ("argument pas légal (négatif)");
			}
			if (etalOccupe==false) {
				throw new IllegalStateException("Etal Vide");
			}
			StringBuilder chaine = new StringBuilder();
			String nom= new String();
			try {
			nom =acheteur.getNom();
			} catch (NullPointerException e) {
				System.out.println("Acheteur Vide");
			}
			chaine.append(nom+ " veut acheter " + quantiteAcheter+ " " + produit + " à " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "+ nom + " vide l'étal de "+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + nom+ ", est ravi de tout trouver sur l'étal de "+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
