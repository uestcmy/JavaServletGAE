package com.appspot.myjavaservlet;

public class Student {
	private String name;
	private String school;
	private String major;
	private String ipaddr;
	private long year;

	public Student(String name, String school,String major, long year,String ipaddr) {
		super();
		this.name = name;
		this.school = school;
		this.major = major;
		this.year = year;
		this.ipaddr = ipaddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setUrl(String school) {
		this.school = school;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	public String getIP() {
		return ipaddr;
	}

	public void setIP(String ipaddr) {
		this.ipaddr = ipaddr;
	}
}
