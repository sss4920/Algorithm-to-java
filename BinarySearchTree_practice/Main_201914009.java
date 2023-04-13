package BinarySearchTree_practice;

import java.util.Scanner;

public class Main_201914009 {
    public static void main(String [] args){
        System.out.println("lab6_1:김수현");

// (1) 정수 키값을 저장할 공백 이진검색트리 tree를 생성
        MyBinarySearchTree tree = new MyBinarySearchTree();

// (2) 사용자가 원하는 갯수의 정수 키값을 입력받아 tree에 삽입한 후, tree 출력
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int i = 0; i < n; i++) {
            tree.add(input.nextInt());
        }
        System.out.println(tree);

// (3) 정수 키값 x, y, z를 입력받아 각각 트리에 있는지 여부를 출력
        int x = input.nextInt();
        int y = input.nextInt();
        int z = input.nextInt();
        System.out.println(tree.contains(x) + " " + tree.contains(y) + " " + tree.contains(z));
        tree.removeMax();
        System.out.println(tree);
        input.close();



    }
}
// 루트만 있으면 됨.
//t.add(7) -> node를 만들어서 7을 트리에 추가 트리는 애초에 별거없음
//즉, add라는 기능을 tree가 가지고 있어야한다.
// contains()라는 메서드를 만들어서 contains(2)하면 2가 있나? 하고 검색해서 있으면 True리턴
//t.toString()하면 중위순회해서 하나의 문자열로 출력함.
// 딴거 다알려주고 contains만 구현하고 과제가 remove하는거다.
class MyBinarySearchTree {
    // 루트 노드를 가리키는 인스턴스 변수 root (알고리즘 연습을 위해 root 만 둘 것)
    private Node root = null;
    // 노드의 구조를 정의하는 클래스 Node (알고리즘 연습을 위해 노드에 key, left, right 필드만 둘 것)
    private class Node{
        int key;
        Node left;
        Node right;
    }
    // 키 값을 매개변수로 받아 그 키값이 존재하는지 여부(true/false)를 리턴
    public boolean contains(int key){
        Node temp = root;
        while (temp != null) {
            if (temp.key == key){ // 만약 내가 찾고하는 key를 찾으면
                return true;    //탐색 성공
            }
            if (temp.key > key) { // 만약 내가 찾고자하는 key가 현재 루트키보다 작으면
                temp = temp.left; // root의 왼쪽트리를 탐색
            } else {              // 만약 크면
                temp = temp.right;//오른쪽 탐색
            }
        }
        return false; //탐색 실패

    }
    public void removeMax(){
        Node temp = root;
        Node p=null;        //부모를 null로 바꿔 놓고 하는게 맞다.
        while (temp != null){
            if (temp.right ==null){ //오른쪽으로 가다가 길없음
                if (temp.left == null){ //왼쪽도 null이면 리프노드인데
                    if (p==null){ //부모가 없다. 첨부터 즉, 루트노드라는 소리.
                        root=null; //노드 자체가 1개밖에 없다. 그러니 root 뽑아버리기.
                        break;
                    }
                    else{ //리프노드라는 뜻 부모는 있어. 즉 부모의 오른쪽에 null 넣으면 알아서 짤림.
                        p.right=null;
                    }
                }
                else { //오른쪽은 갈 곳없는데 왼쪽이 트리가 있다. 그래도 현재값이 왼쪽 트리보다 모두 클 것.
                    if (p==null) {          //그럼 현재 값이 삭제해야되는 값인데 내가 현재 루트노드인 경우
                        root = temp.left;   //루트 노드의 주소가 현재의 왼쪽 자식을 가리키도록 만들고
                        break;
                    }
                    else{ // 근데 내가 루트노드가 아닌 경우면
                        p.right = temp.left; // 현재 값을 삭제해야되니 부모의 왼쪽에 현재 나의 왼쪽 자식을 붙히면 현재 값 삭제.
                    }
                }
                temp=null;
            }
            else{ //오른쪽으로 가다 길이 있어.
                p = temp;
                temp = temp.right;
            }
        }
    }


    // 매개변수로 받은 키값을 트리에 삽입
    public void add(int key) {
        root = treeInsert(root, key);
    }

    // t를 루트로 하는 트리에 key를 삽입하고, 삽입 후 루트 노드를 리턴하는 재귀 메소드
    private Node treeInsert(Node t, int key) {
        if(t == null) {
            Node r = new Node();
            r.key = key;
            return r;
        }
        else if(key < t.key) {
            t.left = treeInsert(t.left, key);
            return t;
        }
        else if(key > t.key) {
            t.right = treeInsert(t.right, key);
            return t;
        }
        else {
            return t;  // 이미 존재하는 키값인 경우 삽입하지 않음
        }
    }
    // 트리의 키값들을 중순위 순회하여 오름차순으로 나열한 하나의 문자열을 만들어 리턴
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("tree = ");
        if(root != null) {
            inorder(result, root);
        }
        return result.toString();
    }

    // t를 루트로 하는 트리를 중순위 순회
    private void inorder(StringBuffer result, Node t) {
        if(t != null) {
            inorder(result, t.left);
            result.append(t.key + " ");
            inorder(result, t.right);
        }
    }
}