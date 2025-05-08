package org.fastcampus.user.domain;

public class UserRelationCounter {

    private int count;

    public UserRelationCounter(int count) {
        this.count = count;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (this.count <= 0) {
            return;
        }
        this.count--;
    }
}
