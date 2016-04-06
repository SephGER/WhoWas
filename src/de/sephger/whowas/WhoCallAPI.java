package de.sephger.whowas;
//import java.text.SimpleDateFormat;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import java.util.Date;
import org.bukkit.ChatColor;

public class WhoCallAPI {
	
	
	public static String getPlayerUUID(String PlayerName)
	{
		String uuid="";	
		String jurl ="{\"id\":\"NULL\",\"name\":\"NULL\"}";
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		try {
			jurl = WhoStuff.readUrl("https://api.mojang.com/users/profiles/Minecraft/"+PlayerName);	
			} catch (Exception e) {
			// TODO Auto-generated catch block
			jurl = ChatColor.DARK_RED+"Error - No connection to Server";
		}
		
		try{
		Map<String, String> myMap = gson.fromJson(jurl, type);
		uuid = myMap.get("id");	
		}
		catch (Exception e){
		uuid = ChatColor.RED+"Error - User not found";
		}
		
		return uuid;
	}
	
	public static NameHist[] getPlayerHistory(String PlayerName){
		String jurl="[{\"name\":\"NULL\"}]";
		Gson gson = new Gson();
		
		try {
			jurl = WhoStuff.readUrl("https://api.mojang.com/user/profiles/"+getPlayerUUID(PlayerName)+"/names");	
			} catch (Exception e) {
			// TODO Auto-generated catch block
				jurl="[{\"name\":\"Error while getting URL\"}]";
		}
		
		if (jurl.equals("")){jurl="[{\"name\":\""+ChatColor.RED+"User not found\"}]";}
		//history=jurl;

		NameHist[] nhist = gson.fromJson(jurl, NameHist[].class);
		String date="";
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date myDate = new Date();
		for(int i=0;i<nhist.length;i++)
		{
			try{
				if(nhist[i].changedToAt>0){
			myDate=new Date(nhist[i].changedToAt);
			date=myDate.toString();
				}else{date=ChatColor.GREEN+"Not Changed"+ChatColor.RESET;}
			}catch (Exception e){date="Error";}
		  nhist[i].date=date;
		}
		return nhist;
		}
	
	public class NameHist
	{
		int id;
		String name;
		long changedToAt;
		String date;
		
		public void putHist(NameHist hist,Integer uid, String uname, long uchangedToAt)
		{
		 hist.id=uid;
		 hist.name=uname;
		 hist.changedToAt=uchangedToAt;
		Date myDate = new Date(hist.changedToAt);
		hist.date=myDate.toString();
		}
  
	}
	

public static String getHistoryStr(String UserName){
		String historys="";
		String date="";
		NameHist[] nhist = getPlayerHistory(UserName);
		Date myDate = new Date();
		for(int i=0;i<nhist.length;i++)
		{
			try{
				if(nhist[i].changedToAt>0){
			myDate=new Date(nhist[i].changedToAt);
			date=myDate.toString();
				}else{date="Not Changed";}
			}catch (Exception e){date="Error";}
		  historys+="\n"+nhist[i].name+" - "+date;
		  nhist[i].date=date;
		}
		return historys;
		
	}
	

	
	
}

