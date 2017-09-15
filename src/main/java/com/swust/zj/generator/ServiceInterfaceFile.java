package com.swust.zj.generator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhouJie on 2017/9/15.
 */
public class ServiceInterfaceFile extends JavaFile{
    private String pojoName;
    private List<String> importNameList;
    private List<Method> methodList;
    class Method{
        String modifier;
        String resultType;
        String name;
        String args;
        List<String> exceptions;
        public Method(String modifier, String resultType, String name, String args, List<String> exceptions){
            this.modifier = modifier;
            this.resultType = resultType;
            this.name = name;
            this.args = args;
            this.exceptions = exceptions;
        }
    }
    public ServiceInterfaceFile(String fileDir, String projectName,String packageDir,String pojoName){
        super(fileDir,projectName,pojoName+"Service",packageDir,"service","interface",pojoName+"Service");
        this.pojoName = pojoName;
        serviceInterfaceInit();
    }
    public void serviceInterfaceInit(){
        importNameList = new LinkedList<>();
        importNameList.add("java.util.List");
        importNameList.add(getProjectPackageStanderString()+".pojo.Tb"+pojoName);
        importNameList.add(getProjectPackageStanderString()+".util.PageBean");

        methodList = new LinkedList<>();
        //add
        Method method = new Method("public","void","add","Tb"+pojoName+" "+pojoName.toLowerCase(),null);
        methodList.add(method);
        //delete
        method = new Method("public","void","delete","int id",null);
        methodList.add(method);
        //update
        method = new Method("public","void","update","Tb"+pojoName+" "+pojoName.toLowerCase(),null);
        methodList.add(method);
        //get
        method = new Method("public","Tb"+pojoName,"get","int id",null);
        methodList.add(method);
        //getAll
        method = new Method("public","List<Tb"+pojoName+">","getAll","",null);
        methodList.add(method);
        //getPage
        method = new Method("public","PageBean<Tb"+pojoName+">","getPage","int page, int rows",null);
        methodList.add(method);
    }
    public void addImports(StringBuilder stringBuilder){
        if(importNameList != null) {
            Iterator<String> iterator = importNameList.iterator();
            while (iterator.hasNext()) {
                stringBuilder.append("import ");
                stringBuilder.append(iterator.next());
                stringBuilder.append(";\n");
            }
        }
    }
    public void addClassAnnotations(StringBuilder stringBuilder){}
    public void addExtends(StringBuilder stringBuilder){}
    public void addImplements(StringBuilder stringBuilder){}
    public void addFields(StringBuilder stringBuilder){}
    public void addMethods(StringBuilder stringBuilder){
        if(methodList != null) {
            Iterator<Method> iterator = methodList.iterator();
            while (iterator.hasNext()) {
                Method method = iterator.next();
                if(method.modifier!=null){
                    stringBuilder.append("\t");
                    stringBuilder.append(method.modifier);
                    stringBuilder.append(" ");
                }
                if(method.resultType!=null){
                    stringBuilder.append(method.resultType);
                    stringBuilder.append(" ");
                }
                if(method.name!=null){
                    stringBuilder.append(method.name);
                    stringBuilder.append("(");
                }
                if(method.args!=null){
                    stringBuilder.append(method.args);
                    stringBuilder.append(")");
                }
                if(method.exceptions!=null){
                    Iterator<String> iteratorExceptions = method.exceptions.iterator();
                    int count = 0;
                    while(iteratorExceptions.hasNext()) {
                        if(count == 0){
                            stringBuilder.append(" throws ");
                        }else{
                            stringBuilder.append(",");
                        }
                        stringBuilder.append(iteratorExceptions.next());
                        count++;
                    }
                }
                stringBuilder.append(";\n");
            }
        }
    }
}
