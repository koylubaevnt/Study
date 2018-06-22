package com.apress.prospring4.ch3;

public interface ArtworkSender {

	void sendArtwork(String artwork, Recipient recipient);
	String getFriendlyName();
	String getShortName();
	
}
