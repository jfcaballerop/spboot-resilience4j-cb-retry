#!/usr/bin/bash

i=0
while true
do 
    ((i=i+1))
    printf "%s `date` : " "$i"
    curl --location --request GET 'http://localhost:9992/api/movies/'$i
    printf "\n"
    sleep 1
done