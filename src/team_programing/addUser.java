package team_programing;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	     	
		  String name,surname,email;
		  name = request.getParameter("name");
		  surname = request.getParameter("surname");
		  email = request.getParameter("email");
		  
	      response.setContentType("text/html");
	      
	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
		  
		  if(email != null)
		  {
			  if(validate(email))
			  {
			      
			      out.println("<h1>" + email + "</h1>");
			      
			      //databaseInsertFunction(email,surname,name);
			      
			      request.setAttribute("email", email);
			      request.getRequestDispatcher("addUser.jsp").forward(request, response); 
			  }
			  else
			  {
				  out.println("<h1> Email invalid </h1>"); 
			  }
			  
		  }
		  
	}
	
	protected void databaseInsertFunction(String email, String surname, String name)
	{
	      Connection conn = null;
		  Statement stmt = null;
		  
	      String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	      String DB_URL="jdbc:mysql://localhost/TEST";

	      //  Database credentials
	      String USER = "root";
	      String PASS = "password";
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO totalsevice.users VALUES " + name + surname
					+ email;
			ResultSet rs = stmt.executeQuery(sql);

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		} // end try
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
