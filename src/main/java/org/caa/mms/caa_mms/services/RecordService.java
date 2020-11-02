package org.caa.mms.caa_mms.services;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.domains.Record;
import org.caa.mms.caa_mms.domains.Staff;
import org.caa.mms.caa_mms.repositories.MemberRepository;
import org.caa.mms.caa_mms.repositories.RecordRepository;

import org.caa.mms.caa_mms.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/*
 *File: RecordService.java
 *Author: JZZ created 10/30/2020
 * Description: Record operations: add, get(by id, by staff_id, etc), retrieve all record
 */
@Service
public class RecordService {
    @Autowired
    RecordRepository recd_repo;
    @Autowired
    StaffRepository staff_repo;
    @Autowired
    MemberRepository memb_repo;

    /* Method：addRecord
     * Description: Pass a Staff, Member object and a String as Parameters to add a record
       LocalDateTime stamp will be add into database automatically.
     * return the id of the record
    */
    public Long addRecord(Staff staff, Member mem, String logInfo) {
        // Initialize a new record object and setup the values
        Record record = new Record();
        record.setMember(mem);
        record.setStaff(staff);
        record.setText(logInfo);

        /*set up the current time with local timezone
        time will be automatically added into database, the following implementation is not needed.
        //ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        //LocalDateTime currTime = LocalDateTime.now(zoneId);
        //record.setTime(currTime);
        */
        // insert the record into database
        Long id = recd_repo.save(record).getId();
        return id;

    }

    /*get a specific record by record id*/
    public Optional<Record> getRecord(Long id) {
        Optional<Record> record = recd_repo.findById(id);
        return record;
    }

    /*get all records of a Staff by staff id*/
    public List<Record> getRecordByStaffId(Long id) {

        Optional<Staff> s = staff_repo.findById(id);

        List<Record> record = recd_repo.findByStaff(s.get());
        return record;
    }

    /*get all records of a Member by member id */
    public List<Record> getRecordByMemId(long id) {

        Optional<Member> m = memb_repo.findById(id);

        List<Record> record = recd_repo.findByMember(m.get());
        return record;
    }
    /*get all the records from database*/
    public List<Record> ListAllRecord() {
        return recd_repo.findAll();
    }
}
