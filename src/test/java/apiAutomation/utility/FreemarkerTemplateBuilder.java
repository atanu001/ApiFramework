package apiAutomation.utility;

import freemarker.cache.FileTemplateLoader;

import freemarker.template.Configuration;

import freemarker.template.Template;

import io.cucumber.core.options.Constants;


import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.Collections;

public final class FreemarkerTemplateBuilder {
    private final Template template;
    private FreemarkerTemplateBuilder(Template template){
        this.template = template;
    }

    public static FreemarkerTemplateBuilder formTemplate(String templateFilePath){
        try{
            Configuration configuration = getConfiguration();
            FileTemplateLoader templateLoader = new FileTemplateLoader(Paths.get(System.getProperty("user.dir"), templateFilePath.substring(0,templateFilePath.lastIndexOf("/"))).toFile());
            configuration.setTemplateLoader(templateLoader);
            Template template = configuration.getTemplate(templateFilePath.substring(templateFilePath.lastIndexOf("/")+1));
            return new FreemarkerTemplateBuilder(template);
        } catch (IOException e) {
            throw new RuntimeException("Error!! Freemarker Template creation is failed " + e);
        }
    }

    private static Configuration getConfiguration(){
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    public String build(){
        return this.build(Collections.EMPTY_MAP);
    }

    public String build(Object dataModel) {
        try {
            StringWriter writer = new StringWriter();
            this.template.process(dataModel, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process freemarker template", e);

        }
    }

}
