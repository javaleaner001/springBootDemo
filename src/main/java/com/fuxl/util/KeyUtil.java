package com.fuxl.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class KeyUtil {
    /**
     * 生成UUDI
     * @return
     */
    public static String generatorUUID(){
        TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        return timeBasedGenerator.generate().toString();
    }

    public static void main(String[] args) {
        System.out.println(generatorUUID());
        System.out.println(generatorUUID());
        System.out.println(generatorUUID());
    }
}
