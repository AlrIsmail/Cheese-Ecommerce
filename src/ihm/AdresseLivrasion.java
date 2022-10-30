package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Client;
import modele.Commande;
import modele.Panier;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdresseLivrasion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanelcenter = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldZIPCode;
	private JTextField textFieldSurname;
	private JTextField textFieldAdress1;
	private JTextField textFieldAdress2;
	private JTextField textFieldTown;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldEmail;
	private Client client;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public AdresseLivrasion(Panier panier) {
		setBounds(100, 100, 660, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanelcenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelcenter, BorderLayout.CENTER);
		contentPanelcenter.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanelcenter.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(8, 0, 0, 0));
			{
				JLabel LabelName = new JLabel("Nom");
				LabelName.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelName);
			}
			{
				JLabel LabelSurname = new JLabel("Prénom");
				LabelSurname.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelSurname);
			}
			{
				JLabel LabelAdress1 = new JLabel("Adresse 1");
				LabelAdress1.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelAdress1);
			}
			{
				JLabel LabelAdress2 = new JLabel("Adresse 2");
				LabelAdress2.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelAdress2);
			}
			{
				JLabel LabelZIPCode = new JLabel("Code Postal    ");
				LabelZIPCode.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelZIPCode);
			}
			{
				JLabel LabelTown = new JLabel("Ville");
				LabelTown.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelTown);
			}
			{
				JLabel LabelPhoneNumber = new JLabel("Téléphone");
				LabelPhoneNumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelPhoneNumber);
			}
			{
				JLabel LabelEmail = new JLabel("Mail");
				LabelEmail.setFont(new Font("Arial Black", Font.PLAIN, 20));
				panel.add(LabelEmail);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanelcenter.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(8, 1, 0, 0));
			{
				textFieldName = new JTextField();
				textFieldName.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldName);
				textFieldName.setColumns(10);
			}
			{
				textFieldSurname = new JTextField();
				textFieldSurname.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldSurname);
				textFieldSurname.setColumns(10);
			}
			{
				textFieldAdress1 = new JTextField();
				textFieldAdress1.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldAdress1);
				textFieldAdress1.setColumns(10);
			}
			{
				textFieldAdress2 = new JTextField();
				textFieldAdress2.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldAdress2);
				textFieldAdress2.setColumns(10);
			}
			{
				textFieldZIPCode = new JTextField();
				textFieldZIPCode.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldZIPCode);
				textFieldZIPCode.setColumns(10);
			}
			{
				textFieldTown = new JTextField();
				textFieldTown.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldTown);
				textFieldTown.setColumns(10);
			}
			{
				textFieldPhoneNumber = new JTextField();
				textFieldPhoneNumber.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldPhoneNumber);
				textFieldPhoneNumber.setColumns(10);
			}
			{
				textFieldEmail = new JTextField();
				textFieldEmail.setFont(new Font("Arial Black", Font.PLAIN, 15));
				panel.add(textFieldEmail);
				textFieldEmail.setColumns(10);
			}
		}
		{
			JPanel ContentPanelSouth = new JPanel();
			ContentPanelSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(ContentPanelSouth, BorderLayout.SOUTH);
			{
				JButton ButtonOk = new JButton("OK");
				ButtonOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				ButtonOk.setPreferredSize(new Dimension(120, 40));
				ButtonOk.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (textFieldAdress2.getText() != "") {
							client = new Client(textFieldName.getText(),textFieldSurname.getText(), textFieldAdress1.getText(),
									textFieldAdress2.getText(), textFieldZIPCode.getText(), textFieldTown.getText(),
									textFieldPhoneNumber.getText(), textFieldEmail.getText());
						}else {
							client = new Client(textFieldName.getText(),textFieldSurname.getText(), textFieldAdress1.getText(),
									textFieldZIPCode.getText(), textFieldTown.getText(),
									textFieldPhoneNumber.getText(), textFieldEmail.getText());
						}
						Facture f = new Facture(new Commande(panier, client));
						f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						f.setVisible(true);
						dispose();
					}
				});
				ButtonOk.setFont(new Font("Arial Black", Font.PLAIN, 20));
				ButtonOk.setActionCommand("OK");
				ContentPanelSouth.add(ButtonOk);
				getRootPane().setDefaultButton(ButtonOk);
			}
			{
				JButton ButtonCancel = new JButton("Annuler");
				ButtonCancel.setPreferredSize(new Dimension(120, 40));
				ButtonCancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VotrePanier f = new VotrePanier(panier);
						f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						f.setVisible(true);
						dispose();
					}
				});
				ButtonCancel.setFont(new Font("Arial Black", Font.PLAIN, 20));
				ButtonCancel.setActionCommand("Cancel");
				ContentPanelSouth.add(ButtonCancel);
			}
		}
	}

}
