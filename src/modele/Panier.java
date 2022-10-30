package modele;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Panier {
	
	private List<Integer> quantiteArticles;
	private List<Article> articles;
	private TypeLivraison livraison;


	public Panier() {
		this.articles = new ArrayList<Article>();
		this.quantiteArticles = new ArrayList<Integer>();
		this.livraison = TypeLivraison.COLISSIMO_POINT_RELAIS;
	}

	public List<Article> getArticle() {
		return this.articles;
	}
	
	public String getArticleIN(int ligne) {
		return this.articles.get(ligne).toString();
	}
	public float getArticlePrixInt(int ligne) {
		return this.articles.get(ligne).getPrixTTC();
	}
	public int getArticleQuatitéIn(int ligne) {
		return this.quantiteArticles.get(ligne);
	}
	
	public int taillePanier() {
		return this.articles.size();
	}
	public boolean estVide() {
		return this.taillePanier() == 0;
	}
	public List<Integer> getQuantiteArticle() {
		return this.quantiteArticles;
	}

	public void addArticle(Article articl, int quantite) throws IllegalArgumentException{
        if(quantite<1) {
            throw new IllegalArgumentException("On peut pas rajouter 0 au panier");
        }
        if(articl.getQuantitéEnStock()==0) {
            throw new IllegalArgumentException("Cette article n'est plus en stock");
        }
        if(quantite>articl.getQuantitéEnStock()) {
            quantite=articl.getQuantitéEnStock();
        }
        if (this.articleDejaExistant(articl)) {
            modifierQuantiteArticle(articl, quantite);
        }else {
            this.articles.add(articl);
            this.quantiteArticles.add(quantite);
        }
        articl.préempterQuantité(quantite);
    }

	private Boolean articleDejaExistant(Article articl) {

		for (int i = 0; i < this.articles.size(); i++) {
			if (this.articles.contains(articl)) {
				return true;
			}
		}
		return false;
	}
	private void modifierQuantiteArticle(Article articl, int newQuantite) {
		int y=0;
		for (int i = 0; i < this.articles.size(); i++) {
			if (this.articles.contains(articl)) {
				y=i;	
			}
		}
		this.quantiteArticles.set(y, newQuantite + this.quantiteArticles.get(y));

	}
	public float calculPrixTotalArticle(Article articl) throws IllegalArgumentException {
		if(!this.articleDejaExistant(articl)) {
			throw new IllegalArgumentException();
		}
		int cmp = 0;
		float res = 0;
		for (Article a : this.articles) {
			if(a.equals(articl)) {
				res = (a.getPrixTTC()*this.quantiteArticles.get(cmp));
			}
			cmp++;
		}
		return res;
	}
	public float calculSoustotalTTC() {
		float tot=0;
		for (int i = 0; i < this.articles.size(); i++) {
			tot=tot+this.articles.get(i).getPrixTTC()*this.quantiteArticles.get(i);
		}
		return tot;
	}
	public float calculTotalTTC() {
		return this.calculSoustotalTTC()+this.FraisDePort();
	}
	public TypeLivraison getLivraison() {
		return this.livraison;
	}
	
	public float FraisDePort() {
		if(this.calculSoustotalTTC()>100) {
			return 0.00F;
		}
		return this.livraison.getCoûtLivrasion();
	}
	
	
	public void setLivraison(int choix) throws IllegalArgumentException {
		if( choix < 1 || choix > 3) {
			throw new IllegalArgumentException("Choix de livraison est soit 1 soit 2 soit 3");
		}
		if(choix==1) {
			this.livraison = TypeLivraison.COLISSIMO_POINT_RELAIS;
		}else if(choix==2) {
			this.livraison = TypeLivraison.COLISSIMO_DOMICILE;
		}else{
			this.livraison = TypeLivraison.CHRONOPOST;
		}		
	}
	
	public List<String[]> toStringPanier() {
		List<String[]> res = new LinkedList<String[]>();
		for(int i = 0; i < this.articles.size(); i++) {
			float f= this.calculPrixTotalArticle(this.articles.get(i));
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			String s = df.format(f);
			res.add(new String[]{this.articles.get(i).getFromage().getDésignation(),String.valueOf(this.articles.get(i).getPrixTTC()).replace('.', ','),
					this.quantiteArticles.get(i).toString(), s.replace('.', ',')});
		}
		return res;
	}
	
	public void viderPanier() {
		int cmp = 0;
		for (Article a : this.articles) {
			a.rendreQuantité(this.quantiteArticles.get(cmp));
			cmp++;
		}
		this.articles.clear();
		this.quantiteArticles.clear();
	}
	public void validerPanier() {
		this.articles.clear();
		this.quantiteArticles.clear();
	}

}
