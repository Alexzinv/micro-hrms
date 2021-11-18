package com.alex.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author _
 * @Date 2021/9/15
 * @Description JWT工具
 */
public class JwtUtils {

	/**
	 * token过期时间
	 */
	public static final long EXPIRE = 1000 * 60 * 60 * 24;
	/**
	 * 密钥
 	 */
	public static final String APP_SECRET = "alko03dasfFEFDSse13FikEBV";

	/**
	 * 生成token
	 * @param id 用户id
	 * @param username 用户昵称
	 * @return token字符串
	 */
	public static String getJwtToken(Long id, String username){

		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("alg", "HS256")

				.setSubject("user")
				.setIssuedAt(Calendar.getInstance().getTime())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

				// 用户信息
				.claim("id", id)
				.claim("username", username)

				// 签名
				.signWith(SignatureAlgorithm.HS256, APP_SECRET)
				.compact();
	}

	/**
	 * 判断token是否存在与有效
	 * @param jwtToken token
	 * @return 是否存在
	 */
	public static boolean checkToken(String jwtToken) {
		if (StringUtils.hasText(jwtToken)) {
			try {
				Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断token是否存在与有效
	 * @param request 请求
	 * @return 是否存在
	 */
	public static boolean checkToken(HttpServletRequest request) {
		try {
			String jwtToken = request.getHeader("token");
			if(!StringUtils.hasText(jwtToken)) {
				return false;
			}
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据token获取用户信息
	 * @param request 请求
	 * @return 用户id
	 */
	public static String getUserIdByJwtToken(HttpServletRequest request) {
		String jwtToken = request.getHeader("token");
		if(!StringUtils.hasText(jwtToken)) {
			return "";
		}
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
		Claims claims = claimsJws.getBody();
		return (String)claims.get("id");
	}


}
