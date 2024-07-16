package org.apicore.utility;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static Map<String, Object> valuesToTemplate(String[] key, String[] value){
        Map<String, Object> valueToTemplate = new HashMap<>();
        for (int i =0;i< key.length;i++){
            String Key = key[i];
            String Value = value[i];
            valueToTemplate.put(Key, Value);
        }

        return valueToTemplate;
    }
}
