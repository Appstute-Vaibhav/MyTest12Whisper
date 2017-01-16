/**
 * Created on 15 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * User entity
 */
public class User {
	/**
	 * User Id
	 */
	private Integer id		= null;

	/**
	 * First Name
	 */
	private String firstName		= null;

	/**
	 * Last Name
	 */
	private String lastName		= null;

	/**
	 * Username
	 */
	private String userName		= null;

	/**
	 * Email Id
	 */
	private String email		= null;

	/**
	 * Display Name
	 */
	private String displayName		= null;

	/**
	 * Mobile Number
	 */
	private String mobileNumber		= null;

	/**
	 * Password
	 */
	private String password		= null;

	/**
	 * Role Id
	 */
	private Integer roleId		= null;

	/**
	 * Birthdate
	 */
	private Timestamp dateOfBirth		= null;

	/**
	 * Gender
	 */
	private String gender		= null;

	/**
	 * Address
	 */
	private String address1		= null;

	/**
	 * Address
	 */
	private String address2		= null;

	/**
	 * Country Name
	 */
	private String country		= null;

	/**
	 * State
	 */
	private String state		= null;

	/**
	 * Place
	 */
	private String place		= null;

	/**
	 * Pincode
	 */
	private Integer zip		= null;

	/**
	 * Modified On(Date)
	 */
	private Timestamp modifiedOn		= null;

	/**
	 * Modified By(userId)
	 */
	private Integer modifiedBy		= null;

	/**
	 * Action
	 */
	private String action		= null;

	/**
	 * Created By (User Id)
	 */
	private Integer createdBy		= null;

	/**
	 * Created On (Date)
	 */
	private Timestamp createdOn		= null;

	/**
	 * Status (Active or Inactive)
	 */
	private Integer status		= null;

