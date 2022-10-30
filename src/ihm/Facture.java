package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Commande;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Facture extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanelAll = new JPanel();
	private JTextField txtFacture;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Facture(Commande commande) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				commande.getPanier().validerPanier();
			}
		});
		setBounds(100, 100, 876, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanelAll.setLayout(new FlowLayout());
		contentPanelAll.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelAll, BorderLayout.CENTER);
		{
			JPanel ContentPanelSouth = new JPanel();
			ContentPanelSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(ContentPanelSouth, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Quitter");
				cancelButton.setPreferredSize(new Dimension(120, 50));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Arial Black", Font.PLAIN, 20));
				cancelButton.setActionCommand("Cancel");
				ContentPanelSouth.add(cancelButton);
			}
		}
		{
			JPanel ContentPanelNorth = new JPanel();
			getContentPane().add(ContentPanelNorth, BorderLayout.NORTH);
			ContentPanelNorth.setLayout(new BorderLayout(0, 0));
			{
				txtFacture = new JTextField();
				txtFacture.setEditable(false);
				txtFacture.setFont(new Font("Arial Black", Font.PLAIN, 30));
				txtFacture.setHorizontalAlignment(SwingConstants.CENTER);
				txtFacture.setText("Facture");
				ContentPanelNorth.add(txtFacture, BorderLayout.CENTER);
				txtFacture.setColumns(10);
			}
			{
				JLabel LabelIconFromage = new JLabel("");
				LabelIconFromage.setIcon(new ImageIcon(Facture.class.getResource("/Photo/fromage2 (1) (1).jpg")));
				ContentPanelNorth.add(LabelIconFromage, BorderLayout.WEST);
			}
		}



		{
			JTextPane textPaneFacture = new JTextPane();
			textPaneFacture.setFont(new Font("Arial Black", Font.PLAIN, 15));
			textPaneFacture.setEditable(false);

			textPaneFacture.setText(commande.toStringFacture());
			{
				JScrollPane pane = new JScrollPane(textPaneFacture);
				getContentPane().add(pane, BorderLayout.CENTER);
			}
			commande.getPanier().validerPanier();
		}


	}
}


