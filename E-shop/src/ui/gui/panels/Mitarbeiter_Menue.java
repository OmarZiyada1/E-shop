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

public class Mitarbeiter_Menue extends JPanel {
	private final JPanel panel_mitarbeiterMenueContainer = new JPanel();
	private final JPanel panel_hallo = new JPanel();
	private final JSeparator separator = new JSeparator();
	private final JPanel panel_mitarbeiterMenue = new JPanel();
	private final JPanel panel_menue = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_ma_child1 = new JPanel();
	private final JPanel panel_design_row1 = new JPanel();
	private final JLabel lbl_artikelnAnzeigen = new JLabel("Artikeln anzeigen");
	private final JLabel lbl_helloName = new JLabel("Hallo Joe");
	private JPanel switcherPanel;
	private E_Shop shop;

	/**
	 * Create the panel.
	 */
	public Mitarbeiter_Menue(JPanel sPanel, E_Shop shop) {
		this.shop = shop;
		this.switcherPanel = sPanel;
		initGUI();

	}

	private void initGUI() {
		setForeground(Color.DARK_GRAY);
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
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_mitarbeiterMenue.add(scrollPane, gbc_scrollPane);
		panel_menue.setForeground(Color.DARK_GRAY);
		panel_menue.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(panel_menue);
		GridBagLayout gbl_panel_menue = new GridBagLayout();
		gbl_panel_menue.columnWidths = new int[] { 149, 0 };
		gbl_panel_menue.rowHeights = new int[] { 20, 0 };
		gbl_panel_menue.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_menue.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_menue.setLayout(gbl_panel_menue);
		panel_ma_child1.setBackground(Color.DARK_GRAY);

		// children

		for (int i = 0; i < shop.mitarbeiterMenue().size(); i++) {
			

			GridBagConstraints gbc_panel_ma_child1 = new GridBagConstraints();
			gbc_panel_ma_child1.fill = GridBagConstraints.BOTH;
			panel_menue.add(panel_ma_child1, gbc_panel_ma_child1);
			GridBagLayout gbl_panel_ma_child1 = new GridBagLayout();
			gbl_panel_ma_child1.columnWidths = new int[] { 14, 45, 0 };
			gbl_panel_ma_child1.rowHeights = new int[] { 13, 0 };
			gbl_panel_ma_child1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel_ma_child1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
			panel_ma_child1.setLayout(gbl_panel_ma_child1);
		}

		GridBagConstraints gbc_panel_design_row1 = new GridBagConstraints();
		gbc_panel_design_row1.fill = GridBagConstraints.BOTH;
		gbc_panel_design_row1.insets = new Insets(0, 0, 0, 5);
		panel_design_row1.setForeground(Color.ORANGE);
		panel_design_row1.setBorder(null);
		panel_design_row1.setBackground(Color.ORANGE);
		panel_ma_child1.add(panel_design_row1, gbc_panel_design_row1);
		GridBagLayout gbl_panel_design_row1 = new GridBagLayout();
		gbl_panel_design_row1.columnWidths = new int[] { 0 };
		gbl_panel_design_row1.rowHeights = new int[] { 0 };
		gbl_panel_design_row1.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel_design_row1.rowWeights = new double[] { Double.MIN_VALUE };
		panel_design_row1.setLayout(gbl_panel_design_row1);

		GridBagConstraints gbc_lbl_artikelnAnzeigen = new GridBagConstraints();
		gbc_lbl_artikelnAnzeigen.fill = GridBagConstraints.BOTH;
		lbl_artikelnAnzeigen.setForeground(Color.WHITE);
		lbl_artikelnAnzeigen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_ma_child1.add(lbl_artikelnAnzeigen, gbc_lbl_artikelnAnzeigen);
	}

}
