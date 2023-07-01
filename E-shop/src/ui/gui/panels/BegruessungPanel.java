package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextPane;

import domain.E_Shop;

import java.awt.Font;

public class BegruessungPanel extends JPanel {
	private JPanel panel_iconConatainer;
	private JSeparator separator;
	private JPanel panel_begruessungNachrichtContainer;
	private JLabel lbl_logo;
	private JTextPane txtpnSchnDassSie;

	//
	private JPanel panel_header_nav;
	private JPanel mainPanel;
	private JPanel switchSidePanel;
	private E_Shop shop;

	/**
	 * Create the panel.
	 */
	public BegruessungPanel(JPanel panel_header_nav, JPanel mainPanel, JPanel switchSidePanel, E_Shop shop) {
		this.panel_header_nav = panel_header_nav;
		this.mainPanel = mainPanel;
		this.switchSidePanel = switchSidePanel;
		this.shop = shop;
		initGUI();
	}

	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 98, 33, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		{
			this.panel_iconConatainer = new JPanel();
			GridBagConstraints gbc_panel_iconConatainer = new GridBagConstraints();
			gbc_panel_iconConatainer.insets = new Insets(0, 0, 5, 0);
			gbc_panel_iconConatainer.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_iconConatainer.gridx = 0;
			gbc_panel_iconConatainer.gridy = 0;
			add(this.panel_iconConatainer, gbc_panel_iconConatainer);
			{
				this.lbl_logo = new JLabel("logo");
				this.panel_iconConatainer.add(this.lbl_logo);
			}
		}
		{
			this.separator = new JSeparator();
			this.separator.setBackground(Color.ORANGE);
			this.separator.setForeground(Color.ORANGE);
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.insets = new Insets(0, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 1;
			add(this.separator, gbc_separator);
		}
		{
			this.panel_begruessungNachrichtContainer = new JPanel();
			this.panel_begruessungNachrichtContainer.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_panel_begruessungNachrichtContainer = new GridBagConstraints();
			gbc_panel_begruessungNachrichtContainer.fill = GridBagConstraints.BOTH;
			gbc_panel_begruessungNachrichtContainer.gridx = 0;
			gbc_panel_begruessungNachrichtContainer.gridy = 2;
			add(this.panel_begruessungNachrichtContainer, gbc_panel_begruessungNachrichtContainer);
			{
				this.txtpnSchnDassSie = new JTextPane();
				this.txtpnSchnDassSie.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 18));
				this.txtpnSchnDassSie.setForeground(Color.WHITE);
				this.txtpnSchnDassSie.setText(
						"Schön, dass Sie unseren E-Shop\r\nbesuchen! Tauchen Sie ein in eine Welt \r\nvoller spannender Produkte und \r\nprofitieren Sie von unseren \r\nattraktivenAngeboten. \r\nViel Spaß beim Stöbern!");
				this.txtpnSchnDassSie.setBackground(Color.DARK_GRAY);
				this.panel_begruessungNachrichtContainer.add(this.txtpnSchnDassSie);
			}
		}
	}

}
