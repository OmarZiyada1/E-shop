package ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.gui.panels.BegruessungPanel;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

public class E_Shop_Gui extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_JF_header = new JPanel();
	private final JPanel panel_JF_sideNavbar = new JPanel();
	private final JPanel panel_JF_main = new JPanel();
	private final JPanel panel_JF_footer = new JPanel();
	private final JPanel panel_logo = new JPanel();
	private final JPanel panel_headerRecht = new JPanel();
	private final JLabel lbl_logoimg = new JLabel("img");
	private final JLabel lbl_logoTitle = new JLabel("E-Shop");
	
	//panels
	private JPanel begruessungPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_Shop_Gui frame = new E_Shop_Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public E_Shop_Gui() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(panel_JF_header, BorderLayout.NORTH);
		panel_JF_header.setLayout(new BoxLayout(panel_JF_header, BoxLayout.X_AXIS));
		
		panel_JF_header.add(panel_logo);
		GridBagLayout gbl_panel_logo = new GridBagLayout();
		gbl_panel_logo.columnWidths = new int[]{0, 0, 0};
		gbl_panel_logo.rowHeights = new int[]{35, 0};
		gbl_panel_logo.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_logo.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_logo.setLayout(gbl_panel_logo);
		
		GridBagConstraints gbc_lbl_logoimg = new GridBagConstraints();
		gbc_lbl_logoimg.anchor = GridBagConstraints.WEST;
		gbc_lbl_logoimg.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_logoimg.gridx = 0;
		gbc_lbl_logoimg.gridy = 0;
		panel_logo.add(lbl_logoimg, gbc_lbl_logoimg);
		
		GridBagConstraints gbc_lbl_logoTitle = new GridBagConstraints();
		gbc_lbl_logoTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_logoTitle.gridx = 1;
		gbc_lbl_logoTitle.gridy = 0;
		panel_logo.add(lbl_logoTitle, gbc_lbl_logoTitle);
		
		panel_JF_header.add(panel_headerRecht);
		
		contentPane.add(panel_JF_sideNavbar, BorderLayout.WEST);
		
		// Entfernen des aktuellen Panels
		panel_JF_sideNavbar.removeAll();
				
		// Erstellen und Hinzufügen des neuen Panels
		begruessungPanel = new BegruessungPanel();
		begruessungPanel.setBounds(0, 0, 600, 400);
		panel_JF_sideNavbar.add(begruessungPanel);
		panel_JF_sideNavbar.repaint();
		
		contentPane.add(panel_JF_main, BorderLayout.EAST);
		
		contentPane.add(panel_JF_footer, BorderLayout.SOUTH);
	}

}
