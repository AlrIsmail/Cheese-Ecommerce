package modele;
import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGestionPanier {
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
	public void testAjoutPanier() {
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"),2);
		this.panier.getArticle().get(0).equals(articles.getArticle("Brebis au Bleu", "250 g"));
		assertEquals((int)this.panier.getQuantiteArticle().get(0), 2);
		assertEquals(this.panier.getArticle().size(), 1);
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "500 g"),2);
		assertEquals(this.panier.getArticle().size(), 2);
	}
	@Test
	public void testModificationPanier() {
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"),2);
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"),1);
		this.panier.getArticle().get(0).equals(articles.getArticle("Brebis au Bleu", "250 g"));
		assertEquals(this.panier.getArticle().size(), 1);
		assertEquals((int)this.panier.getQuantiteArticle().get(0), 3);
		
	}
	@Test
	public void testViderPanier() {
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"),2);
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"),1);
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "500 g"),2);
		this.panier.viderPanier();
		assertEquals(this.panier.getArticle().size(), 0);
		assertEquals(this.panier.getQuantiteArticle().size(), 0);
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAddArticleQuantitéZero() {
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"), 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAddArticleQuantitéNegative() {
		this.panier.addArticle(articles.getArticle("Brebis au Bleu", "250 g"), -1);
	}
	
}
