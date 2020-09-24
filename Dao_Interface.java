package dao;

import java.io.IOException;
import java.util.*;

import com.Instagram.Utility.Instagram_UserDefinedException;

import InstagramEntity.InstagramUser;

public interface Dao_Interface {
	
	public int createAccountdao(InstagramUser iu)throws IOException;
	public InstagramUser viewAccountdao(InstagramUser iu);
	public int deleteAccountdao(InstagramUser iu);
	public void viewMsgsdao();
	List<InstagramUser> viewAll(InstagramUser iu)throws Exception;
	List<InstagramUser> Search() ;
	public int Edit(HashMap<String,String> h,InstagramUser iu);
	public Map<String, List<InstagramUser>> ViewWithListDao();
}
