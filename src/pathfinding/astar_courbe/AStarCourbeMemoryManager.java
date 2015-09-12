package pathfinding.astar_courbe;

import pathfinding.GameState;
import permissions.ReadOnly;
import container.Service;
import exceptions.FinMatchException;
import robot.RobotReal;
import utils.Config;
import utils.Log;
// TODO unifier les MemoryManager

/**
 * Classe qui fournit des objets AStarCourbeNode à AStarCourbe.
 * AStar a besoin de beaucoup de nodes, et l'instanciation d'un objet est long.
 * Du coup on réutilise les mêmes objets sans devoir en créer tout le temps de nouveaux.
 * @author pf
 *
 */

public class AStarCourbeMemoryManager implements Service {

	private static final int nb_instances = 500;

	private final AStarCourbeNode[] nodes = new AStarCourbeNode[nb_instances];
	protected Log log;
	
	// nodes est triés: avant firstAvailable, les gamestate sont indisponibles, après, ils sont disponibles
	private int firstAvailable;
	
	@Override
	public void updateConfig(Config config)
	{
		for(int j = 0; j < nb_instances; j++)
			nodes[j].state.updateConfig(config);
	}

	@Override
	public void useConfig(Config config)
	{}

	public AStarCourbeMemoryManager(Log log, GameState<RobotReal,ReadOnly> realstate)
	{	
		this.log = log;

		firstAvailable = 0;
		// on prépare déjà des gamestates
		log.debug("Instanciation de "+nb_instances+" GameState<RobotChrono>");

		for(int i = 0; i < nb_instances; i++)
		{
			nodes[i] = new AStarCourbeNode();
			nodes[i].state = GameState.cloneGameState(realstate);
		}
		log.debug("Instanciation finie");
	}
	
	/**
	 * Donne un gamestate disponible
	 * @param id_astar
	 * @return
	 * @throws FinMatchException
	 */
	public AStarCourbeNode getNewNode()
	{
		// lève une exception s'il n'y a plus de place
		AStarCourbeNode out;
		out = nodes[firstAvailable];
		firstAvailable++;
		return out;
	}
	
	/**
	 * Signale qu'un gamestate est de nouveau disponible
	 * @param state
	 * @param id_astar
	 * @throws MemoryManagerException
	 */
	public void destroyNode(AStarCourbeNode state)
	{
		
		int indice_state = state.getIndiceMemoryManager();
		/**
		 * S'il est déjà détruit, on lève une exception
		 */
//		if(indice_state >= firstAvailable)
//			throw new MemoryManagerException();

		// On inverse dans le Vector les deux gamestates,
		// de manière à avoir toujours un Vector trié.
		firstAvailable--;
		
		AStarCourbeNode tmp1 = nodes[indice_state];
		AStarCourbeNode tmp2 = nodes[firstAvailable];

		tmp1.setIndiceMemoryManager(firstAvailable);
		tmp2.setIndiceMemoryManager(indice_state);

		nodes[firstAvailable] = tmp1;
		nodes[indice_state] = tmp2;
	}
	
	/**
	 * Signale que tous les gamestates sont disponibles. Très rapide.
	 * @param id_astar
	 */
	public void empty()
	{
		firstAvailable = 0;
	}
	
	/**
	 * Utilisé par les tests
	 */
	public boolean isMemoryManagerEmpty()
	{
		return firstAvailable == 0;
	}
	
}
