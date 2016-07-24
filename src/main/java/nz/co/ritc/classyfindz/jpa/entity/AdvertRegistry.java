/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author rahul
 *
 */
@Entity
@Table(name="advert_registry")
public class AdvertRegistry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="advert_registry_id")
	private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advert_id")
    private Advert advert;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable
    (
        name="adv_reg_has_lis_cat",
        joinColumns={ @JoinColumn(name="advert_registry_id", referencedColumnName="advert_registry_id") },
        inverseJoinColumns={ @JoinColumn(name="listing_category_id", referencedColumnName="listing_category_id") },
        uniqueConstraints=@UniqueConstraint(columnNames={"advert_registry_id", "listing_category_id"})
    )    
    private List<ListingCategory> listingCategoryList;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Advert getAdvert() {
		return advert;
	}
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	public List<ListingCategory> getListingCategoryList() {
		return listingCategoryList;
	}
	public void setListingCategoryList(List<ListingCategory> listingCategoryList) {
		this.listingCategoryList = listingCategoryList;
	}
}