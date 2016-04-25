package no.westerdals.tagalong.mongodb;

import com.mongodb.BasicDBObject;
import no.westerdals.tagalong.model.Post;
import no.westerdals.tagalong.model.Upvote;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Post> filterPosts(String[] parentIds, String[] tagIds, Pageable pageable) {
        Query query = new Query();
        ArrayList<Criteria> criterias = new ArrayList<Criteria>();
        if (tagIds != null && tagIds.length > 0) {
            criterias.add(where("tagIds").in((Object[]) tagIds));
        }
        if (parentIds != null) {
            criterias.add(where("parentId").in((Object[]) parentIds));
        }
        if (pageable != null) {
            query.with(pageable);
        }
        if (criterias.size() > 0) {
            query.addCriteria(new Criteria().orOperator(criterias.stream().toArray(Criteria[]::new)));
        }
        return mongoTemplate.find(query, Post.class);
    }

    // TODO: Use returnNew so we can reload just one post, and without a extra request
    @Override
    public Post upvotePost(String postId, Upvote upvote) {
        Update update = new Update();
        update.push("upvotes", upvote);
        return mongoTemplate.findAndModify(query(where("_id").is(new ObjectId(postId))), update, Post.class);
    }

    @Override
    public Post removePostUpvote(String postId, String userId) {
        Update update = new Update();
        update.pull("upvotes", new BasicDBObject("userId", userId));
        return mongoTemplate.findAndModify(query(where("_id").is(new ObjectId(postId))), update, Post.class);
    }
}
