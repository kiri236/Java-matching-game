package com.User;

public class Userdata {
    final int N = 1000;
    private user[] users ;
    private int idx;
    private int size;

    private int[] l ;
    private int[] r ;
    public void Init()
    {
        this.idx = 0;
        this.size = 0;
        users = new user[N];
        l = new int[N];
        r = new int[N];
        r[0]=1;
        l[1]=0;

    }
    public user get_info(int k)
    {
        return users[k];
    }
    public boolean empty()
    {
        return size==0;
    }
    protected void add(user user1,int k)
    {
        users[idx]= user1;
        r[idx]=r[k];
        l[idx]=k;
        l[r[k]]=idx;
        r[k]=idx++;
        size++;
    }
    public void add(user user1)
    {
        add(user1,l[1]);
    }
    public int find(user user1) {
        if(empty())return -1;
        for (int i = r[0]; i != 1; i = r[i])
        {
            if(users[i].equals(user1))
            {
                return i;
            }
        }
        return -1;
    }
    public boolean Remove(user user1)
    {
        int k = find(user1);
        if(k==-1)return false;
        r[l[k]]=r[k];
        l[r[k]]=l[k];
        size--;
        return true;
    }

}
