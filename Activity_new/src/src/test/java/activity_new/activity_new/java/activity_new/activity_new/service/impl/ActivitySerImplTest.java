package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.RoleEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.entity.enumerated.RoleEnumName;
import activity_new.activity_new.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Transient;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class ActivitySerImplTest {

    private UserEntity testUser;
    private RoleEntity adminRole, moderatorRole, userRole;
    private ActivitySerImpl serviceToTest;

    @Mock
    public UserRepository mockUserRepository;

    public UserEntity getTestUser() {
        return testUser;
    }


    @BeforeEach
    public void init() {

        //ARRANGE
        serviceToTest = new ActivitySerImpl(mockUserRepository);

        adminRole = new RoleEntity();
        adminRole.setRole(RoleEnumName.ADMIN);

        userRole = new RoleEntity();
        userRole.setRole(RoleEnumName.USER);

        moderatorRole = new RoleEntity();
        userRole.setRole(RoleEnumName.MODERATOR);

        testUser = new UserEntity();

        testUser.setUsername("Niki");
        testUser.setFirstName("Nikolay");
        testUser.setLastName("Yakimov");
        testUser.setEmail("yakimov099@gmail.com");
        testUser .setPassword("Test1234");
        testUser.setRoles(Set.of(adminRole, userRole, moderatorRole));
    }

    @Test
    public void testUserNotFound() {

        Assertions.assertThrows(
                NullPointerException.class,
                () -> serviceToTest.loadUserByUsername("Nikita")
        );
    }

    @Test
    public void testUserFound() {


        //Arrange
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        //Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());

        //Assert
        String expectedRoles = "ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR";
        String actualRoles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(userDetails.getUsername(), testUser.getUsername());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }
}