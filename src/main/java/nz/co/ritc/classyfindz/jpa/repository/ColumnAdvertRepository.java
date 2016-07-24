/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import nz.co.ritc.classyfindz.jpa.entity.Advert;
import nz.co.ritc.classyfindz.jpa.entity.ColumnAdvert;

/**
 * @author rahul
 *
 */
public interface ColumnAdvertRepository extends PagingAndSortingRepository<ColumnAdvert, String> {
	
	public List<ColumnAdvert> findByMainIn(Collection<Advert> mains);
}