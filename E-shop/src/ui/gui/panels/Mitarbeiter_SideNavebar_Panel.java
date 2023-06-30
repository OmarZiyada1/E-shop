package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import domain.E_Shop;
import gui.Index_Gui;

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class Mitarbeiter_SideNavebar_Panel extends JPanel {
	private final JPanel panel_mitarbeiterMenueContainer = new JPanel();
	private final JPanel panel_hallo = new JPanel();
	private final JSeparator separator = new JSeparator();
	private final JPanel panel_mitarbeiterMenue = new JPanel();
	private final JPanel panel_menue = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_ma_child1 = new JPanel();
	private final JPanel panel_design_row1 = new JPanel();
	private  JLabel lbl_artikelnAnzeigen ;
	private final JLabel lbl_helloName = new JLabel("Hallo Joe");
	private JPanel switcherPanel;
	private E_Shop shop;
	private final JButton btn_artilkelnAnzeigen = new JButton("Artikeln Anzeigen");
	private final JButton btn_loeschen = new JButton("Artikel loeschen");
	private final JButton btn_einfuegen = new JButton("Artikel einfuegen");
	private final JButton btn_suchen = new JButton("Artikel suchen");
	private final JButton btn_regestrieren = new JButton("Mitarbeiter regestrieren");
	private final JButton btn_MitarbeiterlisteAnzeigen = new JButton("Mitarbeiterliste anzeigen");
	private final JButton btn_ArtikelbestandErhoehen = new JButton("Artikelbestand erhoehen");
	private final JButton btn_ArtikelbestandSenken = new JButton("Artikelbestand senken");
	private final JButton btn_ZeigeVerlauf = new JButton("Zeige Verlauf");
	private final JButton btn_30TageVerlauf = new JButton("30Tage Verlauf");

	/**
	 * Create the panel.
	 */
	public Mitarbeiter_SideNavebar_Panel(JPanel sPanel, E_Shop shop) {
		this.shop = shop;
		this.switcherPanel = sPanel;
		initGUI();

	}

	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 0 };
		gridBagLayout.rowHeights = new int[] { 62, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panel_mitarbeiterMenueContainer = new GridBagConstraints();
		gbc_panel_mitarbeiterMenueContainer.ipady = 10;
		gbc_panel_mitarbeiterMenueContainer.fill = GridBagConstraints.BOTH;
		panel_mitarbeiterMenueContainer.setBackground(Color.DARK_GRAY);
		add(panel_mitarbeiterMenueContainer, gbc_panel_mitarbeiterMenueContainer);
		panel_mitarbeiterMenueContainer.setLayout(new BoxLayout(panel_mitarbeiterMenueContainer, BoxLayout.Y_AXIS));
		FlowLayout flowLayout = (FlowLayout) panel_hallo.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_hallo.setBorder(null);
		panel_hallo.setBackground(Color.DARK_GRAY);
		panel_mitarbeiterMenueContainer.add(panel_hallo);
		lbl_helloName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_helloName.setForeground(Color.ORANGE);

		panel_hallo.add(lbl_helloName);
		separator.setBorder(null);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.ORANGE);
		panel_mitarbeiterMenueContainer.add(separator);
		panel_mitarbeiterMenue.setForeground(Color.DARK_GRAY);
		panel_mitarbeiterMenue.setBorder(null);
		panel_mitarbeiterMenue.setBackground(Color.DARK_GRAY);
		panel_mitarbeiterMenueContainer.add(panel_mitarbeiterMenue);
		GridBagLayout gbl_panel_mitarbeiterMenue = new GridBagLayout();
		gbl_panel_mitarbeiterMenue.columnWidths = new int[] { 135, 0 };
		gbl_panel_mitarbeiterMenue.rowHeights = new int[] { 12, 0 };
		gbl_panel_mitarbeiterMenue.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_mitarbeiterMenue.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_mitarbeiterMenue.setLayout(gbl_panel_mitarbeiterMenue);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_mitarbeiterMenue.add(scrollPane, gbc_scrollPane);
		panel_menue.setForeground(Color.DARK_GRAY);
		panel_menue.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(panel_menue);
		GridBagLayout gbl_panel_menue = new GridBagLayout();
		gbl_panel_menue.columnWidths = new int[] { 149, 0 };
		gbl_panel_menue.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_menue.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_menue.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_menue.setLayout(gbl_panel_menue);
		
		GridBagConstraints gbc_btn_artilkelnAnzeigen = new GridBagConstraints();
		gbc_btn_artilkelnAnzeigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_artilkelnAnzeigen.insets = new Insets(0, 0, 5, 0);
		gbc_btn_artilkelnAnzeigen.gridx = 0;
		gbc_btn_artilkelnAnzeigen.gridy = 0;
		panel_menue.add(btn_artilkelnAnzeigen, gbc_btn_artilkelnAnzeigen);
		
		GridBagConstraints gbc_btn_loeschen = new GridBagConstraints();
		gbc_btn_loeschen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_loeschen.insets = new Insets(0, 0, 5, 0);
		gbc_btn_loeschen.gridx = 0;
		gbc_btn_loeschen.gridy = 1;
		panel_menue.add(btn_loeschen, gbc_btn_loeschen);
		
		GridBagConstraints gbc_btn_einfuegen = new GridBagConstraints();
		gbc_btn_einfuegen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_einfuegen.insets = new Insets(0, 0, 5, 0);
		gbc_btn_einfuegen.gridx = 0;
		gbc_btn_einfuegen.gridy = 2;
		panel_menue.add(btn_einfuegen, gbc_btn_einfuegen);
		
		GridBagConstraints gbc_btn_suchen = new GridBagConstraints();
		gbc_btn_suchen.insets = new Insets(0, 0, 5, 0);
		gbc_btn_suchen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_suchen.gridx = 0;
		gbc_btn_suchen.gridy = 3;
		panel_menue.add(btn_suchen, gbc_btn_suchen);
		
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 4;
		panel_menue.add(btn_regestrieren, gbc_btnNewButton_4);
		
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 0;
		gbc_btnNewButton_5.gridy = 5;
		panel_menue.add(btn_MitarbeiterlisteAnzeigen, gbc_btnNewButton_5);
		
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6.gridx = 0;
		gbc_btnNewButton_6.gridy = 6;
		panel_menue.add(btn_ArtikelbestandErhoehen, gbc_btnNewButton_6);
		
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 0;
		gbc_btnNewButton_7.gridy = 7;
		panel_menue.add(btn_ArtikelbestandSenken, gbc_btnNewButton_7);
		
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 0;
		gbc_btnNewButton_8.gridy = 8;
		panel_menue.add(btn_ZeigeVerlauf, gbc_btnNewButton_8);
		
		GridBagConstraints gbc_btn_ = new GridBagConstraints();
		gbc_btn_.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_.gridx = 0;
		gbc_btn_.gridy = 9;
		panel_menue.add(btn_30TageVerlauf, gbc_btn_);
		panel_ma_child1.setBackground(Color.DARK_GRAY);


	}

}
