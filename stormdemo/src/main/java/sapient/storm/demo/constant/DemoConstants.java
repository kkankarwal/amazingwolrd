/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.constant;

import java.io.Serializable;

public class DemoConstants implements Serializable {
	
	public static final String ACCESS_TOKEN_SECRET = "{INSERT_YOUR_TWITTER_API_ACCESS_TOKEN_SECRET}";
	public static final String ACCESS_TOKEN = "{INSERT_YOUR_TWITTER_API_ACCESS_TOKEN}";
	public static final String CONSUMER_SECRET = "{INSERT_YOUR_TWITTER_API_CONSUMER_KEY_SECRET}";
	public static final String CONSUMER_KEY = "{INSERT_YOUR_TWITTER_API_CONSUMER_KEY}";

	public static final String GOOGLE_API_KEY = "{INSERT_YOUR_GOOGLE_API_KEY_HERE}";

	public static final String RAW_TWEET_SPOUT = "raw-tweet-spout";
	public static final String DESERIALIZER_BOLT = "deserialized-bolt";
	public static final String COUNTRY_COUNTER_BOLT = "country-counter-bolt";
	public static final String STATE_COUNTER_BOLT = "state-counter-bolt";
	public static final String CANDIDATE_COUNTER_BOLT = "candidate-counter-bolt";
	public static final String HASHTAG_COUNTER_BOLT = "hashtag-counter-bolt";
	
	public static final String HASHTAG_CANDIDATE_COUNTER_BOLT = "hashtag-candidate-counter-bolt";
	public static final String HASHTAG_STATE_COUNTER_BOLT = "hashtag-state-counter-bolt";
	public static final String STATE_CANDIDATE_COUNTER_BOLT = "state-candidate-counter-bolt";
	
	public static final String STREAM_01 = "stream1";
	public static final String STREAM_02 = "stream2";
	
	public static final String FIELD_TWEET = "tweet";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_COUNTRY = "country";
	public static final String FIELD_CANDIDATE = "candidate";
	public static final String FIELD_HASHTAG = "hashTag";
	public static final String FIELD_TEXT = "text";
	public static final String FIELD_SENTIMENT = "sentiment";
	public static final String FIELD_COUNT = "count";

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_DRIVER2 = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
	public static final String DB_URL = "jdbc:mysql://{INSERT_YOUR_MYSQL_HOST_HERE}:3306/stormdemodb";
	public static final String USER = "stormdemo";
	public static final String PASS = "S4p13nt!";
	
	public static final String COUNTRY_INS_SQL = "INSERT INTO Countries (`NAME`, `COUNT`) VALUES (?, ?)";
	public static final String CANDIDATE_INS_SQL = "INSERT INTO Candidates (`NAME`, `COUNT`) VALUES (?, ?)";
	public static final String STATE_INS_SQL = "INSERT INTO States (`NAME`, `COUNT`) VALUES (?, ?)";
	public static final String STATE_CANDIDATES_INS_SQL = "INSERT INTO StateCandidates (`STATE`, `NAME`, `COUNT`) VALUES (?, ?, ?)";

	public static final String HASHTAG_INS_SQL = "INSERT INTO HashTags (`HASHTAG`, `COUNT`) VALUES (?, ?)";
	public static final String HASHTAG_CANDIDATES_INS_SQL = "INSERT INTO HashTagCandidates (`HASHTAG`, `NAME`, `COUNT`) VALUES (?, ?, ?)";
	public static final String HASHTAG_STATES_INS_SQL = "INSERT INTO HashTagStates (`HASHTAG`, `STATE`, `COUNT`) VALUES (?, ?, ?)";

}
