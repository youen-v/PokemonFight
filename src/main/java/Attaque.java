import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Attaque {
    private final String nom;
    private final int degat;
    private final String type;
    private int utilisation;

    @JsonCreator
    public Attaque(@JsonProperty("nom") String nom, @JsonProperty("degat") int degat, @JsonProperty("type") String type, @JsonProperty("utilisation") int utilisation) {
        this.nom = nom;
        this.degat = degat;
        this.type = type;
        this.utilisation = utilisation;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public int getDegat() {
        return degat;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public int setUtilisation(int utilisation) {
        return this.utilisation = Math.max(utilisation, 0);
    }

    public int utilisationAttaque(int utilisation) {
        return setUtilisation((utilisation - 1));
    }

    @Override
    public String toString() {
        return nom;
    }
}
