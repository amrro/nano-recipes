package xyz.android.amrro.recipes.data.model;


import android.support.annotation.NonNull;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public final class Step {

    @NonNull
    public final Integer id;
    @NonNull
    public final String shortDescription;
    @NonNull
    public final String description;
    @NonNull
    public final String videoURL;
    @NonNull
    public final String thumbnailURL;

    public Step(@NonNull Integer id,
                @NonNull String shortDescription,
                @NonNull String description,
                @NonNull String videoURL,
                @NonNull String thumbnailURL) {
        Objects.requireNonNull(id, "id cannot be null.");
        Objects.requireNonNull(shortDescription, "shortDescription cannot be null.");
        Objects.requireNonNull(description, "description cannot be null.");

        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Step)) return false;
        Step step = (Step) o;
        return Objects.equals(id, step.id) &&
                Objects.equals(shortDescription, step.shortDescription) &&
                Objects.equals(description, step.description) &&
                Objects.equals(videoURL, step.videoURL) &&
                Objects.equals(thumbnailURL, step.thumbnailURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortDescription, description, videoURL, thumbnailURL);
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                '}';
    }
}
