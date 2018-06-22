/**
 * 
 */
package com.manning.gwtia.ch07.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author KojlubaevNT
 *
 */
public interface TwitterServiceAsync {

	/**
	 * 
	 * @see com.manning.gwtia.ch07.shared.TwitterService#getUserTimeline(java.lang.String)
	 */
	void getUserTimeline(String screenName, AsyncCallback<ArrayList<FeedData>> callback);

}
