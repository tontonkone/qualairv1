package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reply extends Post {

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post origin;
}
