/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.engg;

/**
 *
 * @author rawan
 */
public class BSTNode {

    Employee employee;
    BSTNode left;
    BSTNode right;
    int height; // للتطوير المستقبلي لـ AVL Tree

    public BSTNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

}
