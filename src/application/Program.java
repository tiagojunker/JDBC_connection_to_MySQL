package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			/*
			 * Open a block to protect the transactions, doing them pending until the code
			 * makes the confirmation with commit() function.
			 */
			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			int x = 1;
			if (x < 2) {
				throw new SQLException("Fake Error");
			}

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			// Making the confirmation of transactions
			conn.commit();

			System.out.println("Rows 1: " + rows1);
			System.out.println("Rows 2: " + rows2);

		} catch (SQLException e) {

			try {

				// Call The RollBack Function to Undo the changes made.
				conn.rollback();
				throw new DbException("Transaction Rolled Back! Coused By: " + e.getMessage());

			} catch (SQLException e1) {
				throw new DbException("Error trying to Rolled Back! Coused By: " + e1.getMessage());
			}

		} finally {
			DB.closeStatement(st);
			DB.closeCOnnection();
		}

	}

}
