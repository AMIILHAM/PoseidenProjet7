package com.nnk.springboot.repositorie;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    boolean existsByBidListId(Integer bidListId);
}
