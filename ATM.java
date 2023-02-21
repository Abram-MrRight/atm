import java.util.Scanner;

import javax.xml.stream.events.StartElement;

import java.sql.*;
public class ATM {

    public static void main(String[] args) {
       try {
        String url="jdbc:mysql://localhost:3306/ATM";
        String username="root";
        String pass="";
        boolean isNumber;
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement stm =con.createStatement();
        String name = "Mr.Right";
        double balance = 1000;
        double depositAmount =0.0;
        double withdrawAmount =0.0;
        int pin= 12345;
        int accountNumber=123456;

        ResultSet rs = stm.executeQuery("SELECT * FROM records WHERE pin= " +pin );
         
        while(rs.next()){
            balance =rs.getDouble(2);
            depositAmount =rs.getDouble(3);
            withdrawAmount =rs.getDouble(4);
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");
            
        System.out.print("Enter your pin number: "); 
        int  pinNumber =input.nextInt();
                    
         System.out.print("Enter your account number: ");
            int ac_Number =input.nextInt();       
        
        if((pin ==pinNumber)&& (accountNumber==ac_Number)){
           while(true){
            System.out.println("Hello! " +name);
            System.out.println("--------------------------------------------");

            System.out.println("1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Print the receipt");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("Your current balance is $" + balance);
            } else if (choice == 2) {
                System.out.print("Enter the amount to deposit: ");
                 depositAmount = input.nextDouble();
                balance += depositAmount;
                double bal = stm.executeUpdate("UPDATE records SET balance= "+balance+"WHERE pin= "+pin);
                double depos = stm.executeUpdate("UPDATE records SET depositAmount= "+ depositAmount+"WHERE pin= "+pin);
                System.out.println("$" + depositAmount + " has been deposited to your account.");
            } else if (choice == 3) {
                System.out.print("Enter the amount to withdraw: ");
                 withdrawAmount = input.nextDouble();
                if (withdrawAmount > balance) {
                    System.out.println("Insufficient balance.");
                } else {
                    balance -= withdrawAmount;
                    double bal = stm.executeUpdate("UPDATE records SET balance= "+balance+"WHERE pin= "+pin);
                    double withdraw = stm.executeUpdate("UPDATE records SET withdrawAmount= "+ withdrawAmount+"WHERE pin= "+pin);
                   System.out.println("$" + withdrawAmount + " has been withdrawn from your account.");
                }
                
            }else if (choice == 4) {
                    System.out.println("Thanks for coming! ");                   
                    System.out.println("Amount added: "+ "$" + depositAmount );
                    System.out.println("Amount taken:"+ "$" + withdrawAmount);
                    System.out.println("Your current balance is :"+ "$" +balance);
                   break;
               
            }else if (choice == 5) {
            
                System.out.println("Thank you for using our ATM. Have a nice day!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
                break;
                
            }
            
        } 
        }else {
                System.out.println("Invalid entry");
                
             }
            }catch (Exception e) {
                System.out.println("Exception sucessfully caught! "+e);
               
        } 
 
    }
}
    
 

     
 

   
  
