/*
 * Copyright 2014 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yaza.cms.service;

import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.User;
import com.yaza.cms.domain.config.UserRole;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Judge Muzinda
 */
public interface UserService extends GenericService<User> {

    public User findByUserName(String name);

    public String getCurrentUsername();

    public User getCurrentUser();

    List<User> getAllInActive(Branch branch);

    List<User> getAll(Branch branch);

    /*
     change pass
     */
    public User changePassword(User t);

    public Optional<User> findUserByResetToken(String resetToken);

    List<User> getAllInActive();
    List<User> findByBranch(Branch branch);
    List<User> findByBranchAndUserRoles(Branch branch, UserRole role);
    List<User> findByUserRoles(UserRole role);
}
