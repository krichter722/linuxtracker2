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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import richtercloud.linuxtracker2.jar.controller.TorrentService;
import richtercloud.linuxtracker2.jar.entities.Torrent;

/**
 *
 * @author richter
 */
@Stateless
public class DefaultTorrentService implements TorrentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Torrent> getTorrents(String nameLike, int maxResultSize) {
        TypedQuery<Torrent> offerTypeQuery = entityManager.createQuery("SELECT t from Torrent t WHERE t.name LIKE :nameLike", Torrent.class);
        offerTypeQuery.setParameter("nameLike", "%"+nameLike+"%");
        offerTypeQuery.setMaxResults(maxResultSize);
        List<Torrent> retValue = offerTypeQuery.getResultList();
        return retValue;
    }

    @Override
    public void saveTorrent(Torrent torrent) {
        entityManager.persist(torrent);
    }
}
