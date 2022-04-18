package activity_new.activity_new.web;

import activity_new.activity_new.model.binding.AddCommentBindingModel;
import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.CommentEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.CommentRepository;
import activity_new.activity_new.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("Ivan")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {
    private final static String COMMENT_1 = "Hey Spring is super!";
    private final static String COMMENT_2 = "something else here";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
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
    void testGetComments() throws Exception {
        var activity = initComments(initActivity());

        mockMvc.perform(get("/api" + activity.getId() + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(COMMENT_1)))
                .andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComment() throws Exception {

        AddCommentBindingModel testComment = new AddCommentBindingModel()
                .setMessage(COMMENT_1);

        var emptyActivity = initActivity();

        mockMvc.perform(
                post("/api/" + emptyActivity.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api" + emptyActivity.getId()+"/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }

    private ActivityEntity initActivity() {
        ActivityEntity testActivity = new ActivityEntity();
        testActivity.setTitle("Test Activity");

        return activityRepository.save(testActivity);
    }

    private ActivityEntity initComments(ActivityEntity activity) {


        CommentEntity testComment1 = new CommentEntity();
        testComment1.setAuthor(testUser);
        testComment1.setCreated(LocalDateTime.now());
        testComment1.setContent(COMMENT_1);
        testComment1.setApproved(true);
        testComment1.setActivity(activity);


        CommentEntity testComment2 = new CommentEntity();
        testComment2.setAuthor(testUser);
        testComment2.setCreated(LocalDateTime.now());
        testComment2.setContent(COMMENT_2);
        testComment2.setApproved(true);
        testComment2.setActivity(activity);

        activity.setCommentEntityList(List.of(testComment1, testComment2));

        return activityRepository.save(activity);
    }

}