	/**
	 * Default constructor
	 */
	public User() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The User Id
	 * @param firstName		The First Name
	 * @param lastName		The Last Name
	 * @param userName		The Username
	 * @param email		The Email Id
	 * @param displayName		The Display Name
	 * @param mobileNumber		The Mobile Number
	 * @param password		The Password
	 * @param roleId		The Role Id
	 * @param dateOfBirth		The Birthdate
	 * @param gender		The Gender
	 * @param address1		The Address
	 * @param address2		The Address
	 * @param country		The Country Name
	 * @param state		The State
	 * @param place		The Place
	 * @param zip		The Pincode
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By (User Id)
	 * @param createdOn		The Created On (Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public User(
					Integer id,
					String firstName,
					String lastName,
					String userName,
					String email,
					String displayName,
					String mobileNumber,
					String password,
					Integer roleId,
					Timestamp dateOfBirth,
					String gender,
					String address1,
					String address2,
					String country,
					String state,
					String place,
					Integer zip,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.firstName		= firstName;
		this.lastName		= lastName;
		this.userName		= userName;
		this.email		= email;
		this.displayName		= displayName;
		this.mobileNumber		= mobileNumber;
		this.password		= password;
		this.roleId		= roleId;
		this.dateOfBirth		= dateOfBirth;
		this.gender		= gender;
		this.address1		= address1;
		this.address2		= address2;
		this.country		= country;
		this.state		= state;
		this.place		= place;
		this.zip		= zip;
		this.modifiedOn		= modifiedOn;
		this.modifiedBy		= modifiedBy;
		this.action		= action;
		this.createdBy		= createdBy;
		this.createdOn		= createdOn;
		this.status		= status;
	}


	/**
	 * Gets the value of the id property.
	 *
	 * @return 	 Returns the id 	 {@link Integer }
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param id 	 The id to set 	 {@link Integer }
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the value of the firstName property.
	 *
	 * @return 	 Returns the firstName 	 {@link String }
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the value of the firstName property.
	 *
	 * @param firstName 	 The firstName to set 	 {@link String }
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the value of the lastName property.
	 *
	 * @return 	 Returns the lastName 	 {@link String }
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the value of the lastName property.
	 *
	 * @param lastName 	 The lastName to set 	 {@link String }
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the value of the userName property.
	 *
	 * @return 	 Returns the userName 	 {@link String }
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the value of the userName property.
	 *
	 * @param userName 	 The userName to set 	 {@link String }
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the value of the email property.
	 *
	 * @return 	 Returns the email 	 {@link String }
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 *
	 * @param email 	 The email to set 	 {@link String }
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the value of the displayName property.
	 *
	 * @return 	 Returns the displayName 	 {@link String }
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the value of the displayName property.
	 *
	 * @param displayName 	 The displayName to set 	 {@link String }
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the value of the mobileNumber property.
	 *
	 * @return 	 Returns the mobileNumber 	 {@link String }
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Sets the value of the mobileNumber property.
	 *
	 * @param mobileNumber 	 The mobileNumber to set 	 {@link String }
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Gets the value of the password property.
	 *
	 * @return 	 Returns the password 	 {@link String }
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 *
	 * @param password 	 The password to set 	 {@link String }
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the value of the roleId property.
	 *
	 * @return 	 Returns the roleId 	 {@link Integer }
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * Sets the value of the roleId property.
	 *
	 * @param roleId 	 The roleId to set 	 {@link Integer }
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the value of the dateOfBirth property.
	 *
	 * @return 	 Returns the dateOfBirth 	 {@link Timestamp }
	 */
	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the value of the dateOfBirth property.
	 *
	 * @param dateOfBirth 	 The dateOfBirth to set 	 {@link Timestamp }
	 */
	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the value of the gender property.
	 *
	 * @return 	 Returns the gender 	 {@link String }
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the value of the gender property.
	 *
	 * @param gender 	 The gender to set 	 {@link String }
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the value of the address1 property.
	 *
	 * @return 	 Returns the address1 	 {@link String }
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Sets the value of the address1 property.
	 *
	 * @param address1 	 The address1 to set 	 {@link String }
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * Gets the value of the address2 property.
	 *
	 * @return 	 Returns the address2 	 {@link String }
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Sets the value of the address2 property.
	 *
	 * @param address2 	 The address2 to set 	 {@link String }
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * Gets the value of the country property.
	 *
	 * @return 	 Returns the country 	 {@link String }
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the value of the country property.
	 *
	 * @param country 	 The country to set 	 {@link String }
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the value of the state property.
	 *
	 * @return 	 Returns the state 	 {@link String }
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 *
	 * @param state 	 The state to set 	 {@link String }
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the value of the place property.
	 *
	 * @return 	 Returns the place 	 {@link String }
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Sets the value of the place property.
	 *
	 * @param place 	 The place to set 	 {@link String }
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * Gets the value of the zip property.
	 *
	 * @return 	 Returns the zip 	 {@link Integer }
	 */
	public Integer getZip() {
		return zip;
	}

	/**
	 * Sets the value of the zip property.
	 *
	 * @param zip 	 The zip to set 	 {@link Integer }
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	/**
	 * Gets the value of the modifiedOn property.
	 *
	 * @return 	 Returns the modifiedOn 	 {@link Timestamp }
	 */
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * Sets the value of the modifiedOn property.
	 *
	 * @param modifiedOn 	 The modifiedOn to set 	 {@link Timestamp }
	 */
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * Gets the value of the modifiedBy property.
	 *
	 * @return 	 Returns the modifiedBy 	 {@link Integer }
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the value of the modifiedBy property.
	 *
	 * @param modifiedBy 	 The modifiedBy to set 	 {@link Integer }
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the value of the action property.
	 *
	 * @return 	 Returns the action 	 {@link String }
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the value of the action property.
	 *
	 * @param action 	 The action to set 	 {@link String }
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the value of the createdBy property.
	 *
	 * @return 	 Returns the createdBy 	 {@link Integer }
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the value of the createdBy property.
	 *
	 * @param createdBy 	 The createdBy to set 	 {@link Integer }
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the value of the createdOn property.
	 *
	 * @return 	 Returns the createdOn 	 {@link Timestamp }
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the value of the createdOn property.
	 *
	 * @param createdOn 	 The createdOn to set 	 {@link Timestamp }
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the value of the status property.
	 *
	 * @return 	 Returns the status 	 {@link Integer }
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 *
	 * @param status 	 The status to set 	 {@link Integer }
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Returns the String representation of the User entity.
	 *
	 * @return 	 the string containing the User details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("User Id =" + id + "|");
		strBufTemp.append ("First Name =" + firstName + "|");
		strBufTemp.append ("Last Name =" + lastName + "|");
		strBufTemp.append ("Username =" + userName + "|");
		strBufTemp.append ("Email Id =" + email + "|");
		strBufTemp.append ("Display Name =" + displayName + "|");
		strBufTemp.append ("Mobile Number =" + mobileNumber + "|");
		strBufTemp.append ("Password =" + password + "|");
		strBufTemp.append ("Role Id =" + roleId + "|");
		strBufTemp.append ("Birthdate =" + dateOfBirth + "|");
		strBufTemp.append ("Gender =" + gender + "|");
		strBufTemp.append ("Address =" + address1 + "|");
		strBufTemp.append ("Address =" + address2 + "|");
		strBufTemp.append ("Country Name =" + country + "|");
		strBufTemp.append ("State =" + state + "|");
		strBufTemp.append ("Place =" + place + "|");
		strBufTemp.append ("Pincode =" + zip + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By (User Id) =" + createdBy + "|");
		strBufTemp.append ("Created On (Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}