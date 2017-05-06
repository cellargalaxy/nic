package nic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Nicer {
	public static final int temp = 0;
	public static final int pass = 1;
	public static final int isAdmin = 1;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");
	
	private int grade;
	private long id;
	private String name;
	private String sex;
	private String college;
	private String className;
	private long phone;
	private int shortPhone;
	private long qq;
	private Date birthday;
	private String introduction;
	private String password;
	
	private int admin;
	private int status;
	
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getCollege() {
		return college;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public long getPhone() {
		return phone;
	}
	
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public int getShortPhone() {
		return shortPhone;
	}
	
	public void setShortPhone(int shortPhone) {
		this.shortPhone = shortPhone;
	}
	
	public long getQq() {
		return qq;
	}
	
	public void setQq(long qq) {
		this.qq = qq;
	}
	
	public String getBirthday() {
		return SIMPLE_DATE_FORMAT.format(birthday);
	}
	
	public void setBirthday(String birthday) throws ParseException {
		this.birthday = SIMPLE_DATE_FORMAT.parse(birthday);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAdmin() {
		return admin;
	}
	
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@Override
	public String toString() {
		return "Nicer{" +
				"grade=" + grade +
				", id=" + id +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", college='" + college + '\'' +
				", className='" + className + '\'' +
				", phone=" + phone +
				", shortPhone=" + shortPhone +
				", qq=" + qq +
				", birthday=" + birthday +
				", password='" + password + '\'' +
				", admin=" + admin +
				", status=" + status +
				'}';
	}
}
