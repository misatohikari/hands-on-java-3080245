package bank.exceptions;

public class AmountException extends Exception{ // inheritance.

  // constructor
  public AmountException(String message){ 
    super(message); // pass the message to the parents!
  }

}
