package no.westerdals.westbook.uploads;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import no.westerdals.westbook.model.FileMeta;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

        FileMeta fileMeta = mappingConverter.read(FileMeta.class, file.getMetaData());
        fileMeta.setName(file.getFilename());
        fileMeta.setUploadTime(file.getUploadDate());

        return new DownloadRequest(file.getInputStream(), out, fileMeta);
    }

    @Override
    public FileMeta uploadFile(FileMeta meta, InputStream in) throws IOException
    {
        meta.setName(null);
        meta.setUploadTime(null);
        GridFSFile file = fsTemplate.store(in, meta.getName(), meta);
        FileMeta result = mappingConverter.read(FileMeta.class, file.getMetaData());
        result.setId(file.getId().toString());
        return result;
    }

    @Override
    public FileMeta getFileMeta(String id)
    {
        return mappingConverter.read(FileMeta.class, fsTemplate.findOne(Query.query(Criteria.where("_id").is(id))));
    }
}
