package ihm;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import modele.Articles;
import modele.Fromage;
import modele.GenerationFromages;
import modele.Panier;
import modele.TypeLait;

import javax.swing.DefaultListModel;


import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.DecimalFormat;

public class AccueilTousLesFromages {

	JFrame frame;
	private JTextField textFieldTousLesFromages;
	private JButton ButtonPanier;
	private JList<String> listFromage;
	private final Articles articles = GenerationFromages.générationBaseFromages();
	private Panier panier = new Panier();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccueilTousLesFromages window = new AccueilTousLesFromages();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccueilTousLesFromages() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
                float cal = panier.calculSoustotalTTC();
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                String s1 = df.format(cal);
                ButtonPanier.setText(s1.replace('.', ',') + "\u20AC");
            }
			public void windowLostFocus(WindowEvent e) {
			}
		});
		frame.setBounds(100, 100, 879, 721);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel ContentPanelSouth = new JPanel();
		ContentPanelSouth.setToolTipText("");
		frame.getContentPane().add(ContentPanelSouth, BorderLayout.SOUTH);
		ContentPanelSouth.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton ButtonVache = new JButton("Vache");
		JButton ButtonChevre = new JButton("Chevre");
		JButton ButtonBrebis = new JButton("Brebis");
		JButton ButtonAll = new JButton("Tous");
		
		ButtonChevre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonChevre.setBackground(UIManager.getColor("Button.white"));
				ButtonVache.setBackground(Color.white);
				ButtonBrebis.setBackground(Color.white);
				ButtonAll.setBackground(Color.white);
				DefaultListModel<String> model = (DefaultListModel<String>) listFromage.getModel();
				model.removeAllElements();
				Articles articles = new Articles();
				articles.addFromages(GenerationFromages.générationBaseFromages().getLesFromages());
				for(Fromage S : articles.fromagesAuLaitDe(TypeLait.CHEVRE)) {
					model.add(model.getSize(), S.getDésignation());
				}
			}
		});
		ButtonChevre.setIcon(new ImageIcon(AccueilTousLesFromages.class.getResource("/Photo/chevre (1) (1).jpg")));
		ButtonChevre.setSelectedIcon(null);
		ButtonChevre.setFont(new Font("Arial Black", Font.PLAIN, 20));

		ContentPanelSouth.add(ButtonChevre);

		
		ButtonVache.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonChevre.setBackground(Color.white);
				ButtonVache.setBackground(UIManager.getColor("Button.white"));
				ButtonBrebis.setBackground(Color.white);
				ButtonAll.setBackground(Color.white);
				DefaultListModel<String> model = (DefaultListModel<String>) listFromage.getModel();
				model.removeAllElements();
				Articles articles = new Articles();
				articles.addFromages(GenerationFromages.générationBaseFromages().getLesFromages());
				for(Fromage S : articles.fromagesAuLaitDe(TypeLait.VACHE)) {
					model.add(model.getSize(), S.getDésignation());
				}
			}
		});
		ButtonVache.setIcon(new ImageIcon(AccueilTousLesFromages.class.getResource("/Photo/vache (1) (1) (2).jpg")));
		ButtonVache.setFont(new Font("Arial Black", Font.PLAIN, 18));

		ContentPanelSouth.add(ButtonVache);

		
		ButtonBrebis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonChevre.setBackground(Color.white);
				ButtonVache.setBackground(Color.white);
				ButtonBrebis.setBackground(UIManager.getColor("Button.white"));
				ButtonAll.setBackground(Color.white);
				DefaultListModel<String> model = (DefaultListModel<String>) listFromage.getModel();
				model.removeAllElements();
				Articles articles = new Articles();
				articles.addFromages(GenerationFromages.générationBaseFromages().getLesFromages());
				for(Fromage S : articles.fromagesAuLaitDe(TypeLait.BREBIS)) {
					model.add(model.getSize(), S.getDésignation());
				}

			}
			});
		ButtonBrebis.setIcon(new ImageIcon(AccueilTousLesFromages.class.getResource("/Photo/brebris (1).jpg")));
		ButtonBrebis.setFont(new Font("Arial Black", Font.PLAIN, 20));
		ContentPanelSouth.add(ButtonBrebis);

		
		ButtonAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonChevre.setBackground(Color.white);
				ButtonVache.setBackground(Color.white);
				ButtonBrebis.setBackground(Color.white);
				ButtonAll.setBackground(UIManager.getColor("Button.white"));
				DefaultListModel<String> model = (DefaultListModel<String>) listFromage.getModel();
				model.removeAllElements();
				for(Fromage S : GenerationFromages.générationBaseFromages().getLesFromages()) {
					model.add(model.getSize(), S.getDésignation());
				}
			}
		});
		ButtonAll.setIcon(new ImageIcon(AccueilTousLesFromages.class.getResource("/Photo/fromage2 (1).jpg")));
		ButtonAll.setToolTipText("");
		ButtonAll.setBackground(new Color(240, 240, 240));
		ButtonAll.setForeground(new Color(0, 0, 0));
		ButtonAll.setFont(new Font("Arial Black", Font.PLAIN, 20));
		ContentPanelSouth.add(ButtonAll);

		JPanel ContentPanelNorth = new JPanel();
		frame.getContentPane().add(ContentPanelNorth, BorderLayout.NORTH);
		ContentPanelNorth.setLayout(new BorderLayout(0, 0));

		ButtonPanier = new JButton("0,00\u20AC");
		ButtonPanier.setPreferredSize(new Dimension(200, 50));
		ButtonPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VotrePanier f = new VotrePanier(panier);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
		ButtonPanier.setVerticalAlignment(SwingConstants.BOTTOM);
		ButtonPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ButtonPanier.setIcon(new ImageIcon(AccueilTousLesFromages.class.getResource("/Photo/caddie (1) (1) (1) (1).jpg")));
		ButtonPanier.setFont(new Font("Monotype Corsiva", Font.PLAIN, 34));
		ContentPanelNorth.add(ButtonPanier, BorderLayout.EAST);

		JPanel ContentPanelWest = new JPanel();
		ContentPanelNorth.add(ContentPanelWest, BorderLayout.WEST);
		ContentPanelWest.setLayout(new BorderLayout(0, 0));

		textFieldTousLesFromages = new JTextField();
		textFieldTousLesFromages.setText("Tous les fromages");
		textFieldTousLesFromages.setFont(new Font("Monotype Corsiva", Font.BOLD, 36));
		textFieldTousLesFromages.setEditable(false);
		textFieldTousLesFromages.setColumns(10);
		ContentPanelWest.add(textFieldTousLesFromages, BorderLayout.EAST);

		JLabel LabelIconFormage = new JLabel("");
		LabelIconFormage.setIcon(new ImageIcon(AccueilTousLesFromages.class.getResource("/Photo/fromage2 (1).jpg")));
		ContentPanelWest.add(LabelIconFormage, BorderLayout.WEST);
		JScrollPane scrollPaneCenter = new JScrollPane();
		frame.getContentPane().add(scrollPaneCenter, BorderLayout.CENTER);
		DefaultListModel<String> model = new DefaultListModel<String>();
		listFromage = new JList<String>(model);
		listFromage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = (String) listFromage.getSelectedValue();
			
				 Fromage fromage =  articles.getFromage(selected);
				
				InfoFromage f =  new InfoFromage(fromage, panier);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);	

			}
		});
		listFromage.setFont(new Font("Monotype Corsiva", Font.PLAIN, 36));

		scrollPaneCenter.setViewportView(listFromage);

		for(Fromage S : GenerationFromages.générationBaseFromages().getLesFromages()) {
			model.add(model.getSize(), S.getDésignation());
		}
		
		
	}
}
