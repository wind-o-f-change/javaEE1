package com.inno.dao;

import com.inno.ConnectionManager.ConnectionManager;
import com.inno.ConnectionManager.Myconnect;
import com.inno.pojo.Mobile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@EJB
public class MobileDaoJdbcImpl implements MobileDao {
    private static final Logger LOGGER                 = LoggerFactory.getLogger(MobileDaoJdbcImpl.class);
    public static final  String SELECT_FROM_MOBILE     = "SELECT * FROM mobile WHERE id = ?";
    public static final  String SELECT_ALL_FROM_MOBILE = "SELECT * FROM mobile";
    public static final  String CREATE_TABLE_MOBILE
                                                       = "DROP TABLE IF EXISTS mobile;\n"
                                                         + "create table mobile\n"
                                                         + "(\n"
                                                         + "    id bigserial not null\n"
                                                         + "        constraint mobile_pkey\n"
                                                         + "            primary key,\n"
                                                         + "    model varchar(100) not null,\n"
                                                         + "    price integer not null,\n"
                                                         + "    manufacturer varchar(100) not null\n"
                                                         + ");\n"
                                                         + "\n"
                                                         + "INSERT INTO mobile (model, price, manufacturer)\n"
                                                         + "VALUES\n"
                                                         + "   ('P1', 100, 'Xiaomi'),\n"
                                                         + "   ('EDGE', 1, 'Micro'),\n"
                                                         + "   ('FRY1', 1001, 'Apple'),\n"
                                                         + "   ('FRY1', 1002, 'Apple'),\n"
                                                         + "   ('OGO', 10000, 'SAMSUNG');"
                                                         + "\n";


    private final ConnectionManager connectionManager;

    @Inject
    public MobileDaoJdbcImpl(@Myconnect ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        createTable();
    }

    @Override
    public Mobile getMobileById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_MOBILE)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Mobile(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getMobileById method", e);
        }
        return null;
    }

    @Override
    public void createTable() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_MOBILE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in createTable method", e);
        }
    }

    @Override
    public List<Mobile> getAllMobile() {
        List<Mobile> lstmb = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_MOBILE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                lstmb.add(new Mobile(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }
            return lstmb;
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getMobileById method", e);
        }
        return new ArrayList<>();
    }
}
