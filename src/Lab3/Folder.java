package Lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
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
				//System.out.println("next is or");
				i+=2;
				current = splitedStrings[i];
				currentStringsToFind.add(current);
				next = null;
				if(i<splitedStrings.length-1)
					 next = splitedStrings[i+1];
			}
			
			//remove not searching notes
			for(String string: currentStringsToFind) {
			System.out.print(string+" ");
			}
			System.out.println("");
			for(int j = 0; j<currentNotes.size(); j++) {
				boolean contain = false;
				if(currentNotes.get(j) instanceof ImageNote) {
					System.out.println(currentNotes.get(j).getTitle());
					for(String toFind : currentStringsToFind) {
							contain = (currentNotes.get(j).getTitle().toLowerCase()).contains(toFind); //|| 
									//currentNotes.get(j).getTitle().contains(toFind.toUpperCase());
							if(contain)
								break;
					}
				}
				else {
					for(String toFind : currentStringsToFind) {
						System.out.println(currentNotes.get(j));
						System.out.println(toFind);
						contain = (currentNotes.get(j).getTitle().toLowerCase()).contains(toFind) || 
								//currentNotes.get(j).getTitle().contains(toFind.toUpperCase()) ||
								(((TextNote)currentNotes.get(j)).content.toLowerCase()).contains(toFind)
								//((TextNote)currentNotes.get(j)).content.contains(toFind.toUpperCase())
								;
						System.out.println(contain);
								
						if(contain)
							break;
					}
				}
				
				if(contain)
					continue;
				else {
					currentNotes.remove(j);
				}
				
//				for(Note note:currentNotes) {
//					System.out.print(note + " ");
//				}
//				System.out.println("");
			}
			i++;
			
		}
		return currentNotes;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Note))
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
