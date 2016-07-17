/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="users")
public class Users {
	
	@Id
	@Column(name="username")
	private String username;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable
    (
        name="account_users",
        joinColumns={  @JoinColumn(name="username", referencedColumnName="username", unique=true) },
        inverseJoinColumns={ @JoinColumn(name="account_id", referencedColumnName="account_id") }
    )
	private Account account;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}