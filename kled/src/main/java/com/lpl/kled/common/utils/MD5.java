package com.lpl.kled.common.utils;

import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
/**
 * MD5 加密
 * @author hx
 *
 */
@SuppressWarnings("restriction")
public class MD5 { 

	private final static Log log = LogFactory.getLog(MD5.class); 
	private static final String algorithm = "MD5";
	
	/** 
	* Encode a string using algorithm specified in web.xml and return the 
	* resulting encrypted password. If exception, the plain credentials 
	* string is returned 
	* 
	* @param password Password or other credentials to use in authenticating 
	* this username 
	* @param algorithm Algorithm used to do the digest 
	* 
	* @return encypted password based on the algorithm. 
	*/ 
	public static String encodePassword(String password) { 
	    if (StringUtils.isEmpty(password))
            return "unknow";
	    byte[] unencodedPassword = password.getBytes(); 

	    MessageDigest md = null; 

	    try { 
	        // first create an instance, given the provider 
	        md = MessageDigest.getInstance(algorithm); 
	    } catch (Exception e) { 
	        log.error("Exception: " + e); 

	        return password; 
	    } 

	    md.reset(); 

	    // call the update method one or more times 
	    // (useful when you don't know the size of your data, eg. stream) 
	    md.update(unencodedPassword); 

	    // now calculate the hash 
	    byte[] encodedPassword = md.digest(); 

	    StringBuffer buf = new StringBuffer(); 

	    for (int i = 0; i < encodedPassword.length; i++) { 
	        if ((encodedPassword[i] & 0xff) < 0x10) { 
	            buf.append("0"); 
	        } 

	        buf.append(Long.toString(encodedPassword[i] & 0xff, 16)); 
	    } 

	    return buf.toString(); 
	} 

	/** 
	* Encode a string using Base64 encoding. Used when storing passwords 
	* as cookies. 
	* 
	* This is weak encoding in that anyone can use the decodeString 
	* routine to reverse the encoding. 
	* 
	* @param str 
	* @return String 
	*/ 
	public static String encodeString(String str) { 
	    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder(); 
	    return encoder.encodeBuffer(str.getBytes()).trim(); 
	} 

	/** 
	* Decode a string using Base64 encoding. 
	* 
	* @param str 
	* @return String 
	*/ 
  	public static String decodeString(String str) { 
  	    sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder(); 
		try { 
      		return new String(dec.decodeBuffer(str)); 
		} catch (IOException io) { 
      		throw new RuntimeException(io.getMessage(), io.getCause()); 
		} 
	} 

  	public static void main(String[] args){
  	    String encryptPwd = MD5.encodePassword("123456");
  	    System.out.println(encryptPwd);
  	    System.out.println(encryptPwd.length());
	}
  	
}
