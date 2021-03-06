package lab7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) 	{
		this.name = name;
		notes = new ArrayList<Note>(0);
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> currentNotes = new ArrayList<Note>(notes);
		List<String> currentStringsToFind = new ArrayList<String>();
		
		String[] splitedStrings = keywords.split(" ");
		for(int i = 0; i<splitedStrings.length;i++) {
			splitedStrings[i] = splitedStrings[i].toLowerCase();
		}
		
		int i = 0;
		while(i<splitedStrings.length) {
			currentStringsToFind.clear();
			String current = splitedStrings[i];
			currentStringsToFind.add(current);
			
			String next = null;
			if(i<splitedStrings.length-1)
				 next = splitedStrings[i+1];
			
			//find current "ored" keywords
			while(next != null && next.equals("or")) {
			
				i+=2;
				current = splitedStrings[i];
				currentStringsToFind.add(current);
				next = null;
				if(i<splitedStrings.length-1)
					 next = splitedStrings[i+1];
			}
			
			//remove not searching notes
			for(int j = 0; j<currentNotes.size(); j++) {
				boolean contain = false;
				if(currentNotes.get(j) instanceof ImageNote) {
					for(String toFind : currentStringsToFind) {
							contain = (currentNotes.get(j).getTitle().toLowerCase()).contains(toFind); 
							if(contain)
								break;
					}
				}
				else {
					for(String toFind : currentStringsToFind) {
						contain = (currentNotes.get(j).getTitle().toLowerCase()).contains(toFind) || 
								(((TextNote)currentNotes.get(j)).content.toLowerCase()).contains(toFind)
								;
								
						if(contain)
							break;
					}
				}
				
				if(contain)
					continue;
				else {
					currentNotes.remove(j);
					j--;
				}
			}
			i++;
			
		}
		return currentNotes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for(Note note: notes){
			if(note instanceof TextNote) {
				nText++;
			}
			else if(note instanceof ImageNote) {
				nImage++;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}
	
	@Override
	public int compareTo(Folder o) {
		int compare = name.compareTo(o.name);
		
		if(compare>0)
			return 1;
		else if(compare == 0)
			return 0;
		else
			return -1;
	}
	
}
