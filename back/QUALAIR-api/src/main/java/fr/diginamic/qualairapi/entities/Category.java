package fr.diginamic.qualairapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
public class Category extends BaseEntity{
    private String title;
    @OneToMany(mappedBy = "category")
    private List<Thread> threads;


}
