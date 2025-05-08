package org.fastcampus.user.domain;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCounter;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.info = userInfo;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }
        followingCounter.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException("You cannot unfollow yourself.");
        }
        followingCounter.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCounter.increase();
    }

    private void decreaseFollowerCount() {
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }
}
