package com.pixsterstudio.androidarckxml.utils

object Constants {

    const val IS_LOGIN = "isLogin"
    const val IS_FROM_SETTINGS = "isFromSettings"
    const val DONT_SHOW_ON_BOARDING = "showOnboarding"
    const val IS_ANONYMOUS = "isAnonymous"
    const val DATA = "data"
    const val DATA_LIST = "data_list"
    const val TRIM_OPTIONS = "trim_options"
    const val TEXT = "text"
    const val yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss"
    const val dd_mm_yyyy = "dd-MM-yyyy"
    const val dd_mmm_yyyy = "dd MMM yyyy"
    const val dd_mmmm_yyyy = "dd MMMM yyyy"
    const val TITLE = "title"
    const val FILE_PATH = "path"

    const val FILE_NAME = "Captions"
    const val TEXT_MIME = "text/plain"
    const val VIDEO_MIME = "video/mp4"
    const val FOLDER_NAME_SUBTITLES = "Captions/Subtitles"
    const val FOLDER_NAME_VIDEO = "Captions"

    val hexTransparencies = mapOf(
        100 to "FF",
        99 to "FC",
        98 to "FA",
        97 to "F7",
        96 to "F5",
        95 to "F2",
        94 to "F0",
        93 to "ED",
        92 to "EB",
        91 to "E8",
        90 to "E6",
        89 to "E3",
        88 to "E0",
        87 to "DE",
        86 to "DB",
        85 to "D9",
        84 to "D6",
        83 to "D4",
        82 to "D1",
        81 to "CF",
        80 to "CC",
        79 to "C9",
        78 to "C7",
        77 to "C4",
        76 to "C2",
        75 to "BF",
        74 to "BD",
        73 to "BA",
        72 to "B8",
        71 to "B5",
        70 to "B3",
        69 to "B0",
        68 to "AD",
        67 to "AB",
        66 to "A8",
        65 to "A6",
        64 to "A3",
        63 to "A1",
        62 to "9E",
        61 to "9C",
        60 to "99",
        59 to "96",
        58 to "94",
        57 to "91",
        56 to "8F",
        55 to "8C",
        54 to "8A",
        53 to "87",
        52 to "85",
        51 to "82",
        50 to "80",
        49 to "7D",
        48 to "7A",
        47 to "78",
        46 to "75",
        45 to "73",
        44 to "70",
        43 to "6E",
        42 to "6B",
        41 to "69",
        40 to "66",
        39 to "63",
        38 to "61",
        37 to "5E",
        36 to "5C",
        35 to "59",
        34 to "57",
        33 to "54",
        32 to "52",
        31 to "4F",
        30 to "4D",
        29 to "4A",
        28 to "47",
        27 to "45",
        26 to "42",
        25 to "40",
        24 to "3D",
        23 to "3B",
        22 to "38",
        21 to "36",
        20 to "33",
        19 to "30",
        18 to "2E",
        17 to "2B",
        16 to "29",
        15 to "26",
        14 to "24",
        13 to "21",
        12 to "1F",
        11 to "1C",
        10 to "1A",
        9 to "17",
        8 to "14",
        7 to "12",
        6 to "0F",
        5 to "0D",
        4 to "0A",
        3 to "08",
        2 to "05",
        1 to "03",
        0 to "00"
    )

    object InApp{
        //------Purchase listener error state-----//
        const val PURCHASE_ERROR_USER_CANCELLED=1
        const val PURCHASE_ERROR_ITEM_ALREADY_OWN=2
        const val PURCHASE_ERROR_DEVELOPER_ERROR=3
        const val PURCHASE_ERROR_SERVICE_DISCONNECTED=-1

        //   const val SILVER = "com.pixsterstudio.captions.1week"
        const val WEEKLY = "com.pixsterstudio.captions.weekly"
        const val MONTHLY = "com.pixsterstudio.captions.monthly"
        const val YEARLY = "com.pixsterstudio.captions.yearly"

        //------Billing client initializeState------/
        const val BILLING_CLIENT_STATUS_SUCCESS=1
        const val BILLING_CLIENT_STATUS_FAIL=0
    }

    object FolderNames{
        const val EXPORTED_VIDEOS = "ExportedVideos"
        const val TRIMMED_VIDEOS = "TrimmedVideos"
        const val MERGED_VIDEOS = "MargedVideos"
        const val AUDIO = "Audio"
    }

    object ExtensionNames{
        const val MP4 = ".mp4"
        const val MP3 = ".mp3"
    }

    object WebViews {
        const val WEBVIEW_URL = ""
        const val ABOUT_US = "http://13.234.234.244/smartsociety/home/about_us"
        const val TERMS_AND_CONDITIONS = "http://13.234.234.244/smartsociety/home/termscondition"
        const val PRIVACY_POLICY = "http://13.234.234.244/smartsociety/home/privacypolicy"
        const val HELPNFAQ = "http://13.234.234.244/smartsociety/home/faq"




    }
}