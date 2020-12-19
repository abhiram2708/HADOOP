import sys

for line in sys.stdin:
  line = line.strip()
  name, id, course, year = line.split('\t')
  print '\t'.join([name, id, year, course])
