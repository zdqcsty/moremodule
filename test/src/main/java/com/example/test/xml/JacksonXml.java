package com.example.test.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonXml {

    public static final String XML_PATH = "test/src/main/resources/hdfs-site.xml";

    public static void main(String[] args) throws IOException {

        XmlMapper mapper = new XmlMapper();
        File file = new File(XML_PATH);
        ConfigList configList = mapper.readValue(file, ConfigList.class);

        List<Config> clusterinfos = configList.getClusterinfos();
        for (Config conf : clusterinfos) {
            System.out.println(conf.getName());
        }
    }
}
