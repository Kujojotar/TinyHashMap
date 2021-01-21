package space.glimmer.lab.container;

import java.util.LinkedList;

/**
 * @author Lehr
 * @create: 2021-01-16
 * 链表的实现,需要完成除了getType的其他接口
 * 你可以选择自己手写一遍实现,也可以学习使用Java 泛型容器里的LinkedList实现好了的链表直接写这里
 */
public class NodeList implements BucketContainer{
    /**
     * 成员变量
     * 词条content存储键值对
     * 链表节点next指向下一个链表节点
     */
    private Entry content;
    private NodeList next;

    /**
     * 写死的,不能修改,用来判断具体的数据结构
     * @return
     */
    @Override
    public String getType() {
        return "nodelist";
    }

    /**
     * 搜索某个元素并返回
     * 其中key是这个元素Entry的key
     * @param key
     * @return
     */
    @Override
    public Entry searchElement(String key) {
        NodeList tmp=this;
        if(key!=null){
            while (tmp != null) {
                if (tmp.content!=null&&key.equals(tmp.content.key)) {
                    return tmp.content;
                }
                tmp = tmp.next;
            }
            return null;
        }else {
            while(tmp!=null){
                if(tmp.content!=null&&tmp.content.key==null){
                    return tmp.getContent();
                }
                tmp=tmp.next;
            }
            return null;
        }
    }

    /**
     * 添加一个元素
     * @param e
     */
    @Override
    public void addElement(Entry e) {
        NodeList tmp=this;
        if(this.content==null){
            this.content=e;
            return ;
        }
        while(tmp.next!=null&&tmp.content!=null){
            tmp=tmp.next;
        }
        if(tmp.content==null){
            tmp.content=e;
            return ;
        }
        tmp.next=new NodeList();
        tmp=tmp.next;
        tmp.content=e;
        return ;
        //todo:write your code here for part-a
    }


    /**
     * 更新一个节点
     * @param e
     */
    @Override
    public void updateElement(Entry e) {
        NodeList tmp=this;
        Entry d=this.searchElement(e.key);
        if(d==null){
            return ;
        }
        d.value=e.value;
        return ;
        //todo:write your code here for part-a
    }

    /**
     * 删除一个节点
     * @param key
     */
    @Override
    public void removeElement(String key) {
        NodeList tmp=this;
        if(tmp==null){
            return;
        }
        if(key!=null) {
            if(tmp.content==null){
                return ;
            }
            while(tmp!=null&&tmp.content!=null&&!key.equals(tmp.content.key)){
                tmp=tmp.next;
            }
            if (tmp == null || tmp.content == null) {
                return ;
            }else{
                while(tmp.next!=null){
                    tmp.content=tmp.next.content;
                    tmp=tmp.next;
                }
                tmp.content=null;
            }
        }else{
            if (tmp.content==null) {
                return;
            }
            while(tmp!=null&&tmp.content!=null&&tmp.content.key!=null){
                tmp=tmp.next;
            }
            if (tmp == null || tmp.content == null) {
                return ;
            }else{
                while(tmp.next!=null){
                    tmp.content=tmp.next.content;
                    tmp=tmp.next;
                }
                tmp.content=null;
            }
        }
        return ;
        //todo:write your code here for part-a
    }

    /**
     * 遍历并返回所有元素
     * @return
     */
    @Override
    public Entry[] traverse() {
        if(this==null) {
            return null;
        }
        int i=1;
        NodeList tmp=this;
        while(tmp.next!=null){
            tmp=tmp.next;
            i++;
        }
        Entry[] arr=new Entry[i];
        tmp=this;
        for(int j=0;j<i;j++){
            arr[j]=tmp.content;
            tmp=tmp.next;
        }
        return arr;
    }

    public int getSize(){
        int n=1;
        NodeList tmp=this;
        while(tmp.next!=null){
            tmp=tmp.next;
            n++;
        }
        return n;
    }

    public Entry getContent(){
        return content;
    }

    public NodeList getNext(){
        return next;
    }
}
