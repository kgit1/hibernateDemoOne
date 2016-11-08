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

public class DeleteStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;
			// long way to delete object
			// retrieve object
			// it persisted so changes made on it will be transfered to database
			// on commit

			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Get complete: " + myStudent);

			// delete object
			// System.out.println("Deleting student...");
			// session.delete(myStudent);

			// commit the transaction
			session.getTransaction().commit();

			// NEW CODE
			// short way to delete on the fly
			// just session.createQuery("delete from Student where id=3")
			// .executeUpdate();

			// get session
			session = factory.getCurrentSession();
			// begin transaction
			session.beginTransaction();

			// delete object
			session.createQuery("delete from Student where id=3")
					.executeUpdate();

			// commit and end transaction
			session.getTransaction().commit();
			
			deleteStudentById(factory, 5);

			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

	static void deleteStudentById(SessionFactory factory, int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from Student where id=" + id)
				.executeUpdate();
		session.getTransaction().commit();
	}
}
