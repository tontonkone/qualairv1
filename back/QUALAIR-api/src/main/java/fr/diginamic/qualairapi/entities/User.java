package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

/**
 * @author Kone mamoudou
 *
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity{

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToMany(mappedBy = "author")
    private  Set<Post> posts = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Bookmark> bookmarks = new HashSet<>();

    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
    }

    public void removeBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }
}
