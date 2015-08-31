

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
				tableData += "\r<table class=table border=1>";
				tableData += "<tr style=background-color:green>";
				tableData += "<th style='text-align:center'>";
				tableData += "First Name";
				tableData += "</th>";
				tableData += "<th style='text-align:center'>";
				tableData += "Last Name";
				tableData += "</th>";
				tableData += "</tr>\r";

				for (People p : pList) {
					tableData += "<tr class='info'>";
					tableData += "<td>";
					tableData += "<a href='Details?peopleId=" + p.getId()+"'>" +p.getFirstname()+"</a>";
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
					tableData += "<a href='Details?compId=" + c.getCompanyid() + "'>" + c.getCompanyid() + "</a>";
					tableData += "</td>";
					tableData += "<td>";
					tableData += c.getCompany();
					tableData += "</td>";
					tableData += "</tr>\r";
				}
				tableData += "</table>\r";
			}else {
				tableData += "<p> No Companies Found </p>";
			}
			tableData +="<a class='btn pull-left btn-primary btn-lg' href='FindPeople.jsp'>Home Page</a>";
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
