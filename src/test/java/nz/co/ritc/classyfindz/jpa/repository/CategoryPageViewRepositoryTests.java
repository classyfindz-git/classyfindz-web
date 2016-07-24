/**
 * 
 */
package nz.co.ritc.classyfindz.jpa.repository;

import java.util.Arrays;
import java.util.List;

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
import nz.co.ritc.classyfindz.jpa.entity.CategoryPageView;

/**
 * @author rahul
 *
 */
public class CategoryPageViewRepositoryTests extends ApplicationTests {

	@Autowired
	CategoryPageViewRepository repository;
	
	/**
	 * <pre>
			2	category b	test tag 1
			2	category d	test tag 2
			1	category a	test tag 1
			1	category b	test tag 2
			1	category c	test tag 3
			1	category c	test tag 1
			1	category e	test tag 2
			1	category f	test tag 3
			1	category g	test tag 3
	 * </pre>
	 */
	@Test
	@DatabaseSetup(value = "/dbunit/category_page_view.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/category_page_view.xml", type= DatabaseOperation.DELETE)
	public void test() {
		Page<CategoryPageView> page = repository.findAll(new PageRequest(0, 3, 
				new Sort(
					    new Order(Direction.DESC, "count"), 
					    new Order(Direction.ASC, "listingCategory")
					  )
				));
		Assert.assertNotNull(page);
		Assert.assertEquals(0, page.getNumber()); // Page number
		Assert.assertEquals(3, page.getSize()); // Number of elements in current page
		Assert.assertEquals(9, page.getTotalElements()); // Total number of elements
		Assert.assertEquals(3, page.getTotalPages()); // Total number of pages
		List<CategoryPageView> list = page.getContent();
		Assert.assertEquals("category b", list.get(0).getListingCategory());
		Assert.assertEquals(2, list.get(0).getCount());
		Assert.assertEquals("category d", list.get(1).getListingCategory());
		Assert.assertEquals(2, list.get(1).getCount());
		Assert.assertEquals("category a", list.get(2).getListingCategory());
		Assert.assertEquals(1, list.get(2).getCount());
	}

	@Test
	@DatabaseSetup(value = "/dbunit/category_page_view.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/category_page_view.xml", type= DatabaseOperation.DELETE)
	public void testSelectByTags() {
		Page<CategoryPageView> page = repository.findByTagNameIn(Arrays.asList("test tag 1", "test tag 2"), 
				new PageRequest(0, 3, new Sort(
					    new Order(Direction.DESC, "count"), 
					    new Order(Direction.ASC, "listingCategory")
					  )
				));
		Assert.assertNotNull(page);
		Assert.assertEquals(0, page.getNumber()); // Page number
		Assert.assertEquals(3, page.getSize()); // Number of elements in current page
		Assert.assertEquals(6, page.getTotalElements()); // Total number of elements
		Assert.assertEquals(2, page.getTotalPages()); // Total number of pages
		List<CategoryPageView> list = page.getContent();
		Assert.assertEquals("category b", list.get(0).getListingCategory());
		Assert.assertEquals(2, list.get(0).getCount());
		Assert.assertEquals("category d", list.get(1).getListingCategory());
		Assert.assertEquals(2, list.get(1).getCount());
		Assert.assertEquals("category a", list.get(2).getListingCategory());
		Assert.assertEquals(1, list.get(2).getCount());
	}

}