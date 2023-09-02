package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositorie.TradeRepository;
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
public class TradeServiceImplTest {

    @Mock
    private TradeRepository mockTradeRepository;

    private TradeServiceImpl tradeServiceImplUnderTest;

    @Before
    public void setUp() {
        tradeServiceImplUnderTest = new TradeServiceImpl(mockTradeRepository);
    }

    @Test
    public void testFindById() {
        // Setup
        // Configure TradeRepository.findById(...).
        final Trade trade1 = new Trade();
        trade1.setTradeId(0);
        trade1.setAccount("account");
        trade1.setType("type");
        trade1.setBuyQuantity(0.0);
        trade1.setSellQuantity(0.0);
        final Optional<Trade> trade = Optional.of(trade1);
        when(mockTradeRepository.findById(0)).thenReturn(trade);

        // Run the test
        final Optional<Trade> result = tradeServiceImplUnderTest.findById(0);

        // Verify the results
    }

    @Test
    public void testFindById_TradeRepositoryReturnsAbsent() {
        // Setup
        when(mockTradeRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Trade> result = tradeServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetPage() {
        // Setup
        // Configure TradeRepository.findAll(...).
        final Trade trade = new Trade();
        trade.setTradeId(0);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(0.0);
        trade.setSellQuantity(0.0);
        final Page<Trade> trades = new PageImpl<>(List.of(trade));
        when(mockTradeRepository.findAll(any(Pageable.class))).thenReturn(trades);

        // Run the test
        final Page<Trade> result = tradeServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetPage_TradeRepositoryReturnsNoItems() {
        // Setup
        when(mockTradeRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<Trade> result = tradeServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testSave() {
        // Setup
        final Trade trade = new Trade();
        trade.setTradeId(0);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(0.0);
        trade.setSellQuantity(0.0);

        // Configure TradeRepository.save(...).
        final Trade trade1 = new Trade();
        trade1.setTradeId(0);
        trade1.setAccount("account");
        trade1.setType("type");
        trade1.setBuyQuantity(0.0);
        trade1.setSellQuantity(0.0);
        when(mockTradeRepository.save(any(Trade.class))).thenReturn(trade1);

        // Run the test
        final Trade result = tradeServiceImplUnderTest.save(trade);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Configure TradeRepository.findById(...).
        final Trade trade1 = new Trade();
        trade1.setTradeId(0);
        trade1.setAccount("account");
        trade1.setType("type");
        trade1.setBuyQuantity(0.0);
        trade1.setSellQuantity(0.0);
        final Optional<Trade> trade = Optional.of(trade1);
        when(mockTradeRepository.findById(0)).thenReturn(trade);

        // Run the test
        tradeServiceImplUnderTest.delete(0);

        // Verify the results
        verify(mockTradeRepository).deleteById(0);
    }

    @Test
    public void testDelete_TradeRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockTradeRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        tradeServiceImplUnderTest.delete(0);

        // Verify the results
    }
}
