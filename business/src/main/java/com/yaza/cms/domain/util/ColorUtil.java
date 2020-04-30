/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.util;

import com.itextpdf.text.BaseColor;

/**
 *
 * @author tasu
 */
public class ColorUtil {

    public static BaseColor getColor(String template) {
        switch (template) {
            case "nature-theme":
                return new BaseColor(62, 39, 35);
            case "light-theme":
                return new BaseColor(158, 158, 158);
            case "mat_teal":
                return new BaseColor(0, 150, 136);
            case "mat_deep_purple":
                return new BaseColor(103, 58, 183);
            case "mat_red":
                return new BaseColor(244, 67, 102);
            case "mat_orange":
                return new BaseColor(255, 152, 0);
            case "mat_gray":
                return new BaseColor(158, 158, 158);
            case "mat_blue":
                return new BaseColor(33, 150, 243);
            case "mat_green":
                return new BaseColor(76, 175, 80);
            case "mat_pink":
                return new BaseColor(216, 27, 96);
            case "mat_yellow":
                return new BaseColor(255, 245, 157);
            case "mat_indigo":
                return new BaseColor(63, 81, 181);
            case "mat_light_blue":
                return new BaseColor(129, 212, 250);
            case "mat_cyan":
                return new BaseColor(0, 188, 212);
            case "mat_light_green":
                return new BaseColor(139, 195, 74);
            case "mat_lime":
                return new BaseColor(205, 220, 57);
            case "mat_amber":
                return new BaseColor(225, 193, 7);
            case "mat_deep_orange":
                return new BaseColor(225, 87, 34);
            case "mat_blue_gray":
                return new BaseColor(69, 90, 100);
        }
        throw new IllegalArgumentException("Arguement not recognzied");
    }
    
    public static String getHexColor(String templateColor) {
        switch (templateColor) {
            case "nature-theme":
                return "3E2723";
            case "light-theme":
                return "9E9E9E";
            case "mat_teal":
                return "009688";
            case "mat_deep_purple":
                return "673AB7";
            case "mat_red":
                return "F44366";
            case "mat_orange":
                return "FF9800";
            case "mat_gray":
                return "9E9E9E";
            case "mat_blue":
                return "2196F3";
            case "mat_green":
                return "4CAF50";
            case "mat_pink":
                return "D81B60";
            case "mat_yellow":
                return "FFF59D";
            case "mat_indigo":
                return "3F51B5";
            case "mat_light_blue":
                return "81D4FA";
            case "mat_cyan":
                return "00BCD4";
            case "mat_light_green":
                return "8BC34A";
            case "mat_lime":
                return "CDDC39";
            case "mat_amber":
                return "E1C107";
            case "mat_deep_orange":
                return "E15722";
            case "mat_blue_gray":
                return "455A64";
        }
        throw new IllegalArgumentException("Arguement not recognzied");
    }
}
