import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class TypePokemon {
    private String type;
    private List<String> faiblesse;
    private List<String> resistance;

    public TypePokemon(){}

    @JsonCreator
    public TypePokemon(
            @JsonProperty("type") String type,
            @JsonProperty("faiblesse") List<String> faiblesse,
            @JsonProperty("resistance") List<String> resistance) {
        this.type = type;
        this.faiblesse = faiblesse;
        this.resistance = resistance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getFaiblesses() {
        return switch (type) {
            case "Normal" -> List.of("Combat");
            case "Feu" -> List.of("Eau", "Roche", "Sol");
            case "Eau" -> List.of("Électrique", "Plante");
            case "Plante" -> List.of("Feu", "Glace", "Poison", "Vol", "Insecte");
            case "Électrique" -> List.of("Sol");
            case "Glace" -> List.of("Feu", "Combat", "Roche", "Acier");
            case "Combat" -> List.of("Vol", "Psy", "Fée");
            case "Poison" -> List.of("Sol", "Psy");
            case "Sol" -> List.of("Eau", "Plante", "Glace");
            case "Vol" -> List.of("Électrique", "Glace", "Roche");
            case "Psy" -> List.of("Insecte", "Spectre", "Ténèbres");
            case "Insecte" -> List.of("Feu", "Vol", "Roche");
            case "Roche" -> List.of("Eau", "Plante", "Combat", "Sol", "Acier");
            case "Spectre" -> List.of("Spectre", "Ténèbres");
            case "Dragon" -> List.of("Glace", "Dragon", "Fée");
            case "Ténèbres" -> List.of("Combat", "Insecte", "Fée");
            case "Acier" -> List.of("Feu", "Combat", "Sol");
            case "Fée" -> List.of("Poison", "Acier");
            default -> Collections.emptyList();
        };
    }


    public void setFaiblesse(List<String> faiblesse) {
        this.faiblesse = faiblesse;
    }

    public List<String> getResistances() {
        return switch (type) {
            case "Normal" -> Collections.emptyList();
            case "Feu" -> List.of("Feu", "Plante", "Glace", "Insecte", "Acier", "Fée");
            case "Eau" -> List.of("Feu", "Eau", "Glace", "Acier");
            case "Plante" -> List.of("Eau", "Plante", "Sol", "Électrique");
            case "Électrique" -> List.of("Électrique", "Vol", "Acier");
            case "Glace" -> List.of("Glace");
            case "Combat" -> List.of("Roche", "Insecte", "Ténèbres");
            case "Poison" -> List.of("Plante", "Combat", "Poison", "Insecte", "Fée");
            case "Sol" -> List.of("Poison", "Roche");
            case "Vol" -> List.of("Plante", "Combat", "Insecte");
            case "Psy" -> List.of("Combat", "Psy");
            case "Insecte" -> List.of("Plante", "Combat", "Sol");
            case "Roche" -> List.of("Normal", "Feu", "Poison", "Vol");
            case "Spectre" -> List.of("Poison", "Insecte");
            case "Dragon" -> List.of("Feu", "Eau", "Plante", "Électrique");
            case "Ténèbres" -> List.of("Spectre", "Psy");
            case "Acier" ->
                    List.of("Normal", "Plante", "Glace", "Vol", "Psy", "Insecte", "Roche", "Dragon", "Acier", "Fée");
            case "Fée" -> List.of("Combat", "Insecte", "Ténèbres");
            default -> Collections.emptyList();
        };
    }

    public void setResistance(List<String> resistance) {
        this.resistance = resistance;
    }

    public Integer calculFaiblesseOuRes(Pokemon pokemonAttaque, int degat, int def) {
        // Recherche de faiblesse dans le type d'attaque et du pokemon
        if (getFaiblesses().contains(pokemonAttaque.getAttaqueSelectionner().getType()) &&
                        getFaiblesses().contains(pokemonAttaque.getTypePokemon().getType())
        ) {
            int retourDegat = (((2 * pokemonAttaque.getNiveau().getLevel() / 5 + 2) * degat ) - def + 2);
            return Double.valueOf((retourDegat * 1.5)).intValue();
        }else if (getFaiblesses().contains(pokemonAttaque.getTypePokemon().getType())){
            return (((2 * pokemonAttaque.getNiveau().getLevel() / 5 + 2) * degat ) - def + 2);
        } else {
            // Si pas de faiblesse recherche de résistance
            if (getResistances().contains(pokemonAttaque.getAttaqueSelectionner().getType()) &&
            getResistances().contains(pokemonAttaque.getTypePokemon().getType())){
                return degat - (def*2);
            } else if (getResistances().contains(pokemonAttaque.getTypePokemon().getType())) {
                return degat - def;
            }
            // Si aucun des deux retour de dégât classique
            return degat;
        }
    }

}
