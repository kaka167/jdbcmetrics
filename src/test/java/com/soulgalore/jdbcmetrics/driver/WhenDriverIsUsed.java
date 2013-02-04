package com.soulgalore.jdbcmetrics.driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class WhenDriverIsUsed extends AbstractDriverTest {

	private Connection connection;

	@Before
	public void setup() throws SQLException {
		connection = driver.connect(URL_JDBC_METRICS, null);
	}
	
	@Test
	public void connectionShouldBeProxy() {
		assertThat(connection, instanceOf(Proxy.class));
		assertThat(Proxy.getInvocationHandler(connection), instanceOf(ConnectionInvocationHandler.class));
	}

	@Test
	public void statementShouldBeProxy() throws SQLException {
		Statement statement = connection.createStatement();
		assertThat(statement, instanceOf(Proxy.class));
		assertThat(Proxy.getInvocationHandler(statement), instanceOf(StatementInvocationHandler.class));
	}

	@Test
	public void callableStatementShouldBeProxy() throws SQLException {
		CallableStatement statement = connection.prepareCall("SELECT 1");
		assertThat(statement, instanceOf(Proxy.class));
		assertThat(Proxy.getInvocationHandler(statement), instanceOf(StatementInvocationHandler.class));
	}

	@Test
	public void preparedStatementShouldBeProxy() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT 1");
		assertThat(statement, instanceOf(Proxy.class));
		assertThat(Proxy.getInvocationHandler(statement), instanceOf(StatementInvocationHandler.class));
	}

}
