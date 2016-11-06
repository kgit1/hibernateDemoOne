package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//class SessionFactory 
		//reads the hibernate config file
		//creates Session object
		//heavy-weight object
		//only create once in your app
		
		//Session
		//wraps a JDBC connection
		//main object used to save\retrieve objects
		//short-lived object
		//retrieved from Seccion factory
		
		//create session factory
		//if configure() is empty - 
		//will use default "hibernate.cfg.xml" from src root
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			//use the session object to save java object
			
			//create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Paul", "Walker", "paul@luv2code.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally{
			factory.close();
		}
	}

}
