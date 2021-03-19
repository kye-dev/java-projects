package com.kyematzen.trie;

import java.util.ArrayList;

/**
 * This class implements a Trie.
 *
 * @author Sesh Venugopal
 *
 */
public class Trie {

    // prevent instantiation
    private Trie() { }

    /**
     * Builds a trie by inserting all words in the input array, one at a time,
     * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
     * The words in the input array are all lower case.
     *
     * @param allWords Input array of words (lowercase) to be inserted.
     * @return Root of trie with all words inserted from the input array
     */
    public static TrieNode buildTrie(String[] allWords) {
        TrieNode root = new TrieNode(null, null, null);
        for (int i = 0; i < allWords.length; i++) {
            insertWord(root, allWords[i], allWords, i);
        }
        // FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
        // MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
        return root;
    }
    private static void insertWord(TrieNode root,  String word, String[] words, int index) {
        TrieNode pointer = root.firstChild;
        //TrieNode pointerSibling;

        if (pointer == null) {
            //System.out.println("Pointer is null!");

            root.firstChild = new TrieNode(new Indexes(index, (short) 0, (short) (word.length() - 1)), null, null);

            System.out.println("Pointer created for " + words[root.firstChild.substr.startIndex] + "!");
            return;
        }

        String localWord = words[pointer.substr.startIndex];

        if (localWord.startsWith(Character.toString(word.charAt(0)))) {
            int numOfSimilar = 0;

            for (int i = 0; i < words[pointer.substr.wordIndex].length() - 1; i++) {
                if (words[pointer.substr.wordIndex].charAt(i) == word.charAt(i)) {
                    numOfSimilar++;
                }
            }

            pointer.substr.endIndex = (short) (word.length() - 2 - numOfSimilar);
            if (pointer.firstChild == null) {
                pointer.firstChild = new TrieNode(new Indexes(pointer.substr.wordIndex, (short) (pointer.substr.endIndex + 1), (short) (words[pointer.substr.wordIndex].length() - 1)), null, null);
            } else {
                System.out.println("first child not null.");
                System.out.println("Word: " + words[pointer.firstChild.substr.startIndex]);
                if (words[pointer.firstChild.substr.startIndex].startsWith(Character.toString(word.charAt(0)))) {
                    int numOfSimilar1 = 0;
                    for (int j = 0; j < words[pointer.firstChild.substr.wordIndex].length() - 1; j++) {
                        if (words[pointer.firstChild.substr.wordIndex].charAt(j) == word.charAt(j)) {
                            numOfSimilar1++;
                        }
                    }

                    pointer.firstChild.substr.endIndex = (short) (words[pointer.firstChild.substr.startIndex].length() - numOfSimilar1 - 1);

                    if (pointer.firstChild.sibling == null) {
                        pointer.firstChild.sibling = new TrieNode(new Indexes(index, (short) 0, (short) (word.length() - 1)), null, null);
                        System.out.println("Created child sibling " + words[pointer.firstChild.sibling.substr.wordIndex]);
                    } else {
                        System.out.println("first child sibling not null.");
                    }
                } else {

                }
            }
            //pointer.firstChild.sibling = new TrieNode(new Indexes(index, (short) (pointer.substr.endIndex + 1), (short) (word.length() - 1)), null, null);
        } else {
            //System.out.println("Word " + word + " does not begin with same first letter.");

            //System.out.println("Word " + word + " does not begin with first letter of " + localWord);
            //System.out.println("Now attempting to check other siblings...");
            if (pointer.sibling == null) {
                pointer.sibling = new TrieNode(new Indexes(index, (short) 0, (short) (word.length() - 1)), null, null);
                System.out.println("Created sibling " + word + " of " + localWord);
                return;
            }

            TrieNode localSibling = pointer.sibling;

//            while (localSibling != null) {
//                if (words[localSibling.substr.wordIndex].startsWith(Character.toString(word.charAt(0)))) {
//                    System.out.println("Word " + word + " begins with same first letter for " + words[localSibling.substr.wordIndex]);
//
//                    int numOfSimilar = 0;
//                    for (int i = 0; i < words[pointer.substr.wordIndex].length(); i++) {
//                        if (words[localSibling.substr.wordIndex].charAt(i) == word.charAt(i)) {
//                            numOfSimilar++;
//                        }
//                    }
//
//                    //System.out.println("Amount Similar: " + numOfSimilar);
//                    //String similar = word.substring(0, numOfSimilar);
//                    //System.out.println("Similar prefix: " + similar);
//                    //System.out.println("Creating new sibling overriding current..");
//
//
//                    ArrayList<String> children = new ArrayList<>();
//                    children.add(word.substring(numOfSimilar));
//                    children.add(words[localSibling.substr.wordIndex].substring(numOfSimilar));
//
//                    //TrieNode overwritten = new TrieNode(new Indexes(index, (short) 0, (short) numOfSimilar), localSibling.firstChild, localSibling.sibling);
//
//                    //System.out.println("Overwritten: " + words[overwritten.substr.wordIndex].substring(overwritten.substr.startIndex, overwritten.substr.endIndex));
//
////                    // Checks whether or not we have a child yet of this word.
//                    if (localSibling.firstChild == null) {
//                        String dictionary = children.remove(0);
//                        localSibling.firstChild = new TrieNode(new Indexes(index, (short) numOfSimilar, (short) (dictionary.length() - 1)), null, null);
//                        //System.out.println("Created child for: " + dictionary + ". ");
//                    }
//
////                    for (String child : children) {
////                        System.out.println("Child: " + child);
////                    }
//
//                    break;
//                } else {
//                    //System.out.println("Word " + word + " does not begin with same first letter for " + words[localSibling.substr.wordIndex]);
//                    if (localSibling.sibling != null) {
//                        localSibling = localSibling.sibling;
//                    } else {
//                        localSibling.sibling = new TrieNode(new Indexes(index, (short) 0, (short) (word.length() - 1)), null, null);
//                        System.out.println("Created sibling " + word + " of " + localWord);
//                    }
//                }
//            }
        }
    }

