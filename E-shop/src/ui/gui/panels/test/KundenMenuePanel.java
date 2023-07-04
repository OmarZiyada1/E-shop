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
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		{
			this.btn_ArtikelAnzeigen = new JButton("Artikeln anzeigen");
			add(this.btn_ArtikelAnzeigen);
		}
		{
			this.btn_Warenkorb = new JButton("Warenkorb");
			add(this.btn_Warenkorb);
		}
	}

}
