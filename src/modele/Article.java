package modele;


public class Article {

	private Fromage fromage;
	private String clé;
	private float prixTTC;
	private int quantitéEnStock;
	
	public Article(Fromage fromage, String clé, float prixTTC) {
		this.fromage = fromage;
		this.clé = clé;
		this.prixTTC = prixTTC;
		this.quantitéEnStock = 0;
	}
	
	public Fromage getFromage() {
		return this.fromage;
	}

	public float getPrixTTC() {
		return this.prixTTC;
	}
	
	public int getQuantitéEnStock() {
		return quantitéEnStock;
	}
	public boolean enRupture() {
		return this.quantitéEnStock == 0;
	}
	
	public String getClé() {
		return this.clé;
	}

	public void setQuantitéEnStock(int quantitéEnStock) {
		this.quantitéEnStock = quantitéEnStock;
	}
	
	public void préempterQuantité(int quantité) {
		this.setQuantitéEnStock(this.quantitéEnStock - quantité);
	}
	public void rendreQuantité(int quantité) {
		this.setQuantitéEnStock(this.quantitéEnStock + quantité);
	}

	public String toString() {
		if (clé.equals(""))
			return this.fromage.getDésignation() + ", Prix TTC : " + String.valueOf(this.getPrixTTC()).replace('.', ',') + " €";
		else
			return this.fromage.getDésignation() + ", " + this.clé + ", Prix TTC : " + String.valueOf(this.getPrixTTC()).replace('.', ',') + " €";
	}
	
	public String toStringIHM() {
		if (this.clé != "") {
			return (this.clé + ", Prix TTC : " + this.getPrixTTC() + " €");
		}else {
			return ("Prix TTC : " + this.getPrixTTC() + " €");
		}
	}
	public String toStringAvecStock() {
		return this.toString() + ", Quantité en stock : " + this.quantitéEnStock;
	}
	
	public boolean equals(Object obj) {
		Article other = (Article) obj;
		return (this.fromage.equals(other.fromage) && this.clé.equals(other.clé));
	}
	
}
