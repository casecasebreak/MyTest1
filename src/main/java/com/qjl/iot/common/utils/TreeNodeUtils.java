package com.qjl.iot.common.utils;//package common.utils;
//
//import com.lenovo.bpms.modules.sys.entity.TreeNode;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author Joe
// */
//public class TreeNodeUtils {
//
//    /**
//     * 遍历查找节点
//     * @param treeNodes
//     * @param pid
//     * @param childNode
//     * @param lastChildNode
//     * @return
//     */
//    public static List<TreeNode> treeMenuList(List<TreeNode> treeNodes, int pid, List<TreeNode> childNode, List<TreeNode> lastChildNode) {
//
//        List<TreeNode> tempTreeNode = new ArrayList<TreeNode>();
//        List<TreeNode> tempTreeNode1 = new ArrayList<TreeNode>();
//        for (TreeNode node : treeNodes) {
//            if (node.getpId() == pid) {
//                //说明存在子节点
//                tempTreeNode1 = treeMenuList(treeNodes, node.getId(), childNode, lastChildNode);
//                if (tempTreeNode1.isEmpty()) {
//                    //不存在子节点
//                    lastChildNode.add(node);
//                }
//                childNode.add(node);
//                //用于让上一级判断是否存在子节点
//                //因为存在子节点则tempTreeNode不为空
//                //函数结束后返回tempTreeNode给上一级以供判断
//                tempTreeNode.add(node);
//            }
//
//        }
//        return tempTreeNode;
//    }
//
//
//}
