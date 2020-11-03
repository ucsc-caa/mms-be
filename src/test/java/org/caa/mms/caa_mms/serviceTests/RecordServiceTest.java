package org.caa.mms.caa_mms.serviceTests;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.domains.Record;
import org.caa.mms.caa_mms.domains.Staff;
import org.caa.mms.caa_mms.repositories.RecordRepository;
import org.caa.mms.caa_mms.services.RecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecordServiceTest {

    @Mock
    RecordRepository recordRepo;

    @InjectMocks
    RecordService recordService;

    private final Staff staff = new Staff();
    private final Member member = new Member();
    private final Long id = Long.valueOf(1);
    private final List<Record> expectedRecord = new ArrayList<>();

    public void setUpRecord() {
        Record record = new Record();
        record.setStaff(staff);
        record.setMember(member);
        record.setText("Unit testing...");
        record.setId(id);
        expectedRecord.add(record);
    }


    @Test
    public void testListAllRecord() {
        setUpRecord();
        when(recordRepo.findAll()).thenReturn(expectedRecord);
        List<Record> actualRecord = recordService.ListAllRecord();
        Assert.assertEquals(expectedRecord.get(0).getText(), actualRecord.get(0).getText());
    }

    @Test
    public void testAddRecord() {
        setUpRecord();
        when(recordRepo.findAll()).thenReturn(expectedRecord);
        List<Record> actualRecord = recordService.ListAllRecord();
        Assert.assertEquals(expectedRecord.get(0).getId(), actualRecord.get(0).getId());
    }

}