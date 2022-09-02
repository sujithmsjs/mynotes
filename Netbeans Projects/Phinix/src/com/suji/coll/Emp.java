package com.suji.coll;
import java.io.Serializable;



public class Emp implements Comparable,Serializable {

    private int empid;
    private String name;
    private float sal;
    private String loc;

    public Emp() {
    }

    public Emp(int empid, String name, float sal, String loc) {
        this.empid = empid;
        this.name = name;
        this.sal = sal;
        this.loc = loc;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Emp{" + "empid=" + empid + ", name=" + name + ", sal=" + sal + ", loc=" + loc + '}';
    }

    @Override
    public int compareTo(Object o) {
        
        Emp e = (Emp) o;
        
        if(e.getEmpid() != this.empid) {
            return e.getEmpid() - this.empid;
        }

        if(! e.getName().equals(getName())){
            return this.getName().compareTo(e.getName());
        }
        
        return 0;
    }
}
