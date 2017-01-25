SUMMARY = "SOTA Client"
DESCRIPTION = "SOTA Client application written in C++"
HOMEPAGE = "https://github.com/advancedtelematic/sota_client_cpp"
SECTION = "base"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9741c346eef56131163e13b9db1241b3"

inherit cmake systemd

S = "${WORKDIR}/git"

SRCREV = "a974dc2eea47594a6177f4c69c2d937a819aa7b3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	git://github.com/advancedtelematic/sota_client_cpp \
        file://0001-CMakeLists-Remove-dbus-and-CommonAPI.patch \
	"

DEPENDS = "boost curl openssl dbus common-api-c++-dbus dlt-daemon"
RDEPENDS = ""

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DBUILD_WITH_DBUS_GATEWAY=ON"

do_configure_prepend() {
  if [ ! -e ${STAGING_DIR_TARGET}/${includedir}/dbus ]; then
    ln -s ${STAGING_DIR_TARGET}/${includedir}/dbus-1.0/dbus/ ${STAGING_DIR_TARGET}/${includedir}/dbus
  fi

  if [ ! -e ${STAGING_DIR_TARGET}/${includedir}/dbus/dbus-arch-deps.h ]; then
    ln -s ${STAGING_DIR_TARGET}/${libdir}/dbus-1.0/include/dbus/dbus-arch-deps.h ${STAGING_DIR_TARGET}/${includedir}/dbus/dbus-arch-deps.h
  fi

  if [ ! -e ${STAGING_DIR_TARGET}/${includedir}/CommonAPI ]; then
    ln -s ${STAGING_DIR_TARGET}/${includedir}/CommonAPI-3.1/CommonAPI/ ${STAGING_DIR_TARGET}/${includedir}/CommonAPI
  fi
}

do_install() {
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/build/target/sota_client ${D}${bindir}/sota_client_cpp
}

FILES_${PN} = " \
                ${bindir}/sota_client_cpp \
		"
