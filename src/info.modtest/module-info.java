module info.modtest {
    requires info.modtest.face;
    uses info.modtest.face.ThingSayer;
    provides info.modtest.face.ThingSayer with info.modtest.ComputerSayer;
}
