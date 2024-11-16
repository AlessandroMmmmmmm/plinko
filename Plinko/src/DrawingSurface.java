import processing.core.PApplet;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import shapes.*;
import modifiedShapes.*;
import java.util.Scanner;
import java.util.Random;

public class DrawingSurface extends PApplet {

	ArrayList<PhysicsShape> shapes;
	ArrayList<PhysicsShape> obstacles;
	ArrayList<Goal> goals;

	private int score;
	private boolean showVector = false;
	private int lineNum;

	public DrawingSurface() {
		shapes = new ArrayList<PhysicsShape>();
		obstacles = new ArrayList<PhysicsShape>();
		goals = new ArrayList<Goal>();
		score = 1000;
		lineNum = -1;

	}

	public void settings() {

		setSize(825, 800);
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter the mode (1 or 2): ");
//		mode = scanner.nextInt();

		int rowMulti = 50;
		double pDistance = 50;
		for (int i = 2; i <= 17; i++) {
			if (i % 2 == 0) {
				for (int j = 1; j < i / 2; j++) {
					obstacles.add(new PhysicsShape(
							new Circle(width / 2 + pDistance * (j - 1) + (pDistance / 2), i * rowMulti - 150, 10)));
					obstacles.add(new PhysicsShape(
							new Circle(width / 2 - pDistance * (j - 1) - (pDistance / 2), i * rowMulti - 150, 10)));
				}

//					for(int j = 1; j < i/2; j++) {
//						obstacles.add(new PhysicsShape(new Circle(width/2 -pDistance * (j-1) - (pDistance/2), i*rowMulti -150, 10)));
//					}
			} else {

				for (int j = 1; j < i / 2; j++) {
					obstacles.add(new PhysicsShape(new Circle(width / 2, i * rowMulti - 150, 10)));
					obstacles.add(new PhysicsShape(new Circle(width / 2 + pDistance * j, i * rowMulti - 150, 10)));
					obstacles.add(new PhysicsShape(new Circle(width / 2 - pDistance * j, i * rowMulti - 150, 10)));
				}
			}

		}

		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).setFillColor(new Color(200, 0, 0));
		}

		for (int i = 0; i < 8; i++) {
			if (i <= 6) {
				Goal g = new Goal(new Line(10 + width / 2 + i * 50, 750, width / 2 + 50 + i * 50, 750),
						(int) Math.pow(i * 10, (1 + i * 0.13)));
				g.setStrokeWeight(5);
				goals.add(g);

				Goal g2 = new Goal(new Line(-10 + width / 2 - i * 50, 750, width / 2 - 50 - i * 50, 750),
						(int) Math.pow(i * 10, (1 + i * 0.13)));
				g2.setStrokeWeight(5);
				goals.add(g2);
			} else if (i > 6) {
				Goal g = new Goal(new Line(10 + width / 2 + i * 50, 750, width / 2 + 50 + i * 50, 750),
						(int) Math.pow(i * 10, (1 + i * 0.13)));
				g.setStrokeWeight(5);
				goals.add(g);

				Goal g2 = new Goal(new Line(-10 + width / 2 - i * 50, 750, width / 2 - 50 - i * 50, 750),
						(int) Math.pow(i * 10, (1 + i * 0.13)));
				g2.setStrokeWeight(5);
				goals.add(g2);
			}
		}

		frameRate(60);

	}

	// The statements in draw() are executed until the
	// program is stopped. Each statement is executed in
	// sequence and after the last line is read, the first
	// line is executed again.
	public void draw() {

		background(232, 244, 248);

		fill(0, 0, 0);
		textSize(50);
		text("Money: " + score, 50, 50);
		textSize(20);
		fill(100, 100, 100);
		text("Click to spawn balls! \n1. Hold Shift to speed up \n2. Hold Space to slow down \n3. Press 1 to see velocity vectors",
				50, 80);

		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(this);
		}

		for (int i = 0; i < goals.size(); i++) {
			goals.get(i).draw(this);
			fill(0, 0, 0);
			textSize(15);

			if( goals.get(i).getX() < width/2) {
				text(goals.get(i).getPointValue(), (float) goals.get(i).getX() - 30, (float) goals.get(i).getY() + 25);
			}else if (goals.get(i).getX() >= width/2) {
				text(goals.get(i).getPointValue(), (float) goals.get(i).getX() + 10, (float) goals.get(i).getY() + 25);
			}
				
			
//			text(goals.get(i).getPointValue(), (float) goals.get(i).getX() - 30, (float) goals.get(i).getY() + 25);

//			text(goals.get(i).getPointValue(), (float) goals.get(i).getX() + goals.get(i).getLength()/4, (float) goals.get(i).getY() + 25);
		}

		outerLoop: for (int i = 0; i < shapes.size(); i++) {
			shapes.get(i).draw(this);
			shapes.get(i).act();
			shapes.get(i).accelerate(0, 0);
			shapes.get(i).setFillColor(new Color(200, 200, 200));
			for (int j = 0; j < obstacles.size(); j++) {
				shapes.get(i).circleCollision(obstacles.get(j));
			}

			if (showVector) {
				Line l = shapes.get(i).velocityVector();
				l.draw(this);
			}
			for (int k = 0; k < goals.size(); k++) {

				if (shapes.get(i).circleLineCollision(goals.get(k))) {
					shapes.remove(i);
					i--;
					score += goals.get(k).getPointValue();

					continue outerLoop;
				}
			}

			if (shapes.get(i).checkBoundary(width, height)) {
				if (shapes.get(i).getX() < 0) {
					shapes.get(i).setVelocity(0, shapes.get(i).getVelocityY());
					shapes.get(i).setPos(5 + shapes.get(i).getWidth() / 2, shapes.get(i).getY());
				} else if (shapes.get(i).getX() > width) {
					shapes.get(i).setVelocity(0, shapes.get(i).getVelocityY());
					shapes.get(i).setPos(width - shapes.get(i).getWidth() / 2 - 5, shapes.get(i).getY());
				} else {
					shapes.remove(i);
					i--;
				}
			}
		}

	}

	public void mousePressed() {

		if (score >= 50) {
			Random rand = new Random();
			shapes.add(new PhysicsShape(new Circle(width / 2 + rand.nextDouble(-20, 20), 0, 12)));
			score -= 50;
		}

	}

	public void avoidMouse(PhysicsShape s) {
		Point2D mouse = new Point2D.Double(mouseX, mouseY);
		Point2D shape = new Point2D.Double(s.getX(), s.getY());

		if (mouse.distance(shape) <= 75) {
			if (s.getX() > mouseX) {
				s.setVelocity(5, s.getVelocityY());
			} else {
				s.setVelocity(-5, s.getVelocityY());
			}
		} else {
			s.setVelocity(0, s.getVelocityY());
		}

	}

	public void keyPressed() {
		if (key == ' ') {
			frameRate(24);
		}

		if (keyCode == SHIFT) {
			frameRate(240);
		}

		if (key == '1') {
			showVector ^= true;
		}
	}

	public void keyReleased() {
		if (key == ' ') {
			frameRate(60);
		}

		if (keyCode == SHIFT) {
			frameRate(60);
		}
	}
}
