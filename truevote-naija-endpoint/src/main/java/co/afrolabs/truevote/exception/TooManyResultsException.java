package co.afrolabs.truevote.exception;


/**
 * Wrapper exception that gets thrown when Objectify get() returns too many results
 */
public class TooManyResultsException extends Exception
{

	/**ó
	 * 
	 */
	private static final long serialVersionUID = 2972458529654336066L;

	public TooManyResultsException()
	{
		super();
	}

	public TooManyResultsException(Throwable t)
	{
		super(t);
	}

	public TooManyResultsException(String msg)
	{
		super(msg);
	}

	public TooManyResultsException(String msg, Throwable t)
	{
		super(msg, t);
	}

}
