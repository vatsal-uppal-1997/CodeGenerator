package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ButtonPanel extends JPanel {

	// declaring labels
	private JLabel txtLabel;
	private JLabel angularLabel;
	private JLabel javaLabel;
	private JLabel dbLabel;
	private JLabel testLabel;

	// declaring buttons
	private JButton txtBtn;
	private JButton angularBtn;
	private JButton javaBtn;
	private JButton dbBtn;
	private JButton testBtn;

	public ButtonPanel() {

		// panel size
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);

		// labels
		txtLabel = new JLabel("Create a new text file : ");
		angularLabel = new JLabel("New angular project : ");
		javaLabel = new JLabel("New java application : ");
		dbLabel = new JLabel("New database table : ");
		testLabel = new JLabel("Create new testcases : ");

		// buttons
		txtBtn = new JButton("Text");
		angularBtn = new JButton("Angular");
		javaBtn = new JButton("Java");
		dbBtn = new JButton("Database");
		testBtn = new JButton("Testing");

		// border indentation
		Border innerBorder = BorderFactory.createTitledBorder("Code Generator");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// panel layout
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		//////////////// FIRST ROW///////////////////////

		// controls(for a given cell) how much space it takes
		// relative to the other cell
		gc.weightx = 1;
		gc.weighty = 0.5;
		// x is horizontal & y is vertical i.e. as you increase x you'll go from
		// left to right & as you increase y you'll go from top to bottom
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		// specifies the side of the cell the control sticks to
		gc.anchor = GridBagConstraints.LINE_END;
		add(txtLabel, gc);
		txtLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		//button
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtBtn, gc);
		txtBtn.setForeground(new Color(255, 255, 255));
		txtBtn.setBackground(new Color(255, 102, 0));
		txtBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtBtn.setMnemonic(KeyEvent.VK_T);

		txtBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			    Runtime rs = Runtime.getRuntime();
			    
			    try {
			      rs.exec("notepad");
			    }
			    catch (IOException ev) {
			      System.out.println(ev);
			    }
			  }
			
		});
		
		//////////////// SECOND ROW///////////////////////
		//label
		gc.gridy = 1;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.anchor = GridBagConstraints.LINE_END;
		add(angularLabel, gc);
		angularLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		//button
		gc.gridy = 1;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(angularBtn, gc);
		angularBtn.setForeground(new Color(255, 255, 255));
		angularBtn.setBackground(new Color(255, 102, 0));
		angularBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		angularBtn.setMnemonic(KeyEvent.VK_A);

		//////////////// THIRD ROW///////////////////////
		//label
		gc.gridy = 2;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.anchor = GridBagConstraints.LINE_END;
		add(javaLabel, gc);
		javaLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		//button
		gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(javaBtn, gc);
		javaBtn.setForeground(new Color(255, 255, 255));
		javaBtn.setBackground(new Color(255, 102, 0));
		javaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		javaBtn.setMnemonic(KeyEvent.VK_J);
		
		//////////////// FOURTH ROW///////////////////////
		//label
		gc.gridy = 3;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.anchor = GridBagConstraints.LINE_END;
		add(dbLabel, gc);
		dbLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		//button
		gc.gridy = 3;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(dbBtn, gc);
		dbBtn.setForeground(new Color(255, 255, 255));
		dbBtn.setBackground(new Color(255, 102, 0));
		dbBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		dbBtn.setMnemonic(KeyEvent.VK_D);

		//////////////// FIFTH ROW///////////////////////
		//label
		gc.gridy = 4;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.anchor = GridBagConstraints.LINE_END;
		add(testLabel, gc);
		testLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		//button
		gc.gridy = 4;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(testBtn, gc);
		testBtn.setForeground(new Color(255, 255, 255));
		testBtn.setBackground(new Color(255, 102, 0));
		testBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		testBtn.setMnemonic(KeyEvent.VK_E);
	}

}
