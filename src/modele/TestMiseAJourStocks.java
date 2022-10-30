package modele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMiseAJourStocks {
	
	private Panier panier;
	private Articles articles;
	@Before
	public void setUp() throws Exception{
		this.articles = GenerationFromages.générationBaseFromages();
		this.panier = new Panier();
	}
	@After
	public void tearDown() throws Exception{
		this.articles = null;
		this.panier = null;
	}
	@Test
	public void testAjouteArticle() {
		Article a = articles.getArticle("Brebis au Bleu", "250 g");
		int quantité = a.getQuantitéEnStock();
		this.panier.addArticle(a,2);
		int nouveauQuantité = a.getQuantitéEnStock();
		assertEquals(quantité - 2, nouveauQuantité);
	}
	@Test
	public void testViderPanier() {
		Article a = articles.getArticle("Brebis au Bleu", "250 g");
		int quantité = a.getQuantitéEnStock();
		this.panier.addArticle(a,5);
		this.panier.viderPanier();
		int nouveauQuantité = a.getQuantitéEnStock();
		assertEquals(quantité, nouveauQuantité);
	}
	@Test
	public void testValiderPanier() {
		Article a = articles.getArticle("Brebis au Bleu", "250 g");
		int quantité = a.getQuantitéEnStock();
		this.panier.addArticle(a,5);
		this.panier.validerPanier();
		int nouveauQuantité = a.getQuantitéEnStock();
		assertEquals(quantité - 5, nouveauQuantité);
	}
}
