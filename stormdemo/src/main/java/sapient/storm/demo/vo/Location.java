package sapient.storm.demo.vo;

/**
 * This class is plain POJO to hold State and Country details.
 * @author kkanka
 *
 */
public class Location {

	private String state;
	private String country;

	public Location(String state, String contry) {
		this.state = state;
		this.country = contry;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Location [state=" + state + ", country=" + country + "]";
	}

}