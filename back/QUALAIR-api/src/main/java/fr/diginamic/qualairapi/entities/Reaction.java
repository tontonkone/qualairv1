package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reaction extends BaseEntity{

    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    private Integer count;
}
