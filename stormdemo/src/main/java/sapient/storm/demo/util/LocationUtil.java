/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.util;

import static sapient.storm.demo.constant.DemoConstants.GOOGLE_API_KEY;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import sapient.storm.demo.constant.Country;
import sapient.storm.demo.vo.Location;
import twitter4j.GeoLocation;

public class LocationUtil {

	private static String IOS_3166_PREFIX = "US-";

	private static List<String> statesZipCode = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
			"MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
			"WV", "WI", "WY", "AS", "DC", "GU", "MP", "PR", "VI", "NYC");

	private static List<String> statesNames = Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
			"Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
			"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
			"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin",
			"Wyoming", "American Samoa", "District of Columbia", "Guam", "Northern Mariana Islands", "Puerto Rico", "The United States Virgin Islands", "NEW YORK CITY");

	private static Collection<String> countryNameVarients = Arrays.asList("USA", "US", "United State", "United States", "United States Of America", "The United States Of America",
			"AMERICA", "U.S.A.", "U S A");

	private static Map<String, String> stateMap = new HashMap<String, String>();

	static {
		// ISO 3166 State Code
		stateMap.put("ALABAMA", "US-AL");
		stateMap.put("ALASKA", "US-AK");
		stateMap.put("ARIZONA", "US-AZ");
		stateMap.put("ARKANSAS", "US-AR");
		stateMap.put("CALIFORNIA", "US-CA");
		stateMap.put("COLORADO", "US-CO");
		stateMap.put("CONNECTICUT", "US-CT");
		stateMap.put("DELAWARE", "US-DE");
		stateMap.put("FLORIDA", "US-FL");
		stateMap.put("GEORGIA", "US-GA");
		stateMap.put("HAWAII", "US-HI");
		stateMap.put("IDAHO", "US-ID");
		stateMap.put("ILLINOIS", "US-IL");
		stateMap.put("INDIANA", "US-IN");
		stateMap.put("IOWA", "US-IA");
		stateMap.put("KANSAS", "US-KS");
		stateMap.put("KENTUCKY", "US-KY");
		stateMap.put("LOUISIANA", "US-LA");
		stateMap.put("MAINE", "US-ME");
		stateMap.put("MARYLAND", "US-MD");
		stateMap.put("MASSACHUSETTS", "US-MA");
		stateMap.put("MICHIGAN", "US-MI");
		stateMap.put("MINNESOTA", "US-MN");
		stateMap.put("MISSISSIPPI", "US-MS");
		stateMap.put("MISSOURI", "US-MO");
		stateMap.put("MONTANA", "US-MT");
		stateMap.put("NEBRASKA", "US-NE");
		stateMap.put("NEVADA", "US-NV");
		stateMap.put("NEW HAMPSHIRE", "US-NH");
		stateMap.put("NEW JERSEY", "US-NJ");
		stateMap.put("NEW MEXICO", "US-NM");
		stateMap.put("NEW YORK", "US-NY");
		stateMap.put("NORTH CAROLINA", "US-NC");
		stateMap.put("NORTH DAKOTA", "US-ND");
		stateMap.put("OHIO", "US-OH");
		stateMap.put("OKLAHOMA", "US-OK");
		stateMap.put("OREGON", "US-OR");
		stateMap.put("PENNSYLVANIA", "US-PA");
		stateMap.put("RHODE ISLAND", "US-RI");
		stateMap.put("SOUTH CAROLINA", "US-SC");
		stateMap.put("SOUTH DAKOTA", "US-SD");
		stateMap.put("TENNESSEE", "US-TN");
		stateMap.put("TEXAS", "US-TX");
		stateMap.put("UTAH", "US-UT");
		stateMap.put("VERMONT", "US-VT");
		stateMap.put("VIRGINIA", "US-VA");
		stateMap.put("WASHINGTON", "US-WA");
		stateMap.put("WEST VIRGINIA", "US-WV");
		stateMap.put("WISCONSIN", "US-WI");
		stateMap.put("WYOMING", "US-WY");

		stateMap.put("DISTRICT OF COLUMBIA", "US-DC");
		stateMap.put("DISTRICT OF COLUMBIA", "US-AS");
		stateMap.put("GUAM", "US-GU");
		stateMap.put("NORTHERN MARIANA ISLANDS", "US-MP");
		stateMap.put("PUERTO RICO", "US-PR");
		stateMap.put("UNITED STATES MINOR OUTLYING ISLANDS", "US-UM");
		stateMap.put("THE UNITED STATES VIRGIN ISLANDS", "US-VI");
		stateMap.put("NEW YORK CITY", "US-NY");

	}

	public static Location getUserLocation(String locationText) {

		boolean isLocationFound = Boolean.FALSE;
		String state = null;
		String country = null;

		if (locationText == null) {
			locationText = "";
		}
		String[] locationParams = locationText.toUpperCase().replace('/', ',').replace('.', ' ').replace('?', ' ').trim().split("\\s*,\\s*");

		for (String locParam : locationParams) {

			if (statesZipCode.stream().filter(s -> s.equalsIgnoreCase(locParam)).findFirst().isPresent()) {
				if (locParam.equalsIgnoreCase("NYC")) {
					state = IOS_3166_PREFIX + "NY";
				} else {
					state = IOS_3166_PREFIX + locParam;
				}
				country = Country.USA.name();
				isLocationFound = Boolean.TRUE;
			} else if (statesNames.stream().filter(s -> s.equalsIgnoreCase(locParam)).findFirst().isPresent()) {
				state = stateMap.get(locParam);
				country = Country.USA.name();
				isLocationFound = Boolean.TRUE;
			} else if ((countryNameVarients.stream().filter(s -> s.equalsIgnoreCase(locParam)).findFirst().isPresent()) || locParam.contains("USA")) {
				state = "NA";
				country = Country.USA.name();
			} else {
				state = "NA";
				country = Country.NA.name();
			}

			if (isLocationFound)
				break;

		}
		return new Location(state, country);

	}

	public static Location getLocation(GeoLocation geoLocation) {
		GeoApiContext ctx = new GeoApiContext();
		ctx.setApiKey(GOOGLE_API_KEY);
		GeocodingResult[] results = GeocodingApi.reverseGeocode(ctx, new LatLng(geoLocation.getLatitude(), geoLocation.getLongitude())).awaitIgnoreError();
		if (results != null && results.length > 0) {
			AddressComponent components[] = results[0].addressComponents;
			for (AddressComponent component : components) {
				for (AddressComponentType type : component.types) {
					if (AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1.equals(type)) {
						return new Location(IOS_3166_PREFIX + component.shortName, Country.USA.name());
					}
				}
			}
		}
		return new Location("NA", Country.NA.name());
	}

}
