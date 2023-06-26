package ui.gui.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Component;

public class BegruessungPanel extends JPanel {
	private final JLabel lbl_img = new JLabel("img");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblNewLabel = new JLabel("welcome to our shop \r\nlorem lorem lorem lorem \r\nlorem lorem lorem lorem \r\nlorem lorem ");

	/**
	 * Create the panel.
	 */
	public BegruessungPanel() {

		initGUI();
	}
	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		lbl_img.setForeground(Color.WHITE);
		lbl_img.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_img.setBounds(86, 88, 263, 13);
		
		add(lbl_img);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(86, 191, 263, 9);
		
		add(separator);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(86, 230, 263, 133);
		
		add(lblNewLabel);
	}
}
