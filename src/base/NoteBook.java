package base;

import java.util.ArrayList;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>(0);
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
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
