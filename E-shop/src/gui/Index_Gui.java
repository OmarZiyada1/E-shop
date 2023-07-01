package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import ui.gui.panels.BegruessungPanel;
import ui.gui.panels.Login_Panel;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JSeparator;

import javax.swing.JTextArea;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import javax.swing.Box;

public class Index_Gui extends JFrame {

	private JPanel contentPane;
	private JPanel panel_header = new JPanel();
	private JPanel panel_logoContainer = new JPanel();
	private JPanel panel_header_nav = new JPanel();
	private JLabel lbl_logoImg = new JLabel("img");
	private JLabel lblNewLabel_1 = new JLabel("SAZ Shop");
	private JPanel panel_sideNavbar = new JPanel();
	public JPanel panel_main = new JPanel();

	// e-shop
	public E_Shop e_shop;
	private JPanel panel_northMain = new JPanel();
	private JPanel panel_centerMain = new JPanel();
	private JPanel panel_southMain = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index_Gui frame = new Index_Gui("EShop");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws BestandPasstNichtMitPackungsGroesseException
	 * @throws ParseException
	 * @throws MitarbeiterUsernameIstBenutztException
	 * @throws ArtikelExistiertNichtException
	 * @throws ArtikelExistiertBereitsException
	 * @throws IOException
	 */
	public Index_Gui(String datei) throws IOException, ArtikelExistiertBereitsException, ArtikelExistiertNichtException,
			MitarbeiterUsernameIstBenutztException, ParseException, BestandPasstNichtMitPackungsGroesseException {
		this.e_shop = new E_Shop(datei);

		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));
		panel_header.setBackground(Color.DARK_GRAY);

		contentPane.add(panel_header, BorderLayout.NORTH);
		GridBagLayout gbl_panel_header = new GridBagLayout();
		gbl_panel_header.columnWidths = new int[] { 126, 0, 0 };
		gbl_panel_header.rowHeights = new int[] { 51, 0 };
		gbl_panel_header.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_header.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_header.setLayout(gbl_panel_header);

		GridBagConstraints gbc_panel_logoContainer = new GridBagConstraints();
		gbc_panel_logoContainer.insets = new Insets(0, 0, 0, 5);
		gbc_panel_logoContainer.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_logoContainer.gridx = 0;
		gbc_panel_logoContainer.gridy = 0;
		FlowLayout flowLayout = (FlowLayout) panel_logoContainer.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_logoContainer.setBackground(Color.DARK_GRAY);
		panel_header.add(panel_logoContainer, gbc_panel_logoContainer);

		panel_logoContainer.add(lbl_logoImg);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.ORANGE);

		panel_logoContainer.add(lblNewLabel_1);

		GridBagConstraints gbc_panel_header_nav = new GridBagConstraints();
		gbc_panel_header_nav.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_header_nav.gridx = 1;
		gbc_panel_header_nav.gridy = 0;
		FlowLayout flowLayout_1 = (FlowLayout) panel_header_nav.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_header_nav.setBackground(Color.DARK_GRAY);
		panel_header.add(panel_header_nav, gbc_panel_header_nav);
		panel_sideNavbar.setBackground(Color.DARK_GRAY);

		contentPane.add(panel_sideNavbar, BorderLayout.WEST);
		GridBagLayout gbl_panel_sideNavbar = new GridBagLayout();
		gbl_panel_sideNavbar.columnWidths = new int[] { 188, 0 };
		gbl_panel_sideNavbar.rowHeights = new int[] { 93, 34, 0, 0 };
		gbl_panel_sideNavbar.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_sideNavbar.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_sideNavbar.setLayout(gbl_panel_sideNavbar);
		panel_main.setBackground(new Color(255, 128, 0));

		contentPane.add(panel_main, BorderLayout.CENTER);
		this.panel_main.setLayout(new BorderLayout(0, 0));
		this.panel_northMain.setBackground(Color.DARK_GRAY);

		this.panel_main.add(this.panel_northMain, BorderLayout.NORTH);
		this.panel_centerMain.setBackground(Color.DARK_GRAY);

		this.panel_main.add(this.panel_centerMain, BorderLayout.CENTER);
		this.panel_southMain.setBackground(Color.DARK_GRAY);

		this.panel_main.add(this.panel_southMain, BorderLayout.SOUTH);

		Login_Panel login_Panel = new Login_Panel(this.panel_main, this.panel_sideNavbar, panel_header_nav, e_shop);
		addLoginPanel(this.panel_centerMain, login_Panel);

		panel_sideNavbar.removeAll();
		panel_sideNavbar.add(new BegruessungPanel(this.panel_header, this.panel_main, this.panel_sideNavbar, e_shop));
		panel_sideNavbar.validate();

	}

	private void addLoginPanel(JPanel panelCenterMain, JPanel loginPanel) {
		panelCenterMain.removeAll();
		this.panel_centerMain.setLayout(new MigLayout("", "[276px,grow,fill]", "[149px,grow,fill]"));
		panelCenterMain.add(loginPanel, "cell 0 0,alignx center,aligny center");
		panelCenterMain.validate();
	}
}
