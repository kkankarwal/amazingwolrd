/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.bolt;

import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNT;
import static sapient.storm.demo.constant.DemoConstants.FIELD_HASHTAG;
import static sapient.storm.demo.constant.DemoConstants.FIELD_STATE;
import static sapient.storm.demo.constant.DemoConstants.HASHTAG_STATES_INS_SQL;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.jdbc.mapper.SimpleJdbcMapper;
import org.slf4j.Logger;

import com.google.common.collect.Lists;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import sapient.storm.demo.util.DBUtil;

public class HashTagSateTweetCounterBolt extends BaseRichBolt {

	private static final long serialVersionUID = 6915789642621414552L;

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(HashTagSateTweetCounterBolt.class);

	private Map<HashTagStateTupleKey, Long> counterMap = new ConcurrentHashMap<HashTagStateTupleKey, Long>();
	private OutputCollector collector;
	private ConnectionProvider provider;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.provider = DBUtil.configureDBConnectionProvider();

		List<Column> columnSchema = Lists.newArrayList(
				new Column("HASHTAG", java.sql.Types.VARCHAR), 
				new Column("STATE", java.sql.Types.VARCHAR),
				new Column("COUNT", java.sql.Types.INTEGER));
		JdbcMapper simpleJdbcMapper = new SimpleJdbcMapper(columnSchema);

		this.provider.prepare();
	}

	@Override
	public void execute(Tuple input) {

		String hashTag = null;
		String state = null;
		Long count = null;
		HashTagStateTupleKey tupleKey;

		try {

			// Extract Data from Tuple
			hashTag = input.getStringByField(FIELD_HASHTAG);
			state = input.getStringByField(FIELD_STATE);
			tupleKey = new HashTagStateTupleKey(hashTag, state);

			// Increase Tweet Country count
			count = counterMap.get(tupleKey);
			if (count == null) {
				count = new Long(0L);
			}
			count++;
			counterMap.put(tupleKey, count);
			logger.info("hashTag: {}, State: {}, Count: {}", hashTag, state, count);

			// Record in Database
			persistRecord(hashTag, state, count);

		} catch (Exception e) {
			logger.error(" Failed to process tuple : {}", e.getMessage());
			e.printStackTrace();
		}

		// Emit response
		collector.emit(new Values(hashTag, state, count));
		collector.ack(input);
	}

	@Override
	public void cleanup() {
		this.provider.cleanup();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(FIELD_HASHTAG, FIELD_STATE, FIELD_COUNT));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	private void persistRecord(String hashTag, String state, Long count) {

		// Put records in Database
		Connection connection = this.provider.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(HASHTAG_STATES_INS_SQL);
			pstmt.setString(1, hashTag);
			pstmt.setString(2, state);
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

	}

}

class HashTagStateTupleKey implements Serializable {

	private final String hashTag;
	private final String state;

	public HashTagStateTupleKey(String hashTag, String state) {
		this.hashTag = hashTag;
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + state.hashCode();
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
		HashTagStateTupleKey other = (HashTagStateTupleKey) obj;
		if (this.state != other.state || this.hashTag != other.hashTag)
			return false;
		return true;
	}
}
