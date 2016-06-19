/**
 * 
 */
package nz.co.ritc.classyfindz.api.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nz.co.ritc.classyfindz.api.resource.TagEntry;
import nz.co.ritc.classyfindz.jpa.entity.Tags;
import nz.co.ritc.classyfindz.jpa.repository.TagsRepository;

/**
 * @author rahul
 *
 */
@Component
@RestController
public class TagEntryService {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TagsRepository tagsRepository;

	private Pageable createPageRequest() {
	    return new PageRequest(0, 5);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/public/services/tags")
	public List<TagEntry> getSearchTagStartingWith(@RequestParam(value="tagPart")final String tagPart) {
		logger.debug("Search for " + tagPart);
		return 
		(List<TagEntry>) CollectionUtils.collect(
				tagsRepository.findByTagNameContaining(tagPart, createPageRequest()), 
				new Transformer() {
					@Override
					public Object transform(Object input) {
						final TagEntry tagEntry = new TagEntry();
						tagEntry.setText(((Tags)input).getTagName());
						return tagEntry;
					}
				});
	}
}