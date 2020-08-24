package co.hadwen.aphrodite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Dto for Apis
 */
public abstract class Dto {
    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    public String toJsonString() {
        return GSON.toJson(this, this.getClass());
    }
}
