#include <strings.h>
#include <getopt.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "cachelab.h"
//Daniel Cohen
//dsc381




typedef unsigned long long int memAddress;

typedef struct {
  int b; //2**b words in block
  int B; //1<<b
  int s; //2**s sets in cache
  long S; //1<<s
  int E; //number of lines in set
  long long unsigned hit;
  long long unsigned miss;
  long long unsigned eviction;

} cacheStore;

typedef struct {
  memAddress tag;
  int valid;
  int counter;
} cline;
int verby;

int sniff(long S, int E, cline cache[S][E], long index, memAddress tag, long count)
{
  int evaluation = 0;
  int lineIndex;
  int minVisit = -1;
  int minIndex = 0;
  printf(" %lu ", index);
  for (lineIndex = 0; lineIndex < E; lineIndex++) {
    cline *pointer = &cache[index][lineIndex];
    if (pointer -> valid == 0) {
      evaluation = -1;
      if (verby) {
        printf(" miss ");}
      pointer->tag = tag;
      pointer->valid = 1;
      pointer->counter = count;
      break;
    } else {
      if (pointer -> tag == tag) {
        if (verby) {printf(" hit ");}
        pointer -> counter = count;
        evaluation = 1;
        break;
      }
    }
  }
  if (evaluation == 0) {
    for (lineIndex = 0; lineIndex < E; lineIndex++) {
      if (minVisit == -1 || minVisit > cache[index][lineIndex].counter) {
        minVisit = cache[index][lineIndex].counter;
        minIndex = lineIndex;
      }
    }
    if (verby) {printf( " miss eviction ");}
    cache[index][minIndex].tag = tag;
    cache[index][minIndex].counter = count;
    cache[index][minIndex].valid = 1;
  }
  return evaluation;
}


int main(int argc, char **argv)
{
  cacheStore par;
  par.hit = 0;
  par.miss = 0;
  par.eviction = 0;
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

  if (par.s == 0 || par.E == 0 || par.b == 0 || trace_file == NULL) {
    printf("%s: Missing required command line argument\n", argv[0]);
    exit(1);
  }
  par.S = par.s << 1;
  cline cache[par.S][par.E];

  char action[1];
  memAddress addr;
  int byte;

  //READ FILE
  FILE *file;
  file = fopen(trace_file, "r");

  long count = 0;
  while (fscanf(file, "%s %11x, %d", action, &addr, &byte) != EOF) {
    if (verby & ((char) action[0] != 'I')){
      printf("%s %11x %s ", action, addr, byte);
    }
      count++;
      memAddress tag;
      long setIndex;
      int decision;
      addr = addr >> par.b;
      setIndex = addr & (par.S - 1);
      tag = addr >> par.s;
      if ((char)action[0] == 'L' || (char)action[0] == 'S') {
        decision = sniff(par.S, par.E, cache, setIndex, tag, count);
        printf("\n");
      } else if ((char)action[0] == 'I') {continue;}
      else {
        decision = sniff(par.S, par.E, cache, setIndex, tag, count);
        printf("hit \n");
      }
      if (decision == 1) {par.hit++;}
      else if (decision == 0) {
        par.miss++;
        par.eviction++;
      }
      else if(decision = -1) {par.miss++;}
    }
      printSummary(par.hit, par.miss, par.eviction);
      return 0;
  }




