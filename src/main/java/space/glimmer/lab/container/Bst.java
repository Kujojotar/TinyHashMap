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

    public int insert(Entry[] arr,int index,Bst bst){
        if(bst==null||bst.content==null){
            return index;
        }
        arr[index]=bst.content;
        int a=insert(arr,index+1,bst.left);
        return insert(arr,a,bst.right);
    }
}
