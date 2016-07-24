package nz.co.ritc.classyfindz.jpa.repository;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import nz.co.ritc.classyfindz.ApplicationTests;
import nz.co.ritc.classyfindz.jpa.entity.ColumnAdvert;
import nz.co.ritc.classyfindz.jpa.entity.SearchResultsWithTagNamesView;

public class SearchResultsWithTagNamesViewRepositoryTest extends ApplicationTests {

	@Autowired
	SearchResultsWithTagNamesViewRepository repository;

	@Test
	@DatabaseSetup(value = "/dbunit/search_results_with_tags.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/search_results_with_tags.xml", type= DatabaseOperation.DELETE)
	public void test() {
		Page<ColumnAdvert> page = repository.findByListingCategoryAndTagName("category a", Arrays.asList("test tag 1"), new PageRequest(0,3));
		Assert.assertNotNull(page);
		Assert.assertEquals(1, page.getTotalElements());
		//Assert.assertEquals(1, page.getContent().get(0).getRank());
		Assert.assertEquals("my test advert", page.getContent().get(0).getHeading());
	}

	/**
	 * <pre>
2	10001	10001	category a	10001
3	10003	10003	category a	10002
	 * </pre>
	 */
	@Test
	@DatabaseSetup(value = "/dbunit/search_results_with_tags.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/search_results_with_tags.xml", type= DatabaseOperation.DELETE)
	public void testTwoTags() {
		Page<ColumnAdvert> page = repository.findByListingCategoryAndTagName("category a", Arrays.asList("test tag 1", "test tag 2"), new PageRequest(0,3,
				new Sort(new Order(Direction.ASC, "rank"))));
		Assert.assertNotNull(page);
		Assert.assertEquals(2, page.getTotalElements());
		final List<ColumnAdvert> adverts = page.getContent();
		Assert.assertTrue(StringUtils.join(CollectionUtils.collect(adverts, new Transformer() {
			@Override
			public Object transform(Object arg) {
				return ((ColumnAdvert)arg).getHeading();
			}
		})),CollectionUtils.countMatches(adverts, new Predicate() {
			String[] expectedAdverts = new String[] {"my test advert", "my third test advert"};
			@Override
			public boolean evaluate(Object arg) {
				final String heading = ((ColumnAdvert)arg).getHeading();
				final boolean contains = Arrays.asList(expectedAdverts).contains(heading);
				return contains;
			}
		}) == adverts.size());
	}

	@Test
	@DatabaseSetup(value = "/dbunit/search_results_with_tags.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/search_results_with_tags.xml", type= DatabaseOperation.DELETE)
	public void testIncorrectTag() {
		Page<ColumnAdvert> page = repository.findByListingCategoryAndTagName("category a", Arrays.asList("no such tag"), new PageRequest(0,3));
		Assert.assertNotNull(page);
		Assert.assertEquals(0, page.getTotalElements());
	}
	
}
