package com.example.problemtracker.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @NotEmpty(message = "Comment must include text")
    private String text;
    @NotBlank(message = "Comment has to be made by user")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users userCommented;
    @NotBlank(message = "Comment has to be assigned to problem")
    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problemCommented;
    private Instant date;

}
