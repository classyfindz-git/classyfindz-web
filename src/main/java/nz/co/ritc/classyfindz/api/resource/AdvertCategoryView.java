/**
 * 
 */
package nz.co.ritc.classyfindz.api.resource;

import java.io.Serializable;
import java.util.List;

import nz.co.ritc.classyfindz.api.resource.AdvertCategoryView.ColumnAdvert;

/**
 * @author rahul
 *
 */
public class AdvertCategoryView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1743989939112537257L;

	public static class ColumnAdvert {
		
		private String heading;
		
		private String text;

		public ColumnAdvert(String heading, String text) {
			super();
			this.heading = heading;
			this.text = text;
		}

		public String getHeading() {
			return heading;
		}

		public void setHeading(String heading) {
			this.heading = heading;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
		
	}
	
	private List<ColumnAdvert> adverts;
	
	public AdvertCategoryView(List<ColumnAdvert> adverts) {
		this.adverts = adverts;
	}

	public List<ColumnAdvert> getAdverts() {
		return adverts;
	}

	public void setAdverts(List<ColumnAdvert> adverts) {
		this.adverts = adverts;
	}

}
