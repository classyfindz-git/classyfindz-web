/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="tags")
public class Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String tag_name;
	private String md5_checksum;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getMd5_checksum() {
		return md5_checksum;
	}
	public void setMd5_checksum(String md5_checksum) {
		this.md5_checksum = md5_checksum;
	}

	@Override
	public String toString() {
		return "Tags [id=" + id + ", tag_name=" + tag_name + ", md5_checksum=" + md5_checksum + "]";
	}

}