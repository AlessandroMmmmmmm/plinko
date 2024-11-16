package shapes;

import java.awt.Color;
import java.awt.geom.Point2D;
import processing.core.PApplet;

/**
 * This class represents a double precision circle.
 * 
 * @author Alessandro Montesi
 * @version 10-10-2024
 */
public class Circle extends Shape {

	private double radius;


	/**
	 * Creates a default instance of a Circle object with all dimensions set to
	 * zero.
	 */
	public Circle() {
		super(0, 0);
		this.radius = 0;

	}

	/**
	 * Creates a new instance of a Circle object with its center point at x and y,
	 * and its radius set to r
	 * 
	 * @param x x-coordinate of the center of the circle
	 * @param y y-coordinate of the center of the circle
	 * @param r radius of the circle
	 */
	public Circle(double x, double y, double r) {
		super(x, y);
		this.radius = Math.abs(r);

	}

	/**
	 * Calculates circumference of the circle
	 * 
	 * @return the circumference of the circle
	 */
	public double getPerimeter() {
		return Math.abs((Math.PI * 2 * radius));
	}

	/**
	 * Calculates the area of the circle
	 * 
	 * @return the area of the circle
	 */
	public double getArea() {
		return Math.abs(Math.PI * (radius * radius));
	}

	/**
	 * Draws a new instance of a Circle object.
	 * 
	 * @param marker the PApplet to be used to draw the circle
	 * @pre marker must not be null, and appropriate settings should have been
	 *      selected (color, fill, etc)
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
//		if(color != null)
//		marker.fill(color.getRGB());
		marker.circle((float) getX(), (float) getY(), (float) radius * 2);
//		marker.noFill();
	}

	/**
	 * Determines whether a 2D point is contained inside this circle
	 * 
	 * @param x the x-coordinate of the point to check
	 * @param y the y-coordinate of the point to check
	 * @return true if the point x,y is contained inside, or on the border, of this
	 *         rectangle.
	 */
	public boolean isPointInside(double x, double y) {
		Point2D point = new Point2D.Double(x, y);
		Point2D center = new Point2D.Double(getX(), getY());
		if (radius >= center.distance(x, y)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a boolean stating whether this circle and another circle are touching
	 * 
	 * @pre Do not pass any shape other than a Circle object
	 * @param other another circle
	 * @return boolean stating whether the two circles are touching
	 * 
	 */
	public boolean isTouching(Shape other) {

		Circle otherCircle = (Circle)other;
		if (radius + otherCircle.radius >= Math
				.sqrt(Math.pow(otherCircle.getX() - getX(), 2) + Math.pow(otherCircle.getY() - getY(), 2))) {
			return true;
		}

		return false;
	}

	/**
	 * Changes the radius of the circle.
	 * 
	 * @param x the x-coordinate of the other point used to calculate the new radius
	 * @param y the y-coordinate of the other point used to calculate the new radius
	 */
	public void scaleRadius(double x, double y) {
		Point2D center = new Point2D.Double(getX(), getY());
		Point2D mouse = new Point2D.Double(x, y);

		radius = center.distance(mouse);
	}

	/**
	 * Returns the center point of the circle
	 * 
	 * @return The center point of the circle
	 */
	public Point2D.Double getCenter() {
		return new Point2D.Double(getX(), getY());
	}

	/**
	 * Returns a string containing the x and y coordinates of the center point of
	 * the circle, as well as the radius
	 * 
	 * @return string containing the x and y coordinate of the center of the circle,
	 *         along with the radius.
	 */
	public String toString() {
		return "x: " + getX() + ", y: " + getY() + ", radius: " + radius;
	}

	/**
	 * Sets the color of the circle to a random rgb value.
	 * 
	 */
	public void setColorRandom() {
		Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		super.setFillColor(c);
	}

	/**
	 * Returns whether another circle is completely inside of this circle or not
	 * 
	 * @pre Other shape must be a circle object.
	 * @param other the other circle
	 * @return boolean that tracks if the other circle is inside of this circle
	 */
	public boolean isInside(Shape other) {

		Circle otherCircle = (Circle)other;
		
		Point2D center = new Point2D.Double(getX(), getY());
		Point2D otherCenter = new Point2D.Double(otherCircle.getX(), otherCircle.getY());

		if (radius > otherCircle.radius && center.distance(otherCenter) + otherCircle.radius < radius) {
			return true;
		}
		return false;
	}


	public double getWidth() {

		return 2*radius;
	}

	public double getRadius() {

		return radius;
	}

	public double getHeight() {

		return 2*radius;
	}

}
