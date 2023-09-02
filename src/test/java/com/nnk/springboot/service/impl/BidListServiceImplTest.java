package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositorie.BidListRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BidListServiceImplTest {

    @Mock
    private BidListRepository mockBidListRepository;

    private BidListServiceImpl bidListServiceImplUnderTest;

    @Before
    public void setUp() {
        bidListServiceImplUnderTest = new BidListServiceImpl(mockBidListRepository);
    }

    @Test
    public void testFindById() {
        // Setup
        // Configure BidListRepository.findById(...).
        final BidList bidList1 = new BidList();
        bidList1.setBidListId(0);
        bidList1.setAccount("account");
        bidList1.setType("type");
        bidList1.setBidQuantity(0.0);
        bidList1.setAskQuantity(0.0);
        final Optional<BidList> bidList = Optional.of(bidList1);
        when(mockBidListRepository.findById(0)).thenReturn(bidList);

        // Run the test
        final Optional<BidList> result = bidListServiceImplUnderTest.findById(0);

        // Verify the results
    }

    @Test
    public void testFindById_BidListRepositoryReturnsAbsent() {
        // Setup
        when(mockBidListRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<BidList> result = bidListServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetAllBidList() {
        // Setup
        // Configure BidListRepository.findAll(...).
        final BidList bidList = new BidList();
        bidList.setBidListId(0);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(0.0);
        bidList.setAskQuantity(0.0);
        final Page<BidList> bidLists = new PageImpl<>(List.of(bidList));
        when(mockBidListRepository.findAll(any(Pageable.class))).thenReturn(bidLists);

        // Run the test
        final Page<BidList> result = bidListServiceImplUnderTest.getAllBidList(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetAllBidList_BidListRepositoryReturnsNoItems() {
        // Setup
        when(mockBidListRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<BidList> result = bidListServiceImplUnderTest.getAllBidList(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testSave() {
        // Setup
        final BidList bidList = new BidList();
        bidList.setBidListId(0);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(0.0);
        bidList.setAskQuantity(0.0);

        // Configure BidListRepository.save(...).
        final BidList bidList1 = new BidList();
        bidList1.setBidListId(0);
        bidList1.setAccount("account");
        bidList1.setType("type");
        bidList1.setBidQuantity(0.0);
        bidList1.setAskQuantity(0.0);
        when(mockBidListRepository.save(any(BidList.class))).thenReturn(bidList1);

        // Run the test
        final BidList result = bidListServiceImplUnderTest.save(bidList);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final BidList bidList = new BidList();
        bidList.setBidListId(0);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(0.0);
        bidList.setAskQuantity(0.0);

        // Configure BidListRepository.save(...).
        final BidList bidList1 = new BidList();
        bidList1.setBidListId(0);
        bidList1.setAccount("account");
        bidList1.setType("type");
        bidList1.setBidQuantity(0.0);
        bidList1.setAskQuantity(0.0);
        when(mockBidListRepository.save(any(BidList.class))).thenReturn(bidList1);

        // Run the test
        final BidList result = bidListServiceImplUnderTest.update(bidList);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Configure BidListRepository.findById(...).
        final BidList bidList1 = new BidList();
        bidList1.setBidListId(0);
        bidList1.setAccount("account");
        bidList1.setType("type");
        bidList1.setBidQuantity(0.0);
        bidList1.setAskQuantity(0.0);
        final Optional<BidList> bidList = Optional.of(bidList1);
        when(mockBidListRepository.findById(0)).thenReturn(bidList);

        // Run the test
        bidListServiceImplUnderTest.delete(0);

        // Verify the results
        verify(mockBidListRepository).deleteById(0);
    }

    @Test
    public void testDelete_BidListRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockBidListRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        bidListServiceImplUnderTest.delete(0);

        // Verify the results
    }
}
