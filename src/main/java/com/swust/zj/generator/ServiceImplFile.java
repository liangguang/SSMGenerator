package com.swust.zj.generator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhouJie on 2017/9/15.
 */
public class ServiceImplFile extends JavaFile {
    private String pojoName;
    private List<String> importNameList;
    private List<String> classAnnotationNameList;
    private List<String> implementsNameList;
    private List<Field> fieldList;
    private List<Method> methodList;
    class Field{
        List<String> annotations;
        String modifier;
        String type;
        String name;
        String value;
        public Field(List<String> annotations, String modifier, String type, String name, String value){
            this.annotations = annotations;
            this.modifier = modifier;
            this.type = type;
            this.name = name;
            this.value = value;
        }
    }
    class Method{
        List<String> annotations;
        String modifier;
        String resultType;
        String name;
        String args;
        List<String> exceptions;
        String methodBody;
        public Method(List<String> annotations, String modifier, String resultType, String name, String args, List<String> exceptions, String methodBody){
            this.annotations = annotations;
            this.modifier = modifier;
            this.resultType = resultType;
            this.name = name;
            this.args = args;
            this.exceptions = exceptions;
            this.methodBody = methodBody;
        }
    }
    public ServiceImplFile(String fileDir, String projectName,String packageDir,String pojoName){
        super(fileDir,projectName,pojoName+"ServiceImpl",packageDir,"service/impl","class",pojoName+"ServiceImpl");
        this.pojoName = pojoName;
        serviceImplInit();
    }
    public void serviceImplInit(){
        //initImport
        importNameList = new LinkedList<>();
        importNameList.add("java.util.List");
        importNameList.add("org.apache.commons.lang3.StringUtils");
        importNameList.add("org.springframework.beans.factory.annotation.Autowired");
        importNameList.add("org.springframework.stereotype.Service");
        importNameList.add("com.github.pagehelper.PageHelper");
        importNameList.add("com.github.pagehelper.PageInfo");
        importNameList.add(getProjectPackageStanderString()+".mapper.Tb"+pojoName+"Mapper");
        importNameList.add(getProjectPackageStanderString()+".pojo.Tb"+pojoName);
        importNameList.add(getProjectPackageStanderString()+".pojo.Tb"+pojoName+"Example");
        importNameList.add(getProjectPackageStanderString()+".service."+pojoName+"Service");
        importNameList.add(getProjectPackageStanderString()+".util.PageBean");
        //initClassAnnotations
        classAnnotationNameList = new LinkedList<>();
        classAnnotationNameList.add("@Service");
        //initImplements
        implementsNameList = new LinkedList<>();
        implementsNameList.add(pojoName+"Service");
        //initFields
        fieldList = new LinkedList<>();
        List<String> annotations = new LinkedList<>();
        annotations.add("@Autowired");
        Field field = new Field(annotations,"private","Tb"+pojoName+"Mapper",pojoName.toLowerCase()+"Mapper",null);
        fieldList.add(field);
        //initMethods
        methodList = new LinkedList<>();
        //add
        String methodBody =
                "\t\t"+pojoName.toLowerCase()+"Mapper.insert("+pojoName.toLowerCase()+");\n";
        Method method = new Method(null,"public","void","add","Tb"+pojoName + " " + pojoName.toLowerCase(),null, methodBody);
        methodList.add(method);
        //delete
        methodBody =
                "\t\t"+pojoName.toLowerCase()+"Mapper.deleteByPrimaryKey(id);\n";
        method = new Method(null,"public","void","delete","int id",null, methodBody);
        methodList.add(method);
        //update
        methodBody =
                "\t\t"+pojoName.toLowerCase()+"Mapper.updateByPrimaryKey("+pojoName.toLowerCase()+");\n";
        method = new Method(null,"public","void","update","Tb"+pojoName + " " + pojoName.toLowerCase(),null, methodBody);
        methodList.add(method);
        //get
        methodBody =
                "\t\treturn "+pojoName.toLowerCase()+"Mapper.selectByPrimaryKey(id);\n";
        method = new Method(null,"public","Tb"+pojoName,"get","int id",null, methodBody);
        methodList.add(method);
        //getAll
        methodBody =
                "\t\tTb"+pojoName+"Example example = new Tb"+pojoName+"Example();\n"+
                "\t\tList<Tb"+pojoName+"> all = "+pojoName.toLowerCase()+"Mapper.selectByExample(example);\n"+
                "\t\treturn all;\n";
        method = new Method(null,"public","List<Tb"+pojoName+">","getAll","",null, methodBody);
        methodList.add(method);
        //getPage
        methodBody =
                "\t\tTb"+pojoName+"Example example = new Tb"+pojoName+"Example();\n"+
                "\t\tPageHelper.startPage(page, rows);\n"+
                "\t\tList<Tb"+pojoName+"> ipage = "+pojoName.toLowerCase()+"Mapper.selectByExample(example);\n"+
                "\t\tPageBean<Tb"+pojoName+"> pageBean = new PageBean<>();\n"+
                "\t\tpageBean.setCurrentPage(page);\n"+
                "\t\tpageBean.setPageSize(rows);\n"+
                "\t\tpageBean.setTotalRecord((int) new PageInfo<Tb"+pojoName+">(ipage).getTotal());\n"+
                "\t\tpageBean.setBeanList(ipage);\n"+
                "\t\treturn pageBean;\n";
        method = new Method(null,"public","PageBean<Tb"+pojoName+">","getPage","int page, int rows",null, methodBody);
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
    public void addClassAnnotations(StringBuilder stringBuilder){
        if(classAnnotationNameList != null){
            Iterator<String> iterator = classAnnotationNameList.iterator();
            while(iterator.hasNext()){
                stringBuilder.append(iterator.next());
                stringBuilder.append("\n");
            }
        }
    }
    public void addExtends(StringBuilder stringBuilder){}
    public void addImplements(StringBuilder stringBuilder){
        if(implementsNameList != null){
            Iterator<String> iterator = implementsNameList.iterator();
            int count = 0;
            while(iterator.hasNext()){
                if(count == 0){
                    stringBuilder.append(" implements ");
                }else{
                    stringBuilder.append(",");
                }
                stringBuilder.append(iterator.next());
            }
        }
    }
    public void addFields(StringBuilder stringBuilder){
        if(fieldList != null) {
            Iterator<Field> iterator = fieldList.iterator();
            while (iterator.hasNext()) {
                Field field = iterator.next();
                if(field.annotations!=null){
                    Iterator<String> iteratorAnnotations = field.annotations.iterator();
                    while(iteratorAnnotations.hasNext()) {
                        stringBuilder.append("\t");
                        stringBuilder.append(iteratorAnnotations.next());
                        stringBuilder.append("\n");
                    }
                }
                if(field.modifier!=null){
                    stringBuilder.append("\t");
                    stringBuilder.append(field.modifier);
                    stringBuilder.append(" ");
                }
                if(field.type!=null){
                    stringBuilder.append(field.type);
                    stringBuilder.append(" ");
                }
                if(field.name!=null){
                    stringBuilder.append(field.name);
                }
                if(field.value!=null){
                    stringBuilder.append(" = ");
                    stringBuilder.append(field.value);
                }
                stringBuilder.append(";\n");
            }
        }
    }
    public void addMethods(StringBuilder stringBuilder){
        if(methodList != null) {
            Iterator<Method> iterator = methodList.iterator();
            while (iterator.hasNext()) {
                Method method = iterator.next();
                if(method.annotations!=null){
                    Iterator<String> iteratorAnnotations = method.annotations.iterator();
                    while(iteratorAnnotations.hasNext()) {
                        stringBuilder.append("\t");
                        stringBuilder.append(iteratorAnnotations.next());
                        stringBuilder.append("\n");
                    }
                }
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
                stringBuilder.append("{\n");
                if(method.methodBody!=null){
                    stringBuilder.append(method.methodBody);
                }
                stringBuilder.append("\t}\n");
            }
        }
    }
}
