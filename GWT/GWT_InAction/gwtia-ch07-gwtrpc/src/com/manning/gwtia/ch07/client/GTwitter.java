package com.manning.gwtia.ch07.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.manning.gwtia.ch07.shared.FeedData;
import com.manning.gwtia.ch07.shared.TwitterService;
import com.manning.gwtia.ch07.shared.TwitterServiceAsync;

public class GTwitter extends Composite {

	private TextBox txtScreenName = new TextBox();
	private Button btnGetTweets = new Button("Get Tweets");
	private VerticalPanel tweetPanel = new VerticalPanel();
	
	public GTwitter() {
		FlowPanel rootPanel = new FlowPanel();
		rootPanel.add(txtScreenName);
		rootPanel.add(btnGetTweets);
		rootPanel.add(tweetPanel);
		
		initWidget(rootPanel);
		
		final AsyncCallback<ArrayList<FeedData>> upadteTweetPanelCallback = new AsyncCallback<ArrayList<FeedData>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
				
			}
			
			/* (non-Javadoc)
			 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
			 */
			@Override
			public void onSuccess(ArrayList<FeedData> result) {
				tweetPanel.clear();
				
				for(FeedData status : result) {
					PredefinedFormat fmt = PredefinedFormat.TIME_SHORT;
					String dateStr = DateTimeFormat.getFormat(fmt).format(status.getCreatedAt());
					tweetPanel.add(new Label(dateStr + ": " + status.getText()));
				}
				
			}
		};
		
		btnGetTweets.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				TwitterServiceAsync service = GWT.create(TwitterService.class);
			
				/*
				 * Альтернативный путь установки URL на сервере. Вместо использования аннотации: @RemoteServiceRelativePath  
				 */
				//((ServiceDefTarget) service).setServiceEntryPoint(GWT.getModuleBaseURL() + "service");
				
				service.getUserTimeline(txtScreenName.getText(), upadteTweetPanelCallback);
				
			}
		});
	}
	
}
