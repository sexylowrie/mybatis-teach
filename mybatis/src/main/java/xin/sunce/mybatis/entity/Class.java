package xin.sunce.mybatis.entity;

import java.io.Serializable;

public class Class implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.id
     *
     * @mbg.generated Sun Feb 17 15:59:52 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.name
     *
     * @mbg.generated Sun Feb 17 15:59:52 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.id
     *
     * @return the value of class.id
     * @mbg.generated Sun Feb 17 15:59:52 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.id
     *
     * @param id the value for class.id
     * @mbg.generated Sun Feb 17 15:59:52 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.name
     *
     * @return the value of class.name
     * @mbg.generated Sun Feb 17 15:59:52 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.name
     *
     * @param name the value for class.name
     * @mbg.generated Sun Feb 17 15:59:52 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}