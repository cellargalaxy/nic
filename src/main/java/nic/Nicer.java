package nic;

import java.util.Date;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Nicer {
    public final static int tempStatus = 0;
    public final static int nicerStatus = 1;
    public final static int adminStatus = 2;

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

    private int status;

    public int getTempStatus() {
        return tempStatus;
    }

    public int getNicerStatus() {
        return nicerStatus;
    }

    public int getAdminStatus() {
        return adminStatus;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
