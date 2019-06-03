package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

public class MainFrame extends JFrame {
	
	private JButton btn;
	private ButtonPanel buttonPanel;
	private JFileChooser fileChooser;
	private JTabbedPane tabbedPane;
	private JTextArea textArea;

	public MainFrame() {
		super("Quick Heal Technologies Ltd. ");

		setJMenuBar(createMenuBar());
		setLayout(new BorderLayout());

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileFilter1());
		
		tabbedPane= new JTabbedPane();
		btn = new JButton("Generate");
		buttonPanel = new ButtonPanel();
		textArea= new JTextArea();

		// ActionListener is an interface not a class
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("Please wait while your code gets generated...\n");
			}

		});
		
		add(tabbedPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);
		//add(textPanel, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		buttonPanel.setBackground(new Color(107, 165, 125));
		///////////////////////////////////TABBED PANE///////////////////////////
		 
	    tabbedPane.setBackground(Color.ORANGE);
	    tabbedPane.setForeground(Color.BLACK);
	    tabbedPane.addTab("Tab1", buttonPanel);
	    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	    tabbedPane.addTab("Tab2", textArea);
	    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	    
		////////////////////////GENERATE BUTTON//////////////////////////////////////
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(247, 81, 81));
		btn.setFont(new Font("SansSerif", Font.BOLD, 12));
		btn.setMnemonic(KeyEvent.VK_G);


		setMinimumSize(new Dimension(400, 400));
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	    //////////////////////////////MENU BAR//////////////////////////////////////////
	    private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		//menuBar.setBackground(new Color(166, 196, 244));
		menuBar.setFont(new Font("SansSerif", Font.BOLD, 12));

		//////////////// File Menu/////////////////////////////////////////
		JMenu fileMenu = new JMenu("File");
		
		// creating File menu items
		JMenuItem exportDataItem = new JMenuItem("Export Data");
		JMenuItem importDataItem = new JMenuItem("Import Data");
		JMenuItem exitItem = new JMenuItem("Exit");

		// adding items to the file menu
		fileMenu.add(exportDataItem);
		fileMenu.addSeparator(); // creates a nearly invisible horizontal line
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();	
		fileMenu.add(exitItem);

		//////////////////////////// Window Menu///////////////////////////
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem showPanelItem = new JCheckBoxMenuItem("Tabbed Pane");
		showPanelItem.setSelected(true);
		showMenu.add(showPanelItem);
		windowMenu.add(showMenu);

		// menu bar items
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		// show or hide the buttonPanel
		showPanelItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				tabbedPane.setVisible(menuItem.isSelected());
			}
		});
		// mnemonics for file menu
		fileMenu.setMnemonic(KeyEvent.VK_F);

		// import menuItem action
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
				;

			}
		});

		// export menuItem action
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				};
			}
		});
		
		// exit mnemonic
		exitItem.setMnemonic(KeyEvent.VK_X);
		// exit accelerator
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		return menuBar;
	}
}
