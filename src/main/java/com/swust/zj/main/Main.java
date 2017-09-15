package com.swust.zj.main;

import com.swust.zj.generator.ControllerFile;
import com.swust.zj.generator.JavaFile;
import com.swust.zj.generator.ServiceImplFile;
import com.swust.zj.generator.ServiceInterfaceFile;

import java.util.Scanner;

/**
 * Created by ZhouJie on 2017/9/15.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("generate start...");
        System.out.println("generate mybatis start...");
        try {
            GeneratorPojoAndMapper generatorSqlmap = new GeneratorPojoAndMapper();
            generatorSqlmap.generator(System.getProperty("user.dir")+"/src/main/resources/generatorConfig.xml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("generate mybatis failure...");
            return;
        }
        System.out.println("generate mybatis success...");
        //E:/generate MyProject com/swust/zj Pojo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Input fileDir(for example->E:/myDir):");
        String fileDir = scanner.next();
        System.out.print("Please Input projectName(for example->MyProject):");
        String projectName = scanner.next();
        System.out.print("Please Input packageDir(for example->com/swust/zj):");
        String packageDir = scanner.next();
        System.out.print("Please Input count(for example->3):");
        int count = scanner.nextInt();
        for(int i=0;i<count;i++){
            System.out.print("Please Input the "+i+"th pojoName(for example->User):");
            String pojoName = scanner.next();
            try {
                JavaFile pojoServiceInterface = new ServiceInterfaceFile(fileDir,projectName,packageDir,pojoName);
                JavaFile pojoServiceImpl = new ServiceImplFile(fileDir,projectName,packageDir,pojoName);
                JavaFile pojoController = new ControllerFile(fileDir,projectName,packageDir,pojoName);
                pojoServiceInterface.generate();
                pojoServiceImpl.generate();
                pojoController.generate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("copy resources start...");
        try {
            CopyResources resourceFile = new CopyResources(fileDir, projectName, packageDir);
            System.out.println("copy resources success...");
            resourceFile.copy();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("copy resources failure...");
        }
        System.out.println("generate over...");
    }
}
