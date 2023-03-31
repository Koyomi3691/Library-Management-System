import java.sql.*;
import java.util.*;

class Books
{
    static Connection C1()
    {
        Connection c1 = null; 

        try
        {

            Class.forName("com.mysql.cj.jdbc.Driver");

            c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1989@shiv");
        }

        catch(Throwable e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return c1;
    }

    void getBooks(String n)
    {
        try
        {
            Connection conn = C1();
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from books where title = '" + n + "'");

            while(rs.next())
            {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5));
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

    void getCategory(String c)
    {
        try
        {
            Connection conn = C1();
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from books where category = '" + c + "'");

            while(rs.next())
            {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5));
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
    
    void callBooks() throws SQLException
    {
        Books b1 = new Books();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 1 to search by Name or 2 to search by category  --  ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println();

        switch (choice)
        {
            case 1:
            {
                System.out.print("Enter the Name of the Book  --  ");
                String name = sc.nextLine();
                System.out.println();

                System.out.println("Book ID " + " | " + " Title " + " | " + " Author " + " | " + " Publisher " + " | " + " Category ");

                b1.getBooks(name);
                System.out.println();

                break;
            }
            
            case 2:
            {
                System.out.print("Enter the Name of the Category  --  ");
                String cat = sc.nextLine();
                System.out.println();

                System.out.println("Book ID " + " | " + " Title " + " | " + " Author " + " | " + " Publisher " + " | " + " Category ");

                b1.getCategory(cat);
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
