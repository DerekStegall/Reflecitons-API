package org.basecampcodingacademy.reflections.db;

import org.basecampcodingacademy.reflections.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResponseRepository {
    @Autowired
    public JdbcTemplate jdbc;

    public List<Response> all() {
        return jdbc.query("SELECT id, reflectionId, userName, answers FROM responses", this::mapper);
    }

    public Response create(Response response) {
        return jdbc.queryForObject(
                "INSERT INTO responses (reflectionId, userName) VALUES (?, ?) RETURNING id, reflectionId, userName",
                this::mapper,
                response.reflectionId,
                response.userName

        );
    }

    public Response find() {
        try {
            return jdbc.queryForObject("SELECT id, reflectionId FROM responses WHERE reflectionId = ? LIMIT 1", this::mapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Response update(Response response) {
        return jdbc.queryForObject(
                "UPDATE responses SET userUsername = ? WHERE id = ? RETURNING id, reflectionId",
                this::mapper, response.reflectionId, response.id);
    }

    public void delete(Integer id) {
        jdbc.query("DELETE FROM responses WHERE id = ? RETURNING id, reflectionId", this::mapper, id);
    }

    private Response mapper(ResultSet resultSet, int i) throws SQLException {
        return new Response(
                resultSet.getInt("id"),
                resultSet.getInt("reflectionId"),
                resultSet.getString("userName"),
                resultSet.getString("answers")
        );
    }



}
