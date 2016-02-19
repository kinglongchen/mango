package com.kinglong.mango.tools;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by chenjinlong on 15/10/26.
 */
@Component
public class SessionTokenFactory {
    private static final Integer defaultNumber = 32;
    private static  final char[] charArray=
            {'0','1','2','3','4','5','6','7','8','9'
            ,'a','b','c','d','e','f','g','h','i','j'
            ,'k','l','m','n','o','p','q','r','s','t'
            ,'u','v','w','x','y','z','A','B','C','D'
            ,'E','F','G','H','I','J','K','L','M','N'
            ,'O','P','Q','R','S','T','U','V','W','X'
            ,'Y','Z'};
    public String generate(Integer number) {
        if (number<=0) {
            throw new RuntimeException("参数不能为空");
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = number ;i>0;i--) {
            Integer index = random.nextInt(62);

            sb.append(charArray[index]);
        }
        Long timeStamp = DateTime.now().toDate().getTime();
        return String.format("%s_%s",sb.toString(),timeStamp);
    }

    public String generate() {
        return generate(defaultNumber);
    }
}
