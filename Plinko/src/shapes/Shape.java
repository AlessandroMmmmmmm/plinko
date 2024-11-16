package shapes;

import java.awt.Color;

import processing.core.PApplet;

/**
 * This class represents a shape.
 * 
 * @author Alessandro Montesi
 * @version 10-10-2024
 */
public abstract class Shape {

	// Fields
	private double x, y;
	private Color fillColor, strokeColor;
	private float strokeWeight;

	// Constructors

	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
		fillColor = null;
		strokeColor = null;
		strokeWeight = 1;
	}
	// Methods

	/**
	 * Sets the x position of the shape
	 * 
	 * @param x the new x coordinate of the shape
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y position of the shape
	 * 
	 * @param y the new y coordinate of the shape
	 */
	public void setY(double y) {
		this.y = y;

	}

	/**
	 * Sets the x and y position of the shape
	 * 
	 * @param x the new x coordinate of the shape
	 * @param y the new y coordinate of the shape
	 */
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Sets the fill color of the shape
	 * 
	 * @param c Color object of the desired color
	 */
	public void setFillColor(Color c) {
		fillColor = c;
	}

	/**
	 * Sets the stroke color of the shape
	 * 
	 * @param c Color object of the desired color
	 */
	public void setStrokeColor(Color c) {
		strokeColor = c;
	}

	/**
	 * Sets the stroke weight
	 * 
	 * @param f The thickness of the stroke in pixels
	 */
	public void setStrokeWeight(float f) {
		strokeWeight = f;
	}

	/**
	 * Returns the current fill color
	 * 
	 * @return the current fill color
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Returns the current stroke color
	 * 
	 * @return the current stroke color
	 */
	public Color getStrokeColor() {
		return strokeColor;
	}

	/**
	 * Returns the current stroke weight
	 * 
	 * @return the current stroke weight
	 */
	public float getStrokeWeight() {
		return strokeWeight;
	}

	/**
	 * Returns the current x position
	 * 
	 * @return the current x position
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the current y position
	 * 
	 * @return the current y position
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns the current x and y coordinates
	 * 
	 * @return the current x and y coordinate of the shape
	 */
	public String toString() {
		return "x: " + x + ", y: " + y;
	}

	/**
	 * Returns a double of the width of the shape
	 * 
	 * @return the width of the shape
	 */
	public abstract double getWidth();

	/**
	 * Returns a double of the height of the shape
	 * 
	 * @return the height of the shape
	 */
	public abstract double getHeight();

	/**
	 * Checks if two shapes of the same type are touching
	 * 
	 * @pre the other shape passed in must be of the same shape type as the object
	 *      calling this method. Always returns false for lines.
	 * @param other A shape object of the same type that the method is being called
	 *              from
	 * @return a boolean representing whether the two shapes are touching
	 */
	public abstract boolean isTouching(Shape other);

	/**
	 * Checks if a point at (x, y) is inside the shape
	 * 
	 * @param x the x coordinate of the point
	 * @param y the y coordinate of the point
	 * @return boolean representing whether the point is inside the shape
	 */
	public abstract boolean isPointInside(double x, double y);

	/**
	 * Calculates the perimeter of the shape
	 * 
	 * @return A double of the shape's perimeter
	 */
	public abstract double getPerimeter();

	/**
	 * Calculates the area of the shape
	 * 
	 * @return A double of the shape's area
	 */
	public abstract double getArea();

	/**
	 * Draws a shape using Processing library. Applies the fill color, stroke
	 * weight, and stroke color.
	 * 
	 * @param p A processing object
	 * @post Sets the fill color, stroke color, and stroke weight of the PApplet object passed in
	 */
	public void draw(PApplet p) {

		if (fillColor != null)
			p.fill(fillColor.getRGB());

		if (strokeColor != null)
			p.stroke(strokeColor.getRGB());

		p.strokeWeight(strokeWeight);

	}
	

}
