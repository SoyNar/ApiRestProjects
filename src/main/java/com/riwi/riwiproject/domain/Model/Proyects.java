package com.riwi.riwiproject.domain.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyect")
public class Proyects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tittle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String nameAdmin;

    @OneToMany(mappedBy = "proyect", cascade = CascadeType.ALL)
    private List<Task> tasksProyect;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}
