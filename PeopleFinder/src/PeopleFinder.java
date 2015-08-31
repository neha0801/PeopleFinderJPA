

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.People;
import customTools.DBUtil;

/**
 * Servlet implementation class PeopleFinder
 */
@WebServlet("/PeopleFinder")
public class PeopleFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Statement st;
	private static Connection con ;
	private static ArrayList<String> errorMessages = new ArrayList<String>();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleFinder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/*private void openConnection(){
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, props);
			st = con.createStatement();
		} catch (SQLException|ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connection established successfully...!!");
	
	}*/
	

	/*private String getCustomer(String searchText){
		openConnection();
		String sql="",message="";
		ResultSet rs=null;
		int count=0;
		sql ="select firstname,lastname,FullName from people where lastname like '%" +searchText + "%'";
		
		
		System.out.println(sql);
		try{
			rs = st.executeQuery(sql);
			while(rs.next()) {
				if(count==0){
				message += "<table class = 'table table-bordered table-striped'>"; 
				message+="<br></br>";
				message += "<tr><th><b>First Name</b></th><th><b>Last Name</b></th></tr>";	
				}
				count++;
				System.out.println(rs.getString(3));
				message += ("<tr><td><a href=Details?name=" + rs.getString(1) + rs.getString(2) +">" + rs.getString(1) + "</a></td><td>" + rs.getString(2) + "</td></tr>");
			}
			if (count==0)
				message= "<div>No Employees Found";
			//message+="</div>";
			// close the connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return message;
	}
	
	private String getCompanies(String searchText){
		openConnection();
		String sql="",message="";
		ResultSet rs1=null;
		int count=0;
		sql ="select COMPANY_ID,COMPANY  from company where COMPANY like '%" +searchText +"%'";
	
		
		System.out.println(sql);
		try{
			rs1 = st.executeQuery(sql);
			while(rs1.next()) {
				if(count==0){
				//message= "<p>Companies found:</p>";
				message += "<table class = 'table table-bordered table-striped'>"; 
				message+="<br></br>";
				message += "<tr><th><b>Company Id</b></th><th><b>Company Name</b></th></tr>";
				}					
				count++;
				message += ("<tr><td><a href=Details?companyId=" + rs1.getInt(1) + ">"+ rs1.getInt(1) + "</td><td>" + rs1.getString(2) + "</td></tr>");
			}
			if (count==0)
				message= "<div>No Companies Found";
			//message+="";
			// close the connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return message;
	}*/
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String message="";
		String searchText;
		
		searchText = request.getParameter("search");
		if(searchText.equalsIgnoreCase("")){
			errorMessages.add("Search text parameter is empty!");
		}

		if (errorMessages.size() != 0) {
			showErrorMessage(errorMessages, response);
		} else {
			List<People> pList = DBUtil.searchEmployees(searchText);
			String tableData = "";
			if(pList!=null){
				tableData = "<br><br>";
				tableData += "\r<table class=table border=1 align=center>";
				tableData += "<tr style=background-color:green>";
				tableData += "<th>";
				tableData += "First Name";
				tableData += "</th>";
				tableData += "<th>";
				tableData += "Last Name";
				tableData += "</th>";
				tableData += "</tr>\r";

				for (People p : pList) {
					tableData += "<tr class='info'>";
					tableData += "<td>";
					tableData += p.getFirstname();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getLastname();
					tableData += "</td>";
					tableData += "</tr>\r";
				}
				tableData += "</table>\r";
			}else {
				tableData = "<p> No Employees Found </p>";
			}
			List<Company> compList = DBUtil.searchCompanies(searchText);
			if(compList!=null){
				tableData += "<br><br>";
				tableData += "\r<table class=table border=1 align=center>";
				tableData += "<tr style=background-color:green>";
				tableData += "<th>";
				tableData += "Company ID";
				tableData += "</th>";
				tableData += "<th>";
				tableData += "Company Name";
				tableData += "</th>";
				tableData += "</tr>\r";

				for (Company c : compList) {
					tableData += "<tr class='info'>";
					tableData += "<td>";
					tableData += c.getCompanyid();
					tableData += "</td>";
					tableData += "<td>";
					tableData += c.getCompany();
					tableData += "</td>";
					tableData += "</tr>\r";
				}
				tableData += "</table>\r";
			}else {
				tableData = "<p> No Companies Found </p>";
			}
			
			
			//message+=getCustomer(searchText);	
			//message+=getCompanies(searchText);	
			//message +="<div class='col-sm-offset-2 col-sm-10'><p><a href='FindPeople.jsp' class='btn btn-primary btn-lg' role='button'>Go to Home Page</a></p></div>";
			request.setAttribute("message", tableData);
			getServletContext().getRequestDispatcher("/Output.jsp").forward(
					request, response);
		}
	}

	private void showErrorMessage(ArrayList<String> errMsgList,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Your request is very important for us, but:<br>");
		for (int i = 0; i < errMsgList.size(); i++) {
			out.println("<li>" + errMsgList.get(i));
		}
		out.println("<br><br>");
		out.println("<a href='FindPeople.jsp'>go back and correct your input please...</a>");
		out.println("</body></html>");
		out.close();

	}
}
