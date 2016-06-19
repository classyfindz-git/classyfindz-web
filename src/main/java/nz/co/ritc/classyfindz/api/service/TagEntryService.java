/**
 * 
 */
package nz.co.ritc.classyfindz.api.service;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nz.co.ritc.classyfindz.api.resource.TagEntry;
import nz.co.ritc.classyfindz.exception.TagCreateException;
import nz.co.ritc.classyfindz.exception.TagSearchException;

/**
 * @author rahul
 *
 */
@Component
@RestController
public class TagEntryService {

	@Autowired
	private DataSource dataSource;

	@RequestMapping("/public/services/tags")
	public List<TagEntry> getSearchTagStartingWith(@RequestParam(value="tagPart")final String tagPart) {
		final List<TagEntry> tags = new ArrayList<TagEntry>();
		try {
			final ResultSet results = dataSource.getConnection()
				.prepareStatement("SELECT TOP 5 TAG_NAME FROM TAGS"
						+ " WHERE TAG_NAME REGEX " + StringUtils.quote("*" + tagPart + "*")
						+ " ORDER BY TAG_NAME")
				.executeQuery();
			while (results.next()) {
				final TagEntry tagEntry = new TagEntry();
				tagEntry.setText(results.getString(results.getString("TAG_NAME")));
				tags.add(tagEntry);
			}
		} catch (Exception e) {
			throw new TagSearchException(tagPart, e);
		}
		return tags;
	}
	

	@RequestMapping("/protected/services/tags")
	public void creatTag(@RequestParam(value="tagText")final String  tagName) {
		try {
			final String tagLowerCase = StringUtils.uncapitalize(tagName);
			final String colMD5 = new String(MessageDigest.getInstance("MD5").digest(tagLowerCase.getBytes()));
			final ResultSet resultSet = dataSource.getConnection()
				.prepareStatement("SELECT COUNT(*) FROM TAGS"
					+ " WHERE MD5_CHECKSUM = " + StringUtils.quote(colMD5))
				.executeQuery();
			if (!resultSet.next()) {
				dataSource.getConnection()
						.prepareStatement("INSERT INTO TAGS (tag_name,md5_checksum)"
								+ " VALUES("
								+ StringUtils.quote(tagLowerCase)
								+ StringUtils.quote(colMD5)
								+ ")")
						.executeUpdate();
			} else
				throw new TagCreateException(tagName);
		} catch (Exception e) {
			throw new TagCreateException(tagName, e);
		}
	}
}