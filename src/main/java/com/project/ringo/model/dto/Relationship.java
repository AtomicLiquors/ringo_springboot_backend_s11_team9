package com.project.ringo.model.dto;

public class Relationship {
    private int relationshipId;
    private String userId;
    private String targetId;
    private boolean follow;
    private boolean block;
    private boolean hide;

    public Relationship() {}

    public Relationship(int relationshipId, String userId, String targetId, boolean follow, boolean block, boolean hide) {
        this.relationshipId = relationshipId;
        this.userId = userId;
        this.targetId = targetId;
        this.follow = follow;
        this.block = block;
        this.hide = hide;
    }

    public int getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(int relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    @Override
    public String toString() {
        return "RelationshipDTO{" +
                "relationshipId=" + relationshipId +
                ", userId='" + userId + '\'' +
                ", targetId='" + targetId + '\'' +
                ", follow=" + follow +
                ", block=" + block +
                ", hide=" + hide +
                '}';
    }
}