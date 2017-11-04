package com.rudra.aks.bo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String userName;
	private String emailID;
	private String firstName;
	private String middleName;
	private String lastName;
	char type = 'U'; //U,S,A
	char status = 'A';//
	private String password;
	private String getCreatedBy;
	
	//Json for above properties of UserBO
	/*{
		"userId":"balck",
		"userName":"rudra",
		"firstName":"ankush",
		"lastName":"verman",
		"emailID":"aks@ru.com",
		"password":"1234"
	}*/
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getGetCreatedBy() {
		return getCreatedBy;
	}
	public void setGetCreatedBy(String getCreatedBy) {
		this.getCreatedBy = getCreatedBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public static class UserBuilder {
		public UserBO createUser(Object... userinfo) {
			UserBO userBO = new UserBO();
			userBO.setUserId((Integer)userinfo[0]);
			userBO.setUserName((String)userinfo[1]);
			userBO.setFirstName((String)userinfo[2]);
			userBO.setLastName((String)userinfo[3]);
			userBO.setEmailID((String)userinfo[4]);
			userBO.setPassword((String)userinfo[5]);
			return userBO;
		}
	}
}
