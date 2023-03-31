import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;

public class Reservations
{
    static Connection c3 = null;

    static Connection C3()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            c3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1989@shiv");
        }

        catch(Throwable e)
        {
            System.out.println(e);
        }

        return c3;
    }

    void getDetails(String id)
    {
        try
        {
            Connection conn = C3();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from reservations where userid = '" + id + "'");

            while(rs.next())
            {
                System.out.println(rs.getString(1) + "  |   " + rs.getDate(2) + "   |   " + rs.getDate(3) + "   |   " + rs.getString(4) + "    |    " + rs.getString(5));
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

    void addRecord(String id, Date pick, Date due, String bid, String uid)
    {
        try
        {
            Connection conn = C3();
            Statement stmt = conn.createStatement();

            int ins = stmt.executeUpdate("insert into reservations values('" + id + "', '" + pick + "', '" + due + "', '" + bid + "', '" + uid + "')");

            if (ins > 0)
            {
                System.out.println("Record added successfully");

                ResultSet rs = stmt.executeQuery("select * from reservations where resid = '" + id + "'");

                System.out.println("Reservation ID " + " | " + " Pickup Date " + " | " + " Due Date " + " | " + " Book ID " + " | " + " User ID ");

                while(rs.next())
                {
                    System.out.println(rs.getString(1) + "  |   " + rs.getDate(2) + "   |   " + rs.getDate(3) + "   |   " + rs.getString(4) + "    |    " + rs.getString(5));
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

    void remRecord(String id)
    {
        try
        {
            Connection conn = C3();
            Statement stmt = conn.createStatement();

            int del = stmt.executeUpdate("delete from reservations where resid = '" + id + "'");

            if (del > 0)
            {
                System.out.println("Record deleted Successfully");
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

    void callReservations() throws SQLException, ParseException
    {
        Reservations r1 = new Reservations();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 1 to get details of a reservation, 2 to add a record or 3 to delete a record  --  ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println();

        switch(choice)
        {
            case 1:
            {
                System.out.print("Enter the User ID of the record you want to retrieve  --  ");
                String id1 = sc.nextLine();
                System.out.println();

                System.out.println("Reservation ID " + " | " + " Pickup Date " + " | " + " Due Date " + " | " + " Book ID " + " | " + " User ID ");

                r1.getDetails(id1);
                System.out.println();

                break;
            }

            case 2:
            {
                System.out.print("Enter the Reservation ID  --  ");
                String id2 = sc.nextLine();
                System.out.println();

                System.out.print("Enter the pickup date in yyyy-mm-dd format  --  ");
                String pd1 = sc.nextLine();
                SimpleDateFormat pf = new SimpleDateFormat("yyyy-mm-dd");
                java.util.Date pp = pf.parse(pd1);
                Date pick1 = new Date(pp.getTime());
                System.out.println();

                System.out.print("Enter the duedate in yyyy-mm-dd format  --  ");
                String dd1 = sc.nextLine();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                java.util.Date dp = df.parse(dd1);
                Date due1 = new Date(dp.getTime());
                System.out.println();

                System.out.print("Enter the Book Id  --  ");
                String b1 = sc.nextLine();
                System.out.println();

                System.out.print("Enter the User ID  --  ");
                String u1 = sc.nextLine();
                System.out.println();

                r1.addRecord(id2, pick1, due1, b1, u1);
                System.out.println();

                break;
            }

            case 3:
            {
                System.out.print("Enter the reservation id of the record you want to remove  --  ");
                String id3 = sc.nextLine();
                System.out.println();

                r1.remRecord(id3);
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
