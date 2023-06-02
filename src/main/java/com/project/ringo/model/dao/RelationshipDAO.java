package com.project.ringo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.ringo.model.dto.Relationship;

public class RelationshipDAO {
    private Connection connection;

    public RelationshipDAO(Connection connection) {
        this.connection = connection;
    }

    public void addRelationship(Relationship relationship) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO relationship (user_id, target_id, follow, block, hide) " +
                         "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, relationship.getUserId());
            preparedStatement.setString(2, relationship.getTargetId());
            preparedStatement.setBoolean(3, relationship.isFollow());
            preparedStatement.setBoolean(4, relationship.isBlock());
            preparedStatement.setBoolean(5, relationship.isHide());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void updateRelationship(Relationship relationship) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE relationship SET follow = ?, block = ?, hide = ? " +
                         "WHERE relationship_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, relationship.isFollow());
            preparedStatement.setBoolean(2, relationship.isBlock());
            preparedStatement.setBoolean(3, relationship.isHide());
            preparedStatement.setInt(4, relationship.getRelationshipId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void deleteRelationship(int relationshipId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM relationship WHERE relationship_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, relationshipId);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public List<Relationship> getRelationshipsByUserId(String userId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM relationship WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();

            List<Relationship> list = new ArrayList<>();
            while (resultSet.next()) {
                Relationship relationship = new Relationship();
                relationship.setRelationshipId(resultSet.getInt("relationship_id"));
                relationship.setUserId(resultSet.getString("user_id"));
                relationship.setTargetId(resultSet.getString("target_id"));
                relationship.setFollow(resultSet.getBoolean("follow"));
                relationship.setBlock(resultSet.getBoolean("block"));
                relationship.setHide(resultSet.getBoolean("hide"));
                list.add(relationship);
            }
            return list;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}