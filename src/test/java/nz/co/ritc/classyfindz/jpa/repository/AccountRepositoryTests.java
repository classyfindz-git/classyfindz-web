/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import nz.co.ritc.classyfindz.ApplicationTests;
import nz.co.ritc.classyfindz.jpa.entity.Account;

/**
 * @author rahul
 *
 */
public class AccountRepositoryTests extends ApplicationTests {

	@Autowired
	AccountRepository service;

	@Test
	@DatabaseSetup(value = "/dbunit/account.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/account.xml", type= DatabaseOperation.DELETE)
	public void test() {
		final Account account = service.findByUsername("test@email.com");
		Assert.assertNotNull(account);
		Assert.assertEquals(1, account.getId());
	}

}
