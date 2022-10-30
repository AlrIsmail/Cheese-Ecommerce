package modele;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Commande {

	private LocalDateTime date;
	private Panier panier;
	private Client client;

	public Commande(Panier panier, Client client) {
		this.client=client;
		this.panier=panier;
		this.date= LocalDateTime.now();
	}

	public String toStringDate() {
		String dayFR="NULL";
		switch (this.date.getDayOfWeek()+" ") {
		case ("MONDAY "):
			dayFR=("lundi");
		break;
		case ("TUESDAY "):
			dayFR=("mardi");
		break;
		case ("WEDNESDAY "):
			dayFR=("mercredi");
		break;
		case ("THIRSDAY "):
			dayFR=("jeudi");
		break;
		case ("FRIDAY "):
			dayFR=("vendredi");
		break;
		case ("SATURDAY "):
			dayFR=("samedi");
		break;
		case ("SUNDAY "):
			dayFR=("dimanche");
		break;
		}
		
		String monthFR="NULL";
		switch (this.date.getMonth()+" ") {
		case ("JANUARY "):
			monthFR=("janvier");
		break;
		case ("FEBRUARY "):
			monthFR=("fevrier");
		break;
		case ("MARCH "):
			monthFR=("mars");
		break;
		case ("APRIL "):
			monthFR=("avril");
		break;
		case ("MAY "):
			monthFR=("mai");
		break;
		case ("JUNE "):
			monthFR=("juin");
		break;
		case ("JULY "):
			monthFR=("juillet");
		break;
		case ("AUGUST "):
			monthFR=("aout");
		break;
		case ("SEPTEMBER "):
			monthFR=("septembre");
		break;
		case ("OCTOBER "):
			monthFR=("octobre");
		break;
		case ("NOVEMBRE "):
			monthFR=("novembre");
		break;
		case ("DECEMBER "):
			monthFR=("decembre");
		break;
		}

		String LaDate = " "+dayFR+" "+this.date.getDayOfMonth()+" "+monthFR+" "+this.date.getYear();
		return LaDate;
	}
	public Panier getPanier() {
		return this.panier;
	}
	public Client getClient() {
		return this.client;

	}
	
	public String toStringDateEtHeure() {
		String Lheure = this.date.getHour()+":"+this.date.getMinute()+":"+this.date.getSecond()+" heure d'été Europe centrale";
		return this.toStringDate()+" à "+Lheure;
	}
	
	public String toStringPartRecap() {
		String res="";
		for(int i = 0; i < this.panier.taillePanier(); i++) {
			 res = res+(i+1)+": "+this.panier.getArticleIN(i)+"               quantité commandée :"+this.panier.getArticleQuatitéIn(i)+" prixTTC: "+this.panier.getArticleQuatitéIn(i)*this.panier.getArticlePrixInt(i)+" € \n";
		}
		return res;
	}
	
	public String toStringRecap() {
		float f1= this.panier.calculSoustotalTTC();
		float f2= this.panier.calculTotalTTC();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String s1 = df.format(f1);
		String s2 = df.format(f2);
		return ("RECAP. COMMANDE : \n"
				+ "\n"+this.toStringPartRecap()+"\n"
				+"TOTAL TTC COMMANDE:        "+s1+"\n"
				+this.toStringLivraison()+"\n"
				+"PRIX TOTAL TTC:            "+s2);
	}
	
	public String toStringLivraison() {
		if( this.panier.FraisDePort()==0) {
			return "TRANSPORT OFFERT";
		}
		return "TRANSPORT                  "+this.panier.getLivraison();    
	}

	public String toStringFacture() {
		return ("\t\t  Fromagerie CHYZZ pour vous servir et resservir en fromages\n\n"+
				"Toulouse le" + this.toStringDateEtHeure() + "\n\n" +
				this.client.toString() + "\n\n" +
				this.toStringRecap() + "\n\n" +
				"\t\t  Vous remerciant de votre confiance, la fromagerie CHYZZ");
	}
}

