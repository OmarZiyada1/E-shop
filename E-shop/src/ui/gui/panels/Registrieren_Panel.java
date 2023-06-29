package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import domain.E_Shop;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registrieren_Panel extends JPanel {
	private final JPanel panel_registrierenContainer = new JPanel();
	private final JPanel panel_title = new JPanel();
	private final JLabel lbl_RegistrierenTitle = new JLabel("Registrieren");
	private final JPanel panel_form = new JPanel();
	private final JPanel panel_btns = new JPanel();
	private final JButton btn_registrieren = new JButton("Registrieren");
	private final JButton btn_login = new JButton("Login");
	private final JTextField textField_Name = new JTextField();
	private final JTextField textField_Vorname = new JTextField();
	private final JTextField textField_Strasse = new JTextField();
	private final JTextField textField_HausNr = new JTextField();
	private final JTextField textField_Plz = new JTextField();
	private final JTextField textField_Ort = new JTextField();
	private final JRadioButton rdbtn_Deutschland = new JRadioButton("Deutschland");
	private final JRadioButton rdbtn_Oestereich = new JRadioButton("Östereich");
	private final JTextField textField_UserName = new JTextField();
	private final JTextField textField_Password = new JTextField();
	private final JLabel lbl_Name = new JLabel("Name");
	private final JLabel lbl_Vorname = new JLabel("Vorname");
	private final JLabel lbl_Strasse = new JLabel("Strasse");
	private final JLabel lbl_Plz = new JLabel("Plz.");
	private final JLabel lbl_Username = new JLabel("Username");
	private final JLabel lbl_Password = new JLabel("Password");
	private final JLabel lbl_HausNr = new JLabel("HausNr.");
	private final JLabel lbl_Ort = new JLabel("Ort");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel switchMainPanel;
	private JPanel switchSidePanel;
	
	
	//eshop
	private E_Shop shop;

	/**
	 * Create the panel.
	 */
	public Registrieren_Panel(JPanel switchMainPanel, JPanel switchSidePanel, E_Shop shop) {
		this.switchMainPanel= switchMainPanel;
		this.shop=shop;

		initGUI();
	}
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{87, 0};
		gridBagLayout.rowHeights = new int[]{123, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_panel_registrierenContainer = new GridBagConstraints();
		gbc_panel_registrierenContainer.fill = GridBagConstraints.BOTH;
		gbc_panel_registrierenContainer.gridx = 0;
		gbc_panel_registrierenContainer.gridy = 0;
		panel_registrierenContainer.setBackground(Color.DARK_GRAY);
		add(panel_registrierenContainer, gbc_panel_registrierenContainer);
		GridBagLayout gbl_panel_registrierenContainer = new GridBagLayout();
		gbl_panel_registrierenContainer.columnWidths = new int[]{276, 0};
		gbl_panel_registrierenContainer.rowHeights = new int[]{43, 0, 0, 0};
		gbl_panel_registrierenContainer.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_registrierenContainer.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_registrierenContainer.setLayout(gbl_panel_registrierenContainer);
		
		GridBagConstraints gbc_panel_title = new GridBagConstraints();
		gbc_panel_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_title.insets = new Insets(0, 0, 5, 0);
		gbc_panel_title.gridx = 0;
		gbc_panel_title.gridy = 0;
		panel_title.setBackground(Color.DARK_GRAY);
		panel_registrierenContainer.add(panel_title, gbc_panel_title);
		lbl_RegistrierenTitle.setForeground(Color.ORANGE);
		lbl_RegistrierenTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		panel_title.add(lbl_RegistrierenTitle);
		
		GridBagConstraints gbc_panel_form = new GridBagConstraints();
		gbc_panel_form.anchor = GridBagConstraints.SOUTH;
		gbc_panel_form.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_form.insets = new Insets(0, 0, 5, 0);
		gbc_panel_form.gridx = 0;
		gbc_panel_form.gridy = 1;
		panel_form.setBackground(Color.DARK_GRAY);
		panel_registrierenContainer.add(panel_form, gbc_panel_form);
		GridBagLayout gbl_panel_form = new GridBagLayout();
		gbl_panel_form.columnWidths = new int[]{22, 48, 0, 64, 0, 17, 0};
		gbl_panel_form.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_form.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_form.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_form.setLayout(gbl_panel_form);
		
		GridBagConstraints gbc_lbl_Name = new GridBagConstraints();
		gbc_lbl_Name.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Name.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lbl_Name.gridx = 1;
		gbc_lbl_Name.gridy = 0;
		lbl_Name.setForeground(Color.WHITE);
		panel_form.add(lbl_Name, gbc_lbl_Name);
		
		GridBagConstraints gbc_textField_Name = new GridBagConstraints();
		gbc_textField_Name.anchor = GridBagConstraints.SOUTH;
		gbc_textField_Name.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Name.gridx = 2;
		gbc_textField_Name.gridy = 0;
		panel_form.add(textField_Name, gbc_textField_Name);
		
		GridBagConstraints gbc_lbl_Vorname = new GridBagConstraints();
		gbc_lbl_Vorname.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Vorname.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lbl_Vorname.gridx = 3;
		gbc_lbl_Vorname.gridy = 0;
		lbl_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_Vorname.setForeground(Color.WHITE);
		panel_form.add(lbl_Vorname, gbc_lbl_Vorname);
		
		GridBagConstraints gbc_textField_Vorname = new GridBagConstraints();
		gbc_textField_Vorname.anchor = GridBagConstraints.SOUTH;
		gbc_textField_Vorname.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Vorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Vorname.gridx = 4;
		gbc_textField_Vorname.gridy = 0;
		panel_form.add(textField_Vorname, gbc_textField_Vorname);
		
		GridBagConstraints gbc_lbl_Strasse = new GridBagConstraints();
		gbc_lbl_Strasse.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Strasse.anchor = GridBagConstraints.EAST;
		gbc_lbl_Strasse.gridx = 1;
		gbc_lbl_Strasse.gridy = 1;
		lbl_Strasse.setForeground(Color.WHITE);
		panel_form.add(lbl_Strasse, gbc_lbl_Strasse);
		
		GridBagConstraints gbc_textField_Strasse = new GridBagConstraints();
		gbc_textField_Strasse.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Strasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Strasse.gridx = 2;
		gbc_textField_Strasse.gridy = 1;
		panel_form.add(textField_Strasse, gbc_textField_Strasse);
		
		GridBagConstraints gbc_lbl_HausNr = new GridBagConstraints();
		gbc_lbl_HausNr.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_HausNr.anchor = GridBagConstraints.EAST;
		gbc_lbl_HausNr.gridx = 3;
		gbc_lbl_HausNr.gridy = 1;
		lbl_HausNr.setForeground(Color.WHITE);
		panel_form.add(lbl_HausNr, gbc_lbl_HausNr);
		
		GridBagConstraints gbc_textField_HausNr = new GridBagConstraints();
		gbc_textField_HausNr.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HausNr.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HausNr.gridx = 4;
		gbc_textField_HausNr.gridy = 1;
		panel_form.add(textField_HausNr, gbc_textField_HausNr);
		
		GridBagConstraints gbc_lbl_Plz = new GridBagConstraints();
		gbc_lbl_Plz.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Plz.anchor = GridBagConstraints.EAST;
		gbc_lbl_Plz.gridx = 1;
		gbc_lbl_Plz.gridy = 2;
		lbl_Plz.setForeground(Color.WHITE);
		panel_form.add(lbl_Plz, gbc_lbl_Plz);
		
		GridBagConstraints gbc_textField_Plz = new GridBagConstraints();
		gbc_textField_Plz.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Plz.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Plz.gridx = 2;
		gbc_textField_Plz.gridy = 2;
		panel_form.add(textField_Plz, gbc_textField_Plz);
		
		GridBagConstraints gbc_lbl_Ort = new GridBagConstraints();
		gbc_lbl_Ort.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Ort.anchor = GridBagConstraints.EAST;
		gbc_lbl_Ort.gridx = 3;
		gbc_lbl_Ort.gridy = 2;
		lbl_Ort.setForeground(Color.WHITE);
		panel_form.add(lbl_Ort, gbc_lbl_Ort);
		
		GridBagConstraints gbc_textField_Ort = new GridBagConstraints();
		gbc_textField_Ort.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Ort.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Ort.gridx = 4;
		gbc_textField_Ort.gridy = 2;
		panel_form.add(textField_Ort, gbc_textField_Ort);
		
		GridBagConstraints gbc_rdbtn_Deutschland = new GridBagConstraints();
		gbc_rdbtn_Deutschland.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtn_Deutschland.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn_Deutschland.gridx = 2;
		gbc_rdbtn_Deutschland.gridy = 3;
		buttonGroup.add(rdbtn_Deutschland);
		rdbtn_Deutschland.setForeground(Color.WHITE);
		rdbtn_Deutschland.setBackground(Color.DARK_GRAY);
		panel_form.add(rdbtn_Deutschland, gbc_rdbtn_Deutschland);
		
		GridBagConstraints gbc_rdbtn_Oestereich = new GridBagConstraints();
		gbc_rdbtn_Oestereich.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtn_Oestereich.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn_Oestereich.gridx = 4;
		gbc_rdbtn_Oestereich.gridy = 3;
		buttonGroup.add(rdbtn_Oestereich);
		rdbtn_Oestereich.setForeground(Color.WHITE);
		rdbtn_Oestereich.setBackground(Color.DARK_GRAY);
		panel_form.add(rdbtn_Oestereich, gbc_rdbtn_Oestereich);
		
		GridBagConstraints gbc_lbl_Username = new GridBagConstraints();
		gbc_lbl_Username.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Username.anchor = GridBagConstraints.EAST;
		gbc_lbl_Username.gridx = 1;
		gbc_lbl_Username.gridy = 4;
		lbl_Username.setForeground(Color.WHITE);
		panel_form.add(lbl_Username, gbc_lbl_Username);
		
		GridBagConstraints gbc_textField_UserName = new GridBagConstraints();
		gbc_textField_UserName.gridwidth = 3;
		gbc_textField_UserName.insets = new Insets(0, 0, 5, 5);
		gbc_textField_UserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_UserName.gridx = 2;
		gbc_textField_UserName.gridy = 4;
		panel_form.add(textField_UserName, gbc_textField_UserName);
		
		GridBagConstraints gbc_lbl_Password = new GridBagConstraints();
		gbc_lbl_Password.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_Password.anchor = GridBagConstraints.EAST;
		gbc_lbl_Password.gridx = 1;
		gbc_lbl_Password.gridy = 5;
		lbl_Password.setForeground(Color.WHITE);
		panel_form.add(lbl_Password, gbc_lbl_Password);
		
		GridBagConstraints gbc_textField_Password = new GridBagConstraints();
		gbc_textField_Password.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Password.gridwidth = 3;
		gbc_textField_Password.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Password.gridx = 2;
		gbc_textField_Password.gridy = 5;
		panel_form.add(textField_Password, gbc_textField_Password);
		
		GridBagConstraints gbc_panel_btns = new GridBagConstraints();
		gbc_panel_btns.fill = GridBagConstraints.BOTH;
		gbc_panel_btns.gridx = 0;
		gbc_panel_btns.gridy = 2;
		panel_btns.setBackground(Color.DARK_GRAY);
		panel_registrierenContainer.add(panel_btns, gbc_panel_btns);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_login_actionPerformed(e);
			}
		});
		
		panel_btns.add(btn_login);
		
		panel_btns.add(btn_registrieren);
	}

	protected void do_btn_login_actionPerformed(ActionEvent e) {
		switchMainPanel.removeAll();
		GridBagConstraints gbc_login_Panel = new GridBagConstraints();
		gbc_login_Panel.fill = GridBagConstraints.BOTH;
		switchMainPanel.add(new Login_Panel(switchMainPanel, switchSidePanel, this.shop), gbc_login_Panel);
		switchMainPanel.validate();
	}
}
