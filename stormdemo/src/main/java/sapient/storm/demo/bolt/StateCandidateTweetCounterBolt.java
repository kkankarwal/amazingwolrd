/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.bolt;

import static sapient.storm.demo.constant.DemoConstants.*;
import static sapient.storm.demo.constant.DemoConstants.DB_URL;
import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNT;
import static sapient.storm.demo.constant.DemoConstants.FIELD_STATE;
import static sapient.storm.demo.constant.DemoConstants.PASS;
import static sapient.storm.demo.constant.DemoConstants.USER;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class StateCandidateTweetCounterBolt extends BaseRichBolt {

	private static final long serialVersionUID = -4157219221605857574L;

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(StateCandidateTweetCounterBolt.class);

	private Map<StateCandidateTupleKey, Long> counterMap = new ConcurrentHashMap<StateCandidateTupleKey, Long>();
	private OutputCollector collector;
	private ConnectionProvider provider;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.provider = DBUtil.configureDBConnectionProvider();

		List<Column> columnSchema = Lists.newArrayList(new Column("STATE", java.sql.Types.VARCHAR), new Column("NAME", java.sql.Types.VARCHAR),
				new Column("COUNT", java.sql.Types.INTEGER));
		JdbcMapper simpleJdbcMapper = new SimpleJdbcMapper(columnSchema);

		this.provider.prepare();

	}

	@Override
	public void execute(Tuple input) {
		
		String state = null;
		String candidate = null;
		StateCandidateTupleKey tupleKey;
		Long count = null ;
		
		try {
			// Temp for debug only
			List<Object> objList = input.getValues();
			System.out.println("Tuple input: {} " + objList.toString());
			logger.info("Tuple input: {} ", objList.toString());
			
			state = input.getStringByField(FIELD_STATE);
			candidate = input.getStringByField(FIELD_CANDIDATE);
			tupleKey = new StateCandidateTupleKey(state, candidate);

			// Increase Tweet Country count
			count = counterMap.get(tupleKey);
			if (count == null) {
				count = new Long(0L);
			}
			count++;
			counterMap.put(tupleKey, count);
			System.out.println("Sate:{} " + state + ", Candidate: {} " + candidate + ", Count:{}" + count);
			logger.info("Sate:{}, Candidate: {}, Count:{}", state, candidate, count);

			// Put records in Database
			Connection connection = this.provider.getConnection();
			
			try {
				PreparedStatement pstmt = connection.prepareStatement(STATE_CANDIDATES_INS_SQL);
				pstmt.setString(1, state);
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
		collector.emit(new Values(state, candidate, count));
		collector.ack(input);
	}

	@Override
	public void cleanup() {
		this.provider.cleanup();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(FIELD_STATE, FIELD_CANDIDATE, FIELD_COUNT));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}

class StateCandidateTupleKey implements Serializable {

	private final String state;
	private final String name;

	public StateCandidateTupleKey(String state, String candidate) {
		this.state = state;
		this.name = candidate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + state.hashCode();
		result = (prime * result) + name.hashCode();
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
		StateCandidateTupleKey other = (StateCandidateTupleKey) obj;
		if (this.state != other.state || this.name != other.name)
			return false;
		return true;
	}
}
