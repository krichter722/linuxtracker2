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

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import richtercloud.linuxtracker2.jar.controller.UserAlreadyRegisteredException;
import richtercloud.linuxtracker2.jar.controller.UserService;
import richtercloud.linuxtracker2.jar.entities.AUser;

/**
 *
 * @author richter
 */
@Stateless
@LocalBean
public class DatabaseUserService implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public DatabaseUserService() {
    }

    @Override
    public AUser find(String username, String password) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(AUser.class);
//        Root<User> pet = criteriaQuery.from(AUser.class);
//        Metamodel metamodel = entityManager.getMetamodel();
//        EntityType<User> userEntityType = metamodel.entity(AUser.class);
//        criteriaQuery.where(pet.get(userEntityType.username).isNull());
//        criteriaQuery.select(pet);
//        TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
            //@TODO: figure out how to make JPA metamodel accessible in EJB
            //module
        TypedQuery<AUser> userQuery = entityManager.createQuery("SELECT u FROM AUser u WHERE u.username = :username", AUser.class);
            //not possible to specify parameter name for type scheme because of
            //`The abstract schema type ':clazz' is unknown.`
        userQuery.setParameter("username", username);
        List<AUser> results = userQuery.getResultList();
        if(results.isEmpty()) {
            return null;
        }
        assert results.size() == 1;
        return results.get(0);
    }

    @Override
    public AUser register(String username,
            String password,
            String emailAddress) throws UserAlreadyRegisteredException {
        if(find(username, password) != null) {
            throw new UserAlreadyRegisteredException();
        }
        AUser user = new AUser(username,
                password,
                emailAddress);
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<AUser> getAllUsers() {
        TypedQuery<AUser> userQuery = entityManager.createQuery("SELECT u FROM AUser u", AUser.class);
        List<AUser> retValue = userQuery.getResultList();
        return retValue;
    }
}
