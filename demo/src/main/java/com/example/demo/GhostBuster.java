package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GhostBuster {

	private static final Logger LOG = LoggerFactory.getLogger(GhostBuster.class);

	private static final int INITIAL_DMG = 20;
	private static final int HAUTEUR_TOUR_EIFFEL = 330;
	
	private static Random battleSimulator = new Random();

	private static Integer damage = INITIAL_DMG;

	public static void main(String[] args) {
		SpringApplication.run(GhostBuster.class, args);
		
		Ghost casper = new Ghost("Casper3000", "White", 400, false, HuntingStatus.FREE, 1, 20);
		Ghost gozer = new Ghost("Gozer", "Yellow", 400, true, HuntingStatus.FREE, 5, 25);
		Ghost kawai = new Ghost("Kawai", "Green", 10, false, HuntingStatus.FREE, 5, 25);
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
			
			checkDangerous(ghost);
			
			switch (ghost.getColor()) {
			case "Grey": {
				battle(ghost, 75);
			};
			break;
			default: {
				battle(ghost, 100);
			}
			
			}
			
		} catch (CustomException e) {
			LOG.trace("Combat refusé contre {}", ghost.getSpecimen(), e );
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
	
	private static void checkDangerous(Ghost ghost) {
		approcheFantome(ghost);
	}

	private static void approcheFantome(Ghost ghost) {
		observationFantome(ghost);
		
	}

	private static void observationFantome(Ghost ghost) {
		 verificationCritereDangerosite(evaluationAgressivite(ghost),evaluationTailleFantome(ghost),evaluationNatureFantome(ghost));
		
	}

	private static void verificationCritereDangerosite(boolean agressivite, int tailleFantome,
			boolean natureFantome) {
		if(!natureFantome || (!agressivite && tailleFantome < verificationTailleLimiteDangerosite())) {
			throw new CustomException("Stop");
		}
		
	}

	private static int verificationTailleLimiteDangerosite() {
		return HAUTEUR_TOUR_EIFFEL;
	}

	private static boolean evaluationNatureFantome(Object ghost) {
		return ghost instanceof Ghost;
		
	}

	private static int evaluationTailleFantome(Ghost ghost) {
		return ghost.getSize();
	}
	private static boolean evaluationAgressivite(Ghost ghost) {
		return ghost.isAgressif();
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