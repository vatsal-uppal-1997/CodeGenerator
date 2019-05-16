package gui;

public class Utils {
	public static String getFileExtension(String name) {
		
		int pointIndex= name.lastIndexOf(".");
		
		//if lastIndex returns -1 it means there is no "."
		if(pointIndex == -1) {
			return null;
		}
		//if the "." is present at the end then the file has no extension
		if(pointIndex == name.length()-1) {
			return null;
		}
		return name.substring(pointIndex, name.length());
	}

}
