package no.westerdals.tagalong.uploads;

import no.westerdals.tagalong.model.FileMeta;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface UploadService
{
    DownloadRequest downloadFile(String id, OutputStream out) throws IOException;
    FileMeta uploadFile(FileMeta meta, InputStream in) throws IOException;
    FileMeta getFileMeta(String id);
    List<FileMeta> getAllFileInfo();
}
