package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

import modele.Article;
import modele.Fromage;
import modele.Panier;

public class InfoFromage extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner spinnerQuantite;
	private Article choixArticle;
	private JButton BouttonAjouterAuPanier;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public InfoFromage(Fromage fromage, Panier panier) {
		setBounds(100, 100, 740, 466);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel ContentPanelSouth = new JPanel();
			getContentPane().add(ContentPanelSouth, BorderLayout.SOUTH);
			ContentPanelSouth.setLayout(new BorderLayout(0, 0));
			{
				BouttonAjouterAuPanier = new JButton("Ajouter au panier");
				BouttonAjouterAuPanier.setPreferredSize(new Dimension(250, 50));
				BouttonAjouterAuPanier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				BouttonAjouterAuPanier.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						SpinnerNumberModel modelSpinner = (SpinnerNumberModel) spinnerQuantite.getModel();
						panier.addArticle(choixArticle, (int)modelSpinner.getValue());
						dispose();
					}
				});
				BouttonAjouterAuPanier.setFont(new Font("Arial Black", Font.PLAIN, 20));
				ContentPanelSouth.add(BouttonAjouterAuPanier, BorderLayout.EAST);
			}
			{
				JPanel panel = new JPanel();
				ContentPanelSouth.add(panel);
				{
					JButton ButtonAnnuler = new JButton("Annuler");
					ButtonAnnuler.setPreferredSize(new Dimension(120, 50));
					ButtonAnnuler.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {

							dispose();
						}
					});
					ButtonAnnuler.setFont(new Font("Arial Black", Font.PLAIN, 20));
					ButtonAnnuler.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					panel.setLayout(new BorderLayout(0, 0));
					panel.add(ButtonAnnuler, BorderLayout.EAST);
				}
			}
			{
				JPanel ContentPanelNorth1 = new JPanel();
				ContentPanelSouth.add(ContentPanelNorth1, BorderLayout.NORTH);
				ContentPanelNorth1.setLayout(new BorderLayout(0, 0));
				{
					spinnerQuantite = new JSpinner();
					spinnerQuantite.setModel(new SpinnerNumberModel(0, 0, 0, 1));
					spinnerQuantite.setFont(new Font("Arial Black", Font.PLAIN, 30));
					ContentPanelNorth1.add(spinnerQuantite, BorderLayout.EAST);
					spinnerQuantite.setPreferredSize(new Dimension(100, 50));
				}
				{
					JComboBox<String> comboBoxPoidsPrix = new JComboBox<String>();
					comboBoxPoidsPrix.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String string = (String) comboBoxPoidsPrix.getSelectedItem();
							for(Article article:fromage.getArticles()) { 
								if (article.toStringIHM().equals(string)) {
									choixArticle = article;
									SpinnerNumberModel modelSpinner = (SpinnerNumberModel) spinnerQuantite.getModel();
									if(!article.enRupture()) {
										modelSpinner.setValue(1);
										BouttonAjouterAuPanier.setEnabled(true);
										modelSpinner.setMinimum(1);
									}else {
										modelSpinner.setMinimum(0);
										modelSpinner.setValue(0);
										BouttonAjouterAuPanier.setEnabled(false);

									}

									modelSpinner.setMaximum(article.getQuantitéEnStock());
								}
							}
						}
					});
					DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
					comboBoxPoidsPrix.setModel(model);
					for(Article articles:fromage.getArticles()) { 
						model.addElement(articles.toStringIHM());
					}
					comboBoxPoidsPrix.setFont(new Font("Arial Black", Font.PLAIN, 20));
					ContentPanelNorth1.add(comboBoxPoidsPrix, BorderLayout.CENTER);

				}
			}
		}
		{
			JPanel ContentPanelNorth2 = new JPanel();
			getContentPane().add(ContentPanelNorth2, BorderLayout.NORTH);
			ContentPanelNorth2.setLayout(new BorderLayout(0, 0));
			{
				JLabel LabelIconFromage = new JLabel("");
				LabelIconFromage.setIcon(new ImageIcon(InfoFromage.class.getResource("/Photo/fromage2 (1) (1).jpg")));
				ContentPanelNorth2.add(LabelIconFromage, BorderLayout.WEST);
			}
		}
		{
			JPanel ContentPanelCenter = new JPanel();
			getContentPane().add(ContentPanelCenter, BorderLayout.CENTER);
			ContentPanelCenter.setLayout(new BorderLayout(0, 0));
			{
				JTextArea textAreaInfoFromage = new JTextArea();
				textAreaInfoFromage.setEditable(false);
				textAreaInfoFromage.setFont(new Font("Arial Black", Font.PLAIN, 15));
				textAreaInfoFromage.setColumns(10);
				textAreaInfoFromage.setRows(10);
				ContentPanelCenter.add(textAreaInfoFromage);
				textAreaInfoFromage.setText(fromage.getDescription());
				textAreaInfoFromage.setLineWrap(true);
			}
		}
	}

}