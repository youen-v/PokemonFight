import consoleColors.ConsoleColors;

public class PointDeVieStat {
    private int pointDeVie;
    private int pointDeVieMax;

    public PointDeVieStat() {}

    public PointDeVieStat(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getStatpointDeVie() {
        return pointDeVie;
    }

    public void setStatpointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public void setPointDeVieMax() {
        this.pointDeVieMax = pointDeVie;
    }

    public void progressionStatpointDeVie(Pokemon pokemon) {
        int niveauDuPokemon = pokemon.getNiveau().getLevel();
        int statDeBase = pokemon.getAttaque().getStatattaque();
        double progression =  (statDeBase * niveauDuPokemon ) * 1.4;
        setStatpointDeVie((int) progression);
    }

    public void initStatpointDeVie(Pokemon pokemon) {
        progressionStatpointDeVie(pokemon);
        setPointDeVieMax();
    }

    public void afficherBarre() {
        int tailleBarre = 20;
        int nbBlocs = (int) ((double) pointDeVie / pointDeVieMax * tailleBarre);
        StringBuilder barre = new StringBuilder();
        String colorNivPv = "";

        for (int i = 0; i < tailleBarre; i++) {
            if (i < nbBlocs) {
                barre.append("█");
            } else {
                barre.append("░");
            }
        }
        if (nbBlocs >= (tailleBarre*0.6)) {
            colorNivPv = ConsoleColors.GREEN;
        } else if (nbBlocs < (tailleBarre*0.6) && nbBlocs >= (tailleBarre*0.25)) {
            colorNivPv = ConsoleColors.YELLOW;
        } else if (nbBlocs < (tailleBarre*0.25)) {
            colorNivPv = ConsoleColors.RED;
        }
        System.out.printf("%-4s "+ colorNivPv +"[%s]"+ ConsoleColors.RESET +" %d / %d\n", "PV", barre, pointDeVie, pointDeVieMax);
    }


    @Override
    public String toString() {
        return "" + pointDeVie;
    }
}
