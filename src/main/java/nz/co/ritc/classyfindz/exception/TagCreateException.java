/**
 * 
 */
package nz.co.ritc.classyfindz.exception;

import org.springframework.util.StringUtils;

/**
 * @author rahul
 *
 */
public class TagCreateException extends ApplicationRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4640014133313391758L;

	/**
	 * @param tagStart
	 * @param t
	 */
	public TagCreateException(final String tag,final Throwable t) {
		super("Failed to create new tag " + StringUtils.quote(tag));
		initCause(t);
	}

	/**
	 * @param tag
	 */
	public TagCreateException(String tag) {
		super("Tag already exists " + StringUtils.quote(tag));
	}

}