package DBCon;
import java.sql.*;
import java.util.*;
public class DBCon {
	private Connection con;
	private String jdbcName;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	public String dbName; 
	public String userName;
	public String password;
	public String ip;
	public DBCon(String _dbName, String _userName, String _password, String _ip) {
		con = null;
		jdbcName = "com.mysql.cj.jdbc.Driver";
		stmt = null;
		rs = null;
		pstmt = null;
		dbName = _dbName; 
		userName = _userName;
		password = _password;
		ip = _ip;
	}
	public void connect() {
		try {
			Class.forName(jdbcName);
            //"jdbc:mysql://localhost:3306/" + dbName + "?user="+userName+"&password="+password+"&useUnicode=true&characterEncoding=UTF-8";
			//this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?characterEncoding=UTF-8&" + "user="+userName+"&password="+password);
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?user="+userName);
			this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/final?user=test&password=1234&useUnicode=true&characterEncoding=UTF-8");

			System.out.println("connected");
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public ResultSet exec(String sql) {
		try {
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery(sql);
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}
	
	public int checkLogin(String cname, String cpasswd) {
		//0隞�銵函�����
		//1隞�銵典�Ⅳ�隤�
		//2隞�銵冽��董���
		String name=null, passwd=null;
		ResultSet hrs;
		try {
			//DBCon dbc = new DBCon(dbName, userName, password, ip);
			//dbc.connect();
			connect();
			pstmt = con.prepareStatement("SELECT * FROM customer WHERE name = ?;");
			pstmt.setString(1, cname);
			hrs = pstmt.executeQuery();
			while (hrs.next()) {
				name = hrs.getString("name");
				passwd = hrs.getString("passwd");
				System.out.println("correct: " + passwd);

				if(!cpasswd.equals(passwd)) {
					return 1;
				}
			}
			//dbc.close();
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		System.out.println(name);
		System.out.println(passwd);
		if (passwd == null)
			return 2;
		else return 0;
	}
	
	public ResultSet checkReservation(String cname, String cPasswd) {
		String id = null;
		ResultSet hrs = null;
		try {
			connect();
			pstmt = con.prepareStatement("SELECT id FROM customer WHERE name = ? AND passwd = ?;");

			//pstmt = dbc.exec("SELECT id FROM customer WHERE name = ? AND passwd = ?;");
			pstmt.setString(1, cname);
			pstmt.setString(2, cPasswd);
			hrs = pstmt.executeQuery();
			if(hrs.next())
				id = hrs.getString("id");
			pstmt = con.prepareStatement("SELECT * FROM reservation WHERE id = ?;");
			pstmt.setString(1, id);
			hrs = pstmt.executeQuery();

		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return hrs;
	}
	
	public boolean create(String userID, String passwd, String name, String phone, String email) {
		ResultSet hrs = null;
		try {
			connect();
			pstmt = con.prepareStatement("INSERT INTO customer (userID, passwd, name, phone, email) VALUES (?, ?, ?, ?, ?);");
			pstmt.setString(1, userID);
			pstmt.setString(2, passwd);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			System.out.println(userID + " " + passwd + " " + name + " " + phone + " " + email);
			boolean exeCorrect = pstmt.execute();
			System.out.println(exeCorrect);
			return true;
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
	
	public boolean reservation(String cname, String cPasswd, String date, String time, int number) {
		int id = 0;
		ResultSet hrs = null;
		boolean exeCorrect;
		try {
			connect();
			pstmt = con.prepareStatement("SELECT id FROM customer WHERE name = ? AND passwd = ?;");
			pstmt.setString(1, cname);
			pstmt.setString(2, cPasswd);
			System.out.println(cname + " " + cPasswd);

			hrs = pstmt.executeQuery();
			if(hrs.next())
				id = hrs.getInt("id");

			pstmt = con.prepareStatement("INSERT INTO reservation (id, date, time, number) VALUES (?, ?, ?, ?);");
			pstmt.setInt(1, id);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			pstmt.setInt(4, number);
			System.out.println(id + " " + date + " " + time + " " + number);
			exeCorrect = pstmt.execute();
			System.out.println(exeCorrect);
			return true;
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
	
	public void close() {
		try {
			if (rs != null) {
			rs.close();
			rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con!= null) {
				con.close();
				con = null;
			}
		}catch (SQLException ex){
			ex.printStackTrace();
		}
	}
}