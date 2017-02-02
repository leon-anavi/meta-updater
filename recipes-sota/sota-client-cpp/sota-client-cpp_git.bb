SUMMARY = "SOTA Client"
DESCRIPTION = "SOTA Client application written in C++"
HOMEPAGE = "https://github.com/advancedtelematic/sota_client_cpp"
SECTION = "base"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9741c346eef56131163e13b9db1241b3"

inherit cmake systemd

S = "${WORKDIR}/git"

SRCREV = "b14c668bdf914c71e68706168aa821bcae32c8f3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
          git://github.com/advancedtelematic/sota_client_cpp \
          file://0001-CMakeLists.txt-Support-building-through-bitbake.patch \
          "

DEPENDS = "boost curl openssl dbus common-api-c++-dbus dlt-daemon pugixml"
RDEPENDS = ""

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DBUILD_WITH_DBUS_GATEWAY=ON -DSTAGING_DIR_TARGET=${STAGING_DIR_TARGET}"

do_install() {
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/build/target/sota_client ${D}${bindir}/sota_client_cpp
}

FILES_${PN} = " \
              ${bindir}/sota_client_cpp \
              "
