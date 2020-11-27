package com.example.problemtracker.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;
    @NotBlank(message = "Name of problem cannot be empty")
    private String problemName;
    @NotBlank(message = "Problem has to have priority")
    private String priority;
    @NotBlank(message = "Problem has to have status")
    private String status;
    @NotBlank(message = "Problem has to be described")
    @Lob
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId", referencedColumnName = "userId")
    private User manager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
    private Instant date;


}
