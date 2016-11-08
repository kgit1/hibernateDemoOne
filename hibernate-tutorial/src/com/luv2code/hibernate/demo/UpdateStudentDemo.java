package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;
			//long way to update object
			//retrieve object
			//it persisted so changes made on it will be transfered to database
			//on commit
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Get complete: " + myStudent);

			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			// commit the transaction
			session.getTransaction().commit();

			// NEW CODE
			//short way to update on the fly
			//just session.createQuery("update Student set email='foo@gmail.com'")
			//.executeUpdate();
			
			// get session
			session = factory.getCurrentSession();
			// begin transaction
			session.beginTransaction();
			
			//update object
			session.createQuery("update Student set email='foo@gmail.com'")
					.executeUpdate();

			// commit and end transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
