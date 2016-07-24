package nz.co.ritc.classyfindz.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView;
import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView.ColumnAdvert;
import nz.co.ritc.classyfindz.jpa.entity.CategoryPageView;
import nz.co.ritc.classyfindz.jpa.entity.DefaultSearchResultsView;
import nz.co.ritc.classyfindz.jpa.entity.SearchResultsWithTagNamesView;
import nz.co.ritc.classyfindz.jpa.repository.CategoryPageViewRepository;
import nz.co.ritc.classyfindz.jpa.repository.ColumnAdvertRepository;
import nz.co.ritc.classyfindz.jpa.repository.DefaultSearchResultsViewRepository;
import nz.co.ritc.classyfindz.jpa.repository.SearchResultsWithTagNamesViewRepository;

@Component
@RestController
@CrossOrigin(origins="*")
public class AdvertServices {

	private static final int COL_ADV_SCROSS_PAGE_SIZE = 5;

	private static final int COL_ADV_SCROLL_START_IDX = 0;

	private static final int CATEGORY_VIEW_PAGE_SIZE = 6;

	final Sort _sort = new Sort(
		    new Order(Direction.DESC, "count"), 
		    new Order(Direction.ASC, "listingCategory")
		  );
	final PageRequest _colAdvFirstPage = new PageRequest(COL_ADV_SCROLL_START_IDX, COL_ADV_SCROSS_PAGE_SIZE, new Sort(
		    new Order(Direction.ASC, "rank")
		  ));

	@Autowired
	CategoryPageViewRepository categoryPageViewRepository;

	@Autowired
	ColumnAdvertRepository columnAdvertRepository;

	@Autowired
	DefaultSearchResultsViewRepository defaultSearchResultsViewRepository;

	@Autowired
	SearchResultsWithTagNamesViewRepository searchResultsWithTagNamesViewRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/public/services/adverts")
	public ResponseEntity getAdvertsListDatabase(@RequestParam(required=false) List<String> tags, @RequestParam(required=true) int page) {
		final Map<String, AdvertCategoryView> categories = 
				CollectionUtils.isEmpty(tags) ? loadDefaultAdverts(page) : loadAdvertsForSelectedTags(tags, page);
		return new ResponseEntity(categories, HttpStatus.OK) ;
	}

	private Map<String, AdvertCategoryView> loadDefaultAdverts(int page) {
		final Map<String, AdvertCategoryView> categories = new HashMap<>();
		// List categories having adverts with matching tags, order by descending count of adverts
		final PageRequest pageRequest = new PageRequest(page, CATEGORY_VIEW_PAGE_SIZE, _sort);
		final Page<CategoryPageView> categoriesOnPage = categoryPageViewRepository.findAll(pageRequest) ;
		for (CategoryPageView categoryPageView : categoriesOnPage) {
			// Load first 5 Column Adverts for categories
			categories.put(categoryPageView.getListingCategory(), new AdvertCategoryView(new ArrayList<ColumnAdvert>()));
			final Page<DefaultSearchResultsView> resultView = defaultSearchResultsViewRepository.findByListingCategory(categoryPageView.getListingCategory(), _colAdvFirstPage);
			for (final DefaultSearchResultsView defaultSearchResultsView : resultView) {
				final ColumnAdvert columnAdvert = new ColumnAdvert(defaultSearchResultsView.getColumnAdvert().getHeading(), defaultSearchResultsView.getColumnAdvert().getBody());
				categories.get(categoryPageView.getListingCategory()).getAdverts().add(columnAdvert);
			}
		}
		return categories;
	}
	
	private Map<String, AdvertCategoryView> loadAdvertsForSelectedTags(List<String> tags, int page) {
		final Map<String, AdvertCategoryView> categories = new HashMap<>();
		// List categories having adverts with matching tags, order by descending count of adverts
		final PageRequest pageRequest = new PageRequest(page, CATEGORY_VIEW_PAGE_SIZE, _sort);
		final Page<CategoryPageView> categoriesOnPage = categoryPageViewRepository.findByTagNameIn(tags, pageRequest);
		for (final CategoryPageView categoryPageView : categoriesOnPage) {
			// Load first 5 Column Adverts for categories for selected tags
			categories.put(
					categoryPageView.getListingCategory(), 
					new AdvertCategoryView(new ArrayList<ColumnAdvert>()));
			final Page<nz.co.ritc.classyfindz.jpa.entity.ColumnAdvert> resultView = searchResultsWithTagNamesViewRepository.findByListingCategoryAndTagName(
					categoryPageView.getListingCategory(), 
					tags, 
					_colAdvFirstPage);
			for (final nz.co.ritc.classyfindz.jpa.entity.ColumnAdvert columnAdvert : resultView) {
				final List<ColumnAdvert> adverts = categories.get(categoryPageView.getListingCategory()).getAdverts();
				if (!CollectionUtils.exists(adverts, new Predicate() {
					
					@Override
					public boolean evaluate(Object arg) {
						return ((ColumnAdvert)arg).getHeading().equals(columnAdvert.getHeading()) && 
								((ColumnAdvert)arg).getText().equals(columnAdvert.getBody());
					}
				}))
					adverts.add(new ColumnAdvert(columnAdvert.getHeading(), columnAdvert.getBody()));
			}
		}
		return categories;
	}
}