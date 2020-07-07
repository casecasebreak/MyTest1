package com.qjl.iot.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @业务描述：
 * @package_name： com.lenovo.bpms.common.utils
 * @project_name： lenovo-bms
 * @author： ratelfu@qq.com
 * @create_time： 2020-03-10 15:25
 * @copyright (c) ratelfu 版权所有
 */
public class CompareObjUtils {

    /**
     * @param beforeObj
     * @param afterObj
     * @return 一个以存在修改的field为key，修改后的值为 value 的map
     * @throws Exception
     */
    public static Map compareObj(Object beforeObj,Object afterObj) {
        Map map = new HashMap<String,String>();

        /*if(beforeObj == null) {
            throw new Exception("原对象不能为空");
        }
        if(afterObj == null) {
            throw new Exception("新对象不能为空");
        }
        if(!beforeObj.getClass().isAssignableFrom(afterObj.getClass())){
            throw new Exception("两个对象不相同，无法比较");
        }*/

        //取出属性
        Field[] beforeFields = beforeObj.getClass().getDeclaredFields();
        Field[] afterFields = afterObj.getClass().getDeclaredFields();
        Field.setAccessible(beforeFields, true);
        Field.setAccessible(afterFields, true);

        //遍历取出差异值
        if(beforeFields != null && beforeFields.length > 0){
            for(int i=0; i<beforeFields.length; i++){
                Object beforeValue = null;
                Object afterValue = null;
                try {
                    beforeValue = beforeFields[i].get(beforeObj);
                    afterValue = afterFields[i].get(afterObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if((beforeValue != null && !"".equals(beforeValue) && !beforeValue.equals(afterValue)) || ((beforeValue == null || "".equals(beforeValue)) && afterValue != null)){
                    map.put(beforeFields[i].getName(),afterValue);

                }
            }
        }
        return map;
    }
}
