import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.People;
import customTools.DBUtil;

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
		int id = Integer.parseInt(request.getParameter("peopleId"));
		String tableData = "";
		tableData+="<br></br>";
		List<People> pList = DBUtil.getEmployees(id);
			
			if(pList!=null){
				tableData = "<br><br>";
				tableData += "\r<table class=table border=1 table-striped>";
				tableData += "<tr style=background-color:green>";
				tableData += "<th style=align:center>";
				tableData += "Full Name";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Title";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Street Address";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "City";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "State";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Zipcode";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Email address";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Position";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Company";
				tableData += "</th>";
				tableData += "</tr>\r";

				for (People p : pList) {
					tableData += "<tr class='info'>";
					tableData += "<td>";
					tableData += p.getFullname();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getTitle();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getStreetaddress();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getLocation().getCity();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getLocation().getStates();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getZipcode();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getEmailaddress();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getPositions();
					tableData += "</td>";
					tableData += "<td>";
					tableData += p.getCompany().getCompany();
					tableData += "</td>";
					tableData += "</tr>\r";
				}
				tableData += "</table>\r";
			}

		tableData+="<a class='btn pull-left btn-primary btn-lg' href='FindPeople.jsp'>Home Page</a>";
		request.setAttribute("message", tableData);
		getServletContext().getRequestDispatcher("/Output.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
