package bank;

import bank.exceptions.AmountException;

public class Account {
  // encapsulation - Hiding data so that it cannot be directly accessed outside of its class
  private int id;
  private String type;
  private double balance;

  // counstructor
  public Account(int id, String type, double balance){
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) { 
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException{// in the header we have to specify which exception is thrown in this func
    if (amount < 1){
      throw new AmountException("The minimum deposit is 1.00");
    }else{
      double newBalance = balance + amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance); //update the database itself
    }
  }

  public void withdraw(double amount) throws AmountException{
    if (amount < 1) {
      throw new AmountException("The minimum withdraw is 1.00");
    } else if (amount > getBalance()){
      throw new AmountException("You do not have sufficient funds for this withdrawal");
    } else{
      double newBalance = balance - amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }


  
}
