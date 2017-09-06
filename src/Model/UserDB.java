//Group members: Zhong Yi, Clement, Leyond

package Model;

import Util.*;

import java.sql.*;

public class UserDB {
	public boolean addUser(String username, String password, String salt) {
		boolean result = false;
		
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUES (?,?,?)");
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				pstmt.setString(3, salt);
				result = pstmt.executeUpdate() > 0;
				
				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}

	public boolean updateUser(User user) {
		boolean result = false;

		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET password = ?, salt = ? WHERE username = ?");
				pstmt.setString(1,user.getPassword());
				pstmt.setString(2,util.asHex(user.getSalt()));
				pstmt.setString(3,user.getUsername());
				result = pstmt.executeUpdate() > 0;

				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return result;
	}
	
	public User getUser(String username, String password) {
		User user = null;
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
				pstmt.setString(1, username);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String uname = rs.getString("username");
					String pw = rs.getString("password");
					byte[] salt = util.hexStringToByteArray(rs.getString("salt"));

					boolean pass = true;
					if (password != null) {
						String hpass = util.hash(password,salt);
						if (!hpass.equals(pw)) {
							pass = false;
						}
					}

					if (pass) {
						user = new User(uname,pw,salt);
					}
				}
				
				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return user;
	}
}
