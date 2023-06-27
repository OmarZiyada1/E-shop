package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.gui.panels.Login_Panel;


import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JSeparator;

import javax.swing.JTextArea;
import java.awt.Font;


public class Index_Gui extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_header = new JPanel();
	private final JPanel panel_logoContainer = new JPanel();
	private final JPanel panel_header_nav = new JPanel();
	private final JLabel lbl_logoImg = new JLabel("img");
	private final JLabel lblNewLabel_1 = new JLabel("SAZ Shop");
	private final JPanel panel_sideNavbar = new JPanel();
	private final JPanel panel_logoImgSidenavbar = new JPanel();
	private final JSeparator separator = new JSeparator();
	private final JPanel panel_begrüßungNachricht = new JPanel();
	private final JLabel lblNewLabel = new JLabel("img");
	private final JTextArea txtarea_begruessungNachricht = new JTextArea();
	public final JPanel panel_main = new JPanel();
	
	//panels
	private Login_Panel login_Panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index_Gui frame = new Index_Gui();
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
	public Index_Gui() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		panel_header.setBackground(Color.DARK_GRAY);
		
		contentPane.add(panel_header, BorderLayout.NORTH);
		GridBagLayout gbl_panel_header = new GridBagLayout();
		gbl_panel_header.columnWidths = new int[]{126, 0, 0};
		gbl_panel_header.rowHeights = new int[]{51, 0};
		gbl_panel_header.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_header.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_header.setLayout(gbl_panel_header);
		
		GridBagConstraints gbc_panel_logoContainer = new GridBagConstraints();
		gbc_panel_logoContainer.insets = new Insets(0, 0, 0, 5);
		gbc_panel_logoContainer.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_logoContainer.gridx = 0;
		gbc_panel_logoContainer.gridy = 0;
		FlowLayout flowLayout = (FlowLayout) panel_logoContainer.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_logoContainer.setBackground(Color.DARK_GRAY);
		panel_header.add(panel_logoContainer, gbc_panel_logoContainer);
		
		panel_logoContainer.add(lbl_logoImg);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.ORANGE);
		
		panel_logoContainer.add(lblNewLabel_1);
		
		GridBagConstraints gbc_panel_header_nav = new GridBagConstraints();
		gbc_panel_header_nav.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_header_nav.gridx = 1;
		gbc_panel_header_nav.gridy = 0;
		FlowLayout flowLayout_1 = (FlowLayout) panel_header_nav.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_header_nav.setBackground(Color.DARK_GRAY);
		panel_header.add(panel_header_nav, gbc_panel_header_nav);
		panel_sideNavbar.setBackground(Color.DARK_GRAY);
		
		contentPane.add(panel_sideNavbar, BorderLayout.WEST);
		GridBagLayout gbl_panel_sideNavbar = new GridBagLayout();
		gbl_panel_sideNavbar.columnWidths = new int[]{122, 0};
		gbl_panel_sideNavbar.rowHeights = new int[]{93, 34, 0, 0};
		gbl_panel_sideNavbar.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_sideNavbar.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_sideNavbar.setLayout(gbl_panel_sideNavbar);
		
		GridBagConstraints gbc_panel_logoImgSidenavbar = new GridBagConstraints();
		gbc_panel_logoImgSidenavbar.insets = new Insets(0, 0, 5, 0);
		gbc_panel_logoImgSidenavbar.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_logoImgSidenavbar.gridx = 0;
		gbc_panel_logoImgSidenavbar.gridy = 0;
		panel_sideNavbar.add(panel_logoImgSidenavbar, gbc_panel_logoImgSidenavbar);
		
		panel_logoImgSidenavbar.add(lblNewLabel);
		
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		panel_sideNavbar.add(separator, gbc_separator);
		
		GridBagConstraints gbc_panel_begrüßungNachricht = new GridBagConstraints();
		gbc_panel_begrüßungNachricht.fill = GridBagConstraints.BOTH;
		gbc_panel_begrüßungNachricht.gridx = 0;
		gbc_panel_begrüßungNachricht.gridy = 2;
		panel_sideNavbar.add(panel_begrüßungNachricht, gbc_panel_begrüßungNachricht);
		GridBagLayout gbl_panel_begrüßungNachricht = new GridBagLayout();
		gbl_panel_begrüßungNachricht.columnWidths = new int[]{0, 0};
		gbl_panel_begrüßungNachricht.rowHeights = new int[]{0, 0};
		gbl_panel_begrüßungNachricht.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_begrüßungNachricht.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_begrüßungNachricht.setLayout(gbl_panel_begrüßungNachricht);
		
		GridBagConstraints gbc_txtarea_begruessungNachricht = new GridBagConstraints();
		gbc_txtarea_begruessungNachricht.fill = GridBagConstraints.BOTH;
		gbc_txtarea_begruessungNachricht.gridx = 0;
		gbc_txtarea_begruessungNachricht.gridy = 0;
		txtarea_begruessungNachricht.setForeground(Color.WHITE);
		txtarea_begruessungNachricht.setBackground(Color.DARK_GRAY);
		txtarea_begruessungNachricht.setEditable(false);
		txtarea_begruessungNachricht.setWrapStyleWord(true);
		txtarea_begruessungNachricht.setText("New label New label\r\nNew label New labelNew label\r\nNew label");
		
		panel_begrüßungNachricht.add(txtarea_begruessungNachricht, gbc_txtarea_begruessungNachricht);
		panel_main.setBackground(new Color(255, 128, 0));
		
		contentPane.add(panel_main, BorderLayout.CENTER);
		panel_main.removeAll();
		GridBagLayout gbl_panel_main = new GridBagLayout();
		gbl_panel_main.columnWidths = new int[]{159, 0};
		gbl_panel_main.rowHeights = new int[]{122, 0};
		gbl_panel_main.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_main.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_main.setLayout(gbl_panel_main);
		login_Panel= new Login_Panel(panel_main);
		GridBagConstraints gbc_login_Panel = new GridBagConstraints();
		gbc_login_Panel.fill = GridBagConstraints.BOTH;
		panel_main.add(login_Panel, gbc_login_Panel);
		panel_main.validate();
		
		
		
	}
}
