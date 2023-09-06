package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositorie.RatingRepository;
import com.nnk.springboot.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    /**
     * Get a rating  by ID
     * @param id the rating ID
     */
    @Override
    public Optional<Rating> findById(Integer id) {
        return this.ratingRepository.findById(id);
    }
    /**
     * Get a list of all ratings
     *
     * @return page of Rating containing all rating models
     */
    @Override
    public Page<Rating> getPage(Pageable pageable) {
        return this.ratingRepository.findAll(pageable);
    }

    /**
     * Save a new rating in the DB
     * @param rating the BidListModel to save
     */
    @Override
    public Rating save(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    /**
     * update an existent rating from the DB
     * @param rating the rating ID
     */
    @Override
    public Rating update(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    /**
     * Delete an existent rating from the DB
     * @param ratingId the rating ID
     */
    @Override
    public void delete(Integer ratingId) {
        this.findById(ratingId).ifPresent(rating -> this.ratingRepository.deleteById(rating.getId()));
    }
}
