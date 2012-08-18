package dsq.sedition.options;

import java.io.Serializable;

public class Options implements Serializable {
    public static final String KEY = "OPTIONS_KEY";
    public final Difficulty difficulty;

    public Options(final Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
