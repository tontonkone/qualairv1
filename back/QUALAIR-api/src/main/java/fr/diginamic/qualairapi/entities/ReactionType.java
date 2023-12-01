package fr.diginamic.qualairapi.entities;

import lombok.Getter;

@Getter
public enum ReactionType {
    LIKE (1),
    DISLIKE(-1);
    private int direction;

    ReactionType(int nb){
        this.direction = nb;
    }

}
