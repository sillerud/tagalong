package no.westerdals.westbook.uploads;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.*;
import no.westerdals.westbook.ImageType;
import no.westerdals.westbook.model.FileMeta;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


public class MongoUploadService implements UploadService {
    private GridFsTemplate fsTemplate;
    private MappingMongoConverter mappingConverter;

    public MongoUploadService(GridFsTemplate fsTemplate, MappingMongoConverter mappingConverter) {
        this.fsTemplate = fsTemplate;
        this.mappingConverter = mappingConverter;
    }

    @Override
    public DownloadRequest downloadFile(String id, OutputStream out) throws IOException {
        GridFSDBFile file = fsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        return file == null ? null : new DownloadRequest(file.getInputStream(), out, deserialize(file));
    }

    @Override
    public FileMeta uploadFile(FileMeta meta, InputStream in) throws IOException {
        MongoFileMeta mongoFileMeta = new MongoFileMeta(meta);
        GridFSFile file;
        ImageType imageType = meta.getImageType();
        if (imageType == null) {
            file = fsTemplate.store(in, meta.getName(), mongoFileMeta);
        } else {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            IOUtils.copy(in, bytes);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes.toByteArray()));
            if (image.getWidth() > imageType.getMaxWidth() || image.getHeight() > imageType.getMaxHeight())
                return null;
            file = fsTemplate.store(new ByteArrayInputStream(bytes.toByteArray()), meta.getName(), mongoFileMeta);
        }
        return deserialize(file);
    }

    @Override
    public FileMeta getFileMeta(String id) {
        GridFSFile file = fsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        if (file == null)
            return null;
        return deserialize(file);
    }

    @Override
    public List<FileMeta> getAllFileInfo() {
        return fsTemplate.find(new Query()).stream().map(this::deserialize).collect(Collectors.toList());
    }

    private FileMeta deserialize(GridFSFile file) {
        MongoFileMeta mongoFileMeta = mappingConverter.read(MongoFileMeta.class, file.getMetaData());
        FileMeta fileMeta = new FileMeta(mongoFileMeta.getOwnerId(), file.getFilename(),
                mongoFileMeta.getImageType(),  file.getUploadDate());
        fileMeta.setId(file.getId().toString());
        return fileMeta;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    private static class MongoFileMeta {
        public MongoFileMeta(FileMeta original) {
            this.ownerId = original.getOwnerId();
            this.imageType = original.getImageType();
        }

        private String ownerId;
        private ImageType imageType;
    }
}
