package com.yaza.cms.service.impl;

import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.User;
import com.yaza.cms.domain.config.UserRole;
import com.yaza.cms.repo.UserRepo;
import com.yaza.cms.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepo userRepo;

    @Override
    public List<User> getAll() {
        return userRepo.getOptAll(Boolean.TRUE);
    }
//    @Override
//    public List<User> getAll(Company company) {
//        return userRepo.findByActiveAndCompany(Boolean.TRUE, company);
//    }

    @Override
    public User get(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public void delete(User t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        userRepo.save(t);
    }

    @Override
    public List<User> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public User save(User t) {
        if (t.getId() == null) {
            t.setCreatedBy(getCurrentUser());
            t.setDateCreated(new Date());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(t.getPassword());
            t.setPassword(hashedPassword);
            return userRepo.save(t);
        }
        if (t.getCreatedById() != null) {
            t.setCreatedBy(get(t.getCreatedById()));
        }
        t.setPassword(currentPassword(t));
        t.setModifiedBy(getCurrentUser());
        t.setDateModified(new Date());
        return userRepo.save(t);
    }

    @Override
    @Transactional
    public User changePassword(User t) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(t.getPassword());
        t.setPassword(hashedPassword);
        t.setModifiedBy(getCurrentUser());
        t.setDateModified(new Date());
        return userRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(User current, User old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getUserName().equals(old.getUserName())) {
                if (findByUserName(current.getUserName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (findByUserName(current.getUserName()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUserName(String name) {
        return userRepo.findByUserName(name, Boolean.TRUE);
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        User user = findByUserName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllInActive(Branch branch) {
        return null;
    }

    @Override
    public List<User> getAll(Branch branch) {
        return null;
    }

    @Override
    public String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof String) {
            String principal = (String) authentication.getPrincipal();

            if (principal.compareTo("anonymousUser") != 0) {
                return null;
            }
            return principal;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    public String currentPassword(User user) {
        User old = get(user.getId());
        return old.getPassword();
    }

    @Override
    public Optional<User> findUserByResetToken(String resetToken) {
        return userRepo.findByResetToken(resetToken);
    }

    @Override
    public List<User> getAllInActive() {
        return userRepo.findAllByActiveIsTrue();
    }

    @Override
    public List<User> findByBranch(Branch branch) {
        return userRepo.findByBranch(branch);
    }

    @Override
    public List<User> findByBranchAndUserRoles(Branch branch, UserRole role) {
        return userRepo.findByBranchAndUserRoles(branch, role);
    }

    @Override
    public List<User> findByUserRoles(UserRole role) {
        return userRepo.findByUserRoles(role);
    }

//    @Override
//    public List<User> getAllInActive(Company company) {
//        return userRepo.findByActiveAndCompany(Boolean.FALSE, company);
//    }

    @Override
    public User findByUuid(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
