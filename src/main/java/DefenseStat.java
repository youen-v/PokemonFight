public class DefenseStat {
    private int statdefense;

    public DefenseStat() {}

    public DefenseStat(int statdefense) {
        this.statdefense = statdefense;
    }

    public int getStatdefense() {
        return statdefense;
    }

    public void setStatdefense(int statdefense) {
        this.statdefense = statdefense;
    }

    public void progressionStatdefense(Pokemon pokemon) {
        int niveauDuPokemon = pokemon.getNiveau().getLevel();
        int statDeBase = pokemon.getAttaque().getStatattaque();
        double progression =  statDeBase + (niveauDuPokemon * 1.4);
        setStatdefense((int) progression);
    }

    public void initStatdefense(Pokemon pokemon) {
        progressionStatdefense(pokemon);
    }
}
