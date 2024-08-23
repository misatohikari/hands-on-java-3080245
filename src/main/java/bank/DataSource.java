package bank;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Connection class is used to represent a connection to a database
// java.sql is a java package contains built-in classes that support database interactions
public class DataSource {
  
  // import Connection from java.sql
  public static Connection connect(){
    // jdbc:sqlite:pathTotheDababaseFile
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    
    // if unhandled error handling error shown, it means the code should be inside try block 
    try{
      connection = DriverManager.getConnection(db_file);
      System.out.println("we're connected!");
    }catch(SQLException e){
      e.printStackTrace();
    }

    return connection;
  }

  public static Customer getCustomer(String username){
    // ? serves as a placeholder. this way is safer
    String sql = "select * from customers where username = ?";
    Customer customer = null;

    // inside try is automatically closeable resources that java will automatically close
    // the conneciton once data is retrieved. 
    try(Connection connection = connect();
        // setup stuff to handle the String sql and ? of placeholder case
        PreparedStatement statement = connection.prepareStatement(sql)){
          // parameterIndex for placeholder, and value
          statement.setString(1, username);
          // to execute query which return result set which close automatically too so put inside try
          try(ResultSet resultSet = statement.executeQuery()){
            customer = new Customer(
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getString("username"),
              resultSet.getString("password"),
              resultSet.getInt("account_id")); // this have to match with the column label from the database
          }
    }catch(SQLException e){
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId){
    // use ? for placeholder for security purpose
    // since appending raw user input to a string that will be used to query a database is anacceptable
    String sql = "select * from accounts where id = ?";
    Account account = null;

    // try-with-resources statement is a special form of the try statement that 
    // ensures resources are closed automatically when the block exits.
    try (Connection connection = connect(); // Try-with-resources for Connection
          PreparedStatement statement = connection.prepareStatement(sql)){ // Try-with-resources for PreparedStatement
            statement.setInt(1, accountId); // PreparedStatement is java class used to execute a SQL statement
            // ResultSet is the Java class which represents the data returned from a
            // database query
            try(ResultSet resultSet = statement.executeQuery()){ // Try-with-resources for ResultSet
              account = new Account(
                resultSet.getInt("id"),
                resultSet.getString("type"),
                resultSet.getDouble("balance")
              );
            }
          }catch(SQLException e){
            e.printStackTrace();
          }
          return account;
  }

  public static void updateAccountBalance(int accountId, double balance){
    String sql = "update accounts set balance = ? where id = ?";
    try(
      Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql);
    ){
      statement.setDouble(1, balance);
      statement.setInt(2, accountId);

      statement.executeUpdate(); // with insert, delete, update, we use executeUpdate()

    }catch(SQLException e){
      e.printStackTrace();
    }
    
  }

}
