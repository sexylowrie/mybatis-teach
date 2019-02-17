package xin.sunce.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import xin.sunce.mybatis.entity.Student;

import java.util.HashMap;

public interface StudentDao {
    public Student getStudentById(int id);

    public int addStudent(Student student);

    public int updateStudentName(@Param("name") String name, @Param("id") int id);

    public HashMap<String,String> getStudentByIdWithClassInfo(int id);
}