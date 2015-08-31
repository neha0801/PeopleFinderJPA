package customTools;
import java.util.List;

import model.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DBUtil {
private static final EntityManagerFactory emf = 
Persistence.createEntityManagerFactory("PeopleFinder");
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}

	
	public static List<People> searchEmployees(String text) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select p from People p where p.lastname like '%" + text  +"%'";
		System.out.println(sql);
		TypedQuery<People> query= em.createQuery(sql, People.class);
		List<People> people;
		try{
			people=query.getResultList();
			if(people==null||people.isEmpty())
				people=null;
		}finally{
			em.close();
		}		
		return people;
	}
	public static List<Company> searchCompanies(String text) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select c from Company c where c.company like '%" + text  +"%'";
		System.out.println(sql);
		TypedQuery<Company> query= em.createQuery(sql, Company.class);
		List<Company> company;
		try{
			company=query.getResultList();
			if(company==null||company.isEmpty())
				company=null;
		}finally{
			em.close();
		}		
		return company;
	}
}