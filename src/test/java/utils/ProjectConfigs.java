package utils;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:stands/${env}.properties")
public interface ProjectConfigs extends Config {
    @Key("url")
    @DefaultValue("https://10.38.20.10:8088")
    String url();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("timeout")
    @DefaultValue("4000")
    int timeout();
}
