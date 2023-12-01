package fr.diginamic.qualairapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thread")
public class Thread extends BaseEntity{
    private String title;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "thread")
    private Set<Post> posts;
}
