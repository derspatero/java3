/**
 * Project: A00892244Lab4
 * File: ApplicationException.java
 * Date: Jan 27, 2016
 * Time: 2:43:01 PM
 */

package a00892244.utils;

/**
 * @author Edward Lambke, A00892244
 *
 */

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	public ApplicationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ApplicationException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ApplicationException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ApplicationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
