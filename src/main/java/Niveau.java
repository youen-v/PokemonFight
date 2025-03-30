import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Niveau {
    private Integer level;
    private Double experience;

    public Niveau() {}

    @JsonCreator
    public Niveau(@JsonProperty("lvl") Integer level, @JsonProperty("exp") Double experience) {
        this.level = level;
        this.experience = experience;
    }

    public Integer getLevel() {
        if (level > 100){
            System.out.println("Level out of 100");
            System.exit(0);
        }
        return level;
    }

    public void setLevel(Integer level) {
        // Revoir le math max faire tomber en erreur le calcul du niveau
        this.level = Math.min(level, 100);
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public void calculNiveau(Double experience, Pokemon gagnant){
        Pokemon pokemonGagnant = gagnant;
        int niveau = pokemonGagnant.getNiveau().getLevel();
        double calculPalierLvl = ((4 * Math.pow((niveau + 1), 3) / 5) + 50);
        double experienceActuel = pokemonGagnant.getNiveau().getExperience();
        if ((experienceActuel + experience) > calculPalierLvl) {
            // revoir le calcul restant renvoi une valeur négative
            double restant = experience - (calculPalierLvl - experienceActuel) ;
            int levelUp = niveau + 1;
            pokemonGagnant.getNiveau().setLevel(levelUp);
            pokemonGagnant.getAttaque().progressionStatattaque(pokemonGagnant);
            System.out.println("Level Up !" + " Niveau " + levelUp);
            calculNiveau(restant, pokemonGagnant);
        } else {
            setExperience(experience);
            System.out.println("Nivaux suivant dans -> " + Math.ceil(calculPalierLvl - experienceActuel) + " xp");
            System.out.println(pokemonGagnant.getNiveau().getExperience());
        }
    }

    public void calculerExperienceGagnee(Pokemon gagnant, Pokemon vaincu) {
        int baseXp = 50;
        int diffNiveau = vaincu.getNiveau().getLevel() - gagnant.getNiveau().getLevel();

        double multiplicateurType = 1.0;
        if (vaincu.getTypePokemon().getFaiblesses().contains(gagnant.getTypePokemon().getType())) {
            multiplicateurType = 0.8;
        } else if (vaincu.getTypePokemon().getResistances().contains(gagnant.getTypePokemon().getType())) {
            multiplicateurType = 1.5;
        }

        double multiplicateurNiveau = 1 + (diffNiveau * 0.1);

        if (multiplicateurNiveau < 0.5) multiplicateurNiveau = 0.5;
        System.out.println(gagnant.getNom()+" à gagné : "+baseXp * multiplicateurType * multiplicateurNiveau + " xp !");
        double expGagner = (baseXp * multiplicateurType * multiplicateurNiveau);
        calculNiveau(expGagner, gagnant);
    }

}
