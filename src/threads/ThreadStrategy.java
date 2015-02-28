package threads;

import java.util.ArrayList;

import astar.AStar;
import astar.arc.Decision;
import astar.arc.PathfindingNodes;
import astar.arc.SegmentTrajectoireCourbe;
import astar.arcmanager.StrategyArcManager;
import container.Service;
import exceptions.FinMatchException;
import exceptions.MemoryManagerException;
import exceptions.PathfindingException;
import robot.RobotChrono;
import robot.RobotReal;
import scripts.ScriptAnticipableNames;
import strategie.GameState;
import utils.Config;
import utils.Log;
import utils.Sleep;

/**
 * Effectue les calculs de stratégie
 * @author pf
 *
 */

public class ThreadStrategy extends AbstractThread implements Service
{

	private Log log;
	private AStar<StrategyArcManager, Decision> strategie;
	private GameState<RobotReal> realstate;
	private GameState<RobotChrono> chronostate;
	
	private Decision decision = new Decision(new ArrayList<SegmentTrajectoireCourbe>(), ScriptAnticipableNames.SORTIE_ZONE_DEPART, PathfindingNodes.POINT_DEPART);
	private Decision decisionSecours = null;
	private Decision decisionNormale = null;
	private Decision needNewBestAfterThis = null;
	
	public ThreadStrategy(Log log, Config config, AStar<StrategyArcManager, Decision> strategie, GameState<RobotReal> realstate)
	{
		this.log = log;
		this.strategie = strategie;
		this.realstate = realstate;
		try {
			chronostate = realstate.cloneGameState();
		} catch (FinMatchException e) {
			// Impossible
			e.printStackTrace();
		}
		
		Thread.currentThread().setPriority(4); // TODO priorité thread
		updateConfig();
	}
	
	@Override
	public void run()
	{
		log.debug("Lancement du thread de stratégie", this);
		while(!Config.matchDemarre)
		{
			if(stopThreads)
			{
				log.debug("Stoppage du thread de stratégie", this);
				return;
			}
			Sleep.sleep(50);
		}
		
		while(!finMatch && !stopThreads)
		{
			try {
				realstate.copy(chronostate);
				if(needNewBestAfterThis != null)
				{
					ArrayList<Decision> decisions = strategie.computeStrategyAfter(chronostate, needNewBestAfterThis, 10000);
					printCurrentStrategy(decisions);
					decision = decisions.get(0);
					needNewBestAfterThis = null; // en cas d'erreur, ce n'est pas mis à null
				}
				decisionSecours = strategie.computeStrategyEmergency(chronostate, 10000).get(0);
				decisionNormale = strategie.computeStrategy(chronostate, 10000).get(0);
			} catch (FinMatchException e) {
				break;
			} catch (PathfindingException e) {
				log.critical("Départ dans un obstacle de proximité!", this);
				e.printStackTrace();
			} catch (MemoryManagerException e) {
				// Imposible
				e.printStackTrace();
			}
		}
	}

	public void computeBestDecisionAfter(Decision d)
	{
		needNewBestAfterThis = d;
	}
	
	public Decision getBestDecision()
	{
		do {
			Sleep.sleep(5);
		} while(decision == null);
		return decision;
	}

	public Decision getEmergencyDecision()
	{
		do {
			Sleep.sleep(5);
		} while(decisionSecours == null);
		log.warning("Stratégie de secours demandée! "+decisionSecours, this);
		return decisionSecours;
	}

	public Decision getNormalDecision()
	{
		do {
			Sleep.sleep(5);
		} while(decisionNormale  == null);
		log.warning("Stratégie demandée! "+decisionNormale, this);
		return decisionNormale;
	}

	private void printCurrentStrategy(ArrayList<Decision> decisions)
	{
		String s = new String();
		for(Decision d: decisions)
			s += d.toString()+" ";
		log.debug(s, this);
	}
	
	@Override
	public void updateConfig()
	{
		log.updateConfig();
		strategie.updateConfig();
		realstate.updateConfig();
	}
}
