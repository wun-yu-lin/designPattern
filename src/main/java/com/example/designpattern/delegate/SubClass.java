package com.example.designpattern.delegate;

import lombok.Data;
import lombok.experimental.Delegate;


@Data
public class SubClass    { //Value Object


    private StudentVO studentVO;

    public SubClass(StudentClass studentClass) {
        this.studentVO = new StudentVO(studentClass); //valueObject
    }

    public class StudentVO implements ISubClassForGet {
        @Delegate(types = IStudentClass.class)
        private StudentClass studentClass;


        public StudentVO(StudentClass studentClass){
            this.studentClass = studentClass;   //entity
        }

        @Override
        public String getClassName() {
            return null;
        }
    }


    public interface IStudentClass {
        String getName();
        String getStudentId();

    }

    public interface ISubClassForGet  {
        String getClassName();
    }



    public static void main(String[] args) {
        StudentClass studentClass1 = new StudentClass(); //entity
        studentClass1.setName("小明");
        studentClass1.setStudentId("001");
        studentClass1.setClassName("一年一班");
        studentClass1.setClassId("001");

        SubClass subClass = new SubClass(studentClass1);
//        System.out.println(subClass.getStudentId());
//        System.out.println(subClass.getName());
    }




}
