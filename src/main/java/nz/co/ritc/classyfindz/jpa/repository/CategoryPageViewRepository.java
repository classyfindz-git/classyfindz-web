/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import nz.co.ritc.classyfindz.jpa.entity.CategoryPageView;

/**
 * @author rahul
 *
 */
public interface CategoryPageViewRepository extends PagingAndSortingRepository<CategoryPageView, Long> {


	@Query("SELECT NEW CategoryPageView(c.listingCategory, SUM(c.count)) FROM CategoryPageView c GROUP BY c.listingCategory ORDER BY SUM(c.count) DESC")
	Page<CategoryPageView> findGroupedByCategoryName(Pageable page);

	Page<CategoryPageView> findByTagNameIn(@Param("tags") Collection<String> tags, Pageable page);

	@Query("SELECT DISTINCT c.listingCategory FROM CategoryPageView c WHERE c.tagName IN :tags")
	List<String> findCategoryNamesByTagNameIn(@Param("tags") Collection<String> tags);

	@Query("SELECT  DISTINCT c.listingCategory FROM CategoryPageView c")
	List<String> findCategoryNames();

}