package treedouble;


import java.io.IOException;


class Stack{
    private int maxSize;
    private Node[] stack;
    private int top;

    public Stack(int size){
        this.maxSize = size;
        this.stack = new Node[this.maxSize];
        this.top = -1;
    }

    public void push(Node n){
        this.stack[++this.top] = n;
    }

    public Node pop(){
        return this.stack[this.top--];
    }

    public boolean isEmpty(){
        return (this.top == -1);
    }
}

class Person{
    public int id = 1;


    public Person(){
        for (int i = 0; i < id; i++) {
            id = (int) (Math.random() * 100 -100);
        }
    }
}

class Node{
    public Person person;
    public Node leftChild;
    public Node rightChild;

    public void display(){
        System.out.println("Name: "+person.id);
    }
}

class Tree{

    private Node root;

    public void insert(Person person){
        Node node = new Node();
        node.person = person;
        if (root == null){
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (person.id < current.person.id){
                    current = current.leftChild;
                    if (current == null){
                        parent.leftChild = node;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null){
                        parent.rightChild = node;
                        return;
                    }
                }
            }
        }
    }

    public void displayTree(){
        Stack stack = new Stack(100);
        stack.push(root);
        int nBlanks = 6;
        boolean isRowEmpty = false;

        while (!isRowEmpty) {
            Stack localStack = new Stack(100);
            isRowEmpty = true;
            for(int i=0;i<nBlanks;i++){
                System.out.print(" ");
            }
            while (!stack.isEmpty()) {
                Node temp = stack.pop();
                if (temp != null){
                    temp.display();
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null || temp.rightChild != null){
                        isRowEmpty = false;
                    }
                }else{
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println(" ");
            nBlanks = nBlanks / 2;
            while (!localStack.isEmpty()) {
                stack.push(localStack.pop());
            }
        }
    }

}

public class TreeApp {


    public static void main(String[] args){
        Tree theTree = new Tree();
        theTree.insert(new Person());

        theTree.displayTree();
    }
}
