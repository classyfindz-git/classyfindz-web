/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import nz.co.ritc.classyfindz.jpa.entity.DefaultSearchResultsView;

/**
 * @author rahul
 *
 */
public interface DefaultSearchResultsViewRepository extends PagingAndSortingRepository<DefaultSearchResultsView, Long>  {

	public Page<DefaultSearchResultsView> findByListingCategory(String listingCategory, Pageable pageable);

}