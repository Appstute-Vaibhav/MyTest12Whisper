/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to User table.
 */

public class UserMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=23. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO User (FirstName, LastName, UserName, Email, DisplayName, MobileNumber, Password, RoleId, DateOfBirth, Gender, Address1, Address2, Country, State, Place, Zip, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES (:firstName, :lastName, :userName, :email, :displayName, :mobileNumber, :password, :roleId, :dateOfBirth, :gender, :address1, :address2, :country, :state, :place, :zip, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Insert query for the table.
	 * Total fields in the table=23. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT1_SQL = "INSERT INTO UserRoleMapping (UserId, RoleId, Status ,CreatedOn) VALUES (:id,:roleId,:status,now())";
	
	/**
	 * Insert query for the table.
	 * Total fields in the table=4. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERTTEMP_SQL = "INSERT INTO TempUser(UserId,RoleId,EmailId,Password) VALUES (:id,:roleId,:email,:password)";
	
	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE User SET FirstName = :firstName, LastName = :lastName, UserName = :userName, Email = :email, DisplayName = :displayName, MobileNumber = :mobileNumber, Password = :password, RoleId = :roleId, DateOfBirth = :dateOfBirth, Gender = :gender, Address1 = :address1, Address2 = :address2, Country = :country, State = :state, Place = :place, Zip = :zip, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE1_SQL = "UPDATE User SET Password = :password, RoleId = :roleId, ModifiedOn = now() WHERE Id = :id";
	
	/**
	 * Update query for the table.
	 */
	public static final String SELECTROLE_SQL = "SELECT RoleId FROM UserRole where RoleName like ?";
	
	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE UserRoleMapping SET Status = ? WHERE UserId IN(?) and RoleId IN (?)";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE1_SQL = "Delete From TempUser WHERE UserId IN(?) and RoleId IN (?)";

	
	/**
	 * Select query to retrieve all active records from the table.
	 */
	public static final String SELECTALLACTIVE_SQL = "SELECT * FROM User WHERE Status=1 and if( ? is not null, RoleId = ?, true)";

	/**
	 * Select query to retrieve all active & inactive records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT u.id,u.FirstName,u.LastName,u.Email,u.DisplayName,u.MobileNumber,urm.RoleId,urm.Status FROM User u, UserRoleMapping urm where urm.RoleId != 4 and urm.RoleId != 1 and u.id = urm.UserId";

	/**
	 * Select query to retrieve all active & inactive records from the table.
	 */
	public static final String SELECTENDUSER_SQL = "SELECT u.id,u.FirstName,u.LastName,u.Email,u.DisplayName,u.MobileNumber,urm.RoleId,urm.Status FROM User u, UserRoleMapping urm where u.id = ? and urm.UserId = u.id and urm.RoleId = 4";

	
	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT * FROM User WHERE Id = ? and Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTTEMP_SQL = "SELECT * FROM TempUser WHERE Id = ? and UserId = ?";
	
	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT1_SQL = "SELECT * FROM User12Whispers WHERE if( ? is not null, SubmittedById = ?, true) and if( ? is not null, DateWhispersSubmitted like concat(date(?), '%'), true)";
	
	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT * FROM  User WHERE  if( ? is not null, Id = ?, true) and if( ? is not null, FirstName = ?, true) and if( ? is not null, LastName = ?, true) and if( ? is not null, UserName = ?, true) and if( ? is not null, Email = ?, true) and if( ? is not null, DisplayName = ?, true) and if( ? is not null, MobileNumber = ?, true) and if( ? is not null, Password = ?, true) and if( ? is not null, RoleId = ?, true) and if( ? is not null, DateOfBirth like concat(date(?), '%'), true) and if( ? is not null, Gender = ?, true) and if( ? is not null, Address1 = ?, true) and if( ? is not null, Address2 = ?, true) and if( ? is not null, Country = ?, true) and if( ? is not null, State = ?, true) and if( ? is not null, Place = ?, true) and if( ? is not null, Zip = ?, true) and if( ? is not null, ModifiedOn = ?, true) and if( ? is not null, ModifiedBy = ?, true) and if( ? is not null, CreatedBy = ?, true) and Status = 1";

	/**
	 * Select query to retrieve record by username from the table.
	 */
	public static final String SELECTBYUSERNAME_SQL = "select u.Id,u.FirstName,u.LastName,u.DisplayName,u.Email,u.UserName,u.Password,u.DateOfBirth,u.MobileNumber,u.Gender,urm.RoleId,urm.Status,(WEEK(u.CreatedOn))WeekId,(DAYOFWEEK(u.CreatedOn))DayOfWeek from User u,UserRoleMapping urm where urm.UserId = u.Id and u.UserName = ?";

}// Class ends