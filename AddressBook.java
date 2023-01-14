import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


public class AddressBook {
     //creating the map

     Map<String, String> personPhone = new HashMap<>();
     Map<String, String> personCell = new HashMap<>();
     Map<String, String> personEmail = new HashMap<>();
     Map<String, String> personData = new HashMap<>();
     Map<String, Integer>personAge = new HashMap<>();

     /**
     * Sets the person's primary phone number in the address book
     * @param personName
     * @param phoneNumber
     */

    public void setPersonPhoneNumber(String personName, String phone){
    	personPhone.put(personName, phone);
      //System.out.println("set name and phone: " + personName + phone); 	
    }

    public String getPersonPhoneNumber(String personName){
        String phone1=personPhone.get(personName);
      //System.out.println("get name and phone: " + personName + phone1);   
      	return phone1;
    }

    /**
     * Sets a phone number for the person in the address book
     * The phoneType is arbitrary.
     *
     * @param personName
     * @param phoneNumber
     */
  
    public void setPersonPhoneNumber(String personName, String phoneType, String phoneNumber){
       String keyPersonPhone = personName + "." + phoneType;
       personCell.put(keyPersonPhone, phoneNumber);
       //System.out.println("set name and cell: " + keyPersonPhone + phoneNumber); 
    }

    public String getPersonPhoneNumber(String personName, String phoneType){ 
    	String keyPersonPhone = personName + "." + phoneType;
        String Cell1 = personCell.get(keyPersonPhone);
       //System.out.println("get name and cell: " + keyPersonPhone + Cell1);
        return Cell1;
    }

    /**
     * Sets the person's email address
     * @param personName
     * @param email
     */
    
    public void setPersonEmail(String personName, String email){
    	personEmail.put(personName, email);
     //System.out.println("set name and email: " + personName + email);
    }

    public String getPersonEmail(String personName){
    	String Email1 = personEmail.get(personName);
     //System.out.println("get name and email: " + personName + Email1);
    	return Email1;
    }

    /**
     * Adds a note about the person where each person can only have one note of each type.
     * Each note has a type to identify what it represents.
     * @param personName
     * @param noteType
     * @param data
     */
  
    public void setPersonNote(String personName, String noteType, String data){
    	String keyNameAndData = personName + "." + noteType;     
        personData.put(keyNameAndData, data);
       //System.out.println("set name and note:  " + keyNameAndData + data);
    }

    public String getPersonNote(String personName, String noteType){
    	String keyNameAndData = personName + "." + noteType;
        String theNote = personData.get(keyNameAndData);
      //System.out.println("get name and note:  " + theNote);
        return theNote;
    }

    /**
     * Sets the age of the person
     * @param personName
     * @param age
     */
  
    public void setPersonAge(String personName, int age){
    	personAge.put(personName, age);
   	    //System.out.println("set name and age:  " + age + "," + personName);
    }

    public Integer getPersonAge(String personName){
    	Integer Age1 = personAge.get(personName); 
       //System.out.println("get name and age:" + personName + "," + Age1);
    	return Age1;
    }

    /**
     * Return the list of names of everyone in the address book
     * @return
     */

    public String[] listNames(){
    	String[] all = personPhone.keySet().toArray(new String[0]);
    	System.out.println("All" + all);
    	return all;
    }

    /**
     * Returns a list of the names that start with the specified prefix.
     * If the prefix is null then all of the names are returned.
     * @param prefix
     * @return
     */
    
    public String keyToName(String key){
        String name = key;
        int dot = key.indexOf(".");
        if(dot > 0){
            name = name.substring(0,dot);
        }
        return name;
    }

    public List namesThatStartWith(String prefix){
        Set<String> allNames = new HashSet<>();
        allNames.addAll(personPhone.keySet());
        allNames.addAll(personCell.keySet());
        allNames.addAll(personEmail.keySet());
        allNames.addAll(personData.keySet());
        allNames.addAll(personAge.keySet());
        allNames = allNames.stream().map(this::keyToName).collect(Collectors.toSet()); // Converts the keys so that the ".cell" etc. parts are removed

        List<String> results = new ArrayList<String>();

        if (prefix != null) {
            for (String name : allNames) {
                if (name.startsWith(prefix)) {
                    results.add(name);//append the name to the list
                }
            }
        }
        else {
            return new ArrayList(allNames);
        }
        return results;   
    }
}
