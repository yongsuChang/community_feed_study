package org.fastcampus.user.application;

import org.fastcampus.user.application.interfaces.UserRelationRepository;

public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService, UserRelationRepository userRelationRepository) {
        if(userService == null) {
            throw new IllegalArgumentException("UserService cannot be null");
        }
        if(userRelationRepository == null) {
            throw new IllegalArgumentException("UserRelationRepository cannot be null");
        }
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(Long userId, Long targetUserId) {

        var user = userService.getUser(userId);
        var targetUser = userService.getUser(targetUserId);

        if(userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("You are already following this user.");
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(Long userId, Long targetUserId) {
        var user = userService.getUser(userId);
        var targetUser = userService.getUser(targetUserId);

        if(!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("You are not following this user.");
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }
}
