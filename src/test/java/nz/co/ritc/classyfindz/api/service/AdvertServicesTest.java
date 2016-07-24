package nz.co.ritc.classyfindz.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.TransformerUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import nz.co.ritc.classyfindz.ApplicationTests;
import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView;
import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView.ColumnAdvert;
import nz.co.ritc.classyfindz.jpa.entity.SearchResultsWithTagNamesView;

public class AdvertServicesTest extends ApplicationTests {

	Logger log = Logger.getLogger(AdvertServicesTest.class);
	
	@Autowired
	AdvertServices service;
	
	@Test
	@DatabaseSetup(value = "/dbunit/default_adverts_with_lots_of_ads.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/default_adverts_with_lots_of_ads.xml", type=DatabaseOperation.DELETE)
	public void test() {
		ResponseEntity advertsListDatabase = service.getAdvertsListDatabase(null, 0);
		Assert.assertNotNull(advertsListDatabase);
		final Map<String, AdvertCategoryView> body = (Map<String, AdvertCategoryView>) advertsListDatabase.getBody();
		Assert.assertEquals(4, body.size());
		Assert.assertTrue(body.containsKey("category a"));
		Assert.assertTrue(body.containsKey("category b"));
		Assert.assertTrue(body.containsKey("category c"));
		Assert.assertTrue(body.containsKey("category d"));
		Assert.assertEquals(1, body.get("category a").getAdverts().size());
		Assert.assertEquals("my test advert", body.get("category a").getAdverts().get(0).getHeading());
	}

	@Test
	@DatabaseSetup(value = "/dbunit/adverts_services_tests.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/adverts_services_tests.xml", type=DatabaseOperation.DELETE)
	public void testWithSelectedTag() {
		ResponseEntity advertsListDatabase = service.getAdvertsListDatabase(Arrays.asList("test tag 1"), 0);
		Assert.assertNotNull(advertsListDatabase);
		final Map<String, AdvertCategoryView> body = (Map<String, AdvertCategoryView>) advertsListDatabase.getBody();
		Assert.assertEquals(5, body.size());
		Assert.assertTrue(body.containsKey("category a"));
		Assert.assertTrue(body.containsKey("category b"));
		Assert.assertTrue(body.containsKey("category c"));
		Assert.assertTrue(body.containsKey("category d"));
		Assert.assertTrue(body.containsKey("category e"));
		// Check column advert for category a
		Assert.assertEquals(1, body.get("category a").getAdverts().size());
		Assert.assertEquals("my test advert", body.get("category a").getAdverts().get(0).getHeading());
		// Check column adverts for category b
		final List<ColumnAdvert> advertsCatB = body.get("category b").getAdverts();
		Assert.assertEquals(2, advertsCatB.size());
		assertColumnAdverts(advertsCatB, new String[] {"my test advert",  "my second test advert"});
		// Check column adverts for category c
		Assert.assertEquals(1, body.get("category c").getAdverts().size());
		Assert.assertEquals("my second test advert", body.get("category c").getAdverts().get(0).getHeading());
		// Check column adverts for category d
		Assert.assertEquals(1, body.get("category d").getAdverts().size());
		Assert.assertEquals("my third test advert", body.get("category d").getAdverts().get(0).getHeading());
		// Check column adverts for category e
		Assert.assertEquals(1, body.get("category e").getAdverts().size());
		Assert.assertEquals("my third test advert", body.get("category e").getAdverts().get(0).getHeading());
		// Check third test advert not included in search results
		final Set<String> categories = body.keySet();
		for (String category : categories) {
			final AdvertCategoryView advertCategoryView = body.get(category);
			final List<ColumnAdvert> adverts = advertCategoryView.getAdverts();
			Assert.assertFalse(CollectionUtils.exists(adverts, new Predicate() {
				@Override
				public boolean evaluate(Object arg) {
					return ((ColumnAdvert)arg).getHeading().equals("my third advert");
				}
			}));
		}

	}

	@Test
	@DatabaseSetup(value = "/dbunit/adverts_services_tests_2.xml", type=DatabaseOperation.REFRESH)
	@DatabaseTearDown(value = "/dbunit/adverts_services_tests_2.xml", type=DatabaseOperation.DELETE)
	public void testWithTwoTags() {
		ResponseEntity advertsListDatabase = service.getAdvertsListDatabase(Arrays.asList("test tag 1","test tag 2"), 0);
		Assert.assertNotNull(advertsListDatabase);
		final Map<String, AdvertCategoryView> body = (Map<String, AdvertCategoryView>) advertsListDatabase.getBody();
		Assert.assertEquals(4, body.size());
		Assert.assertTrue(body.containsKey("category a"));
		Assert.assertTrue(body.containsKey("category b"));
		Assert.assertTrue(body.containsKey("category c"));
		Assert.assertTrue(body.containsKey("category d"));
		final List<ColumnAdvert> advertsCatA = body.get("category a").getAdverts();
		Assert.assertEquals(2, advertsCatA.size());
		assertColumnAdverts(advertsCatA, new String[] {"my test advert",  "my sixth test advert"});
		final List<ColumnAdvert> advertsCatB = body.get("category b").getAdverts();
		Assert.assertEquals(4, advertsCatB.size());
		assertColumnAdverts(advertsCatB, new String[] {"my test advert",  "my second test advert","my sixth test advert", "my eight test advert"});
		
		Assert.assertEquals(1, body.get("category c").getAdverts().size());
		Assert.assertEquals("my second test advert", body.get("category c").getAdverts().get(0).getHeading());
		Assert.assertEquals(1, body.get("category d").getAdverts().size());
		Assert.assertEquals("my third test advert", body.get("category d").getAdverts().get(0).getHeading());
	}

	private void assertColumnAdverts(final List<ColumnAdvert> adverts, final String[] expectedAdverts) {
		Assert.assertTrue(StringUtils.join(CollectionUtils.collect(adverts, new Transformer() {
			@Override
			public Object transform(Object arg) {
				return ((ColumnAdvert)arg).getHeading();
			}
		})),CollectionUtils.countMatches(adverts, new Predicate() {
			@Override
			public boolean evaluate(Object arg) {
				final String heading = ((ColumnAdvert)arg).getHeading();
				final boolean contains = Arrays.asList(expectedAdverts).contains(heading);
				log.debug("[" + heading + "]" + contains);
				return contains;
			}
		}) == adverts.size());
	}
}