/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="v_advert_scores_with_tags")
public class SearchResultsWithTagNamesView {

	@Id
	@Column(name="rank", insertable=false, updatable=false)
	private long rank;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="advert_registry_id", updatable=false, insertable=false)
	private AdvertRegistry advertEntry;

	@Column(name="category_name", insertable=false, updatable=false)
	private String listingCategory;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="column_advert_id", updatable=false, insertable=false)
	private ColumnAdvert columnAdvert;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tags_id", updatable=false, insertable=false)
	private Tags tags;	

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

	public ColumnAdvert getColumnAdvert() {
		return columnAdvert;
	}

	public void setColumnAdvert(ColumnAdvert columnAdvert) {
		this.columnAdvert = columnAdvert;
	}

	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}


}