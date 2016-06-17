/**
 * 
 */
package nz.co.ritc.classyfindz.api.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nz.co.ritc.classyfindz.api.resource.TagEntry;

/**
 * @author rahul
 *
 */
@RestController
public class TagEntryService {

	@SuppressWarnings("unchecked")
	@RequestMapping("/public/services/tags")
	public List<TagEntry> getSearchTagStartingWith(@RequestParam(value="tagPart")final String tagStart) {
		return 
		(List<TagEntry>) CollectionUtils.collect(Arrays.asList(new String[]{"test1","test2","test3","test4"}), new Transformer() {
			
			@Override
			public Object transform(Object input) {
				final TagEntry test = new TagEntry();
				test.setText(tagStart + input);
				return test;
			}
		});
	}
}
