package xyz.android.amrro.recipes.data.model;


import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class Step {

    public final Integer id;
    public final String shortDescription;
    public final String description;
    public final String videoURL;
    public final String thumbnailURL;

    public Step(Integer id, String shortDescription, String description, String videoURL, String thumbnailURL) {
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
