package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Util.DB;

public class CategoryDB {
	public Category getCategory(String name) {
		Category category = null;
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM category where name = ?");
				pstmt.setString(1, name);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					category = new Category(rs.getString("name"), rs.getBytes("image"));
				}
				
				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return category;
	}
	
	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> categories = new ArrayList<Category>();
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM category ORDER BY 1");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Category category = new Category(rs.getString("name"), rs.getBytes("image"));
					categories.add(category);
				}
				
				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return categories;
	}
}
