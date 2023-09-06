package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exception.ServiceException;
import com.nnk.springboot.repositorie.UserRepository;
import com.nnk.springboot.service.AppUserService;
import com.nnk.springboot.utils.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get a list of all users
     *
     * @return page of User containing all user
     */
    @Override
    public Page<AppUser> appUserPage(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    /**
     * Save a new user in the DB
     * @param user the AppUser to save
     */
    @Override
    public AppUser save(AppUser user) throws ServiceException {
        if (this.userRepository.countByUsername(user.getUsername(),user.getId()) > 0)
            throw new ServiceException(ErrorMessageConstants.USERNAME_ALREADY_EXIST);

        return this.userRepository.save(user);
    }

    /**
     * update an existent user from the DB
     * @param user the user ID
     */
    @Override
    public AppUser update(AppUser user) throws ServiceException {

        AppUser userToUpdate = this.userRepository.findById(user.getId()).orElseThrow(() -> new ServiceException(ErrorMessageConstants.USER_NOT_FOUND_EXCEPTION_MESSAGE));
        if (this.userRepository.countByUsername(user.getUsername(),user.getId()) > 0)
            throw new ServiceException(ErrorMessageConstants.USERNAME_ALREADY_EXIST);

        userToUpdate.setFullname(user.getFullname());
        userToUpdate.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userToUpdate.setRole(user.getRole());
        return this.userRepository.save(userToUpdate);
    }

    /**
     * Delete an existent user from the DB
     * @param userId the user ID
     */
    @Override
    public void deleteUser(Integer userId) {
        this.findById(userId).ifPresent(appUser -> this.userRepository.deleteById(appUser.getId()));
    }

    /**
     * Get a user  by ID
     * @param userId the user ID
     */
    @Override
    public Optional<AppUser> findById(Integer userId) {
        return this.userRepository.findById(userId);
    }
}
