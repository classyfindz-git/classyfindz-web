/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import javax.persistence.Column;
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
	@Column(name="tag_id")
	private long id;
	@Column(name="tag_name")
	private String tagName;
	@Column(name="md5_checksum")
	private String md5Checksum;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tag_name) {
		this.tagName = tag_name;
	}
	public String getMd5Checksum() {
		return md5Checksum;
	}
	public void setMd5Checksum(String md5_checksum) {
		this.md5Checksum = md5_checksum;
	}

	@Override
	public String toString() {
		return "Tags [id=" + id + ", tag_name=" + tagName + ", md5_checksum=" + md5Checksum + "]";
	}

}