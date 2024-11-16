package modifiedShapes;

import shapes.*;

public class Goal extends PhysicsShape{

	private int pointValue;
	
	public Goal(Line s, int p) {
		super(s);
		pointValue = p;
	}
	
	public int getPointValue() {
		return pointValue;
	}
	
	public float getLength() {
		
		Line l = (Line)this.getShape();
		return (float)l.getLength();
	}

}
