package com.swust.zj.generator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhouJie on 2017/9/14.
 */
public class ControllerFile extends JavaFile {
    private String pojoName;
    private List<String> importNameList;
    private List<String> classAnnotationNameList;
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
    public ControllerFile(String fileDir, String projectName,String packageDir,String pojoName){
        super(fileDir,projectName,pojoName+"Controller",packageDir,"controller","class",pojoName+"Controller");
        this.pojoName = pojoName;
        controllerInit();
    }
    public void controllerInit(){
        //initImport
        importNameList = new LinkedList<>();
        importNameList.add("java.io.File");
        importNameList.add("java.io.FileInputStream");
        importNameList.add("java.io.InputStream");
        importNameList.add("java.io.OutputStream");
        importNameList.add("java.util.List");
        importNameList.add("javax.servlet.http.HttpServletRequest");
        importNameList.add("javax.servlet.http.HttpServletResponse");
        importNameList.add("org.joda.time.DateTime");
        importNameList.add("org.springframework.beans.factory.annotation.Autowired");
        importNameList.add("org.springframework.stereotype.Controller");
        importNameList.add("org.springframework.web.bind.annotation.PathVariable");
        importNameList.add("org.springframework.web.bind.annotation.RequestMapping");
        importNameList.add("org.springframework.web.bind.annotation.RequestMethod");
        importNameList.add("org.springframework.web.bind.annotation.ResponseBody");
        importNameList.add("org.springframework.web.multipart.MultipartFile");
        importNameList.add(getProjectPackageStanderString()+".pojo.Tb"+pojoName);
        importNameList.add(getProjectPackageStanderString()+".service."+pojoName+"Service");
        importNameList.add(getProjectPackageStanderString()+".util.IDUtil");
        importNameList.add(getProjectPackageStanderString()+".util.PageBean");
        importNameList.add(getProjectPackageStanderString()+".util.Result");
        importNameList.add("io.swagger.annotations.Api");
        importNameList.add("io.swagger.annotations.ApiOperation");
        //initClassAnnotations
        classAnnotationNameList = new LinkedList<>();
        classAnnotationNameList.add("@Api");
        classAnnotationNameList.add("@Controller");
        classAnnotationNameList.add("@RequestMapping(\"/"+pojoName.toLowerCase()+"\")");
        //initFields
        fieldList = new LinkedList<>();
        List<String> annotations = new LinkedList<>();
        annotations.add("@Autowired");
        Field field = new Field(annotations,"private",pojoName+"Service",pojoName.toLowerCase()+"Service",null);
        fieldList.add(field);
        //initMethods
        methodList = new LinkedList<>();
        //add
        annotations = new LinkedList<>();
        annotations.add("@ApiOperation(value = \"add "+pojoName.toLowerCase()+"\",httpMethod=\"POST\",response=Result.class)");
        annotations.add("@ResponseBody");
        annotations.add("@RequestMapping(\"/add\")");
        String methodBody =
                "\t\ttry{\n"+
                "\t\t\t"+pojoName.toLowerCase()+"Service.add("+pojoName.toLowerCase()+");\n"+
                "\t\t\treturn Result.success();\n"+
                "\t\t}catch(Exception e){\n"+
                "\t\t\te.printStackTrace();\n"+
                "\t\t\treturn Result.failure();\n"+
                "\t\t}\n";
        Method method = new Method(annotations,"public","Object","add","Tb"+pojoName + " " + pojoName.toLowerCase(),null, methodBody);
        methodList.add(method);
        //delete
        annotations = new LinkedList<>();
        annotations.add("@ApiOperation(value = \"delete "+pojoName.toLowerCase()+"\",httpMethod=\"GET\",response=Result.class)");
        annotations.add("@ResponseBody");
        annotations.add("@RequestMapping(\"/delete/{id}\")");
        methodBody =
                "\t\ttry{\n"+
                "\t\t\t"+pojoName.toLowerCase()+"Service.delete(id);\n"+
                "\t\t\treturn Result.success();\n"+
                "\t\t}catch(Exception e){\n"+
                "\t\t\te.printStackTrace();\n"+
                "\t\t\treturn Result.failure();\n"+
                "\t\t}\n";
        method = new Method(annotations,"public","Object","delete","@PathVariable Integer id",null, methodBody);
        methodList.add(method);
        //update
        annotations = new LinkedList<>();
        annotations.add("@ApiOperation(value = \"update "+pojoName.toLowerCase()+"\",httpMethod=\"POST\",response=Result.class)");
        annotations.add("@ResponseBody");
        annotations.add("@RequestMapping(\"/update\")");
        methodBody =
                "\t\ttry{\n"+
                "\t\t\t"+pojoName.toLowerCase()+"Service.update("+pojoName.toLowerCase()+");\n"+
                "\t\t\treturn Result.success();\n"+
                "\t\t}catch(Exception e){\n"+
                "\t\t\te.printStackTrace();\n"+
                "\t\t\treturn Result.failure();\n"+
                "\t\t}\n";
        method = new Method(annotations,"public","Object","update","Tb"+pojoName + " " + pojoName.toLowerCase(),null, methodBody);
        methodList.add(method);
        //get
        annotations = new LinkedList<>();
        annotations.add("@ApiOperation(value = \"get "+pojoName.toLowerCase()+"\",httpMethod=\"GET\",response=Result.class)");
        annotations.add("@ResponseBody");
        annotations.add("@RequestMapping(\"/get/{id}\")");
        methodBody =
                "\t\ttry{\n"+
                "\t\t\tTb"+pojoName+" "+pojoName.toLowerCase()+" = "+pojoName.toLowerCase()+"Service.get(id);\n"+
                "\t\t\treturn Result.success("+pojoName.toLowerCase()+");\n"+
                "\t\t}catch(Exception e){\n"+
                "\t\t\te.printStackTrace();\n"+
                "\t\t\treturn Result.failure();\n"+
                "\t\t}\n";
        method = new Method(annotations,"public","Object","get","@PathVariable Integer id",null, methodBody);
        methodList.add(method);
        //getAll
        annotations = new LinkedList<>();
        annotations.add("@ApiOperation(value = \"getAll "+pojoName.toLowerCase()+"\",httpMethod=\"GET\",response=Result.class)");
        annotations.add("@ResponseBody");
        annotations.add("@RequestMapping(\"/getAll\")");
        methodBody =
                "\t\ttry{\n"+
                        "\t\t\tList<Tb"+pojoName+"> all = "+pojoName.toLowerCase()+"Service.getAll();\n"+
                        "\t\t\treturn Result.success(all);\n"+
                        "\t\t}catch(Exception e){\n"+
                        "\t\t\te.printStackTrace();\n"+
                        "\t\t\treturn Result.failure();\n"+
                        "\t\t}\n";
        method = new Method(annotations,"public","Object","getAll","",null, methodBody);
        methodList.add(method);
        //getPage
        annotations = new LinkedList<>();
        annotations.add("@ApiOperation(value = \"getPage "+pojoName.toLowerCase()+"\",httpMethod=\"GET\",response=Result.class)");
        annotations.add("@ResponseBody");
        annotations.add("@RequestMapping(\"/getPage\")");
        methodBody =
                "\t\ttry{\n"+
                        "\t\t\tPageBean<Tb"+pojoName+"> pageBean = "+pojoName.toLowerCase()+"Service.getPage(page,rows);\n"+
                        "\t\t\tpageBean.setUrl(\"/user/getPage\");\n"+
                        "\t\t\treturn Result.success(pageBean);\n"+
                        "\t\t}catch(Exception e){\n"+
                        "\t\t\te.printStackTrace();\n"+
                        "\t\t\treturn Result.failure();\n"+
                        "\t\t}\n";
        method = new Method(annotations,"public","Object","getPage","int page,int rows",null, methodBody);
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
    public void addImplements(StringBuilder stringBuilder){}
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
