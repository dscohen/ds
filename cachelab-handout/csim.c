#include <strings.h>
#include <getopt.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "cachelab.h"




typedef unsigned long long int hexAddress;

typedef struct {
  int b; 
  int B; //1<<b
  int s; /
  long S; //1<<s
  int E; 
  long long unsigned hit;
  long long unsigned miss;
  long long unsigned eviction;

} cacheStore;

typedef struct {
  hexAddress tag;
  int valid; //whether it has been written to
  int cycleer;//variable to contain "processor" cycle
} cline;

int verby;//debugging

int sniff(long S, int E, cline cache[S][E], long index, hexAddress tag, long cycle)
{
  //cache function, goes through cache to see if value is there or not.
  //will update cache with cycle value, tag (if it misses), valid to 1.
  int evaluation = 0;
  int iterator;
  int currentLeastRecent = -1;
  int leastRecent = 0;
  printf(" %lu ", index);
  for (iterator = 0; iterator < E; iterator++) {
    cline *location = &cache[index][iterator];
    if (location -> valid == 0) {
      evaluation = -1;
      if (verby) {
        printf(" miss ");
      }
      location->tag = tag;
      location->valid = 1;
      location->cycleer = cycle;
      break;
    } 
    else {
      if (location -> tag == tag) {
        if (verby) {printf(" hit ");}
        location -> cycleer = cycle;
        evaluation = 1;
        break;
      }
    }
  }
  if (evaluation == 0) {
    for (iterator = 0; iterator < E; iterator++) {
      if (currentLeastRecent == -1 || currentLeastRecent > cache[index][iterator].cycleer) {
        currentLeastRecent = cache[index][iterator].cycleer;
        leastRecent = iterator;
      }
    }
    if (verby) {printf( " miss eviction ");}
    cache[index][leastRecent].tag = tag;
    cache[index][leastRecent].cycleer = cycle;
    cache[index][leastRecent].valid = 1;
  }
  return evaluation;
}


int main(int argc, char **argv)
{
  cacheStore par;
  //set everything to zero
  bzero(&par, sizeof(par));
  char *trace_file;
  char c;
  while( (c=getopt(argc,argv,"s:E:b:t:vh")) != -1){
    switch(c){
      case 's':
        par.s = atoi(optarg);
        break;
      case 'E':
        par.E = atoi(optarg);
        break;
      case 'b':
        par.b = atoi(optarg);
        break;
      case 't':
        trace_file = optarg;
        break;
      case 'v':
        verby = 1;
        break;
      case 'h':
        exit(0);
      default:
        exit(1);
    }
  }


  par.S = 1 << par.s;
  cline cache[par.S][par.E];
  bzero(cache, par.S*par.E*sizeof(cline));

  char action[1];
  hexAddress addr;
  int byte;
  FILE *file;
  file = fopen(trace_file, "r");

  long cycle = 0;
  //for each line in file, read instruction (trace), and put it through sniff function
  while (fscanf(file, "%s %llx, %d", action, &addr, &byte) != EOF) {
    if (verby & ((char) action[0] != 'I')){
      printf("%s %llx %d ", action, addr, byte);
    }
      cycle++;
      hexAddress tag;
      long setNumber;
      int success;
      addr = addr >> par.b;
      setNumber = addr & (par.S - 1);
      tag = addr >> par.s;
      if ((char)action[0] == 'L' || (char)action[0] == 'S') {
        success = sniff(par.S, par.E, cache, setNumber, tag, cycle);
        printf("\n");
      } else if ((char)action[0] == 'I') {continue;}
      else {
        success = sniff(par.S, par.E, cache, setNumber, tag, cycle);
        par.hit++;
        //M, extra hit after sniff
        printf("hit \n");
      }
      if (success == 1) {par.hit++;}
      else if (success == 0) {
        par.miss++;
        par.eviction++;
      }
      else if(success == -1) {par.miss++;}
    }
      printSummary(par.hit, par.miss, par.eviction);
      return 0;
  }




