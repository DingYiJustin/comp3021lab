package Lab4;

import java.io.File;
import java.io.Serializable;

public class ImageNote extends Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File image;
	
	public ImageNote(String title) {
		super(title);
	}
}
