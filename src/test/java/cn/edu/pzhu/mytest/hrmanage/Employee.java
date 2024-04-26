package cn.edu.pzhu.mytest.hrmanage;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangcz
 * @since 20240425
 */
@Data
public class Employee {

    private String id;

    private String userId;

    private String trueId;

    private BigDecimal salesDistPaidAmount;

    private BigDecimal afterDeductAmount;

    private String date;

}
