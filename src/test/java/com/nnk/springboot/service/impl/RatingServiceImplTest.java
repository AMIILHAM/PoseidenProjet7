package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositorie.RatingRepository;
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
public class RatingServiceImplTest {

    @Mock
    private RatingRepository mockRatingRepository;

    private RatingServiceImpl ratingServiceImplUnderTest;

    @Before
    public void setUp() {
        ratingServiceImplUnderTest = new RatingServiceImpl(mockRatingRepository);
    }

    @Test
    public void testFindById() {
        // Setup
        // Configure RatingRepository.findById(...).
        final Optional<Rating> rating = Optional.of(new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0));
        when(mockRatingRepository.findById(0)).thenReturn(rating);

        // Run the test
        final Optional<Rating> result = ratingServiceImplUnderTest.findById(0);

        // Verify the results
    }

    @Test
    public void testFindById_RatingRepositoryReturnsAbsent() {
        // Setup
        when(mockRatingRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Rating> result = ratingServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetPage() {
        // Setup
        // Configure RatingRepository.findAll(...).
        final Page<Rating> ratingPage = new PageImpl<>(
                List.of(new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0)));
        when(mockRatingRepository.findAll(any(Pageable.class))).thenReturn(ratingPage);

        // Run the test
        final Page<Rating> result = ratingServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetPage_RatingRepositoryReturnsNoItems() {
        // Setup
        when(mockRatingRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<Rating> result = ratingServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testSave() {
        // Setup
        final Rating rating = new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0);

        // Configure RatingRepository.save(...).
        final Rating rating1 = new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0);
        when(mockRatingRepository.save(any(Rating.class))).thenReturn(rating1);

        // Run the test
        final Rating result = ratingServiceImplUnderTest.save(rating);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final Rating rating = new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0);

        // Configure RatingRepository.save(...).
        final Rating rating1 = new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0);
        when(mockRatingRepository.save(any(Rating.class))).thenReturn(rating1);

        // Run the test
        final Rating result = ratingServiceImplUnderTest.update(rating);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Configure RatingRepository.findById(...).
        final Optional<Rating> rating = Optional.of(new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0));
        when(mockRatingRepository.findById(0)).thenReturn(rating);

        // Run the test
        ratingServiceImplUnderTest.delete(0);

        // Verify the results
        verify(mockRatingRepository).deleteById(0);
    }

    @Test
    public void testDelete_RatingRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockRatingRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        ratingServiceImplUnderTest.delete(0);

        // Verify the results
    }
}
