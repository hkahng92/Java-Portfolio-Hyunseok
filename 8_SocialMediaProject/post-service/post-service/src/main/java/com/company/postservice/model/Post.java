package com.company.postservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class Post {

    private int postId;
    @NotNull(message = "Invalid date. Please give a date in valid format: yyyy-mm-dd")
    private LocalDate postDate;
    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String posterName;
    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String post;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post1 = (Post) o;
        return postId == post1.postId &&
                Objects.equals(postDate, post1.postDate) &&
                Objects.equals(posterName, post1.posterName) &&
                Objects.equals(post, post1.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postDate, posterName, post);
    }
}
