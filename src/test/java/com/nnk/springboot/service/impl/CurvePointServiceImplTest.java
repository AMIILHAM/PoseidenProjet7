package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.repositorie.CurvePointRepository;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurvePointServiceImplTest {

    @Mock
    private CurvePointRepository mockCurvePointRepository;

    private CurvePointServiceImpl curvePointServiceImplUnderTest;

    @Before
    public void setUp() {
        curvePointServiceImplUnderTest = new CurvePointServiceImpl(mockCurvePointRepository);
    }

    @Test
    public void testFindById() {
        // Setup
        // Configure CurvePointRepository.findById(...).
        final CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setId(0);
        curvePoint1.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint1.setTerm(0.0);
        curvePoint1.setValue(0.0);
        curvePoint1.setCreationDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final Optional<CurvePoint> curvePoint = Optional.of(curvePoint1);
        when(mockCurvePointRepository.findById(0)).thenReturn(curvePoint);

        // Run the test
        final Optional<CurvePoint> result = curvePointServiceImplUnderTest.findById(0);

        // Verify the results
    }

    @Test
    public void testFindById_CurvePointRepositoryReturnsAbsent() {
        // Setup
        when(mockCurvePointRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<CurvePoint> result = curvePointServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetPage() {
        // Setup
        // Configure CurvePointRepository.findAll(...).
        final CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(0);
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint.setTerm(0.0);
        curvePoint.setValue(0.0);
        curvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final Page<CurvePoint> curvePointPage = new PageImpl<>(List.of(curvePoint));
        when(mockCurvePointRepository.findAll(any(Pageable.class))).thenReturn(curvePointPage);

        // Run the test
        final Page<CurvePoint> result = curvePointServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetPage_CurvePointRepositoryReturnsNoItems() {
        // Setup
        when(mockCurvePointRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<CurvePoint> result = curvePointServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testSave() {
        // Setup
        final CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(0);
        curvePointDto.setTerm(0.0);
        curvePointDto.setValue(0.0);

        // Configure CurvePointRepository.save(...).
        final CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(0);
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint.setTerm(0.0);
        curvePoint.setValue(0.0);
        curvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        when(mockCurvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        // Run the test
        final CurvePoint result = curvePointServiceImplUnderTest.save(curvePointDto);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(0);
        curvePointDto.setTerm(0.0);
        curvePointDto.setValue(0.0);

        // Configure CurvePointRepository.findById(...).
        final CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setId(0);
        curvePoint1.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint1.setTerm(0.0);
        curvePoint1.setValue(0.0);
        curvePoint1.setCreationDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final Optional<CurvePoint> curvePoint = Optional.of(curvePoint1);
        when(mockCurvePointRepository.findById(0)).thenReturn(curvePoint);

        // Configure CurvePointRepository.save(...).
        final CurvePoint curvePoint2 = new CurvePoint();
        curvePoint2.setId(0);
        curvePoint2.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint2.setTerm(0.0);
        curvePoint2.setValue(0.0);
        curvePoint2.setCreationDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        when(mockCurvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint2);

        // Run the test
        final CurvePoint result = curvePointServiceImplUnderTest.update(curvePointDto);

        // Verify the results
    }

    @Test
    public void testUpdate_CurvePointRepositoryFindByIdReturnsAbsent() {
        // Setup
        final CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(0);
        curvePointDto.setTerm(0.0);
        curvePointDto.setValue(0.0);

        when(mockCurvePointRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> curvePointServiceImplUnderTest.update(curvePointDto))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    public void testDelete() {
        // Setup
        // Configure CurvePointRepository.findById(...).
        final CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setId(0);
        curvePoint1.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint1.setTerm(0.0);
        curvePoint1.setValue(0.0);
        curvePoint1.setCreationDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final Optional<CurvePoint> curvePoint = Optional.of(curvePoint1);
        when(mockCurvePointRepository.findById(0)).thenReturn(curvePoint);

        // Run the test
        curvePointServiceImplUnderTest.delete(0);

        // Verify the results
        verify(mockCurvePointRepository).deleteById(0);
    }

    @Test
    public void testDelete_CurvePointRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockCurvePointRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        curvePointServiceImplUnderTest.delete(0);

        // Verify the results
    }
}
