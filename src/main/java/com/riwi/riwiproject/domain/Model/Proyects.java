package com.riwi.riwiproject.domain.Model;
import com.riwi.riwiproject.Config.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "proyects")
public class Proyects extends Auditable {
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
    private List<Task> tasks = new ArrayList<>();

}
