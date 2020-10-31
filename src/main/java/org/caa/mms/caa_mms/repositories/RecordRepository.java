package org.caa.mms.caa_mms.repositories;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.domains.Record;
import org.caa.mms.caa_mms.domains.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    //Optional<Record> findRecordById(Long id);
    List<Record> findRecordByMember(Member m);
    List<Record> findRecordByStaff(Staff s);

}
