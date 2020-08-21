package org.green.student_manager;

import org.springframework.stereotype.Repository;

/*
student_id		INT			PRIMARY KEY		AUTO_INCREMENT,
student_name	CHAR(10)	NOT NULL,
student_tel		CHAR(13)	NOT	NULL,
student_grade	INT			NOT	NULL,
student_class	CHAR(1)		NOT	NULL
*/

@Repository
public class Student {
	private int student_id;
	private String student_name;
	private String student_tel;
	private int student_grade;
	private String student_class;

	public Student() {
	}

	public Student(String student_name, String student_tel, int student_grade, String student_class) {
		this.student_name = student_name;
		this.student_tel = student_tel;
		this.student_grade = student_grade;
		this.student_class = student_class;
	}

	public Student(int student_id, String student_name, String student_tel, int student_grade, String student_class) {
		this.student_id = student_id;
		this.student_name = student_name;
		this.student_tel = student_tel;
		this.student_grade = student_grade;
		this.student_class = student_class;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_tel() {
		return student_tel;
	}

	public void setStudent_tel(String student_tel) {
		this.student_tel = student_tel;
	}

	public int getStudent_grade() {
		return student_grade;
	}

	public void setStudent_grade(int student_grade) {
		this.student_grade = student_grade;
	}

	public String getStudent_class() {
		return student_class;
	}

	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}

}
