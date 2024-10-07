package com.riwi.riwiproject.domain.Model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tittle;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "proyect_id")
    private Proyects proyect;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userAsigned;

}
