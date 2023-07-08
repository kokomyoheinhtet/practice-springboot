package com.koko.practicespringboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        ))
public class Student {

    @Id
//    @SequenceGenerator(
//            name = "student_seq",
//            sequenceName = "student_seq",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "student_seq"
//    )
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "email_address", nullable = false)
    private String email;

//    @Embedded
    private Guardian guardian;
}
