#!/bin/bash

for i in {50..800..50}
  do 
    /home/mauro/Software/apache-jmeter-3.3/bin/jmeter -n -t /home/mauro/Documents/U/8vo/Infracom/InfraCom/JMeterTest/auto1.jmx -Jusers=$i | grep "summary =" >> results.dat 
 done