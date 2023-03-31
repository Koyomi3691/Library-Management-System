import java.sql.*;
import java.util.*;

public class Users 
{
   static Connection c4 = null;

   static Connection C4()
   {
      try
      {
         Class.forName("com.mysql.cj.jdbc.Driver");

         c4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1989@shiv");  
      }

      catch(Throwable e)
      {
         System.out.println(e);
      }

      return c4;
   }

   void viewUser(String name)
   {
      try
      {
         Connection conn = C4();
         Statement stmt = conn.createStatement();

         ResultSet rs = stmt.executeQuery("select * from users where name = '" + name + "'");

         while(rs.next())
         {
            System.out.println(rs.getString(1) + "  |  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
         }

         rs.close();
         stmt.close();
         conn.close();
      }

      catch(Throwable e)
      {
         System.out.println(e);
      }
   }

   void addUser(String id, String n)
   {
      try
      {
         Connection conn = C4();
         Statement stmt = conn.createStatement();

         int ins = stmt.executeUpdate("insert into users values('"+ id + "', '" + n + "', '--', '--')");

         if (ins > 0)
         {
            System.out.println("Record added successfully");

            ResultSet rs = stmt.executeQuery("select * from users where userid = '" + id + "'");

            System.out.println("User ID " + " | " + " Name " + " | " + " Reservations " + " | " + " Loans ");

            while(rs.next())
            {
               System.out.println(rs.getString(1) + "  |  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
            }

         }
         else
         {
            System.out.println("Error");
         }

         stmt.close();
         conn.close();
      }

      catch(Throwable e)
      {
         System.out.println(e);
      }
   }

   void remUser(String id)
   {
      try
      {
         Connection conn = C4();
         Statement stmt = conn.createStatement();

         int del = stmt.executeUpdate("delete from users where userid = '" + id + "'");

         if (del > 0)
         {
            System.out.println("Record deleted successfully");
         }
         else
         {
            System.out.println("No such record found");
         }

         stmt.close();
         conn.close();
      }

      catch(Throwable e)
      {
         System.out.println(e);
      }
   }

   void editUser(String id, int choice)
   {
      try
      {
         Connection conn = C4();
         Statement stmt = conn.createStatement();

         int c = choice;

         switch(c)
         {
            case 1:
            {
               int addr = stmt.executeUpdate("update users set reserve = 'YES' where userid = '" + id + "'");

               if (addr > 0)
               {
                  System.out.println("Successfully Updated");

                  ResultSet rs = stmt.executeQuery("select * from users where userid = '" + id + "'");

                  System.out.println();

                  System.out.println("User ID " + " | " + " Name " + " | " + " Reservations " + " | " + " Loans ");

                  while(rs.next())
                  {
                     System.out.println(rs.getString(1) + "  |  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
                  }
               }
               else
               {
                  System.out.println("No such users found");
               }

               break;
            }

            case 2:
            {
               int remr = stmt.executeUpdate("update users set reserve = '--' where userid = '" + id + "'");

               if (remr > 0)
               {
                  System.out.println("Successfully Updated");

                  ResultSet rs = stmt.executeQuery("select * from users where userid = '" + id + "'");

                  System.out.println();

                  System.out.println("User ID " + " | " + " Name " + " | " + " Reservations " + " | " + " Loans ");

                  while(rs.next())
                  {
                     System.out.println(rs.getString(1) + "  |  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
                  }
               }
               else
               {
                  System.out.println("No such users found");
               }

               break;
            }

            case 3:
            {
               int addl = stmt.executeUpdate("update users set loan = 'YES' where userid = '" + id + "'");

               if (addl > 0)
               {
                  System.out.println("Successfully Updated");

                  ResultSet rs = stmt.executeQuery("select * from users where userid = '" + id + "'");

                  System.out.println();

                  System.out.println("User ID " + " | " + " Name " + " | " + " Reservations " + " | " + " Loans ");

                  while(rs.next())
                  {
                     System.out.println(rs.getString(1) + "  |  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
                  }
               }
               else
               {
                  System.out.println("No such users found");
               }

               break;
            }

            case 4:
            {
               int reml = stmt.executeUpdate("update users set loan = '--' where userid = '" + id + "'");

               if (reml > 0 )
               {
                  System.out.println("Successfully Updated");

                  ResultSet rs = stmt.executeQuery("select * from users where userid = '" + id + "'");

                  System.out.println();

                  System.out.println("User ID " + " | " + " Name " + " | " + " Reservations " + " | " + " Loans ");

                  while(rs.next())
                  {
                     System.out.println(rs.getString(1) + "  |  " + rs.getString(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
                  }
               }
               else
               {
                  System.out.println("No such users found");
               }

               break;
            }

            default:
            {
               System.out.println("Enter a valid choice");
               break;
            }
         }

         stmt.close();
         conn.close();
      }
      catch(Throwable e)
      {
         System.out.println(e);
      }
   }

   void callUsers() throws SQLException
   {
      Users u1 = new Users();
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter 1 to view user detail, 2 to add user, 3 to remove user or 4 to edit  --  ");
      int c1 = sc.nextInt();
      sc.nextLine();
      System.out.println();

      switch(c1)
      {
         case 1:
         {
            System.out.print("Enter the User name  --  ");
            String n = sc.nextLine();
            System.out.println();

            System.out.println("User ID " + " | " + " Name " + " | " + " Reservations " + " | " + " Loans ");

            u1.viewUser(n);
            System.out.println();

            break;
         }

         case 2:
         {
            System.out.print("Enter the User ID  --  ");
            String id2 = sc.nextLine();
            System.out.println();

            System.out.print("Enter the name of the user  --  ");
            String name = sc.nextLine();
            System.out.println();

            u1.addUser(id2, name);
            System.out.println();

            break;
         }

         case 3:
         {
            System.out.print("Enter the User ID you want to delete  --  ");
            String id3 = sc.nextLine();
            System.out.println();

            u1.remUser(id3);
            System.out.println();

            break;
         }

         case 4:
         {
            System.out.print("Enter the User ID you want to edit  --  ");
            String id4 = sc.nextLine();
            System.out.println();

            System.out.print("Enter 1 to add reservation,  2 to remove a reservation, 3 to add a loan or 4 to remove a loan  --  ");
            int c0 = sc.nextInt();
            System.out.println();

            u1.editUser(id4, c0);
            System.out.println();

            break;
         }

         default:
         {
            System.out.println("Enter a valid choice");
            System.out.println();

            break;
         }
      }

      sc.close();
   }
}