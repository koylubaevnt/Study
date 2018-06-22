/**
 * 
 */
package com.manning.gwtia.ch07.server;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * @author KojlubaevNT
 *
 */
public class TwitterServiceImplx {

	public static void main(String[] args) throws Exception {
		TwitterServiceImplx impl = new TwitterServiceImplx();
		ResponseList<Status> resList = impl.getUserTimeline("ianchesnut");
		
		for(Status status : resList) {
			System.out.println(status.getCreatedAt() + ": " + status.getText());
		}
	}
	
	public ResponseList<Status> getUserTimeline(String screenName) throws TwitterException {
		//ConfigurationBuilder cb = new ConfigurationBuilder();
		
		//Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		Twitter twitter = new TwitterFactory().getInstance();
		return twitter.getUserTimeline(screenName);
	}
}
