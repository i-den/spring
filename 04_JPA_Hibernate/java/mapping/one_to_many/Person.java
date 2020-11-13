package org.example.classes.mapping.one_to_many;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "guide_id")
    private Guide guide;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        if (this.guide != null) {
            this.guide.getPeople().remove(this);
        }
        if (guide != null) {
            guide.getPeople().add(this);
        }
        this.guide = guide;
    }
}
