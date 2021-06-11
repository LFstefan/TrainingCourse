import java.util.HashMap;
import java.util.Map;

public class TreeTest {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static Map<Integer, Integer> cache = new HashMap<>();
    public static int pre_p = 0;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            cache.put(inorder[i], i);
        if((preorder.length == inorder.length)&& (preorder.length == 1)) return new TreeNode(preorder[0]);
        pre_p = 0;
        return build(preorder, inorder, 0, inorder.length-1);
    }

    public static TreeNode build(int[] preorder, int[] inorder, int in_start, int in_end){
        if(in_start > in_end) return null;

        TreeNode root = new TreeNode(preorder[pre_p++]);
        root.left = build(preorder, inorder, in_start, cache.get(root.val)-1);
        root.right = build(preorder, inorder, cache.get(root.val)+1, in_end);

        return root;

    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode root = buildTree(preorder, inorder);
        System.out.println("done");
    }
}