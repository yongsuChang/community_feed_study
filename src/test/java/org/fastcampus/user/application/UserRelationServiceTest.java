package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);
        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(requestDto);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        // given
        userRelationService.follow(requestDto);

        // when + then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when + then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserFollowSaved() {
        // given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unfollow(requestDto);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenUnFollow_thenUserThrowError() {
        // when + then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollowSelf_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when + then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }
}
