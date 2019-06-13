package com.sucre.destiny.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2019-06-12
 */
@TableName("d_person")
public class DPersonDO implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 命造名称
     */
    @TableField("name")
    private String name;

    /**
     * 详细八字
     */
    @TableField("detail")
    private String detail;

    /**
     * 创建人id
     */
    @TableField("create_id")
    private Integer createId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    @Override
    public String toString() {
        return "DPersonDO{" +
        "id=" + id +
        ", name=" + name +
        ", detail=" + detail +
        ", createId=" + createId +
        "}";
    }
}
