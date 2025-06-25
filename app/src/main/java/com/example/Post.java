// Post.java
package com.example; // Ensure this matches your package name

/**
 * Data model class to represent a single blog post from the JSONPlaceholder API.
 * The JSON structure for a post is typically:
 * {
 * "userId": 1,
 * "id": 1,
 * "title": "...",
 * "body": "..."
 * }
 */
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    // Constructor to initialize a Post object
    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    // Getter methods for each field

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    // Setter methods (optional, but good practice if you plan to modify objects)

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
