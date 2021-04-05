package pl.robert.repos.app.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReposDto {

    @JsonProperty("fullName")
    private final String fullName;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("cloneUrl")
    private final String cloneUrl;

    private final int stars;

    @JsonProperty("createdAt")
    private final String createdAt;

    @JsonCreator
    private ReposDto(@JsonProperty("name") String fullName,
                     @JsonProperty("description") String description,
                     @JsonProperty("clone_url") String cloneUrl,
                     @JsonProperty("stargazers_count") int stars,
                     @JsonProperty("created_at") String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    @JsonProperty("stars")
    public int getStars() {
        return stars;
    }
}
