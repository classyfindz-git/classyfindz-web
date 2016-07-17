/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nz.co.ritc.classyfindz.jpa.entity.Account;

/**
 * @author rahul
 *
 */
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("select u.account from Users u where u.username = :username")
	public Account findByUsername(@Param("username")String username);
}