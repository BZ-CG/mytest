package cn.edu.pzhu.mytest.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 树节点
 *
 * @author zhangcz
 * @since 20201028
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    /**
     * id
     */
    @JSONField(ordinal = 1)
    private Integer id;

    /**
     * 父节点id
     */
    @JSONField(ordinal = 2)
    private Integer parentId;

    /**
     * 父节点
     */
    @JSONField(serialize = false)
    private TreeNode parentNode;

    /**
     * 子节点列表
     */
    @JSONField(ordinal = 3)
    private List<TreeNode> childrenList;

}
