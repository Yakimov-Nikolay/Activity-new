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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class ActivitySerImplTest {

    private UserEntity testUser;

    private ActivitySerImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        //ARRANGE
        serviceToTest = new ActivitySerImpl(mockUserRepository);

        RoleEntity adminRole = new RoleEntity();
        adminRole.setRole(RoleEnumName.ADMIN);

        RoleEntity userRole = new RoleEntity();
        userRole.setRole(RoleEnumName.USER);


        testUser = new UserEntity();
        testUser.setUsername("Niki");
        testUser.setEmail("yakimov099@gmail.com");
        testUser.setPassword("Test1234");
        testUser.setRoles(Set.of(adminRole, userRole));
    }

    @Test
    public void testUserNotFound() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("Peso_not_found")
        );
    }

    @Test
    public void testUserFound() {

        //Arrange
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        //Act
        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        //Assert

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = actual
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }
}