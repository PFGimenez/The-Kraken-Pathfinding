/*
 * Copyright (C) 2013-2019 Pierre-François Gimenez
 * Distributed under the MIT License.
 */

package pfg.kraken.astar.tentacles;

import java.awt.Color;

import pfg.kraken.astar.DirectionStrategy;
import pfg.kraken.struct.Kinematic;

/**
 * Les différentes vitesses de courbure qu'on peut suivre
 * 
 * @author pf
 *
 */

public interface TentacleType
{
	public TentacleComputer getComputer();
	public boolean isAcceptable(Kinematic c, DirectionStrategy directionstrategyactuelle, double courbureMax);
	public int getNbArrets(boolean firstMove);
	public double getComputationalCost();
	public Color getColor();
}
