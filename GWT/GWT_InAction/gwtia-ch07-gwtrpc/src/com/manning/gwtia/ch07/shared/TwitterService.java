/**
 * 
 */
package com.manning.gwtia.ch07.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface TwitterService extends RemoteService {

	ArrayList<FeedData> getUserTimeline(String screenName) throws GTwitterException;
	
}
