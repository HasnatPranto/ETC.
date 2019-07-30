#include<bits/stdc++.h>

using namespace std;

int main()
{
    int ds=150,mini=1000,n=0,i=0,dup[150],arr[150],brr[150],hp,j,lp,nowAt,inp,hp2;
    bool b=0;
    stack<int> stk;
    queue<int>qu;

    memset(arr,0,sizeof(arr));
    memset(brr,0,sizeof(brr));
    memset(dup,0,sizeof(dup));

    cout << "Enter The Block Addresses(-1 to finish): ";

    while(cin >> inp && inp!=-1) {
        arr[inp]=1;
        brr[inp]=1;
        dup[n++]=inp;
    }

    cout << "Head Pointer at: "; cin >> hp;
    hp2=hp;
    cout <<"Order of Servicing The Request In\nSSTF: ";

    for(i=0;i<ds;i++)
    {
        b=0;
        for(j=0;j<ds;j++){

            if(arr[j]==1 && mini>abs(j-hp)){
                b=1;
                mini= abs(j-hp);
                nowAt=j;
                lp=j;
            }
        }
        if(b)qu.push(nowAt);
        mini=1000;
        arr[lp]=0;
        hp=nowAt;
    }
    while(!qu.empty()){ cout << qu.front() <<"-> "; qu.pop();}
    cout << "end\n\nFCFS: ";

    for(i=0;i<n;i++)
    {
        if(i==n-1) cout << dup[i] <<"-> end\n";
        else
        cout << dup[i] <<"-> ";
    }

    sort(dup,dup+n);

    cout <<"\nSCAN(left to right): ";

    for(i=hp2;i>=0;i--){
        if(brr[i]==1) cout << i <<"-> ";}
    for(i=hp2+1;i<ds;i++){
        if(brr[i]==1) cout << i <<"-> ";}

    cout << "end\n";

    cout <<"\nC-SCAN(right direction): ";

     for(i=hp2;i<ds;i++){
        if(brr[i]==1) cout << i <<"-> ";}
    for(i=0;i<hp2;i++)
         if(brr[i]==1) cout << i <<"-> ";
    cout << "end\n";

    cout <<"\nLook(left to right): ";

    for(i=hp2;i>=dup[0];i--){
        if(brr[i]==1) cout << i <<"-> ";}
    for(i=hp2+1;i<=dup[n-1];i++)
        if(brr[i]==1) cout << i <<"-> ";
    cout << "end\n";
    return 0;
}
