package gui;

import java.io.*;
import java.util.*;

public class Parser {
	
	public BufferedReader reader = null;

	public String validate(String className) {
		StringBuffer sb = new StringBuffer();
		for (String i: className.split("\\s+")) {
			sb.append(i.substring(0, 1).toUpperCase() 
				+ i.substring(1));
		}
		return sb.toString();
	}
	
	public String parseTemplate(String templateName, Map<String, String> configeration) throws Exception {
		File template = new File("./templates/"+templateName+".template");
		if (!template.exists()) {
			template = new File("./templates");
			template.mkdir();
			System.err.println("[-] COULD NOT READ TEMPLATE ! MAKE SURE THE TEMPLATES FOLDER EXISTS WITH THE .TEMPLATE FILE IN IT");
			throw new Exception("[-] COULD NOT READ TEMPLATE ! MAKE SURE THE TEMPLATES FOLDER EXISTS WITH THE .TEMPLATE FILE IN IT");
		}
		
		try {
			reader = new BufferedReader(new FileReader(template));
			String line;
			StringBuffer sb = new StringBuffer();
			while ( (line = reader.readLine()) != null ) {
				String[] parseLine = line.split("[@]+");
				if (parseLine.length == 1) {
					sb.append(line);
				} else {
					for (String i: parseLine) {
						int len = i.length();
						if (i.charAt(len - 1) == '#')
							if (configeration.containsKey(i.substring(0, len-1)))
								sb.append(configeration.get(i.substring(0, len-1)));
							else {
								System.err.println("[-] ERROR PARSING THE TEMPLATE: CONFIGRATION MISMATCH");
								throw new Exception("[-] ERROR PARSING THE TEMPLATE: CONFIGRATION MISMATCH");
							}
						else
							sb.append(i);
					}
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return sb.toString();
		} catch (IOException e) {
			System.err.println("[-] AN ERROR OCCURED WHILE READING THE TEMPLATE FILE: \n"+e);
			throw e;
		}
	}	

	public void makeFromTemplate(String template, Map<String, String> configeration) throws Exception {
		try {

			String  result = parseTemplate(template, configeration);
			File output = new File("./out");
			if (!output.exists()) 
				output.mkdir();
			output = new File("./out/"+configeration.get("CLASSNAME")+".java");
			output.createNewFile();
			PrintWriter out = new PrintWriter(output);
			out.println(result);
			out.close();
		} catch (IOException e) {
            System.err.println("[-] ERROR OCCURED WHILE CREATING THE TEMPLATE FILE: \n"+e);
            throw e;
		}
	}

	// public static void main(String[] args) {

	// 	JFrame f = new JFrame("Simple Java Template Generator");
	// 	final JTextField tf = new JTextField();
	// 	tf.setBounds(50, 50, 150, 20);
	// 	JButton b = new JButton("Click Here");
	// 	b.setBounds(50, 100, 95, 30);
	// 	b.addActionListener(new ActionListener() {
	// 		public void actionPerformed(ActionEvent e) {
	// 			String className = tf.getText();
	// 			className = validate(className);
	// 			Map<String, String> conf = new HashMap<>();
	// 			conf.put("CLASSNAME", className);
	// 			makeFromTemplate("Basic", conf);
	// 		}
	// 	});
	// 	f.add(b);
	// 	f.add(tf);
	// 	f.setSize(400, 400);
	// 	f.setLayout(null);
	// 	f.setVisible(true);
	// }
}

