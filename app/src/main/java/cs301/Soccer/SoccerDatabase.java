package cs301.Soccer;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> soccerHash = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String name = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(name)){
            return false;
        }
        else{
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            soccerHash.put(name, newPlayer);
            return true;
        }

    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;
        if(soccerHash.containsKey(findPlayer)){
            soccerHash.remove(findPlayer);
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            return soccerHash.get(findPlayer);
        }
        else{

            return null;
        }

    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpGoals();
            return true;
        }
            return false;

    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpAssists();
            return true;
        }

        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpShots();
            return true;
        }

        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpSaves();
            return true;
        }

        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpFouls();
            return true;
        }

        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpYellowCards();
            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {

        String findPlayer = firstName + "&#&" + lastName;

        if(soccerHash.containsKey(findPlayer)){
            soccerHash.get(findPlayer).bumpRedCards();
            return true;
        }

        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(final String teamName) {
        if(teamName == null){
            return soccerHash.size();
        }
        else{
            int counter = 0;
            Enumeration<SoccerPlayer> players = soccerHash.elements();
            while(players.hasMoreElements()){
                if(players.nextElement().getTeamName().equals(teamName)){
                    counter++;
                }
            }
            return counter;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        Enumeration<SoccerPlayer> players = soccerHash.elements();
        int counter = 0;
        SoccerPlayer testPlayer = null;
            while(players.hasMoreElements()){
                testPlayer = players.nextElement();
                if(teamName == null){
                    if(counter == idx) {
                        return testPlayer;
                    }
                    counter++;
                }
                else{
                    if(testPlayer.getTeamName().equals(teamName)){
                        if(counter == idx){
                            return testPlayer;
                        }
                        counter++;
                    }

                }
            }
            return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        try {
            Scanner sc = new Scanner(file);
            String first = "";
            String last = "";
            String key = "";
            int uniNum = 0;
            int numHolder = 0;
            String teamName = "";
            int counter = 1;

            while(sc.hasNextLine()){
                String holder1 = sc.nextLine();
                if(counter == 1) {
                    first = holder1;
                    counter++;
                }
                else if(counter == 2) {
                    last = holder1;
                    if (getPlayer(first, last) != null) {
                        removePlayer(first, last);
                    }
                    key = first + "&#&" + last;
                    counter++;
                }
                else if(counter == 3) {
                    teamName = holder1;
                    counter++;
                }
                else if(counter == 4) {
                    uniNum = Integer.parseInt(holder1);
                    addPlayer(first, last, uniNum, teamName);
                    counter++;
                }
                else if(counter == 5){
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpGoals();
                    }
                    counter++;
                }
                else if (counter == 6) {
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpAssists();
                    }
                    counter++;
                }
                else if (counter == 7) {
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpShots();
                    }
                    counter++;
                }
                else if(counter == 8) {
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpSaves();
                    }
                    counter++;
                }
                else if(counter == 9) {
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpFouls();
                    }
                    counter++;
                }
                else if(counter == 10) {
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpYellowCards();
                    }
                    counter++;
                }
                else if(counter == 11) {
                    numHolder = Integer.parseInt(holder1);
                    for(int i = 0; i < numHolder; i++) {
                        soccerHash.get(key).bumpRedCards();
                    }
                    counter = 1;
                }
            }
            Enumeration<SoccerPlayer> players = soccerHash.elements();
            return true;
        }
        catch (FileNotFoundException e){
            Log.d("File not found", "File not found exception");
            return false;
        }

    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file)  {
        try {
            String fileName = file.getName();
            PrintWriter pw = new PrintWriter(file);
            Enumeration<SoccerPlayer> players = soccerHash.elements();
            while (players.hasMoreElements()) {
                SoccerPlayer tempPlayer = players.nextElement();
                pw.println(logString(tempPlayer.getFirstName()));
                pw.println(logString(tempPlayer.getLastName()));
                pw.println(logString(tempPlayer.getTeamName()));
                pw.println(logString(Integer.toString(tempPlayer.getUniform())));
                pw.println(logString(Integer.toString(tempPlayer.getGoals())));
                pw.println(logString(Integer.toString(tempPlayer.getAssists())));
                pw.println(logString(Integer.toString(tempPlayer.getShots())));
                pw.println(logString(Integer.toString(tempPlayer.getSaves())));
                pw.println(logString(Integer.toString(tempPlayer.getFouls())));
                pw.println(logString(Integer.toString(tempPlayer.getYellowCards())));
                pw.println(logString(Integer.toString(tempPlayer.getRedCards())));
            }
            return true;
        }
        catch (FileNotFoundException e){
            Log.d("File not found","Caught File not found exception");
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        //Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
