/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author ych
 */
package org.foxbpm.engine.impl.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.foxbpm.engine.exception.FixFlowException;
import org.foxbpm.engine.sqlsession.ISqlSession;
import org.foxbpm.engine.sqlsession.ISqlSessionFactory;

public class JdbcSqlSessionFactory implements ISqlSessionFactory {

	private DataSource dataSource;
	public void init(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ISqlSession createSqlSession() {
		Connection connection;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new FixFlowException("jdbc数据库连接获取失败，请检查连接池配置",e);
		}
		return new JdbcSqlSession(connection);
	}
}
