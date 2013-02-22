package com.modularsistemas.modpdf;




public class Helvetica extends CoreFont
{

    public Helvetica()
    {
        bBoxLLx = -166;
        bBoxLLy = -225;
        bBoxURx = 1000;
        bBoxURy = 931;
        underlinePosition = -100;
        underlineThickness = 50;
    }

    protected int getBBoxLLx()
    {
        return bBoxLLx;
    }

    protected int getBBoxLLy()
    {
        return bBoxLLy;
    }

    protected int getBBoxURx()
    {
        return bBoxURx;
    }

    protected int getBBoxURy()
    {
        return bBoxURy;
    }

    protected int getUnderlinePosition()
    {
        return underlinePosition;
    }

    protected int getUnderlineThickness()
    {
        return underlineThickness;
    }

    protected int[][] getMetrics()
    {
        return data;
    }

    int bBoxLLx;
    int bBoxLLy;
    int bBoxURx;
    int bBoxURy;
    int underlinePosition;
    int underlineThickness;
    protected static final String notice = "Copyright (c) 1985, 1987, 1989, 1990, 1997 Adobe Systems Incorporated.  All Rights Reserved.Helvetica is a trademark of Linotype-Hell AG and/or its subsidiaries.";
    int data[][] = {
        {
            32, 278, 84, -50, 86, -50, 87, -40, 89, -90, 
            221, -90, 159, -90, 147, -30, 145, -60
        }, {
            33, 278
        }, {
            34, 355
        }, {
            35, 556
        }, {
            36, 556
        }, {
            37, 889
        }, {
            38, 667
        }, {
            39, 191
        }, {
            40, 333
        }, {
            41, 333
        }, {
            42, 389
        }, {
            43, 584
        }, {
            44, 278, 148, -100, 146, -100
        }, {
            45, 333
        }, {
            46, 278, 148, -100, 146, -100, 32, -60
        }, {
            47, 278
        }, {
            48, 556
        }, {
            49, 556
        }, {
            50, 556
        }, {
            51, 556
        }, {
            52, 556
        }, {
            53, 556
        }, {
            54, 556
        }, {
            55, 556
        }, {
            56, 556
        }, {
            57, 556
        }, {
            58, 278, 32, -50
        }, {
            59, 278, 32, -50
        }, {
            60, 584
        }, {
            61, 584
        }, {
            62, 584
        }, {
            63, 556
        }, {
            64, 1015
        }, {
            65, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            66, 667, 85, -10, 218, -10, 219, -10, 220, -10, 
            217, -10, 44, -20, 46, -20
        }, {
            67, 722, 44, -30, 46, -30
        }, {
            68, 722, 65, -40, 193, -40, 194, -40, 196, -40, 
            192, -40, 197, -40, 195, -40, 86, -70, 87, -40, 
            89, -90, 221, -90, 159, -90, 44, -70, 46, -70
        }, {
            69, 667
        }, {
            70, 611, 65, -80, 193, -80, 194, -80, 196, -80, 
            192, -80, 197, -80, 195, -80, 97, -50, 225, -50, 
            226, -50, 228, -50, 224, -50, 229, -50, 227, -50, 
            44, -150, 101, -30, 233, -30, 234, -30, 235, -30, 
            232, -30, 111, -30, 243, -30, 244, -30, 246, -30, 
            242, -30, 248, -30, 245, -30, 46, -150, 114, -45
        }, {
            71, 778
        }, {
            72, 722
        }, {
            73, 278
        }, {
            74, 500, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 97, -20, 225, -20, 
            226, -20, 228, -20, 224, -20, 229, -20, 227, -20, 
            44, -30, 46, -30, 117, -20, 250, -20, 251, -20, 
            252, -20, 249, -20
        }, {
            75, 667, 79, -50, 211, -50, 212, -50, 214, -50, 
            210, -50, 216, -50, 213, -50, 101, -40, 233, -40, 
            234, -40, 235, -40, 232, -40, 111, -40, 243, -40, 
            244, -40, 246, -40, 242, -40, 248, -40, 245, -40, 
            117, -30, 250, -30, 251, -30, 252, -30, 249, -30, 
            121, -50, 253, -50, 255, -50
        }, {
            76, 556, 84, -110, 86, -110, 87, -70, 89, -140, 
            221, -140, 159, -140, 148, -140, 146, -160, 121, -30, 
            253, -30, 255, -30
        }, {
            77, 833
        }, {
            78, 722
        }, {
            79, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            80, 667, 65, -120, 193, -120, 194, -120, 196, -120, 
            192, -120, 197, -120, 195, -120, 97, -40, 225, -40, 
            226, -40, 228, -40, 224, -40, 229, -40, 227, -40, 
            44, -180, 101, -50, 233, -50, 234, -50, 235, -50, 
            232, -50, 111, -50, 243, -50, 244, -50, 246, -50, 
            242, -50, 248, -50, 245, -50, 46, -180
        }, {
            81, 778, 85, -10, 218, -10, 219, -10, 220, -10, 
            217, -10
        }, {
            82, 722, 79, -20, 211, -20, 212, -20, 214, -20, 
            210, -20, 216, -20, 213, -20, 84, -30, 85, -40, 
            218, -40, 219, -40, 220, -40, 217, -40, 86, -50, 
            87, -30, 89, -50, 221, -50, 159, -50
        }, {
            83, 667, 44, -20, 46, -20
        }, {
            84, 611, 65, -120, 193, -120, 194, -120, 196, -120, 
            192, -120, 197, -120, 195, -120, 79, -40, 211, -40, 
            212, -40, 214, -40, 210, -40, 216, -40, 213, -40, 
            97, -120, 225, -120, 226, -120, 228, -120, 224, -120, 
            229, -120, 227, -60, 58, -20, 44, -120, 101, -120, 
            233, -120, 234, -120, 235, -120, 232, -60, 45, -140, 
            111, -120, 243, -120, 244, -120, 246, -120, 242, -120, 
            248, -120, 245, -60, 46, -120, 114, -120, 59, -20, 
            117, -120, 250, -120, 251, -120, 252, -120, 249, -120, 
            119, -120, 121, -120, 253, -120, 255, -60
        }, {
            85, 722, 65, -40, 193, -40, 194, -40, 196, -40, 
            192, -40, 197, -40, 195, -40, 44, -40, 46, -40
        }, {
            86, 667, 65, -80, 193, -80, 194, -80, 196, -80, 
            192, -80, 197, -80, 195, -80, 71, -40, 79, -40, 
            211, -40, 212, -40, 214, -40, 210, -40, 216, -40, 
            213, -40, 97, -70, 225, -70, 226, -70, 228, -70, 
            224, -70, 229, -70, 227, -70, 58, -40, 44, -125, 
            101, -80, 233, -80, 234, -80, 235, -80, 232, -80, 
            45, -80, 111, -80, 243, -80, 244, -80, 246, -80, 
            242, -80, 248, -80, 245, -80, 46, -125, 59, -40, 
            117, -70, 250, -70, 251, -70, 252, -70, 249, -70
        }, {
            87, 944, 65, -50, 193, -50, 194, -50, 196, -50, 
            192, -50, 197, -50, 195, -50, 79, -20, 211, -20, 
            212, -20, 214, -20, 210, -20, 216, -20, 213, -20, 
            97, -40, 225, -40, 226, -40, 228, -40, 224, -40, 
            229, -40, 227, -40, 44, -80, 101, -30, 233, -30, 
            234, -30, 235, -30, 232, -30, 45, -40, 111, -30, 
            243, -30, 244, -30, 246, -30, 242, -30, 248, -30, 
            245, -30, 46, -80, 117, -30, 250, -30, 251, -30, 
            252, -30, 249, -30, 121, -20, 253, -20, 255, -20
        }, {
            88, 667
        }, {
            89, 667, 65, -110, 193, -110, 194, -110, 196, -110, 
            192, -110, 197, -110, 195, -110, 79, -85, 211, -85, 
            212, -85, 214, -85, 210, -85, 216, -85, 213, -85, 
            97, -140, 225, -140, 226, -140, 228, -140, 224, -140, 
            229, -140, 227, -140, 58, -60, 44, -140, 101, -140, 
            233, -140, 234, -140, 235, -140, 232, -140, 45, -140, 
            105, -20, 237, -20, 111, -140, 243, -140, 244, -140, 
            246, -140, 242, -140, 248, -140, 245, -140, 46, -140, 
            59, -60, 117, -110, 250, -110, 251, -110, 252, -110, 
            249, -110
        }, {
            90, 611
        }, {
            91, 278
        }, {
            92, 278
        }, {
            93, 278
        }, {
            94, 469
        }, {
            95, 556
        }, {
            96, 333
        }, {
            97, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            98, 556, 98, -10, 44, -40, 108, -20, 46, -40, 
            117, -20, 250, -20, 251, -20, 252, -20, 249, -20, 
            118, -20, 121, -20, 253, -20, 255, -20
        }, {
            99, 500, 44, -15, 107, -20
        }, {
            100, 556
        }, {
            101, 556, 44, -15, 46, -15, 118, -30, 119, -20, 
            120, -30, 121, -20, 253, -20, 255, -20
        }, {
            102, 278, 97, -30, 225, -30, 226, -30, 228, -30, 
            224, -30, 229, -30, 227, -30, 44, -30, 101, -30, 
            233, -30, 234, -30, 235, -30, 232, -30, 111, -30, 
            243, -30, 244, -30, 246, -30, 242, -30, 248, -30, 
            245, -30, 46, -30, 148, 60, 146, 50
        }, {
            103, 556, 114, -10
        }, {
            104, 556, 121, -30, 253, -30, 255, -30
        }, {
            105, 222
        }, {
            106, 222
        }, {
            107, 500, 101, -20, 233, -20, 234, -20, 235, -20, 
            232, -20, 111, -20, 243, -20, 244, -20, 246, -20, 
            242, -20, 248, -20, 245, -20
        }, {
            108, 222
        }, {
            109, 833, 117, -10, 250, -10, 251, -10, 252, -10, 
            249, -10, 121, -15, 253, -15, 255, -15
        }, {
            110, 556, 117, -10, 250, -10, 251, -10, 252, -10, 
            249, -10, 118, -20, 121, -15, 253, -15, 255, -15
        }, {
            111, 556, 44, -40, 46, -40, 118, -15, 119, -15, 
            120, -30, 121, -30, 253, -30, 255, -30
        }, {
            112, 556, 44, -35, 46, -35, 121, -30, 253, -30, 
            255, -30
        }, {
            113, 556
        }, {
            114, 333, 97, -10, 225, -10, 226, -10, 228, -10, 
            224, -10, 229, -10, 227, -10, 58, 30, 44, -50, 
            105, 15, 237, 15, 238, 15, 239, 15, 236, 15, 
            107, 15, 108, 15, 109, 25, 110, 25, 241, 25, 
            112, 30, 46, -50, 59, 30, 116, 40, 117, 15, 
            250, 15, 251, 15, 252, 15, 249, 15, 118, 30, 
            121, 30, 253, 30, 255, 30
        }, {
            115, 500, 44, -15, 46, -15, 119, -30
        }, {
            116, 278
        }, {
            117, 556
        }, {
            118, 500, 97, -25, 225, -25, 226, -25, 228, -25, 
            224, -25, 229, -25, 227, -25, 44, -80, 101, -25, 
            233, -25, 234, -25, 235, -25, 232, -25, 111, -25, 
            243, -25, 244, -25, 246, -25, 242, -25, 248, -25, 
            245, -25, 46, -80
        }, {
            119, 722, 97, -15, 225, -15, 226, -15, 228, -15, 
            224, -15, 229, -15, 227, -15, 44, -60, 101, -10, 
            233, -10, 234, -10, 235, -10, 232, -10, 111, -10, 
            243, -10, 244, -10, 246, -10, 242, -10, 248, -10, 
            245, -10, 46, -60
        }, {
            120, 500, 101, -30, 233, -30, 234, -30, 235, -30, 
            232, -30
        }, {
            121, 500, 97, -20, 225, -20, 226, -20, 228, -20, 
            224, -20, 229, -20, 227, -20, 44, -100, 101, -20, 
            233, -20, 234, -20, 235, -20, 232, -20, 111, -20, 
            243, -20, 244, -20, 246, -20, 242, -20, 248, -20, 
            245, -20, 46, -100
        }, {
            122, 500, 101, -15, 233, -15, 234, -15, 235, -15, 
            232, -15, 111, -15, 243, -15, 244, -15, 246, -15, 
            242, -15, 248, -15, 245, -15
        }, {
            123, 334
        }, {
            124, 260
        }, {
            125, 334
        }, {
            126, 584
        }, {
            127, 278
        }, {
            128, 556
        }, {
            129, 278
        }, {
            130, 222
        }, {
            131, 556
        }, {
            132, 333
        }, {
            133, 1000
        }, {
            134, 556
        }, {
            135, 556
        }, {
            136, 333
        }, {
            137, 1000
        }, {
            138, 667, 44, -20, 46, -20
        }, {
            139, 333
        }, {
            140, 1000
        }, {
            141, 278
        }, {
            142, 611
        }, {
            143, 278
        }, {
            144, 278
        }, {
            145, 222, 145, -57
        }, {
            146, 222, 100, -50, 146, -57, 114, -50, 115, -50, 
            154, -50, 32, -70
        }, {
            147, 333
        }, {
            148, 333, 32, -40
        }, {
            149, 350
        }, {
            150, 556
        }, {
            151, 1000
        }, {
            152, 333
        }, {
            153, 1000
        }, {
            154, 500, 44, -15, 46, -15, 119, -30
        }, {
            155, 333
        }, {
            156, 944
        }, {
            157, 278
        }, {
            158, 500, 101, -15, 233, -15, 234, -15, 235, -15, 
            232, -15, 111, -15, 243, -15, 244, -15, 246, -15, 
            242, -15, 248, -15, 245, -15
        }, {
            159, 667, 65, -110, 193, -110, 194, -110, 196, -110, 
            192, -110, 197, -110, 195, -110, 79, -85, 211, -85, 
            212, -85, 214, -85, 210, -85, 216, -85, 213, -85, 
            97, -140, 225, -140, 226, -140, 228, -140, 224, -140, 
            229, -140, 227, -70, 58, -60, 44, -140, 101, -140, 
            233, -140, 234, -140, 235, -140, 232, -140, 45, -140, 
            105, -20, 237, -20, 111, -140, 243, -140, 244, -140, 
            246, -140, 242, -140, 248, -140, 245, -140, 46, -140, 
            59, -60, 117, -110, 250, -110, 251, -110, 252, -110, 
            249, -110
        }, {
            160, 278
        }, {
            161, 333
        }, {
            162, 556
        }, {
            163, 556
        }, {
            164, 556
        }, {
            165, 556
        }, {
            166, 260
        }, {
            167, 556
        }, {
            168, 333
        }, {
            169, 737
        }, {
            170, 370
        }, {
            171, 556
        }, {
            172, 584
        }, {
            173, 278
        }, {
            174, 737
        }, {
            175, 333
        }, {
            176, 400
        }, {
            177, 584
        }, {
            178, 333
        }, {
            179, 333
        }, {
            180, 333
        }, {
            181, 556
        }, {
            182, 537
        }, {
            183, 278
        }, {
            184, 333
        }, {
            185, 333
        }, {
            186, 365
        }, {
            187, 556
        }, {
            188, 834
        }, {
            189, 834
        }, {
            190, 834
        }, {
            191, 611
        }, {
            192, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            193, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            194, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            195, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            196, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            197, 667, 67, -30, 199, -30, 71, -30, 79, -30, 
            211, -30, 212, -30, 214, -30, 210, -30, 216, -30, 
            213, -30, 81, -30, 84, -120, 85, -50, 218, -50, 
            219, -50, 220, -50, 217, -50, 86, -70, 87, -50, 
            89, -100, 221, -100, 159, -100, 117, -30, 250, -30, 
            251, -30, 252, -30, 249, -30, 118, -40, 119, -40, 
            121, -40, 253, -40, 255, -40
        }, {
            198, 1000
        }, {
            199, 722, 44, -30, 46, -30
        }, {
            200, 667
        }, {
            201, 667
        }, {
            202, 667
        }, {
            203, 667
        }, {
            204, 278
        }, {
            205, 278
        }, {
            206, 278
        }, {
            207, 278
        }, {
            208, 722
        }, {
            209, 722
        }, {
            210, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            211, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            212, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            213, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            214, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            215, 584
        }, {
            216, 778, 65, -20, 193, -20, 194, -20, 196, -20, 
            192, -20, 197, -20, 195, -20, 84, -40, 86, -50, 
            87, -30, 88, -60, 89, -70, 221, -70, 159, -70, 
            44, -40, 46, -40
        }, {
            217, 722, 65, -40, 193, -40, 194, -40, 196, -40, 
            192, -40, 197, -40, 195, -40, 44, -40, 46, -40
        }, {
            218, 722, 65, -40, 193, -40, 194, -40, 196, -40, 
            192, -40, 197, -40, 195, -40, 44, -40, 46, -40
        }, {
            219, 722, 65, -40, 193, -40, 194, -40, 196, -40, 
            192, -40, 197, -40, 195, -40, 44, -40, 46, -40
        }, {
            220, 722, 65, -40, 193, -40, 194, -40, 196, -40, 
            192, -40, 197, -40, 195, -40, 44, -40, 46, -40
        }, {
            221, 667, 65, -110, 193, -110, 194, -110, 196, -110, 
            192, -110, 197, -110, 195, -110, 79, -85, 211, -85, 
            212, -85, 214, -85, 210, -85, 216, -85, 213, -85, 
            97, -140, 225, -140, 226, -140, 228, -140, 224, -140, 
            229, -140, 227, -70, 58, -60, 44, -140, 101, -140, 
            233, -140, 234, -140, 235, -140, 232, -140, 45, -140, 
            105, -20, 237, -20, 111, -140, 243, -140, 244, -140, 
            246, -140, 242, -140, 248, -140, 245, -140, 46, -140, 
            59, -60, 117, -110, 250, -110, 251, -110, 252, -110, 
            249, -110
        }, {
            222, 667
        }, {
            223, 611
        }, {
            224, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            225, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            226, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            227, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            228, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            229, 556, 118, -20, 119, -20, 121, -30, 253, -30, 
            255, -30
        }, {
            230, 889
        }, {
            231, 500, 44, -15, 107, -20
        }, {
            232, 556, 44, -15, 46, -15, 118, -30, 119, -20, 
            120, -30, 121, -20, 253, -20, 255, -20
        }, {
            233, 556, 44, -15, 46, -15, 118, -30, 119, -20, 
            120, -30, 121, -20, 253, -20, 255, -20
        }, {
            234, 556, 44, -15, 46, -15, 118, -30, 119, -20, 
            120, -30, 121, -20, 253, -20, 255, -20
        }, {
            235, 556, 44, -15, 46, -15, 118, -30, 119, -20, 
            120, -30, 121, -20, 253, -20, 255, -20
        }, {
            236, 278
        }, {
            237, 278
        }, {
            238, 278
        }, {
            239, 278
        }, {
            240, 556
        }, {
            241, 556, 117, -10, 250, -10, 251, -10, 252, -10, 
            249, -10, 118, -20, 121, -15, 253, -15, 255, -15
        }, {
            242, 556, 44, -40, 46, -40, 118, -15, 119, -15, 
            120, -30, 121, -30, 253, -30, 255, -30
        }, {
            243, 556, 44, -40, 46, -40, 118, -15, 119, -15, 
            120, -30, 121, -30, 253, -30, 255, -30
        }, {
            244, 556, 44, -40, 46, -40, 118, -15, 119, -15, 
            120, -30, 121, -30, 253, -30, 255, -30
        }, {
            245, 556, 44, -40, 46, -40, 118, -15, 119, -15, 
            120, -30, 121, -30, 253, -30, 255, -30
        }, {
            246, 556, 44, -40, 46, -40, 118, -15, 119, -15, 
            120, -30, 121, -30, 253, -30, 255, -30
        }, {
            247, 584
        }, {
            248, 611, 97, -55, 225, -55, 226, -55, 228, -55, 
            224, -55, 229, -55, 227, -55, 98, -55, 99, -55, 
            231, -55, 44, -95, 100, -55, 101, -55, 233, -55, 
            234, -55, 235, -55, 232, -55, 102, -55, 103, -55, 
            104, -55, 105, -55, 237, -55, 238, -55, 239, -55, 
            236, -55, 106, -55, 107, -55, 108, -55, 109, -55, 
            110, -55, 241, -55, 111, -55, 243, -55, 244, -55, 
            246, -55, 242, -55, 248, -55, 245, -55, 112, -55, 
            46, -95, 113, -55, 114, -55, 115, -55, 154, -55, 
            116, -55, 117, -55, 250, -55, 251, -55, 252, -55, 
            249, -55, 118, -70, 119, -70, 120, -85, 121, -70, 
            253, -70, 255, -70, 122, -55, 158, -55
        }, {
            249, 556
        }, {
            250, 556
        }, {
            251, 556
        }, {
            252, 556
        }, {
            253, 500, 97, -20, 225, -20, 226, -20, 228, -20, 
            224, -20, 229, -20, 227, -20, 44, -100, 101, -20, 
            233, -20, 234, -20, 235, -20, 232, -20, 111, -20, 
            243, -20, 244, -20, 246, -20, 242, -20, 248, -20, 
            245, -20, 46, -100
        }, {
            254, 556
        }, {
            255, 500, 97, -20, 225, -20, 226, -20, 228, -20, 
            224, -20, 229, -20, 227, -20, 44, -100, 101, -20, 
            233, -20, 234, -20, 235, -20, 232, -20, 111, -20, 
            243, -20, 244, -20, 246, -20, 242, -20, 248, -20, 
            245, -20, 46, -100
        }
    };
}