package com.apress.prospring4.ch16;

import java.util.List;

public class Grid<T> {

	private int totalPages;
	private int currentPage;
	private long totalRecords;
	private List<T> data;
	
	public Grid() {
	}
	
	public Grid(int currentPage, int totalPages, long totalRecords, List<T> data) {
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.data = data;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public long getTotalRecords() {
		return totalRecords;
	}
	
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
