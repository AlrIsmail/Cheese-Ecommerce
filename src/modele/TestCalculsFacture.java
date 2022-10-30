package modele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCalculsFacture {

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
	public void testPanierSimple() {
		this.panier.addArticle(this.articles.getArticle("Ossau Iraty", "500 g"),3);
		this.panier.setLivraison(2);
		assertEquals(this.panier.calculPrixTotalArticle(this.panier.getArticle().get(0)),50.97F,0.001);
		assertEquals(this.panier.calculSoustotalTTC(),50.97F,0.001);
		assertEquals(this.panier.FraisDePort(), 4.9F, 0);
		assertEquals(this.panier.calculTotalTTC(), 55.87F, 0.01);
	}
	@Test
	public void testPanierPlusieursArticles() {
		this.panier.addArticle(this.articles.getArticle("Ossau Iraty", "500 g"),3);
		this.panier.addArticle(this.articles.getArticle("Brillat Savarin", "entier"),1);
		assertEquals(this.panier.calculPrixTotalArticle(this.panier.getArticle().get(0)),50.97F,0.001);
		assertEquals(this.panier.calculPrixTotalArticle(this.panier.getArticle().get(1)),14.5F,0.001);
		assertEquals(this.panier.calculSoustotalTTC(),65.47F,0.001);
		assertEquals(this.panier.FraisDePort(), 4.9F, 0);
		assertEquals(this.panier.calculTotalTTC(), 70.37F, 0.001);
	}
	@Test
	public void testPanierLivrasionGratuit() {
		this.panier.addArticle(this.articles.getArticle("Ossau Iraty", "500 g"),3);
		this.panier.addArticle(this.articles.getArticle("Brillat Savarin", "entier"),1);
		this.panier.addArticle(this.articles.getArticle("Fondue Savoyarde", "pour 10 personnes"),2);
		assertEquals(this.panier.calculSoustotalTTC(),200.47F,0.001);
		assertEquals(this.panier.FraisDePort(), 0.00F, 0);
		assertEquals(this.panier.calculTotalTTC(), 200.47F, 0.001);
	}

}
