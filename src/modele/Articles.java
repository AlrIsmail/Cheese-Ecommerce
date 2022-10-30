package modele;


import java.util.LinkedList;
import java.util.List;

public class Articles {

	private List<Fromage> lesFromages;
	
	public Articles() {
		this.lesFromages = new LinkedList<Fromage>();
	}
	
	public void addFromages(List<Fromage> fromages) {
		this.lesFromages.addAll(fromages);
	}
	
	public String toStringFromagesEtArticles() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			enForme.append(f.toString() + '\n');
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toString() + '\n');
				}
			}
		}
		return enForme.toString();
	}
	
	public String toStringArticlesEtStock() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toStringAvecStock() + '\n');
				}
			}
		}
		return enForme.toString();
	}
	
	public List<Fromage> fromagesAuLaitDe(TypeLait lait){
		List<Fromage> lesFromagesDeType = new LinkedList<Fromage>();
		for (Fromage f : this.lesFromages ) {
			if(f.getTypeFromage() == lait) {
				lesFromagesDeType.add(f);
			}
		}
		return lesFromagesDeType;
	}
	
	public List<Fromage> getLesFromages(){
		return this.lesFromages;
	}
	
	public Fromage getFromage(String désignation) {
		for(Fromage f : this.lesFromages) {
			if(f.getDésignation().equals(désignation)) {
				return f;
			}
		}
		return null;
	}
	public void regénérationDuStock() {
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					article.setQuantitéEnStock((int) Math.round(Math.random()*100));
				}
			}
		}
	}
	
	public String vérificationSaisie( ) {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() == 0) {
				enForme.append("Pas d'articles pour " + f.toString() + '\n');
			}
		}
		return enForme.toString();
	}
	
	public Article getArticle(String désignation, String clé) {
		for (Fromage f : this.lesFromages) {
			if (f.getDésignation().equals(désignation)) {
				for (Article article : f.getArticles()) {
					if (article.getClé().equals(clé)) {
						return article;
					}
				}
			}
		}
		return null;
	}
	
}
