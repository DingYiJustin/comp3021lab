package lab7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>(0);
	}
	
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook)in.readObject();
			this.folders = n.folders;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public void sortFolders() {
		for(Folder folder: folders) {
			folder.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> returnValue = new ArrayList<Note>();
		for(Folder folder: folders) {
			returnValue.addAll(folder.searchNotes(keywords));
			
		}
		return returnValue;
	}
	
	public boolean insertNote(String folderName, Note note) {
		boolean checkIfExist = false;
		for(Folder folder : folders) {
			if(folder.equals(new Folder(folderName))) {
				checkIfExist = true;
				for(Note transNote : folder.getNotes()) {
					if(note.equals(transNote)) {
						System.out.println("Creating note "+ note.getTitle()+" under folder "+folderName+" failed");
						return false;
					}
				}
				folder.addNote(note);
				return true;
			}
			
		}
		folders.add(new Folder(folderName));
		folders.get(folders.size()-1).addNote(note);
		return true;
		
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
