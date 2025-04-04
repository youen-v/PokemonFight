public class AttaqueStat {
    private int statattaque;

    public AttaqueStat() {}

    public AttaqueStat(int statattaque) {
        this.statattaque = statattaque;
    }

    public int getStatattaque() {
        return statattaque;
    }

    public void setStatattaque(int statattaque) {
        this.statattaque = statattaque;
    }

    public void progressionStatattaque(Pokemon pokemon) {
        int niveauDuPokemon = pokemon.getNiveau().getLevel();
        int statDeBase = pokemon.getAttaque().getStatattaque();
        double progression =  statDeBase + (niveauDuPokemon * 1.4);
        setStatattaque((int) progression);
    }

    public void initStatattaque(Pokemon pokemon) {
        progressionStatattaque(pokemon);
    }
}
