package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import entities.Nutzer;
import gui.Index_Gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;

public class Login_Panel extends JPanel {
	private JPanel panel_LoginContainer = new JPanel();
	private JPanel panel_title = new JPanel();
	private JLabel lbl_mainLogintitle = new JLabel("Login");
	private JPanel panel_btns = new JPanel();
	private JButton btn_registrieren = new JButton("Registrieren");
	private JButton btn_login = new JButton("Login");
	private JPanel panel_form = new JPanel();
	private JRadioButton rdbtn_Kunde = new JRadioButton("Kunde");
	private JRadioButton rdbtnMitarbeiter = new JRadioButton("Mitarbeiter");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField passwordField = new JPasswordField();
	private JTextField textField_userName = new JTextField();

	//
	private JPanel mainPanel;
	private JPanel switchSidePanel;
	private TablePanel tablePanel;
	private JPanel artikelVWPanel;
	private JPanel logoutPanel;
	private JPanel RegistrierenPanel;

	private E_Shop shop;
	private Nutzer loggedNutzer;
	private JPanel panel_header_nav;

	/**
	 * Create the panel.
	 */
	public Login_Panel(JPanel mainPanel, JPanel switchSidePanel, JPanel panel_header_nav, E_Shop shop) {
		this.shop = shop;
		this.mainPanel = mainPanel;
		this.switchSidePanel = switchSidePanel;
		this.panel_header_nav = panel_header_nav;
		initGUI();

	}

	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panel_LoginContainer = new GridBagConstraints();
		gbc_panel_LoginContainer.fill = GridBagConstraints.BOTH;
		panel_LoginContainer.setBackground(Color.DARK_GRAY);
		add(panel_LoginContainer, gbc_panel_LoginContainer);
		GridBagLayout gbl_panel_LoginContainer = new GridBagLayout();
		gbl_panel_LoginContainer.columnWeights = new double[] { 0.0 };
		gbl_panel_LoginContainer.rowWeights = new double[] { 1.0, 0.0, 1.0 };
		gbl_panel_LoginContainer.rowHeights = new int[] { 0, 0, 0 };

		panel_LoginContainer.setLayout(gbl_panel_LoginContainer);
		panel_title.setBackground(Color.DARK_GRAY);

		GridBagConstraints gbc_panel_title = new GridBagConstraints();
		gbc_panel_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_title.anchor = GridBagConstraints.NORTH;
		gbc_panel_title.insets = new Insets(0, 0, 5, 0);
		panel_LoginContainer.add(panel_title, gbc_panel_title);
		lbl_mainLogintitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl_mainLogintitle.setForeground(Color.ORANGE);

		panel_title.add(lbl_mainLogintitle);

