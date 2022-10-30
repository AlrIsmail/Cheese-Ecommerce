package modele;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFlitrageFromage {
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
	public void FiltreTypeLaitVache() {
		List<Fromage> fromages = this.articles.fromagesAuLaitDe(TypeLait.VACHE);
		for (Fromage f : fromages) {
			assertEquals(f.getTypeFromage(), TypeLait.VACHE);
		}
		assertEquals(fromages.size(),this.articles.getLesFromages().size() - 22 - 16);
	}
	@Test
	public void FiltreTypeLaitChevre() {
		List<Fromage> fromages = this.articles.fromagesAuLaitDe(TypeLait.CHEVRE);
		for (Fromage f : fromages) {
			assertEquals(f.getTypeFromage(), TypeLait.CHEVRE);
		}
		assertEquals(fromages.size(),22);
	}
	@Test
	public void FiltreTypeLaitBrebis() {
		List<Fromage> fromages = this.articles.fromagesAuLaitDe(TypeLait.BREBIS);
		for (Fromage f : fromages) {
			assertEquals(f.getTypeFromage(), TypeLait.BREBIS);
		}
		assertEquals(fromages.size(),16);
	}

}
