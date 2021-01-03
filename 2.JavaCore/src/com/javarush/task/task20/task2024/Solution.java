package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public void printSolution(){
        System.out.println("Solution "+node);
        for (Solution s:edges
        ) {
            System.out.println("my edge "+s.node);
        }
        System.out.println("-------------------------");
    }

    public static void main(String[] args) throws ClassNotFoundException,IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\1.txt"));
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        Solution solution3 = new Solution();
        Solution solution4 = new Solution();
        Solution solution5 = new Solution();
        Solution solution6 = new Solution();
        solution1.node =1;
        solution2.node =2;
        solution3.node =3;
        solution4.node =4;
        solution5.node =5;
        solution6.node =6;
        solution1.edges.add(solution2);
        solution2.edges.add(solution2);
        solution2.edges.add(solution4);
        solution2.edges.add(solution5);
        solution4.edges.add(solution1);
        solution4.edges.add(solution5);
        solution5.edges.add(solution4);
        solution6.edges.add(solution3);
        solution1.printSolution();
        solution2.printSolution();
        solution3.printSolution();
        solution4.printSolution();
        solution5.printSolution();
        solution6.printSolution();
        oos.writeObject(solution1);
        oos.writeObject(solution2);
        oos.writeObject(solution3);
        oos.writeObject(solution4);
        oos.writeObject(solution5);
        oos.writeObject(solution6);
        oos.close();
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream("d:\\1.txt"));
        Solution sol1 = (Solution) ois.readObject();
        Solution sol2 = (Solution) ois.readObject();
        Solution sol3 = (Solution) ois.readObject();
        Solution sol4 = (Solution) ois.readObject();
        Solution sol5 = (Solution) ois.readObject();
        Solution sol6 = (Solution) ois.readObject();
        sol1.printSolution();
        sol2.printSolution();
        sol3.printSolution();
        sol4.printSolution();
        sol5.printSolution();
        sol6.printSolution();

    }
}