    /**
     * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the
     * trie whose words start with this prefix.
     * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
     * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell";
     * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell",
     * and for prefix "bell", completion would be the leaf node that holds "bell".
     * (The last example shows that an input prefix can be an entire word.)
     * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
     * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
     *
     * @param root Root of Trie that stores all words to search on for completion lists
     * @param allWords Array of words that have been inserted into the trie
     * @param prefix Prefix to be completed with words in trie
     * @return List of all leaf nodes in trie that hold words that start with the prefix,
     * 			order of leaf nodes does not matter.
     *         If there is no word in the tree that has this prefix, null is returned.
     */
    public static ArrayList<TrieNode> completionList(TrieNode root,
                                                     String[] allWords, String prefix) {
        /** COMPLETE THIS METHOD **/

        // FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
        // MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
        return null;
    }

    public static void print(TrieNode root, String[] allWords) {
        System.out.println("\nTRIE\n");
        print(root, 1, allWords);
    }

    private static void print(TrieNode root, int indent, String[] words) {
        if (root == null) {
            return;
        }
        for (int i=0; i < indent-1; i++) {
            System.out.print("    ");
        }

        if (root.substr != null) {
            String pre = words[root.substr.wordIndex]
                    .substring(0, root.substr.endIndex+1);
            System.out.println("      " + pre);
        }

        for (int i=0; i < indent-1; i++) {
            System.out.print("    ");
        }
        System.out.print(" ---");
        if (root.substr == null) {
            System.out.println("root");
        } else {
            System.out.println(root.substr);
        }

        for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
            for (int i=0; i < indent-1; i++) {
                System.out.print("    ");
            }
            System.out.println("     |");
            print(ptr, indent+1, words);
        }
    }
}