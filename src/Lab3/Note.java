package Lab3;

import java.util.Date;

public class Note implements Comparable<Note>{
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date();
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Note o) {
		return -(date.compareTo(o.date));
	}
	
	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
