/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import nz.co.ritc.classyfindz.jpa.entity.CategoryPageView;

/**
 * @author rahul
 *
 */
public interface CategoryPageViewRepository extends PagingAndSortingRepository<CategoryPageView, Long> {


	Page<CategoryPageView> findByTagNameIn(@Param("tags") Collection<String> tags, Pageable page);
}