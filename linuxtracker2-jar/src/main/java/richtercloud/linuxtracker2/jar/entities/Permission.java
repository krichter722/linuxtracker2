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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Generally all action ought to be reviewed. Only special admins can perform
 * actions like {@link #DELETE_TORRENT} and {@link #BAN_USER} without peer
 * review.
 * @author richter
 */
public enum Permission {

    CREATE_TORRENT,
    DELETE_TORRENT,
    EDIT_POST, BAN_USER,
    SUGGEST_TORRENT_DELETION,
    SUGGEST_BAN_USER,
    SUGGEST_POST_EDITING,
    REVIEW_TORRENT_CREATION,
    REVIEW_TORRENT_DELETION,
    REVIEW_POST_EDITING;

    public final static Set<Permission> ALL = new HashSet<>(Arrays.asList(CREATE_TORRENT,
            DELETE_TORRENT,
            EDIT_POST, BAN_USER,
            SUGGEST_TORRENT_DELETION,
            SUGGEST_BAN_USER,
            SUGGEST_POST_EDITING,
            REVIEW_TORRENT_CREATION,
            REVIEW_TORRENT_DELETION,
            REVIEW_POST_EDITING));
}
