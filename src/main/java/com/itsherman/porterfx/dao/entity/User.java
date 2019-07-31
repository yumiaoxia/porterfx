package com.itsherman.porterfx.dao.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author yumiaoxia 创建时间：2019/7/31
 * 审核人： 未审核    审核日期: /
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "account")})
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;
}
