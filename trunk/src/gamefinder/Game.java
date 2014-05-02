package gamefinder;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Date;
import java.util.logging.Logger;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

//import com.google.gwt.maps.client.impl.LatLngImpl;



@Entity
public class Game<LatLng> implements Comparable<Game>{
    @Id private Long id;
    private String sport;

    private double[] location = new double[2];
    private String locationName;
    private String locationAddress;
    private int hour_s;
    private int min_s;
    private String ampm_s;
    private int hour_e;
    private int min_e;
    private String ampm_e;
    private int numPlayers;
    private int gameIndex;
    private int maxPlayers;
    private ArrayList<String> emailList;
    private Date date=new Date();
    private int year=date.getYear()+1900;
    private int month=date.getMonth();
    private int day=date.getDay();
    private boolean massEmailSent;
    
    public Game(){
    	emailList = new ArrayList<String>();
    	massEmailSent = false;
    }
    public void updateDate(){
    	this.date.setYear(year+1900);
    	this.date.setMonth(month);
    	this.date.setDate(day);
    }
    public Date getDate(){
    	return date;
    }
    public int getYear(){
    	return year;
    }
    public void setYear(int Year){
    	this.year=Year;
    	updateDate();
    }
    public int getMonth(){
            return month;
    }
    public void setMonth(int Month){
    	this.month=Month;
    	updateDate();
    }
    public int getDay(){
    	return day;
    }
    public void setDay(int Day){
    	this.day=Day;
    	updateDate();
    }
    public String getSport(){
            return sport;
    }
    public Long getID(){
    	return id;
    }
    
    public void setIndex(int index){
            this.gameIndex = index;
    }
    public int getIndex(){
            return gameIndex;
    }
    public void setStartTime(int hour, int min, String AMPM){
        this.hour_s = hour;
        this.min_s = min;
        this.ampm_s = AMPM;
    }
    public String getStartTime(){
    	String startTime = String.format("%d:%02d %s", hour_s, min_s, ampm_s);
        return startTime;
    }
    
    public void setNumPlayers(int numPlayers){
            this.numPlayers = numPlayers;
            
    }
    public void setMaxPlayers(int max){
    	this.maxPlayers=max;
    }
    public int getMaxPlayers(){
    	return maxPlayers;
    }
    public int getNumPlayers(){
            return numPlayers;
    }
    
    public void joinGame(){
            this.numPlayers += 1;
    }
    
    public void setEndTime(int hour, int min, String AMPM){
            this.hour_e = hour;
            this.min_e = min;
            this.ampm_e = AMPM;
    }
        
    public String getEndTime(){
    	String endTime = String.format("%d:%02d %s", hour_e, min_e, ampm_e);
        return endTime;
    }
	
	public void setSport(String sport){
		this.sport = sport;
	}

    public double[] getLocation(){    
            return location;
    }
    
    public double getLat(){
    	return location[0];
    }
    
    public double getLng(){
    	return location[1];
    }
    
    public void setLocation(double lat, double lng){
    	location[0] = lat;
    	location[1] = lng;
    }
    
    public void setLocationName(String name){
    	locationName = name;
    }
    
    public String getLocationName(){
    	return locationName;
    }
    
    public void setLocationAddress(String address){
    	locationAddress = address;
    }
	
    public String getLocationAddress(){
    	return locationAddress;
    }

	@Override
	public int compareTo(Game o) {
		if (this.id == (o.getID())) {
            return 1;
        } else if (this.id != (o.getID())) {
            return -1;
        }
		return 0;
	}
	
	public ArrayList<String> getEmailList(){
		return emailList;
	}
	
	public boolean addEmail(String s){
		if(!emailList.contains(s))
			return emailList.add(s);
		else{
			return false;
		}
	}
	
	public void sendEmails(){
		if(!massEmailSent){
			EmailSender.sendEmail(this);
			massEmailSent = true;
		}
	}
	public void sendSingleEmail(String address) {
		if(addEmail(address)){
			EmailSender.sendSingleEmail(this, address);
		}
	}
	public void removeEmail(String address) {
		this.emailList.remove(address);
	}
	public boolean isSubscribed(String address){
		Logger _log = Logger.getLogger(GameServlet.class.getName());
		_log.info(address);
		if (emailList.contains(address))
			_log.info("true!");
		else{
			_log.info("false!");
			for(String email : emailList){
				_log.info(email);
			}
		}
		return emailList.contains(address);
	}
 	
}
