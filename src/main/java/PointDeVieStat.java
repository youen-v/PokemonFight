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
        int nbBlocs = (int) ((double) pointDeVie / pointDeVieMax * 20);
        StringBuilder barre = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            if (i < nbBlocs) {
                barre.append("█");
            } else {
                barre.append("░");
            }
        }

        System.out.printf("%-4s [%s] %d / %d\n", "PV", barre, pointDeVie, pointDeVieMax);
    }


    @Override
    public String toString() {
        return "" + pointDeVie;
    }
}
