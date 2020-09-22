package dao;


import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.Instagram.Utility.Instagram_UserDefinedException;

import InstagramEntity.InstagramUser;
import java.util.*;

public class Dao_Class implements Dao_Interface {

	@Override
	public int createAccountdao(InstagramUser iu)throws IOException  {
		int i=0;
		Connection conn=null;
		try
		{
		
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		conn = DriverManager.getConnection("jdbc:derby:d:/firstdb2;create=true","anushka","anushka");
		PreparedStatement ps= conn.prepareStatement("insert into employe values(?,?,?,?)");
		ps.setString(1,iu.getName());
		ps.setString(2,iu.getPwd());
		ps.setString(3,iu.getEmail());
		ps.setString(4,iu.getAdd());
		
		i = ps.executeUpdate();
		

		File f1 = new File("C://hsbc//data.txt");
		f1.createNewFile();
		
		FileWriter fout = new FileWriter(f1,true);
		
		fout.write(iu.getName());
		fout.write(iu.getPwd());
		fout.write(iu.getEmail());
		fout.write(iu.getAdd());
		
		fout.close();
		
		}
		catch(ClassNotFoundException|SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return i;
		
	}

	@Override
	public int deleteAccountdao(InstagramUser iu) {
		int i=0;
		Connection conn=null;
		try
		{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		conn = DriverManager.getConnection("jdbc:derby:d:/firstdb2;create=true","anushka","anushka");
		PreparedStatement ps= conn.prepareStatement("Delete from employe where name=?");
		ps.setString(1,iu.getName());
		i = ps.executeUpdate();
		}
		catch(ClassNotFoundException|SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return i;
		
	}

	@Override
	public void viewMsgsdao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InstagramUser viewAccountdao(InstagramUser iu){
		InstagramUser uu=null;
		Connection conn=null;
		
		try
		{
		
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.getConnection("jdbc:derby:d:/firstdb2;create=true","anushka","anushka");
			PreparedStatement ps= conn.prepareStatement("Select * from employe where name=?");
			ps.setString(1,iu.getName());
			java.sql.ResultSet res =  ps.executeQuery();
			uu = new InstagramUser(); 
			if(res.next())
			{
				uu.setName(res.getString(1));
				uu.setPwd(res.getString(2));
				uu.setEmail(res.getString(3));
				uu.setAdd(res.getString(4));
			}
		}
		catch(ClassNotFoundException|SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return uu;
	}

	@Override
	public List<InstagramUser> viewAll(InstagramUser iu)throws Instagram_UserDefinedException{
		List<InstagramUser> l = new ArrayList<InstagramUser>();
		Connection conn=null;
		try
		{
		
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.getConnection("jdbc:derby:d:/firstdb2;create=true","anushka","anushka");
			PreparedStatement ps= conn.prepareStatement("Select * from employe where name=?");
			ps.setString(1,iu.getName());
			java.sql.ResultSet res =  ps.executeQuery();
			InstagramUser uu; 
			
			while(res.next())
				
			{
				uu = new InstagramUser();
				uu.setName(res.getString(1));
				uu.setPwd(res.getString(2));
				uu.setEmail(res.getString(3));
				uu.setAdd(res.getString(4));
				l.add(uu);
			}
			if(l.size()==0)
			{
				throw new Instagram_UserDefinedException();
			}
		}
		catch(ClassNotFoundException|SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return l;
	}



	@Override
	public int Edit(HashMap<String,String> h,InstagramUser iu) {
		int i=0;
		Connection conn=null;
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.getConnection("jdbc:derby:d:/firstdb2;create=true","anushka","anushka");
			Set set = (Set) h.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext())
			{
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				
				//ps.setString(1,(String)mapEntry.getKey());
				//PreparedStatement ps=conn.prepareStatement("");
				String key = (String)mapEntry.getKey();
				if(key.equals("Name"))
				{
					PreparedStatement ps= conn.prepareStatement("Update employe set name=? where name=?");
					ps.setString(1,(String)mapEntry.getValue());
					ps.setString(2,iu.getName());
					iu.setName((String)mapEntry.getValue());
					i = ps.executeUpdate();
				}
				
				if(key.equals("Password"))
				{
					PreparedStatement ps= conn.prepareStatement("Update employe set Password=? where name=?");
					ps.setString(1,(String)mapEntry.getValue());
					ps.setString(2,iu.getName());
					i = ps.executeUpdate();
				}
				
				if(key.equals("Email"))
				{
					PreparedStatement ps= conn.prepareStatement("Update employe set Email=? where name=?");
					ps.setString(1,(String)mapEntry.getValue());
					ps.setString(2,iu.getName());
					i = ps.executeUpdate();
				}
				
				
				if(key.equals("Address"))
				{
					PreparedStatement ps= conn.prepareStatement("Update employe set Address=? where name=?");
					ps.setString(1,(String)mapEntry.getValue());
					ps.setString(2,iu.getName());
					i = ps.executeUpdate();
				}
				
			}
		}
		catch(ClassNotFoundException|SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		
		return i;	
		
	}

	@Override
	public List<InstagramUser> Search(){
		List<InstagramUser> l = new ArrayList<InstagramUser>();
		Connection conn=null;
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.getConnection("jdbc:derby:d:/firstdb2;create=true","anushka","anushka");
			PreparedStatement ps= conn.prepareStatement("Select * from employe");
			java.sql.ResultSet res =  ps.executeQuery();
			InstagramUser uu; 
			
			
			
			
			while(res.next())
				
			{
				uu = new InstagramUser();
				uu.setName(res.getString(1));
				uu.setPwd(res.getString(2));
				uu.setEmail(res.getString(3));
				uu.setAdd(res.getString(4));
				l.add(uu);
			}
			

		}
		catch(ClassNotFoundException|SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	
		return l;
	

	}

	@Override
	public Map<String, List<InstagramUser>> ViewWithListDao() {
		List<InstagramUser> l1 = new ArrayList<InstagramUser>();
		l1 = Search();
		List<InstagramUser> l2 = new ArrayList<InstagramUser>();
		l2 = Search();
		
		Map<String, List<InstagramUser>> m = new HashMap<String, List<InstagramUser>>();
		m.put("StudentList", l1);
		m.put("ProfList", l2);
		
		
		return m;
	}
}
