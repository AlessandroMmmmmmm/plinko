package shapes;

import processing.core.PApplet;

/**
 * 
 * A double precision model of a line. Focuses on the detection of intersection
 * between two lines. Uses Processing library to draw the line.
 * 
 * @author Alessandro Montesi
 * @version 10-10-24
 *
 */
public class Line extends Shape {

	private double x2, y2;

	/**
	 * Constructs a line object with two points
	 * 
	 * @param x1 a double representing the X position of the first point
	 * @param y1 a double representing the Y position of the first point
	 * @param x2 a double representing the X position of the second point
	 * @param y2 a double representing the Y position of the second point
	 */
	public Line(double x1, double y1, double x2, double y2) {
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;

	}

	/**
	 * Constructs a line object starting from a point (x,y), with the its length
	 * equal to length, and at an unit circle angle in degrees.
	 * 
	 * @param x      the x coordinate of the point where the line is drawn from.
	 * @param y      the y coordinate of the point where the line is drawn from.
	 * @param angle  the angle in degrees that the line is drawn at
	 * @param length the length of the line
	 * @return A new line object
	 */
	public static Line createLineWithAngle(double x, double y, double angle, double length) {
		
			double x2 = x+length*Math.cos(angle*(Math.PI/180));
			double y2 = y-length*Math.sin(angle*(Math.PI/180));
			Line l = new Line(x,y,x2,y2);
			return l;
			
		}

	/**
	 * Set the second point of the line to a new position
	 * 
	 * @param x2 a double representing the new X position
	 * @param y2 a double representing the new Y position
	 */
	public void setPoint2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Draws the line using the Processing library
	 * 
	 * @param drawer a PApplet object created using Processing
	 */
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.line((float) getX(), (float) getY(), (float) x2, (float) y2);
	}

	// Returns the x coordinate of the intersection point of this line and the other
	// line (assuming they continue forever)
	/**
	 * Returns the X coordinate of the intersection point of this line and another
	 * line
	 * 
	 * @param other Another line object to test for the intersection against this
	 *              line object
	 * @return X coordinate of intersection point or NaN if the lines are colinear
	 *         or parallel
	 */
	public double getIntersectionX(Line other) {

		double x1 = getX();
		double y1 = getX();

		double x3 = other.getX();
		double x4 = other.x2;
		double y3 = other.getY();
		double y4 = other.y2;

		double pX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4))
				/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

		return pX;
	}

	/**
	 * Returns the Y coordinate of the intersection point of this line and another
	 * line
	 * 
	 * @param other Another line object to test for the intersection against this
	 *              line object
	 * @return Y coordinate of intersection point or NaN if the lines are colinear
	 *         or parallel
	 */
	public double getIntersectionY(Line other) {
		double x1 = getX();
		double y1 = getX();

		double x3 = other.getX();
		double x4 = other.x2;
		double y3 = other.getY();
		double y4 = other.y2;

		double pY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4))
				/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		return pY;
	}

	/**
	 * Returns the boolean representing whether this line and another line are
	 * intersecting with each other
	 * 
	 * @param other Another line object to test for the intersection against this
	 *              line object
	 * @return boolean representing whether the two lines are intersecting or not.
	 *         Always returns false for colinear lines.
	 */
	public boolean isTouching(Shape other) {
		Line otherLine = (Line)other;
		double pX = getIntersectionX(otherLine);
		double pY = getIntersectionY(otherLine);

		double x1 = getX();
		double y1 = getX();

		double x3 = other.getX();
		double x4 = otherLine.x2;
		double y3 = other.getY();
		double y4 = otherLine.y2;

		double round = 0.00000001;

		if (Math.min(x1, x2) - round <= pX && Math.max(x1, x2) + round >= pX && Math.min(x3, x4) - round <= pX
				&& Math.max(x3, x4) + round >= pX && pY >= Math.min(y1, y2) - round && Math.max(y1, y2) + round >= pY
				&& Math.min(y3, y4) - round <= pY && Math.max(y3, y4) + round >= pY) {
			return true;
		}

		// not finished
		if (Double.isNaN(pX) && Double.isNaN(pY)) {
			return false;
		}

		return false;
	}

	/**
	 * Returns a string containing the X and Y coordinates of the the points that
	 * make up the line
	 * 
	 * @return string containing the X and Y coordinates of the two points that make
	 *         up the line
	 */
	public String toString() {
		return ("x1:" + getX() + "y1:" + getY() + "x2:" + x2 + "y2:" + y2);
	}

	/**
	 * Returns the width of the line, which is always 0.
	 * 
	 * @return 0
	 */
	public double getWidth() {
		return 0;
	}

	/**
	 * Returns the height of the line, which is always 0.
	 * 
	 * @return 0 
	 */
	public double getHeight() {
		return 0;
	}

	/**
	 * Always returns false;
	 * 
	 * @return false 
	 */
	public boolean isPointInside(double x, double y) {
		return false;
	}

	/**
	 * Returns the perimeter of the line, or in this case its length
	 * 
	 * @return the length of the line
	 */
	public double getPerimeter() {

		return Math.sqrt(Math.pow(x2 - getX(), 2) + Math.pow(y2 - getY(), 2));
	}

	/**
	 * Returns the area of the line, which is always 0
	 * 
	 * @return 0
	 */
	public double getArea() {
		return 0;
	}
	
	public double getX1() {
		return this.getX();
	}
	
	public double getX2() {
		return x2;
	}
	
	public double getY1() {
		return this.getY();
	}
	
	public double getY2() {
		return y2;
	}
	
	public double getLength() {
		return Math.sqrt(Math.pow(getX()-x2, 2) + Math.pow(getY()-y2, 2));
	}
	
	


}
