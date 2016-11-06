package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// create 3 student objects
			Student student1 = new Student("Vin", "Diezel", "riddik@yahoo.com");
			Student student2 = new Student("John", "Doe", "john@yahoo.com");
			Student student3 = new Student("Marry", "Public", "republic@yahoo.com");

			 //start transaction
			 session.beginTransaction();
			
			 //save student object
			 session.save(student1);
			 session.save(student2);
			 session.save(student3);
			
			 //commit transaction
			 session.getTransaction().commit();

			addToDB(session, createStudent("Nika", "Props", "popoil@gmail.com"));

		} finally {
			factory.close();
		}

	}

	static Student createStudent(String firstName, String lastName, String email) {
		return new Student(firstName, lastName, email);
	}

	static void addToDB(Session session, Student student) {
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
	}
}
