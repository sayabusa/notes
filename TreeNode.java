import java.util.*;

/**
 * 树的遍历，递归，栈模拟递归，另外还有不用栈而且只需要常数空间和线性时间的神奇Morris遍历算法
 * @param <T>
 */
@Data
public class TreeNode<T> {
    private T val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {}
    public TreeNode(T v) {
        this.val = v;
    }

    public TreeNode(T v, TreeNode l, TreeNode r) {
        this.val = v;
        this.left = l;
        this.right = r;
    }

    private void visit(TreeNode t) {
        System.out.print(t.val + ", ");
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            visit(root);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 前序非递归，先根节点进栈pop，然后友子树先进，左子树后进
     * @param root
     */
    public void preOrderNoRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> list = new LinkedList<>();
        list.push(root);
        while (!list.isEmpty()) {
            TreeNode treeNode = list.pop();
            if (treeNode != null) {
                visit(treeNode);
                // 栈先进后出，因此友子树先进栈
                list.push(treeNode.right);
                list.push(treeNode.left);
            }
        }
    }

    /**
     * 前序非递归，先访问所有左子树并将根入栈，然后依次访问子树的右子树
     * @param root
     */
    public void preOrderNoRecur2(TreeNode root) {
        Deque<TreeNode> list = new LinkedList<>();
        while (root != null || !list.isEmpty()) {
            while (root != null) {
                visit(root);
                list.push(root);
                root = root.left;
            }
            if (!list.isEmpty()) {
                root = list.pop().right;
            }
        }
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            visit(root);
            inOrder(root.right);
        }
    }

    public void inOrderNoRecur(TreeNode root) {
        Deque<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (root != null || !list.isEmpty()) {
            while (root != null) {
                list.push(root);
                root = root.left;
            }
            root = list.pop();
            visit(root);
            root = root.right;
        }
    }

    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            visit(root);
        }
    }

    /**
     * 后序遍历非递归，顺序是左右根，倒过来就是根右左，就是前序的左右子树顺序颠倒一下
     * @param root
     */
    public void postOrderNoRecur(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> list = new LinkedList<>();
        list.push(root);
        List<T> res = new ArrayList<>();
        while (!list.isEmpty()) {
            TreeNode treeNode = list.pop();
            if (treeNode != null) {
                res.add((T)treeNode.val);
                list.push(treeNode.left);
                list.push(treeNode.right);
            }
        }
        Collections.reverse(res);
        for (T t : res) {
            System.out.print(t + ", ");
        }
    }

    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> list = new LinkedList<>();
        list.add(root);
        TreeNode temp;
        while (!list.isEmpty()) {
            visit(list.peek());
            temp = list.pop();
            if (temp.left != null) {
                list.add(temp.left);
            }
            if (temp.right != null) {
                list.add(temp.right);
            }
        }
    }
}
