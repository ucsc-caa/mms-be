package org.caa.mms.caa_mms.domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
//    @JoinColumn(name = "Staff_id")
    private Staff staff;
    @ManyToOne(cascade = {CascadeType.PERSIST})
//    @JoinColumn(name = "Member_id")
    private Member member;
    private String time;
    private String Text;

}
