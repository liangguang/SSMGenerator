package com.swust.zj.main;

import com.swust.zj.generator.IOUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ZhouJie on 2017/9/15.
 */
public class CopyResources {
    private String fileDir;
    private String projectName;
    private String packageDir;
    Map<String,String> fromTo;
    public static final String ROOT_DIR = System.getProperty("user.dir");
    public static final String RESOURCES_FILE_DIR = "src/main/resources";
    private String getROOTAbsolutePath(){
        return ROOT_DIR;
    }
    private String getResourceAbsolutePath(){
        return ROOT_DIR+"/"+RESOURCES_FILE_DIR;
    }
    public static final String PROJECT_JAVA_FILE_DIR = "src/main/java";
    public static final String PROJECT_RESOURCES_FILE_DIR = "src/main/resources";
    public static final String PROJECT_WEB_APP_DIR = "src/main/webapp";
    public String getProjectAbsolutePath(){
        return fileDir + "/" + projectName;
    }
    public String getProjectJavaAbsolutePath(){
        return getProjectAbsolutePath() + "/"+PROJECT_JAVA_FILE_DIR;
    }
    public String getProjectJavaPackageAbsolutePath(){
        return getProjectAbsolutePath() + "/"+PROJECT_JAVA_FILE_DIR+"/" + packageDir;
    }
    public String getProjectResourcesAbsolutePath(){
        return getProjectAbsolutePath() + "/" + PROJECT_RESOURCES_FILE_DIR;
    }
    public String getProjectWebAppAbsolutePath(){
        return getProjectAbsolutePath() + "/" + PROJECT_WEB_APP_DIR;
    }
    public CopyResources(String fileDir, String projectName, String packageDir){
        this.fileDir = fileDir;
        this.projectName = projectName;
        this.packageDir = packageDir;
        initResourceFile();
    }
    public void initResourceFile(){
        fromTo = new HashMap<>();
        fromTo.put(getResourceAbsolutePath()+"/gradle",getProjectAbsolutePath());
        fromTo.put(getResourceAbsolutePath()+"/cache",getProjectJavaPackageAbsolutePath()+"/cache");
        fromTo.put(getResourceAbsolutePath()+"/config",getProjectJavaPackageAbsolutePath()+"/config");
        fromTo.put(getResourceAbsolutePath()+"/util",getProjectJavaPackageAbsolutePath()+"/util");
        fromTo.put(getResourceAbsolutePath()+"/resources",getProjectResourcesAbsolutePath());
        fromTo.put(getResourceAbsolutePath()+"/WEB-INF",getProjectWebAppAbsolutePath()+"/WEB-INF");
        fromTo.put(getROOTAbsolutePath()+"/temp",getProjectJavaAbsolutePath());
    }
    public void copy(){
        if(fromTo != null) {
            Iterator<String> iterator = fromTo.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                String value = fromTo.get(key);
                IOUtil.copyFileOrDir(key,value);
            }
        }

    }
}
