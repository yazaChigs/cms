/*
 * Copyright 2015 Edward Zengeni.
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
package com.yaza.cms.repo;

import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.User;
import java.util.List;
import java.util.Optional;

import com.yaza.cms.domain.config.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edward Zengeni
 */
public interface UserRepo extends AbstractRepo<User, Long> {

    @Query("from User p where p.active=:active Order By p.userName ASC")
    List<User> getOptAll(@Param("active") Boolean active);

    @Query(value = "from User p left join fetch p.userRoles where p.userName=:userName and p.active=:active")
    User findByUserName(@Param("userName") String userName, @Param("active") Boolean active);

    Optional<User> findByResetToken(String resetToken);

    List<User> findAllByActiveIsTrue();

    List<User> findByBranch(Branch branch);
    List<User> findByBranchAndUserRoles(Branch branch, UserRole role);
    List<User> findByUserRoles( UserRole role);

}
