package xin.sunce.mybatis.dao;

import org.apache.ibatis.annotations.Param;

public interface ClassDao {
    public int updateClassName(@Param("name") String className, @Param("id") int id);
}