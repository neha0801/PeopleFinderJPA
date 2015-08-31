import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String name = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String  compId= request.getParameter("companyId");
		String message="";
		
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, props);
			Statement st = con.createStatement();
			System.out.println("connection established successfully...!!");
			message+="<br></br>";
			
			message += "<table class = 'table table-bordered table-striped'>"; 
			if(name!=null){
				String sql = "SELECT p.FULLNAME, p.TITLE, p.FIRSTNAME,"+
						  "p.LASTNAME,  p.STREETADDRESS,  l.CITY,"
						  +"l.STATES,  p.ZIPCODE,  p.EMAILADDRESS,"+
						  "p.POSITIONS,  c.COMPANY	FROM people p	JOIN company c "+
						  "ON p.COMPANY_ID=c.COMPANY_ID	JOIN location l ON l.LOCATION_ID= p.LOCATION_ID "
						  + "where concat(p.firstname,p.lastName)  = '"+ name +"'";
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);

				message += "<tr><th><b> Full Name</b> </th><th> <b>Title</b> </th><th> <b> First Name </b></th><th> <b> Last Name </b></th><th> <b> Street Address </b>" +
						"</th><th> <b> City </b></th><th> <b> State </b></th><th> <b> Zipcode </b></th><th> <b> Email Address </b></th><th><b> Position </b></th><th><b> Company </b></th></tr>";
				
				if(rs.next()){
					message+= "<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>"
						+ rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) +"</td><td>" + rs.getString(6) + "</td><td>" + rs.getString(7) 
						+ "</td><td>" + rs.getString(8) + "</td><td>" + rs.getString(9) + "</td><td>" + rs.getString(10)   + "</td><td>" + rs.getString(11) +"</td></tr>";
				}
				
			} else if (compId!=null){
				String sql = "SELECT COMPANY_id,Company	FROM company where COMPANY_id = "+ compId ;
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);

				message += "<tr><th><b> Company ID </b></th> <th> <b> Company </b></th></tr>";
				
				if(rs.next()){
				message+= "<tr><td>" + rs.getString(1) + "</td><td>"  + rs.getString(2) + "</td></tr>" ;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		message+="<br></br>";
		message+="</table>";
		message+="<a class='btn pull-left btn-primary btn-lg' href='FindPeople.jsp'>Home Page</a>";
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/Output.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
