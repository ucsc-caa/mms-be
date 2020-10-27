package org.caa.mms.caa_mms.repositories;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.domains.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<Record> findRecordById(Long id);
    List<Record> findRecordByMember(Member m);

}
