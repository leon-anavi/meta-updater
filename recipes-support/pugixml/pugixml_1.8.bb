SUMMARY = "Light-weight C++ XML Processing Library"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://readme.txt;beginline=33;endline=52;md5=0e7d05454930d4a0ddbc582254c6cba2"

SRC_URI = "https://github.com/zeux/pugixml/releases/download/v${PV}/pugixml-${PV}.tar.gz"
SRC_URI[md5sum] = "ffa59ee4853958e243050e6b690b4f2e"
SRC_URI[sha256sum] = "8ef26a51c670fbe79a71e9af94df4884d5a4b00a2db38a0608a87c14113b2904"

inherit cmake

EXTRA_OECMAKE = " -DBUILD_SHARED_LIBS=ON "

RDEPENDS_${PN}-dev = ""

PACKAGES = "${PN}-dev"

do_install() {
  install -d ${D}/${includedir}/pugixml
  install -m 0755 ${S}/src/pugixml.hpp ${D}/${includedir}/pugixml/
  install -m 0755 ${S}/src/pugiconfig.hpp ${D}/${includedir}/pugixml/
}

FILES_${PN}-dev += " \
  ${libdir}/cmake \
  ${includedir}/pugixml.hpp \
"
