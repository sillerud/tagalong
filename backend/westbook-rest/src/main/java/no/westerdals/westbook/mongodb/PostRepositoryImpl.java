package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Post;
import no.westerdals.westbook.model.Upvote;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Post> filterPosts(String parentId, String[] tagIds, Pageable pageable) {
        Query query = new Query();
        if (tagIds != null && tagIds.length > 0) {
            query.addCriteria(where("tags").in((Object[]) tagIds));
        }
        if (parentId != null) {
            query.addCriteria(where("parentId").is(parentId));
        }
        if (pageable != null) {
            query.with(pageable);
        }
        return mongoTemplate.find(query, Post.class);
    }

    @Override
    public Post tagAlongToPost(String postId, Upvote upvote) {
        Update update = new Update();
        update.push("upvotes", upvote);
        return mongoTemplate.findAndModify(query(where("_id").is(new ObjectId(postId))), update, Post.class);
    }
}
