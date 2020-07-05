#!/bin/sh

# Generate configs
dockerize \
  -template /etc/nginx/nginx.conf.template.txt:/etc/nginx/nginx.conf

# Start nginx
nginx -g 'daemon off;' &

# Trap terminate signals
trap 'nginx -s stop' TERM QUIT

# Wait for nginx to terminate
wait
