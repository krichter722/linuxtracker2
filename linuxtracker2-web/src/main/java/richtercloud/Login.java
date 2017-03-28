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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import richtercloud.linuxtracker2.jar.controller.UserService;
import richtercloud.linuxtracker2.jar.entities.AUser;

/**
 * The JSF controller for login and logout related operations.
 *
 * Inspired by
 * http://stackoverflow.com/questions/3841361/jsf-http-session-login.
 *
 * @author richter
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean loggedIn;
    private String username;
    private String password;
    @EJB
    private UserService userService;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    public String login() {
        org.eclipse.persistence.Version.printVersion();
        AUser user = userService.find(username, password);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            username = null;
            password = null;
            return null;
        } else {
            context.getExternalContext().getSessionMap().put("user", user);
            this.loggedIn = true;
            return "";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        this.loggedIn = false;
        context.getExternalContext().getSessionMap().remove("user");
        return "";
    }

    public boolean isLoggedIn() {
        return loggedIn;
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
}
