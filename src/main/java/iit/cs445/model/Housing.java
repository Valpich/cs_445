package iit.cs445.model;


import javax.persistence.*;

@Entity
@Table(name = "housing")
public class Housing extends Accessory<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Housing{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
