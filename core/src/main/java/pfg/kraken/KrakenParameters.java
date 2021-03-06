package pfg.kraken;

import pfg.kraken.astar.engine.PhysicsEngine;
import pfg.kraken.display.Display;
import pfg.kraken.obstacles.Obstacle;
import pfg.kraken.obstacles.RectangularObstacle;
import pfg.kraken.obstacles.RobotShape;
import pfg.kraken.obstacles.container.DynamicObstacles;
import pfg.kraken.struct.XY;

public class KrakenParameters
{
	public RobotShape vehicleTemplate;
	public Display display;
	public PhysicsEngine engine;
	public Iterable<Obstacle> fixedObstacles;
	public DynamicObstacles dynObs;
	public XY bottomLeftCorner;
	public XY topRightCorner;
	public String configfile;
	public String[] configprofile;
	
	public KrakenParameters(RectangularObstacle vehicleTemplate, XY bottomLeftCorner, XY topRightCorner, String configfile, String...profiles)
	{
		this.vehicleTemplate = new RobotShape(vehicleTemplate);
		this.bottomLeftCorner = bottomLeftCorner;
		this.topRightCorner = topRightCorner;
		this.configfile = configfile;
		this.configprofile = profiles;
	}
	
	public void setDisplay(Display display)
	{
		this.display = display;
	}
	
	public void setPhysicsEngine(PhysicsEngine engine)
	{
		this.engine = engine;
	}
	
	public void setFixedObstacles(Iterable<Obstacle> fixedObstacles)
	{
		this.fixedObstacles = fixedObstacles;
	}
	
	public void setDynamicObstacle(DynamicObstacles dynObs)
	{
		this.dynObs = dynObs;
	}
}
