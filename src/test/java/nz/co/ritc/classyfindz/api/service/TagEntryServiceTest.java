/**
 * 
 */
package nz.co.ritc.classyfindz.api.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import nz.co.ritc.classyfindz.ApplicationTests;
import nz.co.ritc.classyfindz.api.resource.TagEntry;

/**
 * @author rahul
 *
 */
public class TagEntryServiceTest extends ApplicationTests {

	@Autowired
	TagEntryService service;
	
	@Test
	@DatabaseSetup(value = "/dbunit/tags.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/tags.xml", type= DatabaseOperation.DELETE)
	public void test() {
		final List<TagEntry> tags = service.getSearchTagStartingWith("test");
		Assert.assertNotNull(tags);
		Assert.assertEquals(1, tags.size());
		Assert.assertEquals("test tag 1", tags.get(0).getText());
	}

}
