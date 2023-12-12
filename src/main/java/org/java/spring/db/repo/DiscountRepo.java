package org.java.spring.db.repo;

import org.java.spring.db.pojo.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Integer>{

}
