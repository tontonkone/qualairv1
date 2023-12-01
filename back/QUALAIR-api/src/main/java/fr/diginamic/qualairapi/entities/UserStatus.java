package fr.diginamic.qualairapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author km84
 * gestions des status de l'user
 *
 */
@Getter

public enum UserStatus {
    ACTIVE ("Active"),
    BLOCKED("Bloqué"),
    EXCLUDED("Exclu");
    private String label;

    UserStatus(String name){
        this.label = name;
    }

}
