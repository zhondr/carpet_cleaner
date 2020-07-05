#!/usr/bin/env bash
set -e

cd $(dirname $0)

HERE=$PWD
BASE_NAME=$(cat docker-image-base-name.txt)
IMAGE_NAME=${BASE_NAME}:0.0.1

bash lib/_prepareDirs.bash

cd "${HERE}"

docker build -t "$IMAGE_NAME" docker
