/**
* Copyright (C) [2016] Sapient Corporation. All rights reserved.
* The software is property of Sapient Corporation and should not be used without prior consent of Sapient Corporation.
* @author: Brian Martin (bmartin@sapient.com), Pranav Tandon (ptandon@sapien.com), Kamal kankarwal(kkankarwal@sapient)
*/
package sapient.storm.demo.spout;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterListener implements StatusListener {
	private static Logger log = LoggerFactory.getLogger(TwitterListener.class);
	private LinkedBlockingQueue<Status> queue;

	public TwitterListener(LinkedBlockingQueue<Status> queue) {
		this.queue = queue;
	}

	@Override
	public void onStatus(final Status status) {
		try {
			synchronized (queue) {
				queue.put(status);
			}
			log.info("Received onStatus: " + status.getText());
		} catch (InterruptedException e) {
			log.error("Error in pushing tweet on queue.", e);
		}
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		log.info("Received a status deletion notice id:" + statusDeletionNotice.getStatusId());
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		log.info("Received track limitation notice:" + numberOfLimitedStatuses);
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		log.info("Received scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	}

	@Override
	public void onStallWarning(StallWarning warning) {
		log.info("Received stall warning:" + warning);
	}

	@Override
	public void onException(Exception ex) {
		log.error("Received exceptions. Exiting for twitter api safety. onException: ", ex);
		System.exit(1);
	}
}