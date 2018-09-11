/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.topology;

import static backtype.storm.Config.NIMBUS_HOST;
import static backtype.storm.Config.TOPOLOGY_DEBUG;
import static sapient.storm.demo.constant.DemoConstants.CANDIDATE_COUNTER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.COUNTRY_COUNTER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.DESERIALIZER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.FIELD_CANDIDATE;
import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNTRY;
import static sapient.storm.demo.constant.DemoConstants.FIELD_HASHTAG;
import static sapient.storm.demo.constant.DemoConstants.FIELD_STATE;
import static sapient.storm.demo.constant.DemoConstants.HASHTAG_CANDIDATE_COUNTER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.HASHTAG_COUNTER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.HASHTAG_STATE_COUNTER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.RAW_TWEET_SPOUT;
import static sapient.storm.demo.constant.DemoConstants.STATE_CANDIDATE_COUNTER_BOLT;
import static sapient.storm.demo.constant.DemoConstants.STATE_COUNTER_BOLT;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import org.apache.storm.shade.org.json.simple.JSONValue;
import org.apache.thrift7.TException;
import org.apache.thrift7.transport.TTransportException;

import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.Nimbus.Client;
import backtype.storm.generated.TopologySummary;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.NimbusClient;
import backtype.storm.utils.Utils;
import sapient.storm.demo.bolt.CandidateTweetCounterBolt;
import sapient.storm.demo.bolt.CountryTweetCounterBolt;
import sapient.storm.demo.bolt.HashTagCandidateTweetCounterBolt;
import sapient.storm.demo.bolt.HashTagSateTweetCounterBolt;
import sapient.storm.demo.bolt.HashTagTweetCounterBolt;
import sapient.storm.demo.bolt.StateCandidateTweetCounterBolt;
import sapient.storm.demo.bolt.StateTweetCounterBolt;
import sapient.storm.demo.bolt.TweetDeserializerBolt;
import sapient.storm.demo.constant.NamedHashTags;
import sapient.storm.demo.spout.TwitterSpout;

/**
 * DemoTopologyBuilder class is responsible for Defining Spout/Bolt topology and deploying the build to Nimbes env.
 * @author kkanka
 *
 */
public class DemoTopologyBuilder implements Serializable {

	private static final long serialVersionUID = 8533373976844226956L;
	private static final String INPUT_JAR = "https://{INSERT_YOUR_GIT_REPO_HERE}/stormdemo";
	private static final String NIMBUS_HOST_NAME = "{INSERT_YOUR_NIMBUS_HOST_HERE}";
	private static final String TOPOLOGY_NAME = "testtopology";
	private static final Integer NIMBUS_PORT_VALUE = 6627;
	

	public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, TException, InterruptedException {

		// ------------------ Define Topology START ------------------------------------------//
		TopologyBuilder builder = new TopologyBuilder();
		// Twitter API --> TwitterSpout		
		 builder.setSpout(RAW_TWEET_SPOUT, new TwitterSpout(
				 NamedHashTags.TRUMP2016.getHashTag(), 
					NamedHashTags.TRUMP.getHashTag(),
					NamedHashTags.NEVERTRUMP.getHashTag(),
					NamedHashTags.DUMPTRUMP.getHashTag(),
					NamedHashTags.DONALNDTRUMP.getHashTag(),
					NamedHashTags.DONALNDTRUMPPREZ.getHashTag(),
					NamedHashTags.FEELTHEBERN.getHashTag(),
					NamedHashTags.SANDERS.getHashTag(),
					NamedHashTags.BERNIESANDERS.getHashTag(),
					NamedHashTags.BERNIESANDERS2016.getHashTag(),
					NamedHashTags.HILLARY2016.getHashTag(),
					NamedHashTags.HILLARYCLINTON.getHashTag(),
					NamedHashTags.ATIMEFORTRUTH.getHashTag(),
					NamedHashTags.CRUZCREW.getHashTag(),
					NamedHashTags.TEDCRUZ.getHashTag(),
				 	NamedHashTags.CRUZ2016.getHashTag()
				 	), 1);
		 
		builder.setBolt(DESERIALIZER_BOLT, new TweetDeserializerBolt(), 10).shuffleGrouping(RAW_TWEET_SPOUT);
		builder.setBolt(COUNTRY_COUNTER_BOLT, new CountryTweetCounterBolt(), 2).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_COUNTRY));
		builder.setBolt(STATE_COUNTER_BOLT, new StateTweetCounterBolt(), 10).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_STATE));
		builder.setBolt(CANDIDATE_COUNTER_BOLT, new CandidateTweetCounterBolt(), 5).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_CANDIDATE));
		builder.setBolt(HASHTAG_COUNTER_BOLT, new HashTagTweetCounterBolt(), 5).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_HASHTAG));

		builder.setBolt(STATE_CANDIDATE_COUNTER_BOLT, new StateCandidateTweetCounterBolt(), 5).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_STATE, FIELD_CANDIDATE));
		builder.setBolt(HASHTAG_CANDIDATE_COUNTER_BOLT, new HashTagCandidateTweetCounterBolt(), 5).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_HASHTAG, FIELD_CANDIDATE));
		builder.setBolt(HASHTAG_STATE_COUNTER_BOLT, new HashTagSateTweetCounterBolt(), 5).fieldsGrouping(DESERIALIZER_BOLT, new Fields(FIELD_STATE, FIELD_HASHTAG));

		// ------------------ Define Topology END ------------------------------------------//
		
		Map storm_conf = Utils.readStormConfig();
		storm_conf.put(NIMBUS_HOST, NIMBUS_HOST_NAME);
		storm_conf.put(TOPOLOGY_DEBUG, true);
		
		submitTopology(storm_conf, builder);
	}

	private static void submitTopology(Map storm_conf, TopologyBuilder topologyBuilder) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException, TException, InterruptedException {
		NimbusClient nimbusClient = createNimbusClient(storm_conf);
		String uploadedJarLocation = StormSubmitter.submitJar(storm_conf, INPUT_JAR);
		Iterator<TopologySummary> topologyList = nimbusClient.getClient().getClusterInfo().get_topologies_iterator();

		while (topologyList.hasNext()) {
			if (TOPOLOGY_NAME.equals(topologyList.next().get_name())) {
				nimbusClient.getClient().killTopology(TOPOLOGY_NAME);
				Thread.sleep(2000);
			}
		}

		String jsonConf = JSONValue.toJSONString(storm_conf);
		System.out.println("jsonConf >> " + jsonConf);
		nimbusClient.getClient().submitTopology(TOPOLOGY_NAME, uploadedJarLocation, jsonConf, topologyBuilder.createTopology());

		Thread.sleep(6000);
	}

	private static NimbusClient createNimbusClient(Map storm_conf) {
		Client client = NimbusClient.getConfiguredClient(storm_conf).getClient();
		NimbusClient nimbusclient = null;
		try {
			nimbusclient = new NimbusClient(storm_conf, NIMBUS_HOST_NAME, NIMBUS_PORT_VALUE);
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		return nimbusclient;
	}


}
