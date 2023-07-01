package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;

public class Suchen_MainPanel extends JPanel {
	private JTextField textField_Suchen;
	private JButton btn_Suchen;

	/**
	 * Create the panel.
	 */
	public Suchen_MainPanel() {

		initGUI();
	}
	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{186, 67, 0};
		gridBagLayout.rowHeights = new int[]{21, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.textField_Suchen = new JTextField();
		GridBagConstraints gbc_textField_Suchen = new GridBagConstraints();
		gbc_textField_Suchen.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Suchen.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Suchen.gridx = 0;
		gbc_textField_Suchen.gridy = 0;
		add(this.textField_Suchen, gbc_textField_Suchen);
		this.textField_Suchen.setColumns(20);
		
		this.btn_Suchen = new JButton("Suchen");
		GridBagConstraints gbc_btn_Suchen = new GridBagConstraints();
		gbc_btn_Suchen.anchor = GridBagConstraints.WEST;
		gbc_btn_Suchen.gridx = 1;
		gbc_btn_Suchen.gridy = 0;
		add(this.btn_Suchen, gbc_btn_Suchen);
	}

}
