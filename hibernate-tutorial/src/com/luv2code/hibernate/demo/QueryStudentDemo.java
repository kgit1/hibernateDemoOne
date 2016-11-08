package com.luv2code.hibernate.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			List<Student> theStudents2 = readByName(session, "Daffy");
			List<Student> theStudents3 = readByNameOrEmail(session, "Daffy","popoil@gmail.com");
			List<Student> theStudents4 = readByNameAndEmailWildcard(session, "Mary","%yahoo%m");
			//display students
			printAllRows(theStudents);
			System.out.println("\n=====================");
			printAllRows(theStudents2);
			System.out.println("\n=====================");
			printAllRows(theStudents3);
			System.out.println("\n=====================");
			printAllRows(theStudents4);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			factory.close();
		}
		
	}

	/**
	 * @param session
	 * @return
	 */
	public static List<Student> readByNameAndEmailWildcard(Session session, String name, String emailWild) {
		List<Student> theStudents4 = 
				session.createQuery("from Student s where s.firstName='"
						+ name + "' or s.email like '" + emailWild + "'").getResultList();
		return theStudents4;
	}

	/**
	 * @param theStudents
	 */
	public static void printAllRows(List<Student> theStudents) {
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	/**
	 * @param session
	 * @return
	 */
	public static List<Student> readByNameOrEmail(Session session, String name, String email) {
		List<Student> theStudents = 
				session.createQuery("from Student s where s.firstName='"
						+ name + "' or s.email='" + email + "'").getResultList();
		return theStudents;
	}

	/**
	 * @param session
	 * @return
	 */
	public static List<Student> readByName(Session session, String name) {
		List<Student> theStudents = 
				session.createQuery("from Student s where s.firstName='"
						+ name + "'").getResultList();
		return theStudents;
	}

}
