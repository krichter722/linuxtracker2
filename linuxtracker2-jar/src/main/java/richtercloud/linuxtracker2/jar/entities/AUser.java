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
package richtercloud.linuxtracker2.jar.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 *
 * @author richter
 */
@Entity
public class AUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String username;
    private String password;
    private String emailAddress;
    /**
     * The permissions the user has. Set can be empty which means that the user
     * has no special permissions.
     */
    /*
    internal implementation notes:
    - EnumType.STRING is a waste of space, but using the ordinal value is very
    prone to errors since changes to the enum can result in wrong values being
    used and using the complete string representation saves writing a converter
    (see http://www.nurkiewicz.com/2013/06/mapping-enums-done-right-with-convert.html
    in case that becomes interesting)
    */
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions = new HashSet<>(Arrays.asList(Permission.EDIT_POST, Permission.CREATE_TORRENT));

    protected AUser() {
    }

    public AUser(String username,
            String password,
            String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
