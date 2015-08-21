package threads;

import container.Service;
import table.GridSpace;
import table.GridSpaceWayPoints;
import table.ObstaclesMobilesIterator;
import utils.Config;
import utils.Log;

/**
 * S'occupe de la mise à jour du cache. Surveille obstaclemanager
 * @author pf
 *
 */

public class ThreadGridSpace extends Thread implements Service {

	protected Log log;
	private ObstaclesMobilesIterator obstaclemanager;
	private GridSpace gridspace;
	
	public ThreadGridSpace(Log log, ObstaclesMobilesIterator obstaclemanager, GridSpace gridspace)
	{
		this.log = log;
		this.obstaclemanager = obstaclemanager;
		this.gridspace = gridspace;
	}

	@Override
	public void run()
	{
		while(true)
		{
			synchronized(obstaclemanager)
			{
				try {
					obstaclemanager.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			log.debug("Réveil de ThreadGridSpace");	
			
//			gridspace.reinitConnections();
		}
//		log.debug("Fermeture de ThreadGridSpace");

	}

	@Override
	public void updateConfig(Config config)
	{}

	@Override
	public void useConfig(Config config)
	{}

}
