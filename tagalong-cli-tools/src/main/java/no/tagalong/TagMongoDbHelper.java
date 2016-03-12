package no.tagalong;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import no.tagalong.model.CsvTag;
import no.tagalong.model.Tag;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TagMongoDbHelper {
    private HashMap<String, ObjectId> objectIds = new HashMap<>();
    private final List<CsvTag> allTags;
    private final String databaseName;
    private final MongoClient client;

    public void startInserting() {
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Tag> collection = database.getCollection("tag", Tag.class);
        while (!allTags.isEmpty()) {
            List<Tag> toUpdate = new ArrayList<>(allTags)
                    .parallelStream()
                    .filter(tag -> tag.getParent() == null || objectIds.containsKey(tag.getParent()))
                    .map(csv -> new Tag(objectIds.get(csv.getParent()), csv.getName(), csv.getDescription()))
                    .collect(Collectors.toList());
            if (toUpdate.isEmpty()) {
                return;
            } else {
                collection.insertMany(toUpdate);
                toUpdate.parallelStream().forEach(tag -> objectIds.put(tag.getName(), tag.getId()));
            }
        }
    }
}
