package modele;

public class Client {
	private String nom;
	private String prénom;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String téléphone;
	private String mail;
	
	public Client(String nom, String prénom, String adresse1,
			String codePostal, String ville, String téléphone, String mail) {
		this.nom = nom;
		this.prénom = prénom;
		this.adresse1 = adresse1;
		this.adresse2 = null;
		this.codePostal = codePostal;
		this.ville = ville;
		this.téléphone = téléphone;
		this.mail = mail;
	}
	public Client(String nom, String prénom, String adresse1, String adresse2,
			String codePostal, String ville, String téléphone, String mail) {
		this.nom = nom;
		this.prénom = prénom;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.téléphone = téléphone;
		this.mail = mail;
	}
	public String toString() {
		if (this.adresse2.equals("") ) {
			return("INFORMATIONS CLIENT : " + "\t" +  this.nom + " " + this.prénom + "\n\t\t\t\t" + this.adresse1 +
					"\n\t\t\t\t" + this.codePostal + " " + this.ville + "\n\t\t\t\t"+
					"téléphone : \t"+ this.téléphone + "\n\t\t\t\t" + "mèl : \t\t" + this.mail);
		}
		else {
			return("INFORMATIONS CLIENT : " + "\t" +  this.nom + " " + this.prénom + "\n\t\t\t\t" + this.adresse1 +
					"\n\t\t\t\t" + this.adresse2 +
					"\n\t\t\t\t" + this.codePostal + " " + this.ville + "\n\t\t\t\t"+
					"téléphone : \t"+ this.téléphone + "\n\t\t\t\t" + "mèl : \t\t" + this.mail);
		}
	}
	
}
