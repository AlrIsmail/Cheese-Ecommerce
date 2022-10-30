package modele;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArticleDeFromage.class, TestCalculsFacture.class, TestFlitrageFromage.class,
	TestGestionPanier.class, TestMiseAJourStocks.class, TestSaisieFromage.class })
public class AllTests {

}

