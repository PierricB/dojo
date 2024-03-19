package com.ghostbusters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class GhostBuster {

	@Value("classpath:ascii-art.txt")
	private static Resource asciiArtFile;

	private static final Logger LOG = LoggerFactory.getLogger(GhostBuster.class);

	private static Random battleSimulator = new Random();

	private static final int INITIAL_DMG = 20;
	private static final int HEIGHT_SPOOK_CENTRAL = 300;
	private static Integer damage = INITIAL_DMG;
	private static Integer nbAvoidedBattle = 0;

	public static void main(String[] args) {

		SpringApplication.run(GhostBuster.class, args);

		List<Ghost> huntingList = new ArrayList<>();

		huntingList.add(new Ghost("Casper3000", "White", 400, false, HuntingStatus.FREE, 20,
				"Vous le verrez sous forme de brume tourbillonnante, Esprit turbulent provoquant des désordres."));

		huntingList.add(new Ghost("Gozer", "Yellow", 400, true, HuntingStatus.FREE, 25, "Un flemmard."));

		huntingList.add(new Ghost("Furtive", "Grey", 350, true, HuntingStatus.FREE, 25,
				"A les mêmes pouvoirs que barbe noire et peut même vous faire vivre l'illusion."));

		huntingList.add(new Ghost("Kawai", "Green", 10, true, HuntingStatus.FREE, 25,
				"Esprit protecteur des voyageurs égarés. Tout comme Marcelino pan y vino, il peut parler avec les animaux."));

		huntingList.add(new Ghost("Ranger", "Orange", 420, false, HuntingStatus.FREE, 25, "Très puissant."));

		huntingList.add(new Ghost("Slimer", "Green", 150, false, HuntingStatus.FREE, 30,
				"Esprit glouton qui se délecte des déchets alimentaires."));

		huntingList.add(new Ghost("Poltergeist", "Grey", 300, false, HuntingStatus.FREE, 15,
				"Esprit frappeur qui aime jouer des tours à ceux qui vivent dans la maison."));

		huntingList.add(new Ghost("Banshee", "Blue", 200, false, HuntingStatus.FREE, 20,
				"Esprit hurlant qui annonce la mort imminente."));

		huntingList.add(new Ghost("Spectre", "Purple", 500, false, HuntingStatus.FREE, 25,
				"Esprit mystérieux qui hante les ruines anciennes."));

		huntingList.add(new Ghost("Revenant", "Black", 180, true, HuntingStatus.FREE, 20,
				"Esprit vengeur revenu d'entre les morts pour poursuivre ses ennemis."));

		huntingList.add(new Ghost("Wraith", "Grey", 280, true, HuntingStatus.FREE, 30,
				"Esprit sinistre qui se déplace silencieusement dans les ténèbres."));

		huntingList.add(new Ghost("Phantom", "White", 350, false, HuntingStatus.FREE, 25,
				"Esprit éthéré qui apparaît et disparaît soudainement."));

		huntingList.add(new Ghost("Shade", "Blue", 500, true, HuntingStatus.FREE, 40,
				"Esprit sombre qui obscurcit l'esprit de ceux qui l'approchent."));

		huntingList.add(new Ghost("Shadow", "Black", 250, true, HuntingStatus.FREE, 20,
				"Se déplace silencieusement dans les coins obscurs."));

		huntingList.add(new Ghost("Ember", "Red", 180, false, HuntingStatus.FREE, 15,
				"Esprit flamboyant qui apparaît sous forme de lueurs incandescentes."));

		huntingList.add(new Ghost("Frost", "White", 550, true, HuntingStatus.FREE, 25,
				"Esprit glacial qui fait chuter les températures autour de lui."));

		huntingList.add(new Ghost("Whisper", "Purple", 200, false, HuntingStatus.FREE, 45,
				"Esprit discret qui murmure des secrets."));

		huntingList.add(new Ghost("Azure", "Blue", 280, true, HuntingStatus.FREE, 30,
				"Esprit azuré qui hante les profondeurs des océans."));

		Collections.shuffle(huntingList);

		int numberOfHoursGhostSleep = battleSimulator.nextInt(3);
		if (numberOfHoursGhostSleep == 2) {
			huntingList.add(new Ghost("Collapsus", "White", 800, false, HuntingStatus.FREE, 50,
					"Peux faire évanouir les ghost busters."));
		}
		
		try {
			for (Ghost ghost : huntingList) {
				hunt(ghost);
				Thread.sleep(battleSimulator.nextInt(2000) + 1000L);
				if ("Collapsus".equals(ghost.getSpecimen())) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (InterruptedException e) {
			LOG.error("Chasse interrompue", e);

		}
		LOG.info("Nb batailles : {}", huntingList.size() - nbAvoidedBattle);
		LOG.info("Nb captures : {}", huntingList.stream()
				.filter(ghost -> HuntingStatus.CAPTURED.equals(ghost.getStatus()))
				.collect(Collectors.toList())
				.size());
		LOG.info("Niv dmg : {}", damage);

	}

	public static void hunt(Ghost ghost) {
		try {
			checkDangerous(ghost);
			switch (ghost.getColor()) {
			case "Black": {
				battle(ghost, 5);
				break;
			}
			case "Grey": {
				battle(ghost, 4);
				break;
			}
			case "Red": {
				battle(ghost, 3);
				break;
			}
			case "Blue": {
				battle(ghost, 2);
				break;
			}
			default: {
				battle(ghost, 1);
			}
			}
		} catch (CustomException e) {
			LOG.info("Combat refusé contre {}", ghost.getSpecimen());
			nbAvoidedBattle += nbAvoidedBattle;
		}
	}

	public static void battle(Ghost ghost, Integer bonus) {
		ghost.setStatus(HuntingStatus.IN_BATTLE);
		LOG.info("Bataille en cours contre {}", ghost.getSpecimen());
		if (damage + battleSimulator.nextInt(10) > ghost.getHealth()) {
			ghost.setStatus(HuntingStatus.CAPTURED);
			damage += bonus;
			//LOG.info("Le code top secret de l'unité de confinement des fantômes est : 21032024NEWLAND");
			LOG.info("Bataille remportée contre {}.", ghost.getSpecimen());
		} else {
			ghost.setStatus(HuntingStatus.FREE);
			damage -= bonus;
			LOG.info("Bataille perdue contre {}.", ghost.getSpecimen());
		}
		if (ghost.getHealth() > 20) {
			//LOG.info("On vous raconte l'histoire du fantôme, on va vous parler de ses centres d'intérêt ou de ses pouvoirs surnaturels : {}", ghost.getHistory());
		}
	}

	private static void checkDangerous(Ghost ghost) {
		approacheGhost(ghost);
	}

	private static void approacheGhost(Ghost ghost) {
		observeGhost(ghost);
	}

	private static void observeGhost(Ghost ghost) {
		checkSomeCriteria(evaluateSustainability(ghost), evaluateMaxSize(ghost), evaluationNature(ghost));
	}

	private static void checkSomeCriteria(boolean ephemeral, int size, boolean nature) {
		if (!nature || (ephemeral && size < verificationTailleLimiteDangerosite())) {
			throw new CustomException("Les critères de combat ne sont pas respectés");
		}
	}

	private static boolean evaluateSustainability(Ghost ghost) {
		return ghost.isEphemeral();
	}

	private static int evaluateMaxSize(Ghost ghost) {
		return ghost.getMaxSize();
	}

	private static boolean evaluationNature(Object ghost) {
		return ghost instanceof Ghost;
	}

	private static int verificationTailleLimiteDangerosite() {
		return HEIGHT_SPOOK_CENTRAL;
	}

}