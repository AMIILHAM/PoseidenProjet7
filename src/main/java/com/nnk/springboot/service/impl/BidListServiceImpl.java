package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositorie.BidListRepository;
import com.nnk.springboot.service.BidListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;

    /**
     * Get a Bid  by ID
     * @param id
     */
    @Override
    public Optional<BidList> findById(Integer id) {
        return this.bidListRepository.findById(id);
    }

    /**
     * Get a list of all bids
     *
     * @return page of BidListModel containing all bid models
     */
    @Override
    public Page<BidList> getAllBidList(Pageable pageable) {
        return bidListRepository.findAll(pageable);
    }

    /**
     * Save a new Bid in the DB
     * @param bidList the BidListModel to save
     */

    @Override
    public BidList save(BidList bidList) {
        return this.bidListRepository.save(bidList);
    }

    /**
     * update an existent bid from the DB
     * @param bidList the bid ID
     */
    @Override
    public BidList update(BidList bidList) {
        return this.save(bidList);
    }

    /**
     * Delete an existent bid from the DB
     * @param bidListId the bid ID
     */
    @Override
    public void delete(Integer bidListId) {
        this.findById(bidListId).ifPresent(bidList -> this.bidListRepository.deleteById(bidList.getBidListId()));
    }
}
