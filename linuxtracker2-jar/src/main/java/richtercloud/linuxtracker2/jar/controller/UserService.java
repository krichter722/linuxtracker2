/**
 * Copyright 2017 Karl-Philipp Richter
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package richtercloud.linuxtracker2.jar.controller;

import java.util.List;
import javax.ejb.Local;
import richtercloud.linuxtracker2.jar.entities.AUser;

/**
 *
 * @author richter
 */
@Local
public interface UserService {

    AUser find(String username, String password);

    /**
     * Registers the user in the user service. Implementations are requested to
     * transform the password into a secure hash and only store this hash
     * instead of the plain text password.
     *
     * @param username the username of the user to register
     * @param password the plain text password of the user to register
     * @param emailAddress the address the user specified at registration
     * @return the registered user instance
     */
    AUser register(String username,
            String password,
            String emailAddress) throws UserAlreadyRegisteredException;

    List<AUser> getAllUsers();
}
