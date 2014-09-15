package br.com.machado.pedro.ivo.dao;

import br.com.machado.pedro.ivo.dao.generic.SimpleDAO;
import br.com.machado.pedro.ivo.entity.beans.generic.Country;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;
import br.com.machado.pedro.ivo.entity.mock.SimpleEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by pedivo on 30/08/14.
 * <p/>
 * <p/>
 * -- DATABASE SCRIPT --
 * CREATE TABLE `SIMPLE_ENTITY` (
 * `id` int(11) NOT NULL,
 * `firstname` char(20) DEFAULT NULL,
 * `lastname` char(30) DEFAULT NULL,
 * `birthday` datetime DEFAULT NULL,
 * `city` char(10) DEFAULT NULL,
 * `email` char(50) DEFAULT NULL,
 * `indexedCountry` char(2) DEFAULT NULL,
 * `notIndexedCountry` char(2) DEFAULT NULL,
 * PRIMARY KEY (`id`),
 * KEY `INDEXED_COUNTRY` (`indexedCountry`) USING BTREE
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class SimpleDAOMariaDB extends SimpleDAO {

		private              Connection conn   = null;

		// Database credentials
		private static final String USER = "root";
		private static final String PASS = "q1w2e3";
		private static final String     DB_URL = "jdbc:mysql://172.16.83.4:3306/BENCHMARKER?useUnicode=true&characterEncoding=UTF-8";
		private static final String     DB_URL_NO_SCHEMA = "jdbc:mysql://172.16.83.4:3306/";
		private static final Logger     LOGGER = LoggerFactory.getLogger(SimpleDAOMariaDB.class);

		@Override
		public Long save(SimpleEntity entity) {
				try {
						String sql = "INSERT INTO SIMPLE_ENTITY " +
								" (id, firstname, lastname, birthday, city, email, indexedCountry, notIndexedCountry) VALUES" +
								" (?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setLong(1, entity.getId());
						preparedStatement.setString(2, entity.getFirstname());
						preparedStatement.setString(3, entity.getLastname());
						preparedStatement.setDate(4, new Date(entity.getBirthday().getTime()));
						preparedStatement.setString(5, entity.getCity());
						preparedStatement.setString(6, entity.getEmail());
						preparedStatement.setString(7, entity.getIndexedCountry().toString());
						preparedStatement.setString(8, entity.getNotIndexedCountry().toString());

						long startTime = System.nanoTime();

						preparedStatement.executeUpdate();

						return System.nanoTime() - startTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[save] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override
		public String getEngine() {
				return "MARIADB";
		}

		@Override public Long countByIndexedCountry(Country country) {
				try {
						String sql = "SELECT count(1) FROM SIMPLE_ENTITY WHERE indexedCountry = ?";
						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setString(1, country.toString());

						long startTime = System.nanoTime();

						ResultSet rs = preparedStatement.executeQuery();

						long totalTime = System.nanoTime() - startTime;
						while (rs.next()) {
								super.result = rs.getLong(1);
						}
						// close ResultSet rs
						rs.close();

						return totalTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[countByIndexedCountry] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long countByNonIndexedCountry(Country country) {
				try {
						String sql = "SELECT count(1) FROM SIMPLE_ENTITY WHERE notIndexedCountry = ?";
						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setString(1, country.toString());

						long startTime = System.nanoTime();

						ResultSet rs = preparedStatement.executeQuery();

						long totalTime = System.nanoTime() - startTime;
						while (rs.next()) {
								super.result = rs.getLong(1);
						}
						// close ResultSet rs
						rs.close();

						return totalTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[countByNonIndexedCountry] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByIndexedCountry(Country country) {
				try {
						String sql = "SELECT * FROM SIMPLE_ENTITY WHERE indexedCountry = ?";
						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setString(1, country.toString());

						long startTime = System.nanoTime();

						ResultSet rs = preparedStatement.executeQuery();

						long totalTime = System.nanoTime() - startTime;

						// close ResultSet rs
						rs.close();

						return totalTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[selectByIndexedCountry] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country) {
				try {
						String sql = "SELECT * FROM SIMPLE_ENTITY WHERE notIndexedCountry = ?";
						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setString(1, country.toString());

						long startTime = System.nanoTime();

						ResultSet rs = preparedStatement.executeQuery();

						long totalTime = System.nanoTime() - startTime;

						// close ResultSet rs
						rs.close();

						return totalTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[selectByNonIndexedCountry] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public SimpleEntity findById(Long id) {
				try {
						String sql = "SELECT * FROM SIMPLE_ENTITY WHERE id = ?";
						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setLong(1, id);

						ResultSet rs = preparedStatement.executeQuery();

						SimpleEntityImpl entity = new SimpleEntityImpl();

						while (rs.next()) {
								entity.setId(rs.getLong("id"));
								entity.setBirthday(rs.getDate("birthday"));
								entity.setCity(rs.getString("city"));
								entity.setEmail(rs.getString("email"));
								entity.setFirstname(rs.getString("firstname"));
								entity.setLastname(rs.getString("lastname"));
								entity.setIndexedCountry(Country.valueOf(rs.getString("indexedCountry")));
								entity.setNotIndexedCountry(Country.valueOf(rs.getString("notIndexedCountry")));
						}

						// close ResultSet rs
						rs.close();

						return entity;
				}
				catch (SQLException e) {
						LOGGER.error("Method[findById] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return null;
		}

		@Override public Long update(SimpleEntity entity) {
				try {
						String sql = "UPDATE SIMPLE_ENTITY set firstname = ?, lastname = ?, birthday = ?, city = ?, email = ?, " +
								"indexedCountry = ?, notIndexedCountry = ? WHERE id = ?";

						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setString(1, entity.getFirstname());
						preparedStatement.setString(2, entity.getLastname());
						preparedStatement.setDate(3, new Date(entity.getBirthday().getTime()));
						preparedStatement.setString(4, entity.getCity());
						preparedStatement.setString(5, entity.getEmail());
						preparedStatement.setString(6, entity.getIndexedCountry().toString());
						preparedStatement.setString(7, entity.getNotIndexedCountry().toString());
						preparedStatement.setLong(8, entity.getId());

						long startTime = System.nanoTime();

						preparedStatement.executeUpdate();

						return System.nanoTime() - startTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[update] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long deleteById(Long id) {

				try {
						String sql = "DELETE FROM SIMPLE_ENTITY WHERE id = ?";

						PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
						preparedStatement.setLong(1, id);

						long startTime = System.nanoTime();

						preparedStatement.executeUpdate();

						return System.nanoTime() - startTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[update] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByIndexedCountry(Country country, int offset, int pagesize) {
				try {
						StringBuffer sqlSB = new StringBuffer("SELECT * FROM SIMPLE_ENTITY WHERE indexedCountry = ? LIMIT ");
						sqlSB.append(pagesize);
						sqlSB.append(" OFFSET ");
						sqlSB.append(offset);

						PreparedStatement preparedStatement = getConnection().prepareStatement(sqlSB.toString());
						preparedStatement.setString(1, country.toString());

						long startTime = System.nanoTime();

						ResultSet rs = preparedStatement.executeQuery();

						long totalTime = System.nanoTime() - startTime;

						// close ResultSet rs
						rs.close();

						return totalTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[selectByIndexedCountry] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public Long selectByNonIndexedCountry(Country country, int offset, int pagesize) {
				try {
						StringBuffer sqlSB = new StringBuffer("SELECT * FROM SIMPLE_ENTITY WHERE notIndexedCountry = ? LIMIT ");
						sqlSB.append(pagesize);
						sqlSB.append(" OFFSET ");
						sqlSB.append(offset);

						PreparedStatement preparedStatement = getConnection().prepareStatement(sqlSB.toString());
						preparedStatement.setString(1, country.toString());

						long startTime = System.nanoTime();

						ResultSet rs = preparedStatement.executeQuery();

						long totalTime = System.nanoTime() - startTime;

						// close ResultSet rs
						rs.close();

						return totalTime;
				}
				catch (SQLException e) {
						LOGGER.error("Method[selectByIndexedCountry] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				return 0l;
		}

		@Override public boolean isConnected() {
				try {
						return getConnection().isValid(10);
				}
				catch (SQLException e) {
						LOGGER.error("Method[isConnected] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				reconnect();
				return true;
		}

		@Override public void reconnect() {

				try {
						getConnection().close();
						connect();
				}
				catch (SQLException e) {
						LOGGER.error("Method[reconnect] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		@Override public void close() {
				try {
						getConnection().close();
				}
				catch (SQLException e) {
						LOGGER.error("Method[close] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		private Connection getConnection() {
				if (conn == null) {
						connect();
				}

				return conn;
		}

		private synchronized void connect() {
				try {
						Class.forName("com.mysql.jdbc.Driver");
						createSchema();
						conn = DriverManager.getConnection(DB_URL, USER, PASS);
				}
				catch (ClassNotFoundException e) {
						LOGGER.error("Method[connect] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
				catch (SQLException e) {
						if (e.getErrorCode() == 1049) {
								createSchema();
								connect();
						}
						LOGGER.error("Method[connect] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}

		private void createSchema() {
				try {
						conn = DriverManager.getConnection(DB_URL_NO_SCHEMA, USER, PASS);
						Statement statement = this.conn.createStatement();
						int r = statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS  `BENCHMARKER` DEFAULT CHARACTER SET utf8 ;");
						r = statement.executeUpdate("CREATE TABLE IF NOT EXISTS `BENCHMARKER`.`SIMPLE_ENTITY` ("
								+ " `id` int(11) NOT NULL,"
								+ " `firstname` char(20) DEFAULT NULL,"
								+ " `lastname` char(30) DEFAULT NULL,"
								+ " `birthday` datetime DEFAULT NULL,"
								+ " `city` char(10) DEFAULT NULL,"
								+ " `email` char(50) DEFAULT NULL,"
								+ " `indexedCountry` char(2) DEFAULT NULL,"
								+ " `notIndexedCountry` char(2) DEFAULT NULL,"
								+ " PRIMARY KEY (`id`),"
								+ " KEY `INDEXED_COUNTRY` (`indexedCountry`) USING BTREE"
								+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8");
						conn.close();
				}
				catch (Exception e) {
						LOGGER.error("Method[createSchema] SQL Exception m[{}] stack[{}]", e.getMessage(), e.getStackTrace());
				}
		}
}
