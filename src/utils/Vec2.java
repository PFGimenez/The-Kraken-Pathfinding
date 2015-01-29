package utils;

/**
 * Classe de calcul de vecteurs de dimension 2
 * @author martial
 * @author pf
 *
 */

// TODO s'occuper des double et float

public class Vec2
{

	public int x;
	public int y;
	
	public Vec2()
	{
		x = 0;
		y = 0;
	}

	public Vec2(int requestedX, int requestedY)
	{
		x = requestedX;
		y = requestedY;
	}

	// Do not square a length, use squared length directly
	// to increase performances
	public int squaredLength()
	{
		return x*x + y*y;
	}

	// Returns this vec2's magnitude
	public float length()
	{
		return (float) Math.hypot(x, y);
	}
	
	// dot product
	public int dot(Vec2 other)
	{
		return x*other.x + y*other.y;
	}
	

	// build a new Vec2 by summing the calling Vec2 and the one in args
	public Vec2 plusNewVector(Vec2 other)
	{
		return new Vec2(x + other.x, y + other.y);
	}
	
	// build a new Vec2 with the value obtained by decrementing the
	// calling Vec2 by the provided Vec2 in args
	public Vec2 minusNewVector(Vec2 other)
	{
		return new Vec2(x - other.x, y - other.y);
	}

	public void plus(Vec2 other)
	{
		x += other.x;
		y += other.y;
	}
	
	public void minus(Vec2 other)
	{
		x -= other.x;
		y -= other.y;
	}

	public Vec2 clone()
	{
		return new Vec2(this.x, this.y);
	}
	
	public int squaredDistance(Vec2 other)
	{
		int tmp_x = x-other.x, tmp_y = y-other.y;
		return tmp_x*tmp_x + tmp_y*tmp_y;
	}

	public float distance(Vec2 other)
	{
		return (float) Math.sqrt(squaredDistance(other));
	}
	
	public String toString()
	{
		return "("+x+","+y+")";
	}
	
	public boolean equals(Vec2 other)
	{
		return x == other.x && y == other.y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (!(obj instanceof Vec2))
			return false;
		Vec2 other = (Vec2) obj;
		if (x != other.x || (y != other.y))
			return false;
		return true;
	}

	/**
	 * Copie this dans other.
	 * @param other
	 */
	public void copy(Vec2 other)
	{
	    other.x = x;
	    other.y = y;
	}

	public Vec2 middleNewVector(Vec2 b)
	{
		return new Vec2((x+b.x)/2, (y+b.y)/2);
	}

	public Vec2 scalarNewVector(float f)
	{
		return new Vec2((int)(f*x), y = (int)(f*y));
	}
	
	public Vec2 rotateNewVector(double angle, Vec2 centreRotation)
	{
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Vec2((int)(cos*(x-centreRotation.x)-sin*(y-centreRotation.y))+centreRotation.x,
		(int)(sin*(x-centreRotation.x)+cos*(y-centreRotation.y))+centreRotation.y);
	}

}

