package com.example.demo.common.service.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.common.error.UnauthorizedException;
import com.example.demo.common.util.GetJwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("jwtService")
public class JwtServiceImpl implements JwtService{
   private static final String SALT =  "wgbtSecretToken";
    private static final String Key =  "userInfo";

    @Override
    public <T> String create(String key, T data, String subject){
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(subject)
                .setIssuer("wgbt.com")
                .setExpiration(new Date(new Date().getTime() + (60*60*24*7) )) // 7주 후
//                .setExpiration(new Date(new Date().getTime() + 5000l)) // 5 second from now
                .claim(key, data)   // 클레임 중요
//                .claim("name", "Robert Token Man") // 클레임 하나더 가능
                .signWith(SignatureAlgorithm.HS256, this.generateKey()) // HS256 방식으로 우리키랑 같이 서명한다.
                .compact();
        return jwt;
    }

    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
            //getBytes는 자바 내부에 관리되는 유니코드 문자열을
            //인자로 지정된 캐릭터셋의 바이트 배열로 반환하는 메서드
            // 즉 wgbtSecretToken salt를 바이트 배열로 변환

        } catch (UnsupportedEncodingException e) {
            throw new UnauthorizedException();
        }

        return key;
    }

    /*
    JWT는 Claim을 JSON형태로 표현하는 것인데, JSON은 “\n”등 개행문자가 있기 때문에,
    REST API 호출시 HTTP Header등에 넣기가 매우 불편하다.
    그래서, JWT에서는 이 Claim JSON 문자열을 BASE64 인코딩을 통해서 하나의 문자열로 변환한다.
    */

    @Override
    public Map<String, Object> get(String key) {
//        HTTP Header -> JWT -> Claim -> Key -> Value.

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = GetJwtToken.getToken(request);    // 토큰을 가져와서
//        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("값없는 토큰");
//            throw new UnauthorizedException();
         Map<String,Object> value = new HashMap<>();
            value.put("checkToken", false);
         return value;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        value.put("checkToken", true);
        return value;
    }

    @Override
    public boolean isUsable(String jwt) {
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
            return true;
        }catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("못쓰는 토큰");
            throw new UnauthorizedException();
            /*개발환경!!!
             * return false;*/
        }
    }

    @Override
    public Integer getUserNo() {
        if(checkNullToken()){
            return (Integer)this.get(Key).get("no");
        }
        return null;
    }

    @Override
    public boolean checkNullToken() {
        return (boolean)this.get(Key).get("checkToken");
    }
}
