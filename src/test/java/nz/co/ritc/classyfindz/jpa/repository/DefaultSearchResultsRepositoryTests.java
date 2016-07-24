/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.TransformerUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

	@Test
	@DatabaseSetup(value = "/dbunit/default_advert_scores.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/default_advert_scores.xml", type= DatabaseOperation.DELETE)
	public void testFindAll() {
		final Iterable<DefaultSearchResultsView> results = service.findAll();
		Assert.assertNotNull(results);
		Collection resultsCollection = CollectionUtils.collect(results.iterator(), TransformerUtils.nopTransformer());
		Assert.assertEquals(2, resultsCollection.size());
	}

	@Test
	@DatabaseSetup(value = "/dbunit/adverts_sorted_by_category.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/adverts_sorted_by_category.xml", type= DatabaseOperation.DELETE)
	public void testSortedByCategory() {
		final Iterable<DefaultSearchResultsView> results = service.findAll();
		Assert.assertNotNull(results);
		String[] categoryList = {"category a", "category b", "category b",  "category c"};
		int i=0;
		for (DefaultSearchResultsView defaultSearchResultsView : results) {
			Assert.assertEquals(categoryList[i], defaultSearchResultsView.getListingCategory());
			i++;
		}
		Assert.assertEquals(4, i);
	}
	
	@Test
	@DatabaseSetup(value = "/dbunit/default_adverts_find_by_category.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/default_adverts_find_by_category.xml", type= DatabaseOperation.DELETE)
	public void testFindByCategory() {
		final Page<DefaultSearchResultsView> resultsEntry = service.findByListingCategory("category b", new PageRequest(0, 5));
		Assert.assertNotNull(resultsEntry);
		Assert.assertEquals(3, resultsEntry.getNumberOfElements());
	}
	
}