/**
 * User: zlin
 * Date: 2019/8/5
 * Time: 14:56
 **/

package com.zl.demotest.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * 生成唯一id
     * @return
     */
    public static Integer getUUIDAsInt(){
        String uuid = UUID.randomUUID().toString();
        char[] cs=new char[11];
        char c=0;
        for (int i = 0,j=0;j<9;i++) {
            if ((c=uuid.charAt(i))>='0'&&c<='9'){
                cs[j++]=c;
            }
        }
        return Integer.valueOf(new String(cs).trim());
    }
}
