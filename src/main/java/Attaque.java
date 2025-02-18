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

    public void setUtilisation(int utilisation) {
        this.utilisation = utilisation;
    }

    @Override
    public String toString() {
        return nom;
    }
}
