/*
 * Copyright (C) 2013-2019 Pierre-François Gimenez
 * Distributed under the MIT License.
 */

package pfg.kraken.astar.endcheck;

import pfg.kraken.struct.Kinematic;
import pfg.kraken.struct.XYO;

public class EndWithXYO implements EndOfTrajectoryCheck
{

	@Override
	public boolean isArrived(Kinematic endPoint, Kinematic robotPoint)
	{
		return robotPoint.getPosition().squaredDistance(endPoint.getPosition()) < 5
				&& Math.abs(XYO.angleDifference(robotPoint.orientationReelle, endPoint.orientationReelle)) < 0.05;
	}

}
