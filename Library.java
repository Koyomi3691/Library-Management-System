import java.sql.*;
import java.text.ParseException;
import java.util.*;

public class Library
{
   public static void main(String Args[]) throws SQLException, ParseException
   {
      Books b = new Books();
      Loan l = new Loan();
      Reservations r = new Reservations();
      Users u = new Users();

      System.out.println();
      System.out.println("Hello, Welcome to the Library Management System. Please select the action you would like to perform!!");

      System.out.println();

      Scanner sc = new Scanner(System.in);

      System.out.print("Enter 1 for operating books, 2 for operating loans, 3 for operating reservations or 4 for operating users  --  ");
      int choice0 = sc.nextInt();
      sc.nextLine();
      System.out.println();
      switch(choice0)
      {
         case 1:
         {
            b.callBooks();
            break;
         }

         case 2:
         {
            l.callLoan();
            break;
         }

         case 3:
         {
            r.callReservations();
            break;
         }

         case 4:
         {
            u.callUsers();
            break;
         }

         default:
         {
            System.out.println("Please enter a valid choice");
            break;
         }
      }

      sc.close();
   }
}
