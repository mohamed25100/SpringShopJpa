package fr.fms.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private Collection<Article> articles; //une categorie est liée à plusieurs articles

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    //Accesseurs
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}