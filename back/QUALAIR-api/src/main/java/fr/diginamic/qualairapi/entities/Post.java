package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entité représentant un post dans un thread de discussion.
 */
@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post extends BaseEntity {
    @ManyToOne
    private User author;
    @ManyToOne
    private Thread thread;

    private String content;

    @OneToMany(mappedBy = "post")
    private Set<Reaction> reactions = new HashSet<>();

    @OneToMany(mappedBy = "origin")
    private Set<Reply> replies = new HashSet<>();

    /**
     * Constructeur pour initialiser un post avec un auteur et un contenu.
     *
     * @param author  L'auteur du post
     * @param content Le contenu du post
     */
    public Post(User author, String content) {
        this.author = author;
        this.content = content;
    }
}
