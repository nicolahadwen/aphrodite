package co.hadwen.hera;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Dto for Apis
 */
public abstract class Dto {
    protected static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    public String toJsonString() {
        return GSON.toJson(this, this.getClass());
    }
}
