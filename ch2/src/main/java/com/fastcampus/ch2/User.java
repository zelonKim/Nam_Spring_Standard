package com.fastcampus.ch2;

import java.util.Arrays;
import java.util.Date;

public class User {
	private String id;
	private String pwd;
	private String name;
	private String email;
	//@DateTimeFormat(pattern="yyyy-MM-dd")  // 지정된 패턴 형식에 맞춰서 타입 변환을 함. (@InitBinder를 대신해서 사용 가능)
	private Date birth;
	private String[] hobby;
	private String[] sns;
	
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String[] getSns() {
		return sns;
	}
	public void setSns(String[] sns) {
		this.sns = sns;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", birth=" + birth + ", sns="
				+ Arrays.toString(sns) + "hobby=" + Arrays.toString(hobby) + "]";
	}
	
	
}
