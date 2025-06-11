package com.example.if20502025k4nsiziba.model;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChildDAO {

    public void insertChild(Child child) {
        String sql = """
    INSERT INTO child (name, gender, birth_date, height, weight,
    head_circumference, hand_circumference, abdominal_circumference, date_added, user_id)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
""";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, child.getName());
            stmt.setBoolean(2, child.isGender());
            stmt.setString(3, child.getBirthDate().toString());
            stmt.setFloat(4, child.getHeight());
            stmt.setFloat(5, child.getWeight());
            stmt.setFloat(6, child.getHeadCircumference());
            stmt.setFloat(7, child.getHandCircumference());
            stmt.setFloat(8, child.getAbdominalCircumference());
            stmt.setString(9, child.getDateAdded().toString());
            stmt.setInt(10, child.getUserId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        child.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Child getChildById(int id) {
        String sql = "SELECT * FROM child WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return buildChildFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Child> getAllChildrenByUser(int userId) {
        List<Child> list = new ArrayList<>();
        String sql = "SELECT * FROM child WHERE user_id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(buildChildFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateChild(Child child) {
        String sql = """
                UPDATE child SET
                    name = ?, gender = ?, birth_date = ?, height = ?, weight = ?,
                    head_circumference = ?, hand_circumference = ?, abdominal_circumference = ?, date_added = ?, user_id = ?
                WHERE id = ?;
            """;
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, child.getName());
            stmt.setBoolean(2, child.isGender());
            stmt.setString(3, child.getBirthDate().toString());
            stmt.setFloat(4, child.getHeight());
            stmt.setFloat(5, child.getWeight());
            stmt.setFloat(6, child.getHeadCircumference());
            stmt.setFloat(7, child.getHandCircumference());
            stmt.setFloat(8, child.getAbdominalCircumference());
            stmt.setString(9, child.getDateAdded().toString());
            stmt.setInt(10, child.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChild(int id) {
        String sql = "DELETE FROM child WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Child buildChildFromResultSet(ResultSet rs) throws SQLException {
        return new Child(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBoolean("gender"),
                LocalDate.parse(rs.getString("birth_date")),
                rs.getFloat("height"),
                rs.getFloat("weight"),
                rs.getFloat("head_circumference"),
                rs.getFloat("abdominal_circumference"),
                rs.getFloat("hand_circumference"),
                LocalDate.parse(rs.getString("date_added")),
                rs.getInt("user_id")
        );
    }
}
