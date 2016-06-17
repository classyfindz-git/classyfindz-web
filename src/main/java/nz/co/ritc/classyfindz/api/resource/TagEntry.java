/**
 * 
 */
package nz.co.ritc.classyfindz.api.resource;

import java.io.Serializable;

/**
 * @author rahul
 *
 */
public class TagEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1635136961298796554L;
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
