package org.unice.polytech.si3.devint.teffaha.numbershooter.core;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author TEFFAHA Mortadha
 *
 * A static class for fetching application configuration
 * the config file needs to be in the same folder as the executing program
 * the name can be altered staticly in the code by modifing the static String  CONFIG_FILE
 */
public class Config {
    private static final String CONFIG_FILE="../../../ressources/config.xml";
	private static boolean initialized = false;
	private static URI path;
	private static String PluginsPath;
    private static String ApplicationName;
    private static Map<String,Object> paramters;

	private static void init() {


		// if allready initialised we skip the intialisation.
		if (Config.initialized) {
			return;
		}

        // initialising the map
        paramters =  new HashMap<String, Object>();

		// we fetch the execution path so we could find the configuration file
		try {
			path = Config.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // we create try to open the file as a Document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = (Document) db.parse(Config.path + Config.CONFIG_FILE);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

        //
        // now that the file is open we try to fetch the available paramters
        parseNodeList(dom.getChildNodes(),Config.paramters);

        // we set the intialisation as done.
        Config.initialized = true;
	}

    private static Map<String,Object> parseNodeList(NodeList list,Map<String,Object> params){

        // we handle all the childs
        for (int i = 0; i < list.getLength(); i++) {
            if(list.item(i).getTextContent().isEmpty()){ // we skip if empty
                continue;
            }
           // we add the name and the parsed value of the object
           params.put(list.item(i).getNodeName(),parsePrimitiveObject(list.item(i).getTextContent()));
           // we parse it's child items too
           if(list.item(i).hasChildNodes()){
               parseNodeList(list.item(i).getChildNodes(),params);
           }

        }

        return params;
    }

    /**
     * parses a string to a primitive Object or to a String. (Byte,Short,Int,Long,Float,Double,Boolean,String)
     * @param textContent the String to be parsed
     * @return the parsed object
     */
    private static Object parsePrimitiveObject(String textContent) {
        Object value;

       // byte
        try{
            return (Object)Byte.parseByte(textContent);
        }catch(NumberFormatException notInt){

        }

       // short
        try{
            return (Object)Short.parseShort(textContent);
        }catch(NumberFormatException notInt){

        }

       // int
        try{
            return (Object)Integer.parseInt(textContent);
        }catch(NumberFormatException notInt){

        }

       // long
        try{
            return (Object)Long.parseLong(textContent);
        }catch(NumberFormatException notInt){

        }

       // float
        try{
            return (Object)Float.parseFloat(textContent);
        }catch(NumberFormatException notInt){

        }

      //  double
        try{
            return (Object)Double.parseDouble(textContent);
        }catch(NumberFormatException notInt){

        }

      //  boolean
        if(textContent.toLowerCase().equals("true") || textContent.toLowerCase().equals("false")){
            return (Object)Boolean.parseBoolean(textContent);
        }

      //  String
        return textContent;
    }

    /**
     * Fetch the paramter by it's name
     * @param name  the name of the paramter to fetch
     * @return  the value associated with the name passed , null if the paramter does not exists
     */
    public static Object getParameterByName(String name){
        Config.init();
        return paramters.get(name);
    }

    /**
     * get the value of the ID'th element
     * @param ID  the id (index) of the value to fetch
     * @return the value if the ID exists otherwise null is returned
     */
    public static Object getParameterValueByID(int ID){
        Config.init();
        if(paramters.values().toArray().length> ID || ID < 0)
            return null;
        return paramters.values().toArray()[ID];
    }

    /**
     * gets the name of the ID'th element
     * @param ID  the ID (index) of the paramter
     * @return   the name of the paramter if it exists otherwise null
     */
    public static String getParameterNameByID(int ID){
        Config.init();
        if(paramters.keySet().toArray().length> ID || ID < 0)
            return null;
        return (String)paramters.keySet().toArray()[ID];
    }

    /**
     * Get the number of available parameters
     * @return   the paramters count
     */
    public static int getPatamtersCount(){
        return paramters.size();
    }

}
