package org.caa.mms.caa_mms.serviceTests;

import java.util.List;

import org.caa.mms.caa_mms.domains.Staff;
import org.caa.mms.caa_mms.services.StaffService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffServiceTest {
    
    @Autowired
    private StaffService service;

    @Test
    public void addStaffTest() {
        Staff mockStaff = new Staff();
        mockStaff.setDept("mock");
        Staff receivedStaff = service.addStaff(mockStaff).get();
        Assert.assertEquals(receivedStaff.getDept(), mockStaff.getDept());
    }

    @Test 
    public void getStaffTest() {
        Staff mockStaff = new Staff();
        mockStaff.setDept("mock");
        long id = service.addStaff(mockStaff).get().getId();
        Staff receivedStaff = service.getStaff(id).get();
        Assert.assertEquals(receivedStaff.getDept(), mockStaff.getDept());
    }

    @Test
    public void deleteStaffTest() {
        Staff mockStaff = new Staff();
        mockStaff.setDept("mock");
        long id = service.addStaff(mockStaff).get().getId();
        if (service.deleteStaff(id)) {
            Assert.assertFalse(service.getStaff(id).isPresent());
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    @Test
    public void getStaffByDeptTest() {
        Staff mockStaff = new Staff();
        mockStaff.setDept("mock");
        service.addStaff(mockStaff);
        List<Staff> receivedList = service.getStaffByDept("mock");
        Assert.assertEquals(1, receivedList.size());
    }
}
