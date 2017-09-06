package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Util.*;

public class ShopDB {
	final static int IMG_WIDTH = 1024;
	final static int IMG_HEIGHT = 600;
	
	public boolean addShop(String name, String contact, String postalcode, String unitno, String category, String description, String encodedimage) {
		boolean added = false;
		
		CategoryDB cdb = new CategoryDB();
		if (cdb.getCategory(category) != null) {
			Connection conn = DB.getConnection();
			if (conn != null) {
				try {
					byte[] image = null;
					if (encodedimage != null) {
						image = util.decodeBase64(encodedimage);
						
						ByteArrayInputStream bis = new ByteArrayInputStream(image);
						Image i = ImageIO.read(bis);
						BufferedImage bufImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
						Graphics g = bufImage.createGraphics();
						g.drawImage(i, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
						g.dispose();
						
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						ImageIO.write(bufImage, "jpg", bos);
						image = bos.toByteArray();
					}
					
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO shop (name,contact,postalcode,unitno,category,description,image) VALUES (?,?,?,?,?,?,?)");
					pstmt.setString(1, name);
					pstmt.setString(2, contact);
					pstmt.setString(3, postalcode);
					pstmt.setString(4, unitno);
					pstmt.setString(5, category);
					pstmt.setString(6, description);
					pstmt.setBytes(7, image);
					added = pstmt.executeUpdate() > 0;
					
					conn.close();
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		};
		
		return added;
	}
	
	public Shop getShop(String n) {
		Shop shop = null;
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM shop WHERE name = ?");
				pstmt.setString(1, n);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String name = rs.getString("name");
					String contact = rs.getString("contact");
					String postalcode = rs.getString("postalcode");
					String unitno = rs.getString("unitno");
					String category = rs.getString("category");
					String description = rs.getString("description");
					byte[] image = rs.getBytes("image");
					
					shop = new Shop(name,contact,postalcode,unitno,category,description,image);
				}
				
				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return shop;
	}
	
	public ArrayList<Shop> getShops(String c) {
		ArrayList<Shop> shops = new ArrayList<Shop>();
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt;

				if (c == null) {
					pstmt = conn.prepareStatement("SELECT * FROM shop");
				} else {
					pstmt = conn.prepareStatement("SELECT * FROM shop WHERE category = ?");
					pstmt.setString(1,c);
				}

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String name = rs.getString("name");
					String contact = rs.getString("contact");
					String postalcode = rs.getString("postalcode");
					String unitno = rs.getString("unitno");
					String category = rs.getString("category");
					String description = rs.getString("description");
					byte[] image = rs.getBytes("image");
					
					Shop shop = new Shop(name,contact,postalcode,unitno,category,description,image);
					shops.add(shop);
				}
				
				conn.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return shops;
	}
}
