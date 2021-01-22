package space.glimmer.lab.container;

/**
 * @author Lehr
 * @create: 2021-01-16
 * 二叉搜索树实现,需要完成除了getType的其他接口
 */
public class Bst implements BucketContainer{
    Entry content;
        Bst left;
        Bst right;
    /**
     * 写死的,不能修改
     * @return
     */
    @Override
    public String getType() {
        return "bst";
    }


    /**
     * 搜索某个元素并返回
     * 其中key是这个元素Entry的key
     * 搜索时比较key的hashcode与元素Entry的key的hashcode，小于时向左子树搜索，大于或等于时像右子树搜索
     * 如果出现需要往左子树搜索但左孩子为空或需要往右子树搜索但右子树为空的情况，则停止搜索并返回null值代表没有搜索到元素
     * @param key
     * @return
     */
    @Override
    public Entry searchElement(String key) {
        if(key!=null){
            if(this.content==null){
                return null;
            }
            if(key.equals(this.content.key)){
                return this.content;
            }
            if((key.hashCode()<this.hashIt(this.content.key)&&this.left==null)||
                    (key.hashCode()>=this.hashIt(this.content.key)&&this.right==null)){
                return null;
            }
            if(key.hashCode()<this.hashIt(this.content.key)){
                return this.left.searchElement(key);
            }else{
                return this.right.searchElement(key);
            }
        }else{
            if(this.content==null){
                return null;
            }
            if(this.content.key==null){
                return this.content;
            }
            if((0<this.hashIt(this.content.key)&&this.left==null)||
                    (0>=this.hashIt(this.content.key)&&this.right==null)){
                return null;
            }
            if(0<this.content.key.hashCode()){
                return this.left.searchElement(key);
            }else{
                return this.right.searchElement(key);
            }
        }
        //todo:write your code here for part-b
    }

    /**
     * 添加一个元素
     * 从根节点开始，依次比较节点key的hashcode与插入元素e的hashcode
     * 如果根节点content为null，直接将content赋为e
     * 如果e.key的hashcode位于两个节点的元素的hashcode之间（大于等于根节点且小于等于右孩子节点或小于根节点且大于左孩子节点），new一个节点储存e，并将新new的节点插入在两节点之间
     * 如果根节点没有与向对应方向递归的孩子，则new一个节点作为对应方向的孩子并插入e
     * 以上情况均未出现，则比较key的hashcode与根节点的hashcode，往对应方向递归插入节点。
     * @param e
     */
    @Override
    public void addElement(Entry e) {
        if(e.key!=null){
            if(this.content==null){
                this.content=e;
                return ;
            }
            if(e.key.hashCode()>=this.hashIt(this.content.key)){
                if(this.right==null||this.right.content==null){
                    this.right=new Bst();
                    this.right.content=e;
                    this.right.right=null;
                    this.right.left=null;
                }else if(e.key.hashCode()<=this.hashIt(this.right.content.key)){
                    Bst node=new Bst();
                    node.content=e;
                    node.right=this.right;
                    node.left=null;
                    this.right=node;
                }else{
                    this.right.addElement(e);
                }
            }
            else{
                if(this.left==null||this.left.content==null){
                    this.left=new Bst();
                    this.left.content=e;
                    this.left.right=null;
                    this.left.left=null;
                }else if(e.key.hashCode()>hashIt(left.content.key)){
                    Bst node=new Bst();
                    node.content=e;
                    node.left=this.left;
                    node.right=null;
                    this.left=node;
                }else{
                    this.left.addElement(e);
                }
            }
        }else{
            if(this.content==null){
                this.content=e;
                return ;
            }
            if(0>=this.hashIt(this.content.key)){
                if(this.right==null){
                    this.right=new Bst();
                    this.right.content=e;
                    this.right.right=null;
                    this.right.left=null;
                }else if(0<=this.hashIt(this.right.content.key)){
                    Bst node=new Bst();
                    node.content=e;
                    node.right=this.right;
                    node.left=null;
                    this.right=node;
                }else{
                    this.right.addElement(e);
                }
            }
            else{
                if(this.left==null){
                    this.left=new Bst();
                    this.left.content=e;
                    this.left.right=null;
                    this.left.left=null;
                }else if(0>hashIt(left.content.key)){
                    Bst node=new Bst();
                    node.content=e;
                    node.left=this.left;
                    node.right=null;
                    this.left=node;
                }else{
                    this.left.addElement(e);
                }
            }
        }
        //todo:write your code here for part-b
    }

