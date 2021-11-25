import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Beer_manager_dao {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String id="jspid";
	String pw="jsppw";
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Beer_manager_dao(){
		
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		}
		
	}//드라이버
	public void getConnect(){

		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		}
	}//getConnect
	public ArrayList<Beer_manager_bean> getAllBeer() {
		getConnect();
		
		ArrayList<Beer_manager_bean>  lists = new ArrayList<Beer_manager_bean>();
		
		String sql = "select * from beer order by num asc";
		try {
			ps = conn.prepareStatement(sql);

		
			rs = ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String nation = rs.getString("nation");
				String style = rs.getString("style");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String inputdate = String.valueOf(rs.getDate("inputdate"));// Date => String

				Beer_manager_bean BMB = new Beer_manager_bean(num,name,nation,style,stock,price,inputdate);
				lists.add(BMB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("lists.size():" + lists.size());
		return lists;
		
	}
	public int insertData(Beer_manager_bean BMB) {
		getConnect();
		int count =0;
		String sql = "insert into beer values(beerseq.nextval,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,BMB.getName());
			ps.setString(2,BMB.getNation());
			ps.setString(3,BMB.getStyle());
			ps.setInt(4, BMB.getStock());
			ps.setInt(5, BMB.getPrice());
			ps.setString(6,BMB.getInputdate());
			
			count=ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return count;
	}
	public int updateData(Beer_manager_bean BMB) {
		getConnect();
		int count=0;
		String sql="update beer set name=?, nation=?, style=?, stock=?,price=?, inputdate=? where num=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,BMB.getName());
			ps.setString(2,BMB.getNation());
			ps.setString(3,BMB.getStyle());
			ps.setInt(4, BMB.getStock());
			ps.setInt(5, BMB.getPrice());
			ps.setString(6,BMB.getInputdate());
			ps.setInt(7,BMB.getNum());
			
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public int deleteData(int num) {
		getConnect();
		int count = 0;
		String sql = "delete from beer where num =?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public ArrayList<Beer_manager_bean> getBeerFromColumn(String word) {
		getConnect();

		ArrayList<Beer_manager_bean>  lists = new ArrayList<Beer_manager_bean>();
		
		String sql = "select * from beer where style like ? " ;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+word+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String nation = rs.getString("nation");
				String style = rs.getString("style");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String inputdate = String.valueOf(rs.getDate("inputdate"));// Date => String

				Beer_manager_bean BMB = new Beer_manager_bean(num,name,nation,style,stock,price,inputdate);
				lists.add(BMB);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("표현식 누락됨");
				e.printStackTrace();
			}
		}

		System.out.println("lists.size():" + lists.size());
		return lists;
	}
	
	
	
}//Beer_manager_dao

