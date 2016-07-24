/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import nz.co.ritc.classyfindz.jpa.entity.ColumnAdvert;
import nz.co.ritc.classyfindz.jpa.entity.DefaultSearchResultsView;
import nz.co.ritc.classyfindz.jpa.entity.SearchResultsWithTagNamesView;

/**
 * @author rahul
 *
 */
public interface SearchResultsWithTagNamesViewRepository extends PagingAndSortingRepository<SearchResultsWithTagNamesView, Long>  {

	@Query("SELECT v.columnAdvert FROM SearchResultsWithTagNamesView v"
			+ " WHERE v.listingCategory = :category AND v.tags.tagName IN :tags")
	public Page<ColumnAdvert> findByListingCategoryAndTagName(@Param("category") String listingCategory, @Param("tags") List<String> tags, Pageable pageable);

}