package com.northcity.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.northcity.blog.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
public class TokenUtil {

	private static final Logger log = LoggerFactory.getLogger(TokenUtil.class);

	// 过期时间5分钟
	private static final long EXPIRE_TIME = 5 * 60 * 1000;

	/**
	 * 生成签名,5min后过期
	 *
	 * @return 加密的token
	 */
	public static String sign(Admin admin) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(admin.getPassword());
		String token = JWT.create()
				.withClaim("username", admin.getName())
				.withExpiresAt(date)
				.sign(algorithm);
		// 附带username信息
		log.info(token);
		return token;
	}

	/**
	 * 校验token是否正确
	 *
	 * @param token  密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String username, String secret) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm)
				.withClaim("username", username)
				.build();
		DecodedJWT jwt = verifier.verify(token);
		return true;
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 *
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("username").asString();
	}

}