package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exception.ServiceException;
import com.nnk.springboot.repositorie.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private AppUserServiceImpl appUserServiceImplUnderTest;

    @Before
    public void setUp() {
        appUserServiceImplUnderTest = new AppUserServiceImpl(mockUserRepository, mockPasswordEncoder);
    }

    @Test
    public void testAppUserPage() {
        // Setup
        // Configure UserRepository.findAll(...).
        final Page<AppUser> appUserPage = new PageImpl<>(
                List.of(new AppUser(0, "username", "password", "fullname", "role")));
        when(mockUserRepository.findAll(any(Pageable.class))).thenReturn(appUserPage);

        // Run the test
        final Page<AppUser> result = appUserServiceImplUnderTest.appUserPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testAppUserPage_UserRepositoryReturnsNoItems() {
        // Setup
        when(mockUserRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<AppUser> result = appUserServiceImplUnderTest.appUserPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testSave() throws Exception {
        // Setup
        final AppUser user = new AppUser(0, "username", "password", "fullname", "role");
        when(mockUserRepository.countByUsername("username", 0)).thenReturn(0);

        // Configure UserRepository.save(...).
        final AppUser appUser = new AppUser(0, "username", "password", "fullname", "role");
        when(mockUserRepository.save(any(AppUser.class))).thenReturn(appUser);

        // Run the test
        final AppUser result = appUserServiceImplUnderTest.save(user);

        // Verify the results
    }

    @Test
    public void testUpdate() throws Exception {
        // Setup
        final AppUser user = new AppUser(0, "username", "password", "fullname", "role");

        // Configure UserRepository.findById(...).
        final Optional<AppUser> appUser = Optional.of(new AppUser(0, "username", "password", "fullname", "role"));
        when(mockUserRepository.findById(0)).thenReturn(appUser);

        when(mockUserRepository.countByUsername("username", 0)).thenReturn(0);
        when(mockPasswordEncoder.encode("password")).thenReturn("password");

        // Configure UserRepository.save(...).
        final AppUser appUser1 = new AppUser(0, "username", "password", "fullname", "role");
        when(mockUserRepository.save(any(AppUser.class))).thenReturn(appUser1);

        // Run the test
        final AppUser result = appUserServiceImplUnderTest.update(user);

        // Verify the results
    }

    @Test
    public void testUpdate_UserRepositoryFindByIdReturnsAbsent() {
        // Setup
        final AppUser user = new AppUser(0, "username", "password", "fullname", "role");
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> appUserServiceImplUnderTest.update(user)).isInstanceOf(ServiceException.class);
    }

    @Test
    public void testDeleteUser() {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<AppUser> appUser = Optional.of(new AppUser(0, "username", "password", "fullname", "role"));
        when(mockUserRepository.findById(0)).thenReturn(appUser);

        // Run the test
        appUserServiceImplUnderTest.deleteUser(0);

        // Verify the results
        verify(mockUserRepository).deleteById(0);
    }

    @Test
    public void testDeleteUser_UserRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        appUserServiceImplUnderTest.deleteUser(0);

        // Verify the results
    }

    @Test
    public void testFindById() {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<AppUser> appUser = Optional.of(new AppUser(0, "username", "password", "fullname", "role"));
        when(mockUserRepository.findById(0)).thenReturn(appUser);

        // Run the test
        final Optional<AppUser> result = appUserServiceImplUnderTest.findById(0);

        // Verify the results
    }

    @Test
    public void testFindById_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<AppUser> result = appUserServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEmpty();
    }
}
