#include<stdio.h>
#include<stdlib.h>
#include<pcap.h>

int main()
{
	pcap_t *f;
	struct pcap_pkthdr hdr;
	int cnt=0;
	char fileName[]={"test.pcap"};
	const u_char *pack;
	char ebuff[PCAP_ERRBUF_SIZE];
	
	f=pcap_open_offline(fileName,ebuff);

	if(f==NULL){
		printf("Invalid file\n");
		return 0;
	}
	else
	{
		while(pack=pcap_next(f,&hdr))
			cnt++;
	}

	printf("%d packets were captured\n", cnt);

	return 0;
}
