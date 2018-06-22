package ru.intuit.deepjava.firstIndependentWork.gameMehanics.base;

public class Field {

	private int rowCount;
	private int columnCount;
	private PointState[][] points;
	
	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		
		points = new PointState[rowCount][columnCount];
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < columnCount; j++) {
				points[i][j] = PointState.EMPTY;
			}	
		}
	}

	public PointState getPointState(int i, int j) {
		return points[i][j];
	}

	public void setPoints(int i, int j, PointState point) {
		this.points[i][j] = point;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}
	
	public boolean isEmpty(int i, int j) {
		return (points[i][j] == PointState.EMPTY) ? true : false;
	}
	
	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("'[");
		boolean firstTime = true;
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < columnCount; j++) {
				if(!isEmpty(i, j)) {
					if(!firstTime) {
						sb.append(",");
					} else {
						firstTime = false;
					}
					sb.append("[")
						.append(i)
						.append(",")
						.append(j)
						.append(",\"")
						.append(getPointState(i, j))
						.append("\"]");
				}
			}	
		}
		sb.append("]'");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Field)) {
			return false;
		}
		
		Field field = (Field) obj;
		if(field.getRowCount() != getRowCount() ||
				field.getColumnCount() != getColumnCount()) {
			return false;
		}
		
		for(int i = 0; i < getRowCount(); i++) {
			for(int j = 0; j < getColumnCount(); j++) {
				if(getPointState(i, j) != field.getPointState(i, j)) {
					return false;
				}
			}	
		}
		return true;
	}
}
