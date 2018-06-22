package com.manning.gwtia.ch07.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.manning.gwtia.ch07.shared.FeedData;
import com.manning.gwtia.ch07.shared.GTwitterException;
import com.manning.gwtia.ch07.shared.TwitterService;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterServiceImpl extends RemoteServiceServlet implements TwitterService {

	private static final long serialVersionUID = 7838385082912966814L;

	public static void main(String[] args) throws Exception {
		TwitterService service = new TwitterServiceImpl();
		ArrayList<FeedData> resList = service.getUserTimeline("@ianchesnut");
		
		for(FeedData status : resList) {
			System.out.println(status.getCreatedAt() + ": " + status.getText());
		}
	}

	@Override
	public ArrayList<FeedData> getUserTimeline(String screenName) throws GTwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		ArrayList<FeedData> result = new ArrayList<>();
		
		try {
			ResponseList<Status> responses = twitter.getUserTimeline(screenName);
			for(Status status : responses) {
				result.add(new FeedData(status.getCreatedAt(), status.getText()));
			}
		} catch (TwitterException e) {
			throw new GTwitterException(e.getMessage());
		}
		return result;
	}

}
