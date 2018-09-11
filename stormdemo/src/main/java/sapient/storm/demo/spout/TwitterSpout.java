/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.spout;

import static sapient.storm.demo.constant.DemoConstants.FIELD_TWEET;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import twitter4j.Status;

public class TwitterSpout implements IRichSpout {
	private static final long serialVersionUID = -5360840124081329391L;
	
	private Map conf;
	private TopologyContext context;
	private SpoutOutputCollector collector;

	private TwitterClient client = new TwitterClient();
	private String[] filters;
	private LinkedBlockingQueue<Status> queue = new LinkedBlockingQueue<>(1000);

	public TwitterSpout(String... filters) {
		this.filters = filters;
	}

	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.conf = conf;
		this.context = context;
		this.collector = collector;
		client.startAccumulatingTweets(this.queue, this.filters);
	}

	public void close() {

	}

	public void activate() {

	}

	public void deactivate() {

	}

	public void nextTuple() {
		List<Status> list = new LinkedList<>();
		synchronized (queue) {
			queue.drainTo(list);
		}

		for (Status tweet : list) {
			collector.emit(new Values(tweet));
		}
		;
	}

	public void ack(Object msgId) {
	}

	public void fail(Object msgId) {

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(FIELD_TWEET));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
