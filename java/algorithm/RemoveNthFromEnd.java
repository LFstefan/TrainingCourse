/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 添加虚拟节点，减少特殊场景的特殊处理，统一处理
        ListNode dummy_node = new ListNode(-1);
        dummy_node.next = head;
        ListNode nth_node = dummy_node;
        ListNode node = dummy_node;
        int i = 1;
        while(node.next != null){
            if(i > n){
                nth_node = nth_node.next;
            }
            node = node.next;
            i++;
        }
        // swapValue(nth_node, nth_node.next);
        nth_node.next = nth_node.next.next;

        return dummy_node.next;
    }
    void swapValue(ListNode a, ListNode b){
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}
