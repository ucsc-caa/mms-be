package org.ucsccaa.mms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucsccaa.mms.domains.Member;
import org.ucsccaa.mms.domains.Orders;
import org.ucsccaa.mms.repositories.MemberRepository;
import org.ucsccaa.mms.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;
    @Autowired
    private MemberRepository memberRepository;

    public Long createOrder(Orders order) {
        if (order == null) 
            throw new RuntimeException("ORDER CANNOT BE NULL");
        Long memberId = order.getMember().getId();
        if (memberId == null)
            throw new RuntimeException("MEMBER ID CANNOT BE NULL");
        if (!memberRepository.existsById(memberId))
            throw new RuntimeException("MEMBER ID NOT EXISTS");
        return repo.save(order).getId();
    }

    public Orders updateOrder(Orders order) {
        if (order == null) 
            throw new RuntimeException("ORDER CANNOT BE NULL");
        Long memberId = order.getMember().getId();
        if (memberId == null)
            throw new RuntimeException("MEMBER ID CANNOT BE NULL");
        if (!memberRepository.existsById(memberId))
            throw new RuntimeException("MEMBER ID NOT EXISTS");
        return repo.existsById(order.getId()) ? repo.save(order) : null;
    }

    public Orders getOrderById(Long id) {
        if (id == null) 
            throw new RuntimeException("ID CANNOT BE NULL");
        Optional<Orders> order = repo.findById(id);
        return order.isPresent() ? order.get() : null;
    }

    public List<Orders> getAll() {
        return repo.findAll();
    }

    public List<Orders> getOrderByMember(Member member) {
        if (member == null) 
            throw new RuntimeException("MEMBER CANNOT BE NULL");
        return repo.findByMember(member);
    }

    public Boolean deleteById(Long id) {
        if (id == null) 
            throw new RuntimeException("ID CANNOT BE NULL");
        Optional<Orders> order = repo.findById(id);
        if (order.isPresent()) {
            repo.delete(order.get());
            return true;
        }
        return false;
    }
}
