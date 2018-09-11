/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.spout;

import static sapient.storm.demo.constant.DemoConstants.ACCESS_TOKEN;
import static sapient.storm.demo.constant.DemoConstants.ACCESS_TOKEN_SECRET;
import static sapient.storm.demo.constant.DemoConstants.CONSUMER_KEY;
import static sapient.storm.demo.constant.DemoConstants.CONSUMER_SECRET;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient implements Serializable {

	static Logger LOG = LoggerFactory.getLogger(TwitterClient.class);

	private String[] filters;

	private Configuration getConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET)
				.setOAuthAccessToken(ACCESS_TOKEN).setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		return cb.build();
	}

	public List<Status> query(String predicate, int maxCount) {
		LOG.info("Extracting data from Twitter APIs ...");
		Twitter twitter = new TwitterFactory(getConfiguration()).getInstance();
		Query query = new Query(predicate);
		query.setCount(maxCount);
		QueryResult result;
		List<Status> reultList = null;
		try {
			result = twitter.search(query);
			reultList = result.getTweets();
		} catch (TwitterException e) {
			LOG.error("Error in retrieveing tweets.", e);
		}
		return reultList;
	}

	public void startAccumulatingTweets(LinkedBlockingQueue<Status> queue, String... filters) {
		LOG.info("Collect Trweets ..");
		this.filters = filters;
		TwitterStream twitterStream = new TwitterStreamFactory(getConfiguration()).getInstance();
		twitterStream.addListener(new TwitterListener(queue));
		twitterStream.filter(getFilterQuery());
	}

	private FilterQuery getFilterQuery() {
		FilterQuery filterQuery = new FilterQuery();
		filterQuery.track(filters);
		return filterQuery;
	}

	
}
