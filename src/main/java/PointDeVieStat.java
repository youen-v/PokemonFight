public class PointDeVieStat {
    private int pointDeVie;

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

    public void progressionStatpointDeVie(Pokemon pokemon) {
        int niveauDuPokemon = pokemon.getNiveau().getLevel();
        int statDeBase = pokemon.getAttaque().getStatattaque();
        double progression =  (statDeBase * niveauDuPokemon ) * 1.4;
        setStatpointDeVie((int) progression);
    }

    public void initStatpointDeVie(Pokemon pokemon) {
        progressionStatpointDeVie(pokemon);
    }

    @Override
    public String toString() {
        return "" + pointDeVie;
    }
}
