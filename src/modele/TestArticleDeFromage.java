package modele;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArticleDeFromage {
	private Articles articles;
	
	@Before
	public void setUp() throws Exception{
		this.articles = GenerationFromages.générationBaseFromages();
	}
	@After
	public void tearDown() throws Exception{
		this.articles = null;
	}
	@Test
	public void testRecupererListeArticleFromage() {
		List<Article> a = new LinkedList<Article>();
		a.add(new Article(articles.getLesFromages().get(0), "250 g", 8.2F));
		a.add(new Article(articles.getLesFromages().get(0), "500 g", 16.4F));
		a.add(new Article(articles.getLesFromages().get(0), "1 kg", 32.8F));
		assertEquals(articles.getLesFromages().get(0).getArticles().toString(), a.toString());
		assertArrayEquals(articles.getLesFromages().get(0).getArticles().toArray(), a.toArray());
	}
	
}
