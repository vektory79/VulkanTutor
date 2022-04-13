#!/bin/bash

# https://www.internalpointers.com/post/build-binary-deb-package-practical-guide

BIN_URL=$(curl -s https://storage.googleapis.com/shaderc/badges/build_link_linux_clang_release.html | grep -oe "https.*tgz")

VERSION=$(echo "$BIN_URL" | grep -oe "https.*tgz" | grep -oE "[0123456789-]+/install" | grep -oE "[0123456789-]+")
VERSION=$(echo "${VERSION//-/.}")

VARIANT=1
PACKGE_DIR=glslc_${VERSION}-${VARIANT}_amd64

mkdir ${PACKGE_DIR}
cd ${PACKGE_DIR}

curl -s $BIN_URL | tar zxvf -

mkdir -p usr/local
mv install/* usr/
rm -rf install

mkdir DEBIAN
echo "Package: gslsc" > DEBIAN/control
echo "Version: ${VERSION}" >> DEBIAN/control
echo "Architecture: amd64" >> DEBIAN/control
echo "Maintainer: Viktor Verbitsky <vektory79@gmail.com>" >> DEBIAN/control
echo "Description: A collection of tools, libraries and tests for shader compilation." >> DEBIAN/control
echo "Depends: vulkan-tools, libvulkan-dev, vulkan-validationlayers-dev" >> DEBIAN/control
echo "Recommends: libglfw3-dev, libglm-dev" >> DEBIAN/control

cd ..
dpkg-deb --build --root-owner-group ${PACKGE_DIR}
sudo dpkg -i ${PACKGE_DIR}.deb
