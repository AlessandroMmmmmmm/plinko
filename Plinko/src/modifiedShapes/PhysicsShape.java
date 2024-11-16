package modifiedShapes;

import processing.core.PApplet;
import processing.core.PVector;
import shapes.*;
import java.awt.Color;
import java.awt.geom.Point2D;

public class PhysicsShape {

	private Shape s;
	private double vx, vy;
	private static final double gravity = 0.06;

	public PhysicsShape(Shape s) {
		this.s = s;
		vx = 0;
		vy = 0;
	}

	public void draw(PApplet surface) {

		this.act();

		// DRAW
		s.draw(surface);

	}

	public void act() {

		s.translate(vx, vy);

		// gravity
//		if(vy <5) {
//			this.accelerate(0, gravity);
//		}else {
//			vy = 5;
//		}

	}

	public boolean checkBoundary(float width, float height) {
		if (s.getY() >= height) {
			return true;
		} else if (s.getX() > width || s.getX() < 0) {
			return true;
		}
		return false;

	}

	public void accelerate(double ax, double ay) {

		vx += ax;
		vy += ay;
		if (vy < 5)
			vy += ay + gravity;
		else
			vy = 5;

	}

	public boolean isPointInside(double x, double y) {
		return s.isPointInside(x, y);
	}

	public double getX() {
		return s.getX();
	}

	public double getY() {
		return s.getY();
	}

	public double getVelocityX() {
		return vx;
	}

	public double getVelocityY() {
		return vy;
	}

	public void setVelocity(double x, double y) {
		this.vx = x;
		this.vy = y;
	}

	public Shape getShape() {
		return s;
	}

	public float getWidth() {
		return (float) s.getWidth();
	}

	public void setPos(double x, double y) {
		s.setX(x);
		s.setX(y);
	}

	public boolean isTouchingSame(PhysicsShape other) {

		if (other.s.getClass() == s.getClass()) {
			return s.isTouching(other.s);
		}

		throw new IllegalArgumentException("The shapes should be of the same type");
	}

	public void circleCollision(PhysicsShape other) {

		Circle cOther = (Circle) other.s;

		double x1 = s.getX();
		double y1 = s.getY();
		double x2 = cOther.getX();
		double y2 = cOther.getY();

		double rad1 = ((Circle) this.s).getRadius();
		double rad2 = cOther.getRadius();
		double minDistance = rad1 + rad2;

		double dx = x2 - x1;
		double dy = y2 - y1;
		double distance = Math.sqrt(dx * dx + dy * dy);

		if (distance < minDistance) {
			double nx = dx / distance;
			double ny = dy / distance;


			double overlap = minDistance - distance;
			s.setPos(x1 - overlap * nx, y1 - overlap * ny);

			
			double dotProduct = vx * nx + vy * ny;
			this.vx -= 1.3 * dotProduct * nx;
			this.vy -= 1.3 * dotProduct * ny;
			this.vy *= 0.9;



			
			//Add bias towards middle of screen
//			double cX = 412.5 - x1;
//			double cY = 400 - y1;
//			double distanceFromMid = Math.sqrt(cX * cX + cY * cY);
//			this.vx += 0.05 * (cX / distanceFromMid);
//		    this.vy += 0.05 *(cY / distanceFromMid);

//		    System.out.println("Collision at: (" + s.getX() + ", " + s.getY() + ")");
		}
	}

	//call on circles
	public boolean circleLineCollision(PhysicsShape other) {
		 if (!(other.getShape() instanceof Line)) {
			 throw new IllegalArgumentException("Expected a Line shape");
		 }
		 
		 if(this.s instanceof Circle) {
			 Circle circle = (Circle)s;
			 
			 double circleX = circle.getX(); // Circle center x-coordinate
			 double circleY = circle.getY(); // Circle center y-coordinate
			 double radius = circle.getRadius(); // Circle radius
			 
			 Line l = (Line) other.getShape();
			 double lineStartX = l.getX1(); // Line start x-coordinate
			 double lineStartY = l.getY1(); // Line start y-coordinate
			 double lineEndX = l.getX2(); // Line end x-coordinate
			 double lineEndY = l.getY2();
			 
			 double lineDX = lineEndX - lineStartX;
			 double lineDY = lineEndY - lineStartY;
			 double startToCircleX = circleX - lineStartX;
			 double startToCircleY = circleY - lineStartY;
			 
			 // Project the circle's center onto the line, clamped to the segment
			 double lineLengthSquared = lineDX * lineDX + lineDY * lineDY;
			 double t = (startToCircleX * lineDX + startToCircleY * lineDY) / lineLengthSquared;
			 t = Math.max(0, Math.min(1, t)); // Clamp t to the range [0, 1]

			 // Calculate the closest point on the line to the circle's center
			 double closestPointX = lineStartX + t * lineDX;
			 double closestPointY = lineStartY + t * lineDY;

			 // Calculate the distance from the closest point to the circle's center
			 double distanceX = circleX - closestPointX;
			 double distanceY = circleY - closestPointY;
			 double distanceSquared = distanceX * distanceX + distanceY * distanceY;

			 // Check if the distance is less than or equal to the circle's radius
			 return (distanceSquared <= radius * radius);
		 }else {
			 return false;
		 }
		
//		 boolean b = false;
//		if (other.s instanceof Line) {
//
//			Line l1 = velocityVector();
//			Line l2 = (Line) other.s;
//
//			if (l1.isTouching(l2)) {
//				double interX = l1.getIntersectionX(l2);
//				double interY = l1.getIntersectionY(l2);
//
//				if (s.isPointInside(interX, interY)) {
//					b = true;
//				}
//
//			}
//			return b;
	}

	public Line velocityVector() {
		double length = Math.sqrt(vx * vx + vy * vy);
		double angle = Math.atan2(vy, vx) * (180.0 / Math.PI) * (-1);

		if (angle < 0) {
			angle += 360;
		}
		Line l = Line.createLineWithAngle(s.getX(), s.getY(), angle, length * 40);
		return l;

	}

	public void setFillColor(Color c) {
		s.setFillColor(c);
	}

	public void setStrokeWeight(int x) {
		s.setStrokeWeight(x);
	}

}
