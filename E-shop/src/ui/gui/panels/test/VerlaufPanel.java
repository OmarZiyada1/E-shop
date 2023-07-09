package ui.gui.panels.test;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import domain.E_Shop;

public class VerlaufPanel extends JPanel {
	private JScrollBar scrollBar;
	private JPanel panel;
	private E_Shop shop;

	/**
	 * Create the panel.
	 */
	public VerlaufPanel(E_Shop shop) {
		this.shop=shop;
		initGUI();
	}
	private void initGUI() {
		setLayout(null);
		
		this.panel = new JPanel();
		this.panel.setBounds(211, 5, 27, 58);
		add(this.panel);
		this.panel.setLayout(null);
		
		this.scrollBar = new JScrollBar();
		this.scrollBar.setBounds(5, 5, 17, 48);
		this.panel.add(this.scrollBar);
	}
	
	

}
