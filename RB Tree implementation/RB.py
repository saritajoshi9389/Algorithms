# Author -> Sarita Joshi
"""RB Color attributes"""
RED = "RED"
BLACK = "BLACK"
class NilNode(object):
    def __init__(self):
        self.color = BLACK
"""NIL defines the sentinel node, self reference"""
NIL = NilNode()

class Node(object):
    def __init__(self, key, color=RED, left=NIL, right=NIL, p=NIL):
        self.color, self.key, self.left, self.right, self.p = (color, key, left, right, p)

# Tree Class
class Tree(object):
    def __init__(self, root=NIL):
        self.root = root

########### left_rotate() as per algorithm in Cormen
def left_rotate(T, x):
    assert (x.right != NIL)
    y = x.right
    x.right = y.left
    if y.left != NIL:
        y.left.p = x
    y.p = x.p
    if x.p == NIL:
        T.root = y
    elif x == x.p.left:
        x.p.left = y
    else:
        x.p.right = y
    y.left = x
    x.p = y

########### right_rotate() as per algorithm in Cormen
def right_rotate(T, x):
    assert (x.left != NIL)
    y = x.left
    x.left = y.right
    if y.right != NIL:
        y.right.p = x
    y.p = x.p
    if x.p == NIL:
        T.root = y
    elif x == x.p.right:
        x.p.right = y
    else:
        x.p.left = y
    y.right = x
    x.p = y

# inserts in the RBTree
def rb_insert(tree, x):
    BTreeInsert(tree, x)
    x.color = RED
    while x != tree.root and x.p.color == RED:
        if x.p == x.p.p.left:
            y = x.p.p.right
            if y.color == RED:
                x.p.color = BLACK
                y.color = BLACK
                x.p.p.color = RED
                x = x.p.p
            else:
                if x == x.p.right:
                    x = x.p
                    left_rotate(tree, x)
                x.p.color = BLACK
                x.p.p.color = RED
                right_rotate(tree, x.p.p)
        else:
            y = x.p.p.left
            if y.color == RED:
                x.p.color = BLACK
                y.color = BLACK
                x.p.p.color = RED
                x = x.p.p
            else:
                if x == x.p.left:
                    x = x.p
                    right_rotate(tree, x)
                x.p.color = BLACK
                x.p.p.color = RED
                left_rotate(tree, x.p.p)
    tree.root.color = BLACK
    
# General binary tree insert (called for RBTreee insert)
def BTreeInsert(tree, z):
    y = NIL
    x = tree.root
    while x != NIL:
        y = x
        if z.key < x.key:
            x = x.left
        else:
            x = x.right
    z.p = y
    if y == NIL:
        tree.root = z
    elif z.key < y.key:
        y.left = z
    elif z.key > y.key:
        y.right = z


# Returns minimum of tree
def tree_minimum(x):
    while x.left != NIL:
        x = x.left
    return x

# Returns the inorder successor of node 'x'
def tree_successor(x):
    if x.right != NIL:
        return tree_minimum(x.right)
    y = x.p
    while y != NIL and x == y.right:
        x = y
        y = y.p
    return y


# returns maximum of tree
def tree_maximum(x):
    while x.right != NIL:
        x = x.right
    return x


# Returns the inorder predecessor of node 'x'
def tree_predecessor(x):
    if x.left != NIL:
        return tree_maximum(x.left)
    y = x.p
    while y != NIL and x == y.left:
        x =y
        y = y.p
    return y


# Returns tree height
def tree_height(node):
    if node == NIL: return 0
    return max(1 + tree_height(node.left), 1 + tree_height(node.right))


#     """Returns the number of internal nodes in the subtree rooted at 'node'."""
def tree_count_internal(node):
    if node == NIL: return 0
    return 1 + tree_count_internal(node.left) + tree_count_internal(node.right)

# Inorder tree traversal gives sorted order for the tree
def inorderTraversal(node):
    if(node != NIL):
        inorderTraversal(node.left)
        print(node.key, node.color)
        inorderTraversal(node.right)

