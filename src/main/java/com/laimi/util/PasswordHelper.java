package com.laimi.util;


import com.laimi.pojo.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

//解密
public class PasswordHelper {

    public static String encryption(User user){
        RandomNumberGenerator randomNumberGenerator=new SecureRandomNumberGenerator();
        String algorithmName = "md5";
        int hashIterations = 2;
        String salt=randomNumberGenerator.nextBytes().toHex();
        String newPassword=new SimpleHash(algorithmName,user.getPassword(),user.getUser_name()+salt,hashIterations).toHex();;
        user.setSalt(salt);
     return newPassword;
    }
}
