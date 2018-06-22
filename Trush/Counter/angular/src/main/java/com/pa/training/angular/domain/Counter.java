package com.pa.training.angular.domain;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = 6959831829974455744L;

	private String id;
	
	private volatile int  max;
	private volatile int current;
	private volatile Status status = Status.STOPPED;
	
	public Counter() { }

	public Counter(String id, int max) {
		this.id = id;
		this.max = max;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Counter other = (Counter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Counter [id=" + id + ", max=" + max + ", current=" + current + ", status=" + status +"]";
	}
	
	public static enum Status {
		RUNNING,
		PAUSED,
		STOPPED
	}
}
