package exceptions;

public class QuoteAlreadyExist extends Exception{
	private static final long serialVersionUID = 1L;
	 
	 public QuoteAlreadyExist()
	  {
	    super();
	  }
	  /**This exception is triggered if the question already exists 
	  *@param s String of the exception
	  */
	  public QuoteAlreadyExist(String s)
	  {
	    super(s);
	  }
	}
