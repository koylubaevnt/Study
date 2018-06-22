package com.koylubaevnt.stepik.java.basecourse;

public class Robot {
	int x;
	int y;
	Direction dir;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public Robot(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public Direction getDirection() {
		return dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void turnLeft() {
		if (dir == Direction.UP) {
			dir = Direction.LEFT;
		} else if (dir == Direction.DOWN) {
			dir = Direction.RIGHT;
		} else if (dir == Direction.LEFT) {
			dir = Direction.DOWN;
		} else if (dir == Direction.RIGHT) {
			dir = Direction.UP;
		}
	}

	public void turnRight() {
		if (dir == Direction.UP) {
			dir = Direction.RIGHT;
		} else if (dir == Direction.DOWN) {
			dir = Direction.LEFT;
		} else if (dir == Direction.LEFT) {
			dir = Direction.UP;
		} else if (dir == Direction.RIGHT) {
			dir = Direction.DOWN;
		}
	}

	public void stepForward() {
		if (dir == Direction.UP) {
			y++;
		}
		if (dir == Direction.DOWN) {
			y--;
		}
		if (dir == Direction.LEFT) {
			x--;
		}
		if (dir == Direction.RIGHT) {
			x++;
		}
	}

	@Override
	public String toString() {
		return dir + " " + x + " " + y;
	}

	public static void moveRobot(Robot robot, int toX, int toY) {
		switch (robot.getDirection()) {
		case UP:
			// Идем по оси Y (если смотрит робот в правильную сторону) или
			// поворачиваемся для оси X
			while (robot.getY() < toY) {
				robot.stepForward();
			}
			if (robot.getX() != toX || robot.getY() != toY) {
				robot.turnLeft();
				moveRobot(robot, toX, toY);
			}
			break;
		case DOWN:
			// Идем по оси Y (если смотрит робот в правильную сторону) или
			// поворачиваемся для оси X
			while (robot.getY() > toY) {
				robot.stepForward();
			}
			if (robot.getX() != toX || robot.getY() != toY) {
				robot.turnLeft();
				moveRobot(robot, toX, toY);
			}
			break;
		case LEFT:
			// Идем по оси X (если смотрит робот в правильную сторону) или
			// поворачиваемся для оси Y
			while (robot.getX() > toX) {
				robot.stepForward();
			}
			if (robot.getY() != toY || robot.getX() != toX) {
				robot.turnLeft();
				moveRobot(robot, toX, toY);
			}
			break;
		case RIGHT:
			// Идем по оси X (если смотрит робот в правильную сторону) или
			// поворачиваемся для оси Y
			while (robot.getX() < toX) {
				robot.stepForward();
			}
			if (robot.getY() != toY || robot.getX() != toX) {
				robot.turnLeft();
				moveRobot(robot, toX, toY);
			}
			break;
		}
	}
	
	public static void main(String[] args) {
		Robot robo = new Robot(0, 0, Robot.Direction.UP);
        Robot.moveRobot(robo, 3, 0);
        System.out.println(robo);
    }
}