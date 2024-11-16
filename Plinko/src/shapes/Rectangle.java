package shapes;

import java.awt.Color;
import java.awt.geom.Point2D;
import processing.core.PApplet;

/**
 * This class represents a double precision Rectangle.
 * 
 * @author Alessandro Montesi
 * @version 10-10-2024
 */
public class Rectangle extends Shape {

	private double width, height;

	/**
	 * Creates a default instance of a Rectangle object with all dimensions set to
	 * zero.
	 */
	public Rectangle() {
		super(0, 0);
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Creates a new instance of a Rectangle object with the left and right edges of
	 * the rectangle at x and x + width. The top and bottom edges are at y and y +
	 * height.
	 * 
	 * @param x      x-coordinate of the upper left corner of the rectangle
	 * @param y      y-coordinate of the upper left corner of the rectangle
	 * @param width  width of the rectangle
	 * @param height height of the rectangle
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;

	}

	/**
	 * Calculates and returns the perimeter of the rectangle
	 * 
	 * @return the perimeter of the rectangle
	 */
	public double getPerimeter() {
		return 2 * (Math.abs(width) + Math.abs(height));
	}

	/**
	 * Calculates and returns the area of the rectangle
	 * 
	 * @return the area of the rectangle
	 */
	public double getArea() {
		return Math.abs(width * height);
	}

	/**
	 * Returns the center point of the rectangle
	 * 
	 * @return The center point of the rectangle
	 */
	public Point2D.Double getCenter() {
		return new Point2D.Double(getX() + width / 2, getY() + height / 2);
	}

	/**
	 * Determines whether the point x,y is contained inside this rectangle
	 * 
	 * @param x the x-coordinate of the point to check
	 * @param y the y-coordinate of the point to check
	 * @return true if the point x,y is contained inside, or on the border, of this
	 *         rectangle.
	 */
	public boolean isPointInside(double x, double y) {
		double left = Math.min(getX(), getX() + width);
		double right = Math.max(getX(), getX() + width);
		double bottom = Math.min(getY(), getY() + height);
		double top = Math.max(getY(), getY() + height);

		if (x < right && x > left && y < top && y > bottom) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a boolean stating whether this rectangle and another rectangle are
	 * touching
	 * 
	 * @pre Only works for Rectangle objects
	 * @param other another rectangle
	 * @return boolean stating whether the two rectangles are touching
	 * 
	 */
	public boolean isTouching(Shape other) {

		Rectangle otherRect = (Rectangle) other;
		
		double left = Math.min(getX(), getX() + width);
		double right = Math.max(getX(), getX() + width);
		double bottom = Math.min(getY(), getY() + height);
		double top = Math.max(getY(), getY() + height);

		double otherLeft = Math.min(otherRect.getX(), otherRect.getX() + otherRect.width);
		double otherRight = Math.max(otherRect.getX(), otherRect.getX() + otherRect.width);
		double otherBottom = Math.min(otherRect.getY(), otherRect.getY() + otherRect.height);
		double otherTop = Math.max(otherRect.getY(), otherRect.getY() + otherRect.height);

		if (right >= otherLeft && left <= otherRight && top >= otherBottom && bottom <= otherTop) {
			return true;
		}

		return false;
	}

	/**
	 * Draws a Rectangle object using Processing.
	 * 
	 * @param marker the PApplet to be used to draw the rectangle
	 * @pre marker must not be null, and appropriate settings should have been
	 *      selected (color, fill, etc)
	 */
	public void draw(PApplet marker) {

		super.draw(marker);
//		if (getFillColor() != null)
//			marker.fill(getFillColor().getRGB());
		marker.rect((float) getX(), (float) getY(), (float) width, (float) height);
//		marker.noFill();
	}

	/**
	 * Returns a string containing the x and y coordinates of the top left point of
	 * the rectangle, as well as the width and height.
	 * 
	 * @return string containing the x and y coordinate of the top left corner of
	 *         the rectangle, as well as the width and height.
	 */
	public String toString() {
		return "x: " + getX() + ", y: " + getY() + ", width: " + width + ", height: " + height;
	}

	/**
	 * Sets the coordinate of the bottom right corner of the Rectangle
	 * 
	 * @param x the x-coordinate of the new bottom right point
	 * @param y the y-coordinate of the new bottom right point
	 */
	public void setBottomRight(double x, double y) {
		width = x - getX();
		height = y - getY();
	}

	/**
	 * Sets the color of the rectangle to a random rgb value
	 * 
	 */
	public void setColorRandom() {
		Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		super.setFillColor(c);

	}

	/**
	 * Returns a whether another rectangle is completely inside of this rectangle
	 * 
	 * @pre Only works for Rectangle objects
	 * @param other the other rectangle
	 * @return boolean that tracks if the other rectangle is inside of this
	 *         rectangle
	 */
	public boolean isInside(Rectangle other) {

		Rectangle otherRect = (Rectangle) other;
		if (otherRect.getArea() < this.getArea()) {
			double left = Math.min(getX(), getX() + width);
			double right = Math.max(getX(), getX() + width);
			double bottom = Math.min(getY(), getY() + height);
			double top = Math.max(getY(), getY() + height);
			double otherLeft = Math.min(otherRect.getX(), otherRect.getX() + otherRect.width);
			double otherRight = Math.max(otherRect.getX(), otherRect.getX() + otherRect.width);
			double otherBottom = Math.min(otherRect.getY(), otherRect.getY() + otherRect.height);
			double otherTop = Math.max(otherRect.getY(), otherRect.getY() + otherRect.height);

			if (otherLeft > left && otherRight < right && otherTop < top && otherBottom > bottom) {
				return true;
			}
		}
		return false;
	}

	public double getWidth() {

		return width;
	}

	public double getHeight() {

		return height;
	}

	

}