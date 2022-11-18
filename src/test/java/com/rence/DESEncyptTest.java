package com.rence;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DESEncyptTest {
	
	 @Test
	    void contextLoads() {
	    }

	    @Test
	    void jasypt() {
	        String username = "";
	        String password = "";

	        System.out.println(jasyptEncoding(username));
	        System.out.println(jasyptEncoding(password));
	    }

	    public String jasyptEncoding(String value) {

	        String key = "password";
	        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
	        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
	        pbeEnc.setPassword(key);
	        return pbeEnc.encrypt(value);
	    }
}