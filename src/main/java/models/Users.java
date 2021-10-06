package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Remember that Hibernate is designed to help us fix the paradigm mismatch between SQL and Java, which
 * is an OOP language. As such, we don't have to write any SQL in order to persist records. We can instead
 * let Hibernate take care of generating the queries. That said, in order for this to happen, we have to provide
 * some "mappings" from Hibernate so that the ORM frameworks knows how a Java model correlates to a table in
 * our DB. In fact, we have to tell Hibernate whether or not a model should even represent a table in our DB.
 * 
 * We can do this via several annotations.
 */

// Entity makes this class as an entity for us, meaning that we intend to map this class to a table in our DB.
@Entity

// The Table annotations allow us to specify information about the table we want our associated with our model.
// You can, for instance, specify the table's name (the DB table). That said, you don't have to specify the table
// name as Hibernate will just use the model's name as the table name if you specify no name.
@Table(name = "users")
public class Users {
	/*
	 * The Id annotation denotes that we wish to use this field as a primary key on this table.
	 */
	@Id
	/*
	 * The Column annotation allows us to specify that this field should be a column on my table.
	 */
	@Column(name="CID")
	private int cid;
	@Column (name="Name")
	private String name;
	@Column(name="NonSecurePassword")
	private String nonSecurePassword;
	@Column(name="AdminLevel")
	private int adminlevel;
	
	public Users() {
		super();
	}
	
	public Users(int cid, String name, String nonSecurePassword, int adminlevel) {
		super();
		this.cid = cid;
		this.name = name;
		this.nonSecurePassword = nonSecurePassword;
		this.adminlevel = adminlevel;
	}

	@Override
	public String toString() {
		return "Users [cid=" + cid + ", name=" + name + ", nonSecurePassword=" + nonSecurePassword + ", adminlevel="
				+ adminlevel + ", getCid()=" + getCid() + ", getName()=" + getName() + ", getNonSecurePassword()="
				+ getNonSecurePassword() + ", getAdminlevel()=" + getAdminlevel() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNonSecurePassword() {
		return nonSecurePassword;
	}
	public void setNonSecurePassword(String nonSecurePassword) {
		this.nonSecurePassword = nonSecurePassword;
	}
	public int getAdminlevel() {
		return adminlevel;
	}
	public void setAdminlevel(int adminlevel) {
		this.adminlevel = adminlevel;
	}
	
}
