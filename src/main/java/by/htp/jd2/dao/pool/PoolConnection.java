package by.htp.jd2.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class PoolConnection {
	private static final Logger log = Logger.getLogger(PoolConnection.class);

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	private BlockingQueue<Connection> connections;
	private static final PoolConnection instanse = new PoolConnection();

	private PoolConnection() {
	}
	
	public void makePoolConnection() {
		
		BasicConfigurator.configure();
		DBResourceManager dBResourceManager = DBResourceManager.getInstance();
		driverName = dBResourceManager.getValue(DBParameter.DB_DRIVER);
		url = dBResourceManager.getValue(DBParameter.DB_URL);
		user = dBResourceManager.getValue(DBParameter.DB_USER); 
		password = dBResourceManager.getValue(DBParameter.DB_PASSWORD);
		
		try {
			this.poolSize = Integer.parseInt(dBResourceManager.getValue(DBParameter.DB_POLL_SIZE));
		} catch (NumberFormatException e) {
			
			log.log(Level.ERROR, "NumberFormatException", e);
			poolSize = 5;
		}

		try {
			Class.forName(driverName);
			connections = new ArrayBlockingQueue<Connection>(poolSize);
			
			for (int i = 0; i < poolSize; i++) {
				Connection con = DriverManager.getConnection(url, user, password);
				connections.add(con);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		log.info("con is made");
	}
	
	public Connection take() throws InterruptedException {

		return connections.take();
	}

	public void release(Connection con) {
		try {
			if (con != null) {
				con.setAutoCommit(true);
				connections.add(con);
			} else {
				log.info("con == null");
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public static PoolConnection getInstance() {
		return instanse;
	}
	
	public void closeConnectionInPool() {
	
		Iterator <Connection> iter =  connections.iterator();
		
		while(iter.hasNext()) {
			try {
				
				iter.next().close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		log.info("con is closed");
	}
	

}


