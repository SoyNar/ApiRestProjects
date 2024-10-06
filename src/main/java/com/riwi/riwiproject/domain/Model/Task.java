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

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}
