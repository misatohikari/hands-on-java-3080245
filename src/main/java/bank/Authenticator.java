package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  // public static Customer means return Customer class
  public static Customer login(String username, String password) throws LoginException{
    Customer customer = DataSource.getCustomer(username); // access DataSource class of getCustomer methods
    if (customer == null){
      throw new LoginException("User not found"); // LoginException is a built-in login error
    }

    // .equals vs == 
    // .equals is to compare the contents whether two objects are logically equivalent.
    // == is to compoare references(memory addresses) of objects. or references types. 
    if(password.equals(customer.getPassword())){  // Access Customer class of getPassword method
      customer.setAuthenticated(true);
      return customer;
    }else throw new LoginException("Incorrect password");
  }

  public static void logout(Customer customer){
    customer.setAuthenticated(false);
  }
}
