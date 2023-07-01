package ui.gui.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import domain.E_Shop;
import entities.Nutzer;
import gui.Index_Gui;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LogoutPanel extends JPanel {
	private JButton btn_Logout;
	private JPanel panel_header_nav;
	private JPanel mainPanel;
	private JPanel switchSidePanel;
	private E_Shop shop;
	private Nutzer nutzer;

	//

	/**
	 * Create the panel.
	 */
	public LogoutPanel(JPanel panel_header_nav, JPanel mainPanel, JPanel switchSidePanel, Nutzer nutzer, E_Shop shop) {
		this.panel_header_nav = panel_header_nav;
		this.mainPanel = mainPanel;
		this.switchSidePanel = switchSidePanel;
		this.shop = shop;
		this.nutzer = nutzer;
		initGUI();
	}

	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		{
			this.btn_Logout = new JButton("Logout");
			this.btn_Logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btn_Logout_actionPerformed(e);
				}
			});
			add(this.btn_Logout);
		}
	}

	protected void do_btn_Logout_actionPerformed(ActionEvent e) {
	
	
		// shop.loggeMitarbeiterAus(loggedMitarbeiter);
		try {
			JOptionPane.showMessageDialog(null, "Logout Erfolgreich. Ihe �nderungen wurden gespeichert", "Info Message",
					JOptionPane.INFORMATION_MESSAGE);
			mainPanel.removeAll();
			switchSidePanel.removeAll();
			panel_header_nav.removeAll();
			
			addLoginPanel();
			addBegruessungsPanel();
			shop.schreibeArtikel();
			shop.schreibeMitarbeiter();
			shop.schreibeVerlauf();
			shop.schreibeKunde();
			mainPanel.validate();
			switchSidePanel.validate();
			panel_header_nav.validate();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void addLoginPanel() {
		mainPanel.add(new Login_Panel(mainPanel, switchSidePanel, panel_header_nav, shop), BorderLayout.CENTER);
		mainPanel.validate();
	}

	private void addBegruessungsPanel() {
		mainPanel.add(new BegruessungPanel(panel_header_nav, mainPanel, switchSidePanel, shop) , BorderLayout.WEST);
	mainPanel.validate();
	}
	

}
