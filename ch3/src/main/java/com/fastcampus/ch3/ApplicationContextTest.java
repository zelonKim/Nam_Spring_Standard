/*
package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.*;
import org.springframework.stereotype.*;
import javax.inject.*;
import java.util.*;




@Component
@PropertySource("setting.properties")
class SysInfo {
    @Value("#{systemProperties['user.timezone']}")
    String timeZone;

    @Value("#{systemEnvironment['APPDATA']}")
    String currDir;

    @Value("${autosave}")
    boolean autosave;

    @Value("${autosaveDir}")
    String autosaveDir;

    @Value("${autosaveInterval}")
    int autosaveInterval;


    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", currDir='" + currDir + '\'' +
                ", autosave=" + autosave +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveInterval=" + autosaveInterval +
                '}';
    }
}



public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");

        System.out.println(ac.getBean(SysInfo.class)); // SysInfo{timeZone='Asia/Seoul', currDir='C:\Users\ksz18\AppData\Roaming', autosave=true, autosaveDir='/autosave', autosaveInterval=30}

//      Properties pro = System.getProperties();
//      Map<String, String> env = System.getenv();

    }
}

*/
