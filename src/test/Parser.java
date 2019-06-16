import java.io.*;
import java.util.*;
import java.util.regex.*;


class TemplateError extends RuntimeException {


	public TemplateError(String s, Throwable e) {
		super(s, e);
	}

	public TemplateError(String s) {
		super(s);
	}

}





public class Parser {


	private BufferedReader reader;
	private StringBuffer parsedContent;
	private String fileContent;
	private String templateName;
	private File templateDir;
	private File templateFile;
	private Map<String, String> conf;

	public Parser(String templateName, Map<String, String> conf) {
		
		this.templateName = templateName;
		this.templateDir = new File("./templates/");

		// CHECKS -------------------------------------
		if (!templateDir.exists()) {
			templateDir.mkdir();
			throw new TemplateError("The template directory did not exist ! Created one but the execution cannot continue.");
		} 
		this.templateFile = new File("./templates/"+this.templateName+".template");
		if (!templateFile.exists()) {
			throw new TemplateError("The template file specified does not exist !");
		}
		// CHECKS xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		
		// IO ------------------------------------------	
		try {
			this.reader = new BufferedReader(new FileReader(templateFile));
		} catch (Exception e) {
			System.err.println(e);
		}
		StringBuffer temp = new StringBuffer();
		String line;
		try {
			while ( (line = this.reader.readLine()) != null ) 
				temp.append(line+"\n");
		} catch (IOException e) {
			System.err.println(e);
		}
		// IO xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		
		this.fileContent = temp.toString();
		this.conf = conf;
	}
	
	private Matcher getMatcher(String txt) {
		String patternStr = "@[A-z]+#@";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(txt);
		return matcher;
	}

	private void roughParse() {
		
		Matcher matcher = getMatcher(this.fileContent);
		StringBuffer sb = new StringBuffer(this.fileContent);

		while (matcher.find()) {
			int startIdx = matcher.start();
			int endIdx = matcher.end();
			String key = this.fileContent.substring(startIdx + 1, endIdx - 2);
			String value = this.conf.get(key);
			if (value.indexOf(",") != -1)
				continue;
			sb.replace(startIdx, endIdx, value);
		}	

		this.fileContent = sb.toString();
	}

	private void blockParse() {
	
		String[] arr = this.fileContent.split("[#@]+[{}\\n\\r]+");
		for (String block: arr) {
			Matcher matcher = getMatcher(block);
			if (!matcher.matches()) {
				this.parsedContent.append(block);
				continue;
			}
			int startIdx = matcher.start();
			int endIdx = matcher.end();
			String key = block.substring(startIdx + 1, endIdx - 2);
			String[] values = this.conf.get(key).split("\\s*,\\s*");
			StringBuffer temp = new StringBuffer(block);
			for (String value: values) {
				temp.replace(startIdx, endIdx, value);
				this.parsedContent.append(temp.toString());
			}
		}	
		
	}

	public String parse() {
	
		roughParse();
		blockParse();	
		return this.parsedContent.toString();
	
	}

	public void write() {
		
		try {
			
			File outputDir = new File("./out");
			if (!outputDir.exists())
				outputDir.mkdir();
			File output = new File("./out/"+this.conf.get("CLASSNAME")+".java");
			output.createNewFile();
			PrintWriter out = new PrintWriter(output);
			out.println(this.parsedContent.toString());
			out.close();
		} catch (IOException e) {
			throw new TemplateError("Error while writing output file", e);
		}
		
	}

}
