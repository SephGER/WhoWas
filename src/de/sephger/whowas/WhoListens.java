package de.sephger.whowas;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WhoListens implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// TODO Auto-generated method stub
		if(args.length>0){
		if(args[0].equals("is")){
		 if(args.length>1){
		sender.sendMessage("UUID of "+args[1]+": "+WhoCallAPI.getPlayerUUID(args[1]));	
		return true;
		 }
		sender.sendMessage("Your UUID: "+WhoCallAPI.getPlayerUUID(sender.getName())); 
		return true;
		}
		if(args[0].equals("was")){
			if(args.length>1){
		sender.sendMessage("History of "+args[1]+":"+WhoCallAPI.getHistoryStr(args[1]));
		return true;
			}
		sender.sendMessage("Your History: "+WhoCallAPI.getHistoryStr(sender.getName()));	
		return true;
		}
		}
		return false;
	}
	
	
	
}