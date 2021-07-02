package com.example.test.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * @JacksonXmlElementWrapper(localName = "Students")
 * @JacksonXmlProperty(localName = "Stu")
 * List<Student> student;  //学生列表
 */

@JacksonXmlRootElement(localName = "configuration")
public class ConfigList {
    @JacksonXmlProperty(localName = "cluster")
    public List<Config> configList;

    public List<Config> getClusterinfos() {
        return configList;
    }

    public void setClusterinfos(List<Config> configList) {
        this.configList = configList;
    }

    public ConfigList() {
    }
}
