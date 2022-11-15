package com.example.homework31.models

class Currency {
    var id = 0
    var code = ""
    var Ccy = ""
    var CcyNm_RU   = ""
    var CcyNm_UZ   = ""
    var CcyNm_UZC   = ""
    var CcyNm_EN   = ""
    var Nominal = ""
    var Rate = ""
    var Diff = ""
    var Date = ""

    constructor(
        id: Int,
        code: String,
        Ccy: String,
        CcyNm_RU: String,
        CcyNm_UZ: String,
        CcyNm_UZC: String,
        CcyNm_EN: String,
        Nominal: String,
        Rate: String,
        Diff: String,
        Date: String
    ) {
        this.id = id
        this.code = code
        this.Ccy = Ccy
        this.CcyNm_RU = CcyNm_RU
        this.CcyNm_UZ = CcyNm_UZ
        this.CcyNm_UZC = CcyNm_UZC
        this.CcyNm_EN = CcyNm_EN
        this.Nominal = Nominal
        this.Rate = Rate
        this.Diff = Diff
        this.Date = Date
    }

    constructor(
        code: String,
        Ccy: String,
        CcyNm_RU: String,
        CcyNm_UZ: String,
        CcyNm_UZC: String,
        CcyNm_EN: String,
        Nominal: String,
        Rate: String,
        Diff: String,
        Date: String
    ) {
        this.code = code
        this.Ccy = Ccy
        this.CcyNm_RU = CcyNm_RU
        this.CcyNm_UZ = CcyNm_UZ
        this.CcyNm_UZC = CcyNm_UZC
        this.CcyNm_EN = CcyNm_EN
        this.Nominal = Nominal
        this.Rate = Rate
        this.Diff = Diff
        this.Date = Date
    }


}