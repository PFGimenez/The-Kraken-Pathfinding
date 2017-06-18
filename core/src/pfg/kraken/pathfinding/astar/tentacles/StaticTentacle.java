/*
 * Copyright (C) 2013-2017 Pierre-François Gimenez
 * Distributed under the MIT License.
 */

package pfg.kraken.pathfinding.astar.tentacles;

import pfg.kraken.robot.CinematiqueObs;

/**
 * Arc courbe de longueur fixe
 * 
 * @author pf
 *
 */

public class StaticTentacle extends Tentacle
{
	private static final long serialVersionUID = -5599092863248049576L;
	public CinematiqueObs[] arcselems = new CinematiqueObs[ClothoidesComputer.NB_POINTS];

	public StaticTentacle(int demieLargeurNonDeploye, int demieLongueurArriere, int demieLongueurAvant)
	{
		for(int i = 0; i < ClothoidesComputer.NB_POINTS; i++)
			arcselems[i] = new CinematiqueObs(demieLargeurNonDeploye, demieLongueurArriere, demieLongueurAvant);
	}

	/**
	 * Une copie afin d'éviter la création d'objet
	 * 
	 * @param arcCourbe
	 */
	public void copy(StaticTentacle arcCourbe)
	{
		for(int i = 0; i < arcselems.length; i++)
			arcselems[i].copy(arcCourbe.arcselems[i]);
	}

	@Override
	public int getNbPoints()
	{
		return ClothoidesComputer.NB_POINTS;
	}

	@Override
	public CinematiqueObs getPoint(int indice)
	{
		return arcselems[indice];
	}

	@Override
	public CinematiqueObs getLast()
	{
		return arcselems[ClothoidesComputer.NB_POINTS - 1];
	}

	@Override
	protected double getLongueur()
	{
		return ClothoidesComputer.DISTANCE_ARC_COURBE;
	}

}