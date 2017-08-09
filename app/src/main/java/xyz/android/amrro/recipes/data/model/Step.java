package xyz.android.amrro.recipes.data.model;


import android.support.annotation.NonNull;

import java.util.Objects;

public final class Step {

    @NonNull
    private final Integer id;
    @NonNull
    private final String shortDescription;
    @NonNull
    private final String description;
    @NonNull
    private final String videoURL;
    @NonNull
    private final String thumbnailURL;

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

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getShortDescription() {
        return shortDescription;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getVideoURL() {
        return videoURL;
    }

    @NonNull
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Step)) return false;
        Step step = (Step) o;
        return Objects.equals(getId(), step.getId()) &&
                Objects.equals(getShortDescription(), step.getShortDescription()) &&
                Objects.equals(getDescription(), step.getDescription()) &&
                Objects.equals(getVideoURL(), step.getVideoURL()) &&
                Objects.equals(getThumbnailURL(), step.getThumbnailURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getShortDescription(), getDescription(), getVideoURL(), getThumbnailURL());
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
