package org.caa.mms.caa_mms.services;

import java.util.List;
import java.util.Optional;

import org.caa.mms.caa_mms.domains.Staff;
import org.caa.mms.caa_mms.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    
    @Autowired
    private StaffRepository repo;

    public Optional<Staff> addStaff(Staff staff) {
        return Optional.ofNullable(repo.save(staff));
    }

    public Optional<Staff> getStaff(long id) {
        return repo.findById(id);
    }

    public boolean deleteStaff(long id) {
        Optional<Staff> staff = repo.findById(id);
        if (staff.isPresent()) {
            repo.delete(staff.get());
            return true;
        } else {
            return false;
        }
    }

    public List<Staff> getStaffByDept(String dept) {
        return repo.findByDept(dept);
    }

    public List<Staff> getAll() {
        return repo.findAll();
    }

}
