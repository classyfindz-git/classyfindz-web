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
import nz.co.ritc.classyfindz.jpa.entity.DefaultSearchResultsView;

/**
 * @author rahul
 *
 */
public class DefaultSearchResultsRepositoryTests extends ApplicationTests {

	@Autowired
	DefaultSearchResultsViewRepository service;
	
	@Test
	@DatabaseSetup(value = "/dbunit/default_advert_scores.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/default_advert_scores.xml", type= DatabaseOperation.DELETE)
	public void test() {
		final DefaultSearchResultsView resultsEntry = service.findOne((long) 1);
		Assert.assertNotNull(resultsEntry);
	}

}
