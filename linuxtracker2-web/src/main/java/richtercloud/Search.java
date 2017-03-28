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

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import richtercloud.linuxtracker2.jar.controller.TorrentService;
import richtercloud.linuxtracker2.jar.entities.Torrent;

/**
 *
 * @author richter
 */
@ManagedBean
public class Search implements Serializable {
    private static final long serialVersionUID = 1L;
    private String search;
    @EJB
    private TorrentService torrentService;
    private List<Torrent> searchTorrents;

    public Search() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Torrent> getSearchTorrents() {
        return searchTorrents;
    }

    public void setSearchTorrents(List<Torrent> searchTorrents) {
        this.searchTorrents = searchTorrents;
    }

    public List<String> autoComplete(String input) {
        List<String> retValue = torrentService.getTorrents(input,
                0).stream().map(torrent -> torrent.getName()).collect(Collectors.toList());
        return retValue;
    }

    public void runSearch() {
        this.searchTorrents = torrentService.getTorrents(this.search,
                0);
    }

    /**
     * Handles the magnet link download.
     *
     * Inspired by http://stackoverflow.com/questions/9391838/how-to-provide-a-file-download-from-a-jsf-backing-bean.
     */
    public void download() throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
        //The following should work (research content type (investigate with FF) and implement file creation):
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();
//
//        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
//        ec.setResponseContentType(contentType); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
//        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
//        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
//
//        OutputStream output = ec.getResponseOutputStream();
//        // Now you can write the InputStream of the file to the above OutputStream the usual way.
//        // ...
//
//        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }
}
