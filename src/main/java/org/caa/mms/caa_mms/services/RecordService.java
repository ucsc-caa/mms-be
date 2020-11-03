package org.caa.mms.caa_mms.services;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.domains.Record;
import org.caa.mms.caa_mms.domains.Staff;
import org.caa.mms.caa_mms.repositories.MemberRepository;
import org.caa.mms.caa_mms.repositories.RecordRepository;

import org.caa.mms.caa_mms.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepo;
    @Autowired
    StaffRepository staffRepo;
    @Autowired
    MemberRepository memberRepo;

    /**
     * Return the id of the new Record
     * @param staff, member: the staff/member who is included in this record
     * will be represented as staffId and memberId in database
     * @param logInfo: A message of the operations. For example "Log in"
     * @return id if the record is exist otherwise return 0
     * @Hint: the LocalDateTime will be automatically added into database
     */
    public Long addRecord(Staff staff, Member member, String logInfo) {
        // Initialize a new record object and setup the values
        Record record = new Record();
        record.setMember(member);
        record.setStaff(staff);
        record.setText(logInfo);

        // insert the record into database
        Long id = recordRepo.save(record).getId();
        return id;

    }

    /**
     * Returns a Specific Optional Record
     * @param id: the record id that use to search the specific Record
     * return the specific Record
     */
    public Optional<Record> getRecord(Long id) {
        Optional<Record> record = recordRepo.findById(id);
        return record;
    }

    /**
     * Returns a list of Record that related to a single Staff
     * @param id: the id of the staff
     * return List of Record that related to the <tt>StaffId<tt/>
     */
    public List<Record> getRecordByStaffId(Long id) {

        Optional<Staff> s = staffRepo.findById(id);

        List<Record> record = recordRepo.findByStaff(s.get());
        return record;
    }

    /**
     * Returns a list of Record that related to a single Member
     * @param id: the id of the member
     * return List of Record that related to the <tt>MemberId<tt/>
     */
    public List<Record> getRecordByMemberId(long id) {

        Optional<Member> m = memberRepo.findById(id);

        List<Record> record = recordRepo.findByMember(m.get());
        return record;
    }

    /**
     * Returns All the Records from the Database
     * return List of Record
     */
    public List<Record> ListAllRecord() {
        return recordRepo.findAll();
    }
}