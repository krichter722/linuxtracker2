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
package richtercloud;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import richtercloud.linuxtracker2.jar.controller.UserAlreadyRegisteredException;
import richtercloud.linuxtracker2.jar.controller.UserService;
import richtercloud.linuxtracker2.jar.entities.AUser;

/**
 *
 * @author richter
 */
@ManagedBean
@RequestScoped
public class Register implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String email;
    @EJB
    private UserService userService;

    /**
     * Creates a new instance of Register
     */
    public Register() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String register() {
        try {
            AUser registeredUser = userService.register(this.username,
                    this.password,
                    this.email);
        }catch(UserAlreadyRegisteredException ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Registration failed because the username is already taken"));
            return null;
        }
        return "/index.xhtml";
    }
}
