package bank;

public class Customer {

  // Encapsulation - wrapping data (fields) and methods that operate on the data into a single unit or class, 
  // restricting direct access to some of the object's components and only allowing controlled access through public methods.
  private int id;
  private String name;
  private String username;
  private String password;
  private int accountId;
  private boolean authenticated;

  public Customer(int id, String name, String username, String password, int accountId){
    setId(id);
    setName(name);
    setUsername(username);
    setPassword(password);
    setAccountId(accountId);
    setAuthenticated(false);
  }

  // get a write and read methods since the componenets are encapsulated. 
  // right click, select Code generate for Java and generate getters and setters. 

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }


  public boolean isAuthenticated() {
    return this.authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }


}
