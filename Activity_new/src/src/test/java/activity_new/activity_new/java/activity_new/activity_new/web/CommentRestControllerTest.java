package activity_new.activity_new.web;

import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.CommentEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.CommentRepository;
import activity_new.activity_new.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@WithMockUser("Ivan")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {
    private final static String COMMENT_1 = "something";
    private final static String COMMENT_2 = "something else";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity testUser;

    @BeforeEach
    void setup() {
        testUser = new UserEntity();
        testUser.setPassword("password");
        testUser.setUsername("Ivan");
        testUser.setFirstName("Ivan");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        activityRepository.deleteAll();
        userRepository.deleteAll();


    }

    @Test
    void testGetComment() throws Exception {
        long activityId = initActivity();

        mockMvc.perform(get("/api" + activityId + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(COMMENT_1)))
                .andExpect(jsonPath("$.[1].message", is(COMMENT_2)));

    }

    private long initActivity() {
        ActivityEntity testActivity = new ActivityEntity();
        testActivity.setTitle("Test Activity");

        testActivity = activityRepository.save(testActivity);

        CommentEntity testComment1 = new CommentEntity();
        testComment1.setAuthor(testUser);
        testComment1.setCreated(LocalDateTime.now());
        testComment1.setContent(COMMENT_1);
        testComment1.setApproved(true);
        testComment1.setActivity(testActivity);


        CommentEntity testComment2 = new CommentEntity();
        testComment2.setAuthor(testUser);
        testComment2.setCreated(LocalDateTime.now());
        testComment2.setContent(COMMENT_2);
        testComment2.setApproved(true);
        testComment2.setActivity(testActivity);

        testActivity.setCommentEntityList(List.of(testComment1, testComment2));

        return activityRepository.save(testActivity).getId();
    }

}