package org.caa.mms.caa_mms.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Staff {
    @Id
    private Long id;
}
