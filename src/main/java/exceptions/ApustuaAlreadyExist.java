package exceptions;

public class ApustuaAlreadyExist extends Exception{
	public ApustuaAlreadyExist()
	  {
	    super();
	  }
	  /**This exception is triggered if the event has already finished
	  *@param s String of the exception
	  */
	  public ApustuaAlreadyExist(String s)
	  {
	    super(s);
	  }

}
