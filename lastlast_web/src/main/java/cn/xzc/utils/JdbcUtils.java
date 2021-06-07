package cn.xzc.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	private static DataSource ds;
	
	static {
		ds = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection getConnection() {
		try {
			//���ȵõ���ǰ�߳��ϰ󶨵�����
			Connection conn = tl.get();
			if (conn == null) {
				conn = ds.getConnection();//�����ǰ�߳���û�а�һ�����ӣ�������ݿ����ӳ���һ������
			}
			tl.set(conn);//�����Ӱ󶨵���ǰ�߳���ȥ
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void startTransaction() {
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void commitTransaction() {
		try {
			Connection conn = getConnection();
			if (conn != null) {
				conn.commit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConn() {
		Connection conn = null;
		try {
			conn = getConnection();
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			/*
			if (conn != null) {
				tl.remove();
			}
			*/
			tl.remove();//ǧ��Ҫע�⣬�����ǰ�߳��ϰ󶨵����ӣ���ThreadLocal�������Ƴ�����Ӧ��ǰ�߳��ϵ����ӣ�
		}
	}
	
}