package no.westerdals.westbook.uploads;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import no.westerdals.westbook.model.FileMeta;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MongoUploadService implements UploadService
{
    private GridFsTemplate fsTemplate;
    private MappingMongoConverter mappingConverter;

    public MongoUploadService(GridFsTemplate fsTemplate, MappingMongoConverter mappingConverter)
    {
        this.fsTemplate = fsTemplate;
        this.mappingConverter = mappingConverter;
    }

    @Override
    public DownloadRequest downloadFile(String id, OutputStream out) throws IOException
    {
        GridFSDBFile file = fsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        if (file == null)
            return null;
        return new DownloadRequest(file.getInputStream(), out, deserialize(file));
    }

    @Override
    public FileMeta uploadFile(FileMeta meta, InputStream in) throws IOException
    {
        String name = meta.getName();
        meta.setName(null);
        meta.setUploadTime(null);
        GridFSFile file = fsTemplate.store(in, name, meta);
        return deserialize(file);
    }

    @Override
    public FileMeta getFileMeta(String id)
    {
        return mappingConverter.read(FileMeta.class, fsTemplate.findOne(Query.query(Criteria.where("_id").is(id))));
    }

    private FileMeta deserialize(GridFSFile file)
    {
        DBObject meta = file.getMetaData();
        FileMeta fileMeta = new FileMeta(
                (String)meta.get("ownerId"),
                file.getFilename(),
                (boolean)meta.get("attachment"),
                file.getUploadDate());
        fileMeta.setId(file.getId().toString());
        return fileMeta;
    }
}
