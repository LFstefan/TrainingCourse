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
class ReverseListBetweenIndex {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 虚拟头节点
        ListNode dummy_node = new ListNode(-1);
        dummy_node.next = head;

        ListNode pre = dummy_node;
        for(int i = 0; i < left -1; i++){
            pre = pre.next;
        }

        ListNode pre_node = pre;
        ListNode curr_node = pre.next;
        ListNode next_node = null;

        for(int i = left; i < right; i++) {
            next_node = curr_node.next;
            curr_node.next = next_node.next;
            next_node.next = pre.next;
            pre.next = next_node;
        }

        return dummy_node.next;
    }
}