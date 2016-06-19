/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import nz.co.ritc.classyfindz.jpa.entity.Tags;

/**
 * @author rahul
 *
 */
public interface TagsRepository extends PagingAndSortingRepository<Tags, Long> {

	public List<Tags> findByTag_nameLike(String tag_name, Pageable pageable);
}