/*
 * Copyright (C) 2013-2019 Pierre-François Gimenez
 * Distributed under the MIT License.
 */

package pfg.kraken;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pfg.kraken.obstacles.RobotShape;
import pfg.kraken.struct.Kinematic;
import pfg.kraken.struct.XY;
import pfg.kraken.astar.tentacles.StaticTentacle;
import pfg.kraken.astar.tentacles.clothoid.ClothoTentacle;
import pfg.kraken.astar.tentacles.clothoid.ClothoidComputer;
import static pfg.kraken.astar.tentacles.Tentacle.*;

/**
 * Tests unitaires de la recherche de chemin courbe
 * 
 * @author pf
 *
 */

public class Test_ClothoidesComputer extends JUnit_Test
{
	private ClothoidComputer clotho;

	@Before
	public void setUp() throws Exception
	{
		super.setUpStandard("default");
		clotho = injector.getService(ClothoidComputer.class);
	}

	@Test
	public void test_clotho() throws Exception
	{
		int nbArc = 16;
		StaticTentacle arc[] = new StaticTentacle[nbArc];
		for(int i = 0; i < nbArc; i++)
			arc[i] = new StaticTentacle(injector.getService(RobotShape.class));

		Kinematic c = new Kinematic(0, 1000, Math.PI / 2, false, 0, false);
		clotho.getTrajectoire(c, ClothoTentacle.SAME_CURVATURE, arc[0], 0);
		clotho.getTrajectoire(arc[0], ClothoTentacle.LEFT_2, arc[1], 0);
		clotho.getTrajectoire(arc[1], ClothoTentacle.SAME_CURVATURE, arc[2], 0);
		clotho.getTrajectoire(arc[2], ClothoTentacle.LEFT_1, arc[3], 0);
		clotho.getTrajectoire(arc[3], ClothoTentacle.SAME_CURVATURE, arc[4], 0);
		clotho.getTrajectoire(arc[4], ClothoTentacle.SAME_CURVATURE, arc[5], 0);
		clotho.getTrajectoire(arc[5], ClothoTentacle.SAME_CURVATURE, arc[6], 0);
		clotho.getTrajectoire(arc[6], ClothoTentacle.LEFT_1, arc[7], 0);
		clotho.getTrajectoire(arc[7], ClothoTentacle.LEFT_2, arc[8], 0);
		clotho.getTrajectoire(arc[8], ClothoTentacle.LEFT_2, arc[9], 0);
		clotho.getTrajectoire(arc[9], ClothoTentacle.RIGHT_1, arc[10], 0);
		clotho.getTrajectoire(arc[10], ClothoTentacle.RIGHT_1, arc[11], 0);
		clotho.getTrajectoire(arc[11], ClothoTentacle.RIGHT_1, arc[12], 0);
		clotho.getTrajectoire(arc[12], ClothoTentacle.RIGHT_1, arc[13], 0);
		clotho.getTrajectoire(arc[13], ClothoTentacle.RIGHT_1, arc[14], 0);
		clotho.getTrajectoire(arc[14], ClothoTentacle.LEFT_2, arc[15], 0);

		for(int a = 0; a < nbArc; a++)
		{
			for(int i = 0; i < NB_POINTS; i++)
			{
				if(i > 0)
				{
					double distance = arc[a].arcselems[i-1].cinem.getPosition().distance(arc[a].arcselems[i].cinem.getPosition());
					assert distance <= PRECISION_TRACE_MM && distance >= PRECISION_TRACE_MM*0.97 : distance;
				}
				else if(a > 0)
				{
					double distance = arc[a-1].arcselems[NB_POINTS - 1].cinem.getPosition().distance(arc[a].arcselems[0].cinem.getPosition());
					assert distance <= PRECISION_TRACE_MM && distance >= PRECISION_TRACE_MM*0.97 : distance;
				}
			}
			if(a == 0)
			{
				Assert.assertEquals(arc[0].arcselems[NB_POINTS - 1].cinem.getX(), 0, 0.1);
				Assert.assertEquals(arc[0].arcselems[NB_POINTS - 1].cinem.getY(), 1000 + (int) DISTANCE_ARC_COURBE, 0.1);
			}
		}

		Assert.assertEquals(0, arc[nbArc - 1].arcselems[arc[nbArc - 1].arcselems.length - 1].cinem.getPosition().distance(new XY(-166.41,1335.34)), 0.1);
	}
}
