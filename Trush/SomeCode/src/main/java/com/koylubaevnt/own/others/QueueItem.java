package com.koylubaevnt.own.others;

import java.nio.file.Path;

public class QueueItem {

	private String data;
	private Path path;
	
	public QueueItem(String data, Path path) {
		super();
		this.data = data;
		this.path = path;
	}
	public String getData() {
		return data;
	}
	public Path getPath() {
		return path;
	}
	
	
}
