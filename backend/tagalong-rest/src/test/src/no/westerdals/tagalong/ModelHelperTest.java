package no.westerdals.tagalong;

import no.westerdals.tagalong.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ModelHelperTest {

    @Test
    public void testValueMerge() throws Exception {
        Post sourcePost = new Post();
        sourcePost.setTitle("title");
        Post toMerge = new Post();
        toMerge.setContent("content");
        Post expected = new Post();
        expected.setTitle("title");
        expected.setContent("content");
        assertEquals(expected, ModelHelper.mapObjects(sourcePost, toMerge, Post.class));
    }

    @Test
    public void testValueOverwrite() throws Exception {
        Post sourcePost = new Post();
        sourcePost.setTitle("title");
        sourcePost.setContent("content");
        Post toMerge = new Post();
        toMerge.setContent("overwritten");
        Post expected = new Post();
        expected.setTitle("title");
        expected.setContent("overwritten");
        assertEquals(expected, ModelHelper.mapObjects(sourcePost, toMerge, Post.class));
    }

    @Test
    public void testSourceObjectChanges() throws Exception {
        Post sourcePost = new Post();
        sourcePost.setTitle("title");
        sourcePost.setContent("content");
        Post toMerge = new Post();
        toMerge.setContent("overwritten");
        assertSame(sourcePost, ModelHelper.mapObjects(sourcePost, toMerge, Post.class));
    }
}