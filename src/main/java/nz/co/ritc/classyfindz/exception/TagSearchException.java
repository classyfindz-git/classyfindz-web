/**
 * 
 */
package nz.co.ritc.classyfindz.exception;


/**
 * @author rahul
 *
 */
public class TagSearchException extends ApplicationRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3832379646527019076L;

	/**
	 * @param tagStart
	 * @param t
	 */
	public TagSearchException(final String tagStart,final Throwable t) {
		super("Failed to lookup tag " + tagStart);
		initCause(t);
	}

}
