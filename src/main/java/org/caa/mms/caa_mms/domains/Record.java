package org.caa.mms.caa_mms.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    public Record(Staff s){
        this.staff = s;
    }
    public Record(Member m){
        this.member = m;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Staff_id")
    private Staff staff;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Member_id")
    private Member member;
    private LocalDateTime time;
    private String Text;



}

