package org.caa.mms.caa_mms.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Staff_id")
    private Staff staff;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Member_id")
    private Member member;
    @Column(insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime time;
    private String text;


}

