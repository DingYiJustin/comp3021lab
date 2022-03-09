package Lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>(0);
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
			//for(Note note: )
		}
		return returnValue;
	}
	
	public boolean insertNote(String folderName, Note note) {
		boolean checkIfExist = false;
		for(Folder folder : folders) {
			if(folder.equals(new Folder(folderName))) {
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
	
}
