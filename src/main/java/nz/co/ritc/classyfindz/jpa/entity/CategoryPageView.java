/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="v_category_pages")
public class CategoryPageView {

	@Id
	@Column(name="category_name", insertable=false, updatable=false)
	private String listingCategory;
	
	@Column(name="count", insertable=false, updatable=false)
	private int count;

	@Column(name="tag_name", insertable=false, updatable=false)
	private String tagName;

	public String getListingCategory() {
		return listingCategory;
	}

	public void setListingCategory(String listingCategory) {
		this.listingCategory = listingCategory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}