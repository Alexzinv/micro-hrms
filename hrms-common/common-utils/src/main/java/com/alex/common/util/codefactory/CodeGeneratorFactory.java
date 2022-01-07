package com.alex.common.util.codefactory;

import com.alex.common.consant.CodePrefixEnum;

/**
 * @author _Alexzinv_
 * @date 2022/1/7
 * @description 编码生成工厂类
 *
 * ======================================================
 *  该工厂模式是心血来潮写着玩的，存在严重漏洞，不在任何地方使用
 * ======================================================
 */
public class CodeGeneratorFactory {

    private static CodeGeneratorFactory factory = null;

    private CodeGeneratorFactory(){ }

    public static CodeGeneratorFactory getGenerator(){
        if(factory == null){
            synchronized (CodeGeneratorFactory.class){
                if(factory == null){
                    factory = new CodeGeneratorFactory();
                }
            }
        }
        return factory;
    }

    public String getCode(CodePrefixEnum codePrefixEnum){
        CodeGeneratorAbstract codeGenerator;
        String prefix = codePrefixEnum.getPrefix();
        if(DepartmentCodeGenerator.PREFIX.equalsIgnoreCase(prefix)){
            codeGenerator = new DepartmentCodeGenerator();
        }else if(RoleCodeGenerator.PREFIX.equalsIgnoreCase(prefix)) {
            codeGenerator = new RoleCodeGenerator();
        }else {
            codeGenerator = new WorkerNumberGenerator();
        }
        return codeGenerator.getPrefix() + codeGenerator.initValue() + codeGenerator.getSuffix();
    }

    public String getCode(CodePrefixEnum codePrefixEnum, Long value){
        CodeGeneratorAbstract codeGenerator;
        String prefix = codePrefixEnum.getPrefix();
        if(DepartmentCodeGenerator.PREFIX.equalsIgnoreCase(prefix)){
            codeGenerator = new DepartmentCodeGenerator();
        }else if(RoleCodeGenerator.PREFIX.equalsIgnoreCase(prefix)) {
            codeGenerator = new RoleCodeGenerator();
        }else {
            codeGenerator = new WorkerNumberGenerator();
        }
        codeGenerator.setValue(value);
        return codeGenerator.getPrefix() + codeGenerator.getValue() + codeGenerator.getSuffix();
    }

    public String getCode(CodePrefixEnum codePrefixEnum, String oldCode){
        String codePrefix = codePrefixEnum.getPrefix();
        String value = oldCode.replace(codePrefix, "");
        long val = Long.parseLong(value);
        return getCode(codePrefixEnum, val);
    }
}
