/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="v_default_advert_scores")
public class DefaultSearchResultsView {

	@Id
	@Column(name="rank", insertable=false, updatable=false)
	private long rank;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="advert_registry_id", updatable=false, insertable=false)
	private AdvertRegistry advertEntry;

	@Column(name="category_name", insertable=false, updatable=false)
	private String listingCategory;
	
	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public AdvertRegistry getAdvertEntry() {
		return advertEntry;
	}

	public void setAdvertEntry(AdvertRegistry advertEntry) {
		this.advertEntry = advertEntry;
	}

	public String getListingCategory() {
		return listingCategory;
	}

	public void setListingCategory(String listingCategory) {
		this.listingCategory = listingCategory;
	}

}