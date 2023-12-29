package com.cj.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cj.entity.User;
import com.cj.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    @Resource
    private static UserService userService;


    private  static final Long TOKEN_EXPIRED_TIME = 60*60L;  // 一小时过期

    private static final String jwtId = "tokenId";

    /**
     * jwt加密解密密钥
     */
    private static final String JWT_SECRET = "jwt+00";


    /**
     * 创建jwt token
     */
    public static String createToken(Map<String, Object> claims,Long time){
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;  // 指定签名的时候使用签名算法，也就是header那部分
        Date now = new Date(System.currentTimeMillis());


        SecretKey secretKey = generalKey();

        long nowMillis = System.currentTimeMillis();  // 生成jwt时间

        // 下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder()   // 这里其实就是new 一个JwtBuilder,设置jwt的body
                .setClaims(claims)   // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的
                .setId(jwtId)        // 设置jwt的id
                .setIssuedAt(now)  // jwt的签发时间
                .signWith(hs256, secretKey);  // 设置签名使用的签名算法和签名使用的秘钥

        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date((expMillis));
            builder.setExpiration(exp);     // 设置过期时间
        }

        return builder.compact();
    }


    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        // 签名密钥，和生成签名的密钥一摸一样
        SecretKey secretKey = generalKey();

        Claims claims;

        try{
            claims = Jwts.parser()  // 得到DefaultJwtParser
                    .setSigningKey(secretKey)        // 设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        }catch (Exception e){
            claims = null;
        }

        return claims;
    }

    /**
     * 生成签名秘钥
     */
    public static SecretKey generalKey() {
        String stringKey = JWT_SECRET;        // JWT密钥
        byte[] encodedKey = Base64.getEncoder().encode(stringKey.getBytes());
        SecretKeySpec spec = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return spec;
    }


    /**
     * 根据userId和openid生成token
     *
     */
    public static String generateToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("userName", user.getUsername());
        return createToken(map, TOKEN_EXPIRED_TIME);
    }


    /**
     * 获取当前用户的信息
     */
    public static User getCurrentUserInfo() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StringUtils.isNotBlank(token)){
                Claims claims = verifyJwt(token);
                if (claims != null) {
                    String userId = (String) claims.get("userId");
                    return
                            userService.getById(userId);
                } else{
                    return null;
                }
            }
        }catch(Exception e){
            return null;
        }
        return null;
    }

    /**
     * 通过token获取用户id
     */
    public static Long getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = verifyJwt(token);
        if (claims != null){
            return (Long) claims.get("userId");
        } else{
            return 0L;
        }
    }


}
