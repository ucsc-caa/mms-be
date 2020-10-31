package org.caa.mms.caa_mms.repositories;

import java.util.List;
import java.util.Optional;

import org.caa.mms.caa_mms.domains.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public Optional<Staff> findById(Long id);
    public List<Staff> findByDept(String dept);
}
