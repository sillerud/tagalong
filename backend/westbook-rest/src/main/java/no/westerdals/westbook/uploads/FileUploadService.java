package no.westerdals.westbook.uploads;

import no.westerdals.westbook.model.FileMeta;
import no.westerdals.westbook.mongodb.FileMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class FileUploadService implements UploadService
{
    @Autowired
    FileMetaRepository metaRepository;

    private Path path;
    public void setPath(String path) throws IOException
    {
        this.path = Paths.get(path);
        if (!Files.exists(this.path))
        {
            Files.createDirectories(this.path);
        }
    }

    @Override
    public DownloadRequest downloadFile(String id, OutputStream out) throws IOException
    {
        FileMeta fileMeta = metaRepository.findOne(id);
        Path file = resolve(fileMeta);
        if (!Files.exists(file))
        {
            return null;
        }

        InputStream in = Files.newInputStream(file);
        return new DownloadRequest(in, out, fileMeta);
    }

    @Override
    public FileMeta uploadFile(FileMeta meta, InputStream in) throws IOException
    {
        FileMeta fileMeta = metaRepository.insert(meta);
        int len;
        Path file = resolve(fileMeta);
        OutputStream out = Files.newOutputStream(file);
        byte[] buffer = new byte[4096];
        while ((len = in.read(buffer)) >= 0)
        {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        return fileMeta;
    }

    @Override
    public FileMeta getFileMeta(String id)
    {
        return metaRepository.findOne(id);
    }

    private Path resolve(FileMeta meta)
    {
        return path.resolve(meta.getId());
    }
}
