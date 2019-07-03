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

package com.totalit.bloodbankstatement.repo;

import com.totalit.bloodbankstatement.domain.config.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author Edward Zengeni
 */
public interface UserRoleRepo extends AbstractNameDescRepo<UserRole, Long> {
    
    @Query("from UserRole p where p.active=:active Order By p.name ASC")
    public List<UserRole> getOptAll(@Param("active") Boolean active);
    
    @Query("from UserRole p where p.name=:name")
    public UserRole getUserRoleByName(@Param("name") String name);
}
