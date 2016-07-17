package nz.co.ritc.classyfindz.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView;
import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView.ColumnAdvert;

@Component
@RestController
@CrossOrigin(origins="*")
public class AdvertCategoryService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/public/services/adverts")
	public ResponseEntity getAdvertsListDatabase(@RequestParam(required=false) List<String> tags) {
		final Map<String, AdvertCategoryView> categories = dummyMethod();

		return new ResponseEntity(categories, HttpStatus.OK) ;
	}

	private Map<String, AdvertCategoryView> realMethod() {
		return null;
	}
	
	private Map<String, AdvertCategoryView> dummyMethod() {
		final Map<String, AdvertCategoryView> categories = new HashMap<>();
		int i = 0;	
		double random = Math.random();
		while (i < (random * 10)) {
			final String category = RandomStringUtils.randomAlphabetic(15).replace('c', ' ');
			categories.put(category, getAdvertsForCategory());
			i++;
			if (random < 0.1)
				random *= 10;
		}
		return categories;
	}

	private AdvertCategoryView getAdvertsForCategory() {
		final List<ColumnAdvert> adverts = new ArrayList<ColumnAdvert>();
		int i = 0;
		double random = Math.random();
		while (i < (random * 10)) {
			adverts.add(new ColumnAdvert(RandomStringUtils.randomAlphabetic(15).replace('b', ' '), RandomStringUtils.randomAlphabetic(75).replace('a', ' ')));
			i++;
			if (random < 0.1)
				random *= 10;
		}
		return new AdvertCategoryView(adverts);
	}

}