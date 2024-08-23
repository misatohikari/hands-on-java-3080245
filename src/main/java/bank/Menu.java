package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args){
    System.out.println("Welcome to java pra Bank!");

    // To access non-static class data, static methods must use an instance of the class
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();
    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.println("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    // unhandled exception type error - solution is to wrap with try catch block
    try{
      customer = Authenticator.login(username, password);
    }catch(LoginException e){
      System.out.println("There was an error: " + e.getMessage());
    }
   
    return customer;
  }

  private void showMenu(Customer customer, Account account){

    int selection = 0;

    while(selection != 4 && customer.isAuthenticated()){
      System.out.println("==========================================");
      System.out.println("Please select one of the following options");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("==========================================");

      selection = scanner.nextInt(); // .next() is for string, for int will will be nextInt()
      double amount = 0;

      switch(selection){
        case 1:
          System.out.println("How much would you like to deposit");
          amount = scanner.nextDouble();
          try{
            account.deposit(amount); 
            // in this deposit func, error is thrown so have to be wrapped with try catch
          }catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again");
          }
          
          break; // for switch, don't forget break!! otherwise will execute all the codes below.
        case 2:
          System.out.println("How much would you like to withdraw");
          amount = scanner.nextDouble();
          try{
            account.withdraw(amount);
          } catch (AmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
          }
          break;
        case 3:
          System.out.println("Current balance: " + account.getBalance());
          break;
        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking at java pra Bank!");
          break;
        default: // do not forget the default case to handle any other options!
          System.out.println("Invalid option. Please try again");
          break;
      }
    }
  }

}
