package com.koylubaevnt.stepik.java.basecourse.throwables;

public class Robot {

	public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
		int count = 3;
		while (count > 0) {
			try(RobotConnection robotConnection = robotConnectionManager.getConnection()) {
				robotConnection.moveRobotTo(toX, toY);
				count = 0;
			} catch (RobotConnectionException e) {
				count--;
				if(count == 0) {
					throw e;
				}
			}
		}
	}
}
