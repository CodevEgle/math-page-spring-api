package lt.ca.javau10;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lt.ca.javau10.entities.Assessment;
import lt.ca.javau10.entities.ExampleExercise;
import lt.ca.javau10.entities.Exercise;
import lt.ca.javau10.entities.Question;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.entities.Year;
import lt.ca.javau10.models.ERole;
import lt.ca.javau10.models.Role;
import lt.ca.javau10.repositories.RoleRepository;
import lt.ca.javau10.repositories.YearRepository;

@Component
public class DbSeeder implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(DbSeeder.class);

    private RoleRepository roleRepository;
   
    private YearRepository yearRepository;

    public DbSeeder (RoleRepository roleRepository, YearRepository yearRepository) {
    	this.roleRepository = roleRepository;
    	this.yearRepository = yearRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            logger.info("ROLE_ADMIN added.");
        }
        
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_USER));
            logger.info("ROLE_USER added.");
        }
        
        if (roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
            logger.info("ROLE_MODERATOR added.");
        }
        
        if (yearRepository.count() == 0) {
        	loadYearData();
        }
        	
    }

	private void loadYearData() {
		
		List<ExampleExercise> exampleExercises = Arrays.asList(
				new ExampleExercise(),
				new ExampleExercise()
				);
		List<Theory> theories = Arrays.asList(
				new Theory("Kvadratinės lygties samprata", "Kvadratinė lygtis nuo paprastos lygties skiriasi tuom, kad "
						+ "nežinomasis (dažnai žymimas x) yra pakeltas antruoju laipsniu. Tai reiškia, kad galime gauti "
						+ "du atsakymus - du lygties sprendinius. Kvadratinės lygties pavyzdys: x^2 = 25", exampleExercises),
				new Theory("Kvadratinis trinaris", "Kvadratinės lygtys yra skirstomos į pilnąsias ir nepilnąsias kvadratines lygtis. "
						+ "Pilnoji kvadratinė lygtis atrodo taip: ax^2 + bx + c = 0. Čia a, b, c yra vadinami lygties "
						+ "koeficientais - tai bet kokie skaičiai, priklausantys realiųjų skaičių aibei. "
						+ "Tuo atveju, kai a = 0, lygtis virsta paprastąja lygtimi. Kai b ir/arba c lygūs nuliui, "
						+ "tuomet kvadratinė lygtis vadinama nepilnąją kvadratine lygtimi.", exampleExercises)
				);
		
		List<Question> questions =  Arrays.asList(
				new Question("Sudarykite kvadratinę lygtį, jei $a=2, b=−5, c=3$:", Arrays.asList("$−5x^2+2x−3=0$", 
						"$2x^2+5x−3=0$", "$2x^2−5x+3=0$", "$−5x+x^2+3=0$"), "$2x^2−5x+3=0$", 1),
				new Question("Kiek sprendinių turi lygtis $3x^2−5x−2=0$", Arrays.asList("1", 
						"2", "3", "Neturi sprendinių"), "2", 1)
				);
		List<Exercise> exercises = Arrays.asList(
	            new Exercise(),
	            new Exercise()
	        );
		
		List<Assessment> assessments = Arrays.asList(
		            new Assessment("Atsiskaitymas iš temos KVADRATINĖS LYGTYS", questions, exercises)
		        );
		
		List<Topic> topics = Arrays.asList(
	            new Topic("KVADRATINĖS LYGTYS", "Kvadratinės lygties samprata. Kvadratinis trinaris."
	            		+ "Pilnųjų kvadratinių lygčių sprendimas", theories, assessments),
	            new Topic("Įvadas į trigonometriją", "Sinuso, kosinuso ir tangento apibrėžimai", theories, assessments)
	        );
		List<Year> years = Arrays.asList(
				new Year("9 klasė", "Devintos klasės matematikos kursas", topics),
	            new Year("10 klasė", "Dešimtos klasės matematikos kursas")
	        );

	        yearRepository.saveAll(years);
	}
	
}