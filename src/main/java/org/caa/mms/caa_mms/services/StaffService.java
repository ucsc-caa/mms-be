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
    private StaffRepository staffRepository;

    public Optional<Staff> addStaff(Staff staff) {
        staff.setId(null);
        return Optional.ofNullable(staffRepository.save(staff));
    }

    public Optional<Staff> updateStaff(Staff staff, long id) {
        Optional<Staff> oldStaff = staffRepository.findById(id);
        if (oldStaff.isPresent()) {
            staff.setId(id);
            return Optional.ofNullable(staffRepository.save(staff));
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<Staff> getStaff(long id) {
        return staffRepository.findById(id);
    }

    public boolean deleteStaff(long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()) {
            staffRepository.delete(staff.get());
            return true;
        } else {
            return false;
        }
    }

    public List<Staff> getStaffByDept(String dept) {
        return staffRepository.findByDept(dept);
    }

    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

}
