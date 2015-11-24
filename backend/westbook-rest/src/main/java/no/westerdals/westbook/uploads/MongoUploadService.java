package no.westerdals.westbook.uploads;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import lombok.*;
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
        MongoFileMeta mongoFileMeta = new MongoFileMeta(meta);
        GridFSFile file = fsTemplate.store(in, meta.getName(), mongoFileMeta);
        return deserialize(file);
    }

    @Override
    public FileMeta getFileMeta(String id)
    {
        GridFSFile file = fsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        return deserialize(file);
    }

    private FileMeta deserialize(GridFSFile file)
    {
        MongoFileMeta mongoFileMeta = mappingConverter.read(MongoFileMeta.class, file.getMetaData());
        FileMeta fileMeta = new FileMeta(mongoFileMeta.getOwnerId(), file.getFilename(),
                mongoFileMeta.isAttachment(),  file.getUploadDate());
        fileMeta.setId(file.getId().toString());
        return fileMeta;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    private static class MongoFileMeta
    {
        public MongoFileMeta(FileMeta original)
        {
            this.ownerId = original.getOwnerId();
            this.attachment = original.isAttachment();
        }

        private String ownerId;
        private boolean attachment;
    }
}