# Function to search a element in tree
def searchElement(node,element):
    if(subSearch(node,element)):
        print "Element Found!!",element
    else:
        print "Element not found!"

def subSearch(node,element):
    flag = False
    while(node != NIL and not flag):
        rdata = node.key
        if(element < rdata):
            node = node.left
        elif element > rdata:
            node = node.right
        else:
            flag = True
            break
        flag = subSearch(node,element)
    return flag

def subSearch1(node,element):
    resultNode = NIL
    while(node != NIL and resultNode != node):
        rdata = node.key
        if(element < rdata):
            node = node.left
        elif element > rdata:
            node = node.right
        else:
            return node
        flag = subSearch1(node,element)
    return resultNode


# displaying the tree
def displayRB(t):
    def node_value(node):
        return node.key
    def node_color(node):
        return node.color
    def visit_node(node):
        "Visit a node."
        print("  Node - %s [color=\"%s\"];" % (node_value(node), node_color(node)))
        if node.left:
            if node.left != NIL:
                visit_node(node.left)
                print("  %s -> %s ;" % (node_value(node), node_value(node.left)))
        if node.right:
            if node.right != NIL:
                visit_node(node.right)
                print("  %s -> %s ;" % (node_value(node), node_value(node.right)))

    print("red_black_tree {")
    visit_node(t.root)
    print("}")

# Reads initial tree from the input file, numbers.txt
def userInput():
    print "RB Implementation"
    with open("numbers.txt") as file:
        numbers = file.read().strip("").split(",")
    print "Initial numbers read from input file is ->",numbers
    index = 0
    tree = Tree()
    while index < len(numbers):
        rb_insert(tree,Node(int(numbers[index])))
        index += 1
    displayRB(tree)
    userInteraction(tree)

# Main function, iterates to get user input
def userInteraction(tree):
    while True:
        while True:
            input = int(raw_input('Enter the operation you want to perform: \n'
                                  '1)INSERT\n2)MAX ELEMENT\n3)MIN ELEMENT\n4)SORT\n5)SEARCH\n6)PREDECESSOR\n7)SUCCESSOR\n8)EXIT\n'))
            executeTask(tree,input)
            break

# User Interaction Function
def executeTask(tree,option):
    if option==1:
        val = int(raw_input("Enter the element to be inserted \n"))
        rb_insert(tree, Node(val))
        print "Height of the tree is -> ",tree_height(tree.root)
        displayRB(tree)
        inorderTraversal(tree.root)

    elif option ==2:
        maxNode= tree_maximum(tree.root)
        print "Height of the tree is -> ",tree_height(tree.root)
        print "Max Element is : ",maxNode.key

    elif option ==3:
        minNode = tree_minimum(tree.root)
        print "Height of the tree is -> ",tree_height(tree.root)
        print "Min Element is : ",minNode.key

    elif option == 4:
        print "Sorted Tree is as Below\n"
        inorderTraversal(tree.root)

    elif option == 5:
        required = int(raw_input("Enter element to be searched\n"))
        searchElement(tree.root,required)
    elif option == 6:
        required = int(raw_input("Enter Element for predecessor :: \n"))
        x = subSearch1(tree.root,required)
        if x!= NIL and tree_predecessor(x)!=NIL:
            print " Predecessor of",required, "is ->", tree_predecessor(x).key
        else:
            print " Element not found or predecessor does not exist"
    elif option == 7:
        required = int(raw_input("Enter element for successor\n"))
        y = subSearch1(tree.root,required)
        if y!= NIL and tree_successor(y)!=NIL:
            print " Successor of",required, "is ->", tree_successor(y).key
        else:
            print "Element not found or successor does not exist"
    elif option == 8:
        exit(0)
    else:
        print "Invalid Input!"




######################################################################
    """Tree made for testing
             5
            / \
           3   7
          /   / \
         1   6   10
"""
userInput()
userInteraction()
# tree = Tree()
# rb_insert(tree, Node(5))
# rb_insert(tree, Node(3))
# rb_insert(tree, Node(7))
# rb_insert(tree, Node(1))
# rb_insert(tree, Node(6))
# rb_insert(tree, Node(10))
# displayRB(tree)

