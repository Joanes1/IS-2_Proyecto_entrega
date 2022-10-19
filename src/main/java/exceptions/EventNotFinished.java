package exceptions;
public class EventNotFinished extends Exception {
 private static final long serialVersionUID = 1L;
 
 public EventNotFinished()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public EventNotFinished(String s)
  {
    super(s);
  }
}