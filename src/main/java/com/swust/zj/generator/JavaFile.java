package com.swust.zj.generator;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by ZhouJie on 2017/9/12.
 */
public abstract class JavaFile {
    private String fileDir;
    private String fileName;
    private String projectName;
    private String packageDir;
    private String filePackageDir;
    private String fileType;
    private String className;
    public JavaFile(String fileDir,String projectName,String fileName,String packageDir,String filePackageDir,String fileType,String className){
        this.fileDir = fileDir;
        this.projectName = projectName;
        this.fileName = fileName;
        this.packageDir = packageDir;
        this.filePackageDir = filePackageDir;
        this.fileType = fileType;
        this.className = className;
    }
    public static final String JAVA_FILE_DIR = "src/main/java";
    public String getProjectAbsolutePath(){
        return fileDir + "/" + projectName;
    }
    public String getJavaAbsolutePath(){
        return getProjectAbsolutePath() + "/"+JAVA_FILE_DIR+"/" + packageDir + "/" + filePackageDir;
    }
    public File makeJavaDir() throws Exception{
        synchronized (JavaFile.class) {
            File dir = new File(getJavaAbsolutePath());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir;
        }
    }
    public File makeJavaFile() throws Exception{
        synchronized (JavaFile.class) {
            File dir = makeJavaDir();
            if (!fileName.endsWith(".java")) {
                fileName += ".java";
            }
            File file = new File(dir, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        }
    }
    public void generate() throws  Exception{
        //startWrite
        File file = makeJavaFile();
        FileWriter fileWriter = new FileWriter(file);
        //initString
        StringBuilder stringBuilder = new StringBuilder();
        //ensureWritePackage
        stringBuilder.append("package ");
        stringBuilder.append(getFilePackageStanderString());
        stringBuilder.append(";\n");
        //ensureWriteImport
        addImports(stringBuilder);
        //writeClassAnnotations
        addClassAnnotations(stringBuilder);
        //ensureWriteClassStart
        stringBuilder.append("public ");
        stringBuilder.append(fileType);
        stringBuilder.append(" ");
        stringBuilder.append(className);
        stringBuilder.append(" ");
        //ensureWriteExtends
        addExtends(stringBuilder);
        //ensureWritesImplements
        addImplements(stringBuilder);
        stringBuilder.append("{\n");
        //ensureWriteField
        addFields(stringBuilder);
        //ensureWriteMethod
        addMethods(stringBuilder);
        //ensureWriteClassEnd
        stringBuilder.append("}\n");
        //writeAll
        fileWriter.write(stringBuilder.toString());
        //endWrite
        fileWriter.flush();
        fileWriter.close();
    }
    public String getProjectPackageStanderString(){
        String[] temps = packageDir.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<temps.length;i++){
            if(i!=0){
                stringBuilder.append(".");
            }
            stringBuilder.append(temps[i]);
        }
        return stringBuilder.toString();
    }
    public String getFilePackageStanderString(){
        String[] temps = (packageDir+"/"+filePackageDir).split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<temps.length;i++){
            if(i!=0){
                stringBuilder.append(".");
            }
            stringBuilder.append(temps[i]);
        }
        return stringBuilder.toString();
    }
    public abstract void addImports(StringBuilder stringBuilder);
    public abstract void addClassAnnotations(StringBuilder stringBuilder);
    public abstract void addExtends(StringBuilder stringBuilder);
    public abstract void addImplements(StringBuilder stringBuilder);
    public abstract void addFields(StringBuilder stringBuilder);
    public abstract void addMethods(StringBuilder stringBuilder);
}
