package org.ucsccaa.mms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ucsccaa.mms.domains.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