    /**
     * 更新一个节点
     * 首先搜素节点，获得节点元素
     * 若没搜索到元素，则返回
     * 若搜索到元素，直接更新元素的value，之后返回
     * @param e
     */
    @Override
    public void updateElement(Entry e) {
        Entry t=this.searchElement(e.key);
        if(t==null){
            return ;
        }
        t.value=e.value;
        //todo:write your code here for part-b
    }

    /**
     * 删除一个节点
     * 首先比较key的hashcode与节点content.key的hashcode
     * 如果不相等时往对应方向递归删除，相等时先比较两个key是否相等，不相等时向右子树递归删除
     * 相等时如果节点为叶子节点，直接将content置空作为删除
     * 如果不是叶子节点且只有左孩子，从左子树中找出key的hashcode最大的content替换目标节点的content，并删除左子树key的hashcode最大的节点
     * 如果右孩子，从右子树中找出key的hashcode最小的content替换目标节点的content，并删除右子树key的hashcode最小的节点
     * @param key
     */
    @Override
    public void removeElement(String key) {
        if (key != null) {
            if (key.equals(this.content.key)) {
                if (this.left == null && this.right == null) {
                    this.content = null;
                    return;
                }
                if (this.right == null) {
                    Bst tmp = this.left;
                    if (tmp.right == null || tmp.content == null || tmp.right.content == null) {
                        this.content = tmp.content;
                        this.left = tmp.left;
                        return;
                    }
                    Bst p = tmp;
                    tmp = tmp.right;
                    while (tmp.right != null && tmp.right.content != null) {
                        tmp = tmp.right;
                        p = p.right;
                    }
                    this.content = tmp.content;
                    p.right = null;
                    return ;
                } else {
                    Bst tmp = this.right;
                    if (tmp.left == null || tmp.content == null || tmp.left.content == null) {
                        this.content = tmp.content;
                        this.right = tmp.right;
                        return;
                    }
                    Bst p = tmp;
                    tmp = tmp.left;
                    while (tmp.left != null && tmp.left.content != null) {
                        tmp = tmp.left;
                        p = p.left;
                    }
                    this.content = tmp.content;
                    p.left = null;
                    return ;
                }
            }
            if (hashIt(key) >= hashIt(this.content.key) &&this.right != null) {
                this.right.removeElement(key);
            }
            if (hashIt(key)<hashIt(this.content.key)&&this.left != null) {
                this.left.removeElement(key);
            }
        } else {
            if (this.content != null && this.content.key == null) {
                if (this.left == null && this.right == null) {
                    this.content = null;
                    return;
                }
                if (this.right == null) {
                    Bst tmp = this.left;
                    if (tmp.right == null || tmp.content == null || tmp.right.content == null) {
                        this.content = tmp.content;
                        this.left = tmp.left;
                        return;
                    }
                    Bst p = tmp;
                    tmp = tmp.right;
                    while (tmp.right != null && tmp.right.content != null) {
                        tmp = tmp.right;
                        p = p.right;
                    }
                    this.content = tmp.content;
                    p.right = null;
                } else {
                    Bst tmp = this.right;
                    if (tmp.left == null || tmp.content == null || tmp.left.content == null) {
                        this.content = tmp.content;
                        this.right = tmp.right;
                        return;
                    }
                    Bst p = tmp;
                    tmp = tmp.left;
                    while (tmp.left != null && tmp.left.content != null) {
                        tmp = tmp.left;
                        p = p.left;
                    }
                    this.content = tmp.content;
                    p.left = null;
                }
            }
            if (0 >= hashIt(this.content.key) && this.right != null) {
                this.right.removeElement(key);
            }
            if (0 < hashIt(this.content.key) && this.left != null) {
                this.left.removeElement(key);
            }
            //todo:write your code here for part-b
        }
    }

    /**
     * 按照树的"先序遍历",遍历并返回所有元素
     * @return
     */
    @Override
    public Entry[] traverse() {
        if(this==null){
            return null;
        }
        Entry[] arr=new Entry[count(this)];
        insert(arr,0,this);
        //todo:write your code here for part-b
        return arr;
    }

    /**
     * 辅助方法
     */
    public int hashIt(String key){
        return key==null?0:key.hashCode();
    }
    /**
     * 辅助方法：获取树的节点数
     */
    public static int count(Bst bst){
        if(bst==null||bst.content==null){
            return 0;
        }
        return 1+count(bst.left)+count(bst.right);
    }
    /**
    * 辅助方法：通过先序遍历向Entry数组插入元素
    */
    public int insert(Entry[] arr,int index,Bst bst){
        if(bst==null||bst.content==null){
            return index;
        }
        arr[index]=bst.content;
        int a=insert(arr,index+1,bst.left);
        return insert(arr,a,bst.right);
    }
}
