package obstacles.types;

import pathfinding.thetastar.LocomotionArc;
import pathfinding.thetastar.RayonCourbure;
import permissions.ReadOnly;
import permissions.ReadWrite;
import robot.Speed;
import utils.Vec2;

/**
 * Obstacle formé par le robot lorsqu'il effectue une trajectoire courbe
 * @author pf
 *
 */

public class ObstacleTrajectoireCourbe extends ObstacleRectanglesCollection
{	
	/**
	 * 
	 * @param objectifFinal
	 * @param intersection
	 * @param directionAvant de norme 1000
	 * @param vitesse
	 */
//	public ObstacleTrajectoireCourbe(int objectifFinal, int intersection, Vec2<ReadOnly> directionAvant, RayonCourbure rayon)
	public ObstacleTrajectoireCourbe(LocomotionArc arc)
	{
		// La position de cet obstacle est inutile...
		super(null);
/*		
//		Vec2<ReadWrite> directionApres = new Vec2<ReadWrite>(intersection.getOrientationFinale(objectifFinal));

		int rayonCourbure = arc.getRayonCourbure().rayon;

		double angleDepart = arc.getOrientationAuHook();
		double angleRotation = (directionApres.getArgument() - angleDepart) % (2*Math.PI);

		if(angleRotation < -Math.PI)
			angleRotation += 2*Math.PI;
		else if(angleRotation > Math.PI)
			angleRotation -= 2*Math.PI;

		int distanceAnticipation = (int)(rayonCourbure * Math.tan(Math.abs(angleRotation/2)));
		
		Vec2<ReadWrite> pointDepart = position.minusNewVector(directionAvant.scalarNewVector(distanceAnticipation/1000.));
		Vec2<ReadWrite> orthogonalDirectionAvant = directionAvant.rotateNewVector(Math.PI/2);

		// Afin de placer le centre du cercle entre les deux directions
		if(orthogonalDirectionAvant.dot(directionApres) < 0)
			Vec2.scalar(orthogonalDirectionAvant, -1);

		Vec2<ReadWrite> centreCercle = pointDepart.plusNewVector(orthogonalDirectionAvant.scalarNewVector(rayonCourbure/1000.));

//		double angleEntreOmbre = Math.atan2(longueurRobot/2, rayonCourbure+largeurRobot/2);
//		nb_rectangles = (int)(Math.abs(angleRotation/angleEntreOmbre))+1;
		nb_rectangles = (int) Math.abs(nbOmbres * angleRotation/(2 * Math.PI));
		ombresRobot = (ObstacleRectangular[]) new ObstacleRectangular[nb_rectangles];
		for(int i = 0; i < nb_rectangles-1; i++)
			ombresRobot[i] = new ObstacleRectangular(pointDepart.rotateNewVector(i*angleRotation/(nb_rectangles-1), centreCercle).getReadOnly(), longueurRobot, largeurRobot, angleDepart+i*angleRotation/(nb_rectangles-1));
		ombresRobot[nb_rectangles-1] = new ObstacleRectangular(pointDepart.rotateNewVector(angleRotation, centreCercle).getReadOnly(), longueurRobot, largeurRobot, angleDepart+angleRotation);

//		log.debug("Erreur! diff = "+(2*distanceAnticipation - rayonCourbure * Math.abs(angleRotation)), this);
		
//		segment = new SegmentTrajectoireCourbe(objectifFinal, (int)(2*distanceAnticipation - rayonCourbure * Math.abs(angleRotation)), distanceAnticipation, pointDepart.getReadOnly(), directionAvant.getReadOnly(), vitesse);*/
	}

	/**
	 * Renvoie le segment associé
	 * @return
	 */
/*	public SegmentTrajectoireCourbe getSegment()
	{
		return segment;
	}
*/
}