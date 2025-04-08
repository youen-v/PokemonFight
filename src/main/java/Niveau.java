import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import consoleColors.ConsoleColors;

public class Niveau {
    private Integer level;
    private Double experience;
    private Double palier;

    public Niveau() {}

    @JsonCreator
    public Niveau(@JsonProperty("lvl") Integer level, @JsonProperty("exp") Double experience) {
        this.level = level;
        this.experience = experience;
        this.palier = ((4 * Math.pow((this.level + 1), 3) / 5) + 50);
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

    public Double getPalier() {
        return palier;
    }

    public void calculNiveau(Double experience, Pokemon gagnant){
        Niveau niveau = gagnant.getNiveau();
        double experienceActuel = gagnant.getNiveau().getExperience();
        if ((experienceActuel + experience) > niveau.getPalier()) {
            double restant = experience - (niveau.getPalier() - experienceActuel) ;
            int levelUp = niveau.getLevel() + 1;
            gagnant.getNiveau().setLevel(levelUp);
            gagnant.getAttaque().progressionStatattaque(gagnant);
            System.out.println("Level Up !" + " Niveau " + levelUp);
            calculNiveau(restant, gagnant);
        } else {
            setExperience(experience);
            afficherBarre(gagnant.getNiveau());
            System.out.println(gagnant.getNiveau().getExperience());
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

    public void afficherBarre(Niveau niveau) {
        int tailleBarre = 50;
        double palier = niveau.getPalier();
        int nbBlocs = (int) ((double) experience / palier * tailleBarre);
        StringBuilder barre = new StringBuilder();
        String colorNivPv = ConsoleColors.CYAN;

        for (int i = 0; i < tailleBarre; i++) {
            if (i < nbBlocs) {
                barre.append("█");
            } else {
                barre.append("░");
            }
        }
        System.out.printf("%-4s "+ colorNivPv +"[%s]"+ ConsoleColors.RESET +" %.2f / %.2f\n", "EXP", barre, experience, palier);
    }

}
