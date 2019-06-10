package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.util.*;
import java.awt.event.KeyEvent;

public class JavaPanel extends JPanel {

	// declaring labels
	private JLabel classLabel;
	private JLabel superClassLabel;
	private JLabel methodLabel;

	// declaring text-fields
	private JTextField className;
	private JTextField superClassName;
	private JTextField methodName;

	// declaring buttons
	private JButton genBtn;

	public JavaPanel() {

		// labels
		classLabel = new JLabel("Class name : ");
		superClassLabel = new JLabel("Superclass name : ");
		methodLabel = new JLabel("Method name : ");

		// text fields
		className = new JTextField(10);
		superClassName = new JTextField(10);
		methodName = new JTextField(10);

		// generate button
		genBtn = new JButton("Generate");
		
		genBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> conf = new HashMap<>();
				conf.put("CLASSNAME", className.getText());
				conf.put("SUPER", superClassName.getText());
				conf.put("METHOD", methodName.getText());
				Parser tempParse = new Parser();
				try {
					tempParse.makeFromTemplate("java", conf);
				} catch(Exception err) {
					System.err.println(err);
				}
			}
		});

		// panel layout
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// border indentation
		Border innerBorder = BorderFactory.createTitledBorder("Java Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		/////////////// FIRST ROW//////////////////////////////////////
		gbc.weightx = 1;
		gbc.weighty = 0.3;

		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;

		// label
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(classLabel, gbc);
		gbc.insets = new Insets(3, 0, 0, 5);
		// text-field
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(className, gbc);

		/////////////// SECOND ROW//////////////////////////////////////

		gbc.weightx = 1;
		gbc.weighty = 0.3;

		// label
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(superClassLabel, gbc);
		gbc.insets = new Insets(3, 0, 0, 5);
		// text-field
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(superClassName, gbc);

		/////////////// THIRD ROW//////////////////////////////////////

		gbc.weightx = 1;
		gbc.weighty = 0.3;

		// label
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(methodLabel, gbc);
		gbc.insets = new Insets(3, 0, 0, 5);
		// text-field
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(methodName, gbc);

		////////////// FOURTH ROW/////////////////////////////////////////

		gbc.weightx = 1;
		gbc.weighty = 0.3;

		// Generate Button
		gbc.gridy = 3;
		gbc.gridx = 1;
		add(genBtn, gbc);
		genBtn.setForeground(new Color(255, 255, 255));
		genBtn.setBackground(new Color(247, 81, 81));
		genBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		genBtn.setMnemonic(KeyEvent.VK_G);
		genBtn.setPreferredSize(new Dimension(100, 50));
	}

}
