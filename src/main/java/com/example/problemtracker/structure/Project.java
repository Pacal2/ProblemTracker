package com.example.problemtracker.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @NotBlank(message = "Name is required")
    private String projectName;
    @NotBlank(message = "Description is required")
    @Lob
    private String description;
    @NotBlank(message = "Project needs manager")
    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;
    @Nullable
    @OneToMany(fetch = FetchType.LAZY)
    private List<Problem> assignedProblems;

}