		GridBagConstraints gbc_panel_form = new GridBagConstraints();
		gbc_panel_form.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_form.anchor = GridBagConstraints.SOUTH;
		gbc_panel_form.insets = new Insets(0, 0, 5, 0);
		gbc_panel_form.gridy = 1;
		panel_form.setBackground(Color.DARK_GRAY);
		panel_LoginContainer.add(panel_form, gbc_panel_form);
		GridBagLayout gbl_panel_form = new GridBagLayout();
		gbl_panel_form.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel_form.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panel_form.setLayout(gbl_panel_form);
		GridBagConstraints gbc_rdbtn_Kunde = new GridBagConstraints();
		gbc_rdbtn_Kunde.gridx = 0;
		gbc_rdbtn_Kunde.gridy = 0;
		gbc_rdbtn_Kunde.insets = new Insets(0, 0, 5, 5);
		buttonGroup.add(rdbtn_Kunde);
		this.rdbtn_Kunde.setSelected(true);
		rdbtn_Kunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtn_Kunde_actionPerformed(e);
			}
		});
		rdbtn_Kunde.setForeground(Color.WHITE);
		rdbtn_Kunde.setBackground(Color.DARK_GRAY);
		panel_form.add(rdbtn_Kunde, gbc_rdbtn_Kunde);

		GridBagConstraints gbc_rdbtnMitarbeiter = new GridBagConstraints();
		gbc_rdbtnMitarbeiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnMitarbeiter.gridy = 0;
		gbc_rdbtnMitarbeiter.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMitarbeiter.gridx = 1;
		buttonGroup.add(rdbtnMitarbeiter);
		rdbtnMitarbeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtnMitarbeiter_actionPerformed(e);
			}
		});
		rdbtnMitarbeiter.setForeground(Color.WHITE);
		rdbtnMitarbeiter.setBackground(Color.DARK_GRAY);
		panel_form.add(rdbtnMitarbeiter, gbc_rdbtnMitarbeiter);

		GridBagConstraints gbc_textField_userName = new GridBagConstraints();
		gbc_textField_userName.gridwidth = 2;
		gbc_textField_userName.insets = new Insets(0, 0, 5, 0);
		gbc_textField_userName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_userName.gridx = 0;
		gbc_textField_userName.gridy = 1;
		this.textField_userName.setColumns(10);
		this.panel_form.add(this.textField_userName, gbc_textField_userName);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 2;
		this.panel_form.add(this.passwordField, gbc_passwordField);

		GridBagConstraints gbc_panel_btns = new GridBagConstraints();
		gbc_panel_btns.fill = GridBagConstraints.BOTH;
		gbc_panel_btns.gridx = 0;
		gbc_panel_btns.gridy = 2;
		panel_btns.setBackground(Color.DARK_GRAY);
		panel_LoginContainer.add(panel_btns, gbc_panel_btns);
		btn_registrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_registrieren_actionPerformed(e);
			}
		});

		panel_btns.add(btn_registrieren);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do_btn_login_actionPerformed(e);
				} catch (ArtikelExistiertNichtException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel_btns.add(btn_login);
	}

	protected void do_btn_registrieren_actionPerformed(ActionEvent e) {
		mainPanel.removeAll();
		this.RegistrierenPanel = new Registrieren_Panel(mainPanel, switchSidePanel, panel_header_nav, this.shop);
		mainPanel.add(RegistrierenPanel, BorderLayout.CENTER);
		mainPanel.validate();

	}

	protected void do_rdbtnMitarbeiter_actionPerformed(ActionEvent e) {
		btn_registrieren.setVisible(false);
	}

	protected void do_rdbtn_Kunde_actionPerformed(ActionEvent e) {
		btn_registrieren.setVisible(true);

	}

	protected void do_btn_login_actionPerformed(ActionEvent e) throws ArtikelExistiertNichtException {
		String userName = textField_userName.getText().trim();
		char[] passwordArr = passwordField.getPassword();
		String password = new String(passwordArr).trim();

		if (userName.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle erforderlichen Felder aus");

		} else {

			if (rdbtnMitarbeiter.isSelected()) {
				try {
					loggedNutzer = shop.mitarbeiterEinloggen(userName, password);
					JOptionPane.showMessageDialog(null, "Du hast erfolgreich angemeldet!", "Info Message",
							JOptionPane.INFORMATION_MESSAGE);

					mainPanel.removeAll();
					switchSidePanel.removeAll();
					panel_header_nav.removeAll();
					addSideNavBar();
					addTablePanel();
					addVerwaltungsPanel();
					addLogoutPanel();
					mainPanel.add(new Suchen_MainPanel(), BorderLayout.NORTH);
					mainPanel.validate();

				} catch (NutzernameOderPasswortFalschException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Info Message", JOptionPane.ERROR_MESSAGE);
				}

			} else if (rdbtn_Kunde.isSelected()) {
				try {
					loggedNutzer = shop.kundenEinloggen(userName, password);

					JOptionPane.showMessageDialog(null, "Du hast erfolgreich angemeldet!", "Info Message",
							JOptionPane.INFORMATION_MESSAGE);
					mainPanel.removeAll();
					switchSidePanel.removeAll();
					addTablePanel();
					addLogoutPanel();

				} catch (NutzernameOderPasswortFalschException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Info Message", JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}

	/**
	 * 
	 */
	private void addLogoutPanel() {
		this.logoutPanel = new LogoutPanel(panel_header_nav, mainPanel, switchSidePanel, loggedNutzer, shop);
		panel_header_nav.add(logoutPanel);
		panel_header_nav.revalidate();
	}

	/**
	 * 
	 */
	private void addVerwaltungsPanel() {
		this.artikelVWPanel = new ArtikelVerwaltungsPanel(this.tablePanel);
		mainPanel.add(artikelVWPanel, BorderLayout.SOUTH);
		mainPanel.validate();
	}

	/**
	 * @throws ArtikelExistiertNichtException
	 */
	private void addTablePanel() throws ArtikelExistiertNichtException {
		this.tablePanel = new TablePanel(this.shop);
		mainPanel.add(tablePanel, BorderLayout.CENTER);
		mainPanel.validate();
	}

	private void addSideNavBar() {

		GridBagConstraints gbc_mMenue_Panel = new GridBagConstraints();
		gbc_mMenue_Panel.ipady = 10;
		gbc_mMenue_Panel.fill = GridBagConstraints.BOTH;
		switchSidePanel.setLayout(new BoxLayout(switchSidePanel, BoxLayout.Y_AXIS));
		switchSidePanel.add(new Mitarbeiter_SideNavebar_Panel(mainPanel, new ArtikelVerwaltungsPanel(this.tablePanel), shop),
				gbc_mMenue_Panel);
		switchSidePanel.validate();
	}

}
