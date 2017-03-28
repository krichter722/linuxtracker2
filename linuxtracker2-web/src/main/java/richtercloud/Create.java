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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.UploadedFile;
import richtercloud.linuxtracker2.jar.controller.TorrentService;
import richtercloud.linuxtracker2.jar.entities.Torrent;

/**
 *
 * @author richter
 */
@ManagedBean
public class Create implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private byte[] torrentData;
    @EJB
    private TorrentService torrentService;
    private UploadedFile uploadedFile;

    public Create() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     * Handles upload and saving of torrent since it's nicer to have one button
     * for both as long as AJAX doesn't work (see create.xhtml).
     *
     * @return the URL for redirection to the details page of the saved torrent
     */
    public String upload() throws NoSuchAlgorithmException {
        String fileName = uploadedFile.getFileName();
        String contentType = uploadedFile.getContentType();
        //@TODO: check whether content type matches torrent
        this.torrentData = uploadedFile.getContents();

        if(this.name == null || this.name.isEmpty()) {
            this.name = fileName;
        }
        //@TODO: validate uploaded file

        String md5Hash = new String(MessageDigest.getInstance("MD5").digest(torrentData));
        Torrent torrent = new Torrent(name, md5Hash);
        torrentService.saveTorrent(torrent);
        return String.format("details.xhtml?hash=%s",
                md5Hash);
    }
}
