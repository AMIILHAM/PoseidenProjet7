package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositorie.TradeRepository;
import com.nnk.springboot.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    /**
     * Get a trade  by ID
     * @param tradeId the trade ID
     */
    @Override
    public Optional<Trade> findById(Integer tradeId) {
        return this.tradeRepository.findById(tradeId);
    }

    /**
     * Get a list of all trades
     *
     * @return page of Trade containing all trade
     */
    @Override
    public Page<Trade> getPage(Pageable pageable) {
        return this.tradeRepository.findAll(pageable);
    }

    /**
     * Save a new trade in the DB
     * @param trade the Trade to save
     */
    @Override
    public Trade save(Trade trade) {
        return this.tradeRepository.save(trade);
    }

    /**
     * Delete an existent trade from the DB
     * @param tradeId the trade ID
     */
    @Override
    public void delete(Integer tradeId) {
        this.findById(tradeId).ifPresent(trade -> this.tradeRepository.deleteById(trade.getTradeId()));
    }
}
