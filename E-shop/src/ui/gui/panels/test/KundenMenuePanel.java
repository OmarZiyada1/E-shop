package ui.gui.panels.test;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

public class KundenMenuePanel extends JPanel {
	private JButton btn_ArtikelAnzeigen;
	private JButton btn_Warenkorb;

	/**
	 * Create the panel.
	 */
	public KundenMenuePanel() {

		initGUI();
	}
	private void initGUI() {
		setBorder(new TitledBorder(null, "Kunde Menue", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{111, 0};
		gridBagLayout.rowHeights = new int[]{21, 21, 0, 21, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		{
			this.btn_ArtikelAnzeigen = new JButton("Artikeln anzeigen");
			GridBagConstraints gbc_btn_ArtikelAnzeigen = new GridBagConstraints();
			gbc_btn_ArtikelAnzeigen.fill = GridBagConstraints.HORIZONTAL;
			gbc_btn_ArtikelAnzeigen.insets = new Insets(0, 0, 5, 0);
			gbc_btn_ArtikelAnzeigen.gridx = 0;
			gbc_btn_ArtikelAnzeigen.gridy = 0;
			add(this.btn_ArtikelAnzeigen, gbc_btn_ArtikelAnzeigen);
		}
		{
			this.btn_Warenkorb = new JButton("Warenkorb");
			GridBagConstraints gbc_btn_Warenkorb = new GridBagConstraints();
			gbc_btn_Warenkorb.fill = GridBagConstraints.HORIZONTAL;
			gbc_btn_Warenkorb.insets = new Insets(0, 0, 5, 0);
			gbc_btn_Warenkorb.gridx = 0;
			gbc_btn_Warenkorb.gridy = 1;
			add(this.btn_Warenkorb, gbc_btn_Warenkorb);
		}
	}

}
