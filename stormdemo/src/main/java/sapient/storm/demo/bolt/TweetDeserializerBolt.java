
/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/

package sapient.storm.demo.bolt;

import static sapient.storm.demo.constant.DemoConstants.*;
import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNTRY;
import static sapient.storm.demo.constant.DemoConstants.FIELD_SENTIMENT;
import static sapient.storm.demo.constant.DemoConstants.FIELD_STATE;
import static sapient.storm.demo.constant.DemoConstants.FIELD_TEXT;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import sapient.storm.demo.util.NameTagUtil;
import sapient.storm.demo.util.LocationUtil;
import sapient.storm.demo.vo.Location;
import twitter4j.Status;

/**
 * TweetDeserializerBolt class is responsible for reading raw tweeter feed and translate it into tuple of -
 *  - <COUNTRY, STATE, CANDIDATE, SENTIMENT, HASHTAG, TEXT >.
 * @author kkanka
 *
 */
public class TweetDeserializerBolt extends BaseRichBolt {

	private static final long serialVersionUID = -2797231267356239412L;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		Status s = (Status) input.getValue(0);
		Location location = null;
		if (s.getGeoLocation() != null) {
			location = getDetailsFromGeoLocation(s);
		} else {
			location = getDetailsFromPlace(s);
		}
		collector.emit(new Values(location.getCountry(), location.getState(), getCandidateName(s), getSentiment(s), getHashTag(s), s.getText()));
	}

	@Override
	public void cleanup() {

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(FIELD_COUNTRY, FIELD_STATE, FIELD_CANDIDATE, FIELD_SENTIMENT, FIELD_HASHTAG, FIELD_TEXT));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	/**
	 * The method takes Tweet text and returns associated ISO 3166 STATE Code (if found any) from User GeoLocation (Longitude, Latitude).
	 * @param status -> String | Tweet Text
	 * @return Location
	 */
	private Location getDetailsFromGeoLocation(Status status) {
		return LocationUtil.getLocation(status.getGeoLocation());
	}

	/**
	 * The method takes Tweet text and returns associated ISO 3166 STATE Code (if found any) from Location details on User profile.
	 * @param status -> String | Tweet Text
	 * @return Location
	 */
	private Location getDetailsFromPlace(Status status) {
		return LocationUtil.getUserLocation(status.getUser().getLocation());
	}

	/**
	 * The method takes Tweet text and returns associated Candidate Name (if found any).
	 * @param t -> String | Tweet Text
	 * @return String -> Candidate Name
	 */
	private String getCandidateName(Status t) {
		return NameTagUtil.getCandidateName(t).getCandidateName();
	}

	/**
	 * The method takes Tweet text and returns HashTag attached.
	 * @param t -> String | Tweet Text
	 * @return String -> HashTag
	 */
	private String getHashTag(Status t) {
		return NameTagUtil.getHashTagId(t).getHashTag();
	}

	private short getSentiment(Status t) {

		return 0;
	}
}
