/*
Copyright (C) 2013-2017 Pierre-François Gimenez
*/

package kraken.obstacles.types;

import java.util.ArrayList;
import java.util.List;

import kraken.container.Service;
import kraken.utils.Log;

/**
 * Classe qui contient les obstacles fixes
 * @author pf
 *
 */

public class ObstaclesFixes implements Service {

    private List<Obstacle> obstacles = new ArrayList<Obstacle>();
    protected Log log;
    
    private ObstaclesFixes(Log log)
    {
    	this.log = log;
    }

    public boolean addAll(List<Obstacle> o)
    {
    	return obstacles.addAll(o);
    }
    
    public List<Obstacle> getObstacles()
    {
    	return obstacles;
    }

}
