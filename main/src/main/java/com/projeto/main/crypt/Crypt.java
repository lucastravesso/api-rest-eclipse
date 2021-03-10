package com.projeto.main.crypt;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Crypt {

	
	//CRIPTOGRAFIA CRIADA COM MD5 NA MAO SEM USAR SERVIÇO DO SPRING DE BCrypt
	
	public static String crypt(String senha)
	{
		String retorno = "";
		
		MessageDigest md;
		try {
			
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			retorno = hash.toString(16);
			
		}catch(Exception e) {}
		
		return retorno;
	}
	
}
