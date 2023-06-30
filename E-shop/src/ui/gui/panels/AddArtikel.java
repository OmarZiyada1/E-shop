package ui.gui.panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import domain.E_Shop;
import entities.Artikel;
public class AddArtikel extends JPanel{
	public AddArtikel() {
	}
	
	
	public interface AddArticleListener {
		public void onArticleAdded(Artikel artikel);
	}
	
	private E_Shop sh = null;
	private AddArticleListener addBookListener = null;

	private JButton hinzufuegenButton;
	private JTextField nameTextFeld = null;
	private JTextField beschreibungTextFeld = null;
	private JTextField preis = null;
	private JTextField bestand = null;
	
	

}
