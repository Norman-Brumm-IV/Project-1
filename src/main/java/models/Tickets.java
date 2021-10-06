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
@Table(name = "tickets")
public class Tickets {
	/*
	 * The Id annotation denotes that we wish to use this field as a primary key on this table.
	 */
	@Id
	/*
	 * The Column annotation allows us to specify that this field should be a column on my table.
	 */
	@Column(name="TicketNumber")
	private int tNumber;
	@Column(name="CID")
	private int cid;
	@Column(name="Reason")
	private String reason;
	@Column
	private double amount;
	@Column(name="Reviewer")
	private Integer reviewer;
	@Column(name="Approved")
	private Boolean approved;
	@Column(name="ADReason")
	private String adReason;
	
	public int gettNumber() {
		return tNumber;
	}
	public void settNumber(int tNumber) {
		this.tNumber = tNumber;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Integer getReviewer() {
		return reviewer;
	}
	public void setReviewer(Integer reviewer) {
		this.reviewer = reviewer;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public String getAdReason() {
		return adReason;
	}
	public void setAdReason(String adReason) {
		this.adReason = adReason;
	}
	@Override
	public String toString() {
		return "Tickets [tNumber=" + tNumber + ", cid=" + cid + ", reason=" + reason + ", amount=" + amount
				+ ", reviewer=" + reviewer + ", approved=" + approved + ", adReason=" + adReason + ", gettNumber()="
				+ gettNumber() + ", getCid()=" + getCid() + ", getReason()=" + getReason() + ", getAmount()="
				+ getAmount() + ", getReviewer()=" + getReviewer() + ", getApproved()=" + getApproved()
				+ ", getAdReason()=" + getAdReason() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public Tickets() {
		super();
	}
	
	/**
	 * This method is only intended for creating a new ticket
	 * @param cid - Colonial ID Number
	 * @param reason - Why does this need to be reimbursed
	 * @param amount - the amount to be reimbursed
	 */
	public Tickets(int cid, String reason, double amount) {
		super();
		this.cid = cid;
		this.reason = reason;
		this.amount = amount;
	}
	public Tickets(int tNumber, int cid, String reason, double amount, Integer reviewer, Boolean approved,
			String adReason) {
		super();
		this.tNumber = tNumber;
		this.cid = cid;
		this.reason = reason;
		this.amount = amount;
		this.reviewer = reviewer;
		this.approved = approved;
		this.adReason = adReason;
	}
}
