package com.jongtk.hanjan.security.util;

import java.time.ZonedDateTime;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
	
	//properties를 통해 주입 (git repo를 통한 유출 방지)
	@org.springframework.beans.factory.annotation.Value("${api.secretKey.jwt}")	
	private String secretKey;
	
	public String generateToken(String str) throws Exception {
		
		log.info("secretKey ======="+secretKey);
	
		// ZonedDateTime = LocalDateTime과 비교시 시차개념(연산메소드)이 추가된 객체
		return Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(Date.from(ZonedDateTime.now().plusWeeks(1).toInstant()))
				.claim("sub", str)
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
				.compact();
	}
	
	public String ValidateWithExtract(String token) throws Exception {
		
		String originStr = null;
		
		
		try {
			DefaultJws defaultJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(token);

			log.info(defaultJws);
			
			DefaultClaims claims =  (DefaultClaims) defaultJws.getBody();
			originStr = claims.getSubject();
			
		} catch (Exception e) {
			e.printStackTrace();
			originStr = null;
		}
		
		return originStr;
		
		
	}

}
