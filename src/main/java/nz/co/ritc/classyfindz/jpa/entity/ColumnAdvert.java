/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="column_advert")
public class ColumnAdvert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="column_advert_id")
	private long id;

	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "advert_id")
	private Advert main;

	@Column(name="heading")
	private String heading;

	@Column(name="body")
	private String body;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Advert getMain() {
		return main;
	}

	public void setMain(Advert main) {
		this.main = main;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}