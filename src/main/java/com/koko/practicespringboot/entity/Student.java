package com.koko.practicespringboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
//    @SequenceGenerator(name = "student_seq_name1", sequenceName = "student_seq", allocationSize = 0)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_name1")
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Embedded
    private Guardian guardian;
}
