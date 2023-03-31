import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;

public class Loan
{
    static Connection c2 = null;

    static Connection C2()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            c2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1989@shiv");
        }

        catch(Throwable e)
        {
            System.out.println(e);
        }

        return c2;
    }

    void getDetails(String id)
    {
        try
        {
            Connection conn = C2();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from loan where userid = '" + id + "'");
            
            while(rs.next())
            {
                System.out.println(rs.getString(1) + "  |  " + rs.getDate(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
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

    void addLoan(String id, Date due, String bid, String uid)
    {
        try
        {
            Connection conn = C2();
            Statement stmt = conn.createStatement();

            int ins = stmt.executeUpdate("insert into loan values('"+ id + "', '" + due + "', '" + bid + "', '" + uid + "')");

            if (ins > 0)
            {
                System.out.println("Record added successfully");

                System.out.println("Loan ID " + " | " + " Due Date " + " | " + " Book ID " + " | " + " User ID ");

                ResultSet rs = stmt.executeQuery("select * from loan where loanid = '" + id + "'");

                while(rs.next())
                {
                    System.out.println(rs.getString(1) + "  |  " + rs.getDate(2) + "  |  " + rs.getString(3) + "  |  " + rs.getString(4));
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

    void remLoan(String id)
    {
        try
        {
            Connection conn = C2();
            Statement stmt = conn.createStatement();

            int del = stmt.executeUpdate("delete from loan where loanid = '" + id + "'");

            if (del > 0 )
            {
                System.out.println("Successfully deleted record");
            }
            else
            {
                System.out.println("No such record found");
            }

            conn.close();
            stmt.close();

        }

        catch(Throwable e)
        {
            System.out.println(e);
        }
    }

    void callLoan() throws SQLException, ParseException
    {
        Loan l1 = new Loan();
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter 1 to get the details of a particular loan, 2 to add a new record or 3 to delete an existing record  --  ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println();

        switch (choice)
        {
            case 1:
            {
                System.out.print("Enter the User ID of the record you want to retrieve  --  ");
                String id1 = sc.nextLine();
                System.out.println();

                System.out.println("Loan ID " + " | " + " Due Date " + " | " + " Book ID " + " | " + " User ID ");

                l1.getDetails(id1);
                System.out.println();

                break;
            }

            case 2:
            {
                System.out.print("Enter the Loan ID --  ");
                String id1 = sc.nextLine();
                System.out.println();

                System.out.print("Enter the Due Date in yyyy-mm-dd format --  ");
                String d1 = sc.nextLine();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
                java.util.Date p = f.parse(d1);
                Date due1 = new Date(p.getTime());
                System.out.println();

                System.out.print("Enter the Book ID --  ");
                String bid1 = sc.nextLine();
                System.out.println();

                System.out.print("Enter the User ID --  ");
                String uid1 = sc.nextLine();
                System.out.println();
                
                l1.addLoan(id1, due1, bid1, uid1);
                System.out.println();

                break;
            }

            case 3:
            {
                System.out.print    ("Enter the Loan ID of the record you want to delete  --  ");
                String id3 = sc.nextLine();
                System.out.println();

                l1.remLoan(id3);
                System.out.println();

                break;
            }

            default:
            {
                System.out.println("Enter a Valid Choice");
                System.out.println();

                break;
            }
        }

        sc.close();
        
    }

}