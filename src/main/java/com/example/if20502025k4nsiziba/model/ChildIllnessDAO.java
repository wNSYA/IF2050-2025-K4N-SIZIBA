package com.example.if20502025k4nsiziba.model;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChildIllnessDAO {

    public void insertIllness(ChildIllness illness) {
        String sql = "INSERT INTO child_illness (child_id, illness_name, description, date_of_illness) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, illness.getChildId());
            stmt.setString(2, illness.getIllnessName());
            stmt.setString(3, illness.getDescription());
            stmt.setString(4, illness.getDateOfIllness().toString());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        illness.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ChildIllness> getIllnessesByChildId(int childId) {
        List<ChildIllness> list = new ArrayList<>();
        String sql = "SELECT * FROM child_illness WHERE child_id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, childId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(buildIllnessFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteIllness(int id) {
        String sql = "DELETE FROM child_illness WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ChildIllness buildIllnessFromResultSet(ResultSet rs) throws SQLException {
        return new ChildIllness(
                rs.getInt("id"),
                rs.getInt("child_id"),
                rs.getString("illness_name"),
                rs.getString("description"),
                LocalDate.parse(rs.getString("date_of_illness"))
        );
    }
}