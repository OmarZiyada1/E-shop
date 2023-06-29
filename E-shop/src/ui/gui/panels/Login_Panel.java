package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import domain.E_Shop;
import gui.Index_Gui;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class Login_Panel extends JPanel {
	private final JPanel panel = new JPanel();
	private final JPanel panel_title = new JPanel();
	private final JLabel lbl_mainLogintitle = new JLabel("Login");
	private final JPanel panel_btns = new JPanel();
	private final JButton btn_registrieren = new JButton("Registrieren");
	private final JButton btn_login = new JButton("Login");
	private final JPanel panel_form = new JPanel();
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();
	private JPanel switchMainPanel;
	private JPanel switchSidePanel;
	private final JRadioButton rdbtn_Kunde = new JRadioButton("Kunde");
	private final JRadioButton rdbtnMitarbeiter = new JRadioButton("Mitarbeiter");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private E_Shop shop;

	/**
	 * Create the panel.
	 */
	public Login_Panel(JPanel switchMainPanel, JPanel switchSidePanel, E_Shop shop) {
		this.shop = shop;
		this.switchMainPanel = switchMainPanel;
		this.switchSidePanel = switchSidePanel;
		initGUI();

	}

	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.setBackground(Color.DARK_GRAY);
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 276, 0 };
		gbl_panel.rowHeights = new int[] { 43, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		panel_title.setBackground(Color.DARK_GRAY);

		GridBagConstraints gbc_panel_title = new GridBagConstraints();
		gbc_panel_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_title.insets = new Insets(0, 0, 5, 0);
		gbc_panel_title.gridx = 0;
		gbc_panel_title.gridy = 0;
		panel.add(panel_title, gbc_panel_title);
		lbl_mainLogintitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl_mainLogintitle.setForeground(Color.ORANGE);

		panel_title.add(lbl_mainLogintitle);

		GridBagConstraints gbc_panel_form = new GridBagConstraints();
		gbc_panel_form.anchor = GridBagConstraints.SOUTH;
		gbc_panel_form.insets = new Insets(0, 0, 5, 0);
		gbc_panel_form.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_form.gridx = 0;
		gbc_panel_form.gridy = 1;
		panel_form.setBackground(Color.DARK_GRAY);
		panel.add(panel_form, gbc_panel_form);
		GridBagLayout gbl_panel_form = new GridBagLayout();
		gbl_panel_form.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_form.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_form.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_form.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_form.setLayout(gbl_panel_form);

		GridBagConstraints gbc_rdbtn_Kunde = new GridBagConstraints();
		gbc_rdbtn_Kunde.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn_Kunde.gridx = 0;
		gbc_rdbtn_Kunde.gridy = 0;
		buttonGroup.add(rdbtn_Kunde);
		rdbtn_Kunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtn_Kunde_actionPerformed(e);
			}
		});
		rdbtn_Kunde.setForeground(Color.WHITE);
		rdbtn_Kunde.setBackground(Color.DARK_GRAY);
		panel_form.add(rdbtn_Kunde, gbc_rdbtn_Kunde);

		GridBagConstraints gbc_rdbtnMitarbeiter = new GridBagConstraints();
		gbc_rdbtnMitarbeiter.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMitarbeiter.gridx = 1;
		gbc_rdbtnMitarbeiter.gridy = 0;
		buttonGroup.add(rdbtnMitarbeiter);
		rdbtnMitarbeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtnMitarbeiter_actionPerformed(e);
			}
		});
		rdbtnMitarbeiter.setForeground(Color.WHITE);
		rdbtnMitarbeiter.setBackground(Color.DARK_GRAY);
		panel_form.add(rdbtnMitarbeiter, gbc_rdbtnMitarbeiter);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		textField.setColumns(10);
		panel_form.add(textField, gbc_textField);

		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 2;
		textField_1.setColumns(10);
		panel_form.add(textField_1, gbc_textField_1);

		GridBagConstraints gbc_panel_btns = new GridBagConstraints();
		gbc_panel_btns.fill = GridBagConstraints.BOTH;
		gbc_panel_btns.gridx = 0;
		gbc_panel_btns.gridy = 2;
		panel_btns.setBackground(Color.DARK_GRAY);
		panel.add(panel_btns, gbc_panel_btns);
		btn_registrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_registrieren_actionPerformed(e);
			}
		});

		panel_btns.add(btn_registrieren);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_login_actionPerformed(e);
			}
		});

		panel_btns.add(btn_login);
	}

	protected void do_btn_registrieren_actionPerformed(ActionEvent e) {
		switchMainPanel.removeAll();
		GridBagConstraints gbc_login_Panel = new GridBagConstraints();
		gbc_login_Panel.fill = GridBagConstraints.BOTH;
		switchMainPanel.add(new Registrieren_Panel(switchMainPanel, switchSidePanel, this.shop), gbc_login_Panel);
		switchMainPanel.validate();

	}

	protected void do_rdbtnMitarbeiter_actionPerformed(ActionEvent e) {
		btn_registrieren.setVisible(false);
	}

	protected void do_rdbtn_Kunde_actionPerformed(ActionEvent e) {
		btn_registrieren.setVisible(true);

	}

	protected void do_btn_login_actionPerformed(ActionEvent e) {
		if (rdbtnMitarbeiter.isSelected()) {

			switchSidePanel.removeAll();
			GridBagConstraints gbc_mMenue_Panel = new GridBagConstraints();
			gbc_mMenue_Panel.ipady = 10;
			gbc_mMenue_Panel.fill = GridBagConstraints.BOTH;
			switchSidePanel.setLayout(new BoxLayout(switchSidePanel, BoxLayout.Y_AXIS));
			switchSidePanel.add(new Mitarbeiter_Menue(switchSidePanel, shop), gbc_mMenue_Panel);
			switchSidePanel.validate();
		} 
	}
}
