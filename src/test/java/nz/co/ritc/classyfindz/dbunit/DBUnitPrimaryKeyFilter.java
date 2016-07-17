package nz.co.ritc.classyfindz.dbunit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.filter.IColumnFilter;
import org.springframework.stereotype.Component;

@Component
public class DBUnitPrimaryKeyFilter implements IColumnFilter {

	private HashMap h = new HashMap() {{
	    put("advert_registry_has_l_category",
	    		Arrays.asList(new String[] {"advert_registry_id", "listing_category_id"}));
	    put("advert_has_tags",
	    		Arrays.asList(new String[] {"advert_id", "tags_id"}));
	}};
	
	@Override
	public boolean accept(String tableName, Column column) {
        return h.containsKey(tableName.toLowerCase()) ?
        		((List<String>) h.get(tableName.toLowerCase())).contains(column.getColumnName().toLowerCase()) :
        			column.getColumnName().equalsIgnoreCase(tableName + "_id") ;
    }
}
