package lab7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class TextNote extends Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String content;
	
	public TextNote(String title) 
	{
		super(title);
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		
		String lineSeparator = System.getProperty("line.separator", "\n");
		File file = new File(absolutePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempContent = reader.readLine();
			while((tempContent)!=null) {
				tempContent = reader.readLine();
				result = result+tempContent+lineSeparator;
			}
			reader.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(reader != null) {
				try {
					reader.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		String fileTitle = this.getTitle();
		if(fileTitle.contains(" ")) {
			fileTitle = fileTitle.replace(" ","_");
		}
		try {
			File file = null;
			if(pathFolder != "")
				file = new File(pathFolder + File.separator + fileTitle + ".txt");
			else
				file = new File("." + File.separator + fileTitle + ".txt");
			if(!file.exists())
				file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
}
