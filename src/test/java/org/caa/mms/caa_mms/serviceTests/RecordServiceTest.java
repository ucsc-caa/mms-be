package org.caa.mms.caa_mms.serviceTests;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.domains.Record;
import org.caa.mms.caa_mms.domains.Staff;
import org.caa.mms.caa_mms.services.RecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {
    @Autowired
    private RecordService s;

    @Test
    public void addRecordTest() {
        Member member = new Member();
        Staff staff = new Staff();
        String logInfo = "mock log in";
        Long id = s.addRecord(staff, member, logInfo);
        Assert.assertNotEquals(id, java.util.Optional.of(0));
    }
    @Test
    public void getRecordByIdTest() {
        Member member = new Member();
        Staff staff = new Staff();
        String logInfo = "mock log in";
        Long id = s.addRecord(staff, member, logInfo);
        Record mock_rec = s.getRecord(id).get();
        Assert.assertEquals(id, mock_rec.getId());
    }
    @Test
    public void getRecordByStaffIdTest() {
        Member member = new Member();
        Staff staff = new Staff();
        String logInfo = "mock log in";
        Long id = s.addRecord(staff, member, logInfo);
        List<Record> RecordListByStaff = s.getRecordByStaffId(staff.getId());
        Assert.assertNotEquals(0,RecordListByStaff.size());
    }
}
