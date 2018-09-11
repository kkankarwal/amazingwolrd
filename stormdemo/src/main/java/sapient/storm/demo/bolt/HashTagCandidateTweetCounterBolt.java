/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/

package sapient.storm.demo.bolt;

import static sapient.storm.demo.constant.DemoConstants.DB_URL;
import static sapient.storm.demo.constant.DemoConstants.FIELD_CANDIDATE;
import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNT;
import static sapient.storm.demo.constant.DemoConstants.PASS;
import static sapient.storm.demo.constant.DemoConstants.*;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.common.HikariCPConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.jdbc.mapper.SimpleJdbcMapper;
import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import sapient.storm.demo.util.DBUtil;

/**
 * HashTagCandidateTweetCounterBolt class receives Tuple from TweetDeserializerBolt, maintains HashTag-Candidate count 
 * and persist the counter record to database using HikariCP Connection provider.
 * @author kkanka
 *
 */
public class HashTagCandidateTweetCounterBolt extends BaseRichBolt {

	private static final long serialVersionUID = 4675814881787099990L;

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(HashTagCandidateTweetCounterBolt.class);

	private Map<HashTagCandidateTupleKey, Long> counterMap = new ConcurrentHashMap<HashTagCandidateTupleKey, Long>();
	private OutputCollector collector;
	private ConnectionProvider provider;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.provider = DBUtil.configureDBConnectionProvider();

		List<Column> columnSchema = Lists.newArrayList(new Column("HASHTAG", java.sql.Types.VARCHAR), new Column("NAME", java.sql.Types.VARCHAR), new Column("COUNT", java.sql.Types.INTEGER));
		JdbcMapper simpleJdbcMapper = new SimpleJdbcMapper(columnSchema);

		this.provider.prepare();
	}

	@Override
	public void execute(Tuple input) {

		String hashTag = null;	
		String candidate = null;
		Long count = null;
		HashTagCandidateTupleKey tupleKey;
		
		try {

			// Extract Data from Tuple
			hashTag = input.getStringByField(FIELD_HASHTAG);
			candidate = input.getStringByField(FIELD_CANDIDATE);
			tupleKey = new HashTagCandidateTupleKey(hashTag, candidate);
			// Increase Tweet Country count
			count = counterMap.get(tupleKey);
			if (count == null) {
				count = new Long(0L);
			}
			count++;
			counterMap.put(tupleKey, count);
			logger.info("hashTag: {}, candidate: {}, Count: {}", hashTag, candidate, count);

			// Record in DB

			// Put records in Database
			Connection connection = this.provider.getConnection();
			try {
				PreparedStatement pstmt = connection.prepareStatement(HASHTAG_CANDIDATES_INS_SQL);
				pstmt.setString(1, hashTag);
				pstmt.setString(2, candidate);
				pstmt.setLong(3, count);
				int result = pstmt.executeUpdate();
				logger.info("Query execution result: " + result);

			} catch (SQLException e) {
				logger.error(" Failed to persist data : {}", e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(" Failed to persist data : {}", e.getMessage());
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			logger.error(" Failed to process tuple : {}", e.getMessage());
			e.printStackTrace();
		}

		// Emit response
		collector.emit(new Values(hashTag, candidate, count));
		collector.ack(input);
	}

	@Override
	public void cleanup() {
		this.provider.cleanup();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(FIELD_HASHTAG, FIELD_CANDIDATE, FIELD_COUNT));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}

class HashTagCandidateTupleKey implements Serializable {

	private final String hashTag;
	private final String candidate;

	public HashTagCandidateTupleKey(String hashTag, String candidate) {
		this.hashTag = hashTag;
		this.candidate = candidate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + candidate.hashCode();
		result = (prime * result) + hashTag.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashTagCandidateTupleKey other = (HashTagCandidateTupleKey) obj;
		if (this.candidate != other.candidate || this.hashTag != other.hashTag)
			return false;
		return true;
	}
}