package ui.gui.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArtikelVerwaltungsPanel extends JPanel {
	private JButton btn_loeschen;
	private JButton btn_einfuegen;
	private JButton btn_erhoehen;
	private JButton btn_senken;
	private TablePanel tablePanel;
	
	/**
	 * Create the panel.
	 */
	public ArtikelVerwaltungsPanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
		initGUI();
	}
	private void initGUI() {
		{
			this.btn_loeschen = new JButton("Löschen");
			this.btn_loeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btn_loeschen_actionPerformed(e);
				}
			});
			add(this.btn_loeschen);
		}
		{
			this.btn_einfuegen = new JButton("Einfuegen");
			add(this.btn_einfuegen);
		}
		{
			this.btn_erhoehen = new JButton("Erhoehen");
			add(this.btn_erhoehen);
		}
		{
			this.btn_senken = new JButton("Senken");
			add(this.btn_senken);
		}
	}

	protected void do_btn_loeschen_actionPerformed(ActionEvent e) {
		tablePanel.selectedrow();
	}
}
