package ihm;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.GridLayout;


import javax.swing.table.DefaultTableModel;

import modele.Panier;
import modele.TypeLivraison;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VotrePanier extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTitle;
	private JTable tablePanier;
	private final ButtonGroup buttonGroupLivraison = new ButtonGroup();
	private JButton ButtonValiderLePanier;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VotrePanier(Panier panier) {
		setBounds(100, 100, 737, 460);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel CotentPanelNorth = new JPanel();
			getContentPane().add(CotentPanelNorth, BorderLayout.NORTH);
			{
				JLabel LabelIconCaddie = new JLabel("");
				LabelIconCaddie.setIcon(new ImageIcon(VotrePanier.class.getResource("/Photo/caddie (1) (1) (1) (1).jpg")));
				LabelIconCaddie.setFont(new Font("Tahoma", Font.PLAIN, 20));
				CotentPanelNorth.add(LabelIconCaddie);
			}
			{
				textFieldTitle = new JTextField();
				textFieldTitle.setText("Votre Panier");
				textFieldTitle.setFont(new Font("Monotype Corsiva", Font.PLAIN, 41));
				textFieldTitle.setEditable(false);
				textFieldTitle.setColumns(10);
				CotentPanelNorth.add(textFieldTitle);
			}
		}
		{
			JPanel ContentPanelSouth = new JPanel();
			getContentPane().add(ContentPanelSouth, BorderLayout.SOUTH);
			ContentPanelSouth.setLayout(new BorderLayout(0, 0));
			{
				JPanel ContentPanelSouth1 = new JPanel();
				ContentPanelSouth.add(ContentPanelSouth1, BorderLayout.SOUTH);
				{
					ButtonValiderLePanier = new JButton("Valider le panier");
					if(panier.estVide()) {
						ButtonValiderLePanier.setEnabled(false);
					}
					ButtonValiderLePanier.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							AdresseLivrasion f = new AdresseLivrasion(panier);
							f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							f.setVisible(true);
							dispose();
						}
					});
					ButtonValiderLePanier.setFont(new Font("Arial Black", Font.PLAIN, 20));
					ContentPanelSouth1.add(ButtonValiderLePanier);
				}
				{
					JButton ButtonViderLePanier = new JButton("Vider le panier");
					if(panier.estVide()) {
						ButtonViderLePanier.setEnabled(false);
					}
					ButtonViderLePanier.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							DefaultTableModel model = (DefaultTableModel) tablePanier.getModel();
							float f1= panier.calculSoustotalTTC();
							float f2= panier.calculTotalTTC();
							DecimalFormat df = new DecimalFormat();
							df.setMaximumFractionDigits(2);
							String s1 = df.format(f1);
							String s2 = df.format(f2);
							model.setRowCount(0);
							model.addRow(new Object[] {"Produit", "Prix", "Quantit\u00E9", "total"});
							model.addRow(new Object[] {null,null,null,null});
							model.addRow(new Object[] {null, null, "SOUS TOTAL TTC", s1.replace('.', ',')});
							model.addRow(new Object[] {null, null, "FRAIS DE PORT", String.valueOf(panier.FraisDePort()).replace('.', ',')});
							model.addRow(new Object[] {null, null, "TOTAL TTC", s2.replace('.', ',')});
							ButtonViderLePanier.setEnabled(false);
							panier.viderPanier();
							ButtonValiderLePanier.setEnabled(false);
						}
					});
					
					ButtonViderLePanier.setFont(new Font("Arial Black", Font.PLAIN, 20));
					ContentPanelSouth1.add(ButtonViderLePanier);
				}
				
				{
					JButton ButtonContinuerLesAchats = new JButton("Continuer les achats");
					ButtonContinuerLesAchats.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							dispose();
						}
						
					});
					ButtonContinuerLesAchats.setFont(new Font("Arial Black", Font.PLAIN, 20));
					ContentPanelSouth1.add(ButtonContinuerLesAchats);
				}
			}
			{
				JPanel ContentPanelWest = new JPanel();
				ContentPanelSouth.add(ContentPanelWest, BorderLayout.WEST);
				ContentPanelWest.setLayout(new GridLayout(3, 3, 3, 0));
				{
					JRadioButton RadioButtonColissimoRelais = new JRadioButton("Colissimo en point Relais 4,9€");
					RadioButtonColissimoRelais.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							panier.setLivraison(1);
							DefaultTableModel model = (DefaultTableModel) tablePanier.getModel();
							model.setRowCount(model.getRowCount()-2);
							model.addRow(new Object[]{null, null, "FRAIS DE PORT", String.valueOf(panier.FraisDePort()).replace('.', ',')});
							float f= panier.calculTotalTTC();
							DecimalFormat df = new DecimalFormat();
							df.setMaximumFractionDigits(2);
							String s = df.format(f);
							model.addRow(new Object[]{null, null, "TOTAL TTC", s.replace('.', ',')});
							
						}
					});
					buttonGroupLivraison.add(RadioButtonColissimoRelais);
					RadioButtonColissimoRelais.setFont(new Font("Arial Black", Font.PLAIN, 17));
					if(panier.getLivraison() == TypeLivraison.COLISSIMO_POINT_RELAIS) {
						RadioButtonColissimoRelais.setSelected(true);
					}
					ContentPanelWest.add(RadioButtonColissimoRelais);
				}
				{
					JRadioButton RadioButtonColissimoDomicile = new JRadioButton("Colissimo à domicile 4,9€");
					RadioButtonColissimoDomicile.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							panier.setLivraison(2);
							DefaultTableModel model = (DefaultTableModel) tablePanier.getModel();
							model.setRowCount(model.getRowCount()-2);
							model.addRow(new Object[]{null, null, "FRAIS DE PORT", String.valueOf(panier.FraisDePort()).replace('.', ',')});
							float f= panier.calculTotalTTC();
							DecimalFormat df = new DecimalFormat();
							df.setMaximumFractionDigits(2);
							String s = df.format(f);
							model.addRow(new Object[]{null, null, "TOTAL TTC", s.replace('.', ',')});
						}
					});
					buttonGroupLivraison.add(RadioButtonColissimoDomicile);
					RadioButtonColissimoDomicile.setFont(new Font("Arial Black", Font.PLAIN, 17));
					if(panier.getLivraison() == TypeLivraison.COLISSIMO_DOMICILE) {
						RadioButtonColissimoDomicile.setSelected(true);
					}
					ContentPanelWest.add(RadioButtonColissimoDomicile);
				}
				{
					JRadioButton RadioButtonChronopost = new JRadioButton("Chronopost 9,9€");
					RadioButtonChronopost.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							panier.setLivraison(3);
							DefaultTableModel model = (DefaultTableModel) tablePanier.getModel();
							model.setRowCount(model.getRowCount()-2);
							model.addRow(new Object[]{null, null, "FRAIS DE PORT", String.valueOf(panier.FraisDePort()).replace('.', ',')});
							float f= panier.calculTotalTTC();
							DecimalFormat df = new DecimalFormat();
							df.setMaximumFractionDigits(2);
							String s = df.format(f);
							model.addRow(new Object[]{null, null, "TOTAL TTC", s.replace('.', ',')});
						}
					});
					buttonGroupLivraison.add(RadioButtonChronopost);
					if(panier.getLivraison() == TypeLivraison.CHRONOPOST) {
						RadioButtonChronopost.setSelected(true);
					}
					RadioButtonChronopost.setFont(new Font("Arial Black", Font.PLAIN, 17));
					ContentPanelWest.add(RadioButtonChronopost);
				}
			}
		}
		{
			tablePanier = new JTable();
			tablePanier.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tablePanier.setFont(new Font("Arial Black", Font.PLAIN, 15));
			
		    JScrollPane pane = new JScrollPane(tablePanier);			
		    getContentPane().add(pane, BorderLayout.CENTER);
			float f1= panier.calculSoustotalTTC();
			float f2= panier.calculTotalTTC();
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			String s1 = df.format(f1);
			String s2 = df.format(f2);
			tablePanier.setModel(new DefaultTableModel(
					new Object[][] {
						{"Produit", "Prix", "Quantit\u00E9", "total"},
						{null, null, null, null},
						{null, null, "SOUS TOTAL TTC", s1.replace('.', ',')},
						{null, null, "FRAIS DE PORT", String.valueOf(panier.FraisDePort()).replace('.', ',')},
						{null, null, "TOTAL TTC", s2.replace('.', ',')},
					},
					new String[] {
						"New column", "New column", "New column", "New column"
					}
				));
			
			DefaultTableModel model = (DefaultTableModel) tablePanier.getModel();
			if(!panier.estVide()) {
				model.setRowCount(0);
				model.addRow(new Object[] {"Produit", "Prix", "Quantit\u00E9", "total"});
				for(Object[] a : panier.toStringPanier()) {
					model.addRow(a);
				}
				
				model.addRow(new Object[]{null, null, "SOUS TOTAL TTC",s1.replace('.', ',')});
				model.addRow(new Object[]{null, null, "FRAIS DE PORT", String.valueOf(panier.FraisDePort()).replace('.', ',')});
				model.addRow(new Object[]{null, null, "TOTAL TTC", s2.replace('.', ',')});
			}
			
		}
	}

}
