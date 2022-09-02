package com.suji.cls;

import java.util.Comparator;
import java.util.Objects;

class SortByName implements Comparator<Met> {

    @Override
    public int compare(Met o1, Met o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}



public class Met implements Comparator<Met> {

    private String returnType;
    private String name;
    private String paras;
    private String modifier;

    public static void main(String[] args) {
        Met m = new Met("int", "m1", "int,int");
        System.out.println(m);
    }

    public Met(String returnType, String name, String paras) {

        this.returnType = returnType;
        this.name = name;
        this.paras = paras;
    }

    Met(String returnType, String name, String paras, String modifier) {
        this(returnType, name, paras);
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    
    
    @Override
    public String toString() {
        return returnType + " " + name + "(" + paras + ')';
    }

    public String show() {
        return modifier + " " + returnType + " " + name + "(" + paras + ')';
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParas() {
        return paras;
    }

    public void setParas(String paras) {
        this.paras = paras;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Met other = (Met) obj;
        if (!Objects.equals(this.returnType, other.returnType)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.paras, other.paras)) {
            return false;
        }
        return true;
    }

    @Override
    public int compare(Met m1, Met m2) {
        return m1.getName().compareTo(m2.getName());
    }

}
