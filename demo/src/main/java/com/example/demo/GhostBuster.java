package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GhostBuster {

	private static final Logger LOG = LoggerFactory.getLogger(GhostBuster.class);

	private static final int INITIAL_DMG = 20;
	
	private static Random battleSimulator = new Random();

	private static Integer damage = INITIAL_DMG;

	public static void main(String[] args) {

		GhostBuster ghostbuster = new GhostBuster();
		
		Ghost casper = new Ghost("Casper3000", "White", 50, true, HuntingStatus.FREE, 1, 20);
		Ghost gozer = new Ghost("Gozer", "Yellow", 400, true, HuntingStatus.FREE, 5, 25);
		Ghost kawai = new Ghost("Kawai", "Green", 400, false, HuntingStatus.FREE, 5, 25);
		Ghost furtive = new Ghost("Furtive", "Grey", 400, true, HuntingStatus.FREE, 5, 25);

		List<Ghost> huntingList = new ArrayList<Ghost>();
		huntingList.add(gozer);
		huntingList.add(kawai);
		huntingList.add(furtive);
		huntingList.add(casper);

		Collections.shuffle(huntingList);
		
		for (Ghost ghost : huntingList) {
			hunt(ghost);
		}
		LOG.info("Dommages finaux {}", damage);

	}

	public static void hunt(Ghost ghost) {
		try {
			
			if (!ghost.isDangerous()) {
				LOG.warn("Warning");
				throw new CustomException("Stop");
				
			}
			
			switch (ghost.getColor()) {
			case "Grey": {
				battle(ghost, 75);
			};
			break;
			default: {
				battle(ghost, 100);
			}
			
			}
			
		} catch (Exception e) {
			LOG.error("Combat refusé contre {}", ghost.getSpecimen(), e );
			e.printStackTrace();
		}
	}
	
	public static void battle(Ghost ghost, Integer visibility) {
		ghost.setStatus(HuntingStatus.IN_BATTLE);
		LOG.info("Bataille en cours contre {}", ghost.getSpecimen() );
		if (damage + battleSimulator.nextInt(0, 10) > ghost.getHealth()) {
			ghost.setStatus(HuntingStatus.CAPTURED);
			damage += 3;
			LOG.info("Bataille remportée contre {}", ghost.getSpecimen() );
		} else {
			ghost.setStatus(HuntingStatus.FREE);
			damage -= 1;
			LOG.info("Bataille perdue contre {}", ghost.getSpecimen() );
		}

	}

	// Scénario : Notre équipe de chasseurs de fantômes part à la chasse ! Ont-ils réussi à venir à bout de toutes leurs cibles ?

	// Questions : En tant qu'analyste de l'équipe de chasseurs, je veux savoir comment s'est passée la chasse.
	// En utilisant des logs, je veux savoir :
	// - combien de combats ont eu lieu ?
	// - pourquoi certains combats ont été évités ?
	// - combien de fantômes ont été capurés ?
	// - quel est le niveau de dommage de l'équipe à la fin de la chasse 

	// Indications : 
	// Utiliser LOG.error(), LOG.info() de la bibliotèque slf4j (import déjà fait dans la classe)

    // TODO 	
	// Ajouter un log inutile pour la MCO, qui spamme / avec info securité ATO
	// Ajouter une queston plus complexe : sleep aléatoire entre chaque combat + modif du formatter pouir afficher ms PBA
	// Config logger en INFO + appel dn trace ou debug dans isDangerous EBR
	// Bonus : ajouter méthode sur taille et gestion munitions / fusil ?
	
	
 
}