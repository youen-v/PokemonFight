import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Niveau {
    private Integer level;
    private Integer experience;

    public Niveau() {}

    @JsonCreator
    public Niveau(@JsonProperty("lvl") Integer level, @JsonProperty("exp") Integer experience) {
        this.level = level;
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
