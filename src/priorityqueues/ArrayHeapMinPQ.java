// package priorityqueues;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.NoSuchElementException;
//
// public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {
//     // IMPORTANT: Do not rename these fields or change their visibility.
//     // We access these during grading to test your code.
//     static final int START_INDEX = 0;
//     List<PriorityNode<T>> items;
//     HashMap<T, Integer> map;
//
//     public ArrayHeapMinPQ() {
//         items = new ArrayList<>();
//         map = new HashMap<T, Integer>();
//     }
//
//     // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
//     // You'll probably want to add more helper methods like this one to make your code easier to read.
//     /**
//      * A helper method for swapping the items at two indices of the array heap.
//      */
//     public void swap(int a, int b) {
//         PriorityNode<T> holdA = items.get(a);
//         PriorityNode<T> holdB = items.get(b);
//         items.set(a, holdB);
//         items.set(b, holdA);
//         map.put(items.get(a).getItem(), a);
//     }
//
//     @Override
//     public void add(T item, double priority) {
//         if (item == null || map.containsKey(item)) {
//             throw new IllegalArgumentException();
//         }
//         int saveIndex = 0;
//         PriorityNode<T> addNode = new PriorityNode<T>(item, priority);
//         items.add(addNode);
//         if (items.size() > 1) {
//             saveIndex = percUp(items.size() - 1);
//         }
//         map.put(item, saveIndex);
//     }
//
//     @Override
//     public boolean contains(T item) {
//         return map.containsKey(item);
//     }
//
//     @Override
//     public T peekMin() {
//         if (items.size() == 0) {
//             throw new NoSuchElementException();
//         }
//         return items.get(START_INDEX).getItem();
//     }
//
//     @Override
//     public T removeMin() {
//         if (items.size() == 0) {
//             throw new NoSuchElementException();
//         }
//         T save = items.get(START_INDEX).getItem();
//         if (items.size() == 1) {
//             map.remove(items.get(0).getItem());
//             items.remove(START_INDEX);
//         } else {
//             save = items.get(START_INDEX).getItem();
//             map.remove(save);
//             swap(0, items.size() - 1);
//             items.remove(items.size() - 1);
//             if (items.size() > START_INDEX && items.size() > 1) {
//                 int index = percDown(START_INDEX);
//                 map.put(items.get(index).getItem(), index);
//             }
//         }
//         return save;
//     }
//
//     @Override
//     //Changes the priority of the given item.
//     public void changePriority(T item, double priority) {
//         int mapIndex = 0;
//         if (!map.containsKey(item)) {
//             throw new NoSuchElementException();
//         }
//         double storePriority = 0;
//         if (map.get(item) < items.size()) {
//             storePriority = items.get(map.get(item)).getPriority();
//         }
//         items.get(map.get(item)).setPriority(priority);
//         if (storePriority < priority) {
//             mapIndex = percDown(map.get(item));
//         } else {
//             mapIndex = percUp(map.get(item));
//         }
//         map.put(item, mapIndex);
//     }
//
//     @Override
//     public int size() {
//         return items.size();
//     }
//
//     private int percUp(int indexSpot) {
//         indexSpot = items.size() - 1;
//         while (indexSpot != START_INDEX &&
//               (items.get(indexSpot).getPriority() < items.get(indexSpot / 2).getPriority())) {
//             swap(indexSpot, (indexSpot - 1) / 2);
//             //map.put(items.get(indexSpot).getItem(), indexSpot);
//             indexSpot = (indexSpot - 1) / 2;
//         }
//
//     }
//
//     private int percDown(int tracker) {
//         if (items.size() == 2) {
//             if (items.get(0).getPriority() > items.get(1).getPriority()) {
//                 swap(0, 1);
//             }
//         } else {
//             if (tracker * 2 + 2 <= items.size() || items.size() < 5) {
//                 if (tracker >= size() / 2 ||
//                     tracker * 2 + 2 == items.size() && (items.get(tracker * 2 + 1).getPriority() >
//                         items.get(tracker).getPriority())
//                     ||
//                     (items.get(tracker * 2 + 1).getPriority() > items.get(tracker).getPriority() &&
//                         items.get(tracker * 2 + 2).getPriority() > items.get(tracker).getPriority())
//                 ) {
//                     return tracker;
//                 } else if (tracker * 2 + 2 < items.size()) {
//                     if (items.get(2 * tracker + 1).getPriority() < items.get(2 * tracker + 2).getPriority() ||
//                     items.get(2 * tracker + 1).getPriority() == items.get(2 * tracker + 2).getPriority()) {
//                         swap(tracker, tracker * 2 + 1);
//                         //map.put(items.get(tracker).getItem(), tracker);
//                         tracker = tracker * 2 + 1;
//                     } else {
//                         swap(tracker, tracker * 2 + 2);
//                         tracker = tracker * 2 + 2;
//                     }
//                     tracker = percDown(tracker);
//                 } else if (tracker * 2 + 2 == items.size() && items.get(2 * tracker + 1).getPriority()
//                         < items.get(tracker).getPriority()) {
//                         swap(tracker, tracker * 2 + 1);
//                         //map.put(items.get(tracker).getItem(), tracker);
//                         tracker = tracker * 2 + 1;
//                         percDown(tracker);
//                 } else if (items.size() < 5) {
//                     if (items.get(2 * tracker + 1).getPriority() < items.get(tracker).getPriority()) {
//                         swap(tracker, tracker * 2 + 1);
//                         tracker = tracker * 2 + 1;
//                     }
//                 }
//             }
//         }
//
//         return tracker;
//     }
//
// }
