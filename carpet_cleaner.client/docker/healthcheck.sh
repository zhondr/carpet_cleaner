#!/bin/sh

curl -f http://localhost:80/health || exit 1
